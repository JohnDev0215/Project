package com.amazon.avod.media.framework.util;

/* JADX INFO: loaded from: classes2.dex */
public final class BitUtils {
    public static byte createMaskedByte(int i, int i2, int i3, int i4) {
        if (i4 > 8) {
            throw new IllegalArgumentException("numBits > 8");
        }
        if (i2 > 32) {
            throw new IllegalArgumentException("maskValueOffset > 32");
        }
        if (i3 > 7) {
            throw new IllegalArgumentException("maskOffset > 7");
        }
        byte b = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 1;
            if (((1 << (i2 + i5)) & i) <= 0) {
                i6 = 0;
            }
            b = (byte) (b | (i6 << (i3 + i5)));
        }
        return b;
    }
}
