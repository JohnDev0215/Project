package com.neusoft.de.nts.OneCoreTest.utils;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
public class NumberSizeUtil {
    public static boolean isNumberInRange(BigInteger bigInteger, IntNumberType intNumberType) {
        return bigInteger.compareTo(intNumberType.max) <= 0 && bigInteger.compareTo(intNumberType.min) >= 0;
    }

    public static void assertIsNumberInRange(BigInteger bigInteger, IntNumberType intNumberType) {
        if (!isNumberInRange(bigInteger, intNumberType)) {
            throw new RuntimeException("Number has to be of type " + intNumberType + " and between " + intNumberType.getMin() + " & " + intNumberType.getMax() + " but is " + bigInteger);
        }
    }

    public enum IntNumberType {
        INT8(8, true),
        UINT8(8, false),
        INT16(16, true),
        UINT16(16, false),
        INT32(32, true),
        UINT32(32, false),
        INT64(64, true),
        UINT64(64, false);

        private final int bits;
        private final BigInteger max;
        private final BigInteger min;
        private final boolean signed;

        IntNumberType(int i, boolean z) {
            this.bits = i;
            this.signed = z;
            if (!z) {
                this.min = BigInteger.ZERO;
                this.max = BigInteger.valueOf(2L).pow(i).subtract(BigInteger.ONE);
            } else {
                int i2 = i - 1;
                this.min = BigInteger.valueOf(2L).pow(i2).negate();
                this.max = BigInteger.valueOf(2L).pow(i2).subtract(BigInteger.ONE);
            }
        }

        public BigInteger getMin() {
            return this.min;
        }

        public BigInteger getMax() {
            return this.max;
        }
    }
}
