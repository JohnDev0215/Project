package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.e0;
import kotlinx.coroutines.internal.d0;
import kotlinx.coroutines.internal.f0;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final e0 f7762m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f7763n;

    static {
        c cVar = new c();
        f7763n = cVar;
        f7762m = new f(cVar, f0.d("kotlinx.coroutines.io.parallelism", v5.h.b(64, d0.a()), 0, 0, 12, null), "Dispatchers.IO", 1);
    }

    private c() {
        super(0, 0, null, 7, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    public final e0 f0() {
        return f7762m;
    }

    @Override // kotlinx.coroutines.e0
    public String toString() {
        return "Dispatchers.Default";
    }
}
