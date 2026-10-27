package com.byd.vrsettings.utils;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    private static final String TAG = "NEU_";
    static boolean isDebug = true;

    public static void d(String str, String str2) {
        if (isDebug) {
            RLog.d(TAG + str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (isDebug) {
            RLog.d(TAG + str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            RLog.i(TAG + str, str2);
        }
    }

    public static void e(String str, String str2) {
        RLog.e(TAG + str, str2);
    }
}
