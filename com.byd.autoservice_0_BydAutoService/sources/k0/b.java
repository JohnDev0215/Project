package k0;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3577b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public StringBuilder f3578c = new StringBuilder(128);

    public b(String str) {
        this.f3577b = str;
    }

    public final void a() {
        if (this.f3578c.length() > 0) {
            Log.d(this.f3577b, this.f3578c.toString());
            StringBuilder sb = this.f3578c;
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
    public void write(char[] cArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            char c3 = cArr[i3 + i5];
            if (c3 == '\n') {
                a();
            } else {
                this.f3578c.append(c3);
            }
        }
    }
}
