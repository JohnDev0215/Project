package org.tukaani.xz.rangecoder;

import androidx.core.view.ViewCompat;
import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes2.dex */
public final class RangeDecoderFromBuffer extends RangeDecoder {
    private static final int INIT_SIZE = 5;
    private final byte[] buf;
    private int pos;

    public RangeDecoderFromBuffer(int inputSizeMax, ArrayCache arrayCache) {
        byte[] byteArray = arrayCache.getByteArray(inputSizeMax - 5, false);
        this.buf = byteArray;
        this.pos = byteArray.length;
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
    }

    public void prepareInputBuffer(DataInputStream in, int len) throws IOException {
        if (len < 5) {
            throw new CorruptedInputException();
        }
        if (in.readUnsignedByte() != 0) {
            throw new CorruptedInputException();
        }
        this.code = in.readInt();
        this.range = -1;
        int len2 = len - 5;
        byte[] bArr = this.buf;
        int length = bArr.length - len2;
        this.pos = length;
        in.readFully(bArr, length, len2);
    }

    public boolean isFinished() {
        return this.pos == this.buf.length && this.code == 0;
    }

    @Override // org.tukaani.xz.rangecoder.RangeDecoder
    public void normalize() throws IOException {
        if ((this.range & ViewCompat.MEASURED_STATE_MASK) == 0) {
            try {
                int i = this.code << 8;
                byte[] bArr = this.buf;
                int i2 = this.pos;
                this.pos = i2 + 1;
                this.code = i | (bArr[i2] & 255);
                this.range <<= 8;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CorruptedInputException();
            }
        }
    }
}
