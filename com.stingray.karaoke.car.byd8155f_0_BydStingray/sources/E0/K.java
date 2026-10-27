package E0;

import A.C0024l;
import C0.C0107n;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import q0.C2164c;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class K extends C0.V implements C0.J, InterfaceC0151a, W {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C2164c f1506A;
    public boolean B;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f1510F;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Object f1512H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f1513I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final /* synthetic */ N f1514J;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1515q;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1519u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1520v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1521w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public a1.a f1522x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC2782k f1524z;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1516r = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1517s = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1518t = 3;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f1523y = 0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final G f1507C = new G(this, 1);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final V.d f1508D = new V.d(new K[16]);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f1509E = true;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f1511G = true;

    public K(N n10) {
        this.f1514J = n10;
        this.f1512H = n10.f1568r.f1526C;
    }

    @Override // E0.W
    public final void H(boolean z6) {
        S sO0;
        N n10 = this.f1514J;
        S sO02 = n10.a().O0();
        if (Boolean.valueOf(z6).equals(sO02 != null ? Boolean.valueOf(sO02.f1575q) : null) || (sO0 = n10.a().O0()) == null) {
            return;
        }
        sO0.f1575q = z6;
    }

    @Override // C0.J
    public final int P(int i7) {
        w0();
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.P(i7);
    }

    @Override // E0.InterfaceC0151a
    public final void R() {
        F.q0(this.f1514J.f1555a, false, 7);
    }

    @Override // C0.J
    public final int V(int i7) {
        w0();
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.V(i7);
    }

    @Override // E0.InterfaceC0151a
    public final G a() {
        return this.f1507C;
    }

    @Override // C0.J
    public final int a0(int i7) {
        w0();
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.a0(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    @Override // C0.J
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final C0.V b(long r7) {
        /*
            r6 = this;
            E0.N r0 = r6.f1514J
            E0.F r1 = r0.f1555a
            E0.F r1 = r1.G()
            r2 = 0
            if (r1 == 0) goto L10
            int r1 = r1.v()
            goto L11
        L10:
            r1 = r2
        L11:
            r3 = 2
            E0.F r4 = r0.f1555a
            if (r1 == r3) goto L25
            E0.F r1 = r4.G()
            if (r1 == 0) goto L21
            int r1 = r1.v()
            goto L22
        L21:
            r1 = r2
        L22:
            r5 = 4
            if (r1 != r5) goto L27
        L25:
            r0.f1556b = r2
        L27:
            E0.F r0 = r4.G()
            r1 = 3
            if (r0 == 0) goto L68
            int r2 = r6.f1518t
            if (r2 == r1) goto L3e
            boolean r2 = r4.f1468G
            if (r2 == 0) goto L37
            goto L3e
        L37:
            java.lang.String r6 = "measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()"
            l9.d.h0(r6)
            r6 = 0
            throw r6
        L3e:
            int r2 = r0.v()
            int r2 = x.AbstractC2641j.c(r2)
            r5 = 1
            if (r2 == 0) goto L64
            if (r2 == r5) goto L64
            if (r2 == r3) goto L65
            if (r2 != r1) goto L50
            goto L65
        L50:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            int r7 = r0.v()
            java.lang.String r7 = A7.k.v(r7)
            java.lang.String r8 = "Measurable could be only measured from the parent's measure or layout block. Parents state is "
            java.lang.String r7 = r8.concat(r7)
            r6.<init>(r7)
            throw r6
        L64:
            r3 = r5
        L65:
            r6.f1518t = r3
            goto L6a
        L68:
            r6.f1518t = r1
        L6a:
            int r0 = r4.f1479R
            if (r0 != r1) goto L71
            r4.c()
        L71:
            r6.z0(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.K.b(long):C0.V");
    }

    @Override // C0.J
    public final int c(int i7) {
        w0();
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.c(i7);
    }

    @Override // C0.V
    public final int e0(C0107n c0107n) {
        N n10 = this.f1514J;
        F fG = n10.f1555a.G();
        int iV = fG != null ? fG.v() : 0;
        G g7 = this.f1507C;
        if (iV == 2) {
            g7.c = true;
        } else {
            F fG2 = n10.f1555a.G();
            if ((fG2 != null ? fG2.v() : 0) == 4) {
                g7.d = true;
            }
        }
        this.f1519u = true;
        S sO0 = n10.a().O0();
        A7.m.c(sO0);
        int iE0 = sO0.e0(c0107n);
        this.f1519u = false;
        return iE0;
    }

    @Override // C0.V
    public final int f0() {
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.f0();
    }

    @Override // C0.V
    public final int h0() {
        S sO0 = this.f1514J.a().O0();
        A7.m.c(sO0);
        return sO0.h0();
    }

    @Override // C0.V, C0.J
    public final Object i() {
        return this.f1512H;
    }

    @Override // C0.V
    public final void k0(long j3, float f10, C2164c c2164c) {
        y0(j3, null, c2164c);
    }

    @Override // E0.InterfaceC0151a
    public final void l(A.F f10) {
        V.d dVarL = this.f1514J.f1555a.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                K k10 = ((F) objArr[i10]).t().f1569s;
                A7.m.c(k10);
                f10.b(k10);
                i10++;
            } while (i10 < i7);
        }
    }

    @Override // C0.V
    public final void l0(long j3, float f10, InterfaceC2782k interfaceC2782k) {
        y0(j3, interfaceC2782k, null);
    }

    @Override // E0.InterfaceC0151a
    public final C0170u n() {
        return (C0170u) this.f1514J.f1555a.f1469H.c;
    }

    public final void q0() {
        boolean z6 = this.B;
        this.B = true;
        N n10 = this.f1514J;
        if (!z6 && n10.f1559g) {
            F.q0(n10.f1555a, true, 6);
        }
        V.d dVarL = n10.f1555a.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f10 = (F) objArr[i10];
                K kY = f10.y();
                if (kY == null) {
                    throw new IllegalArgumentException("Error: Child node's lookahead pass delegate cannot be null when in a lookahead scope.");
                }
                if (kY.f1517s != Integer.MAX_VALUE) {
                    kY.q0();
                    F.t0(f10);
                }
                i10++;
            } while (i10 < i7);
        }
    }

    @Override // E0.InterfaceC0151a
    public final InterfaceC0151a r() {
        N nT;
        F fG = this.f1514J.f1555a.G();
        if (fG == null || (nT = fG.t()) == null) {
            return null;
        }
        return nT.f1569s;
    }

    @Override // E0.InterfaceC0151a
    public final void requestLayout() {
        this.f1514J.f1555a.p0(false);
    }

    public final void t0() {
        if (this.B) {
            int i7 = 0;
            this.B = false;
            V.d dVarL = this.f1514J.f1555a.L();
            int i10 = dVarL.f9279n;
            if (i10 > 0) {
                Object[] objArr = dVarL.l;
                do {
                    K k10 = ((F) objArr[i7]).t().f1569s;
                    A7.m.c(k10);
                    k10.t0();
                    i7++;
                } while (i7 < i10);
            }
        }
    }

    @Override // E0.InterfaceC0151a
    public final void u() {
        V.d dVarL;
        int i7;
        this.f1510F = true;
        G g7 = this.f1507C;
        g7.h();
        N n10 = this.f1514J;
        boolean z6 = n10.f1560h;
        F f10 = n10.f1555a;
        if (z6 && (i7 = (dVarL = f10.L()).f9279n) > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (f11.x() && f11.D() == 1) {
                    K k10 = f11.t().f1569s;
                    A7.m.c(k10);
                    K k11 = f11.t().f1569s;
                    a1.a aVar = k11 != null ? k11.f1522x : null;
                    A7.m.c(aVar);
                    if (k10.z0(aVar.f11027a)) {
                        F.q0(f10, false, 7);
                    }
                }
                i10++;
            } while (i10 < i7);
        }
        C0169t c0169t = n().f1704W;
        A7.m.c(c0169t);
        if (n10.f1561i || (!this.f1519u && !c0169t.f1577s && n10.f1560h)) {
            n10.f1560h = false;
            int i11 = n10.c;
            n10.c = 4;
            Owner ownerA = I.a(f10);
            n10.g(false);
            k0 snapshotObserver = ownerA.getSnapshotObserver();
            C0024l c0024l = new C0024l(this, c0169t, n10, 3);
            snapshotObserver.getClass();
            if (f10.f1482n != null) {
                snapshotObserver.a(f10, snapshotObserver.f1687h, c0024l);
            } else {
                snapshotObserver.a(f10, snapshotObserver.f1684e, c0024l);
            }
            n10.c = i11;
            if (n10.o && c0169t.f1577s) {
                requestLayout();
            }
            n10.f1561i = false;
        }
        if (g7.d) {
            g7.f1496e = true;
        }
        if (g7.f1495b && g7.e()) {
            g7.g();
        }
        this.f1510F = false;
    }

    public final void v0() {
        V.d dVarL;
        int i7;
        N n10 = this.f1514J;
        if (n10.f1567q <= 0 || (i7 = (dVarL = n10.f1555a.L()).f9279n) <= 0) {
            return;
        }
        Object[] objArr = dVarL.l;
        int i10 = 0;
        do {
            F f10 = (F) objArr[i10];
            N nT = f10.t();
            if ((nT.o || nT.f1566p) && !nT.f1560h) {
                f10.p0(false);
            }
            K k10 = nT.f1569s;
            if (k10 != null) {
                k10.v0();
            }
            i10++;
        } while (i10 < i7);
    }

    public final void w0() {
        int i7;
        N n10 = this.f1514J;
        F.q0(n10.f1555a, false, 7);
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

    public final void x0() {
        this.f1513I = true;
        F fG = this.f1514J.f1555a.G();
        if (!this.B) {
            q0();
            if (this.f1515q && fG != null) {
                fG.p0(false);
            }
        }
        if (fG == null) {
            this.f1517s = 0;
        } else if (!this.f1515q && (fG.v() == 3 || fG.v() == 4)) {
            if (this.f1517s != Integer.MAX_VALUE) {
                l9.d.h0("Place was called on a node which was placed already");
                throw null;
            }
            this.f1517s = fG.t().f1562j;
            fG.t().f1562j++;
        }
        u();
    }

    public final void y0(long j3, InterfaceC2782k interfaceC2782k, C2164c c2164c) {
        N n10 = this.f1514J;
        if (n10.f1555a.f1478Q) {
            l9.d.g0("place is called on a deactivated node");
            throw null;
        }
        n10.c = 4;
        this.f1520v = true;
        this.f1513I = false;
        if (!a1.h.b(j3, this.f1523y)) {
            if (n10.f1566p || n10.o) {
                n10.f1560h = true;
            }
            v0();
        }
        F f10 = n10.f1555a;
        Owner ownerA = I.a(f10);
        if (n10.f1560h || !this.B) {
            n10.f(false);
            this.f1507C.f1498g = false;
            k0 snapshotObserver = ownerA.getSnapshotObserver();
            J j10 = new J(n10, ownerA, j3);
            snapshotObserver.getClass();
            if (f10.f1482n != null) {
                snapshotObserver.a(f10, snapshotObserver.f1686g, j10);
            } else {
                snapshotObserver.a(f10, snapshotObserver.f1685f, j10);
            }
        } else {
            S sO0 = n10.a().O0();
            A7.m.c(sO0);
            sO0.G0(a1.h.d(j3, sO0.f873p));
            x0();
        }
        this.f1523y = j3;
        this.f1524z = interfaceC2782k;
        this.f1506A = c2164c;
        n10.c = 5;
    }

    @Override // E0.InterfaceC0151a
    public final boolean z() {
        return this.B;
    }

    public final boolean z0(long j3) {
        N n10 = this.f1514J;
        F f10 = n10.f1555a;
        if (f10.f1478Q) {
            l9.d.g0("measure is called on a deactivated node");
            throw null;
        }
        F fG = f10.G();
        F f11 = n10.f1555a;
        f11.f1468G = f11.f1468G || (fG != null && fG.f1468G);
        if (!f11.x()) {
            a1.a aVar = this.f1522x;
            if (aVar == null ? false : a1.a.b(aVar.f11027a, j3)) {
                AndroidComposeView androidComposeView = f11.f1487t;
                if (androidComposeView != null) {
                    androidComposeView.f11643S.f(f11, true);
                }
                f11.u0();
                return false;
            }
        }
        this.f1522x = new a1.a(j3);
        o0(j3);
        this.f1507C.f1497f = false;
        V.d dVarL = f11.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                K k10 = ((F) objArr[i10]).t().f1569s;
                A7.m.c(k10);
                k10.f1507C.c = false;
                i10++;
            } while (i10 < i7);
        }
        long jC = this.f1521w ? this.f872n : H7.H.c(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.f1521w = true;
        S sO0 = n10.a().O0();
        if (!(sO0 != null)) {
            l9.d.h0("Lookahead result from lookaheadRemeasure cannot be null");
            throw null;
        }
        n10.c = 2;
        n10.f1559g = false;
        k0 snapshotObserver = I.a(f11).getSnapshotObserver();
        M m10 = new M(0, j3, n10);
        snapshotObserver.getClass();
        if (f11.f1482n != null) {
            snapshotObserver.a(f11, snapshotObserver.f1683b, m10);
        } else {
            snapshotObserver.a(f11, snapshotObserver.c, m10);
        }
        n10.f1560h = true;
        n10.f1561i = true;
        if (AbstractC0156f.p(f11)) {
            n10.f1557e = true;
            n10.f1558f = true;
        } else {
            n10.d = true;
        }
        n10.c = 5;
        n0(H7.H.c(sO0.l, sO0.f871m));
        return (((int) (jC >> 32)) == sO0.l && ((int) (4294967295L & jC)) == sO0.f871m) ? false : true;
    }
}
