package com.amazon.ion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/* JADX INFO: loaded from: classes3.dex */
public class Decimal extends BigDecimal {
    public static final Decimal ZERO = new Decimal(0);
    public static final Decimal NEGATIVE_ZERO = new NegativeZero(0);

    private static final class NegativeZero extends Decimal {
        private NegativeZero(int i) {
            super(BigInteger.ZERO, i);
        }

        private NegativeZero(int i, MathContext mathContext) {
            super(BigInteger.ZERO, i, mathContext);
        }

        @Override // java.math.BigDecimal, java.lang.Number
        public float floatValue() {
            float fFloatValue = super.floatValue();
            return Float.compare(0.0f, fFloatValue) <= 0 ? fFloatValue * (-1.0f) : fFloatValue;
        }

        @Override // java.math.BigDecimal, java.lang.Number
        public double doubleValue() {
            double dDoubleValue = super.doubleValue();
            return Double.compare(0.0d, dDoubleValue) <= 0 ? dDoubleValue * (-1.0d) : dDoubleValue;
        }

        @Override // java.math.BigDecimal
        public BigDecimal abs() {
            return new BigDecimal(unscaledValue(), scale());
        }

        @Override // java.math.BigDecimal
        public BigDecimal abs(MathContext mathContext) {
            return new BigDecimal(unscaledValue(), scale(), mathContext);
        }

        @Override // java.math.BigDecimal
        public String toString() {
            return '-' + super.toString();
        }

        @Override // java.math.BigDecimal
        public String toEngineeringString() {
            return '-' + super.toEngineeringString();
        }

        @Override // java.math.BigDecimal
        public String toPlainString() {
            return '-' + super.toPlainString();
        }
    }

    public static boolean isNegativeZero(BigDecimal bigDecimal) {
        return bigDecimal.getClass() == NegativeZero.class;
    }

    public static BigDecimal bigDecimalValue(BigDecimal bigDecimal) {
        return (bigDecimal == null || bigDecimal.getClass() == BigDecimal.class) ? bigDecimal : new BigDecimal(bigDecimal.unscaledValue(), bigDecimal.scale());
    }

    public static boolean equals(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return isNegativeZero(bigDecimal) == isNegativeZero(bigDecimal2) && bigDecimal.equals(bigDecimal2);
    }

    public static Decimal negativeZero(int i) {
        return new NegativeZero(i);
    }

    public static Decimal negativeZero(int i, MathContext mathContext) {
        return new NegativeZero(i, mathContext);
    }

    public static Decimal valueOf(BigInteger bigInteger, int i) {
        return new Decimal(bigInteger, i);
    }

    public static Decimal valueOf(BigInteger bigInteger, int i, MathContext mathContext) {
        return new Decimal(bigInteger, i, mathContext);
    }

    public static Decimal valueOf(BigInteger bigInteger) {
        return new Decimal(bigInteger);
    }

    public static Decimal valueOf(int i) {
        return new Decimal(i);
    }

    public static Decimal valueOf(int i, MathContext mathContext) {
        return new Decimal(i, mathContext);
    }

    public static Decimal valueOf(long j) {
        return new Decimal(j);
    }

    public static Decimal valueOf(double d) {
        if (Double.compare(d, -0.0d) == 0) {
            return new NegativeZero(1);
        }
        return new Decimal(Double.toString(d));
    }

    public static Decimal valueOf(BigDecimal bigDecimal) {
        if (bigDecimal == null || (bigDecimal instanceof Decimal)) {
            return (Decimal) bigDecimal;
        }
        return new Decimal(bigDecimal.unscaledValue(), bigDecimal.scale());
    }

    public static Decimal valueOf(String str) {
        boolean zStartsWith = str.startsWith("-");
        Decimal decimal = new Decimal(str);
        return (zStartsWith && decimal.signum() == 0) ? new NegativeZero(decimal.scale()) : decimal;
    }

    private Decimal(BigInteger bigInteger, int i) {
        super(bigInteger, i);
    }

    private Decimal(BigInteger bigInteger, int i, MathContext mathContext) {
        super(bigInteger, i, mathContext);
    }

    private Decimal(BigInteger bigInteger) {
        super(bigInteger);
    }

    private Decimal(int i) {
        super(i);
    }

    private Decimal(int i, MathContext mathContext) {
        super(i, mathContext);
    }

    private Decimal(long j) {
        super(j);
    }

    private Decimal(String str) {
        super(str);
    }

    public final boolean isNegativeZero() {
        return getClass() == NegativeZero.class;
    }
}
