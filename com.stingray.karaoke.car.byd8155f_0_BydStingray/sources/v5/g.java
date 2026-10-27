package V5;

import F0.AbstractC0227s0;
import Q.AbstractC0520n;
import Q.AbstractC0530s0;
import Q.S;
import Q.T;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.R0;
import T.X;
import m7.q;
import n0.C1863M;
import n0.InterfaceC1866P;
import x.AbstractC2614C;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f9506a = new h(40, (float) 7.5d, (float) 2.5d, 10, 5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f9507b = new h(56, 11, 3, 12, 6);

    public static final void a(final o oVar, final float f10, g0.o oVar2, boolean z6, boolean z10, boolean z11, long j3, long j10, InterfaceC1866P interfaceC1866P, float f11, boolean z12, final boolean z13, C0761q c0761q, final int i7, final int i10, final int i11) {
        int i12;
        long j11;
        long jA;
        boolean z14;
        int i13;
        g0.o oVar3;
        int i14;
        InterfaceC1866P interfaceC1866P2;
        float f12;
        boolean z15;
        boolean z16;
        boolean z17;
        boolean z18;
        final boolean z19;
        final g0.o oVar4;
        final long j12;
        final float f13;
        final InterfaceC1866P interfaceC1866P3;
        final boolean z20;
        final long j13;
        final boolean z21;
        final boolean z22;
        A7.m.f(oVar, "state");
        c0761q.S(-1491869157);
        if ((i7 & 6) == 0) {
            i12 = i7 | (c0761q.f(oVar) ? 4 : 2);
        } else {
            i12 = i7;
        }
        if ((i7 & 48) == 0) {
            i12 |= c0761q.c(f10) ? 32 : 16;
        }
        int i15 = i12 | 224640;
        if ((i7 & 1572864) == 0) {
            j11 = j3;
            i15 |= ((i11 & 64) == 0 && c0761q.e(j11)) ? 1048576 : 524288;
        } else {
            j11 = j3;
        }
        if ((i7 & 12582912) == 0) {
            if ((i11 & 128) == 0) {
                jA = j10;
                int i16 = c0761q.e(jA) ? 8388608 : 4194304;
                i15 |= i16;
            } else {
                jA = j10;
            }
            i15 |= i16;
        } else {
            jA = j10;
        }
        if ((i7 & 100663296) == 0) {
            i15 |= 33554432;
        }
        int i17 = i15 | 805306368;
        int i18 = i11 & 1024;
        if (i18 != 0) {
            i13 = i10 | 6;
            z14 = z12;
        } else if ((i10 & 6) == 0) {
            z14 = z12;
            i13 = i10 | (c0761q.g(z14) ? 4 : 2);
        } else {
            z14 = z12;
            i13 = i10;
        }
        if ((i10 & 48) == 0) {
            i13 |= c0761q.g(z13) ? 32 : 16;
        }
        if ((i17 & 306783379) == 306783378 && (i13 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar4 = oVar2;
            z20 = z6;
            z22 = z10;
            z21 = z11;
            j12 = j11;
            j13 = jA;
            z19 = z14;
            interfaceC1866P3 = interfaceC1866P;
            f13 = f11;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                oVar3 = g0.l.f14684a;
                if ((i11 & 64) != 0) {
                    j11 = l6.d.c(c0761q).f18704g;
                    i17 &= -3670017;
                }
                if ((i11 & 128) != 0) {
                    jA = AbstractC0520n.a(j11, c0761q);
                    i17 &= -29360129;
                }
                J.d dVar = ((S) c0761q.k(T.f5665a)).f5660b;
                J.c cVar = new J.c(50);
                dVar.getClass();
                J.d dVar2 = new J.d(cVar, cVar, cVar, cVar);
                int i19 = i17 & (-234881025);
                float f14 = 16;
                if (i18 != 0) {
                    z14 = false;
                }
                i14 = i19;
                interfaceC1866P2 = dVar2;
                f12 = f14;
                z15 = true;
                z16 = true;
                z17 = false;
            } else {
                c0761q.K();
                if ((i11 & 64) != 0) {
                    i17 &= -3670017;
                }
                if ((i11 & 128) != 0) {
                    i17 &= -29360129;
                }
                oVar3 = oVar2;
                z17 = z10;
                z16 = z11;
                interfaceC1866P2 = interfaceC1866P;
                f12 = f11;
                i14 = i17 & (-234881025);
                z15 = z6;
            }
            c0761q.q();
            boolean z23 = z14;
            h hVar = z14 ? f9507b : f9506a;
            R0 r02 = AbstractC0227s0.f2259f;
            final float fY = ((a1.b) c0761q.k(r02)).y(f10);
            a1.b bVar = (a1.b) c0761q.k(r02);
            long j14 = j11;
            float f15 = hVar.f9508a;
            final int iO = bVar.O(f15);
            float fY2 = ((a1.b) c0761q.k(r02)).y(f12);
            float fA = oVar.a();
            float f16 = f12;
            c0761q.Q(147728297);
            InterfaceC1866P interfaceC1866P4 = interfaceC1866P2;
            float fMin = Math.min(1.0f, fA / fY);
            boolean z24 = z15;
            float fMax = (Math.max(fMin - 0.4f, 0.0f) * 5) / 3;
            long j15 = jA;
            float f17 = 2;
            float fMax2 = Math.max(0.0f, Math.min(Math.abs(fA) - fY, fY * f17) / fY) / 4;
            boolean z25 = z16;
            final boolean z26 = z17;
            g0.o oVar5 = oVar3;
            float fPow = (fMax2 - ((float) Math.pow(fMax2, 2))) * f17;
            int i20 = (((int) ((fMin * fY) + ((fY * fPow) * f17))) + iO) - iO;
            float fS = AbstractC2673a.s(fMax * 0.8f, 0.8f);
            float f18 = ((fPow * f17) + ((0.4f * fMax) - 0.25f)) * 0.5f;
            float fMin2 = Math.min(1.0f, fMax);
            c0761q.Q(1621944692);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new a();
                c0761q.a0(objG);
            }
            a aVar = (a) objG;
            c0761q.p(false);
            aVar.f9476a.setValue(Integer.valueOf(i20));
            aVar.f9477b.setValue(Float.valueOf(0.0f));
            aVar.c.setValue(Float.valueOf(fS));
            aVar.d.setValue(Float.valueOf(f18));
            aVar.f9478e.setValue(Float.valueOf(fMin2));
            c0761q.p(false);
            c0761q.Q(206113030);
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = C0735d.O(Float.valueOf(0.0f), T.S.f8224q);
                c0761q.a0(objG2);
            }
            final X x8 = (X) objG2;
            c0761q.p(false);
            c0761q.Q(206114807);
            if (((Boolean) oVar.d.getValue()).booleanValue()) {
                x8.setValue(Float.valueOf(((Number) aVar.f9476a.getValue()).intValue()));
                z18 = false;
            } else {
                Boolean boolValueOf = Boolean.valueOf(oVar.b());
                c0761q.Q(206124380);
                boolean zD = ((i14 & 14) == 4) | c0761q.d(iO) | c0761q.c(fY2);
                Object objG3 = c0761q.G();
                if (zD || objG3 == s6) {
                    objG3 = new d(oVar, iO, fY2, x8, null);
                    c0761q.a0(objG3);
                }
                z18 = false;
                c0761q.p(false);
                C0735d.f(c0761q, boolValueOf, (InterfaceC2785n) objG3);
            }
            c0761q.p(z18);
            g0.o oVarL = androidx.compose.foundation.layout.c.l(oVar5, f15);
            c0761q.Q(206138292);
            boolean zD2 = c0761q.d(iO) | ((i13 & 112) == 32) | ((i14 & 57344) == 16384) | ((i14 & 14) == 4) | c0761q.c(fY);
            Object objG4 = c0761q.G();
            if (zD2 || objG4 == s6) {
                objG4 = new InterfaceC2782k() { // from class: V5.b
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        float fFloatValue;
                        float fFloatValue2;
                        C1863M c1863m = (C1863M) obj;
                        A7.m.f(c1863m, "$this$graphicsLayer");
                        boolean z27 = z13;
                        int i21 = iO;
                        X x9 = x8;
                        if (z27) {
                            fFloatValue = ((Number) x9.getValue()).floatValue();
                            fFloatValue2 = i21;
                        } else {
                            fFloatValue = i21;
                            fFloatValue2 = ((Number) x9.getValue()).floatValue();
                        }
                        c1863m.u(fFloatValue - fFloatValue2);
                        float fU = 1.0f;
                        if (z26 && !oVar.b()) {
                            fU = AbstractC2673a.u(AbstractC2614C.f23391b.a(((Number) x9.getValue()).floatValue() / AbstractC2673a.r(fY, 1.0f)), 0.0f, 1.0f);
                        }
                        c1863m.i(fU);
                        c1863m.j(fU);
                        return q.f19267a;
                    }
                };
                c0761q.a0(objG4);
            }
            c0761q.p(false);
            AbstractC0530s0.a(androidx.compose.ui.graphics.a.a(oVarL, (InterfaceC2782k) objG4), interfaceC1866P4, j14, 0L, b0.g.b(432148128, new f(hVar, z25, oVar, j15, z24, fY, aVar), c0761q), c0761q, ((i14 >> 12) & 896) | 12582912, 120);
            z19 = z23;
            oVar4 = oVar5;
            j12 = j14;
            f13 = f16;
            interfaceC1866P3 = interfaceC1866P4;
            z20 = z24;
            j13 = j15;
            z21 = z25;
            z22 = z26;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: V5.c
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    int iZ2 = C0735d.Z(i10);
                    boolean z27 = z13;
                    int i21 = i11;
                    g.a(oVar, f10, oVar4, z20, z22, z21, j12, j13, interfaceC1866P3, f13, z19, z27, (C0761q) obj, iZ, iZ2, i21);
                    return q.f19267a;
                }
            };
        }
    }
}
