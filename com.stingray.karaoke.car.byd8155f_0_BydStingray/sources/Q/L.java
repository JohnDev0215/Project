package Q;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import T.C0735d;
import T.C0752l0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import androidx.compose.material3.MinimumInteractiveModifier;
import e0.C1178r;
import h2.AbstractC1347d;
import n0.AbstractC1861K;
import n0.C1891t;
import n0.InterfaceC1866P;
import n7.AbstractC1998m;
import x.C2629d;
import x.C2648n;
import y.C2716v;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final P.h f5572a = new P.h(0.16f, 0.1f, 0.08f, 0.1f);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8, types: [boolean, int] */
    public static final void a(InterfaceC2772a interfaceC2772a, g0.o oVar, boolean z6, InterfaceC1866P interfaceC1866P, C0498c c0498c, C0508h c0508h, C2716v c2716v, D.L l, C.k kVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        InterfaceC1866P interfaceC1866PA;
        D.L l10;
        C0498c c0498c2;
        boolean z10;
        C2716v c2716v2;
        C.k kVar2;
        int i11;
        C0508h c0508h2;
        C.k kVar3;
        C0498c c0498c3;
        C2716v c2716v3;
        long j3;
        C2648n c2648n;
        ?? r22;
        boolean z11;
        C0508h c0508h3;
        C.k kVar4;
        D.L l11;
        C0498c c0498c4;
        c0761q.S(650121315);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2772a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        int i12 = i10 | 384;
        if ((i7 & 3072) == 0) {
            i12 = i10 | 1408;
        }
        if ((i7 & 24576) == 0) {
            i12 |= 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= 65536;
        }
        int i13 = 114819072 | i12;
        if ((805306368 & i7) == 0) {
            i13 |= c0761q.h(bVar) ? 536870912 : 268435456;
        }
        if ((306783379 & i13) == 306783378 && c0761q.x()) {
            c0761q.K();
            z11 = z6;
            interfaceC1866PA = interfaceC1866P;
            c0498c4 = c0498c;
            c0508h3 = c0508h;
            c2716v3 = c2716v;
            l11 = l;
            kVar4 = kVar;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                D.L l12 = AbstractC0500d.f5851a;
                interfaceC1866PA = T.a(S.e.f7634b, c0761q);
                C0516l c0516l = (C0516l) c0761q.k(AbstractC0520n.f5997a);
                C0498c c0498c5 = c0516l.f5953K;
                if (c0498c5 == null) {
                    c0498c5 = new C0498c(AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, S.e.f7638h), C1891t.b(0.12f, AbstractC0520n.b(c0516l, S.e.c)), C1891t.b(0.38f, AbstractC0520n.b(c0516l, S.e.f7635e)));
                    c0516l.f5953K = c0498c5;
                }
                C0508h c0508h4 = new C0508h(S.e.f7633a, S.e.f7639i, S.e.f7636f, S.e.f7637g, S.e.d);
                l10 = AbstractC0500d.f5851a;
                c0498c2 = c0498c5;
                z10 = true;
                c2716v2 = null;
                kVar2 = null;
                i11 = i13 & (-523265);
                c0508h2 = c0508h4;
            } else {
                c0761q.K();
                z10 = z6;
                interfaceC1866PA = interfaceC1866P;
                c0498c2 = c0498c;
                c2716v2 = c2716v;
                l10 = l;
                kVar2 = kVar;
                i11 = i13 & (-523265);
                c0508h2 = c0508h;
            }
            c0761q.q();
            c0761q.Q(-239156623);
            Object obj = C0753m.f8270a;
            if (kVar2 == null) {
                Object objG = c0761q.G();
                if (objG == obj) {
                    objG = new C.k();
                    c0761q.a0(objG);
                }
                kVar3 = (C.k) objG;
            } else {
                kVar3 = kVar2;
            }
            c0761q.p(false);
            D.L l13 = l10;
            long j10 = z10 ? c0498c2.f5841a : c0498c2.c;
            C.k kVar5 = kVar3;
            long j11 = z10 ? c0498c2.f5842b : c0498c2.d;
            c0761q.Q(-239150048);
            if (c0508h2 == null) {
                c0498c3 = c0498c2;
                c2716v3 = c2716v2;
                j3 = j10;
                r22 = 0;
                c2648n = null;
            } else {
                int i14 = i11 >> 6;
                Object objG2 = c0761q.G();
                if (objG2 == obj) {
                    objG2 = new C1178r();
                    c0761q.a0(objG2);
                }
                C1178r c1178r = (C1178r) objG2;
                c0498c3 = c0498c2;
                boolean zF = c0761q.f(kVar5);
                Object objG3 = c0761q.G();
                if (zF || objG3 == obj) {
                    objG3 = new C0504f(kVar5, c1178r, null);
                    c0761q.a0(objG3);
                }
                C0735d.f(c0761q, kVar5, (InterfaceC2785n) objG3);
                C.j jVar = (C.j) AbstractC1998m.i0(c1178r);
                float f10 = !z10 ? c0508h2.f5902e : jVar instanceof C.m ? c0508h2.f5901b : jVar instanceof C.h ? c0508h2.d : jVar instanceof C.d ? c0508h2.c : c0508h2.f5900a;
                Object objG4 = c0761q.G();
                if (objG4 == obj) {
                    kVar5 = kVar5;
                    c2716v3 = c2716v2;
                    j3 = j10;
                    objG4 = new C2629d(new a1.e(f10), x.E0.c, null, 12);
                    c0761q.a0(objG4);
                } else {
                    kVar5 = kVar5;
                    c2716v3 = c2716v2;
                    j3 = j10;
                }
                C2629d c2629d = (C2629d) objG4;
                a1.e eVar = new a1.e(f10);
                boolean zH = c0761q.h(c2629d) | c0761q.c(f10) | (((i14 & 14) ^ 6) > 4 && c0761q.g(z10)) | c0761q.f(c0508h2) | c0761q.h(jVar);
                Object objG5 = c0761q.G();
                if (zH || objG5 == obj) {
                    objG5 = new C0506g(c2629d, f10, z10, c0508h2, jVar, null);
                    c0761q.a0(objG5);
                }
                C0735d.f(c0761q, eVar, (InterfaceC2785n) objG5);
                c2648n = c2629d.c;
                r22 = 0;
            }
            c0761q.p(r22);
            float f11 = c2648n != null ? ((a1.e) c2648n.f23569m.getValue()).l : (float) r22;
            g0.o oVarA = L0.k.a(oVar, r22, C0510i.f5921n);
            b0.b bVarB = b0.g.b(956488494, new C0512j(j11, l13, bVar, 0), c0761q);
            C0773y c0773y = AbstractC0530s0.f6039a;
            float f12 = 0 + ((a1.e) c0761q.k(c0773y)).l;
            C0735d.b(new C0752l0[]{AbstractC0524p.f6016a.a(new C1891t(j11)), c0773y.a(new a1.e(f12))}, b0.g.b(1279702876, new C0528r0(oVarA, interfaceC1866PA, j3, f12, c2716v3, kVar5, z10, interfaceC2772a, f11, bVarB), c0761q), c0761q, 56);
            z11 = z10;
            c0508h3 = c0508h2;
            kVar4 = kVar2;
            l11 = l13;
            c0498c4 = c0498c3;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0514k(interfaceC2772a, oVar, z11, interfaceC1866PA, c0498c4, c0508h3, c2716v3, l11, kVar4, bVar, i7);
        }
    }

    public static final void b(g0.o oVar, float f10, long j3, C0761q c0761q, int i7, int i10) {
        long jC;
        g0.o oVar2;
        float f11;
        float f12;
        long j10;
        c0761q.S(1562471785);
        int i11 = i7 | 54;
        if ((i7 & 384) == 0) {
            if ((i10 & 4) == 0) {
                jC = j3;
                int i12 = c0761q.e(j3) ? 256 : 128;
                i11 |= i12;
            } else {
                jC = j3;
            }
            i11 |= i12;
        } else {
            jC = j3;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
            j10 = jC;
            f12 = f10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                oVar2 = g0.l.f14684a;
                f11 = AbstractC0526q.f6017a;
                if ((i10 & 4) != 0) {
                    float f13 = S.c.f7630a;
                    jC = AbstractC0520n.c(25, c0761q);
                }
            } else {
                c0761q.K();
                oVar2 = oVar;
                f11 = f10;
            }
            c0761q.q();
            c0761q.Q(-433645095);
            float density = a1.e.a(f11, 0.0f) ? 1.0f / ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).getDensity() : f11;
            c0761q.p(false);
            AbstractC0133o.a(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.d(oVar2, 1.0f), density), jC, AbstractC1861K.f19381a), c0761q, 0);
            long j11 = jC;
            f12 = f11;
            j10 = j11;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new r(oVar2, f12, j10, i7, i10);
        }
    }

    public static final void c(InterfaceC2772a interfaceC2772a, g0.o oVar, boolean z6, C0529s c0529s, C.k kVar, C0761q c0761q, int i7) {
        int i10;
        C.k kVar2;
        int i11;
        C0529s c0529s2;
        boolean z10;
        boolean z11;
        C.k kVar3;
        b0.b bVar = Q5.I.f6386a;
        c0761q.S(-1142896114);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2772a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        int i12 = i10 | 384;
        if ((i7 & 3072) == 0) {
            i12 = i10 | 1408;
        }
        int i13 = i12 | 24576;
        if ((196608 & i7) == 0) {
            i13 |= c0761q.h(bVar) ? 131072 : 65536;
        }
        if ((74899 & i13) == 74898 && c0761q.x()) {
            c0761q.K();
            z11 = z6;
            c0529s2 = c0529s;
            kVar3 = kVar;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                c0761q.Q(-1519621781);
                long j3 = ((C1891t) c0761q.k(AbstractC0524p.f6016a)).f19453a;
                C0516l c0516l = (C0516l) c0761q.k(AbstractC0520n.f5997a);
                C0529s c0529s3 = c0516l.f5954L;
                if (c0529s3 == null) {
                    long j10 = C1891t.f19451j;
                    c0529s3 = new C0529s(j10, j3, j10, C1891t.b(0.38f, j3));
                    c0516l.f5954L = c0529s3;
                }
                long j11 = c0529s3.f6038b;
                if (C1891t.c(j11, j3)) {
                    c0761q.p(false);
                } else {
                    long jB = C1891t.b(0.38f, j3);
                    long j12 = j3 != 16 ? j3 : j11;
                    if (jB == 16) {
                        jB = c0529s3.d;
                    }
                    C0529s c0529s4 = new C0529s(c0529s3.f6037a, j12, c0529s3.c, jB);
                    c0761q.p(false);
                    c0529s3 = c0529s4;
                }
                kVar2 = null;
                i11 = i13 & (-7169);
                c0529s2 = c0529s3;
                z10 = true;
            } else {
                c0761q.K();
                z10 = z6;
                c0529s2 = c0529s;
                kVar2 = kVar;
                i11 = i13 & (-7169);
            }
            c0761q.q();
            T.R0 r02 = AbstractC0537w.f6062a;
            g0.o oVarK = oVar.k(MinimumInteractiveModifier.f11534a);
            float f10 = S.f.f7641b;
            g0.o oVarD = androidx.compose.foundation.a.d(androidx.compose.foundation.a.b(h6.q.m(androidx.compose.foundation.layout.c.l(oVarK, f10), T.a(5, c0761q)), z10 ? c0529s2.f6037a : c0529s2.c, AbstractC1861K.f19381a), kVar2, M.c(false, f10 / 2, c0761q, 54, 4), z10, new L0.g(0), interfaceC2772a, 8);
            C0.K kE = AbstractC0133o.e(g0.b.f14664p, false);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarD);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a2 = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a2);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            C0735d.a(AbstractC0524p.f6016a.a(new C1891t(z10 ? c0529s2.f6038b : c0529s2.d)), bVar, c0761q, ((i11 >> 12) & 112) | 8);
            c0761q.p(true);
            z11 = z10;
            kVar3 = kVar2;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0531t(interfaceC2772a, oVar, z11, c0529s2, kVar3, i7);
        }
    }
}
