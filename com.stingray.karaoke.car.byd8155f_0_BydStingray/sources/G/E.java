package G;

import A.C0006c;
import A.EnumC0017h0;
import A.I0;
import D.C0130l;
import T.AbstractC0750k0;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import d0.InterfaceC1054c;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0314y[] f2499a = new C0314y[0];

    public static final void a(H7.r rVar, g0.o oVar, V v10, InterfaceC2785n interfaceC2785n, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(2002163445);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(rVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(v10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            c(b0.g.b(-1488997347, new androidx.compose.foundation.lazy.layout.b(v10, oVar, interfaceC2785n, C0735d.R(rVar, c0761q)), c0761q), c0761q, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L(rVar, oVar, v10, interfaceC2785n, i7, 0);
        }
    }

    public static final void b(Object obj, int i7, S s6, b0.b bVar, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(-2079116560);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.h(obj) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.h(s6) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.h(bVar) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zF = c0761q.f(obj) | c0761q.f(s6);
            Object objG = c0761q.G();
            Object obj2 = C0753m.f8270a;
            if (zF || objG == obj2) {
                objG = new Q(obj, s6);
                c0761q.a0(objG);
            }
            Q q4 = (Q) objG;
            C0732b0 c0732b0 = q4.c;
            C0738e0 c0738e0 = q4.f2521e;
            C0738e0 c0738e02 = q4.f2522f;
            c0732b0.j(i7);
            AbstractC0750k0 abstractC0750k0 = C0.T.f869a;
            Q q10 = (Q) c0761q.k(abstractC0750k0);
            AbstractC1167g abstractC1167gC = AbstractC1179s.c();
            InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
            AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
            try {
                if (q10 != ((Q) c0738e02.getValue())) {
                    c0738e02.setValue(q10);
                    if (q4.d.i() > 0) {
                        Q q11 = (Q) c0738e0.getValue();
                        if (q11 != null) {
                            q11.b();
                        }
                        if (q10 != null) {
                            q10.a();
                        } else {
                            q10 = null;
                        }
                        c0738e0.setValue(q10);
                    }
                }
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                boolean zF2 = c0761q.f(q4);
                Object objG2 = c0761q.G();
                if (zF2 || objG2 == obj2) {
                    objG2 = new A.F(q4, 18);
                    c0761q.a0(objG2);
                }
                C0735d.d(q4, (InterfaceC2782k) objG2, c0761q);
                C0735d.a(abstractC0750k0.a(q4), bVar, c0761q, ((i11 >> 6) & 112) | 8);
            } catch (Throwable th) {
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                throw th;
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H(obj, i7, s6, bVar, i10);
        }
    }

    public static final void c(b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        int i11 = 4;
        c0761q.S(674185128);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(bVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0750k0 abstractC0750k0 = d0.m.f13274a;
            d0.k kVar = (d0.k) c0761q.k(abstractC0750k0);
            Object[] objArr = {kVar};
            d0 d0Var = d0.f2553m;
            c0 c0Var = new c0(kVar, 1);
            io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
            io.sentry.internal.debugmeta.c cVar2 = new io.sentry.internal.debugmeta.c(d0Var, 15, c0Var);
            boolean zH = c0761q.h(kVar);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new I0(kVar, 19);
                c0761q.a0(objG);
            }
            Object obj = (e0) w0.c.Z(objArr, cVar2, (InterfaceC2772a) objG, c0761q, 0, 4);
            C0735d.a(abstractC0750k0.a(obj), b0.g.b(1863926504, new A.L(obj, 7, bVar), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, i11, bVar);
        }
    }

    public static final void d(I i7, Object obj, int i10, Object obj2, C0761q c0761q, int i11) {
        int i12;
        c0761q.S(1439843069);
        if ((i11 & 6) == 0) {
            i12 = (c0761q.f(i7) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= c0761q.f(obj) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i12 |= c0761q.d(i10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= c0761q.f(obj2) ? 2048 : 1024;
        }
        if ((i12 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            ((InterfaceC1054c) obj).e(obj2, b0.g.b(980966366, new F0.W(i10, i7, obj2), c0761q), c0761q, 48);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H(i7, obj, i10, obj2, i11);
        }
    }

    public static final int e(int i7, V.d dVar) {
        int i10 = dVar.f9279n - 1;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = ((i10 - i11) / 2) + i11;
            Object[] objArr = dVar.l;
            int i13 = ((C0297g) objArr[i12]).f2560a;
            if (i13 != i7) {
                if (i13 < i7) {
                    i11 = i12 + 1;
                    if (i7 < ((C0297g) objArr[i11]).f2560a) {
                    }
                } else {
                    i10 = i12 - 1;
                }
            }
            return i12;
        }
        return i11;
    }

    public static final List f(I i7, S s6, C0006c c0006c) {
        G7.d dVar;
        if (!c0006c.f158a.m() && s6.l.isEmpty()) {
            return n7.u.l;
        }
        ArrayList arrayList = new ArrayList();
        V.d dVar2 = c0006c.f158a;
        if (!dVar2.m()) {
            dVar = G7.d.o;
        } else {
            if (dVar2.l()) {
                throw new NoSuchElementException("MutableVector is empty.");
            }
            Object[] objArr = dVar2.l;
            int i10 = ((C0303m) objArr[0]).f2601a;
            int i11 = dVar2.f9279n;
            if (i11 > 0) {
                int i12 = 0;
                do {
                    int i13 = ((C0303m) objArr[i12]).f2601a;
                    if (i13 < i10) {
                        i10 = i13;
                    }
                    i12++;
                } while (i12 < i11);
            }
            if (i10 < 0) {
                throw new IllegalArgumentException("negative minIndex");
            }
            if (dVar2.l()) {
                throw new NoSuchElementException("MutableVector is empty.");
            }
            Object[] objArr2 = dVar2.l;
            int i14 = ((C0303m) objArr2[0]).f2602b;
            int i15 = dVar2.f9279n;
            if (i15 > 0) {
                int i16 = 0;
                do {
                    int i17 = ((C0303m) objArr2[i16]).f2602b;
                    if (i17 > i14) {
                        i14 = i17;
                    }
                    i16++;
                } while (i16 < i15);
            }
            dVar = new G7.d(i10, Math.min(i14, i7.b() - 1), 1);
        }
        int size = s6.l.size();
        for (int i18 = 0; i18 < size; i18++) {
            Q q4 = (Q) s6.get(i18);
            int iH = h(q4.c.i(), i7, q4.f2519a);
            int i19 = dVar.l;
            if ((iH > dVar.f2712m || i19 > iH) && iH >= 0 && iH < i7.b()) {
                arrayList.add(Integer.valueOf(iH));
            }
        }
        int i20 = dVar.l;
        int i21 = dVar.f2712m;
        if (i20 <= i21) {
            while (true) {
                arrayList.add(Integer.valueOf(i20));
                if (i20 == i21) {
                    break;
                }
                i20++;
            }
        }
        return arrayList;
    }

    public static T.X g() {
        return C0735d.O(m7.q.f19267a, T.S.f8222n);
    }

    public static final int h(int i7, I i10, Object obj) {
        int iA;
        return (obj == null || i10.b() == 0 || (i7 < i10.b() && obj.equals(i10.c(i7))) || (iA = i10.a(obj)) == -1) ? i7 : iA;
    }

    public static final g0.o l(g0.o oVar, InterfaceC0307q interfaceC0307q, C0006c c0006c, boolean z6, a1.k kVar, EnumC0017h0 enumC0017h0, boolean z10, C0761q c0761q, int i7) {
        if (!z10) {
            c0761q.Q(-1890658823);
            c0761q.p(false);
            return oVar;
        }
        c0761q.Q(-1890632411);
        boolean z11 = true;
        boolean z12 = ((((i7 & 112) ^ 48) > 32 && c0761q.f(interfaceC0307q)) || (i7 & 48) == 32) | ((((i7 & 896) ^ 384) > 256 && c0761q.f(c0006c)) || (i7 & 384) == 256) | ((((i7 & 7168) ^ 3072) > 2048 && c0761q.g(z6)) || (i7 & 3072) == 2048) | ((((57344 & i7) ^ 24576) > 16384 && c0761q.f(kVar)) || (i7 & 24576) == 16384);
        if ((((458752 & i7) ^ 196608) <= 131072 || !c0761q.f(enumC0017h0)) && (i7 & 196608) != 131072) {
            z11 = false;
        }
        boolean z13 = z12 | z11;
        Object objG = c0761q.G();
        if (z13 || objG == C0753m.f8270a) {
            objG = new C0306p(interfaceC0307q, c0006c, z6, kVar, enumC0017h0);
            c0761q.a0(objG);
        }
        g0.o oVarK = oVar.k((C0306p) objG);
        c0761q.p(false);
        return oVarK;
    }

    public Object i(int i7) {
        C0297g c0297gM = j().m(i7);
        return c0297gM.c.b().b(Integer.valueOf(i7 - c0297gM.f2560a));
    }

    public abstract B5.u j();

    public Object k(int i7) {
        Object objB;
        C0297g c0297gM = j().m(i7);
        int i10 = i7 - c0297gM.f2560a;
        InterfaceC2782k key = c0297gM.c.getKey();
        return (key == null || (objB = key.b(Integer.valueOf(i10))) == null) ? new C0295e(i7) : objB;
    }
}
