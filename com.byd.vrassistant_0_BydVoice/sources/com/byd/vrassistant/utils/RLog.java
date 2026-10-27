package com.byd.vrassistant.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class RLog {
    private static final String ADD_TAG = "_RLog";
    private static final String LOG_FORMAT = "[%s][%s:%d] - %s";
    private static final String TAG = "NEU_RLog";

    public static void caller(String str, String str2) {
    }

    public static void s_log(String str, String str2, String str3) {
        d(str + ADD_TAG, str2 + " ,and: " + Base64Utils.getBase64(str3));
    }

    public static void s_log(String str, String str2) {
        Log.d(str + ADD_TAG, Base64Utils.getBase64(str2));
    }

    public static void d(String str, String str2) {
        Log.i(str, str2);
    }

    public static void d(String str, Throwable th) {
        d(str, th.toString());
    }

    public static void e(String str, String str2) {
        Log.w(str, str2);
    }

    public static void i(String str, String str2) {
        d(str, str2);
    }

    public static void v(String str, String str2) {
        d(str, str2);
    }

    public static void i(String str, Throwable th) {
        d(str, th);
    }

    public static void e(String str, Throwable th) {
        e(str, th.toString());
    }

    public static void i(String str, String str2, Throwable th) {
        d(str, str2, th);
    }

    public static void d(String str, String str2, Throwable th) {
        d(str, str2 + th.toString());
    }

    public static void e(String str, String str2, Throwable th) {
        e(str, str2 + th.toString());
    }
}
