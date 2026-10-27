package com.android.providers.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class BackgroundThread extends HandlerThread {
    private static Handler sHandler;
    private static HandlerExecutor sHandlerExecutor;
    private static BackgroundThread sInstance;

    private BackgroundThread() {
        super("bg", 10);
    }

    private static void ensureThreadLocked() {
        if (sInstance == null) {
            BackgroundThread backgroundThread = new BackgroundThread();
            sInstance = backgroundThread;
            backgroundThread.start();
            Handler handler = new Handler(sInstance.getLooper());
            sHandler = handler;
            sHandlerExecutor = new HandlerExecutor(handler);
        }
    }

    public static BackgroundThread get() {
        BackgroundThread backgroundThread;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            backgroundThread = sInstance;
        }
        return backgroundThread;
    }

    public static Handler getHandler() {
        Handler handler;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            handler = sHandler;
        }
        return handler;
    }

    public static Executor getExecutor() {
        HandlerExecutor handlerExecutor;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            handlerExecutor = sHandlerExecutor;
        }
        return handlerExecutor;
    }

    public static void waitForIdle() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        getExecutor().execute(new Runnable() { // from class: com.android.providers.media.util.-$$Lambda$BackgroundThread$CNziD_H7D1eJBsqUqp1XUqtWOGg
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
