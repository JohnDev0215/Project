package com.amazon.ion.impl;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_IonConstants {
    public static final byte[] BINARY_VERSION_MARKER_1_0;
    public static final int BINARY_VERSION_MARKER_SIZE;
    public static final int MAX_LONG_TEXT_SIZE = Math.max(Long.toString(Long.MAX_VALUE).length(), Long.toString(Long.MIN_VALUE).length());
    public static final int True = makeTypeDescriptor(1, 1);
    public static final int False = makeTypeDescriptor(1, 0);

    public static final int getLowNibble(int i) {
        return i & 15;
    }

    public static final int getTypeCode(int i) {
        return i >> 4;
    }

    public static final boolean isHighSurrogate(int i) {
        return (i & (-1024)) == 55296;
    }

    public static final boolean isLowSurrogate(int i) {
        return (i & (-1024)) == 56320;
    }

    public static final int makeTypeDescriptor(int i, int i2) {
        return (i << 4) | i2;
    }

    public static final int makeUnicodeScalar(int i, int i2) {
        return (((i & 1023) << 10) | (i2 & 1023)) + 65536;
    }

    private _Private_IonConstants() {
    }

    static {
        byte[] bArr = {-32, 1, 0, -22};
        BINARY_VERSION_MARKER_1_0 = bArr;
        BINARY_VERSION_MARKER_SIZE = bArr.length;
    }
}
