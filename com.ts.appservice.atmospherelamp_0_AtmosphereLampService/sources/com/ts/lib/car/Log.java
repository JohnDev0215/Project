package com.ts.lib.car;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: loaded from: classes3.dex */
public class Log {
    public static boolean DEBUG_LOGGER = false;
    public static final int DEBUG_LOGGER_CLOSE = 0;
    public static final int DEBUG_LOGGER_OPEN = 1;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.logger";
    public static final String TAG = "CAR.VENDOR.LOG";

    public static void checkLogLogger(Context context) {
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), PERSIST_DEBUG_LOGGER, 0) == 1;
        android.util.Log.i(TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    public static void verbose(String tag, String msg) {
        if (DEBUG_LOGGER) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void debug(String tag, String msg) {
        if (DEBUG_LOGGER) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void debug(String tag, String msg, boolean subLogger) {
        if (DEBUG_LOGGER || subLogger) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        android.util.Log.i(tag, msg);
    }

    public static void warning(String tag, String msg) {
        android.util.Log.w(tag, msg);
    }

    public static void error(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }
}
