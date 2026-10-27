package defpackage;

import android.os.SystemClock;
import java.io.Closeable;

/* JADX INFO: compiled from: Timer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class va3 implements Closeable {
    public static va3 e;

    public va3() {
        SystemClock.uptimeMillis();
        if (e == null) {
            e = this;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (e == this) {
            e = null;
            SystemClock.uptimeMillis();
        }
    }
}
