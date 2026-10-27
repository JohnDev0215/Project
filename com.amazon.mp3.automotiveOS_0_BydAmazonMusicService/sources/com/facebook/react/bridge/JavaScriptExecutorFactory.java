package com.facebook.react.bridge;

/* JADX INFO: loaded from: classes6.dex */
public interface JavaScriptExecutorFactory {
    JavaScriptExecutor create() throws Exception;

    void startSamplingProfiler();

    void stopSamplingProfiler(String str);
}
