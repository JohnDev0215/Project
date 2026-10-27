package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface JavaJSExecutor {

    public interface Factory {
        JavaJSExecutor create() throws Exception;
    }

    void close();

    @DoNotStrip
    String executeJSCall(String str, String str2) throws ProxyExecutorException;

    @DoNotStrip
    void loadBundle(String str) throws ProxyExecutorException;

    @DoNotStrip
    void setGlobalVariable(String str, String str2);

    public static class ProxyExecutorException extends Exception {
        public ProxyExecutorException(Throwable th) {
            super(th);
        }
    }
}
