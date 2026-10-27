package com.android.providers.media.util;

import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class Memory {
    public static int peekInt(byte[] bArr, int i, ByteOrder byteOrder) {
        int i2;
        int i3;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i4 = i + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i] & 255) << 24) | ((bArr[i4] & 255) << 16);
            i2 = i6 | ((bArr[i5] & 255) << 8);
            i3 = (bArr[i5 + 1] & 255) << 0;
        } else {
            int i7 = i + 1;
            int i8 = i7 + 1;
            int i9 = ((bArr[i] & 255) << 0) | ((bArr[i7] & 255) << 8);
            i2 = i9 | ((bArr[i8] & 255) << 16);
            i3 = (bArr[i8 + 1] & 255) << 24;
        }
        return i3 | i2;
    }

    public static void pokeInt(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i3 = i + 1;
            bArr[i] = (byte) ((i2 >> 24) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            bArr[i4 + 1] = (byte) ((i2 >> 0) & 255);
            return;
        }
        int i5 = i + 1;
        bArr[i] = (byte) ((i2 >> 0) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >> 8) & 255);
        bArr[i6] = (byte) ((i2 >> 16) & 255);
        bArr[i6 + 1] = (byte) ((i2 >> 24) & 255);
    }
}
