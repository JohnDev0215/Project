package K;

import A.C0022k;
import A.C0024l;
import C0.C0114v;
import D.AbstractC0133o;
import D.C0130l;
import F0.AbstractC0227s0;
import F0.C0210j0;
import N0.AbstractC0418m;
import N0.C0408c;
import N0.C0409d;
import N0.C0411f;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import e0.C1178r;
import java.util.ArrayList;
import java.util.List;
import k3.T0;
import n0.C1880i;
import y0.AbstractC2737o;
import y0.InterfaceC2735m;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f3172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0738e0 f3173b = C0735d.O(null, T.S.f8224q);
    public C0411f c;
    public final C1178r d;

    public J0(C0411f c0411f) {
        N0.D d;
        this.f3172a = c0411f;
        C0408c c0408c = new C0408c(c0411f);
        List listA = c0411f.a(c0411f.l.length());
        int size = listA.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0409d c0409d = (C0409d) listA.get(i7);
            N0.I iA = ((AbstractC0418m) c0409d.f4371a).a();
            if (iA != null && (d = iA.f4352a) != null) {
                c0408c.a(d, c0409d.f4372b, c0409d.c);
            }
        }
        this.c = c0408c.f();
        this.d = new C1178r();
    }

    public final void a(int i7, C0761q c0761q) {
        int i10;
        char c;
        C1880i c1880iK;
        boolean z6;
        N0.D d;
        g0.o oVarM;
        N0.H h10;
        N0.G g7;
        c0761q.S(1154651354);
        char c10 = 2;
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            Object obj = (C0210j0) c0761q.k(AbstractC0227s0.f2267p);
            C0411f c0411f = this.c;
            List listA = c0411f.a(c0411f.l.length());
            int size = listA.size();
            int i11 = 0;
            while (i11 < size) {
                C0409d c0409d = (C0409d) listA.get(i11);
                int i12 = c0409d.f4372b;
                int i13 = c0409d.c;
                if (i12 != i13) {
                    c0761q.Q(1383677450);
                    C0411f c0411f2 = this.c;
                    C0738e0 c0738e0 = this.f3173b;
                    N0.H h11 = (N0.H) c0738e0.getValue();
                    boolean zA = A7.m.a(c0411f2, (h11 == null || (g7 = h11.f4348a) == null) ? null : g7.f4340a);
                    int i14 = c0409d.f4372b;
                    if (zA && (h10 = (N0.H) c0738e0.getValue()) != null) {
                        c1880iK = h10.k(i14, i13);
                        m0.d dVarB = h10.b(i14);
                        c1880iK.g(h6.e.a(h10.f(i14) == h10.f(i13) ? Math.min(h10.b(i13 - 1).f18882a, dVarB.f18882a) : 0.0f, dVarB.f18883b) ^ (-9223372034707292160L));
                    } else {
                        c1880iK = null;
                    }
                    B2.O o = c1880iK != null ? new B2.O(c1880iK, 25) : null;
                    g0.o oVar = g0.l.f14684a;
                    if (o != null && (oVarM = h6.q.m(oVar, o)) != null) {
                        oVar = oVarM;
                    }
                    Object objG = c0761q.G();
                    Object obj2 = C0753m.f8270a;
                    if (objG == obj2) {
                        objG = new C.k();
                        c0761q.a0(objG);
                    }
                    C.k kVar = (C.k) objG;
                    g0.o oVarG = androidx.compose.foundation.a.g(oVar.k(new M0(new T0(i14, i13, this))), kVar);
                    InterfaceC2735m.f24073a.getClass();
                    g0.o oVarA = L0.k.a(AbstractC2737o.h(oVarG, AbstractC2737o.f24075b), false, C0340g.f3265z);
                    boolean zH = c0761q.h(this) | c0761q.f(c0409d) | c0761q.h(obj);
                    Object objG2 = c0761q.G();
                    if (zH || objG2 == obj2) {
                        objG2 = new C0024l(this, c0409d, obj, 7);
                        c0761q.a0(objG2);
                    }
                    AbstractC0133o.a(androidx.compose.foundation.a.f(oVarA, kVar, (InterfaceC2772a) objG2), c0761q, 0);
                    AbstractC0418m abstractC0418m = (AbstractC0418m) c0409d.f4371a;
                    N0.I iA = abstractC0418m.a();
                    if (iA == null || (iA.f4352a == null && iA.f4353b == null && iA.c == null && iA.d == null)) {
                        z6 = false;
                        c = 2;
                        c0761q.Q(1386435086);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(1384566902);
                        Object objG3 = c0761q.G();
                        if (objG3 == obj2) {
                            objG3 = new C0333c0();
                            c0761q.a0(objG3);
                        }
                        C0333c0 c0333c0 = (C0333c0) objG3;
                        Object objG4 = c0761q.G();
                        if (objG4 == obj2) {
                            d = null;
                            objG4 = new H0(c0333c0, kVar, null);
                            c0761q.a0(objG4);
                        } else {
                            d = null;
                        }
                        C0735d.f(c0761q, kVar, (InterfaceC2785n) objG4);
                        c = 2;
                        Boolean boolValueOf = Boolean.valueOf((c0333c0.f3242a.i() & 2) != 0);
                        C0732b0 c0732b0 = c0333c0.f3242a;
                        Boolean boolValueOf2 = Boolean.valueOf((c0732b0.i() & 1) != 0);
                        Boolean boolValueOf3 = Boolean.valueOf((c0732b0.i() & 4) != 0);
                        N0.I iA2 = abstractC0418m.a();
                        N0.D d10 = iA2 != null ? iA2.f4352a : d;
                        N0.I iA3 = abstractC0418m.a();
                        N0.D d11 = iA3 != null ? iA3.f4353b : d;
                        N0.I iA4 = abstractC0418m.a();
                        N0.D d12 = iA4 != null ? iA4.c : d;
                        N0.I iA5 = abstractC0418m.a();
                        Object[] objArr = {boolValueOf, boolValueOf2, boolValueOf3, d10, d11, d12, iA5 != null ? iA5.d : d};
                        boolean zH2 = c0761q.h(this) | c0761q.f(c0409d);
                        Object objG5 = c0761q.G();
                        if (zH2 || objG5 == obj2) {
                            objG5 = new C0022k(this, c0409d, c0333c0, 7);
                            c0761q.a0(objG5);
                        }
                        b(objArr, (InterfaceC2782k) objG5, c0761q, (i10 << 6) & 896);
                        z6 = false;
                        c0761q.p(false);
                    }
                    c0761q.p(z6);
                } else {
                    c = c10;
                    c0761q.Q(1386448974);
                    c0761q.p(false);
                }
                i11++;
                c10 = c;
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 6, this);
        }
    }

    public final void b(Object[] objArr, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        c0761q.S(-2083052099);
        int i10 = (i7 & 48) == 0 ? (c0761q.h(interfaceC2782k) ? 32 : 16) | i7 : i7;
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(this) ? 256 : 128;
        }
        c0761q.O(-416686647, Integer.valueOf(objArr.length));
        for (Object obj : objArr) {
            i10 |= c0761q.h(obj) ? 4 : 0;
        }
        c0761q.p(false);
        if ((i10 & 14) == 0) {
            i10 |= 2;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            A7.D d = new A7.D(2);
            d.f(interfaceC2782k);
            d.j(objArr);
            ArrayList arrayList = d.l;
            Object[] array = arrayList.toArray(new Object[arrayList.size()]);
            boolean zH = c0761q.h(this) | ((i10 & 112) == 32);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new C0350l(this, interfaceC2782k, 1);
                c0761q.a0(objG);
            }
            C0735d.e(array, (InterfaceC2782k) objG, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(i7, 5, this, objArr, interfaceC2782k);
        }
    }
}
