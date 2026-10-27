package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.simple.SimpleFilter;

/* JADX INFO: loaded from: classes2.dex */
class SimpleInputStream extends InputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILTER_BUF_SIZE = 4096;
    private InputStream in;
    private final SimpleFilter simpleFilter;
    private final byte[] filterBuf = new byte[4096];
    private int pos = 0;
    private int filtered = 0;
    private int unfiltered = 0;
    private boolean endReached = $assertionsDisabled;
    private IOException exception = null;
    private final byte[] tempBuf = new byte[1];

    static int getMemoryUsage() {
        return 5;
    }

    SimpleInputStream(InputStream in, SimpleFilter simpleFilter) {
        if (in == null) {
            throw null;
        }
        this.in = in;
        this.simpleFilter = simpleFilter;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        int size = 0;
        while (true) {
            try {
                int copySize = Math.min(this.filtered, len);
                System.arraycopy(this.filterBuf, this.pos, buf, off, copySize);
                int i = this.pos + copySize;
                this.pos = i;
                int i2 = this.filtered - copySize;
                this.filtered = i2;
                off += copySize;
                len -= copySize;
                size += copySize;
                if (i + i2 + this.unfiltered == 4096) {
                    System.arraycopy(this.filterBuf, i, this.filterBuf, 0, i2 + this.unfiltered);
                    this.pos = 0;
                }
                if (len == 0 || this.endReached) {
                    break;
                }
                int inSize = this.in.read(this.filterBuf, this.pos + this.filtered + this.unfiltered, 4096 - ((this.pos + this.filtered) + this.unfiltered));
                if (inSize == -1) {
                    this.endReached = true;
                    this.filtered = this.unfiltered;
                    this.unfiltered = 0;
                } else {
                    int i3 = this.unfiltered + inSize;
                    this.unfiltered = i3;
                    int iCode = this.simpleFilter.code(this.filterBuf, this.pos, i3);
                    this.filtered = iCode;
                    this.unfiltered -= iCode;
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
        if (size > 0) {
            return size;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        return this.filtered;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }
}
