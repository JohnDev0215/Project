package org.greenrobot.essentials.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes5.dex */
public class LimitedInputStream extends InputStream {
    private int bytesLeft;
    private final InputStream in;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public static GZIPInputStream createGZIPInputStream(InputStream inputStream, int i) throws IOException {
        return new GZIPInputStream(new LimitedInputStream(inputStream, i));
    }

    public LimitedInputStream(InputStream inputStream, int i) {
        this.in = inputStream;
        this.bytesLeft = i;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return Math.min(this.in.available(), this.bytesLeft);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bytesLeft <= 0) {
            return -1;
        }
        int i = this.in.read();
        if (i != -1) {
            this.bytesLeft--;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.bytesLeft;
        if (i3 <= 0) {
            return -1;
        }
        int i4 = this.in.read(bArr, i, Math.min(i3, i2));
        if (i4 > 0) {
            this.bytesLeft -= i4;
        }
        return i4;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long jSkip = this.in.skip(Math.min(this.bytesLeft, j));
        if (jSkip > 0) {
            this.bytesLeft = (int) (((long) this.bytesLeft) - jSkip);
        }
        return jSkip;
    }

    public int getBytesLeft() {
        return this.bytesLeft;
    }
}
