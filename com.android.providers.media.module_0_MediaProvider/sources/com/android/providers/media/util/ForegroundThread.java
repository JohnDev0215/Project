package com.android.providers.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class ForegroundThread extends HandlerThread {
    private static Handler sHandler;
    private static HandlerExecutor sHandlerExecutor;
    private static ForegroundThread sInstance;

    private ForegroundThread() {
        super("fg", -2);
    }

    private static void ensureThreadLocked() {
        if (sInstance == null) {
            ForegroundThread foregroundThread = new ForegroundThread();
            sInstance = foregroundThread;
            foregroundThread.start();
            Handler handler = new Handler(sInstance.getLooper());
            sHandler = handler;
            sHandlerExecutor = new HandlerExecutor(handler);
        }
    }

    public static ForegroundThread get() {
        ForegroundThread foregroundThread;
        synchronized (ForegroundThread.class) {
            ensureThreadLocked();
            foregroundThread = sInstance;
        }
        return foregroundThread;
    }

    public static Handler getHandler() {
        Handler handler;
        synchronized (ForegroundThread.class) {
            ensureThreadLocked();
            handler = sHandler;
        }
        return handler;
    }

    public static Executor getExecutor() {
        HandlerExecutor handlerExecutor;
        synchronized (ForegroundThread.class) {
            ensureThreadLocked();
            handlerExecutor = sHandlerExecutor;
        }
        return handlerExecutor;
    }

    public static void waitForIdle() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        getExecutor().execute(new Runnable() { // from class: com.android.providers.media.util.-$$Lambda$ForegroundThread$x2gQq5pKMPcFLLeSEHbZ5j52dn8
            @Override // java.lang.Runnable
            public final void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
