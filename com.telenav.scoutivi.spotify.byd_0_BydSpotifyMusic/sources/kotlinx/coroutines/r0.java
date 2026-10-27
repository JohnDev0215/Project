package kotlinx.coroutines;

import k5.g;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r0 {
    public static final Object a(long j7, k5.d<? super g5.r> dVar) throws Throwable {
        if (j7 <= 0) {
            return g5.r.f6563a;
        }
        k kVar = new k(l5.c.c(dVar), 1);
        kVar.A();
        if (j7 < Long.MAX_VALUE) {
            b(kVar.getContext()).A(j7, kVar);
        }
        Object objY = kVar.y();
        if (objY == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objY;
    }

    public static final q0 b(k5.g gVar) {
        g.b bVar = gVar.get(k5.e.f7273b);
        if (!(bVar instanceof q0)) {
            bVar = null;
        }
        q0 q0Var = (q0) bVar;
        return q0Var != null ? q0Var : o0.a();
    }

    public static final long c(double d7) {
        if (z5.a.compareTo-LRDsOJo(d7, z5.a.f10049g.getZERO-UwyO8pc()) > 0) {
            return v5.h.c(z5.a.toLongMilliseconds-impl(d7), 1L);
        }
        return 0L;
    }
}
