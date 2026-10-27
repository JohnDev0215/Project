package b.f.l;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1727c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public StringBuilder f1728d = new StringBuilder(128);

    public b(String str) {
        this.f1727c = str;
    }

    public final void c() {
        if (this.f1728d.length() > 0) {
            Log.d(this.f1727c, this.f1728d.toString());
            StringBuilder sb = this.f1728d;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        c();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        c();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            char c2 = cArr[i2 + i4];
            if (c2 == '\n') {
                c();
            } else {
                this.f1728d.append(c2);
            }
        }
    }
}
