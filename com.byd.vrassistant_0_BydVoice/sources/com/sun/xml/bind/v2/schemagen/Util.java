package com.sun.xml.bind.v2.schemagen;

/* JADX INFO: loaded from: classes3.dex */
public final class Util {
    private Util() {
    }

    public static String escapeURI(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isSpaceChar(cCharAt)) {
                sb.append("%20");
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String getParentUriPath(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (str.endsWith("/")) {
            str = str.substring(0, iLastIndexOf);
            iLastIndexOf = str.lastIndexOf(47);
        }
        return str.substring(0, iLastIndexOf) + "/";
    }

    public static String normalizeUriPath(String str) {
        return str.endsWith("/") ? str : str.substring(0, str.lastIndexOf(47) + 1);
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean equal(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
