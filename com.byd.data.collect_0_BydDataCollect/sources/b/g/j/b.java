package b.g.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StringBuilder f2658c = new StringBuilder(128);

    public b(String str) {
        this.f2657b = str;
    }

    private void a() {
        if (this.f2658c.length() > 0) {
            Log.d(this.f2657b, this.f2658c.toString());
            StringBuilder sb = this.f2658c;
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
    public void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                a();
            } else {
                this.f2658c.append(c2);
            }
        }
    }
}
