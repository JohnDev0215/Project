package com.ts.avm.sdk.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class LogManager {
    private static final String TAG = "AVM_SDK";
    private static final boolean isLogEnable = true;

    public static void logi(String str, String str2) {
        Log.i(TAG, formatMessage(str, str2));
    }

    public static void loge(String str, String str2) {
        Log.e(TAG, formatMessage(str, str2));
    }

    public static void loge(String str, String str2, Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace.length != 0) {
            Log.e(TAG, formatMessage(str, str2) + " [class name]: " + stackTrace[0].getClassName() + " [line number]:" + stackTrace[0].getLineNumber());
        } else {
            Log.e(TAG, formatMessage(str, str2));
        }
    }

    public static void logv(String str, String str2) {
        Log.v(TAG, formatMessage(str, str2));
    }

    public static void logw(String str, String str2) {
        Log.w(TAG, formatMessage(str, str2));
    }

    public static void logd(String str, String str2) {
        Log.d(TAG, formatMessage(str, str2));
    }

    public static void logd(String str, String str2, Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace.length != 0) {
            Log.d(TAG, formatMessage(str, str2) + " [class name]: " + stackTrace[0].getClassName() + " [line number]:" + stackTrace[0].getLineNumber());
        } else {
            Log.d(TAG, formatMessage(str, str2));
        }
    }

    private static String formatMessage(String str, String str2) {
        return String.format("%s::%s", str, str2);
    }
}
