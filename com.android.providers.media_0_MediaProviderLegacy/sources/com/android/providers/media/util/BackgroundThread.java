package com.android.providers.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Executor;

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

    public static Executor getExecutor() {
        HandlerExecutor handlerExecutor;
        synchronized (BackgroundThread.class) {
            ensureThreadLocked();
            handlerExecutor = sHandlerExecutor;
        }
        return handlerExecutor;
    }
}
