package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class Longs {
    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    static final class AsciiDigits {
        private static final byte[] asciiDigits;

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i = 0; i <= 9; i++) {
                bArr[i + 48] = (byte) i;
            }
            for (int i2 = 0; i2 <= 26; i2++) {
                byte b = (byte) (i2 + 10);
                bArr[i2 + 65] = b;
                bArr[i2 + 97] = b;
            }
            asciiDigits = bArr;
        }

        static int digit(char c) {
            if (c < 128) {
                return asciiDigits[c];
            }
            return -1;
        }
    }

    public static Long tryParse(String str, int i) {
        Preconditions.checkNotNull(str);
        if (str.isEmpty()) {
            return null;
        }
        if (i < 2 || i > 36) {
            throw new IllegalArgumentException("radix must be between MIN_RADIX and MAX_RADIX but was " + i);
        }
        int i2 = str.charAt(0) == '-' ? 1 : 0;
        if (i2 == str.length()) {
            return null;
        }
        int i3 = i2 + 1;
        int iDigit = AsciiDigits.digit(str.charAt(i2));
        if (iDigit < 0 || iDigit >= i) {
            return null;
        }
        long j = -iDigit;
        long j2 = i;
        long j3 = Long.MIN_VALUE / j2;
        while (i3 < str.length()) {
            int i4 = i3 + 1;
            int iDigit2 = AsciiDigits.digit(str.charAt(i3));
            if (iDigit2 < 0 || iDigit2 >= i || j < j3) {
                return null;
            }
            long j4 = j * j2;
            long j5 = iDigit2;
            if (j4 < j5 - Long.MIN_VALUE) {
                return null;
            }
            j = j4 - j5;
            i3 = i4;
        }
        if (i2 != 0) {
            return Long.valueOf(j);
        }
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-j);
    }
}
