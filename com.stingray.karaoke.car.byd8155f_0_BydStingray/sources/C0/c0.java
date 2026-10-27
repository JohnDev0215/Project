package C0;

import A.I0;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0745i;
import T.C0753m;
import T.C0754m0;
import T.C0757o;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final O f891a = new O(5);

    public static final void a(g0.o oVar, b0.b bVar, K k10, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1949933075);
        int i11 = 2;
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(k10) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int i12 = c0761q.f8304P;
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            C0159i c0159i = C0159i.f1675p;
            int i13 = ((i10 << 3) & 896) | 6;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            InterfaceC0161k.f1681b.getClass();
            C0735d.V(c0761q, k10, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            if (c0761q.f8303O) {
                c0761q.b(m7.q.f19267a, new C0745i(i11, 2));
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            A7.k.o((i13 >> 6) & 14, bVar, c0761q, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(i7, 0, oVar, bVar, k10);
        }
    }

    public static final long b(float f10, float f11) {
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
        int i7 = a0.f883b;
        return jFloatToRawIntBits;
    }

    public static final void c(f0 f0Var, g0.o oVar, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-511989831);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(f0Var) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int i11 = c0761q.f8304P;
            C0757o c0757oQ = C0735d.Q(c0761q);
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            C0159i c0159i = C0159i.f1675p;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, f0Var, f0Var.c);
            C0735d.V(c0761q, c0757oQ, f0Var.d);
            C0735d.V(c0761q, interfaceC2785n, f0Var.f899e);
            InterfaceC0161k.f1681b.getClass();
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0735d.V(c0761q, oVarD, C0160j.c);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            c0761q.p(true);
            if (c0761q.x()) {
                c0761q.Q(-26502501);
                c0761q.p(false);
            } else {
                c0761q.Q(-26580342);
                boolean zH = c0761q.h(f0Var);
                Object objG = c0761q.G();
                if (zH || objG == C0753m.f8270a) {
                    objG = new I0(f0Var, 3);
                    c0761q.a0(objG);
                }
                C0735d.i((InterfaceC2772a) objG, c0761q);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(f0Var, oVar, interfaceC2785n, i7, 1, false);
        }
    }

    public static final void d(g0.o oVar, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7, int i10) {
        int i11;
        c0761q.S(-1298353104);
        int i12 = i10 & 1;
        if (i12 != 0) {
            i11 = i7 | 6;
        } else if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            if (i12 != 0) {
                oVar = g0.l.f14684a;
            }
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = new f0(O.f865m);
                c0761q.a0(objG);
            }
            c((f0) objG, oVar, interfaceC2785n, c0761q, (i11 << 3) & 1008);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0(oVar, interfaceC2785n, i7, i10);
        }
    }

    public static final m0.d e(r rVar) {
        r rVarJ = rVar.j();
        return rVarJ != null ? rVarJ.o(rVar, true) : new m0.d(0.0f, 0.0f, (int) (rVar.L() >> 32), (int) (rVar.L() & 4294967295L));
    }

    public static final m0.d f(r rVar) {
        r rVarH = h(rVar);
        float fL = (int) (rVarH.L() >> 32);
        float fL2 = (int) (rVarH.L() & 4294967295L);
        m0.d dVarO = h(rVar).o(rVar, true);
        float f10 = dVarO.f18882a;
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        if (f10 > fL) {
            f10 = fL;
        }
        float f11 = dVarO.f18883b;
        if (f11 < 0.0f) {
            f11 = 0.0f;
        }
        if (f11 > fL2) {
            f11 = fL2;
        }
        float f12 = dVarO.c;
        if (f12 < 0.0f) {
            f12 = 0.0f;
        }
        if (f12 <= fL) {
            fL = f12;
        }
        float f13 = dVarO.d;
        float f14 = f13 >= 0.0f ? f13 : 0.0f;
        if (f14 <= fL2) {
            fL2 = f14;
        }
        if (f10 == fL || f11 == fL2) {
            return m0.d.f18881e;
        }
        long jG = rVarH.g(h6.e.a(f10, f11));
        long jG2 = rVarH.g(h6.e.a(fL, f11));
        long jG3 = rVarH.g(h6.e.a(fL, fL2));
        long jG4 = rVarH.g(h6.e.a(f10, fL2));
        float fD = m0.c.d(jG);
        float fD2 = m0.c.d(jG2);
        float fD3 = m0.c.d(jG4);
        float fD4 = m0.c.d(jG3);
        float fMin = Math.min(fD, Math.min(fD2, Math.min(fD3, fD4)));
        float fMax = Math.max(fD, Math.max(fD2, Math.max(fD3, fD4)));
        float fE = m0.c.e(jG);
        float fE2 = m0.c.e(jG2);
        float fE3 = m0.c.e(jG4);
        float fE4 = m0.c.e(jG3);
        return new m0.d(fMin, Math.min(fE, Math.min(fE2, Math.min(fE3, fE4))), fMax, Math.max(fE, Math.max(fE2, Math.max(fE3, fE4))));
    }

    public static final boolean g(int i7, int i10) {
        return i7 == i10;
    }

    public static final r h(r rVar) {
        r rVar2;
        r rVarJ = rVar.j();
        while (true) {
            r rVar3 = rVarJ;
            rVar2 = rVar;
            rVar = rVar3;
            if (rVar == null) {
                break;
            }
            rVarJ = rVar.j();
        }
        E0.d0 d0Var = rVar2 instanceof E0.d0 ? (E0.d0) rVar2 : null;
        if (d0Var == null) {
            return rVar2;
        }
        E0.d0 d0Var2 = d0Var.f1650y;
        while (true) {
            E0.d0 d0Var3 = d0Var2;
            E0.d0 d0Var4 = d0Var;
            d0Var = d0Var3;
            if (d0Var == null) {
                return d0Var4;
            }
            d0Var2 = d0Var.f1650y;
        }
    }

    public static final E0.S i(E0.S s6) {
        E0.F f10 = s6.f1582w.f1648w;
        while (true) {
            E0.F fG = f10.G();
            E0.F f11 = null;
            if ((fG != null ? fG.f1482n : null) == null) {
                E0.S sO0 = ((E0.d0) f10.f1469H.d).O0();
                A7.m.c(sO0);
                return sO0;
            }
            E0.F fG2 = f10.G();
            if (fG2 != null) {
                f11 = fG2.f1482n;
            }
            A7.m.c(f11);
            E0.F fG3 = f10.G();
            A7.m.c(fG3);
            f10 = fG3.f1482n;
            A7.m.c(f10);
        }
    }

    public static final long j(long j3, long j10) {
        return h6.j.f(a0.a(j10) * m0.f.d(j3), a0.b(j10) * m0.f.b(j3));
    }
}
