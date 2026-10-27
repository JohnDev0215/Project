package org.tukaani.xz.rangecoder;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes2.dex */
public final class RangeEncoderToBuffer extends RangeEncoder {
    private final byte[] buf;
    private int bufPos;

    public RangeEncoderToBuffer(int bufSize, ArrayCache arrayCache) {
        this.buf = arrayCache.getByteArray(bufSize, false);
        reset();
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    public void reset() {
        super.reset();
        this.bufPos = 0;
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    public int getPendingSize() {
        return ((this.bufPos + ((int) this.cacheSize)) + 5) - 1;
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    public int finish() {
        try {
            super.finish();
            return this.bufPos;
        } catch (IOException e) {
            throw new Error();
        }
    }

    public void write(OutputStream out) throws IOException {
        out.write(this.buf, 0, this.bufPos);
    }

    @Override // org.tukaani.xz.rangecoder.RangeEncoder
    void writeByte(int b) {
        byte[] bArr = this.buf;
        int i = this.bufPos;
        this.bufPos = i + 1;
        bArr[i] = (byte) b;
    }
}
