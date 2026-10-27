package p;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class eb extends l13 {
    public static volatile eb g;
    public static final db h = new db(1);
    public final c80 f = new c80();

    public static eb w() {
        if (g != null) {
            return g;
        }
        synchronized (eb.class) {
            try {
                if (g == null) {
                    g = new eb();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return g;
    }

    public final void x(Runnable runnable) {
        c80 c80Var = this.f;
        if (c80Var.h == null) {
            synchronized (c80Var.f) {
                try {
                    if (c80Var.h == null) {
                        c80Var.h = c80.w(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        c80Var.h.post(runnable);
    }
}
