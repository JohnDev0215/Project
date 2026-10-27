package com.ts.car.sdk.core;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class Logger {
    private static final String APP_TAG = "CoreSystemServer";
    private static final String SYSTEM_PROP_LOG_SWITCH = "persist.sys.coresystemserver.logswitch";
    private static boolean sDebugLog;

    static {
        sDebugLog = true;
        sDebugLog = SystemProperties.getBoolean(SYSTEM_PROP_LOG_SWITCH, false);
    }

    public static void changeLogSwitchState(boolean state) {
        sDebugLog = state;
    }

    public static void verbose(String tag, String msg) {
        if (sDebugLog) {
            Log.v(APP_TAG, tag + " " + msg);
            return;
        }
        Log.v(tag, msg);
    }

    public static void verbose(String tag, Exception exc) {
        verbose(tag, prepareStackTrace(exc));
    }

    public static void debug(String tag, String msg) {
        if (sDebugLog) {
            Log.d(APP_TAG, tag + " " + msg);
            return;
        }
        Log.d(tag, msg);
    }

    public static void debug(String msg) {
        if (sDebugLog) {
            Log.d(APP_TAG, msg);
        }
    }

    public static void debug(String tag, Exception exc) {
        debug(tag, prepareStackTrace(exc));
    }

    public static void info(String tag, String msg, Throwable throwable) {
        if (sDebugLog) {
            Log.i(APP_TAG, tag + " " + msg, throwable);
            return;
        }
        Log.i(tag, msg, throwable);
    }

    public static void info(String tag, String msg) {
        if (sDebugLog) {
            Log.i(APP_TAG, tag + " " + msg);
            return;
        }
        Log.i(tag, msg);
    }

    public static void info(String msg) {
        if (sDebugLog) {
            Log.i(APP_TAG, msg);
        } else {
            Log.i(APP_TAG, msg);
        }
    }

    public static void info(String tag, Exception exc) {
        info(tag, prepareStackTrace(exc));
    }

    public static void warning(String msg) {
        warning(APP_TAG, msg);
    }

    public static void warning(String tag, String msg) {
        Log.w(APP_TAG, tag + " " + msg);
    }

    public static void warning(String msg, Throwable throwable) {
        Log.w(APP_TAG, msg, throwable);
    }

    public static void warning(String tag, String msg, Throwable throwable) {
        Log.w(APP_TAG, tag + " " + msg, throwable);
    }

    public static void warning(String tag, Exception exc) {
        warning(tag, prepareStackTrace(exc));
    }

    public static void error(String tag, String msg, Throwable throwable) {
        Log.e(APP_TAG, tag + " " + msg, throwable);
    }

    public static void error(String msg, Throwable throwable) {
        Log.e(APP_TAG, msg, throwable);
    }

    public static void error(String tag, String msg) {
        Log.e(APP_TAG, tag + " " + msg);
    }

    public static void error(String tag, Exception exc) {
        error(tag, prepareStackTrace(exc));
    }

    public static void error(Throwable ex) {
        Log.e(APP_TAG, "", ex);
    }

    public static void error(String msg) {
        Log.e(APP_TAG, msg);
    }

    public static String prepareStackTrace(Exception exc) {
        if (exc == null) {
            return null;
        }
        return Log.getStackTraceString(exc);
    }
}
