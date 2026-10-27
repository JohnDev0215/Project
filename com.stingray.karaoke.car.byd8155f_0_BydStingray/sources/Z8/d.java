package Z8;

import S8.AbstractC0721s;
import S8.O;
import X8.t;
import java.util.concurrent.Executor;
import q7.C2195i;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class d extends O implements Executor {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f11010n = new d();
    public static final AbstractC0721s o;

    static {
        l lVar = l.f11019n;
        int i7 = t.f10579a;
        if (64 >= i7) {
            i7 = 64;
        }
        o = lVar.O(X8.a.l("kotlinx.coroutines.io.parallelism", i7, 0, 0, 12), null);
    }

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        o.L(interfaceC2194h, runnable);
    }

    @Override // S8.AbstractC0721s
    public final void M(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        o.M(interfaceC2194h, runnable);
    }

    @Override // S8.AbstractC0721s
    public final AbstractC0721s O(int i7, String str) {
        return l.f11019n.O(1, str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        L(C2195i.l, runnable);
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        return "Dispatchers.IO";
    }
}
