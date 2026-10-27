package org.apache.commons.compress.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes4.dex */
public class BitInputStream implements Closeable {
    private static final long[] MASKS = new long[64];
    private static final int MAXIMUM_CACHE_SIZE = 63;
    private long bitsCached;
    private int bitsCachedSize;
    private final ByteOrder byteOrder;
    private final CountingInputStream in;

    static {
        for (int i = 1; i <= 63; i++) {
            long[] jArr = MASKS;
            jArr[i] = (jArr[i - 1] << 1) + 1;
        }
    }

    public BitInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.in = new CountingInputStream(inputStream);
        this.byteOrder = byteOrder;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    public void clearBitCache() {
        this.bitsCached = 0L;
        this.bitsCachedSize = 0;
    }

    public long readBits(int i) throws IOException {
        if (i < 0 || i > 63) {
            throw new IOException("count must not be negative or greater than 63");
        }
        if (ensureCache(i)) {
            return -1L;
        }
        if (this.bitsCachedSize < i) {
            return processBitsGreater57(i);
        }
        return readCachedBits(i);
    }

    public int bitsCached() {
        return this.bitsCachedSize;
    }

    public long bitsAvailable() throws IOException {
        return ((long) this.bitsCachedSize) + (((long) this.in.available()) * 8);
    }

    public void alignWithByteBoundary() {
        int i = this.bitsCachedSize % 8;
        if (i > 0) {
            readCachedBits(i);
        }
    }

    public long getBytesRead() {
        return this.in.getBytesRead();
    }

    private long processBitsGreater57(int i) throws IOException {
        long j;
        int i2 = i - this.bitsCachedSize;
        int i3 = 8 - i2;
        long j2 = this.in.read();
        if (j2 < 0) {
            return j2;
        }
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            long[] jArr = MASKS;
            this.bitsCached = ((jArr[i2] & j2) << this.bitsCachedSize) | this.bitsCached;
            j2 >>>= i2;
            j = jArr[i3];
        } else {
            long j3 = this.bitsCached << i2;
            long[] jArr2 = MASKS;
            this.bitsCached = j3 | ((j2 >>> i3) & jArr2[i2]);
            j = jArr2[i3];
        }
        long j4 = j2 & j;
        long j5 = this.bitsCached & MASKS[i];
        this.bitsCached = j4;
        this.bitsCachedSize = i3;
        return j5;
    }

    private long readCachedBits(int i) {
        long j;
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            long j2 = this.bitsCached;
            j = MASKS[i] & j2;
            this.bitsCached = j2 >>> i;
        } else {
            j = MASKS[i] & (this.bitsCached >> (this.bitsCachedSize - i));
        }
        this.bitsCachedSize -= i;
        return j;
    }

    private boolean ensureCache(int i) throws IOException {
        while (true) {
            int i2 = this.bitsCachedSize;
            if (i2 >= i || i2 >= 57) {
                return false;
            }
            long j = this.in.read();
            if (j < 0) {
                return true;
            }
            if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.bitsCached = (j << this.bitsCachedSize) | this.bitsCached;
            } else {
                this.bitsCached = j | (this.bitsCached << 8);
            }
            this.bitsCachedSize += 8;
        }
    }
}
