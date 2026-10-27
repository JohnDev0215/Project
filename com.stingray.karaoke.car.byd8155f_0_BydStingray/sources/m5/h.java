package M5;

import A7.m;
import T.C0761q;
import g0.o;
import k6.AbstractC1692D;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class h implements InterfaceC2786o {
    public static final h l = new h();

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        float fD0;
        o oVarO;
        o oVar = (o) obj;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        m.f(oVar, "$this$composed");
        c0761q.Q(-717923371);
        if (((Boolean) c0761q.k(AbstractC1692D.f18239b)).booleanValue()) {
            oVarO = androidx.compose.foundation.layout.a.k(oVar, 0);
        } else {
            if (((Boolean) c0761q.k(AbstractC1692D.c)).booleanValue()) {
                fD0 = 0;
            } else {
                m6.b bVar = m6.c.f19252a;
                bVar.getClass();
                c0761q.Q(-1379251012);
                fD0 = bVar.D0(6, c0761q) + (bVar.C0(6, c0761q) * 2);
                c0761q.p(false);
            }
            oVarO = androidx.compose.foundation.layout.a.o(oVar, fD0, 0.0f, 0.0f, 0.0f, 14);
        }
        c0761q.p(false);
        return oVarO;
    }
}
