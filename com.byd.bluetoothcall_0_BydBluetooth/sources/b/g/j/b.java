package b.g.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StringBuilder f2291c = new StringBuilder(128);

    public b(String str) {
        this.f2290b = str;
    }

    private void k() {
        if (this.f2291c.length() > 0) {
            Log.d(this.f2290b, this.f2291c.toString());
            StringBuilder sb = this.f2291c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        k();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        k();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                k();
            } else {
                this.f2291c.append(c2);
            }
        }
    }
}
