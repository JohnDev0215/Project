package n0;

import android.util.Log;
import java.io.Writer;

/* JADX INFO: compiled from: LogWriter.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class b extends Writer {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6504d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public StringBuilder f6505e = new StringBuilder(128);

    public b(String str) {
        this.f6504d = str;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        e();
    }

    public final void e() {
        if (this.f6505e.length() > 0) {
            Log.d(this.f6504d, this.f6505e.toString());
            StringBuilder sb = this.f6505e;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        e();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i8, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            char c8 = cArr[i8 + i10];
            if (c8 == '\n') {
                e();
            } else {
                this.f6505e.append(c8);
            }
        }
    }
}
