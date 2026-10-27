package org.apache.commons.net.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class ToNetASCIIInputStream extends FilterInputStream {
    private static final int LAST_WAS_CR = 1;
    private static final int LAST_WAS_NL = 2;
    private static final int NOTHING_SPECIAL = 0;
    private int status;

    public ToNetASCIIInputStream(InputStream input) {
        super(input);
        this.status = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.status == 2) {
            this.status = 0;
            return 10;
        }
        int ch = this.in.read();
        if (ch != 10) {
            if (ch == 13) {
                this.status = 1;
                return 13;
            }
        } else if (this.status != 1) {
            this.status = 2;
            return 13;
        }
        this.status = 0;
        return ch;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int offset2;
        if (length < 1) {
            return 0;
        }
        int ch = available();
        if (length > ch) {
            length = ch;
        }
        if (length < 1) {
            length = 1;
        }
        int i = read();
        int ch2 = i;
        if (i == -1) {
            return -1;
        }
        while (true) {
            offset2 = offset + 1;
            buffer[offset] = (byte) ch2;
            length--;
            if (length <= 0) {
                break;
            }
            int i2 = read();
            ch2 = i2;
            if (i2 == -1) {
                break;
            }
            offset = offset2;
        }
        return offset2 - offset;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int result = this.in.available();
        if (this.status == 2) {
            return result + 1;
        }
        return result;
    }
}
