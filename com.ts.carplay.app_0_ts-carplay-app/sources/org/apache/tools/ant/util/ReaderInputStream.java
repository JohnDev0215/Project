package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.tools.tar.TarConstants;

/* JADX INFO: loaded from: classes.dex */
public class ReaderInputStream extends InputStream {
    private int begin;
    private String encoding;
    private Reader in;
    private byte[] slack;

    public ReaderInputStream(Reader reader) {
        this.encoding = System.getProperty("file.encoding");
        this.in = reader;
    }

    public ReaderInputStream(Reader reader, String encoding) {
        this(reader);
        if (encoding == null) {
            throw new IllegalArgumentException("encoding must not be null");
        }
        this.encoding = encoding;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        byte result;
        if (this.in == null) {
            throw new IOException("Stream Closed");
        }
        if (this.slack != null && this.begin < this.slack.length) {
            result = this.slack[this.begin];
            int i = this.begin + 1;
            this.begin = i;
            if (i == this.slack.length) {
                this.slack = null;
            }
        } else {
            byte[] buf = new byte[1];
            if (read(buf, 0, 1) <= 0) {
            }
            result = buf[0];
        }
        if (result < -1) {
            result = (byte) (result + TarConstants.LF_OLDNORM);
        }
        return result;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] b, int off, int len) throws IOException {
        if (this.in == null) {
            throw new IOException("Stream Closed");
        }
        while (this.slack == null) {
            char[] buf = new char[len];
            int n = this.in.read(buf);
            if (n == -1) {
                return -1;
            }
            if (n > 0) {
                this.slack = new String(buf, 0, n).getBytes(this.encoding);
                this.begin = 0;
            }
        }
        if (len > this.slack.length - this.begin) {
            len = this.slack.length - this.begin;
        }
        System.arraycopy(this.slack, this.begin, b, off, len);
        int i = this.begin + len;
        this.begin = i;
        if (i >= this.slack.length) {
            this.slack = null;
        }
        return len;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int limit) {
        try {
            this.in.mark(limit);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream Closed");
        }
        if (this.slack != null) {
            return this.slack.length - this.begin;
        }
        if (this.in.ready()) {
            return 1;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream Closed");
        }
        this.slack = null;
        this.in.reset();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.in.close();
        this.slack = null;
        this.in = null;
    }
}
