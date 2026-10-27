package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.internal.g;

/* JADX INFO: compiled from: ConcurrentLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<N extends g<N>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7629a = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_next");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7630b = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_prev");
    private volatile Object _next = null;
    private volatile Object _prev;

    public g(N n7) {
        this._prev = n7;
    }

    private final N c() {
        N n7 = (N) f();
        while (n7 != null && n7.g()) {
            n7 = (N) n7._prev;
        }
        return n7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object e() {
        return this._next;
    }

    private final N h() {
        N n7 = (N) d();
        kotlin.jvm.internal.l.c(n7);
        while (n7.g()) {
            n7 = (N) n7.d();
            kotlin.jvm.internal.l.c(n7);
        }
        return n7;
    }

    public final void b() {
        f7630b.lazySet(this, null);
    }

    public final N d() {
        Object objE = e();
        if (objE == f.f7628a) {
            return null;
        }
        return (N) objE;
    }

    public final N f() {
        return (N) this._prev;
    }

    public abstract boolean g();

    public final boolean i() {
        return d() == null;
    }

    public final void j() {
        while (true) {
            g gVarC = c();
            g gVarH = h();
            gVarH._prev = gVarC;
            if (gVarC != null) {
                gVarC._next = gVarH;
            }
            if (!gVarH.g() && (gVarC == null || !gVarC.g())) {
                return;
            }
        }
    }

    public final boolean k(N n7) {
        return kotlinx.coroutines.j.a(f7629a, this, null, n7);
    }
}
