package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public class LimitedInputStream extends FilterInputStream {
    private int mBytesToRead;
    private int mBytesToReadWhenMarked;

    public LimitedInputStream(InputStream inputStream, int limit) {
        super(inputStream);
        inputStream.getClass();
        if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        }
        this.mBytesToRead = limit;
        this.mBytesToReadWhenMarked = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.mBytesToRead == 0) {
            return -1;
        }
        int i = ((FilterInputStream) this).in.read();
        if (i != -1) {
            this.mBytesToRead--;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int i = this.mBytesToRead;
        if (i == 0) {
            return -1;
        }
        int i2 = ((FilterInputStream) this).in.read(buffer, byteOffset, Math.min(byteCount, i));
        if (i2 > 0) {
            this.mBytesToRead -= i2;
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long byteCount) throws IOException {
        long jSkip = ((FilterInputStream) this).in.skip(Math.min(byteCount, this.mBytesToRead));
        this.mBytesToRead = (int) (((long) this.mBytesToRead) - jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return Math.min(((FilterInputStream) this).in.available(), this.mBytesToRead);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int readLimit) {
        if (((FilterInputStream) this).in.markSupported()) {
            ((FilterInputStream) this).in.mark(readLimit);
            this.mBytesToReadWhenMarked = this.mBytesToRead;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        if (!((FilterInputStream) this).in.markSupported()) {
            throw new IOException("mark is not supported");
        }
        if (this.mBytesToReadWhenMarked == -1) {
            throw new IOException("mark not set");
        }
        ((FilterInputStream) this).in.reset();
        this.mBytesToRead = this.mBytesToReadWhenMarked;
    }
}
