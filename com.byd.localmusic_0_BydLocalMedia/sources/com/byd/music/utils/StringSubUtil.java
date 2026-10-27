package com.byd.music.utils;

/* JADX INFO: loaded from: classes5.dex */
public class StringSubUtil {
    private static boolean isSub = true;

    public static String subString(String str) {
        if (!isSub) {
            return str;
        }
        try {
            if (str.length() >= 4) {
                return str.substring(0, 2);
            }
            return str.substring(0, 1);
        } catch (Throwable unused) {
            return str;
        }
    }
}
