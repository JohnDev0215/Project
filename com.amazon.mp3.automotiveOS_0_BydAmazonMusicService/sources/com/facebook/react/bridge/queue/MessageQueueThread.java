package com.facebook.react.bridge.queue;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface MessageQueueThread {
    @DoNotStrip
    void assertIsOnThread();

    @DoNotStrip
    void assertIsOnThread(String str);

    @DoNotStrip
    <T> Future<T> callOnQueue(Callable<T> callable);

    @DoNotStrip
    MessageQueueThreadPerfStats getPerfStats();

    @DoNotStrip
    boolean isOnThread();

    @DoNotStrip
    void quitSynchronous();

    @DoNotStrip
    void resetPerfStats();

    @DoNotStrip
    boolean runOnQueue(Runnable runnable);
}
