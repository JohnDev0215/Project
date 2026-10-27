package org.apache.shiro.codec;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class H64 {
    private static final char[] itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static short toShort(byte b) {
        return (short) (b & 255);
    }

    private static int toInt(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 < 1 || i2 > 4) {
            throw new IllegalArgumentException("numBytes must be between 1 and 4.");
        }
        int i4 = toShort(bArr[i]);
        for (int i5 = 1; i5 < i2; i5++) {
            short s = toShort(bArr[i + i5]);
            if (i5 == 1) {
                i3 = s << 8;
            } else if (i5 == 2) {
                i3 = s << 16;
            } else if (i5 == 3) {
                i3 = s << 24;
            }
            i4 |= i3;
        }
        return i4;
    }

    private static void append(Appendable appendable, char c) {
        try {
            appendable.append(c);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to append character to internal buffer.", e);
        }
    }

    private static void encodeAndAppend(int i, Appendable appendable, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            append(appendable, itoa64[i & 63]);
            i >>= 6;
        }
    }

    public static String encodeToString(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        int i = length % 3;
        int i2 = 0;
        int i3 = length - i;
        while (i2 < i3) {
            encodeAndAppend(toInt(bArr, i2, 3), sb, 4);
            i2 += 3;
        }
        if (i > 0) {
            encodeAndAppend(toInt(bArr, i2, i), sb, i + 1);
        }
        return sb.toString();
    }
}
