package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class G extends W implements Executor {
    public static final G e = new G();
    private static final C0204p0 f;

    /* JADX WARN: Type inference failed for: r0v1, types: [d1, java.lang.Object, v] */
    static {
        ?? r0 = d1.e;
        int iA = U0.a();
        if (64 >= iA) {
            iA = 64;
        }
        int iD = U0.d("kotlinx.coroutines.io.parallelism", iA, 0, 0, 12);
        r0.getClass();
        if (!(iD >= 1)) {
            throw new IllegalArgumentException(C0186g0.g(Integer.valueOf(iD), "Expected positive parallelism level, but got ").toString());
        }
        f = new C0204p0(r0, iD);
    }

    private G() {
    }

    public final void c(AbstractC0208s abstractC0208s, Runnable runnable) {
        f.c(abstractC0208s, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        c(L.c, runnable);
    }

    public final String toString() {
        return "Dispatchers.IO";
    }
}
