package E0;

import C0.C0107n;
import io.sentry.C1489n1;

/* JADX INFO: renamed from: E0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0169t extends S {
    @Override // E0.S
    public final void F0() {
        K kY = this.f1582w.f1648w.y();
        A7.m.c(kY);
        kY.x0();
    }

    @Override // C0.J
    public final int P(int i7) {
        C1489n1 c1489n1F = this.f1582w.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.a((d0) f10.f1469H.d, f10.n(), i7);
    }

    @Override // C0.J
    public final int V(int i7) {
        C1489n1 c1489n1F = this.f1582w.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.j((d0) f10.f1469H.d, f10.n(), i7);
    }

    @Override // C0.J
    public final int a0(int i7) {
        C1489n1 c1489n1F = this.f1582w.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.i((d0) f10.f1469H.d, f10.n(), i7);
    }

    @Override // C0.J
    public final C0.V b(long j3) {
        o0(j3);
        d0 d0Var = this.f1582w;
        V.d dVarL = d0Var.f1648w.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                K kY = ((F) objArr[i10]).y();
                A7.m.c(kY);
                kY.f1518t = 3;
                i10++;
            } while (i10 < i7);
        }
        F f10 = d0Var.f1648w;
        S.E0(this, f10.f1463A.h(this, f10.n(), j3));
        return this;
    }

    @Override // C0.J
    public final int c(int i7) {
        C1489n1 c1489n1F = this.f1582w.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.d((d0) f10.f1469H.d, f10.n(), i7);
    }

    @Override // E0.Q
    public final int q0(C0107n c0107n) {
        K k10 = this.f1582w.f1648w.t().f1569s;
        A7.m.c(k10);
        boolean z6 = k10.f1519u;
        G g7 = k10.f1507C;
        if (!z6) {
            N n10 = k10.f1514J;
            if (n10.c == 2) {
                g7.f1497f = true;
                if (g7.f1495b) {
                    n10.f1560h = true;
                    n10.f1561i = true;
                }
            } else {
                g7.f1498g = true;
            }
        }
        C0169t c0169t = k10.n().f1704W;
        if (c0169t != null) {
            c0169t.f1577s = true;
        }
        k10.u();
        C0169t c0169t2 = k10.n().f1704W;
        if (c0169t2 != null) {
            c0169t2.f1577s = false;
        }
        Integer num = (Integer) g7.f1500i.get(c0107n);
        int iIntValue = num != null ? num.intValue() : Integer.MIN_VALUE;
        this.B.put(c0107n, Integer.valueOf(iIntValue));
        return iIntValue;
    }
}
