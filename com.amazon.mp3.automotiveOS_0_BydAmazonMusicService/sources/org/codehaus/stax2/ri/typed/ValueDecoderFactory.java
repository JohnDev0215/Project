package org.codehaus.stax2.ri.typed;

import java.math.BigInteger;
import org.codehaus.stax2.typed.TypedValueDecoder;

/* JADX INFO: loaded from: classes7.dex */
public final class ValueDecoderFactory {
    protected BooleanDecoder mBooleanDecoder = null;
    protected IntDecoder mIntDecoder = null;
    protected LongDecoder mLongDecoder = null;
    protected FloatDecoder mFloatDecoder = null;
    protected DoubleDecoder mDoubleDecoder = null;

    public IntDecoder getIntDecoder() {
        if (this.mIntDecoder == null) {
            this.mIntDecoder = new IntDecoder();
        }
        return this.mIntDecoder;
    }

    public FloatDecoder getFloatDecoder() {
        if (this.mFloatDecoder == null) {
            this.mFloatDecoder = new FloatDecoder();
        }
        return this.mFloatDecoder;
    }

    public DoubleDecoder getDoubleDecoder() {
        if (this.mDoubleDecoder == null) {
            this.mDoubleDecoder = new DoubleDecoder();
        }
        return this.mDoubleDecoder;
    }

    public static abstract class DecoderBase extends TypedValueDecoder {
        protected int mNextPtr;
        static final BigInteger BD_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        static final BigInteger BD_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

        public abstract String getType();

        protected DecoderBase() {
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void handleEmptyValue() {
            throw new IllegalArgumentException("Empty value (all white space) not a valid lexical representation of " + getType());
        }

        protected void verifyDigits(String str, int i, int i2) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt > '9' || cCharAt < '0') {
                    throw constructInvalidValue(str);
                }
                i++;
            }
        }

        protected int skipSignAndZeroes(String str, char c, boolean z, int i) {
            int i2 = 1;
            if (z) {
                if (1 >= i) {
                    throw constructInvalidValue(str);
                }
                i2 = 2;
                c = str.charAt(1);
            }
            int i3 = c - '0';
            if (i3 < 0 || i3 > 9) {
                throw constructInvalidValue(str);
            }
            while (i3 == 0 && i2 < i) {
                int iCharAt = str.charAt(i2) - '0';
                if (iCharAt < 0 || iCharAt > 9) {
                    break;
                }
                i2++;
                i3 = iCharAt;
            }
            this.mNextPtr = i2;
            return i3;
        }

        protected static final int parseInt(String str, int i, int i2) {
            int iCharAt = str.charAt(i) - '0';
            int i3 = i + 1;
            if (i3 >= i2) {
                return iCharAt;
            }
            int iCharAt2 = (iCharAt * 10) + (str.charAt(i3) - '0');
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return iCharAt2;
            }
            int iCharAt3 = (iCharAt2 * 10) + (str.charAt(i4) - '0');
            int i5 = i4 + 1;
            if (i5 >= i2) {
                return iCharAt3;
            }
            int iCharAt4 = (iCharAt3 * 10) + (str.charAt(i5) - '0');
            int i6 = i5 + 1;
            if (i6 >= i2) {
                return iCharAt4;
            }
            int iCharAt5 = (iCharAt4 * 10) + (str.charAt(i6) - '0');
            int i7 = i6 + 1;
            if (i7 >= i2) {
                return iCharAt5;
            }
            int iCharAt6 = (iCharAt5 * 10) + (str.charAt(i7) - '0');
            int i8 = i7 + 1;
            if (i8 >= i2) {
                return iCharAt6;
            }
            int iCharAt7 = (iCharAt6 * 10) + (str.charAt(i8) - '0');
            int i9 = i8 + 1;
            if (i9 >= i2) {
                return iCharAt7;
            }
            int iCharAt8 = (iCharAt7 * 10) + (str.charAt(i9) - '0');
            return i9 + 1 < i2 ? (iCharAt8 * 10) + (str.charAt(r3) - '0') : iCharAt8;
        }

        protected static final int parseInt(int i, String str, int i2, int i3) {
            int iCharAt = (i * 10) + (str.charAt(i2) - '0');
            int i4 = i2 + 1;
            if (i4 >= i3) {
                return iCharAt;
            }
            int iCharAt2 = (iCharAt * 10) + (str.charAt(i4) - '0');
            int i5 = i4 + 1;
            if (i5 >= i3) {
                return iCharAt2;
            }
            int iCharAt3 = (iCharAt2 * 10) + (str.charAt(i5) - '0');
            int i6 = i5 + 1;
            if (i6 >= i3) {
                return iCharAt3;
            }
            int iCharAt4 = (iCharAt3 * 10) + (str.charAt(i6) - '0');
            int i7 = i6 + 1;
            if (i7 >= i3) {
                return iCharAt4;
            }
            int iCharAt5 = (iCharAt4 * 10) + (str.charAt(i7) - '0');
            int i8 = i7 + 1;
            if (i8 >= i3) {
                return iCharAt5;
            }
            int iCharAt6 = (iCharAt5 * 10) + (str.charAt(i8) - '0');
            int i9 = i8 + 1;
            if (i9 >= i3) {
                return iCharAt6;
            }
            int iCharAt7 = (iCharAt6 * 10) + (str.charAt(i9) - '0');
            return i9 + 1 < i3 ? (iCharAt7 * 10) + (str.charAt(r3) - '0') : iCharAt7;
        }

        protected static final long parseLong(String str, int i, int i2) {
            int i3 = i2 - 9;
            return (((long) parseInt(str, i, i3)) * 1000000000) + ((long) parseInt(str, i3, i2));
        }

        protected IllegalArgumentException constructInvalidValue(String str) {
            return new IllegalArgumentException("Value \"" + str + "\" not a valid lexical representation of " + getType());
        }

        protected String lexicalDesc(String str) {
            return _clean(str);
        }

        protected String _clean(String str) {
            return str.trim();
        }
    }

    public static final class BooleanDecoder extends DecoderBase {
        protected boolean mValue;

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "boolean";
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            char cCharAt = str.charAt(0);
            if (cCharAt == 't') {
                if (length == 4 && str.charAt(1) == 'r' && str.charAt(2) == 'u' && str.charAt(3) == 'e') {
                    this.mValue = true;
                    return;
                }
            } else if (cCharAt == 'f') {
                if (length == 5 && str.charAt(1) == 'a' && str.charAt(2) == 'l' && str.charAt(3) == 's' && str.charAt(4) == 'e') {
                    this.mValue = false;
                    return;
                }
            } else if (cCharAt == '0') {
                if (length == 1) {
                    this.mValue = false;
                    return;
                }
            } else if (cCharAt == '1' && length == 1) {
                this.mValue = true;
                return;
            }
            throw constructInvalidValue(str);
        }
    }

    public static final class IntDecoder extends DecoderBase {
        protected int mValue;

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "int";
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int iSkipSignAndZeroes;
            int length = str.length();
            char cCharAt = str.charAt(0);
            boolean z = cCharAt == '-';
            if (z || cCharAt == '+') {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, true, length);
            } else {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, false, length);
            }
            int i = this.mNextPtr;
            int i2 = length - i;
            if (i2 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                this.mValue = iSkipSignAndZeroes;
                return;
            }
            verifyDigits(str, i, length);
            if (i2 <= 8) {
                int i3 = DecoderBase.parseInt(iSkipSignAndZeroes, str, i, i2 + i);
                if (z) {
                    i3 = -i3;
                }
                this.mValue = i3;
                return;
            }
            if (i2 == 9 && iSkipSignAndZeroes < 3) {
                long j = (iSkipSignAndZeroes == 2 ? 2000000000L : 1000000000L) + ((long) DecoderBase.parseInt(str, i, i2 + i));
                if (z) {
                    long j2 = -j;
                    if (j2 >= -2147483648L) {
                        this.mValue = (int) j2;
                        return;
                    }
                } else if (j <= 2147483647L) {
                    this.mValue = (int) j;
                    return;
                }
            }
            throw new IllegalArgumentException("value \"" + lexicalDesc(str) + "\" not a valid 32-bit integer: overflow.");
        }
    }

    public static final class LongDecoder extends DecoderBase {
        protected long mValue;

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "long";
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int iSkipSignAndZeroes;
            int length = str.length();
            char cCharAt = str.charAt(0);
            boolean z = cCharAt == '-';
            if (z || cCharAt == '+') {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, true, length);
            } else {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, false, length);
            }
            int i = this.mNextPtr;
            int i2 = length - i;
            if (i2 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                this.mValue = iSkipSignAndZeroes;
                return;
            }
            verifyDigits(str, i, length);
            if (i2 <= 8) {
                int i3 = DecoderBase.parseInt(iSkipSignAndZeroes, str, i, i2 + i);
                if (z) {
                    i3 = -i3;
                }
                this.mValue = i3;
                return;
            }
            int i4 = i - 1;
            int i5 = i2 + 1;
            if (i5 <= 18) {
                long j = DecoderBase.parseLong(str, i4, i5 + i4);
                if (z) {
                    j = -j;
                }
                this.mValue = j;
                return;
            }
            this.mValue = parseUsingBD(str.substring(i4, i5 + i4), z);
        }

        private long parseUsingBD(String str, boolean z) {
            BigInteger bigInteger = new BigInteger(str);
            if (z) {
                BigInteger bigIntegerNegate = bigInteger.negate();
                if (bigIntegerNegate.compareTo(DecoderBase.BD_MIN_LONG) >= 0) {
                    return bigIntegerNegate.longValue();
                }
            } else if (bigInteger.compareTo(DecoderBase.BD_MAX_LONG) <= 0) {
                return bigInteger.longValue();
            }
            throw new IllegalArgumentException("value \"" + lexicalDesc(str) + "\" not a valid long: overflow.");
        }
    }

    public static final class FloatDecoder extends DecoderBase {
        protected float mValue;

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "float";
        }

        public float getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            if (length == 3) {
                char cCharAt = str.charAt(0);
                if (cCharAt == 'I') {
                    if (str.charAt(1) == 'N' && str.charAt(2) == 'F') {
                        this.mValue = Float.POSITIVE_INFINITY;
                        return;
                    }
                } else if (cCharAt == 'N' && str.charAt(1) == 'a' && str.charAt(2) == 'N') {
                    this.mValue = Float.NaN;
                    return;
                }
            } else if (length == 4 && str.charAt(0) == '-' && str.charAt(1) == 'I' && str.charAt(2) == 'N' && str.charAt(3) == 'F') {
                this.mValue = Float.NEGATIVE_INFINITY;
                return;
            }
            try {
                this.mValue = Float.parseFloat(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }

    public static final class DoubleDecoder extends DecoderBase {
        protected double mValue;

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "double";
        }

        public double getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            if (length == 3) {
                char cCharAt = str.charAt(0);
                if (cCharAt == 'I') {
                    if (str.charAt(1) == 'N' && str.charAt(2) == 'F') {
                        this.mValue = Double.POSITIVE_INFINITY;
                        return;
                    }
                } else if (cCharAt == 'N' && str.charAt(1) == 'a' && str.charAt(2) == 'N') {
                    this.mValue = Double.NaN;
                    return;
                }
            } else if (length == 4 && str.charAt(0) == '-' && str.charAt(1) == 'I' && str.charAt(2) == 'N' && str.charAt(3) == 'F') {
                this.mValue = Double.NEGATIVE_INFINITY;
                return;
            }
            try {
                this.mValue = Double.parseDouble(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }
}
