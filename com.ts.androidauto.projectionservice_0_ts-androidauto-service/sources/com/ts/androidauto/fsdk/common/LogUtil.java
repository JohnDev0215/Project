package com.ts.androidauto.fsdk.common;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class LogUtil {
    private static final String APP_TAG = "AndroidAutoLib";
    private static boolean mAggregateLog = true;

    public static void init() {
    }

    public static void verbose(String tag, String msg) {
        if (mAggregateLog) {
            Log.v(APP_TAG, tag + " " + msg);
            return;
        }
        Log.v(tag, msg);
    }

    public static void verbose(String tag, Exception exc) {
        verbose(tag, prepareStackTrace(exc));
    }

    public static void debug(String tag, String msg) {
        if (mAggregateLog) {
            Log.d(APP_TAG, tag + " " + msg);
            return;
        }
        Log.d(tag, msg);
    }

    public static void debug(String tag, Exception exc) {
        debug(tag, prepareStackTrace(exc));
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

    public static void info(String tag, Exception exc) {
        info(tag, prepareStackTrace(exc));
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

    public static void warning(String tag, Exception exc) {
        warning(tag, prepareStackTrace(exc));
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

    public static void error(String tag, Exception exc) {
        error(tag, prepareStackTrace(exc));
    }

    public static String prepareStackTrace(Exception exc) {
        if (exc == null) {
            return null;
        }
        return Log.getStackTraceString(exc);
    }
}
