package org.bouncycastle.crypto.util;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Pack {
    public static int bigEndianToInt(byte[] bArr, int i) {
        int i2 = bArr[i] << Ascii.CAN;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        return (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
    }

    public static long bigEndianToLong(byte[] bArr, int i) {
        int iBigEndianToInt = bigEndianToInt(bArr, i);
        return (((long) bigEndianToInt(bArr, i + 4)) & 4294967295L) | ((((long) iBigEndianToInt) & 4294967295L) << 32);
    }

    public static void intToBigEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 24);
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    public static void intToLittleEndian(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    public static int littleEndianToInt(byte[] bArr, int i) {
        byte b = bArr[i];
        int i2 = i + 1;
        int i3 = b | ((bArr[i2] & 255) << 8);
        int i4 = i2 + 1;
        return ((bArr[i4 + 1] & 255) << 24) | i3 | ((bArr[i4] & 255) << 16);
    }

    public static long littleEndianToLong(byte[] bArr, int i) {
        return ((((long) littleEndianToInt(bArr, i + 4)) & 4294967295L) << 32) | (((long) littleEndianToInt(bArr, i)) & 4294967295L);
    }

    public static void longToBigEndian(long j, byte[] bArr, int i) {
        intToBigEndian((int) (j >>> 32), bArr, i);
        intToBigEndian((int) (j & 4294967295L), bArr, i + 4);
    }

    public static void longToLittleEndian(long j, byte[] bArr, int i) {
        intToLittleEndian((int) (4294967295L & j), bArr, i);
        intToLittleEndian((int) (j >>> 32), bArr, i + 4);
    }
}
