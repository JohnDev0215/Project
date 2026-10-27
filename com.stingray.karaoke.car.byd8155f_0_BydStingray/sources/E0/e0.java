package E0;

import C0.InterfaceC0115w;
import D.AbstractC0143z;
import G.C0293c;
import G.C0306p;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import k0.InterfaceC1593e;
import l0.C1714e;
import l0.C1727r;
import l0.InterfaceC1712c;
import l0.InterfaceC1721l;
import u.AbstractC2454B;
import u.C2481u;

/* JADX INFO: loaded from: classes.dex */
public abstract class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2481u f1665a;

    static {
        C2481u c2481u = AbstractC2454B.f22582a;
        f1665a = new C2481u();
    }

    public static final void a(g0.n nVar) {
        if (nVar.f14695x) {
            b(nVar, -1, 1);
        } else {
            l9.d.h0("autoInvalidateInsertedNode called on unattached node");
            throw null;
        }
    }

    public static final void b(g0.n nVar, int i7, int i10) {
        if (!(nVar instanceof AbstractC0164n)) {
            c(nVar, i7 & nVar.f14686n, i10);
            return;
        }
        AbstractC0164n abstractC0164n = (AbstractC0164n) nVar;
        c(nVar, abstractC0164n.f1689y & i7, i10);
        int i11 = (~abstractC0164n.f1689y) & i7;
        for (g0.n nVar2 = abstractC0164n.f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
            b(nVar2, i11, i10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void c(g0.n nVar, int i7, int i10) {
        if (i10 != 0 || nVar.w0()) {
            if ((i7 & 2) != 0 && (nVar instanceof InterfaceC0174y)) {
                AbstractC0156f.n((InterfaceC0174y) nVar);
                if (i10 == 2) {
                    d0 d0VarR = AbstractC0156f.r(nVar, 2);
                    d0VarR.f1651z = true;
                    d0VarR.f1644M.a();
                    if (d0VarR.f1646O != null) {
                        if (d0VarR.f1647P != null) {
                            d0VarR.f1647P = null;
                        }
                        d0VarR.l1(null, false);
                        d0VarR.f1648w.r0(false);
                    }
                }
            }
            if ((i7 & 128) != 0 && (nVar instanceof InterfaceC0173x) && i10 != 2) {
                AbstractC0156f.t(nVar).Q();
            }
            if ((i7 & 256) != 0 && (nVar instanceof InterfaceC0166p) && i10 != 2) {
                AbstractC0156f.t(nVar).R();
            }
            if ((i7 & 4) != 0 && (nVar instanceof InterfaceC0165o)) {
                AbstractC0156f.m((InterfaceC0165o) nVar);
            }
            if ((i7 & 8) != 0 && (nVar instanceof p0)) {
                AbstractC0156f.o((p0) nVar);
            }
            if ((i7 & 64) != 0 && (nVar instanceof l0)) {
                AbstractC0156f.t((l0) nVar).S();
            }
            if ((i7 & 1024) != 0 && (nVar instanceof C1727r) && i10 != 2) {
                C1727r c1727r = (C1727r) nVar;
                C1714e c1714e = ((androidx.compose.ui.focus.b) AbstractC0156f.u(c1727r).getFocusOwner()).f11570g;
                c1714e.b(c1714e.c, c1727r);
            }
            if ((i7 & 2048) != 0 && (nVar instanceof InterfaceC1721l)) {
                InterfaceC1721l interfaceC1721l = (InterfaceC1721l) nVar;
                C0157g.f1668b = null;
                interfaceC1721l.o(C0157g.f1667a);
                if (C0157g.f1668b != null) {
                    if (i10 == 2) {
                        g0.n nVar2 = ((g0.n) interfaceC1721l).l;
                        if (!nVar2.f14695x) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        V.d dVar = new V.d(new g0.n[16]);
                        g0.n nVar3 = nVar2.f14688q;
                        if (nVar3 == null) {
                            AbstractC0156f.b(dVar, nVar2);
                        } else {
                            dVar.b(nVar3);
                        }
                        while (dVar.m()) {
                            g0.n nVarF = (g0.n) dVar.o(dVar.f9279n - 1);
                            if ((nVarF.o & 1024) == 0) {
                                AbstractC0156f.b(dVar, nVarF);
                            } else {
                                while (true) {
                                    if (nVarF == null) {
                                        break;
                                    }
                                    if ((nVarF.f14686n & 1024) != 0) {
                                        V.d dVar2 = null;
                                        while (nVarF != null) {
                                            if (nVarF instanceof C1727r) {
                                                C1727r c1727r2 = (C1727r) nVarF;
                                                C1714e c1714e2 = ((androidx.compose.ui.focus.b) AbstractC0156f.u(c1727r2).getFocusOwner()).f11570g;
                                                c1714e2.b(c1714e2.c, c1727r2);
                                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                                int i11 = 0;
                                                for (g0.n nVar4 = ((AbstractC0164n) nVarF).f1690z; nVar4 != null; nVar4 = nVar4.f14688q) {
                                                    if ((nVar4.f14686n & 1024) != 0) {
                                                        i11++;
                                                        if (i11 == 1) {
                                                            nVarF = nVar4;
                                                        } else {
                                                            if (dVar2 == null) {
                                                                dVar2 = new V.d(new g0.n[16]);
                                                            }
                                                            if (nVarF != null) {
                                                                dVar2.b(nVarF);
                                                                nVarF = null;
                                                            }
                                                            dVar2.b(nVar4);
                                                        }
                                                    }
                                                }
                                                if (i11 == 1) {
                                                }
                                            }
                                            nVarF = AbstractC0156f.f(dVar2);
                                        }
                                    } else {
                                        nVarF = nVarF.f14688q;
                                    }
                                }
                            }
                        }
                    } else {
                        C1714e c1714e3 = ((androidx.compose.ui.focus.b) AbstractC0156f.u(interfaceC1721l).getFocusOwner()).f11570g;
                        c1714e3.b(c1714e3.f18329e, interfaceC1721l);
                    }
                }
            }
            if ((i7 & 4096) == 0 || !(nVar instanceof InterfaceC1712c)) {
                return;
            }
            InterfaceC1712c interfaceC1712c = (InterfaceC1712c) nVar;
            C1714e c1714e4 = ((androidx.compose.ui.focus.b) AbstractC0156f.u(interfaceC1712c).getFocusOwner()).f11570g;
            c1714e4.b(c1714e4.d, interfaceC1712c);
        }
    }

    public static final void d(g0.n nVar) {
        if (nVar.f14695x) {
            b(nVar, -1, 0);
        } else {
            l9.d.h0("autoInvalidateUpdatedNode called on unattached node");
            throw null;
        }
    }

    public static final int e(g0.m mVar) {
        int i7 = mVar instanceof InterfaceC0115w ? 3 : 1;
        if (mVar instanceof InterfaceC1593e) {
            i7 |= 4;
        }
        if (mVar instanceof AppendedSemanticsElement) {
            i7 |= 8;
        }
        if (mVar instanceof y0.t) {
            i7 |= 16;
        }
        if ((mVar instanceof AbstractC0143z) || (mVar instanceof C0306p)) {
            i7 |= 32;
        }
        if (mVar instanceof C0293c) {
            i7 |= 256;
        }
        return mVar instanceof C0.S ? i7 | 64 : i7;
    }

    public static final int f(g0.n nVar) {
        int i7 = nVar.f14686n;
        if (i7 != 0) {
            return i7;
        }
        Class<?> cls = nVar.getClass();
        C2481u c2481u = f1665a;
        int iC = c2481u.c(cls);
        if (iC >= 0) {
            return c2481u.c[iC];
        }
        int i10 = nVar instanceof InterfaceC0174y ? 3 : 1;
        if (nVar instanceof InterfaceC0165o) {
            i10 |= 4;
        }
        if (nVar instanceof p0) {
            i10 |= 8;
        }
        if (nVar instanceof n0) {
            i10 |= 16;
        }
        if (nVar instanceof D0.d) {
            i10 |= 32;
        }
        if (nVar instanceof l0) {
            i10 |= 64;
        }
        if (nVar instanceof InterfaceC0173x) {
            i10 |= 128;
        }
        if (nVar instanceof InterfaceC0166p) {
            i10 |= 256;
        }
        if (nVar instanceof C1727r) {
            i10 |= 1024;
        }
        if (nVar instanceof InterfaceC1721l) {
            i10 |= 2048;
        }
        if (nVar instanceof InterfaceC1712c) {
            i10 |= 4096;
        }
        if (nVar instanceof w0.d) {
            i10 |= 8192;
        }
        if (nVar instanceof A0.a) {
            i10 |= 16384;
        }
        if (nVar instanceof InterfaceC0162l) {
            i10 |= 32768;
        }
        int i11 = nVar instanceof s0 ? 262144 | i10 : i10;
        c2481u.f(i11, cls);
        return i11;
    }

    public static final int g(g0.n nVar) {
        if (!(nVar instanceof AbstractC0164n)) {
            return f(nVar);
        }
        AbstractC0164n abstractC0164n = (AbstractC0164n) nVar;
        int iG = abstractC0164n.f1689y;
        for (g0.n nVar2 = abstractC0164n.f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
            iG |= g(nVar2);
        }
        return iG;
    }

    public static final boolean h(int i7) {
        return (i7 & 128) != 0;
    }
}
