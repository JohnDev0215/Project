package org.codehaus.stax2.ri.typed;

import org.codehaus.stax2.typed.Base64Variant;

/* JADX INFO: loaded from: classes7.dex */
public final class ValueEncoderFactory {
    protected TokenEncoder _tokenEncoder = null;
    protected IntEncoder _intEncoder = null;
    protected LongEncoder _longEncoder = null;
    protected FloatEncoder _floatEncoder = null;
    protected DoubleEncoder _doubleEncoder = null;

    public Base64Encoder getEncoder(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        return new Base64Encoder(base64Variant, bArr, i, i2 + i);
    }

    static abstract class ScalarEncoder extends AsciiValueEncoder {
        protected ScalarEncoder() {
        }
    }

    static final class TokenEncoder extends ScalarEncoder {
        String _value;

        protected TokenEncoder() {
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public boolean isCompleted() {
            return this._value == null;
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            String str = this._value;
            this._value = null;
            int length = str.length();
            str.getChars(0, length, cArr, i);
            return i + length;
        }
    }

    static abstract class TypedScalarEncoder extends ScalarEncoder {
        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public final boolean isCompleted() {
            return true;
        }

        protected TypedScalarEncoder() {
        }
    }

    static final class IntEncoder extends TypedScalarEncoder {
        int _value;

        protected IntEncoder() {
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            return NumberUtil.writeInt(this._value, cArr, i);
        }
    }

    static final class LongEncoder extends TypedScalarEncoder {
        long _value;

        protected LongEncoder() {
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            return NumberUtil.writeLong(this._value, cArr, i);
        }
    }

    static final class FloatEncoder extends TypedScalarEncoder {
        float _value;

        protected FloatEncoder() {
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            return NumberUtil.writeFloat(this._value, cArr, i);
        }
    }

    static final class DoubleEncoder extends TypedScalarEncoder {
        double _value;

        protected DoubleEncoder() {
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            return NumberUtil.writeDouble(this._value, cArr, i);
        }
    }

    static final class Base64Encoder extends AsciiValueEncoder {
        int _chunksBeforeLf;
        final byte[] _input;
        final int _inputEnd;
        int _inputPtr;
        final Base64Variant _variant;

        protected Base64Encoder(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
            this._variant = base64Variant;
            this._input = bArr;
            this._inputPtr = i;
            this._inputEnd = i2;
            this._chunksBeforeLf = base64Variant.getMaxLineLength() >> 2;
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public boolean isCompleted() {
            return this._inputPtr >= this._inputEnd;
        }

        @Override // org.codehaus.stax2.ri.typed.AsciiValueEncoder
        public int encodeMore(char[] cArr, int i, int i2) {
            int i3 = this._inputEnd - 3;
            int i4 = i2 - 5;
            while (true) {
                int i5 = this._inputPtr;
                if (i5 > i3) {
                    int i6 = this._inputEnd - i5;
                    if (i6 <= 0 || i > i4) {
                        return i;
                    }
                    byte[] bArr = this._input;
                    int i7 = i5 + 1;
                    this._inputPtr = i7;
                    int i8 = bArr[i5] << 16;
                    if (i6 == 2) {
                        this._inputPtr = i7 + 1;
                        i8 |= (bArr[i7] & 255) << 8;
                    }
                    return this._variant.encodeBase64Partial(i8, i6, cArr, i);
                }
                if (i > i4) {
                    return i;
                }
                byte[] bArr2 = this._input;
                int i9 = i5 + 1;
                int i10 = i9 + 1;
                int i11 = ((bArr2[i5] << 8) | (bArr2[i9] & 255)) << 8;
                this._inputPtr = i10 + 1;
                i = this._variant.encodeBase64Chunk(i11 | (bArr2[i10] & 255), cArr, i);
                int i12 = this._chunksBeforeLf - 1;
                this._chunksBeforeLf = i12;
                if (i12 <= 0) {
                    cArr[i] = '\n';
                    this._chunksBeforeLf = this._variant.getMaxLineLength() >> 2;
                    i++;
                }
            }
        }
    }
}
