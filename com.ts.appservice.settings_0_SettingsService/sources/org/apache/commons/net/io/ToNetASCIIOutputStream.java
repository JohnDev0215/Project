package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class ToNetASCIIOutputStream extends FilterOutputStream {
    private boolean lastWasCR;

    public ToNetASCIIOutputStream(OutputStream output) {
        super(output);
        this.lastWasCR = false;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int ch) throws IOException {
        if (ch != 10) {
            if (ch == 13) {
                this.lastWasCR = true;
                this.out.write(13);
                return;
            }
        } else if (!this.lastWasCR) {
            this.out.write(13);
        }
        this.lastWasCR = false;
        this.out.write(ch);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] buffer, int offset, int offset2) throws IOException {
        while (true) {
            int length = offset2 - 1;
            if (offset2 > 0) {
                int offset3 = offset + 1;
                write(buffer[offset]);
                offset = offset3;
                offset2 = length;
            }
        }
    }
}
