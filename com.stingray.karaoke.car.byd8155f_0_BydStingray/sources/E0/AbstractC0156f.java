package E0;

import C0.C0107n;
import T.AbstractC0750k0;
import T.C0735d;
import android.view.View;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import y0.C2733k;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: E0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0156f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0154d f1666a = new C0154d(0);

    public static final long a(float f10, boolean z6) {
        return ((z6 ? 1L : 0L) & 4294967295L) | (((long) Float.floatToIntBits(f10)) << 32);
    }

    public static final void b(V.d dVar, g0.n nVar) {
        V.d dVarL = t(nVar).L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            int i10 = i7 - 1;
            Object[] objArr = dVarL.l;
            do {
                dVar.b((g0.n) ((F) objArr[i10]).f1469H.f1603f);
                i10--;
            } while (i10 >= 0);
        }
    }

    public static final int c(Q q4, C0107n c0107n) {
        Q qV0 = q4.v0();
        if (qV0 == null) {
            l9.d.h0("Child of " + q4 + " cannot be null when calculating alignment line");
            throw null;
        }
        if (q4.z0().a().containsKey(c0107n)) {
            Integer num = (Integer) q4.z0().a().get(c0107n);
            if (num != null) {
                return num.intValue();
            }
            return Integer.MIN_VALUE;
        }
        int iE0 = qV0.e0(c0107n);
        if (iE0 == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        qV0.f1576r = true;
        q4.f1577s = true;
        q4.D0();
        qV0.f1576r = false;
        q4.f1577s = false;
        return iE0 + ((int) (c0107n instanceof C0107n ? qV0.B0() & 4294967295L : qV0.B0() >> 32));
    }

    public static final boolean d(C0153c c0153c) {
        q0 q0Var = (q0) t(c0153c).f1469H.f1602e;
        A7.m.d(q0Var, "null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode");
        return q0Var.f1694y;
    }

    public static final g0.n e(InterfaceC0163m interfaceC0163m, int i7) {
        g0.n nVar = ((g0.n) interfaceC0163m).l.f14688q;
        if (nVar == null || (nVar.o & i7) == 0) {
            return null;
        }
        while (nVar != null) {
            int i10 = nVar.f14686n;
            if ((i10 & 2) != 0) {
                return null;
            }
            if ((i10 & i7) != 0) {
                return nVar;
            }
            nVar = nVar.f14688q;
        }
        return null;
    }

    public static final g0.n f(V.d dVar) {
        if (dVar == null || dVar.l()) {
            return null;
        }
        return (g0.n) dVar.o(dVar.f9279n - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final InterfaceC0174y g(g0.n nVar) {
        if ((nVar.f14686n & 2) != 0) {
            if (nVar instanceof InterfaceC0174y) {
                return (InterfaceC0174y) nVar;
            }
            if (nVar instanceof AbstractC0164n) {
                g0.n nVar2 = ((AbstractC0164n) nVar).f1690z;
                while (nVar2 != 0) {
                    if (nVar2 instanceof InterfaceC0174y) {
                        return (InterfaceC0174y) nVar2;
                    }
                    nVar2 = (!(nVar2 instanceof AbstractC0164n) || (nVar2.f14686n & 2) == 0) ? nVar2.f14688q : ((AbstractC0164n) nVar2).f1690z;
                }
            }
        }
        return null;
    }

    public static final int h(long j3, long j10) {
        boolean z6 = ((int) (j3 & 4294967295L)) != 0;
        return z6 != (((int) (4294967295L & j10)) != 0) ? z6 ? -1 : 1 : (int) Math.signum(Float.intBitsToFloat((int) (j3 >> 32)) - Float.intBitsToFloat((int) (j10 >> 32)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object i(InterfaceC0162l interfaceC0162l, AbstractC0750k0 abstractC0750k0) {
        if (!((g0.n) interfaceC0162l).l.f14695x) {
            l9.d.h0("Cannot read CompositionLocal because the Modifier node is not currently attached.");
            throw null;
        }
        b0.e eVar = (b0.e) t(interfaceC0162l).f1467F;
        eVar.getClass();
        return C0735d.P(eVar, abstractC0750k0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v6 */
    public static final s0 j(InterfaceC0163m interfaceC0163m, Object obj) {
        Y y10;
        g0.n nVar = ((g0.n) interfaceC0163m).l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = t(interfaceC0163m);
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 262144) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 262144) != 0) {
                        ?? F9 = nVar2;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            if (F9 instanceof s0) {
                                s0 s0Var = (s0) F9;
                                if (obj.equals(s0Var.r())) {
                                    return s0Var;
                                }
                            } else if ((F9.f14686n & 262144) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar = dVar;
                                while (nVar3 != null) {
                                    if ((nVar3.f14686n & 262144) != 0) {
                                        i7++;
                                        dVar = dVar;
                                        if (i7 == 1) {
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
                                if (i7 == 1) {
                                }
                            }
                            F9 = f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [E0.m, E0.s0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v7 */
    public static final s0 k(s0 s0Var) {
        Y y10;
        g0.n nVar = ((g0.n) s0Var).l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = t(s0Var);
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 262144) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 262144) != 0) {
                        ?? F9 = nVar2;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            if (F9 instanceof s0) {
                                s0 s0Var2 = (s0) F9;
                                if (A7.m.a(s0Var.r(), s0Var2.r()) && g0.a.a(s0Var, s0Var2)) {
                                    return s0Var2;
                                }
                            } else if ((F9.f14686n & 262144) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar = dVar;
                                while (nVar3 != null) {
                                    if ((nVar3.f14686n & 262144) != 0) {
                                        i7++;
                                        dVar = dVar;
                                        if (i7 == 1) {
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
                                if (i7 == 1) {
                                }
                            }
                            F9 = f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        return null;
    }

    public static final int l(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void m(InterfaceC0165o interfaceC0165o) {
        if (((g0.n) interfaceC0165o).l.f14695x) {
            r(interfaceC0165o, 1).W0();
        }
    }

    public static final void n(InterfaceC0174y interfaceC0174y) {
        t(interfaceC0174y).Q();
    }

    public static final void o(p0 p0Var) {
        t(p0Var).T();
    }

    public static final boolean p(F f10) {
        if (f10.f1482n != null) {
            F fG = f10.G();
            if ((fG != null ? fG.f1482n : null) == null || f10.t().f1556b) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void q(g0.n nVar, InterfaceC2772a interfaceC2772a) {
        g0 g0Var = nVar.f14689r;
        if (g0Var == null) {
            g0Var = new g0((f0) nVar);
            nVar.f14689r = g0Var;
        }
        u(nVar).getSnapshotObserver().a(g0Var, C0155e.f1655r, interfaceC2772a);
    }

    public static final d0 r(InterfaceC0163m interfaceC0163m, int i7) {
        d0 d0Var = ((g0.n) interfaceC0163m).l.f14690s;
        A7.m.c(d0Var);
        if (d0Var.Q0() != interfaceC0163m || !e0.h(i7)) {
            return d0Var;
        }
        d0 d0Var2 = d0Var.f1649x;
        A7.m.c(d0Var2);
        return d0Var2;
    }

    public static final d0 s(InterfaceC0163m interfaceC0163m) {
        if (!((g0.n) interfaceC0163m).l.f14695x) {
            l9.d.h0("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
            throw null;
        }
        d0 d0VarR = r(interfaceC0163m, 2);
        if (d0VarR.Q0().f14695x) {
            return d0VarR;
        }
        l9.d.h0("LayoutCoordinates is not attached.");
        throw null;
    }

    public static final F t(InterfaceC0163m interfaceC0163m) {
        d0 d0Var = ((g0.n) interfaceC0163m).l.f14690s;
        if (d0Var != null) {
            return d0Var.f1648w;
        }
        l9.d.i0("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        throw null;
    }

    public static final Owner u(InterfaceC0163m interfaceC0163m) {
        AndroidComposeView androidComposeView = t(interfaceC0163m).f1487t;
        if (androidComposeView != null) {
            return androidComposeView;
        }
        l9.d.i0("This node does not have an owner.");
        throw null;
    }

    public static final View v(InterfaceC0163m interfaceC0163m) {
        if (((g0.n) interfaceC0163m).l.f14695x) {
            return (View) I.a(t(interfaceC0163m));
        }
        l9.d.h0("Cannot get View because the Modifier node is not currently attached.");
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [z7.k] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static final void w(C2733k c2733k, InterfaceC2782k interfaceC2782k) {
        Y y10;
        g0.n nVar = c2733k.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = t(c2733k);
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 262144) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 262144) != 0) {
                        ?? F9 = nVar2;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            boolean zBooleanValue = true;
                            if (F9 instanceof s0) {
                                s0 s0Var = (s0) F9;
                                if ("androidx.compose.ui.input.pointer.PointerHoverIcon".equals(s0Var.r()) && g0.a.a(c2733k, s0Var)) {
                                    zBooleanValue = ((Boolean) interfaceC2782k.b(s0Var)).booleanValue();
                                }
                                if (!zBooleanValue) {
                                    return;
                                }
                            } else {
                                if (((F9.f14686n & 262144) != 0) && (F9 instanceof AbstractC0164n)) {
                                    g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                    int i7 = 0;
                                    F9 = F9;
                                    dVar = dVar;
                                    while (nVar3 != null) {
                                        if ((nVar3.f14686n & 262144) != 0) {
                                            i7++;
                                            dVar = dVar;
                                            if (i7 == 1) {
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
                                    if (i7 == 1) {
                                    }
                                }
                            }
                            F9 = f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [E0.s0, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v0, types: [z7.k] */
    /* JADX WARN: Type inference failed for: r7v10, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static final void x(s0 s0Var, InterfaceC2782k interfaceC2782k) {
        g0.n nVar = ((g0.n) s0Var).l;
        if (!nVar.f14695x) {
            l9.d.h0("visitSubtreeIf called on an unattached node");
            throw null;
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            b(dVar, nVar);
        } else {
            dVar.b(nVar2);
        }
        while (dVar.m()) {
            g0.n nVar3 = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVar3.o & 262144) != 0) {
                for (g0.n nVar4 = nVar3; nVar4 != null; nVar4 = nVar4.f14688q) {
                    if ((nVar4.f14686n & 262144) != 0) {
                        ?? dVar2 = 0;
                        ?? F9 = nVar4;
                        while (F9 != 0) {
                            if (F9 instanceof s0) {
                                s0 s0Var2 = (s0) F9;
                                r0 r0Var = (A7.m.a(s0Var.r(), s0Var2.r()) && g0.a.a(s0Var, s0Var2)) ? (r0) interfaceC2782k.b(s0Var2) : r0.l;
                                if (r0Var == r0.f1698n) {
                                    return;
                                }
                                if (r0Var == r0.f1697m) {
                                    break;
                                }
                            } else if ((F9.f14686n & 262144) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar5 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar2 = dVar2;
                                while (nVar5 != null) {
                                    if ((nVar5.f14686n & 262144) != 0) {
                                        i7++;
                                        dVar2 = dVar2;
                                        if (i7 == 1) {
                                            F9 = nVar5;
                                        } else {
                                            if (dVar2 == 0) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (F9 != 0) {
                                                dVar2.b(F9);
                                                F9 = 0;
                                            }
                                            dVar2.b(nVar5);
                                        }
                                    }
                                    nVar5 = nVar5.f14688q;
                                    F9 = F9;
                                    dVar2 = dVar2;
                                }
                                if (i7 == 1) {
                                }
                            }
                            F9 = f(dVar2);
                        }
                    }
                }
            }
            b(dVar, nVar3);
        }
    }
}
