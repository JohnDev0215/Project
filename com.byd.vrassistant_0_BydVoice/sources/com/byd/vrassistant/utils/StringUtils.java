package com.byd.vrassistant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class StringUtils {
    public static boolean contains(String str, String[] strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(String[] strArr, String str) {
        if (strArr == null) {
            return -1;
        }
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
