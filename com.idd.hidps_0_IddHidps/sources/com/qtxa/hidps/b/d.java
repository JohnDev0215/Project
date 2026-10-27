package com.qtxa.hidps.b;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String[] f66a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static String a(byte[] bArr) {
        String[] strArr = f66a;
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(strArr[(b2 >> 4) & 15]);
            sb.append(strArr[b2 & 15]);
        }
        sb.setLength(sb.length());
        return sb.toString();
    }

    public static byte b(String str, int i) {
        int i2 = i + 1;
        String strSubstring = str.substring(i, i2);
        String strSubstring2 = str.substring(i2, i + 2);
        return (byte) ((Integer.valueOf(strSubstring2, 16).intValue() & 15) | ((Integer.valueOf(strSubstring, 16).intValue() << 4) & 240));
    }

    public static byte[] c(String str) {
        int length = str == null ? 0 : str.length();
        if (length == 0) {
            return new byte[0];
        }
        if (length % 2 != 0) {
            return new byte[0];
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = b(str, i2 * 2);
        }
        return bArr;
    }
}
