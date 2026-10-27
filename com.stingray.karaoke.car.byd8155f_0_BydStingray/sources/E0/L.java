package E0;

import A.I0;
import C0.C0107n;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.List;
import q0.C2164c;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class L extends C0.V implements C0.J, InterfaceC0151a, W {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f1525A;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Object f1526C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f1527D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f1528E;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f1532I;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public float f1534K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f1535L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public InterfaceC2782k f1536M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C2164c f1537N;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public float f1539P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final B8.A f1540Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f1541R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final /* synthetic */ N f1542S;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1543q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1546t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1547u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1549w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f1551y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C2164c f1552z;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1544r = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1545s = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1548v = 3;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f1550x = 0;
    public boolean B = true;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final G f1529F = new G(this, 0);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final V.d f1530G = new V.d(new L[16]);

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f1531H = true;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final I0 f1533J = new I0(this, 9);

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public long f1538O = 0;

    public L(N n10) {
        this.f1542S = n10;
        this.f1540Q = new B8.A(n10, 3, this);
    }

    public final void A0(long j3, float f10, InterfaceC2782k interfaceC2782k, C2164c c2164c) {
        C0.U placementScope;
        this.f1528E = true;
        boolean zB = a1.h.b(j3, this.f1550x);
        N n10 = this.f1542S;
        if (!zB || this.f1541R) {
            if (n10.f1564m || n10.l || this.f1541R) {
                n10.f1557e = true;
                this.f1541R = false;
            }
            w0();
        }
        if (AbstractC0156f.p(n10.f1555a)) {
            d0 d0Var = n10.a().f1650y;
            F f11 = n10.f1555a;
            if (d0Var == null || (placementScope = d0Var.f1578t) == null) {
                placementScope = I.a(f11).getPlacementScope();
            }
            K k10 = n10.f1569s;
            A7.m.c(k10);
            F fG = f11.G();
            if (fG != null) {
                fG.t().f1562j = 0;
            }
            k10.f1517s = Integer.MAX_VALUE;
            C0.U.d(placementScope, k10, (int) (j3 >> 32), (int) (4294967295L & j3));
        }
        K k11 = n10.f1569s;
        if (k11 == null || k11.f1520v) {
            z0(j3, f10, interfaceC2782k, c2164c);
        } else {
            l9.d.h0("Error: Placement happened before lookahead.");
            throw null;
        }
    }

    public final boolean B0(long j3) {
        N n10 = this.f1542S;
        F f10 = n10.f1555a;
        if (f10.f1478Q) {
            l9.d.g0("measure is called on a deactivated node");
            throw null;
        }
        Owner ownerA = I.a(f10);
        F f11 = n10.f1555a;
        F fG = f11.G();
        boolean z6 = true;
        f11.f1468G = f11.f1468G || (fG != null && fG.f1468G);
        if (!f11.B() && a1.a.b(this.o, j3)) {
            ((AndroidComposeView) ownerA).f11643S.f(f11, false);
            f11.u0();
            return false;
        }
        this.f1529F.f1497f = false;
        V.d dVarL = f11.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                ((F) objArr[i10]).t().f1568r.f1529F.c = false;
                i10++;
            } while (i10 < i7);
        }
        this.f1546t = true;
        long j10 = n10.a().f872n;
        o0(j3);
        if (n10.c != 5) {
            l9.d.h0("layout state is not idle before measure starts");
            throw null;
        }
        n10.c = 1;
        n10.d = false;
        n10.f1570t = j3;
        k0 snapshotObserver = I.a(f11).getSnapshotObserver();
        snapshotObserver.a(f11, snapshotObserver.c, n10.f1571u);
        if (n10.c == 1) {
            n10.f1557e = true;
            n10.f1558f = true;
            n10.c = 5;
        }
        if (a1.j.a(n10.a().f872n, j10) && n10.a().l == this.l && n10.a().f871m == this.f871m) {
            z6 = false;
        }
        n0(H7.H.c(n10.a().l, n10.a().f871m));
        return z6;
    }

    @Override // E0.W
    public final void H(boolean z6) {
        N n10 = this.f1542S;
        boolean z10 = n10.a().f1575q;
        if (z6 != z10) {
            n10.a().f1575q = z10;
            this.f1541R = true;
        }
    }

    @Override // C0.J
    public final int P(int i7) {
        x0();
        return this.f1542S.a().P(i7);
    }

    @Override // E0.InterfaceC0151a
    public final void R() {
        F.s0(this.f1542S.f1555a, false, 7);
    }

    @Override // C0.J
    public final int V(int i7) {
        x0();
        return this.f1542S.a().V(i7);
    }

    @Override // E0.InterfaceC0151a
    public final G a() {
        return this.f1529F;
    }

    @Override // C0.J
    public final int a0(int i7) {
        x0();
        return this.f1542S.a().a0(i7);
    }

    @Override // C0.J
    public final C0.V b(long j3) {
        int i7;
        N n10 = this.f1542S;
        F f10 = n10.f1555a;
        if (f10.f1479R == 3) {
            f10.c();
        }
        F f11 = n10.f1555a;
        if (AbstractC0156f.p(f11)) {
            K k10 = n10.f1569s;
            A7.m.c(k10);
            k10.f1518t = 3;
            k10.b(j3);
        }
        F fG = f11.G();
        if (fG == null) {
            this.f1548v = 3;
        } else {
            if (this.f1548v != 3 && !f11.f1468G) {
                l9.d.h0("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
                throw null;
            }
            int iC = AbstractC2641j.c(fG.v());
            if (iC != 0) {
                i7 = 2;
                if (iC != 2) {
                    throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is ".concat(A7.k.v(fG.v())));
                }
            } else {
                i7 = 1;
            }
            this.f1548v = i7;
        }
        B0(j3);
        return this;
    }

    @Override // C0.J
    public final int c(int i7) {
        x0();
        return this.f1542S.a().c(i7);
    }

    @Override // C0.V
    public final int e0(C0107n c0107n) {
        N n10 = this.f1542S;
        F fG = n10.f1555a.G();
        int iV = fG != null ? fG.v() : 0;
        G g7 = this.f1529F;
        if (iV == 1) {
            g7.c = true;
        } else {
            F fG2 = n10.f1555a.G();
            if ((fG2 != null ? fG2.v() : 0) == 3) {
                g7.d = true;
            }
        }
        this.f1549w = true;
        int iE0 = n10.a().e0(c0107n);
        this.f1549w = false;
        return iE0;
    }

    @Override // C0.V
    public final int f0() {
        return this.f1542S.a().f0();
    }

    @Override // C0.V
    public final int h0() {
        return this.f1542S.a().h0();
    }

    @Override // C0.V, C0.J
    public final Object i() {
        return this.f1526C;
    }

    @Override // C0.V
    public final void k0(long j3, float f10, C2164c c2164c) {
        A0(j3, f10, null, c2164c);
    }

    @Override // E0.InterfaceC0151a
    public final void l(A.F f10) {
        V.d dVarL = this.f1542S.f1555a.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                f10.b(((F) objArr[i10]).t().f1568r);
                i10++;
            } while (i10 < i7);
        }
    }

    @Override // C0.V
    public final void l0(long j3, float f10, InterfaceC2782k interfaceC2782k) {
        A0(j3, f10, interfaceC2782k, null);
    }

    @Override // E0.InterfaceC0151a
    public final C0170u n() {
        return (C0170u) this.f1542S.f1555a.f1469H.c;
    }

    public final List q0() {
        N n10 = this.f1542S;
        n10.f1555a.z0();
        boolean z6 = this.f1531H;
        V.d dVar = this.f1530G;
        if (!z6) {
            return dVar.f();
        }
        F f10 = n10.f1555a;
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (dVar.f9279n <= i10) {
                    dVar.b(f11.t().f1568r);
                } else {
                    L l = f11.t().f1568r;
                    Object[] objArr2 = dVar.l;
                    Object obj = objArr2[i10];
                    objArr2[i10] = l;
                }
                i10++;
            } while (i10 < i7);
        }
        dVar.p(((V.a) f10.p()).l.f9279n, dVar.f9279n);
        this.f1531H = false;
        return dVar.f();
    }

    @Override // E0.InterfaceC0151a
    public final InterfaceC0151a r() {
        N nT;
        F fG = this.f1542S.f1555a.G();
        if (fG == null || (nT = fG.t()) == null) {
            return null;
        }
        return nT.f1568r;
    }

    @Override // E0.InterfaceC0151a
    public final void requestLayout() {
        this.f1542S.f1555a.r0(false);
    }

    public final void t0() {
        boolean z6 = this.f1527D;
        this.f1527D = true;
        F f10 = this.f1542S.f1555a;
        if (!z6) {
            if (f10.B()) {
                F.s0(f10, true, 6);
            } else if (f10.x()) {
                F.q0(f10, true, 6);
            }
        }
        Y y10 = f10.f1469H;
        d0 d0Var = ((C0170u) y10.c).f1649x;
        for (d0 d0Var2 = (d0) y10.d; !A7.m.a(d0Var2, d0Var) && d0Var2 != null; d0Var2 = d0Var2.f1649x) {
            if (d0Var2.f1645N) {
                d0Var2.W0();
            }
        }
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (f11.H() != Integer.MAX_VALUE) {
                    f11.z().t0();
                    F.t0(f11);
                }
                i10++;
            } while (i10 < i7);
        }
    }

    @Override // E0.InterfaceC0151a
    public final void u() {
        V.d dVarL;
        int i7;
        this.f1532I = true;
        G g7 = this.f1529F;
        g7.h();
        N n10 = this.f1542S;
        boolean z6 = n10.f1557e;
        F f10 = n10.f1555a;
        if (z6 && (i7 = (dVarL = f10.L()).f9279n) > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (f11.B() && f11.C() == 1 && F.l0(f11)) {
                    F.s0(f10, false, 7);
                }
                i10++;
            } while (i10 < i7);
        }
        if (n10.f1558f || (!this.f1549w && !n().f1577s && n10.f1557e)) {
            n10.f1557e = false;
            int i11 = n10.c;
            n10.c = 3;
            n10.e(false);
            k0 snapshotObserver = I.a(f10).getSnapshotObserver();
            snapshotObserver.a(f10, snapshotObserver.f1684e, this.f1533J);
            n10.c = i11;
            if (n().f1577s && n10.l) {
                requestLayout();
            }
            n10.f1558f = false;
        }
        if (g7.d) {
            g7.f1496e = true;
        }
        if (g7.f1495b && g7.e()) {
            g7.g();
        }
        this.f1532I = false;
    }

    public final void v0() {
        if (this.f1527D) {
            int i7 = 0;
            this.f1527D = false;
            N n10 = this.f1542S;
            Y y10 = n10.f1555a.f1469H;
            d0 d0Var = ((C0170u) y10.c).f1649x;
            for (d0 d0Var2 = (d0) y10.d; !A7.m.a(d0Var2, d0Var) && d0Var2 != null; d0Var2 = d0Var2.f1649x) {
                if (d0Var2.f1646O != null) {
                    if (d0Var2.f1647P != null) {
                        d0Var2.f1647P = null;
                    }
                    d0Var2.l1(null, false);
                    d0Var2.f1648w.r0(false);
                }
            }
            V.d dVarL = n10.f1555a.L();
            int i10 = dVarL.f9279n;
            if (i10 > 0) {
                Object[] objArr = dVarL.l;
                do {
                    ((F) objArr[i7]).z().v0();
                    i7++;
                } while (i7 < i10);
            }
        }
    }

    public final void w0() {
        V.d dVarL;
        int i7;
        N n10 = this.f1542S;
        if (n10.f1565n <= 0 || (i7 = (dVarL = n10.f1555a.L()).f9279n) <= 0) {
            return;
        }
        Object[] objArr = dVarL.l;
        int i10 = 0;
        do {
            F f10 = (F) objArr[i10];
            N nT = f10.t();
            if ((nT.l || nT.f1564m) && !nT.f1557e) {
                f10.r0(false);
            }
            nT.f1568r.w0();
            i10++;
        } while (i10 < i7);
    }

    public final void x0() {
        int i7;
        N n10 = this.f1542S;
        F.s0(n10.f1555a, false, 7);
        F f10 = n10.f1555a;
        F fG = f10.G();
        if (fG == null || f10.f1479R != 3) {
            return;
        }
        int iC = AbstractC2641j.c(fG.v());
        if (iC != 0) {
            i7 = 2;
            if (iC != 2) {
                i7 = fG.f1479R;
            }
        } else {
            i7 = 1;
        }
        f10.f1479R = i7;
    }

    public final void y0() {
        this.f1535L = true;
        N n10 = this.f1542S;
        F fG = n10.f1555a.G();
        float f10 = n().f1640I;
        Y y10 = n10.f1555a.f1469H;
        d0 d0Var = (d0) y10.d;
        while (d0Var != ((C0170u) y10.c)) {
            A7.m.d(d0Var, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
            A a10 = (A) d0Var;
            f10 += a10.f1640I;
            d0Var = a10.f1649x;
        }
        if (f10 != this.f1534K) {
            this.f1534K = f10;
            if (fG != null) {
                fG.i0();
            }
            if (fG != null) {
                fG.O();
            }
        }
        if (!this.f1527D) {
            if (fG != null) {
                fG.O();
            }
            t0();
            if (this.f1543q && fG != null) {
                fG.r0(false);
            }
        }
        if (fG == null) {
            this.f1545s = 0;
        } else if (!this.f1543q && fG.v() == 3) {
            if (this.f1545s != Integer.MAX_VALUE) {
                l9.d.h0("Place was called on a node which was placed already");
                throw null;
            }
            this.f1545s = fG.t().f1563k;
            fG.t().f1563k++;
        }
        u();
    }

    @Override // E0.InterfaceC0151a
    public final boolean z() {
        return this.f1527D;
    }

    public final void z0(long j3, float f10, InterfaceC2782k interfaceC2782k, C2164c c2164c) {
        N n10 = this.f1542S;
        F f11 = n10.f1555a;
        if (f11.f1478Q) {
            l9.d.g0("place is called on a deactivated node");
            throw null;
        }
        n10.c = 3;
        this.f1550x = j3;
        this.f1525A = f10;
        this.f1551y = interfaceC2782k;
        this.f1552z = c2164c;
        this.f1547u = true;
        this.f1535L = false;
        Owner ownerA = I.a(f11);
        if (n10.f1557e || !this.f1527D) {
            this.f1529F.f1498g = false;
            n10.d(false);
            this.f1536M = interfaceC2782k;
            this.f1538O = j3;
            this.f1539P = f10;
            this.f1537N = c2164c;
            k0 snapshotObserver = ownerA.getSnapshotObserver();
            snapshotObserver.a(n10.f1555a, snapshotObserver.f1685f, this.f1540Q);
        } else {
            d0 d0VarA = n10.a();
            d0VarA.d1(a1.h.d(j3, d0VarA.f873p), f10, interfaceC2782k, c2164c);
            y0();
        }
        n10.c = 5;
    }
}
