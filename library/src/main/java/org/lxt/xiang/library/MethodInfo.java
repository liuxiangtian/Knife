package org.lxt.xiang.library;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodInfo {

    public static final int GET = 1;
    public static final int PUT = 2;

    private int type;
    private String key;
    private Method method;
    private Type params;
    private Object[] args;
    private boolean isAsync;
    private Class returnType;

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    public Type getParams() {
        return params;
    }

    public void setParams(Type params) {
        this.params = params;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "type: "+type+" key: "+ key;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }


    public static class Builder {

        private MethodInfo methodInfo;

        public Builder() {
            methodInfo = new MethodInfo();
        }

        public Builder setType(int type) {
            methodInfo.setType(type);
            return this;
        }

        public Builder setKey(String key) {
            methodInfo.setKey(key);
            return this;
        }

        public Builder setMethod(Method method) {
            methodInfo.setMethod(method);
            return this;
        }

        public Builder setParams(Type params) {
            methodInfo.setParams(params);
            return this;
        }

        public Builder setArgs(Object[] args) {
            methodInfo.setArgs(args);
            return this;
        }

        public Builder setAsync(boolean async) {
            methodInfo.setAsync(async);
            return this;
        }

        public Builder setReturnType(Class returnType) {
            methodInfo.setReturnType(returnType);
            return this;
        }

        public MethodInfo build(){
            return methodInfo;
        }
    }

}
