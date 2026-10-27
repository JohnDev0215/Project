package S5;

import A3.v;
import D.L;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import k6.AbstractC1692D;
import n0.AbstractC1861K;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b0.b f8006m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8007n;

    public /* synthetic */ g(b0.b bVar, boolean z6, int i7) {
        this.l = i7;
        this.f8006m = bVar;
        this.f8007n = z6;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    g0.l lVar = g0.l.f14684a;
                    c0761q.Q(922869720);
                    Object objG = c0761q.G();
                    if (objG == C0753m.f8270a) {
                        objG = new v(3);
                        c0761q.a0(objG);
                    }
                    c0761q.p(false);
                    g0.o oVarO = com.bumptech.glide.c.O(lVar, (InterfaceC2772a) objG);
                    m6.b bVar = m6.c.f19252a;
                    g0.o oVarB = androidx.compose.foundation.a.b(h6.q.m(oVarO, J.e.a(bVar.e0(c0761q))), l6.d.c(c0761q).f18706h, AbstractC1861K.f19381a);
                    A7.m.f(oVarB, "<this>");
                    g0.o oVarB2 = g0.a.b(oVarB, new M5.a(1, false, false));
                    A7.m.f(oVarB2, "<this>");
                    g0.o oVarB3 = g0.a.b(oVarB2, new M5.a(0, false, this.f8007n));
                    float fJ = bVar.J(c0761q);
                    float fK = bVar.K(c0761q);
                    this.f8006m.o(oVarB3, new L(fJ, fK, fJ, fK), c0761q, 0);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    C0735d.a(AbstractC1692D.f18238a.a(h6.i.m(c0761q2)), b0.g.b(-1049967127, new g(this.f8006m, this.f8007n, 0), c0761q2), c0761q2, 56);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
