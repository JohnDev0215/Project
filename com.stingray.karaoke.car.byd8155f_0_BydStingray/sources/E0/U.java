package E0;

import n0.C1863M;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1588a;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a1.a f1594i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M.t f1589b = new M.t(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final M.t f1590e = new M.t(5);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V.d f1591f = new V.d(new F[16]);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f1592g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final V.d f1593h = new V.d(new T[16]);

    public U(F f10) {
        this.f1588a = f10;
    }

    public static boolean b(F f10, a1.a aVar) {
        if (f10.f1482n == null) {
            return false;
        }
        boolean Z6 = aVar != null ? f10.Z(aVar) : F.a0(f10);
        F fG = f10.G();
        if (Z6 && fG != null) {
            if (fG.f1482n == null) {
                F.s0(fG, false, 3);
            } else if (f10.D() == 1) {
                F.q0(fG, false, 3);
            } else if (f10.D() == 2) {
                fG.p0(false);
            }
        }
        return Z6;
    }

    public static boolean c(F f10, a1.a aVar) {
        boolean zK0 = aVar != null ? f10.k0(aVar) : F.l0(f10);
        F fG = f10.G();
        if (zK0 && fG != null) {
            if (f10.C() == 1) {
                F.s0(fG, false, 3);
            } else if (f10.C() == 2) {
                fG.r0(false);
            }
        }
        return zK0;
    }

    public static boolean h(F f10) {
        return f10.C() == 1 || f10.t().f1568r.f1529F.e();
    }

    public static boolean i(F f10) {
        G g7;
        if (f10.D() == 1) {
            return true;
        }
        K k10 = f10.t().f1569s;
        return (k10 == null || (g7 = k10.f1507C) == null || !g7.e()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(boolean r6) {
        /*
            r5 = this;
            M.t r0 = r5.f1590e
            r1 = 1
            if (r6 == 0) goto L13
            java.lang.Object r6 = r0.l
            V.d r6 = (V.d) r6
            r6.g()
            E0.F r5 = r5.f1588a
            r6.b(r5)
            r5.f1477P = r1
        L13:
            E0.h0 r5 = E0.h0.f1673m
            java.lang.Object r6 = r0.l
            V.d r6 = (V.d) r6
            r6.q(r5)
            int r5 = r6.f9279n
            java.lang.Object r2 = r0.f4151m
            E0.F[] r2 = (E0.F[]) r2
            if (r2 == 0) goto L27
            int r3 = r2.length
            if (r3 >= r5) goto L2f
        L27:
            r2 = 16
            int r2 = java.lang.Math.max(r2, r5)
            E0.F[] r2 = new E0.F[r2]
        L2f:
            r3 = 0
            r0.f4151m = r3
            r3 = 0
        L33:
            if (r3 >= r5) goto L3e
            java.lang.Object[] r4 = r6.l
            r4 = r4[r3]
            r2[r3] = r4
            int r3 = r3 + 1
            goto L33
        L3e:
            r6.g()
            int r5 = r5 - r1
        L42:
            r6 = -1
            if (r6 >= r5) goto L54
            r6 = r2[r5]
            A7.m.c(r6)
            boolean r1 = r6.f1477P
            if (r1 == 0) goto L51
            M.t.B(r6)
        L51:
            int r5 = r5 + (-1)
            goto L42
        L54:
            r0.f4151m = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.U.a(boolean):void");
    }

    public final void d() {
        V.d dVar = this.f1593h;
        if (dVar.m()) {
            int i7 = dVar.f9279n;
            if (i7 > 0) {
                Object[] objArr = dVar.l;
                int i10 = 0;
                do {
                    T t10 = (T) objArr[i10];
                    if (t10.f1586a.V()) {
                        boolean z6 = t10.f1587b;
                        boolean z10 = t10.c;
                        F f10 = t10.f1586a;
                        if (z6) {
                            F.q0(f10, z10, 2);
                        } else {
                            F.s0(f10, z10, 2);
                        }
                    }
                    i10++;
                } while (i10 < i7);
            }
            dVar.g();
        }
    }

    public final void e(F f10) {
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (A7.m.a(f11.Y(), Boolean.TRUE) && !f11.f1478Q) {
                    if (this.f1589b.v(f11, true)) {
                        f11.b0();
                    }
                    e(f11);
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final void f(F f10, boolean z6) {
        M.t tVar = this.f1589b;
        if (((t0) ((io.sentry.internal.debugmeta.c) (z6 ? tVar.l : tVar.f4151m)).f16570n).isEmpty()) {
            return;
        }
        if (!this.c) {
            l9.d.h0("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
            throw null;
        }
        if (z6 ? f10.x() : f10.B()) {
            l9.d.g0("node not yet measured");
            throw null;
        }
        g(f10, z6);
    }

    public final void g(F f10, boolean z6) {
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        M.t tVar = this.f1589b;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if ((!z6 && h(f11)) || (z6 && i(f11))) {
                    if (AbstractC0156f.p(f11) && !z6) {
                        if (f11.x() && tVar.v(f11, true)) {
                            m(f11, true, false);
                        } else {
                            f(f11, true);
                        }
                    }
                    if ((z6 ? f11.x() : f11.B()) && tVar.v(f11, z6)) {
                        m(f11, z6, false);
                    }
                    if (!(z6 ? f11.x() : f11.B())) {
                        g(f11, z6);
                    }
                }
                i10++;
            } while (i10 < i7);
        }
        if ((z6 ? f10.x() : f10.B()) && tVar.v(f10, z6)) {
            m(f10, z6, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x010c A[LOOP:1: B:38:0x0085->B:80:0x010c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010f A[EDGE_INSN: B:98:0x010f->B:81:0x010f BREAK  A[LOOP:1: B:38:0x0085->B:80:0x010c], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [V.d] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [V.d] */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3, types: [int] */
    /* JADX WARN: Type inference failed for: r15v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean j(F0.C0236x r17) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.U.j(F0.x):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [E0.F, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v6 */
    public final void k(F f10, long j3) {
        g0.n nVar;
        if (f10.f1478Q) {
            return;
        }
        F f11 = this.f1588a;
        if (f10.equals(f11)) {
            l9.d.g0("measureAndLayout called on root");
            throw null;
        }
        if (!f11.V()) {
            l9.d.g0("performMeasureAndLayout called with unattached root");
            throw null;
        }
        if (!f11.W()) {
            l9.d.g0("performMeasureAndLayout called with unplaced root");
            throw null;
        }
        if (this.c) {
            l9.d.g0("performMeasureAndLayout called during measure layout");
            throw null;
        }
        if (this.f1594i != null) {
            this.c = true;
            this.d = false;
            try {
                M.t tVar = this.f1589b;
                ((io.sentry.internal.debugmeta.c) tVar.l).R(f10);
                ((io.sentry.internal.debugmeta.c) tVar.f4151m).R(f10);
                if (b(f10, new a1.a(j3)) || f10.w()) {
                    if (A7.m.a(f10.Y(), Boolean.TRUE)) {
                        f10.b0();
                    }
                }
                e(f10);
                c(f10, new a1.a(j3));
                if (f10.u() && f10.W()) {
                    f10.o0();
                    ((V.d) this.f1590e.l).b(f10);
                    f10.f1477P = true;
                }
                d();
                this.c = false;
                this.d = false;
            } catch (Throwable th) {
                this.c = false;
                this.d = false;
                throw th;
            }
        }
        V.d dVar = this.f1591f;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            do {
                Y y10 = ((F) objArr[i10]).f1469H;
                C0170u c0170u = (C0170u) y10.c;
                boolean zH = e0.h(128);
                if (zH) {
                    nVar = c0170u.f1703V;
                } else {
                    nVar = c0170u.f1703V.f14687p;
                    if (nVar != null) {
                    }
                    i10++;
                }
                C1863M c1863m = d0.f1628Q;
                for (g0.n nVarS0 = c0170u.S0(zH); nVarS0 != null && (nVarS0.o & 128) != 0; nVarS0 = nVarS0.f14688q) {
                    if ((nVarS0.f14686n & 128) != 0) {
                        ?? dVar2 = 0;
                        ?? F9 = nVarS0;
                        while (F9 != 0) {
                            if (F9 instanceof InterfaceC0173x) {
                                ((InterfaceC0173x) F9).t0((C0170u) y10.c);
                            } else if ((F9.f14686n & 128) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                                int i11 = 0;
                                F9 = F9;
                                dVar2 = dVar2;
                                while (nVar2 != null) {
                                    if ((nVar2.f14686n & 128) != 0) {
                                        i11++;
                                        dVar2 = dVar2;
                                        if (i11 == 1) {
                                            F9 = nVar2;
                                        } else {
                                            if (dVar2 == 0) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (F9 != 0) {
                                                dVar2.b(F9);
                                                F9 = 0;
                                            }
                                            dVar2.b(nVar2);
                                        }
                                    }
                                    nVar2 = nVar2.f14688q;
                                    F9 = F9;
                                    dVar2 = dVar2;
                                }
                                if (i11 == 1) {
                                }
                            }
                            F9 = AbstractC0156f.f(dVar2);
                        }
                    }
                    if (nVarS0 == nVar) {
                        break;
                    }
                }
                i10++;
            } while (i10 < i7);
        }
        dVar.g();
    }

    public final void l() {
        M.t tVar = this.f1589b;
        if (tVar.H()) {
            F f10 = this.f1588a;
            if (!f10.V()) {
                l9.d.g0("performMeasureAndLayout called with unattached root");
                throw null;
            }
            if (!f10.W()) {
                l9.d.g0("performMeasureAndLayout called with unplaced root");
                throw null;
            }
            if (this.c) {
                l9.d.g0("performMeasureAndLayout called during measure layout");
                throw null;
            }
            if (this.f1594i != null) {
                this.c = true;
                this.d = false;
                try {
                    if (!((t0) ((io.sentry.internal.debugmeta.c) tVar.l).f16570n).isEmpty()) {
                        if (f10.f1482n != null) {
                            o(f10, true);
                        } else {
                            n(f10);
                        }
                    }
                    o(f10, false);
                    this.c = false;
                    this.d = false;
                } catch (Throwable th) {
                    this.c = false;
                    this.d = false;
                    throw th;
                }
            }
        }
    }

    public final boolean m(F f10, boolean z6, boolean z10) {
        a1.a aVar;
        F fG;
        if (f10.f1478Q) {
            return false;
        }
        if (f10.W() || f10.X() || ((f10.B() && h(f10)) || A7.m.a(f10.Y(), Boolean.TRUE) || ((f10.x() && i(f10)) || f10.m()))) {
            F f11 = this.f1588a;
            if (f10 == f11) {
                aVar = this.f1594i;
                A7.m.c(aVar);
            } else {
                aVar = null;
            }
            if (z6) {
                zC = f10.x() ? b(f10, aVar) : false;
                if (z10 && ((zC || f10.w()) && A7.m.a(f10.Y(), Boolean.TRUE))) {
                    f10.b0();
                }
            } else {
                zC = f10.B() ? c(f10, aVar) : false;
                if (z10 && f10.u() && (f10 == f11 || ((fG = f10.G()) != null && fG.W() && f10.X()))) {
                    if (f10 == f11) {
                        f10.j0();
                    } else {
                        f10.o0();
                    }
                    ((V.d) this.f1590e.l).b(f10);
                    f10.f1477P = true;
                }
            }
            d();
        }
        return zC;
    }

    public final void n(F f10) {
        V.d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f11 = (F) objArr[i10];
                if (h(f11)) {
                    if (AbstractC0156f.p(f11)) {
                        o(f11, true);
                    } else {
                        n(f11);
                    }
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final void o(F f10, boolean z6) {
        a1.a aVar;
        if (f10.f1478Q) {
            return;
        }
        if (f10 == this.f1588a) {
            aVar = this.f1594i;
            A7.m.c(aVar);
        } else {
            aVar = null;
        }
        if (z6) {
            b(f10, aVar);
        } else {
            c(f10, aVar);
        }
    }

    public final boolean p(F f10, boolean z6) {
        int iC = AbstractC2641j.c(f10.v());
        if (iC == 0 || iC == 1) {
            return false;
        }
        if (iC == 2 || iC == 3) {
            this.f1593h.b(new T(f10, false, z6));
            return false;
        }
        if (iC != 4) {
            throw new A9.k();
        }
        if (f10.B() && !z6) {
            return false;
        }
        f10.f0();
        if (f10.f1478Q) {
            return false;
        }
        if (!f10.W() && (!f10.B() || !h(f10))) {
            return false;
        }
        F fG = f10.G();
        if (fG == null || !fG.B()) {
            this.f1589b.l(f10, false);
        }
        return !this.d;
    }

    public final void q(long j3) {
        a1.a aVar = this.f1594i;
        if (aVar == null ? false : a1.a.b(aVar.f11027a, j3)) {
            return;
        }
        if (this.c) {
            l9.d.g0("updateRootConstraints called while measuring");
            throw null;
        }
        this.f1594i = new a1.a(j3);
        F f10 = this.f1588a;
        if (f10.f1482n != null) {
            f10.e0();
        }
        f10.f0();
        this.f1589b.l(f10, f10.f1482n != null);
    }
}
