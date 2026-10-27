package com.amazon.music.metrics.util;

/* JADX INFO: loaded from: classes4.dex */
public final class StringUtil {
    private StringUtil() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || isEmpty(str);
    }

    public static boolean isNullOrWhiteSpace(String str) {
        return str == null || isEmpty(str.trim());
    }

    private static boolean isEmpty(String str) {
        return str.length() == 0;
    }
}
