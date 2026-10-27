package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
final class w extends Writer {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f2469e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private StringBuilder f2470g = new StringBuilder(128);

    w(String str) {
        this.f2469e = str;
    }

    private void m() {
        if (this.f2470g.length() > 0) {
            Log.d(this.f2469e, this.f2470g.toString());
            StringBuilder sb = this.f2470g;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        m();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        m();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i7, int i8) {
        for (int i9 = 0; i9 < i8; i9++) {
            char c7 = cArr[i7 + i9];
            if (c7 == '\n') {
                m();
            } else {
                this.f2470g.append(c7);
            }
        }
    }
}
