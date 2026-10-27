package Q;

import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import K.C0335d0;
import N1.C0435p;
import O.C0477h;
import R.AbstractC0675c;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import androidx.compose.material3.MinimumInteractiveModifier;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import h2.AbstractC1347d;
import q7.InterfaceC2189c;
import x3.AbstractC2673a;
import y0.C2730h;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0517l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5980a = S.m.d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5981b;
    public static final long c;
    public static final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f5982e;

    static {
        float f10 = S.m.c;
        f5981b = f10;
        c = l9.l.k(f10, S.m.f7704b);
        d = S.m.f7703a;
        f5982e = 2;
    }

    public static final void a(float f10, InterfaceC2782k interfaceC2782k, g0.o oVar, boolean z6, G7.a aVar, int i7, InterfaceC2772a interfaceC2772a, U u4, C.k kVar, C0761q c0761q, int i10) {
        int i11;
        C.k kVar2;
        boolean z10;
        int i12;
        InterfaceC2772a interfaceC2772a2;
        int i13;
        InterfaceC2772a interfaceC2772a3;
        C.k kVar3;
        boolean z11;
        c0761q.S(-202044027);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.c(f10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(oVar) ? 256 : 128;
        }
        int i14 = i11 | 3072;
        if ((i10 & 24576) == 0) {
            i14 |= c0761q.f(aVar) ? 16384 : 8192;
        }
        int i15 = i14 | 1769472;
        if ((12582912 & i10) == 0) {
            i15 |= c0761q.f(u4) ? 8388608 : 4194304;
        }
        int i16 = i15 | 100663296;
        if ((38347923 & i16) == 38347922 && c0761q.x()) {
            c0761q.K();
            z11 = z6;
            i13 = i7;
            interfaceC2772a3 = interfaceC2772a;
            kVar3 = kVar;
        } else {
            c0761q.M();
            if ((i10 & 1) == 0 || c0761q.w()) {
                Object objG = c0761q.G();
                if (objG == C0753m.f8270a) {
                    objG = new C.k();
                    c0761q.a0(objG);
                }
                kVar2 = (C.k) objG;
                z10 = true;
                i12 = 0;
                interfaceC2772a2 = null;
            } else {
                c0761q.K();
                z10 = z6;
                i12 = i7;
                interfaceC2772a2 = interfaceC2772a;
                kVar2 = kVar;
            }
            c0761q.q();
            int i17 = i16 >> 6;
            C.k kVar4 = kVar2;
            b(f10, interfaceC2782k, oVar, z10, interfaceC2772a2, u4, kVar2, i12, b0.g.b(308249025, new K.C0(kVar2, u4, z10), c0761q), b0.g.b(-1843234110, new C0477h(u4, z10), c0761q), aVar, c0761q, (i16 & 14) | 905969664 | (i16 & 112) | (i16 & 896) | (i16 & 7168) | (57344 & i17) | (458752 & i17) | (i17 & 3670016) | ((i16 << 6) & 29360128), (i16 >> 12) & 14, 0);
            i13 = i12;
            interfaceC2772a3 = interfaceC2772a2;
            kVar3 = kVar4;
            z11 = z10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0501d0(f10, interfaceC2782k, oVar, z11, aVar, i13, interfaceC2772a3, u4, kVar3, i10);
        }
    }

    public static final void b(float f10, InterfaceC2782k interfaceC2782k, g0.o oVar, boolean z6, InterfaceC2772a interfaceC2772a, U u4, C.k kVar, int i7, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, G7.a aVar, C0761q c0761q, int i10, int i11, int i12) {
        int i13;
        U u10;
        int i14;
        int i15;
        U uC;
        int i16;
        int i17;
        c0761q.S(1191170377);
        if ((i10 & 6) == 0) {
            i13 = (c0761q.c(f10) ? 4 : 2) | i10;
        } else {
            i13 = i10;
        }
        if ((i10 & 48) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i13 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i13 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= c0761q.h(interfaceC2772a) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            if ((i12 & 32) == 0) {
                u10 = u4;
                int i18 = c0761q.f(u10) ? 131072 : 65536;
                i13 |= i18;
            } else {
                u10 = u4;
            }
            i13 |= i18;
        } else {
            u10 = u4;
        }
        if ((i10 & 1572864) == 0) {
            i13 |= c0761q.f(kVar) ? 1048576 : 524288;
        }
        int i19 = 128 & i12;
        if (i19 != 0) {
            i13 |= 12582912;
            i14 = i7;
        } else {
            i14 = i7;
            if ((i10 & 12582912) == 0) {
                i13 |= c0761q.d(i14) ? 8388608 : 4194304;
            }
        }
        if ((i10 & 100663296) == 0) {
            i13 |= c0761q.h(interfaceC2786o) ? 67108864 : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i13 |= c0761q.h(interfaceC2786o2) ? 536870912 : 268435456;
        }
        if ((i11 & 6) == 0) {
            i15 = i11 | (c0761q.f(aVar) ? 4 : 2);
        } else {
            i15 = i11;
        }
        if ((i13 & 306783379) == 306783378 && (i15 & 3) == 2 && c0761q.x()) {
            c0761q.K();
            i17 = i14;
            uC = u10;
        } else {
            c0761q.M();
            boolean z10 = true;
            if ((i10 & 1) == 0 || c0761q.w()) {
                if ((32 & i12) != 0) {
                    C0497b0 c0497b0 = C0497b0.f5820a;
                    uC = C0497b0.c(c0761q);
                    i13 &= -458753;
                } else {
                    uC = u10;
                }
                if (i19 != 0) {
                    i14 = 0;
                }
                i16 = i14;
            } else {
                c0761q.K();
                if ((i12 & 32) != 0) {
                    i13 &= -458753;
                }
                i16 = i14;
                uC = u10;
            }
            c0761q.q();
            boolean z11 = (29360128 & i13) == 8388608;
            if ((((i15 & 14) ^ 6) <= 4 || !c0761q.f(aVar)) && (i15 & 6) != 4) {
                z10 = false;
            }
            boolean z12 = z10 | z11;
            Object objG = c0761q.G();
            if (z12 || objG == C0753m.f8270a) {
                objG = new C0523o0(f10, i16, interfaceC2772a, aVar);
                c0761q.a0(objG);
            }
            C0523o0 c0523o0 = (C0523o0) objG;
            c0523o0.f6003m = interfaceC2772a;
            c0523o0.f6005p = interfaceC2782k;
            c0523o0.d(f10);
            int i20 = ((i13 >> 3) & 1008) | ((i13 >> 6) & 57344);
            int i21 = i13 >> 9;
            c(c0523o0, oVar, z6, null, kVar, interfaceC2786o, interfaceC2786o2, c0761q, i20 | (458752 & i21) | (i21 & 3670016));
            i17 = i16;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0503e0(f10, interfaceC2782k, oVar, z6, interfaceC2772a, uC, kVar, i17, interfaceC2786o, interfaceC2786o2, aVar, i10, i11, i12);
        }
    }

    public static final void c(C0523o0 c0523o0, g0.o oVar, boolean z6, U u4, C.k kVar, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, C0761q c0761q, int i7) {
        int i10;
        int i11;
        U uC;
        U u10;
        c0761q.S(-1303883986);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c0523o0) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(kVar) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2786o) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2786o2) ? 1048576 : 524288;
        }
        if ((599187 & i10) == 599186 && c0761q.x()) {
            c0761q.K();
            u10 = u4;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                C0497b0 c0497b0 = C0497b0.f5820a;
                i11 = i10 & (-7169);
                uC = C0497b0.c(c0761q);
            } else {
                c0761q.K();
                i11 = i10 & (-7169);
                uC = u4;
            }
            c0761q.q();
            if (c0523o0.l < 0) {
                throw new IllegalArgumentException("steps should be >= 0");
            }
            int i12 = i11 >> 3;
            d(oVar, c0523o0, z6, kVar, interfaceC2786o, interfaceC2786o2, c0761q, (i11 & 896) | (i12 & 14) | ((i11 << 3) & 112) | (i12 & 7168) | (57344 & i12) | (i12 & 458752));
            u10 = uC;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0499c0(c0523o0, oVar, z6, u10, kVar, interfaceC2786o, interfaceC2786o2, i7);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void d(g0.o oVar, C0523o0 c0523o0, boolean z6, C.k kVar, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, C0761q c0761q, int i7) {
        int i10;
        g0.o suspendPointerInputElement;
        boolean z10;
        int i11 = 1;
        c0761q.S(1390990089);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(c0523o0) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(kVar) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2786o) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2786o2) ? 131072 : 65536;
        }
        int i12 = i10;
        if ((i12 & 74899) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            c0523o0.f6008s = c0761q.k(AbstractC0227s0.l) == a1.k.f11038m;
            g0.l lVar = g0.l.f14684a;
            InterfaceC2189c interfaceC2189c = null;
            if (z6) {
                C0515k0 c0515k0 = new C0515k0(c0523o0, null);
                C2730h c2730h = y0.v.f24101a;
                suspendPointerInputElement = new SuspendPointerInputElement(c0523o0, kVar, c0515k0, 4);
            } else {
                suspendPointerInputElement = lVar;
            }
            boolean z11 = c0523o0.f6008s;
            boolean zBooleanValue = ((Boolean) c0523o0.f6011v.getValue()).booleanValue();
            boolean zH = c0761q.h(c0523o0);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (zH || objG == s6) {
                objG = new C0435p(i11, c0523o0, interfaceC2189c);
                c0761q.a0(objG);
            }
            g0.o oVarA = A.X.a(lVar, c0523o0, z6, kVar, zBooleanValue, (InterfaceC2786o) objG, z11);
            T.R0 r02 = AbstractC0537w.f6062a;
            g0.o oVarK = L0.k.a(androidx.compose.foundation.layout.c.j(oVar.k(MinimumInteractiveModifier.f11534a), f5981b, f5980a, 0.0f, 0.0f, 12), false, new C0511i0(0, c0523o0, z6)).k(AbstractC0675c.f7289b);
            float fI = c0523o0.o.i();
            G7.a aVar = c0523o0.f6004n;
            g0.o oVarK2 = androidx.compose.foundation.c.a(L0.k.a(oVarK, true, new y.s0(fI, new G7.a(aVar.f2710a, aVar.f2711b), c0523o0.l)), z6, kVar).k(suspendPointerInputElement).k(oVarA);
            boolean zH2 = c0761q.h(c0523o0);
            Object objG2 = c0761q.G();
            if (zH2 || objG2 == s6) {
                objG2 = new C0335d0(c0523o0, i11);
                c0761q.a0(objG2);
            }
            C0.K k10 = (C0.K) objG2;
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarK2);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, k10, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.o oVarT = androidx.compose.foundation.layout.c.t(androidx.compose.ui.layout.a.c(lVar, V.l));
            boolean zH3 = c0761q.h(c0523o0);
            Object objG3 = c0761q.G();
            if (zH3 || objG3 == s6) {
                z10 = false;
                objG3 = new C0505f0(c0523o0, 0 == true ? 1 : 0);
                c0761q.a0(objG3);
            } else {
                z10 = false;
            }
            g0.o oVarE = androidx.compose.ui.layout.a.e(oVarT, (InterfaceC2782k) objG3);
            g0.g gVar = g0.b.l;
            C0.K kE = AbstractC0133o.e(gVar, z10);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarE);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            int i15 = (i12 >> 3) & 14;
            interfaceC2786o.g(c0523o0, c0761q, Integer.valueOf(((i12 >> 9) & 112) | i15));
            c0761q.p(true);
            g0.o oVarC = androidx.compose.ui.layout.a.c(lVar, V.f5709m);
            C0.K kE2 = AbstractC0133o.e(gVar, false);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarC);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            interfaceC2786o2.g(c0523o0, c0761q, Integer.valueOf(i15 | ((i12 >> 12) & 112)));
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0509h0(oVar, c0523o0, z6, kVar, interfaceC2786o, interfaceC2786o2, i7);
        }
    }

    public static final float e(float f10, float f11, float f12, float[] fArr) {
        Float fValueOf;
        if (fArr.length == 0) {
            fValueOf = null;
        } else {
            float f13 = fArr[0];
            int length = fArr.length - 1;
            if (length == 0) {
                fValueOf = Float.valueOf(f13);
            } else {
                float fAbs = Math.abs(AbstractC2673a.O(f11, f12, f13) - f10);
                G7.c it = new G7.d(1, length, 1).iterator();
                while (it.f2715n) {
                    float f14 = fArr[it.b()];
                    float fAbs2 = Math.abs(AbstractC2673a.O(f11, f12, f14) - f10);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        f13 = f14;
                        fAbs = fAbs2;
                    }
                }
                fValueOf = Float.valueOf(f13);
            }
        }
        return fValueOf != null ? AbstractC2673a.O(f11, f12, fValueOf.floatValue()) : f10;
    }
}
