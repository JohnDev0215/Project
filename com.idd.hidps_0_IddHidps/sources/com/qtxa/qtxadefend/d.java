package com.qtxa.qtxadefend;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f118a = "0123456789ABCDEF".toCharArray();

    private static int a(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if ('A' > c || c > 'F') {
            c2 = 'a';
            if ('a' > c || c > 'f') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    static byte[] b(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("hexBinary needs to be even-length: " + str);
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int iA = a(str.charAt(i));
            int iA2 = a(str.charAt(i + 1));
            if (iA == -1 || iA2 == -1) {
                throw new IllegalArgumentException("contains illegal character for hexBinary: " + str);
            }
            bArr[i / 2] = (byte) ((iA * 16) + iA2);
        }
        return bArr;
    }

    static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(f118a[(b2 >> 4) & 15]);
            sb.append(f118a[b2 & 15]);
        }
        return sb.toString();
    }
}
