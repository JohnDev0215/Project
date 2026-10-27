package com.ts.car.timesync;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "TimeSyncService";

    public static void debug(String tag, String msg) {
        Log.d(TAG, "@" + tag + "@: " + msg);
    }

    public static void err(String tag, String msg) {
        Log.e(TAG, "@" + tag + "@: " + msg);
    }

    public static void info(String tag, String msg) {
        Log.i(TAG, "@" + tag + "@: " + msg);
    }

    public static void warning(String tag, String msg) {
        Log.w(TAG, "@" + tag + "@: " + msg);
    }

    public static void warning(String tag, String msg, Throwable tr) {
        Log.w(TAG, "@" + tag + "@: " + msg, tr);
    }
}
