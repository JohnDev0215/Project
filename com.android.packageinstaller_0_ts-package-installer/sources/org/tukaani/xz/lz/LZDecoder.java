package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes2.dex */
public final class LZDecoder {
    private final byte[] buf;
    private final int bufSize;
    private int full;
    private int pos;
    private int start;
    private int limit = 0;
    private int pendingLen = 0;
    private int pendingDist = 0;

    public LZDecoder(int dictSize, byte[] presetDict, ArrayCache arrayCache) {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.bufSize = dictSize;
        this.buf = arrayCache.getByteArray(dictSize, false);
        if (presetDict != null) {
            int iMin = Math.min(presetDict.length, dictSize);
            this.pos = iMin;
            this.full = iMin;
            this.start = iMin;
            System.arraycopy(presetDict, presetDict.length - iMin, this.buf, 0, iMin);
        }
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
    }

    public void reset() {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.limit = 0;
        this.buf[this.bufSize - 1] = 0;
    }

    public void setLimit(int outMax) {
        int i = this.bufSize;
        int i2 = this.pos;
        if (i - i2 <= outMax) {
            this.limit = i;
        } else {
            this.limit = i2 + outMax;
        }
    }

    public boolean hasSpace() {
        return this.pos < this.limit;
    }

    public boolean hasPending() {
        return this.pendingLen > 0;
    }

    public int getPos() {
        return this.pos;
    }

    public int getByte(int dist) {
        int i = this.pos;
        int offset = (i - dist) - 1;
        if (dist >= i) {
            offset += this.bufSize;
        }
        return this.buf[offset] & 255;
    }

    public void putByte(byte b) {
        byte[] bArr = this.buf;
        int i = this.pos;
        int i2 = i + 1;
        this.pos = i2;
        bArr[i] = b;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public void repeat(int dist, int len) throws IOException {
        if (dist < 0 || dist >= this.full) {
            throw new CorruptedInputException();
        }
        int left = Math.min(this.limit - this.pos, len);
        this.pendingLen = len - left;
        this.pendingDist = dist;
        int i = this.pos;
        int back = (i - dist) - 1;
        if (dist >= i) {
            back += this.bufSize;
        }
        do {
            byte[] bArr = this.buf;
            int i2 = this.pos;
            this.pos = i2 + 1;
            int back2 = back + 1;
            bArr[i2] = bArr[back];
            if (back2 != this.bufSize) {
                back = back2;
            } else {
                back = 0;
            }
            left--;
        } while (left > 0);
        int i3 = this.full;
        int i4 = this.pos;
        if (i3 < i4) {
            this.full = i4;
        }
    }

    public void repeatPending() throws IOException {
        int i = this.pendingLen;
        if (i > 0) {
            repeat(this.pendingDist, i);
        }
    }

    public void copyUncompressed(DataInputStream inData, int len) throws IOException {
        int copySize = Math.min(this.bufSize - this.pos, len);
        inData.readFully(this.buf, this.pos, copySize);
        int i = this.pos + copySize;
        this.pos = i;
        if (this.full < i) {
            this.full = i;
        }
    }

    public int flush(byte[] out, int outOff) {
        int i = this.pos;
        int copySize = i - this.start;
        if (i == this.bufSize) {
            this.pos = 0;
        }
        System.arraycopy(this.buf, this.start, out, outOff, copySize);
        this.start = this.pos;
        return copySize;
    }
}
