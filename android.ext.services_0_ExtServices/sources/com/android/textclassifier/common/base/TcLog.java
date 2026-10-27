package com.android.textclassifier.common.base;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class TcLog {
    public static final boolean ENABLE_FULL_LOGGING = Log.isLoggable("androidtc", 2);

    private static String getTag(String str) {
        return "androidtc";
    }

    public static void v(String str, String str2) {
        if (ENABLE_FULL_LOGGING) {
            Log.v(getTag(str), str2);
        }
    }

    public static void d(String str, String str2) {
        Log.d(getTag(str), str2);
    }

    public static void w(String str, String str2) {
        Log.w(getTag(str), str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(getTag(str), str2, th);
    }
}
