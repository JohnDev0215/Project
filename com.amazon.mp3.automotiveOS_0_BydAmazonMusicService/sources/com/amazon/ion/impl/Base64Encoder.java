package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.fasterxml.jackson.core.JsonPointer;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: classes3.dex */
final class Base64Encoder {
    private static final EL[] Base64Alphabet;
    static final int[] Base64EncodingCharToInt;
    static final int[] Base64EncodingIntToChar;
    static final char Base64EncodingTerminator;
    static final int[] URLSafe64CharToInt;
    static final int[] URLSafe64IntToChar;
    static final char URLSafe64IntToCharTerminator;

    static class EL {
        public char letter;
        public int value;

        public EL(int i, char c) {
            this.value = i;
            this.letter = c;
        }
    }

    static {
        EL[] elArr = {new EL(-1, '='), new EL(0, 'A'), new EL(17, 'R'), new EL(34, 'i'), new EL(51, 'z'), new EL(1, 'B'), new EL(18, 'S'), new EL(35, 'j'), new EL(52, '0'), new EL(2, 'C'), new EL(19, 'T'), new EL(36, 'k'), new EL(53, '1'), new EL(3, 'D'), new EL(20, 'U'), new EL(37, 'l'), new EL(54, '2'), new EL(4, 'E'), new EL(21, 'V'), new EL(38, 'm'), new EL(55, '3'), new EL(5, 'F'), new EL(22, 'W'), new EL(39, 'n'), new EL(56, '4'), new EL(6, 'G'), new EL(23, 'X'), new EL(40, 'o'), new EL(57, '5'), new EL(7, 'H'), new EL(24, 'Y'), new EL(41, 'p'), new EL(58, '6'), new EL(8, 'I'), new EL(25, 'Z'), new EL(42, 'q'), new EL(59, '7'), new EL(9, 'J'), new EL(26, 'a'), new EL(43, 'r'), new EL(60, '8'), new EL(10, 'K'), new EL(27, 'b'), new EL(44, 's'), new EL(61, '9'), new EL(11, 'L'), new EL(28, 'c'), new EL(45, 't'), new EL(62, '+'), new EL(12, 'M'), new EL(29, 'd'), new EL(46, 'u'), new EL(63, JsonPointer.SEPARATOR), new EL(13, 'N'), new EL(30, 'e'), new EL(47, 'v'), new EL(14, 'O'), new EL(31, 'f'), new EL(48, 'w'), new EL(15, 'P'), new EL(32, 'g'), new EL(49, 'x'), new EL(16, 'Q'), new EL(33, 'h'), new EL(50, 'y')};
        Base64Alphabet = elArr;
        URLSafe64IntToCharTerminator = init64IntToCharTerminator(elArr);
        URLSafe64IntToChar = init64IntToChar(elArr);
        URLSafe64CharToInt = init64CharToInt(elArr);
        Base64EncodingIntToChar = init64IntToChar(elArr);
        Base64EncodingCharToInt = init64CharToInt(elArr);
        Base64EncodingTerminator = init64IntToCharTerminator(elArr);
    }

    private static char init64IntToCharTerminator(EL[] elArr) {
        for (EL el : elArr) {
            if (el.value == -1) {
                return el.letter;
            }
        }
        throw new RuntimeException(new IonException("fatal: invalid char map definition - missing terminator"));
    }

    private static int[] init64IntToChar(EL[] elArr) {
        int[] iArr = new int[64];
        for (EL el : elArr) {
            int i = el.value;
            if (i != -1) {
                iArr[i] = el.letter;
            }
        }
        return iArr;
    }

    private static int[] init64CharToInt(EL[] elArr) {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = -1;
        }
        for (EL el : elArr) {
            char c = el.letter;
            if (c > 255) {
                throw new RuntimeException("fatal base 64 encoding static initializer: letter out of bounds");
            }
            int i2 = el.value;
            if (i2 >= 0) {
                iArr[c] = i2;
            }
        }
        return iArr;
    }

    private Base64Encoder() {
    }

    static final class TextStream extends Reader {
        final int[] _bintochar;
        byte[] _inbuf;
        int _outBufEnd;
        int _outBufPos;
        char[] _outbuf;
        final char _padding;
        boolean _ready;
        final InputStream _source;
        int _state;

        @Override // java.io.Reader
        public boolean markSupported() {
            return false;
        }

        TextStream(InputStream inputStream, int[] iArr, char c) {
            this._inbuf = new byte[385];
            this._outbuf = new char[512];
            this._source = inputStream;
            this._bintochar = iArr;
            this._padding = c;
            this._ready = true;
        }

        public TextStream(InputStream inputStream) {
            this(inputStream, Base64Encoder.URLSafe64IntToChar, Base64Encoder.URLSafe64IntToCharTerminator);
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this._ready) {
                this._ready = false;
                this._source.close();
            }
            this._inbuf = null;
            this._outbuf = null;
        }

        @Override // java.io.Reader
        public void mark(int i) throws IOException {
            throw new UnsupportedOperationException();
        }

        private void loadNextBuffer() throws IOException {
            this._outBufEnd = 0;
            this._outBufPos = 0;
            int i = this._source.read(this._inbuf, 0, 384);
            if (i < 0) {
                this._state = 1;
                return;
            }
            int i2 = 0;
            while (i2 < i) {
                byte[] bArr = this._inbuf;
                int i3 = i2 + 1;
                int i4 = (bArr[i2] & 255) << 16;
                if (i3 >= i) {
                    char[] cArr = this._outbuf;
                    int i5 = this._outBufEnd;
                    int i6 = i5 + 1;
                    int[] iArr = this._bintochar;
                    cArr[i5] = (char) iArr[(16515072 & i4) >> 18];
                    int i7 = i6 + 1;
                    cArr[i6] = (char) iArr[(i4 & 258048) >> 12];
                    int i8 = i7 + 1;
                    char c = this._padding;
                    cArr[i7] = c;
                    this._outBufEnd = i8 + 1;
                    cArr[i8] = c;
                    return;
                }
                int i9 = i3 + 1;
                int i10 = i4 | ((bArr[i3] & 255) << 8);
                if (i9 >= i) {
                    char[] cArr2 = this._outbuf;
                    int i11 = this._outBufEnd;
                    int i12 = i11 + 1;
                    int[] iArr2 = this._bintochar;
                    cArr2[i11] = (char) iArr2[(16515072 & i10) >> 18];
                    int i13 = i12 + 1;
                    cArr2[i12] = (char) iArr2[(258048 & i10) >> 12];
                    int i14 = i13 + 1;
                    cArr2[i13] = (char) iArr2[(i10 & 4032) >> 6];
                    this._outBufEnd = i14 + 1;
                    cArr2[i14] = this._padding;
                    return;
                }
                int i15 = i9 + 1;
                int i16 = i10 | ((bArr[i9] & 255) << 0);
                char[] cArr3 = this._outbuf;
                int i17 = this._outBufEnd;
                int i18 = i17 + 1;
                int[] iArr3 = this._bintochar;
                cArr3[i17] = (char) iArr3[(16515072 & i16) >> 18];
                int i19 = i18 + 1;
                cArr3[i18] = (char) iArr3[(258048 & i16) >> 12];
                int i20 = i19 + 1;
                cArr3[i19] = (char) iArr3[(i16 & 4032) >> 6];
                this._outBufEnd = i20 + 1;
                cArr3[i20] = (char) iArr3[(i16 & 63) >> 0];
                i2 = i15;
            }
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName() + " is closed");
            }
            if (this._state != 0) {
                return -1;
            }
            if (this._outBufPos >= this._outBufEnd) {
                loadNextBuffer();
            }
            int i = this._outBufPos;
            if (i >= this._outBufEnd) {
                return -1;
            }
            char[] cArr = this._outbuf;
            this._outBufPos = i + 1;
            return cArr[i];
        }

        @Override // java.io.Reader
        public int read(char[] cArr) throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName() + " is closed");
            }
            if (this._state != 0) {
                return -1;
            }
            int length = cArr.length;
            while (length > 0) {
                if (this._outBufPos >= this._outBufEnd) {
                    loadNextBuffer();
                }
                int i = this._outBufPos;
                int i2 = this._outBufEnd;
                if (i >= i2) {
                    break;
                }
                int i3 = i2 - i;
                if (i3 > length) {
                    i3 = length;
                }
                System.arraycopy(this._outbuf, i, cArr, 0, i3);
                this._outBufPos += i3;
                length -= i3;
            }
            return 0;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName() + " is closed");
            }
            if (this._state != 0) {
                return -1;
            }
            while (i2 > 0) {
                if (this._outBufPos >= this._outBufEnd) {
                    loadNextBuffer();
                }
                int i3 = this._outBufPos;
                int i4 = this._outBufEnd;
                if (i3 >= i4) {
                    break;
                }
                int i5 = i4 - i3;
                if (i5 > i2) {
                    i5 = i2;
                }
                System.arraycopy(this._outbuf, i3, cArr, i, i5);
                this._outBufPos += i5;
                i += i5;
                i2 -= i5;
            }
            return i;
        }

        @Override // java.io.Reader
        public boolean ready() throws IOException {
            return this._ready && this._source.available() > 0;
        }

        @Override // java.io.Reader
        public void reset() throws IOException {
            throw new IOException("reset not supported");
        }

        @Override // java.io.Reader
        public long skip(long j) throws IOException {
            if (!this._ready) {
                throw new IOException(TextStream.class.getName() + " is closed");
            }
            long j2 = 0;
            if (j < 0) {
                throw new IllegalArgumentException("error skip only support non-negative a values for n");
            }
            long j3 = j;
            while (j3 > 0) {
                int i = this._outBufEnd - this._outBufPos;
                if (i < 1) {
                    loadNextBuffer();
                    i = this._outBufEnd - this._outBufPos;
                    if (i < 1) {
                        break;
                    }
                }
                long j4 = i;
                if (j4 > j3) {
                    this._outBufPos += (int) j3;
                    break;
                }
                j3 -= j4;
                this._outBufPos += i;
            }
            j2 = j3;
            return j - j2;
        }
    }
}
