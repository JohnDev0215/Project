package com.android.bluetooth.util;

import android.net.INetd;

/* JADX INFO: loaded from: classes.dex */
public class AesUtil {
    private AesUtil() {
    }

    public static String encrypt(String str) {
        if (str == null || str.length() == 0) {
            return INetd.NEXTHOP_NONE;
        }
        StringBuilder sb = new StringBuilder(str);
        int len = str.length();
        if (len >= 16) {
            sb.replace(0, 8, "******");
        } else if (len >= 12) {
            sb.replace(0, 6, "*****");
        } else if (len >= 8) {
            sb.replace(0, 4, "***");
        } else if (len >= 4) {
            sb.replace(0, 2, "**");
        } else if (len >= 2) {
            sb.replace(0, 1, "*");
        } else {
            sb.insert(0, "*");
        }
        return sb.toString();
    }
}
