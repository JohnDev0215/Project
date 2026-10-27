package b.g.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StringBuilder f1935c = new StringBuilder(128);

    public b(String str) {
        this.f1934b = str;
    }

    private void j() {
        if (this.f1935c.length() > 0) {
            Log.d(this.f1934b, this.f1935c.toString());
            StringBuilder sb = this.f1935c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        j();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        j();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                j();
            } else {
                this.f1935c.append(c2);
            }
        }
    }
}
