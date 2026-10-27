package X1;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public final class l extends Writer {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final StringBuilder f9993m = new StringBuilder(128);
    public final String l = "FragmentManager";

    public final void b() {
        StringBuilder sb = this.f9993m;
        if (sb.length() > 0) {
            Log.d(this.l, sb.toString());
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        b();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        b();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i7, int i10) {
        for (int i11 = 0; i11 < i10; i11++) {
            char c = cArr[i7 + i11];
            if (c == '\n') {
                b();
            } else {
                this.f9993m.append(c);
            }
        }
    }
}
