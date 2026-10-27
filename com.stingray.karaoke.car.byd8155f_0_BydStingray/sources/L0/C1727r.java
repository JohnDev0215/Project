package l0;

import A7.z;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.F;
import E0.InterfaceC0162l;
import E0.Y;
import E0.d0;
import E0.f0;
import E0.q0;
import androidx.compose.ui.platform.AndroidComposeView;
import d8.C1134u;
import u.C2484x;

/* JADX INFO: renamed from: l0.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1727r extends g0.n implements InterfaceC0162l, f0, D0.d {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EnumC1726q f18353A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f18354y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f18355z;

    public static final boolean J0(C1727r c1727r) {
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            l9.d.h0("visitSubtreeIf called on an unattached node");
            throw null;
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            AbstractC0156f.b(dVar, nVar);
        } else {
            dVar.b(nVar2);
        }
        while (dVar.m()) {
            g0.n nVar3 = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVar3.o & 1024) != 0) {
                for (g0.n nVar4 = nVar3; nVar4 != null; nVar4 = nVar4.f14688q) {
                    if ((nVar4.f14686n & 1024) != 0) {
                        V.d dVar2 = null;
                        g0.n nVarF = nVar4;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                if (c1727r2.f18353A != null) {
                                    int iOrdinal = c1727r2.I0().ordinal();
                                    if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
                                        return true;
                                    }
                                    if (iOrdinal == 3) {
                                        return false;
                                    }
                                    throw new A9.k();
                                }
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i7 = 0;
                                for (g0.n nVar5 = ((AbstractC0164n) nVarF).f1690z; nVar5 != null; nVar5 = nVar5.f14688q) {
                                    if ((nVar5.f14686n & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            nVarF = nVar5;
                                        } else {
                                            if (dVar2 == null) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar2.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar2.b(nVar5);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar2);
                        }
                    }
                }
            }
            AbstractC0156f.b(dVar, nVar3);
        }
        return false;
    }

    public static final boolean K0(C1727r c1727r) {
        Y y10;
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = AbstractC0156f.t(c1727r);
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 1024) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 1024) != 0) {
                        g0.n nVarF = nVar2;
                        V.d dVar = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                if (c1727r2.f18353A != null) {
                                    int iOrdinal = c1727r2.I0().ordinal();
                                    if (iOrdinal == 0) {
                                        return false;
                                    }
                                    if (iOrdinal == 1) {
                                        return true;
                                    }
                                    if (iOrdinal == 2 || iOrdinal == 3) {
                                        return false;
                                    }
                                    throw new A9.k();
                                }
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i7 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar == null) {
                                                dVar = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar.b(nVar3);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
    @Override // g0.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void A0() {
        /*
            r4 = this;
            l0.q r0 = r4.I0()
            int r0 = r0.ordinal()
            r1 = 1
            if (r0 == 0) goto L2e
            if (r0 == r1) goto L11
            r2 = 2
            if (r0 == r2) goto L2e
            goto L4f
        L11:
            t4.e r0 = l0.AbstractC1713d.E(r4)
            boolean r2 = r0.f22462m     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L1f
            t4.e.b(r0)     // Catch: java.lang.Throwable -> L1d
            goto L1f
        L1d:
            r4 = move-exception
            goto L2a
        L1f:
            r0.f22462m = r1     // Catch: java.lang.Throwable -> L1d
            l0.q r1 = l0.EnumC1726q.f18352n     // Catch: java.lang.Throwable -> L1d
            r4.M0(r1)     // Catch: java.lang.Throwable -> L1d
            t4.e.c(r0)
            goto L4f
        L2a:
            t4.e.c(r0)
            throw r4
        L2e:
            androidx.compose.ui.node.Owner r0 = E0.AbstractC0156f.u(r4)
            l0.g r0 = r0.getFocusOwner()
            androidx.compose.ui.focus.b r0 = (androidx.compose.ui.focus.b) r0
            r2 = 0
            r3 = 8
            r0.b(r3, r1, r2)
            androidx.compose.ui.node.Owner r0 = E0.AbstractC0156f.u(r4)
            l0.g r0 = r0.getFocusOwner()
            androidx.compose.ui.focus.b r0 = (androidx.compose.ui.focus.b) r0
            l0.e r0 = r0.f11570g
            u.A r1 = r0.c
            r0.b(r1, r4)
        L4f:
            r0 = 0
            r4.f18353A = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.C1727r.A0():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [V.d] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v4 */
    public final C1719j H0() {
        Y y10;
        C1719j c1719j = new C1719j();
        c1719j.f18338a = true;
        C1723n c1723n = C1723n.f18348b;
        c1719j.f18339b = c1723n;
        c1719j.c = c1723n;
        c1719j.d = c1723n;
        c1719j.f18340e = c1723n;
        c1719j.f18341f = c1723n;
        c1719j.f18342g = c1723n;
        c1719j.f18343h = c1723n;
        c1719j.f18344i = c1723n;
        c1719j.f18345j = C1717h.o;
        c1719j.f18346k = C1717h.f18334p;
        g0.n nVar = this.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        F fT = AbstractC0156f.t(this);
        g0.n nVar2 = nVar;
        loop0: while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 3072) != 0) {
                while (nVar2 != null) {
                    int i7 = nVar2.f14686n;
                    if ((i7 & 3072) != 0) {
                        if (nVar2 != nVar && (i7 & 1024) != 0) {
                            break loop0;
                        }
                        if ((i7 & 2048) != 0) {
                            ?? F9 = nVar2;
                            ?? dVar = 0;
                            while (F9 != 0) {
                                if (F9 instanceof InterfaceC1721l) {
                                    ((InterfaceC1721l) F9).o(c1719j);
                                } else if ((F9.f14686n & 2048) != 0 && (F9 instanceof AbstractC0164n)) {
                                    g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                    int i10 = 0;
                                    F9 = F9;
                                    dVar = dVar;
                                    while (nVar3 != null) {
                                        if ((nVar3.f14686n & 2048) != 0) {
                                            i10++;
                                            dVar = dVar;
                                            if (i10 == 1) {
                                                F9 = nVar3;
                                            } else {
                                                if (dVar == 0) {
                                                    dVar = new V.d(new g0.n[16]);
                                                }
                                                if (F9 != 0) {
                                                    dVar.b(F9);
                                                    F9 = 0;
                                                }
                                                dVar.b(nVar3);
                                            }
                                        }
                                        nVar3 = nVar3.f14688q;
                                        F9 = F9;
                                        dVar = dVar;
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                F9 = AbstractC0156f.f(dVar);
                            }
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        return c1719j;
    }

    public final EnumC1726q I0() {
        EnumC1726q enumC1726q;
        F f10;
        AndroidComposeView androidComposeView;
        InterfaceC1716g focusOwner;
        d0 d0Var = this.l.f14690s;
        t4.e eVar = (d0Var == null || (f10 = d0Var.f1648w) == null || (androidComposeView = f10.f1487t) == null || (focusOwner = androidComposeView.getFocusOwner()) == null) ? null : ((androidx.compose.ui.focus.b) focusOwner).f11571h;
        if (eVar != null && (enumC1726q = (EnumC1726q) ((C2484x) eVar.f22463n).e(this)) != null) {
            return enumC1726q;
        }
        EnumC1726q enumC1726q2 = this.f18353A;
        return enumC1726q2 == null ? EnumC1726q.f18352n : enumC1726q2;
    }

    @Override // E0.f0
    public final void L() {
        EnumC1726q enumC1726qI0 = I0();
        L0();
        if (enumC1726qI0 != I0()) {
            AbstractC1713d.z(this);
        }
    }

    public final void L0() {
        EnumC1726q enumC1726q = this.f18353A;
        if (enumC1726q == null) {
            if (enumC1726q != null) {
                throw new IllegalStateException("Re-initializing focus target node.");
            }
            t4.e eVarE = AbstractC1713d.E(this);
            try {
                if (eVarE.f22462m) {
                    t4.e.b(eVarE);
                }
                eVarE.f22462m = true;
                M0((K0(this) && J0(this)) ? EnumC1726q.f18351m : EnumC1726q.f18352n);
                t4.e.c(eVarE);
            } catch (Throwable th) {
                t4.e.c(eVarE);
                throw th;
            }
        }
        int iOrdinal = I0().ordinal();
        if (iOrdinal == 0 || iOrdinal == 2) {
            z zVar = new z();
            AbstractC0156f.q(this, new C1134u(zVar, 2, this));
            Object obj = zVar.l;
            if (obj == null) {
                A7.m.j("focusProperties");
                throw null;
            }
            if (((InterfaceC1718i) obj).a()) {
                return;
            }
            ((androidx.compose.ui.focus.b) AbstractC0156f.u(this).getFocusOwner()).b(8, true, true);
        }
    }

    public final void M0(EnumC1726q enumC1726q) {
        ((C2484x) AbstractC1713d.E(this).f22463n).i(this, enumC1726q);
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
