package org.apache.commons.net.telnet;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
final class TelnetOutputStream extends OutputStream {
    private final TelnetClient client;
    private final boolean convertCRtoCRLF = true;
    private boolean lastWasCR;

    TelnetOutputStream(TelnetClient client) {
        this.client = client;
    }

    @Override // java.io.OutputStream
    public void write(int ch) throws IOException {
        synchronized (this.client) {
            int ch2 = ch & 255;
            if (this.client.requestedWont(0)) {
                if (this.lastWasCR) {
                    this.client.sendByte(10);
                    if (ch2 == 10) {
                        this.lastWasCR = false;
                        return;
                    }
                }
                if (ch2 != 10) {
                    if (ch2 != 13) {
                        if (ch2 == 255) {
                            this.client.sendByte(255);
                            this.client.sendByte(255);
                            this.lastWasCR = false;
                        } else {
                            this.client.sendByte(ch2);
                            this.lastWasCR = false;
                        }
                    } else {
                        this.client.sendByte(13);
                        this.lastWasCR = true;
                    }
                } else {
                    if (!this.lastWasCR) {
                        this.client.sendByte(13);
                    }
                    this.client.sendByte(ch2);
                    this.lastWasCR = false;
                }
            } else if (ch2 == 255) {
                this.client.sendByte(ch2);
                this.client.sendByte(255);
            } else {
                this.client.sendByte(ch2);
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer) throws Throwable {
        write(buffer, 0, buffer.length);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:? -> B:9:0x0011). Please report as a decompilation issue!!! */
    @Override // java.io.OutputStream
    public void write(byte[] buffer, int offset, int offset2) throws Throwable {
        Throwable th;
        synchronized (this.client) {
            while (true) {
                int length = offset2 - 1;
                if (offset2 > 0) {
                    int offset3 = offset + 1;
                    try {
                        write(buffer[offset]);
                        offset = offset3;
                        offset2 = length;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                } else {
                    try {
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.client.flushOutputStream();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.client.closeOutputStream();
    }
}
