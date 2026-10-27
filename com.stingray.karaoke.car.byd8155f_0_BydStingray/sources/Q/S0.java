package Q;

import A.C0004b;
import B2.C0074n;
import D.AbstractC0133o;
import T.C0754m0;
import T.C0761q;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import n0.C1891t;
import n0.InterfaceC1866P;
import x.AbstractC2631e;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final S0 f5662a = new S0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5663b = 56;
    public static final float c = 280;
    public static final float d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f5664e = 2;

    public static M0 c(long j3, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, C0761q c0761q, int i7) {
        long j26;
        long j27;
        long j28;
        long j29;
        long j30;
        long j31;
        long j32;
        long j33;
        long j34;
        long j35;
        long j36;
        long j37;
        long j38 = (i7 & 16) != 0 ? C1891t.f19452k : j13;
        long j39 = (i7 & 32) != 0 ? C1891t.f19452k : j14;
        long j40 = (i7 & 64) != 0 ? C1891t.f19452k : j15;
        long j41 = (i7 & 128) != 0 ? C1891t.f19452k : j16;
        long j42 = (i7 & 256) != 0 ? C1891t.f19452k : j17;
        long j43 = C1891t.f19452k;
        long j44 = (i7 & 2048) != 0 ? j43 : j18;
        long j45 = (i7 & 4096) != 0 ? j43 : j19;
        long j46 = (i7 & 8192) != 0 ? j43 : j20;
        long j47 = (i7 & 16384) != 0 ? j43 : j21;
        long j48 = (i7 & 134217728) != 0 ? j43 : j22;
        long j49 = (i7 & 268435456) != 0 ? j43 : j23;
        long j50 = (i7 & 536870912) != 0 ? j43 : j24;
        long j51 = (i7 & TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) != 0 ? j43 : j25;
        C0516l c0516l = (C0516l) c0761q.k(AbstractC0520n.f5997a);
        long j52 = j47;
        M0 m02 = c0516l.f5957O;
        c0761q.Q(27085453);
        if (m02 == null) {
            j27 = j43;
            j26 = j42;
            M0 m03 = new M0(AbstractC0520n.b(c0516l, 18), AbstractC0520n.b(c0516l, 18), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 18), AbstractC0520n.b(c0516l, 39), AbstractC0520n.b(c0516l, 39), AbstractC0520n.b(c0516l, 39), AbstractC0520n.b(c0516l, 39), AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, 2), (O.S) c0761q.k(O.T.f4941a), AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 2), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 2), AbstractC0520n.b(c0516l, 26), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 2), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 18)), AbstractC0520n.b(c0516l, 2), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 19)), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), AbstractC0520n.b(c0516l, 19), C1891t.b(0.38f, AbstractC0520n.b(c0516l, 19)), AbstractC0520n.b(c0516l, 19));
            c0516l.f5957O = m03;
            m02 = m03;
        } else {
            j26 = j42;
            j27 = j43;
        }
        c0761q.p(false);
        long j53 = j3 != 16 ? j3 : m02.f5593a;
        long j54 = j10 != 16 ? j10 : m02.f5594b;
        long j55 = j11 != 16 ? j11 : m02.c;
        long j56 = j12 != 16 ? j12 : m02.d;
        if (j38 == 16) {
            j38 = m02.f5595e;
        }
        long j57 = j38;
        if (j39 == 16) {
            j39 = m02.f5596f;
        }
        long j58 = j39;
        if (j40 == 16) {
            j40 = m02.f5597g;
        }
        long j59 = j40;
        if (j41 == 16) {
            j41 = m02.f5598h;
        }
        long j60 = j41;
        if (j26 != 16) {
            j28 = j27;
            j29 = j26;
        } else {
            j28 = j27;
            j29 = m02.f5599i;
        }
        if (j28 != 16) {
            j30 = j44;
            j31 = j28;
        } else {
            j30 = j44;
            j31 = m02.f5600j;
        }
        if (j30 != 16) {
            j33 = j30;
            j32 = j45;
        } else {
            j32 = j45;
            j33 = m02.l;
        }
        if (j32 != 16) {
            j35 = j32;
            j34 = j46;
        } else {
            j34 = j46;
            j35 = m02.f5602m;
        }
        if (j34 != 16) {
            j37 = j34;
            j36 = j52;
        } else {
            j36 = j52;
            j37 = m02.f5603n;
        }
        return new M0(j53, j54, j55, j56, j57, j58, j59, j60, j29, j31, m02.f5601k, j33, j35, j37, j36 != 16 ? j36 : m02.o, j28 != 16 ? j28 : m02.f5604p, j28 != 16 ? j28 : m02.f5605q, j28 != 16 ? j28 : m02.f5606r, j28 != 16 ? j28 : m02.f5607s, j28 != 16 ? j28 : m02.f5608t, j28 != 16 ? j28 : m02.f5609u, j28 != 16 ? j28 : m02.f5610v, j28 != 16 ? j28 : m02.f5611w, j28 != 16 ? j28 : m02.f5612x, j28 != 16 ? j28 : m02.f5613y, j28 != 16 ? j28 : m02.f5614z, j28 != 16 ? j28 : m02.f5577A, j48 != 16 ? j48 : m02.B, j49 != 16 ? j49 : m02.f5578C, j50 != 16 ? j50 : m02.f5579D, j51 != 16 ? j51 : m02.f5580E, j28 != 16 ? j28 : m02.f5581F, j28 != 16 ? j28 : m02.f5582G, j28 != 16 ? j28 : m02.f5583H, j28 != 16 ? j28 : m02.f5584I, j28 != 16 ? j28 : m02.f5585J, j28 != 16 ? j28 : m02.f5586K, j28 != 16 ? j28 : m02.f5587L, j28 != 16 ? j28 : m02.f5588M, j28 != 16 ? j28 : m02.f5589N, j28 != 16 ? j28 : m02.f5590O, j28 != 16 ? j28 : m02.f5591P, j28 != 16 ? j28 : m02.f5592Q);
    }

    public static D.L d() {
        float f10 = R.O.f7278b;
        return new D.L(f10, R.O.d, f10, 0);
    }

    public final void a(boolean z6, boolean z10, C.k kVar, g0.o oVar, M0 m02, InterfaceC1866P interfaceC1866P, float f10, float f11, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-818661242);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(kVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(oVar) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(m02) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(interfaceC1866P) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.c(f10) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.c(f11) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.f(this) ? 67108864 : 33554432;
        }
        if ((38347923 & i10) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.M();
            if ((i7 & 1) != 0 && !c0761q.w()) {
                c0761q.K();
            }
            c0761q.q();
            R0 r02 = new R0(new E.m(0, 2, T.Q0.class, w.L.a(!z6 ? m02.f5597g : z10 ? m02.f5598h : ((Boolean) com.bumptech.glide.c.S(kVar, c0761q, (i10 >> 6) & 14).getValue()).booleanValue() ? m02.f5595e : m02.f5596f, AbstractC2631e.q(150, 0, null, 6), null, c0761q, 48, 12), "value", "getValue()Ljava/lang/Object;"));
            float f12 = R.O.f7278b;
            AbstractC0133o.a(g0.a.b(androidx.compose.ui.draw.a.b(oVar, new C0004b(interfaceC1866P, 27, r02)), new Q0(kVar, z6, z10, m02, f10, f11)), c0761q, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new N0(this, z6, z10, kVar, oVar, m02, interfaceC1866P, f10, f11, i7);
        }
    }

    public final void b(String str, InterfaceC2785n interfaceC2785n, boolean z6, boolean z10, C0074n c0074n, C.k kVar, boolean z11, InterfaceC2785n interfaceC2785n2, InterfaceC2785n interfaceC2785n3, InterfaceC2785n interfaceC2785n4, InterfaceC2785n interfaceC2785n5, InterfaceC2785n interfaceC2785n6, InterfaceC2785n interfaceC2785n7, InterfaceC2785n interfaceC2785n8, InterfaceC1866P interfaceC1866P, M0 m02, D.L l, InterfaceC2785n interfaceC2785n9, C0761q c0761q, int i7, int i10) {
        int i11;
        int i12;
        D.L l10;
        int i13;
        InterfaceC2785n interfaceC2785nB;
        D.L l11;
        D.L l12;
        InterfaceC2785n interfaceC2785n10;
        c0761q.S(289640444);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.g(z10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.f(c0074n) ? 16384 : 8192;
        }
        if ((i7 & 196608) == 0) {
            i11 |= c0761q.f(kVar) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i11 |= c0761q.g(z11) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.h(interfaceC2785n2) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2785n3) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.h(interfaceC2785n4) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.h(interfaceC2785n5) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.h(interfaceC2785n6) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.h(interfaceC2785n7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.h(interfaceC2785n8) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= c0761q.f(interfaceC1866P) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i12 |= c0761q.f(m02) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= 524288;
        }
        int i14 = i12 | 12582912;
        if ((i10 & 100663296) == 0) {
            i14 |= c0761q.f(this) ? 67108864 : 33554432;
        }
        if ((i11 & 306783379) == 306783378 && (38347923 & i14) == 38347922 && c0761q.x()) {
            c0761q.K();
            l12 = l;
            interfaceC2785n10 = interfaceC2785n9;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                if (interfaceC2785n2 == null) {
                    float f10 = R.O.f7278b;
                    l10 = new D.L(f10, f10, f10, f10);
                } else {
                    float f11 = R.O.f7278b;
                    float f12 = X0.f5757a;
                    l10 = new D.L(f11, f12, f11, f12);
                }
                i13 = (-3670017) & i14;
                interfaceC2785nB = b0.g.b(-435523791, new O0(z6, z11, kVar, m02, interfaceC1866P), c0761q);
                l11 = l10;
            } else {
                c0761q.K();
                i13 = i14 & (-3670017);
                l11 = l;
                interfaceC2785nB = interfaceC2785n9;
            }
            c0761q.q();
            int i15 = i11 << 3;
            int i16 = i11 >> 3;
            int i17 = i11 >> 9;
            int i18 = i13 << 21;
            R.O.a(str, interfaceC2785n, c0074n, interfaceC2785n2, interfaceC2785n3, interfaceC2785n4, interfaceC2785n5, interfaceC2785n6, interfaceC2785n7, interfaceC2785n8, z10, z6, z11, kVar, l11, m02, interfaceC2785nB, c0761q, (i15 & 896) | (i15 & 112) | 6 | (i16 & 7168) | (i17 & 57344) | (i17 & 458752) | (i17 & 3670016) | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192), (i11 & 896) | ((i13 >> 9) & 14) | ((i11 >> 6) & 112) | (i17 & 7168) | (i16 & 57344) | ((i13 << 3) & 3670016) | (i13 & 29360128));
            l12 = l11;
            interfaceC2785n10 = interfaceC2785nB;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new P0(this, str, interfaceC2785n, z6, z10, c0074n, kVar, z11, interfaceC2785n2, interfaceC2785n3, interfaceC2785n4, interfaceC2785n5, interfaceC2785n6, interfaceC2785n7, interfaceC2785n8, interfaceC1866P, m02, l12, interfaceC2785n10, i7, i10);
        }
    }
}
