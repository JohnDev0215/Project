package a.h.j;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public StringBuilder f800b = new StringBuilder(128);

    public b(String str) {
        this.f799a = str;
    }

    public final void a() {
        if (this.f800b.length() > 0) {
            Log.d(this.f799a, this.f800b.toString());
            StringBuilder sb = this.f800b;
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
                this.f800b.append(c2);
            }
        }
    }
}
