package org.tukaani.xz;

import java.io.IOException;
import org.tukaani.xz.simple.SimpleFilter;

/* JADX INFO: loaded from: classes2.dex */
class SimpleOutputStream extends FinishableOutputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILTER_BUF_SIZE = 4096;
    private FinishableOutputStream out;
    private final SimpleFilter simpleFilter;
    private final byte[] filterBuf = new byte[4096];
    private int pos = 0;
    private int unfiltered = 0;
    private IOException exception = null;
    private boolean finished = $assertionsDisabled;
    private final byte[] tempBuf = new byte[1];

    static int getMemoryUsage() {
        return 5;
    }

    SimpleOutputStream(FinishableOutputStream out, SimpleFilter simpleFilter) {
        if (out == null) {
            throw null;
        }
        this.out = out;
        this.simpleFilter = simpleFilter;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) b;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        while (len > 0) {
            int copySize = Math.min(len, 4096 - (this.pos + this.unfiltered));
            System.arraycopy(buf, off, this.filterBuf, this.pos + this.unfiltered, copySize);
            off += copySize;
            len -= copySize;
            int i = this.unfiltered + copySize;
            this.unfiltered = i;
            int filtered = this.simpleFilter.code(this.filterBuf, this.pos, i);
            this.unfiltered -= filtered;
            try {
                this.out.write(this.filterBuf, this.pos, filtered);
                int i2 = this.pos + filtered;
                this.pos = i2;
                int i3 = this.unfiltered;
                if (i2 + i3 == 4096) {
                    byte[] bArr = this.filterBuf;
                    System.arraycopy(bArr, i2, bArr, 0, i3);
                    this.pos = 0;
                }
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    private void writePending() throws IOException {
        IOException e = this.exception;
        if (e != null) {
            throw e;
        }
        try {
            this.out.write(this.filterBuf, this.pos, this.unfiltered);
            this.finished = true;
        } catch (IOException e2) {
            this.exception = e2;
            throw e2;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw new UnsupportedOptionsException("Flushing is not supported");
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (!this.finished) {
            writePending();
            try {
                this.out.finish();
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            if (!this.finished) {
                try {
                    writePending();
                } catch (IOException e) {
                }
            }
            try {
                this.out.close();
            } catch (IOException e2) {
                if (this.exception == null) {
                    this.exception = e2;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }
}
