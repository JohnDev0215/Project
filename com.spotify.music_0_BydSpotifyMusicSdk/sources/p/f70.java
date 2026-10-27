package p;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class f70 extends kotlinx.coroutines.f implements Executor {
    public static final f70 k = new f70();
    public static final kotlinx.coroutines.b l;

    static {
        nq2 nq2Var = nq2.k;
        int i = pi2.a;
        if (64 >= i) {
            i = 64;
        }
        l = nq2Var.J0(v03.O("kotlinx.coroutines.io.parallelism", i, 0, 0, 12));
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        l.G0(y10Var, runnable);
    }

    @Override // kotlinx.coroutines.b
    public final void H0(y10 y10Var, Runnable runnable) {
        l.H0(y10Var, runnable);
    }

    @Override // kotlinx.coroutines.b
    public final kotlinx.coroutines.b J0(int i) {
        return nq2.k.J0(1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        G0(EmptyCoroutineContext.c, runnable);
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        return "Dispatchers.IO";
    }
}
