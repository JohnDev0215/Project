package org.apache.commons.net.io;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.net.SocketClient;

/* JADX INFO: loaded from: classes3.dex */
public final class DotTerminatedMessageWriter extends Writer {
    private static final int LAST_WAS_CR_STATE = 1;
    private static final int LAST_WAS_NL_STATE = 2;
    private static final int NOTHING_SPECIAL_STATE = 0;
    private Writer output;
    private int state;

    public DotTerminatedMessageWriter(Writer output) {
        super(output);
        this.output = output;
        this.state = 0;
    }

    @Override // java.io.Writer
    public void write(int ch) throws IOException {
        synchronized (this.lock) {
            try {
                if (ch == 10) {
                    if (this.state != 1) {
                        this.output.write(13);
                    }
                    this.output.write(10);
                    this.state = 2;
                    return;
                }
                if (ch != 13) {
                    if (ch == 46 && this.state == 2) {
                        this.output.write(46);
                    }
                    this.state = 0;
                    this.output.write(ch);
                    return;
                }
                this.state = 1;
                this.output.write(13);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:? -> B:9:0x0011). Please report as a decompilation issue!!! */
    @Override // java.io.Writer
    public void write(char[] buffer, int offset, int offset2) throws Throwable {
        Throwable th;
        synchronized (this.lock) {
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

    @Override // java.io.Writer
    public void write(char[] buffer) throws Throwable {
        write(buffer, 0, buffer.length);
    }

    @Override // java.io.Writer
    public void write(String string) throws Throwable {
        write(string.toCharArray());
    }

    @Override // java.io.Writer
    public void write(String string, int offset, int length) throws Throwable {
        write(string.toCharArray(), offset, length);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        synchronized (this.lock) {
            this.output.flush();
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.output == null) {
                return;
            }
            if (this.state == 1) {
                this.output.write(10);
            } else if (this.state != 2) {
                this.output.write(SocketClient.NETASCII_EOL);
            }
            this.output.write(".\r\n");
            this.output.flush();
            this.output = null;
        }
    }
}
