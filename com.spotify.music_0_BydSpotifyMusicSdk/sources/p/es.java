package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class es extends q61 {
    public final zp m;

    public es(zp zpVar) {
        this.m = zpVar;
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        l((Throwable) obj);
        return lq2.a;
    }

    @Override // p.t61
    public final void l(Throwable th) {
        kotlinx.coroutines.g gVarK = k();
        zp zpVar = this.m;
        Throwable thP = zpVar.p(gVarK);
        if (zpVar.v()) {
            c00 c00Var = zpVar.l;
            v03.g(c00Var, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            ea0 ea0Var = (ea0) c00Var;
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ea0.f120p;
                Object obj = atomicReferenceFieldUpdater.get(ea0Var);
                tz2 tz2Var = fa0.b;
                if (!v03.b(obj, tz2Var)) {
                    if (!(obj instanceof Throwable)) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(ea0Var, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(ea0Var) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    return;
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(ea0Var, tz2Var, thP)) {
                    if (atomicReferenceFieldUpdater.get(ea0Var) != tz2Var) {
                        break;
                    }
                }
                return;
            }
        }
        zpVar.m(thP);
        if (zpVar.v()) {
            return;
        }
        zpVar.n();
    }
}
