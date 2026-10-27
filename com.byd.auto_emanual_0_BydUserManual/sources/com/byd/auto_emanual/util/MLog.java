package com.byd.auto_emanual.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MLog {
    private static final String TAG = "UserManual";

    public static void i(String tag, String msg) {
        Log.i(TAG, getMsg(tag, msg));
    }

    public static void i(String tag, String msg, Exception e) {
        Log.i(TAG, getMsg(tag, msg, e));
    }

    public static void e(String tag, String msg) {
        Log.e(TAG, getMsg(tag, msg));
    }

    public static void e(String tag, String msg, Exception e) {
        Log.e(TAG, getMsg(tag, msg, e));
    }

    public static void d(String tag, String msg) {
        Log.d(TAG, getMsg(tag, msg));
    }

    public static void d(String tag, String msg, Exception e) {
        Log.d(TAG, getMsg(tag, msg, e));
    }

    public static void v(String tag, String msg) {
        Log.v(TAG, getMsg(tag, msg));
    }

    public static void v(String tag, String msg, Exception e) {
        Log.v(TAG, getMsg(tag, msg, e));
    }

    private static String getMsg(String tag, String msg) {
        return tag + " " + msg;
    }

    private static String getMsg(String tag, String msg, Exception e) {
        return getMsg(tag, msg) + " " + e;
    }
}
