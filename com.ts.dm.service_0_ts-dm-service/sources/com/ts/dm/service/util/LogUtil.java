package com.ts.dm.service.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class LogUtil {
    private static final String APP_TAG = "DeviceManagerService";
    private static boolean mAggregateLog = true;

    public static void init() {
    }

    public static void verbose(String tag, String msg) {
    }

    public static void verbose(String tag, Exception exc) {
        verbose(tag, prepareStackTrace(exc));
    }

    public static void debug(String tag, String msg) {
    }

    public static void debug(String tag, Exception exc) {
        debug(tag, prepareStackTrace(exc));
    }

    public static void info(String tag, String msg, Throwable throwable) {
    }

    public static void info(String tag, String msg) {
    }

    public static void info(String tag, Exception exc) {
        info(tag, prepareStackTrace(exc));
    }

    public static void warning(String tag, String msg) {
    }

    public static void warning(String tag, String msg, Throwable throwable) {
    }

    public static void warning(String tag, Exception exc) {
        warning(tag, prepareStackTrace(exc));
    }

    public static void error(String tag, String msg, Throwable throwable) {
    }

    public static void error(String tag, String msg) {
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
