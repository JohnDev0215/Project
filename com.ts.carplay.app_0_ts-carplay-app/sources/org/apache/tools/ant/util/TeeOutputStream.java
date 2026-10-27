package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class TeeOutputStream extends OutputStream {
    private OutputStream left;
    private OutputStream right;

    public TeeOutputStream(OutputStream left, OutputStream right) {
        this.left = left;
        this.right = right;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.left.close();
        } finally {
            this.right.close();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.left.flush();
        this.right.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.left.write(b);
        this.right.write(b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.left.write(b, off, len);
        this.right.write(b, off, len);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.left.write(b);
        this.right.write(b);
    }
}
