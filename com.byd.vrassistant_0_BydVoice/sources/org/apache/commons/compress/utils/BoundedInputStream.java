package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public class BoundedInputStream extends InputStream {
    private long bytesRemaining;
    private final InputStream in;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public BoundedInputStream(InputStream inputStream, long j) {
        this.in = inputStream;
        this.bytesRemaining = j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.bytesRemaining;
        if (j <= 0) {
            return -1;
        }
        this.bytesRemaining = j - 1;
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (i2 > j) {
            i2 = (int) j;
        }
        int i3 = this.in.read(bArr, i, i2);
        if (i3 >= 0) {
            this.bytesRemaining -= (long) i3;
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = this.in.skip(Math.min(this.bytesRemaining, j));
        this.bytesRemaining -= jSkip;
        return jSkip;
    }

    public long getBytesRemaining() {
        return this.bytesRemaining;
    }
}
