package K;

import A.C0022k;
import B2.C0074n;
import N0.C0411f;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import n0.AbstractC1887p;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: K.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0344i {
    static {
        float f10 = 40;
        l9.l.k(f10, f10);
    }

    public static final void a(String str, InterfaceC2782k interfaceC2782k, g0.o oVar, boolean z6, boolean z10, N0.L l, C0329a0 c0329a0, Z z11, boolean z12, int i7, int i10, C0074n c0074n, InterfaceC2782k interfaceC2782k2, C.k kVar, AbstractC1887p abstractC1887p, InterfaceC2786o interfaceC2786o, C0761q c0761q, int i11, int i12) {
        int i13;
        int i14;
        InterfaceC2782k interfaceC2782k3;
        InterfaceC2782k interfaceC2782k4;
        c0761q.S(945255183);
        if ((i11 & 6) == 0) {
            i13 = (c0761q.f(str) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i13 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= c0761q.g(z10) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i13 |= c0761q.f(l) ? 131072 : 65536;
        }
        if ((i11 & 1572864) == 0) {
            i13 |= c0761q.f(c0329a0) ? 1048576 : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i13 |= c0761q.f(z11) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i13 |= c0761q.g(z12) ? 67108864 : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i13 |= c0761q.d(i7) ? 536870912 : 268435456;
        }
        if ((i12 & 6) == 0) {
            i14 = i12 | (c0761q.d(i10) ? 4 : 2);
        } else {
            i14 = i12;
        }
        if ((i12 & 48) == 0) {
            i14 |= c0761q.f(c0074n) ? 32 : 16;
        }
        int i15 = i14 | 384;
        if ((i12 & 3072) == 0) {
            i15 |= c0761q.f(kVar) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i15 |= c0761q.f(abstractC1887p) ? 16384 : 8192;
        }
        if ((i12 & 196608) == 0) {
            i15 |= c0761q.h(interfaceC2786o) ? 131072 : 65536;
        }
        int i16 = i15;
        if ((306783379 & i13) == 306783378 && (i16 & 74899) == 74898 && c0761q.x()) {
            c0761q.K();
            interfaceC2782k4 = interfaceC2782k2;
        } else {
            c0761q.M();
            if ((i11 & 1) == 0 || c0761q.w()) {
                interfaceC2782k3 = C0340g.f3254n;
            } else {
                c0761q.K();
                interfaceC2782k3 = interfaceC2782k2;
            }
            c0761q.q();
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            T.S s6 = T.S.f8224q;
            if (objG == obj) {
                objG = C0735d.O(new T0.y(str, 0L, 6), s6);
                c0761q.a0(objG);
            }
            T.X x8 = (T.X) objG;
            T0.y yVar = (T0.y) x8.getValue();
            T0.y yVar2 = new T0.y(new C0411f(str, null, 6), yVar.f8482b, yVar.c);
            boolean zF = c0761q.f(yVar2);
            Object objG2 = c0761q.G();
            if (zF || objG2 == obj) {
                objG2 = new B8.A(yVar2, 12, x8);
                c0761q.a0(objG2);
            }
            C0735d.i((InterfaceC2772a) objG2, c0761q);
            boolean z13 = (i13 & 14) == 4;
            Object objG3 = c0761q.G();
            if (z13 || objG3 == obj) {
                objG3 = C0735d.O(str, s6);
                c0761q.a0(objG3);
            }
            Object obj2 = (T.X) objG3;
            c0329a0.getClass();
            int i17 = c0329a0.f3214a;
            T0.n nVar = new T0.n(i17);
            if (T0.n.a(i17, -1)) {
                nVar = null;
            }
            int i18 = nVar != null ? nVar.f8464a : 0;
            Boolean bool = c0329a0.f3215b;
            boolean zBooleanValue = bool != null ? bool.booleanValue() : true;
            int i19 = c0329a0.c;
            T0.o oVar2 = new T0.o(i19);
            if (T0.o.a(i19, 0)) {
                oVar2 = null;
            }
            int i20 = oVar2 != null ? oVar2.f8465a : 1;
            int i21 = c0329a0.d;
            T0.l lVar = !T0.l.a(i21, -1) ? new T0.l(i21) : null;
            int i22 = lVar != null ? lVar.f8458a : 1;
            U0.b bVar = c0329a0.f3217f;
            if (bVar == null) {
                bVar = U0.b.f8957n;
            }
            T0.m mVar = new T0.m(z12, i18, zBooleanValue, i20, i22, bVar);
            boolean z14 = !z12;
            int i23 = z12 ? 1 : i10;
            int i24 = z12 ? 1 : i7;
            boolean zF2 = ((i13 & 112) == 32) | c0761q.f(obj2);
            Object objG4 = c0761q.G();
            if (zF2 || objG4 == obj) {
                objG4 = new C0022k(interfaceC2782k, x8, obj2, 4);
                c0761q.a0(objG4);
            }
            int i25 = i16 << 9;
            X.d(yVar2, (InterfaceC2782k) objG4, oVar, l, c0074n, interfaceC2782k3, kVar, abstractC1887p, z14, i24, i23, mVar, z11, z6, z10, interfaceC2786o, c0761q, (i13 & 896) | ((i13 >> 6) & 7168) | (i25 & 57344) | (i25 & 458752) | (3670016 & i25) | (i25 & 29360128), (i13 & 57344) | ((i13 >> 15) & 896) | (i13 & 7168) | (i16 & 458752));
            interfaceC2782k4 = interfaceC2782k3;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0342h(str, interfaceC2782k, oVar, z6, z10, l, c0329a0, z11, z12, i7, i10, c0074n, interfaceC2782k4, kVar, abstractC1887p, interfaceC2786o, i11, i12);
        }
    }
}
