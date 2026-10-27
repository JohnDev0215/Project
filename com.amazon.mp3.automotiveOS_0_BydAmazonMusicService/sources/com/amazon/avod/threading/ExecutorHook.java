package com.amazon.avod.threading;

/* JADX INFO: loaded from: classes2.dex */
public interface ExecutorHook {
    void onAfterExecute(Runnable runnable, Throwable th);

    void onBeforeExecute(Runnable runnable);
}
