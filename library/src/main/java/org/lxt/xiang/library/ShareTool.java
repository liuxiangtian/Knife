package org.lxt.xiang.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * It is never too late to learn.
 */
public class ShareTool {

    private static final String TAG = "ShareTool";

    private static final String SHARE_FILE_NAME = "share_file";
    private static final String GET_GET = "get";
    private static final String GET_LOAD = "load";
    private static final String PUT_PUT = "put";
    private static final String PUT_SAVE = "save";

    public static <T> T create(final Context context, final Class<T> cls) {
        validateServiceInterface(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //1.解析方法，处理注解，提取信息
                //2.缓存方法，避免反射
                //3.利用信息实现功能
                Annotation[] annotations = method.getAnnotations();
                for (Annotation anno : annotations) {
                    if (anno instanceof GET) {
                        Object key = handleGET(method, (GET) anno, context);
                        if (key != null) return key;
                    } else if (anno instanceof PUT) {
                        handlePut(method, args, (PUT) anno, context);
                    }
                }
                return null;
            }
        });

    }

    private static void handlePut(Method method, Object[] args, PUT anno, Context context) {
        PUT put = anno;
        String key = put.key();
        if (key.isEmpty() || key.trim().isEmpty()) {
            key = method.getName();
            if (key.startsWith(PUT_PUT)) {
                key = key.replaceFirst(PUT_PUT, "").toUpperCase();
            } else if(key.startsWith(PUT_SAVE)){
                key = key.replaceFirst(PUT_SAVE, "").toUpperCase();
            }
        }
        if (!key.isEmpty()) {
             Log.i(TAG, "------------------------------");
             Log.i(TAG, "------------------------------");
            Log.i(TAG, "handlePut  : " + key);
            SharedPreferences prefs = context.getSharedPreferences(SHARE_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            if (args.length == 0) return ;
            Object firstArgs = args[0];
            Class firstCls = firstArgs.getClass();
            Log.i(TAG, "handlePut: " + firstArgs.toString());
            Log.i(TAG, "handlePut: " + firstArgs.getClass().getSimpleName());
            Log.i(TAG, "handlePut: " + (firstCls == Integer.class));
            if (firstCls == String.class) {
                editor.putString(key, (String) firstArgs);
            } else if (firstCls == Integer.class) {
                editor.putInt(key, ((Integer)firstArgs).intValue());
            }
            editor.commit();
        }
    }

    @Nullable
    private static Object handleGET(Method method, GET anno, Context context) {
        GET get = anno;
        String key = get.key();
        if (key.isEmpty() || key.trim().isEmpty()) {
            key = method.getName();
            if (key.startsWith(GET_GET)) {
                key = key.replaceFirst(GET_GET, "").toUpperCase();
            }
        }
        if (!key.isEmpty()) {
            SharedPreferences prefs = context.getSharedPreferences(SHARE_FILE_NAME, Context.MODE_PRIVATE);
            Class returnCls = method.getReturnType();
            if (returnCls == String.class) {
                return prefs.getString(key, "default value");
            } else if (returnCls == int.class) {
                return prefs.getInt(key, 999);
            }
        }
        return null;
    }

    static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        } else if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

}
