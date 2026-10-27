package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider;
import com.amazon.music.media.playback.util.impl.ThreadUtilsProvider;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ThreadUtils {
    private static ThreadUtilsProvider provider = new DefaultThreadUtilsProvider();

    public static ScheduledExecutorService getBackgroundExecutor() {
        return provider.getBackgroundExecutor();
    }

    public static ExecutorService createBackgroundExecutor(String str) {
        return provider.createBackgroundExecutor(str);
    }

    public static Executor getMainThreadExecutor() {
        return provider.getMainThreadExecutor();
    }

    public static boolean isOnMainThread() {
        return provider.isOnMainThread();
    }

    public static void runOnMainThread(Runnable runnable) {
        Validate.notNull(runnable);
        if (isOnMainThread()) {
            runnable.run();
        } else {
            postOnMainThread(runnable);
        }
    }

    public static void postOnMainThread(Runnable runnable) {
        Validate.notNull(runnable);
        provider.postOnMainThread(runnable);
    }

    public static void postOnMainThreadDelayed(Runnable runnable, long j) {
        Validate.notNull(runnable);
        provider.postOnMainThreadDelayed(runnable, j);
    }

    public static void callOnMainThread(@NonNull Runnable runnable) {
        try {
            callOnMainThread(new RunnableCaller(runnable));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T callOnMainThread(final Callable<T> callable) throws Exception {
        T t;
        Validate.notNull(callable);
        if (isOnMainThread()) {
            return callable.call();
        }
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        synchronized (atomicReference2) {
            provider.postOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.util.ThreadUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        atomicReference.set(callable.call());
                    } catch (Exception e) {
                        atomicReference2.set(e);
                    }
                    synchronized (atomicReference2) {
                        atomicReference2.notifyAll();
                    }
                }
            });
            boolean z = false;
            while (true) {
                try {
                    atomicReference2.wait();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            Exception exc = (Exception) atomicReference2.get();
            if (exc != null) {
                throw exc;
            }
            t = (T) atomicReference.get();
        }
        return t;
    }

    public static void cancelOnMainThread(Runnable runnable) {
        Validate.notNull(runnable);
        provider.cancelOnMainThread(runnable);
    }

    public static void runInBackground(Runnable runnable) {
        Validate.notNull(runnable);
        if (!isOnMainThread()) {
            runnable.run();
        } else {
            postInBackground(runnable);
        }
    }

    public static void postInBackground(Runnable runnable) {
        Validate.notNull(runnable);
        provider.postInBackground(runnable);
    }

    public static ScheduledFuture<?> postInBackgroundDelayed(Runnable runnable, long j) {
        Validate.notNull(runnable);
        return provider.postInBackgroundDelayed(new RunnableCaller(runnable), j);
    }

    private ThreadUtils() {
    }
}
