package com.byd.common.countrycode;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "NEU_";
    static boolean isDebug = true;

    public static void d(String str, String str2) {
        if (isDebug) {
            Log.d(TAG + str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (isDebug) {
            Log.w(TAG + str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            Log.i(TAG + str, str2);
        }
    }

    public static void e(String str, String str2) {
        Log.e(TAG + str, str2);
    }
}
