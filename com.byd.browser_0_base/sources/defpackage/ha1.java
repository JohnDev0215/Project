package defpackage;

import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ha1 extends Writer {
    public final StringBuilder e = new StringBuilder(128);

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        q();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        q();
    }

    public final void q() {
        StringBuilder sb = this.e;
        if (sb.length() > 0) {
            sb.toString();
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                q();
            } else {
                this.e.append(c);
            }
        }
    }
}
