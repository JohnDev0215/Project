package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class MaskUtils {
    public static String maskString(String str) {
        if (str == null || str.length() <= 2) {
            return str;
        }
        return str.substring(0, 2) + "******" + str.substring(str.length() - 2);
    }
}
