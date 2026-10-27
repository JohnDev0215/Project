package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class FromNetASCIIOutputStream extends FilterOutputStream {
    private boolean lastWasCR;

    public FromNetASCIIOutputStream(OutputStream output) {
        super(output);
        this.lastWasCR = false;
    }

    private void __write(int ch) throws IOException {
        if (ch == 10) {
            if (this.lastWasCR) {
                this.out.write(FromNetASCIIInputStream._lineSeparatorBytes);
                this.lastWasCR = false;
                return;
            } else {
                this.lastWasCR = false;
                this.out.write(10);
                return;
            }
        }
        if (ch == 13) {
            this.lastWasCR = true;
            return;
        }
        if (this.lastWasCR) {
            this.out.write(13);
            this.lastWasCR = false;
        }
        this.out.write(ch);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int ch) throws IOException {
        if (FromNetASCIIInputStream._noConversionRequired) {
            this.out.write(ch);
        } else {
            __write(ch);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] buffer, int offset, int offset2) throws IOException {
        if (FromNetASCIIInputStream._noConversionRequired) {
            this.out.write(buffer, offset, offset2);
            return;
        }
        while (true) {
            int length = offset2 - 1;
            if (offset2 <= 0) {
                return;
            }
            int offset3 = offset + 1;
            __write(buffer[offset]);
            offset = offset3;
            offset2 = length;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (FromNetASCIIInputStream._noConversionRequired) {
            super.close();
            return;
        }
        if (this.lastWasCR) {
            this.out.write(13);
        }
        super.close();
    }
}
