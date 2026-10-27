package com.amazon.ion.impl;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
final class IonBinary {
    private static final BigInteger MAX_LONG_VALUE = new BigInteger(Long.toString(Long.MAX_VALUE));
    private static final Double DOUBLE_POS_ZERO = Double.valueOf(0.0d);
    static final int ZERO_DECIMAL_TYPEDESC = _Private_IonConstants.makeTypeDescriptor(5, 0);
    static final int NULL_DECIMAL_TYPEDESC = _Private_IonConstants.makeTypeDescriptor(5, 15);

    private IonBinary() {
    }

    public static BigInteger unsignedLongToBigInteger(int i, long j) {
        return new BigInteger(i, new byte[]{(byte) ((j >> 56) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)});
    }
}
