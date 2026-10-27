package com.android.providers.media.util;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Executor;

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

    public static Executor getExecutor() {
        HandlerExecutor handlerExecutor;
        synchronized (ForegroundThread.class) {
            ensureThreadLocked();
            handlerExecutor = sHandlerExecutor;
        }
        return handlerExecutor;
    }
}
