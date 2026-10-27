package spi;

import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public final class ys {
    public static Thread a;

    private ys() {
    }

    public static void a() {
        if (a == null) {
            a = Looper.getMainLooper().getThread();
        }
        if (!(Thread.currentThread() == a)) {
            throw new IllegalStateException("Must be called on the Main thread.");
        }
    }
}
