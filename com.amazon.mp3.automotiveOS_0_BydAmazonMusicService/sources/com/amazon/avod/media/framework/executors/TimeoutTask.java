package com.amazon.avod.media.framework.executors;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public interface TimeoutTask {

    public interface TimeoutCallable<V> extends Callable<V>, TimeoutTask {
    }

    public interface TimeoutRunnable extends Runnable, TimeoutTask {
    }

    long getTimeoutInNanos();

    void onCancel();

    void postRun();
}
