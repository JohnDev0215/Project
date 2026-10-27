package Q;

import D.AbstractC0120b;
import F0.AbstractC0227s0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import androidx.compose.foundation.layout.FillElement;
import e0.C1178r;
import n0.AbstractC1861K;
import n0.C1880i;
import n0.C1891t;
import n0.InterfaceC1859I;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0497b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0497b0 f5820a = new C0497b0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5821b;
    public static final float c;
    public static final C1880i d;

    static {
        float f10 = S.m.f7705e;
        f5821b = f10;
        c = f10;
        d = AbstractC1861K.i();
    }

    public static U c(C0761q c0761q) {
        return e((C0516l) c0761q.k(AbstractC0520n.f5997a));
    }

    public static void d(p0.d dVar, long j3, long j10, long j11, float f10, float f11) {
        long jA = n7.z.a(f10, f10);
        long jA2 = n7.z.a(f11, f11);
        m0.d dVarA = h6.f.a(h6.e.a(m0.c.d(j3), 0.0f), h6.j.f(m0.f.d(j10), m0.f.b(j10)));
        m0.e eVar = new m0.e(dVarA.f18882a, dVarA.f18883b, dVarA.c, dVarA.d, jA, jA2, jA2, jA);
        C1880i c1880i = d;
        InterfaceC1859I.a(c1880i, eVar);
        p0.d.U(dVar, c1880i, j11, 0.0f, 60);
        c1880i.f19436a.rewind();
    }

    public static U e(C0516l c0516l) {
        U u4 = c0516l.f5956N;
        if (u4 != null) {
            return u4;
        }
        float f10 = S.m.f7703a;
        U u10 = new U(AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, 32), AbstractC0520n.b(c0516l, 32), AbstractC0520n.b(c0516l, 26), AbstractC1861K.n(C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), c0516l.f5969p), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), C1891t.b(0.12f, AbstractC0520n.b(c0516l, 18)), C1891t.b(0.12f, AbstractC0520n.b(c0516l, 18)), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)));
        c0516l.f5956N = u10;
        return u10;
    }

    public final void a(C.k kVar, g0.o oVar, U u4, boolean z6, long j3, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar2;
        long j10;
        g0.o oVar3;
        long j11;
        c0761q.S(-290277409);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(kVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        int i11 = i10 | 48;
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(u4) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.g(z6) ? 2048 : 1024;
        }
        int i12 = i11 | 24576;
        if ((196608 & i7) == 0) {
            i12 |= c0761q.f(this) ? 131072 : 65536;
        }
        if ((74899 & i12) == 74898 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar;
            j11 = j3;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                oVar2 = g0.l.f14684a;
                j10 = AbstractC0517l0.c;
            } else {
                c0761q.K();
                oVar2 = oVar;
                j10 = j3;
            }
            c0761q.q();
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new C1178r();
                c0761q.a0(objG);
            }
            C1178r c1178r = (C1178r) objG;
            boolean z10 = (i12 & 14) == 4;
            Object objG2 = c0761q.G();
            if (z10 || objG2 == s6) {
                objG2 = new W(kVar, c1178r, null);
                c0761q.a0(objG2);
            }
            C0735d.f(c0761q, kVar, (InterfaceC2785n) objG2);
            long jFloatToRawIntBits = !c1178r.isEmpty() ? (((long) Float.floatToRawIntBits(a1.g.b(j10) / 2)) << 32) | (((long) Float.floatToRawIntBits(a1.g.a(j10))) & 4294967295L) : j10;
            FillElement fillElement = androidx.compose.foundation.layout.c.f11475a;
            g0.o oVarG = androidx.compose.foundation.a.g(androidx.compose.foundation.layout.c.m(oVar2, a1.g.b(jFloatToRawIntBits), a1.g.a(jFloatToRawIntBits)), kVar);
            long j12 = z6 ? u4.f5680a : u4.f5683f;
            float f10 = S.m.f7703a;
            AbstractC0120b.b(c0761q, androidx.compose.foundation.a.b(oVarG, j12, T.a(5, c0761q)));
            oVar3 = oVar2;
            j11 = j10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new X(this, kVar, oVar3, u4, z6, j11, i7);
        }
    }

    public final void b(C0523o0 c0523o0, g0.o oVar, boolean z6, U u4, InterfaceC2785n interfaceC2785n, InterfaceC2786o interfaceC2786o, float f10, float f11, C0761q c0761q, int i7) {
        int i10;
        int i11;
        g0.o oVar2;
        InterfaceC2786o interfaceC2786o2;
        float f12;
        float f13;
        InterfaceC2785n interfaceC2785n2;
        InterfaceC2785n interfaceC2785n3;
        long j3;
        long j10;
        long j11;
        InterfaceC2785n interfaceC2785n4;
        g0.o oVar3;
        g0.o oVar4;
        C0761q c0761q2;
        InterfaceC2786o interfaceC2786o3;
        float f14;
        float f15;
        g0.o oVar5;
        InterfaceC2785n interfaceC2785n5;
        c0761q.S(49984771);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c0523o0) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        int i12 = i10 | 48;
        if ((i7 & 384) == 0) {
            i12 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i12 |= c0761q.f(u4) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i12 |= 8192;
        }
        int i13 = i12 | 14352384;
        if ((100663296 & i7) == 0) {
            i13 |= c0761q.f(this) ? 67108864 : 33554432;
        }
        if ((38347923 & i13) == 38347922 && c0761q.x()) {
            c0761q.K();
            oVar5 = oVar;
            interfaceC2785n5 = interfaceC2785n;
            interfaceC2786o3 = interfaceC2786o;
            f14 = f10;
            f15 = f11;
        } else {
            c0761q.M();
            int i14 = i7 & 1;
            T.S s6 = C0753m.f8270a;
            if (i14 == 0 || c0761q.w()) {
                g0.l lVar = g0.l.f14684a;
                boolean z10 = ((i13 & 896) == 256) | ((((i13 & 7168) ^ 3072) > 2048 && c0761q.f(u4)) || (i13 & 3072) == 2048);
                Object objG = c0761q.G();
                if (z10 || objG == s6) {
                    objG = new Y(u4, z6);
                    c0761q.a0(objG);
                }
                InterfaceC2785n interfaceC2785n6 = (InterfaceC2785n) objG;
                i11 = i13 & (-57345);
                oVar2 = lVar;
                interfaceC2786o2 = I.o;
                f12 = AbstractC0517l0.d;
                f13 = AbstractC0517l0.f5982e;
                interfaceC2785n2 = interfaceC2785n6;
            } else {
                c0761q.K();
                i11 = i13 & (-57345);
                oVar2 = oVar;
                interfaceC2785n2 = interfaceC2785n;
                interfaceC2786o2 = interfaceC2786o;
                f12 = f10;
                f13 = f11;
            }
            c0761q.q();
            long jA = u4.a(z6, false);
            g0.o oVar6 = oVar2;
            long jA2 = u4.a(z6, true);
            if (z6) {
                interfaceC2785n3 = interfaceC2785n2;
                j3 = u4.f5682e;
            } else {
                interfaceC2785n3 = interfaceC2785n2;
                j3 = u4.f5687j;
            }
            if (z6) {
                j10 = jA;
                j11 = u4.c;
            } else {
                j10 = jA;
                j11 = u4.f5685h;
            }
            long j12 = j11;
            g0.o oVarE = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.d(oVar6, 1.0f), AbstractC0517l0.f5980a);
            float f16 = c0761q.k(AbstractC0227s0.l) == a1.k.f11038m ? 180.0f : 0.0f;
            if (f16 != 0.0f) {
                oVarE = androidx.compose.ui.graphics.a.b(oVarE, 0.0f, 0.0f, 0.0f, 0.0f, f16, 0L, null, false, 130815);
            }
            g0.o oVar7 = oVarE;
            long j13 = j10;
            boolean zH = c0761q.h(c0523o0) | c0761q.e(j13) | c0761q.e(jA2) | c0761q.e(j3) | c0761q.e(j12) | ((i11 & 3670016) == 1048576) | ((29360128 & i11) == 8388608);
            InterfaceC2785n interfaceC2785n7 = interfaceC2785n3;
            boolean zF = ((i11 & 458752) == 131072) | zH | c0761q.f(interfaceC2785n7);
            Object objG2 = c0761q.G();
            if (zF || objG2 == s6) {
                interfaceC2785n4 = interfaceC2785n7;
                oVar3 = oVar6;
                oVar4 = oVar7;
                c0761q2 = c0761q;
                Z z11 = new Z(c0523o0, j13, jA2, j3, j12, f12, f13, interfaceC2785n4, interfaceC2786o2);
                c0761q2.a0(z11);
                objG2 = z11;
            } else {
                oVar3 = oVar6;
                interfaceC2785n4 = interfaceC2785n7;
                oVar4 = oVar7;
                c0761q2 = c0761q;
            }
            h6.j.b(0, c0761q2, oVar4, (InterfaceC2782k) objG2);
            interfaceC2786o3 = interfaceC2786o2;
            f14 = f12;
            f15 = f13;
            oVar5 = oVar3;
            interfaceC2785n5 = interfaceC2785n4;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0495a0(this, c0523o0, oVar5, z6, u4, interfaceC2785n5, interfaceC2786o3, f14, f15, i7);
        }
    }
}
