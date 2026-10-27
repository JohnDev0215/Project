package com.byd.foundation.utils;

import android.content.Context;
import android.content.res.Resources;
import java.util.IllegalFormatException;

/* JADX INFO: loaded from: classes.dex */
public class StringUtils {
    private static final String TAG = "StringUtils";

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static int length(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    public static String reverse(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        int i = length >> 1;
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            char c = charArray[i2];
            int i3 = (length - i2) - 1;
            charArray[i2] = charArray[i3];
            charArray[i3] = c;
        }
        return new String(charArray);
    }

    public static String getString(Context context, int i) {
        return getString(context, i, (Object[]) null);
    }

    public static String getString(Context context, int i, Object... objArr) {
        try {
            return format(context.getString(i), objArr);
        } catch (Resources.NotFoundException e) {
            LogUtils.e(TAG, e.getMessage(), e);
            return String.valueOf(i);
        }
    }

    public static String[] getStringArray(Context context, int i) {
        try {
            return context.getResources().getStringArray(i);
        } catch (Resources.NotFoundException e) {
            LogUtils.e(TAG, e.getMessage(), e);
            return new String[]{String.valueOf(i)};
        }
    }

    public static String format(String str, Object... objArr) {
        if (str == null || objArr == null || objArr.length <= 0) {
            return str;
        }
        try {
            return String.format(str, objArr);
        } catch (IllegalFormatException e) {
            LogUtils.e(TAG, e.getMessage(), e);
            return str;
        }
    }
}
