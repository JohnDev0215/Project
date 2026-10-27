package com.ts.carplay.common.util;

/* JADX INFO: loaded from: classes.dex */
public final class LogEncrypt {
    private static final int DISPLAY_LENGTH = 4;
    private static final int IP_LENGTH_MIN = 7;
    private static final int IP_START_INDEX = 7;
    private static final int MAC_LENGTH_MAX = 17;
    private static final int MAC_START_INDEX = 6;
    private static final int MIN_LENGTH = 5;
    private static final int START_FLAG = 0;

    public static String MacAddressEncrypt(String str) {
        if (str == null || str.length() < 17) {
            return "**************";
        }
        return "******" + str.substring(6);
    }

    public static String IpAddressEncrypt(String str) {
        if (str == null || str.length() < 7 || str.length() == 7) {
            return "*******";
        }
        return "*******" + str.substring(7);
    }

    public static String phoneNumberEncrypt(String str) {
        if (str == null || str.length() < 5) {
            return "*******";
        }
        return "*****" + str.substring(5);
    }
}
