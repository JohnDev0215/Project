package b.g.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StringBuilder f1781c = new StringBuilder(128);

    public b(String str) {
        this.f1780b = str;
    }

    private void o() {
        if (this.f1781c.length() > 0) {
            Log.d(this.f1780b, this.f1781c.toString());
            StringBuilder sb = this.f1781c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        o();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        o();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                o();
            } else {
                this.f1781c.append(c2);
            }
        }
    }
}
