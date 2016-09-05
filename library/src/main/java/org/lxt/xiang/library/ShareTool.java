package org.lxt.xiang.library;

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

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

    private static Settings settings = Settings.getInstance();
    private static Map<Method, ShareMethod> mShareMethodCache = new HashMap<>();

    public static <T> T create(final Context context, final Class<T> cls) {
        validateServiceInterface(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ShareMethod shareMethod = loadShareMethod(method,args);
                Handler handler = new Handler(shareMethod, context);
                return handler.call();
            }
        });

    }

    private static ShareMethod loadShareMethod(Method method, Object[] args) {
        ShareMethod shareMethod = mShareMethodCache.get(method);
        if(shareMethod==null){
            Annotation[] annotations = method.getAnnotations();
            for (Annotation anno : annotations) {
                shareMethod = handleAnno(anno,method,args);
                if (shareMethod != null) {
                    mShareMethodCache.put(method,shareMethod);
                    return shareMethod;
                }
            }
        }
        return shareMethod;
    }

    private static ShareMethod handleAnno(Annotation anno, Method method, Object[] args) {
        ShareMethod shareMethod = null;
        if(anno instanceof GET){
            GET get = (GET) anno;
            String key = getOptKey(get,method);
            shareMethod = new ShareMethod(ShareMethod.READ, key, method, args);
        } else if(anno instanceof PUT){
            PUT put = (PUT) anno;
            String key = getOptKey(put,method);
            shareMethod = new ShareMethod(ShareMethod.WRITE, key, method, args);
        }
        return shareMethod;
    }

    private static String getOptKey(PUT put, Method method) {
        String key = put.key();
        if(key.trim().isEmpty()){
            key = method.getName();
            for (String prefix : settings.getPUT_PREFIX()) {
                if(key.startsWith(prefix)){
                    key = key.replace(prefix,"").toUpperCase();
                    return key;
                }
            }
        }
        return key;
    }

    private static String getOptKey(GET get, Method method) {
        String key = get.key();
        if(key.trim().isEmpty()){
            key = method.getName();
            for (String prefix : settings.getGET_PREFIX()) {
                if(key.startsWith(prefix)){
                    key = key.replace(prefix,"").toUpperCase();
                    return key;
                }
            }
        }
        return key;
    }


    static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        } else if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

    public void addGetPrefix(final String prefix){
        settings.addGetPrefix(prefix);
    }

    public void addPutPrefix(final String prefix){
        settings.addPutPrefix(prefix);
    }

}
