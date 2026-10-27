package com.android.server.telecom;

import android.os.Looper;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public final class ThreadUtil {
    private static final String TAG = "ThreadUtil";

    public static boolean isOnMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void checkNotOnMainThread() {
        if (isOnMainThread()) {
            Log.wtf(TAG, new IllegalStateException(), "Must not be on the main thread!", new Object[0]);
        }
    }
}
