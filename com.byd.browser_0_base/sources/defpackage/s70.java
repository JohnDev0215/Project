package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class s70 extends hi0 implements Executor {
    public static final s70 f = new s70();
    public static final f71 g;

    static {
        wi3 wi3Var = wi3.f;
        int i = gy2.a;
        if (64 >= i) {
            i = 64;
        }
        int iR1 = n3.r1("kotlinx.coroutines.io.parallelism", i, 0, 0, 12);
        wi3Var.getClass();
        if (!(iR1 >= 1)) {
            throw new IllegalArgumentException(i21.g(Integer.valueOf(iR1), "Expected positive parallelism level, but got ").toString());
        }
        g = new f71(wi3Var, iR1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        h1(dh0.e, runnable);
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        g.h1(m00Var, runnable);
    }

    @Override // defpackage.p00
    public final String toString() {
        return "Dispatchers.IO";
    }
}
