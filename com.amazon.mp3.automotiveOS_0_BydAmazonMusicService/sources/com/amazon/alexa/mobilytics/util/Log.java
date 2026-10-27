package com.amazon.alexa.mobilytics.util;

/* JADX INFO: loaded from: classes2.dex */
public final class Log {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static boolean isRelease = false;

    public static String tag(Class<?> cls) {
        return "Mobilytics";
    }

    private Log() {
    }

    public static void i(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    public static void d(String str, String str2) {
        if (isRelease) {
            return;
        }
        android.util.Log.d(str, str2);
    }
}
