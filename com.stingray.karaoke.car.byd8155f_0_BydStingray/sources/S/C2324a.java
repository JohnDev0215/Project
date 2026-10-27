package s;

import android.os.Looper;
import h6.f;

/* JADX INFO: renamed from: s.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2324a extends f {
    public static volatile C2324a d;
    public final C2326c c = new C2326c();

    public static C2324a w() {
        if (d != null) {
            return d;
        }
        synchronized (C2324a.class) {
            try {
                if (d == null) {
                    d = new C2324a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return d;
    }

    public final boolean x() {
        this.c.getClass();
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
