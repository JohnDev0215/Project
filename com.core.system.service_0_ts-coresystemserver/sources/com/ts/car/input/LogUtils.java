package com.ts.car.input;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    public static void log(String tag, String msg) {
        if (isLoggable()) {
            Log.d(tag, msg);
        }
    }

    public static void log(String tag, String format, String... values) {
        if (isLoggable()) {
            Log.d(tag, String.format(format, values));
        }
    }

    public static void loge(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void logw(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void logwtf(String tag, String msg) {
        Log.wtf(tag, msg);
    }

    public static void logwtf(String tag, Throwable tr) {
        Log.wtf(tag, tr);
    }

    private static boolean isLoggable() {
        return SystemProperties.getBoolean("persist.sys.input.enable", true);
    }
}
