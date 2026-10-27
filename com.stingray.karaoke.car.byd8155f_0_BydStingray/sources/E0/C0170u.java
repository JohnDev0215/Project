package E0;

import C0.C0107n;
import androidx.compose.ui.node.Owner;
import io.sentry.C1489n1;
import n0.AbstractC1861K;
import n0.C1878g;
import n0.C1891t;
import n0.InterfaceC1889r;
import q0.C2164c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: E0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0170u extends d0 {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final C1878g f1702X;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final q0 f1703V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public C0169t f1704W;

    static {
        C1878g c1878gH = AbstractC1861K.h();
        int i7 = C1891t.l;
        c1878gH.e(C1891t.f19447f);
        c1878gH.k(1.0f);
        c1878gH.l(1);
        f1702X = c1878gH;
    }

    public C0170u(F f10) {
        super(f10);
        q0 q0Var = new q0();
        q0Var.o = 0;
        this.f1703V = q0Var;
        q0Var.f14690s = this;
        this.f1704W = f10.f1482n != null ? new C0169t(this) : null;
    }

    @Override // E0.d0
    public final void L0() {
        if (this.f1704W == null) {
            this.f1704W = new C0169t(this);
        }
    }

    @Override // E0.d0
    public final S O0() {
        return this.f1704W;
    }

    @Override // C0.J
    public final int P(int i7) {
        C1489n1 c1489n1F = this.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.a((d0) f10.f1469H.d, f10.o(), i7);
    }

    @Override // E0.d0
    public final g0.n Q0() {
        return this.f1703V;
    }

    @Override // C0.J
    public final int V(int i7) {
        C1489n1 c1489n1F = this.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.j((d0) f10.f1469H.d, f10.o(), i7);
    }

    @Override // E0.d0
    public final void V0(C0154d c0154d, long j3, C0168s c0168s, boolean z6, boolean z10) {
        boolean z11;
        boolean z12;
        int i7;
        C0168s c0168s2;
        Object[] objArr;
        boolean z13;
        i0 i0Var;
        C0168s c0168s3 = c0168s;
        boolean z14 = false;
        boolean z15 = true;
        F f10 = this.f1648w;
        switch (c0154d.f1627a) {
            case 1:
                z11 = true;
                break;
            default:
                L0.j jVarQ = f10.q();
                z11 = !(jVarQ != null && jVarQ.f3910n);
                break;
        }
        if (z11) {
            if (h6.e.l(j3) && ((i0Var = this.f1646O) == null || !this.f1633A || i0Var.l(j3))) {
                z12 = z10;
            } else {
                if (z6) {
                    float fH0 = H0(j3, P0());
                    if (!Float.isInfinite(fH0) && !Float.isNaN(fH0)) {
                        z12 = false;
                    }
                }
                z12 = z10;
            }
            z14 = true;
        } else {
            z12 = z10;
        }
        if (z14) {
            int i10 = c0168s3.f1700n;
            V.d dVarK = f10.K();
            int i11 = dVarK.f9279n;
            if (i11 > 0) {
                Object[] objArr2 = dVarK.l;
                int i12 = i11 - 1;
                while (true) {
                    F f11 = (F) objArr2[i12];
                    if (f11.W()) {
                        switch (c0154d.f1627a) {
                            case 1:
                                objArr = objArr2;
                                i7 = i10;
                                z13 = z15;
                                c0168s2 = c0168s3;
                                f11.M(j3, c0168s, z6, z12);
                                break;
                            default:
                                Y y10 = f11.f1469H;
                                ((d0) y10.d).U0(d0.f1632U, ((d0) y10.d).N0(j3, z15), c0168s, true, z12);
                                objArr = objArr2;
                                i7 = i10;
                                z13 = z15;
                                c0168s2 = c0168s3;
                                break;
                        }
                        long jD = c0168s.d();
                        if (Float.intBitsToFloat((int) (jD >> 32)) < 0.0f && ((int) (jD & 4294967295L)) != 0) {
                            if (c0168s2.f1701p) {
                                c0168s2.f1700n = c0168s2.o - (z13 ? 1 : 0);
                            }
                        }
                    } else {
                        objArr = objArr2;
                        i7 = i10;
                        z13 = z15;
                        c0168s2 = c0168s3;
                    }
                    i12--;
                    if (i12 >= 0) {
                        objArr2 = objArr;
                        i10 = i7;
                        z15 = z13;
                        c0168s3 = c0168s2;
                    }
                }
            } else {
                i7 = i10;
                c0168s2 = c0168s3;
            }
            c0168s2.f1700n = i7;
        }
    }

    @Override // C0.J
    public final int a0(int i7) {
        C1489n1 c1489n1F = this.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.i((d0) f10.f1469H.d, f10.o(), i7);
    }

    @Override // C0.J
    public final C0.V b(long j3) {
        o0(j3);
        F f10 = this.f1648w;
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                ((F) objArr[i10]).z().f1548v = 3;
                i10++;
            } while (i10 < i7);
        }
        f1(f10.f1463A.h(this, f10.o(), j3));
        a1();
        return this;
    }

    @Override // C0.J
    public final int c(int i7) {
        C1489n1 c1489n1F = this.f1648w.F();
        C0.K kU = c1489n1F.u();
        F f10 = (F) c1489n1F.f16624m;
        return kU.d((d0) f10.f1469H.d, f10.o(), i7);
    }

    @Override // E0.d0
    public final void c1(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        F f10 = this.f1648w;
        Owner ownerA = I.a(f10);
        V.d dVarK = f10.K();
        int i7 = dVarK.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarK.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (f11.W()) {
                    f11.k(interfaceC1889r, c2164c);
                }
                i10++;
            } while (i10 < i7);
        }
        if (ownerA.getShowLayoutBounds()) {
            J0(interfaceC1889r, f1702X);
        }
    }

    @Override // E0.d0, C0.V
    public final void k0(long j3, float f10, C2164c c2164c) {
        d1(j3, f10, null, c2164c);
        if (this.f1576r) {
            return;
        }
        b1();
        this.f1648w.z().y0();
    }

    @Override // C0.V
    public final void l0(long j3, float f10, InterfaceC2782k interfaceC2782k) {
        d1(j3, f10, interfaceC2782k, null);
        if (this.f1576r) {
            return;
        }
        b1();
        this.f1648w.z().y0();
    }

    @Override // E0.Q
    public final int q0(C0107n c0107n) {
        C0169t c0169t = this.f1704W;
        if (c0169t != null) {
            return c0169t.q0(c0107n);
        }
        L l = this.f1648w.t().f1568r;
        boolean z6 = l.f1549w;
        G g7 = l.f1529F;
        if (!z6) {
            N n10 = l.f1542S;
            if (n10.c == 1) {
                g7.f1497f = true;
                if (g7.f1495b) {
                    n10.f1557e = true;
                    n10.f1558f = true;
                }
            } else {
                g7.f1498g = true;
            }
        }
        l.n().f1577s = true;
        l.u();
        l.n().f1577s = false;
        Integer num = (Integer) g7.f1500i.get(c0107n);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }
}
