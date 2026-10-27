package com.ts.qnxapp.util;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    private static final String PROPERTY_LOGGING_ENABLED = "persist.someip.log.enabled";
    private static final String TAG = "QnxMessageCenter";
    private static boolean mLoggable = true;

    public static void logI(String tag, String msg) {
        if (mLoggable) {
            Log.i(TAG, tag + ":" + msg);
        }
    }

    public static void logD(String tag, String msg) {
        if (mLoggable) {
            Log.d(TAG, tag + ":" + msg);
        }
    }

    public static void logW(String tag, String msg) {
        if (mLoggable) {
            Log.w(TAG, tag + ":" + msg);
        }
    }

    public static void logE(String tag, String msg) {
        if (mLoggable) {
            Log.e(TAG, tag + ":" + msg);
        }
    }

    public static boolean isLoggable() {
        return SystemProperties.getBoolean(PROPERTY_LOGGING_ENABLED, false);
    }
}
