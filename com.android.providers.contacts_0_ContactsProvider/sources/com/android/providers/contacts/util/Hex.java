package com.android.providers.contacts.util;

/* JADX INFO: loaded from: classes.dex */
public class Hex {
    private static final byte[] DIGITS;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] SECOND_CHAR = new char[256];

    static {
        for (int i = 0; i < 256; i++) {
            char[] cArr = FIRST_CHAR;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(i >> 4) & 15];
            SECOND_CHAR[i] = cArr2[i & 15];
        }
        DIGITS = new byte[103];
        for (int i2 = 0; i2 <= 70; i2++) {
            DIGITS[i2] = -1;
        }
        for (byte b = 0; b < 10; b = (byte) (b + 1)) {
            DIGITS[b + 48] = b;
        }
        for (byte b2 = 0; b2 < 6; b2 = (byte) (b2 + 1)) {
            byte[] bArr = DIGITS;
            byte b3 = (byte) (b2 + 10);
            bArr[b2 + 65] = b3;
            bArr[b2 + 97] = b3;
        }
    }

    public static String encodeHex(byte[] bArr, boolean z) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (z && i3 == 0 && i2 == bArr.length - 1) {
                break;
            }
            int i4 = i + 1;
            cArr[i] = FIRST_CHAR[i3];
            i = i4 + 1;
            cArr[i4] = SECOND_CHAR[i3];
        }
        return new String(cArr, 0, i);
    }

    public static byte[] decodeHex(String str) {
        byte b;
        byte b2;
        byte[] bArr = DIGITS;
        int length = str.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters: " + str);
        }
        byte[] bArr2 = new byte[length >> 1];
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt <= 'f' && (b = bArr[cCharAt]) != -1) {
                int i4 = i3 + 1;
                char cCharAt2 = str.charAt(i3);
                if (cCharAt2 <= 'f' && (b2 = bArr[cCharAt2]) != -1) {
                    bArr2[i2] = (byte) ((b << 4) | b2);
                    i2++;
                    i = i4;
                }
            }
            z = true;
        }
        if (!z) {
            return bArr2;
        }
        throw new IllegalArgumentException("Invalid hexadecimal digit: " + str);
    }
}
