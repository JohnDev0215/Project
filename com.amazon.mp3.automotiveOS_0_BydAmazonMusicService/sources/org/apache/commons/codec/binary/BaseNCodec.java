package org.apache.commons.codec.binary;

import java.util.Arrays;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BaseNCodec {

    @Deprecated
    protected final byte PAD;
    private final int chunkSeparatorLength;
    private final int encodedBlockSize;
    protected final int lineLength;
    protected final byte pad;
    private final int unencodedBlockSize;

    abstract void decode(byte[] bArr, int i, int i2, Context context);

    abstract void encode(byte[] bArr, int i, int i2, Context context);

    protected int getDefaultBufferSize() {
        return 8192;
    }

    protected abstract boolean isInAlphabet(byte b);

    static class Context {
        byte[] buffer;
        int currentLinePos;
        boolean eof;
        int ibitWorkArea;
        long lbitWorkArea;
        int modulus;
        int pos;
        int readPos;

        Context() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos), Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea), Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos));
        }
    }

    protected BaseNCodec(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (byte) 61);
    }

    protected BaseNCodec(int i, int i2, int i3, int i4, byte b) {
        this.PAD = (byte) 61;
        this.unencodedBlockSize = i;
        this.encodedBlockSize = i2;
        this.lineLength = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.chunkSeparatorLength = i4;
        this.pad = b;
    }

    int available(Context context) {
        if (context.buffer != null) {
            return context.pos - context.readPos;
        }
        return 0;
    }

    private byte[] resizeBuffer(Context context) {
        byte[] bArr = context.buffer;
        if (bArr == null) {
            context.buffer = new byte[getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        } else {
            byte[] bArr2 = new byte[bArr.length * 2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            context.buffer = bArr2;
        }
        return context.buffer;
    }

    protected byte[] ensureBufferSize(int i, Context context) {
        byte[] bArr = context.buffer;
        return (bArr == null || bArr.length < context.pos + i) ? resizeBuffer(context) : bArr;
    }

    int readResults(byte[] bArr, int i, int i2, Context context) {
        if (context.buffer == null) {
            return context.eof ? -1 : 0;
        }
        int iMin = Math.min(available(context), i2);
        System.arraycopy(context.buffer, context.readPos, bArr, i, iMin);
        int i3 = context.readPos + iMin;
        context.readPos = i3;
        if (i3 >= context.pos) {
            context.buffer = null;
        }
        return iMin;
    }

    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        decode(bArr, 0, bArr.length, context);
        decode(bArr, 0, -1, context);
        int i = context.pos;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, context);
        return bArr2;
    }

    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        encode(bArr, 0, bArr.length, context);
        encode(bArr, 0, -1, context);
        int i = context.pos - context.readPos;
        byte[] bArr2 = new byte[i];
        readResults(bArr2, 0, i, context);
        return bArr2;
    }

    protected boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.pad == b || isInAlphabet(b)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        long j = ((long) (((length + r0) - 1) / this.unencodedBlockSize)) * ((long) this.encodedBlockSize);
        int i = this.lineLength;
        return i > 0 ? j + ((((((long) i) + j) - 1) / ((long) i)) * ((long) this.chunkSeparatorLength)) : j;
    }
}
