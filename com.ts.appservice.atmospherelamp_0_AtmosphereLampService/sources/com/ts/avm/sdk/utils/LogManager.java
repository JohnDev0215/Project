package com.ts.avm.sdk.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class LogManager {
    private static final String TAG = "AVM_SDK";
    private static final boolean isLogEnable = true;

    public static void logi(String tag, String msg) {
        Log.i(TAG, formatMessage(tag, msg));
    }

    public static void loge(String tag, String msg) {
        Log.e(TAG, formatMessage(tag, msg));
    }

    public static void loge(String tag, String msg, Throwable throwable) {
        StackTraceElement[] element = throwable.getStackTrace();
        if (element.length != 0) {
            Log.e(TAG, formatMessage(tag, msg) + " [class name]: " + element[0].getClassName() + " [line number]:" + element[0].getLineNumber());
            return;
        }
        Log.e(TAG, formatMessage(tag, msg));
    }

    public static void logv(String tag, String msg) {
        Log.v(TAG, formatMessage(tag, msg));
    }

    public static void logw(String tag, String msg) {
        Log.w(TAG, formatMessage(tag, msg));
    }

    public static void logd(String tag, String msg) {
        Log.d(TAG, formatMessage(tag, msg));
    }

    public static void logd(String tag, String msg, Throwable throwable) {
        StackTraceElement[] element = throwable.getStackTrace();
        if (element.length != 0) {
            Log.d(TAG, formatMessage(tag, msg) + " [class name]: " + element[0].getClassName() + " [line number]:" + element[0].getLineNumber());
            return;
        }
        Log.d(TAG, formatMessage(tag, msg));
    }

    private static String formatMessage(String tag, String msg) {
        return String.format("%s::%s", tag, msg);
    }
}
