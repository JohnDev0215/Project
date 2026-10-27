package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BoundedArchiveInputStream extends InputStream {
    private final long end;
    private long loc;
    private ByteBuffer singleByteBuffer;

    protected abstract int read(long j, ByteBuffer byteBuffer) throws IOException;

    public BoundedArchiveInputStream(long j, long j2) {
        long j3 = j + j2;
        this.end = j3;
        if (j3 < j) {
            throw new IllegalArgumentException("Invalid length of stream at offset=" + j + ", length=" + j2);
        }
        this.loc = j;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.loc >= this.end) {
            return -1;
        }
        ByteBuffer byteBuffer = this.singleByteBuffer;
        if (byteBuffer == null) {
            this.singleByteBuffer = ByteBuffer.allocate(1);
        } else {
            byteBuffer.rewind();
        }
        if (read(this.loc, this.singleByteBuffer) < 1) {
            return -1;
        }
        this.loc++;
        return this.singleByteBuffer.get() & 255;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.loc;
        long j2 = this.end;
        if (j >= j2) {
            return -1;
        }
        long jMin = Math.min(i2, j2 - j);
        if (jMin <= 0) {
            return 0;
        }
        if (i < 0 || i > bArr.length || jMin > bArr.length - i) {
            throw new IndexOutOfBoundsException("offset or len are out of bounds");
        }
        int i3 = read(this.loc, ByteBuffer.wrap(bArr, i, (int) jMin));
        if (i3 > 0) {
            this.loc += (long) i3;
        }
        return i3;
    }
}
