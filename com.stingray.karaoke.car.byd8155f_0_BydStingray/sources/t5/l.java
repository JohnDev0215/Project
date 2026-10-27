package T5;

import D.AbstractC0133o;
import Q.C0523o0;
import T.C0761q;
import g0.o;
import h6.q;
import l9.n;
import n0.AbstractC1861K;
import n0.C1891t;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class l implements InterfaceC2786o {
    public final /* synthetic */ float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f8736m;

    public l(float f10, long j3) {
        this.l = f10;
        this.f8736m = j3;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f((C0523o0) obj, "it");
        if ((iIntValue & 17) == 16 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            m6.b bVar = m6.c.f19252a;
            o oVarE = androidx.compose.foundation.layout.c.e(oVarD, bVar.c0(c0761q));
            J.d dVar = J.e.f3041a;
            o oVarM = q.m(oVarE, dVar);
            long jB = C1891t.b(0.3f, l6.d.c(c0761q).f18696b);
            n nVar = AbstractC1861K.f19381a;
            AbstractC0133o.a(androidx.compose.foundation.a.b(oVarM, jB, nVar), c0761q, 0);
            AbstractC0133o.a(androidx.compose.foundation.a.b(q.m(androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.d(lVar, this.l / this.f8736m), bVar.c0(c0761q)), dVar), l6.d.c(c0761q).f18702f, nVar), c0761q, 0);
        }
        return m7.q.f19267a;
    }
}
