package c.g.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StringBuilder f2268c = new StringBuilder(128);

    public b(String str) {
        this.f2267b = str;
    }

    private void a() {
        if (this.f2268c.length() > 0) {
            Log.d(this.f2267b, this.f2268c.toString());
            StringBuilder sb = this.f2268c;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c2 = cArr[i + i3];
            if (c2 == '\n') {
                a();
            } else {
                this.f2268c.append(c2);
            }
        }
    }
}
