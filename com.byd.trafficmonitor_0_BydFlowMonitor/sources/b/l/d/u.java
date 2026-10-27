package b.l.d;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class u extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public StringBuilder f2931c = new StringBuilder(128);

    public u(String str) {
        this.f2930b = str;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        w();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        w();
    }

    public final void w() {
        if (this.f2931c.length() > 0) {
            Log.d(this.f2930b, this.f2931c.toString());
            StringBuilder sb = this.f2931c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                w();
            } else {
                this.f2931c.append(c2);
            }
        }
    }
}
