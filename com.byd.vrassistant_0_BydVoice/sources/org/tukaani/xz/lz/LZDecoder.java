package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.CorruptedInputException;

/* JADX INFO: loaded from: classes4.dex */
public final class LZDecoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final byte[] buf;
    private final int bufSize;
    private int full;
    private int pos;
    private int start;
    private int limit = 0;
    private int pendingLen = 0;
    private int pendingDist = 0;

    public LZDecoder(int i, byte[] bArr, ArrayCache arrayCache) {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.bufSize = i;
        byte[] byteArray = arrayCache.getByteArray(i, false);
        this.buf = byteArray;
        if (bArr != null) {
            int iMin = Math.min(bArr.length, i);
            this.pos = iMin;
            this.full = iMin;
            this.start = iMin;
            System.arraycopy(bArr, bArr.length - iMin, byteArray, 0, iMin);
        }
    }

    public void copyUncompressed(DataInputStream dataInputStream, int i) throws IOException {
        int iMin = Math.min(this.bufSize - this.pos, i);
        dataInputStream.readFully(this.buf, this.pos, iMin);
        int i2 = this.pos + iMin;
        this.pos = i2;
        if (this.full < i2) {
            this.full = i2;
        }
    }

    public int flush(byte[] bArr, int i) {
        int i2 = this.pos;
        int i3 = this.start;
        int i4 = i2 - i3;
        if (i2 == this.bufSize) {
            this.pos = 0;
        }
        System.arraycopy(this.buf, i3, bArr, i, i4);
        this.start = this.pos;
        return i4;
    }

    public int getByte(int i) {
        int i2 = this.pos;
        int i3 = (i2 - i) - 1;
        if (i >= i2) {
            i3 += this.bufSize;
        }
        return this.buf[i3] & 255;
    }

    public int getPos() {
        return this.pos;
    }

    public boolean hasPending() {
        return this.pendingLen > 0;
    }

    public boolean hasSpace() {
        return this.pos < this.limit;
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.buf);
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

    public void repeat(int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i >= this.full) {
            throw new CorruptedInputException();
        }
        int iMin = Math.min(this.limit - this.pos, i2);
        this.pendingLen = i2 - iMin;
        this.pendingDist = i;
        int i4 = (this.pos - i) - 1;
        if (i4 < 0) {
            int i5 = this.bufSize;
            int i6 = i4 + i5;
            int iMin2 = Math.min(i5 - i6, iMin);
            byte[] bArr = this.buf;
            System.arraycopy(bArr, i6, bArr, this.pos, iMin2);
            this.pos += iMin2;
            iMin -= iMin2;
            if (iMin == 0) {
                return;
            } else {
                i4 = 0;
            }
        }
        do {
            int iMin3 = Math.min(iMin, this.pos - i4);
            byte[] bArr2 = this.buf;
            System.arraycopy(bArr2, i4, bArr2, this.pos, iMin3);
            i3 = this.pos + iMin3;
            this.pos = i3;
            iMin -= iMin3;
        } while (iMin > 0);
        if (this.full < i3) {
            this.full = i3;
        }
    }

    public void repeatPending() throws IOException {
        int i = this.pendingLen;
        if (i > 0) {
            repeat(this.pendingDist, i);
        }
    }

    public void reset() {
        this.start = 0;
        this.pos = 0;
        this.full = 0;
        this.limit = 0;
        this.buf[this.bufSize - 1] = 0;
    }

    public void setLimit(int i) {
        int i2 = this.bufSize;
        int i3 = this.pos;
        if (i2 - i3 <= i) {
            this.limit = i2;
        } else {
            this.limit = i3 + i;
        }
    }
}
