package com.amazon.music.arcus;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public class ThreadUtils {
    private static final Looper INSTANCE1 = getNewBackgroundLooper(1);
    private static final Looper INSTANCE2 = getNewBackgroundLooper(2);
    private static final String TAG = "ThreadUtils";

    public static Handler getBackgroundHandler() {
        return new Handler(getBackgroundLooper());
    }

    private static Looper getBackgroundLooper() {
        return INSTANCE1;
    }

    private static Looper getNewBackgroundLooper(int i) {
        HandlerThread handlerThread = new HandlerThread(String.format("%s%s_%d", TAG, HandlerThread.class.getSimpleName(), Integer.valueOf(i)), 3);
        handlerThread.start();
        return handlerThread.getLooper();
    }
}
