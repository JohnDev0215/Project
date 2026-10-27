package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class w extends Writer {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f741c;
    private StringBuilder d = new StringBuilder(128);

    w(String str) {
        this.f741c = str;
    }

    private void a() {
        if (this.d.length() > 0) {
            Log.d(this.f741c, this.d.toString());
            StringBuilder sb = this.d;
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
                this.d.append(c2);
            }
        }
    }
}
