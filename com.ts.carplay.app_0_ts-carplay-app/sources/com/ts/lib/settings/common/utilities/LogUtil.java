package com.ts.lib.settings.common.utilities;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class LogUtil {
    private static final String APP_TAG = "TS.SETTINGS.SERVICE";
    private static boolean mAggregateLog = true;

    public static void verbose(String tag, String msg) {
        if (mAggregateLog) {
            Log.v(APP_TAG, tag + " " + msg);
            return;
        }
        Log.v(tag, msg);
    }

    public static void debug(String tag, String msg) {
        if (mAggregateLog) {
            Log.d(APP_TAG, tag + " " + msg);
            return;
        }
        Log.d(tag, msg);
    }

    public static void info(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.i(APP_TAG, tag + " " + msg, throwable);
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void info(String tag, String msg) {
        if (mAggregateLog) {
            Log.i(APP_TAG, tag + " " + msg);
            return;
        }
        Log.i(tag, msg);
    }

    public static void warning(String tag, String msg) {
        if (mAggregateLog) {
            Log.w(APP_TAG, tag + " " + msg);
            return;
        }
        Log.w(tag, msg);
    }

    public static void warning(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.w(APP_TAG, tag + " " + msg, throwable);
            return;
        }
        Log.w(tag, msg, throwable);
    }

    public static void error(String tag, String msg, Throwable throwable) {
        if (mAggregateLog) {
            Log.e(APP_TAG, tag + " " + msg, throwable);
            return;
        }
        Log.e(tag, msg, throwable);
    }

    public static void error(String tag, String msg) {
        if (mAggregateLog) {
            Log.e(APP_TAG, tag + " " + msg);
            return;
        }
        Log.e(tag, msg);
    }
}
