package com.android.systemui.util;

/* JADX INFO: loaded from: classes.dex */
public class StringUtil {
    public static String desensitizeString(String string, int startIndex, int endIndex) {
        if (string == null || string.length() == 0) {
            return string;
        }
        StringBuilder sb = new StringBuilder(string);
        while (startIndex < endIndex) {
            sb.setCharAt(startIndex, '*');
            startIndex++;
        }
        return sb.toString();
    }

    public static String desensitizeString(String string, int startIndex) {
        if (string == null || string.length() == 0) {
            return string;
        }
        float length = string.length() * 0.8f;
        StringBuilder sb = new StringBuilder(string);
        while (startIndex < length) {
            sb.setCharAt(startIndex, '*');
            startIndex++;
        }
        return sb.toString();
    }
}
