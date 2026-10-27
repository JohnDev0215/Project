package com.android.car.apps.common.util;

import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public class SafeLog {
    private SafeLog() {
    }

    public static void logv(String tag, String message) {
        if (Log.isLoggable(tag, 2)) {
            Log.v(tag, message);
        }
    }

    public static void logi(String tag, String message) {
        if (Log.isLoggable(tag, 4)) {
            Log.i(tag, message);
        }
    }

    public static void logd(String tag, String message) {
        if (Log.isLoggable(tag, 3)) {
            Log.d(tag, message);
        }
    }

    public static void logw(String tag, String message) {
        if (Log.isLoggable(tag, 5)) {
            Log.w(tag, message);
        }
    }

    public static void loge(String tag, String message) {
        loge(tag, message, null);
    }

    public static void loge(String tag, String message, Exception exception) {
        if (Log.isLoggable(tag, 6)) {
            Log.e(tag, message, exception);
        }
    }
}
