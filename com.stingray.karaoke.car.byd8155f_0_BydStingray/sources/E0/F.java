package E0;

import A.I0;
import F0.W0;
import T.C0738e0;
import T.C0770v;
import T.InterfaceC0749k;
import T.InterfaceC0771w;
import androidx.compose.ui.platform.AndroidComposeView;
import d1.C1058b;
import d1.C1059c;
import io.sentry.C1489n1;
import java.util.List;
import k0.C1590b;
import n0.C1863M;
import n0.InterfaceC1889r;
import q0.C2164c;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class F implements InterfaceC0749k, j0, InterfaceC0161k {

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final C f1460T = new C("Undefined intrinsics block and it is required");

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final B f1461U = new B();

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public static final C1.b f1462V = new C1.b(1);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C0.K f1463A;
    public C1489n1 B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public a1.b f1464C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public a1.k f1465D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public W0 f1466E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public InterfaceC0771w f1467F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f1468G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Y f1469H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public C0.G f1470I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public d0 f1471J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f1472K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public g0.o f1473L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public g0.o f1474M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C1058b f1475N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public C1059c f1476O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f1477P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f1478Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public int f1479R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public int f1480S;
    public final boolean l;
    private final N layoutDelegate;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1481m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public F f1482n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f1483p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public V.d f1484q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1485r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public F f1486s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AndroidComposeView f1487t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public d1.o f1488u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1489v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1490w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public L0.j f1491x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final V.d f1492y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1493z;

    public F(int i7, int i10, boolean z6) {
        this(L0.k.f3911a.addAndGet(1), (i7 & 1) != 0 ? false : z6);
    }

    public static boolean a0(F f10) {
        K k10 = f10.layoutDelegate.f1569s;
        return f10.Z(k10 != null ? k10.f1522x : null);
    }

    public static boolean l0(F f10) {
        L l = f10.layoutDelegate.f1568r;
        return f10.k0(l.f1546t ? new a1.a(l.o) : null);
    }

    public static void q0(F f10, boolean z6, int i7) {
        F fG;
        if ((i7 & 1) != 0) {
            z6 = false;
        }
        boolean z10 = (i7 & 2) != 0;
        boolean z11 = (i7 & 4) != 0;
        if (f10.f1482n == null) {
            l9.d.h0("Lookahead measure cannot be requested on a node that is not a part of theLookaheadScope");
            throw null;
        }
        AndroidComposeView androidComposeView = f10.f1487t;
        if (androidComposeView == null || f10.f1490w || f10.l) {
            return;
        }
        androidComposeView.v(f10, true, z6, z10);
        if (z11) {
            K k10 = f10.layoutDelegate.f1569s;
            A7.m.c(k10);
            N n10 = k10.f1514J;
            F fG2 = n10.f1555a.G();
            int i10 = n10.f1555a.f1479R;
            if (fG2 == null || i10 == 3) {
                return;
            }
            while (fG2.f1479R == i10 && (fG = fG2.G()) != null) {
                fG2 = fG;
            }
            int iC = AbstractC2641j.c(i10);
            if (iC == 0) {
                if (fG2.f1482n != null) {
                    q0(fG2, z6, 6);
                    return;
                } else {
                    s0(fG2, z6, 6);
                    return;
                }
            }
            if (iC != 1) {
                throw new IllegalStateException("Intrinsics isn't used by the parent");
            }
            if (fG2.f1482n != null) {
                fG2.p0(z6);
            } else {
                fG2.r0(z6);
            }
        }
    }

    public static void s0(F f10, boolean z6, int i7) {
        AndroidComposeView androidComposeView;
        F fG;
        if ((i7 & 1) != 0) {
            z6 = false;
        }
        boolean z10 = (i7 & 2) != 0;
        boolean z11 = (i7 & 4) != 0;
        if (f10.f1490w || f10.l || (androidComposeView = f10.f1487t) == null) {
            return;
        }
        androidComposeView.v(f10, false, z6, z10);
        if (z11) {
            N n10 = f10.layoutDelegate.f1568r.f1542S;
            F fG2 = n10.f1555a.G();
            int i10 = n10.f1555a.f1479R;
            if (fG2 == null || i10 == 3) {
                return;
            }
            while (fG2.f1479R == i10 && (fG = fG2.G()) != null) {
                fG2 = fG;
            }
            int iC = AbstractC2641j.c(i10);
            if (iC == 0) {
                s0(fG2, z6, 6);
            } else {
                if (iC != 1) {
                    throw new IllegalStateException("Intrinsics isn't used by the parent");
                }
                fG2.r0(z6);
            }
        }
    }

    public static void t0(F f10) {
        if (E.f1459a[AbstractC2641j.c(f10.layoutDelegate.c)] != 1) {
            throw new IllegalStateException("Unexpected state ".concat(A7.k.v(f10.layoutDelegate.c)));
        }
        N n10 = f10.layoutDelegate;
        if (n10.f1559g) {
            q0(f10, true, 6);
            return;
        }
        if (n10.f1560h) {
            f10.p0(true);
        }
        N n11 = f10.layoutDelegate;
        if (n11.d) {
            s0(f10, true, 6);
        } else if (n11.f1557e) {
            f10.r0(true);
        }
    }

    @Override // E0.j0
    public final boolean A() {
        return V();
    }

    public final boolean B() {
        return this.layoutDelegate.d;
    }

    public final int C() {
        return this.layoutDelegate.f1568r.f1548v;
    }

    public final int D() {
        int i7;
        K k10 = this.layoutDelegate.f1569s;
        if (k10 == null || (i7 = k10.f1518t) == 0) {
            return 3;
        }
        return i7;
    }

    public final List E() {
        Y y10 = this.f1469H;
        V.d dVar = (V.d) y10.f1604g;
        if (dVar == null) {
            return n7.u.l;
        }
        V.d dVar2 = new V.d(new C0.N[dVar.f9279n]);
        g0.n nVar = (g0.n) y10.f1603f;
        int i7 = 0;
        while (nVar != null) {
            q0 q0Var = (q0) y10.f1602e;
            if (nVar == q0Var) {
                break;
            }
            d0 d0Var = nVar.f14690s;
            if (d0Var == null) {
                throw new IllegalArgumentException("getModifierInfo called on node with no coordinator");
            }
            i0 i0Var = d0Var.f1646O;
            i0 i0Var2 = ((C0170u) y10.c).f1646O;
            g0.n nVar2 = nVar.f14688q;
            if (nVar2 != q0Var || d0Var == nVar2.f14690s) {
                i0Var2 = null;
            }
            if (i0Var == null) {
                i0Var = i0Var2;
            }
            dVar2.b(new C0.N((g0.o) dVar.l[i7], d0Var, i0Var));
            nVar = nVar.f14688q;
            i7++;
        }
        return dVar2.f();
    }

    public final C1489n1 F() {
        C1489n1 c1489n1 = this.B;
        if (c1489n1 != null) {
            return c1489n1;
        }
        C1489n1 c1489n12 = new C1489n1(this, this.f1463A);
        this.B = c1489n12;
        return c1489n12;
    }

    public final F G() {
        F f10 = this.f1486s;
        while (f10 != null && f10.l) {
            f10 = f10.f1486s;
        }
        return f10;
    }

    public final int H() {
        return this.layoutDelegate.f1568r.f1545s;
    }

    public final int I() {
        return this.layoutDelegate.f1568r.l;
    }

    public final float J() {
        return this.layoutDelegate.f1568r.f1534K;
    }

    public final V.d K() {
        boolean z6 = this.f1493z;
        V.d dVar = this.f1492y;
        if (z6) {
            dVar.g();
            dVar.c(dVar.f9279n, L());
            dVar.q(f1462V);
            this.f1493z = false;
        }
        return dVar;
    }

    public final V.d L() {
        z0();
        if (this.o == 0) {
            return (V.d) this.f1483p.f16569m;
        }
        V.d dVar = this.f1484q;
        A7.m.c(dVar);
        return dVar;
    }

    public final void M(long j3, C0168s c0168s, boolean z6, boolean z10) {
        Y y10 = this.f1469H;
        d0 d0Var = (d0) y10.d;
        C1863M c1863m = d0.f1628Q;
        ((d0) y10.d).U0(d0.f1631T, d0Var.N0(j3, true), c0168s, z6, z10);
    }

    public final void N(int i7, F f10) {
        if (!(f10.f1486s == null)) {
            StringBuilder sb = new StringBuilder("Cannot insert ");
            sb.append(f10);
            sb.append(" because it already has a parent. This tree: ");
            sb.append(h(0));
            sb.append(" Other tree: ");
            F f11 = f10.f1486s;
            sb.append(f11 != null ? f11.h(0) : null);
            l9.d.h0(sb.toString());
            throw null;
        }
        if (f10.f1487t != null) {
            l9.d.h0("Cannot insert " + f10 + " because it already has an owner. This tree: " + h(0) + " Other tree: " + f10.h(0));
            throw null;
        }
        f10.f1486s = this;
        io.sentry.internal.debugmeta.c cVar = this.f1483p;
        ((V.d) cVar.f16569m).a(i7, f10);
        ((I0) cVar.f16570n).a();
        i0();
        if (f10.l) {
            this.o++;
        }
        U();
        AndroidComposeView androidComposeView = this.f1487t;
        if (androidComposeView != null) {
            f10.b(androidComposeView);
        }
        if (f10.layoutDelegate.f1565n > 0) {
            N n10 = this.layoutDelegate;
            n10.b(n10.f1565n + 1);
        }
    }

    public final void O() {
        if (this.f1472K) {
            Y y10 = this.f1469H;
            d0 d0Var = (C0170u) y10.c;
            d0 d0Var2 = ((d0) y10.d).f1650y;
            this.f1471J = null;
            while (true) {
                if (A7.m.a(d0Var, d0Var2)) {
                    break;
                }
                if ((d0Var != null ? d0Var.f1646O : null) != null) {
                    this.f1471J = d0Var;
                    break;
                }
                d0Var = d0Var != null ? d0Var.f1650y : null;
            }
        }
        d0 d0Var3 = this.f1471J;
        if (d0Var3 != null && d0Var3.f1646O == null) {
            l9.d.i0("layer was not set");
            throw null;
        }
        if (d0Var3 != null) {
            d0Var3.W0();
            return;
        }
        F fG = G();
        if (fG != null) {
            fG.O();
        }
    }

    public final void P() {
        Y y10 = this.f1469H;
        d0 d0Var = (d0) y10.d;
        C0170u c0170u = (C0170u) y10.c;
        while (d0Var != c0170u) {
            A7.m.d(d0Var, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
            A a10 = (A) d0Var;
            i0 i0Var = a10.f1646O;
            if (i0Var != null) {
                i0Var.invalidate();
            }
            d0Var = a10.f1649x;
        }
        i0 i0Var2 = ((C0170u) y10.c).f1646O;
        if (i0Var2 != null) {
            i0Var2.invalidate();
        }
    }

    public final void Q() {
        if (this.f1482n != null) {
            q0(this, false, 7);
        } else {
            s0(this, false, 7);
        }
    }

    public final void R() {
        N n10 = this.layoutDelegate;
        if (n10.f1557e || n10.d || this.f1477P) {
            return;
        }
        AndroidComposeView androidComposeView = (AndroidComposeView) I.a(this);
        ((V.d) androidComposeView.f11643S.f1590e.l).b(this);
        this.f1477P = true;
        androidComposeView.A(null);
    }

    public final void S() {
        N n10 = this.layoutDelegate;
        n10.f1568r.B = true;
        K k10 = n10.f1569s;
        if (k10 != null) {
            k10.f1511G = true;
        }
    }

    public final void T() {
        this.f1491x = null;
        ((AndroidComposeView) I.a(this)).x();
    }

    public final void U() {
        F f10;
        if (this.o > 0) {
            this.f1485r = true;
        }
        if (!this.l || (f10 = this.f1486s) == null) {
            return;
        }
        f10.U();
    }

    public final boolean V() {
        return this.f1487t != null;
    }

    public final boolean W() {
        return this.layoutDelegate.f1568r.f1527D;
    }

    public final boolean X() {
        return this.layoutDelegate.f1568r.f1528E;
    }

    public final Boolean Y() {
        K k10 = this.layoutDelegate.f1569s;
        if (k10 != null) {
            return Boolean.valueOf(k10.B);
        }
        return null;
    }

    public final boolean Z(a1.a aVar) {
        if (aVar == null || this.f1482n == null) {
            return false;
        }
        K k10 = this.layoutDelegate.f1569s;
        A7.m.c(k10);
        return k10.z0(aVar.f11027a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b9, code lost:
    
        r4 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bf, code lost:
    
        if (r3 >= r1) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c1, code lost:
    
        if (r8 == null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c3, code lost:
    
        if (r4 == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c7, code lost:
    
        if (r5.f1474M == null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c9, code lost:
    
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ca, code lost:
    
        r6.j(r3, r8, r9, r4, r0 ^ 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d6, code lost:
    
        l9.d.i0("structuralUpdate requires a non-null tail");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00db, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00dc, code lost:
    
        l9.d.i0("expected prior modifier list to be non-empty");
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00df, code lost:
    
        throw null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(g0.o r15) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.F.a(g0.o):void");
    }

    public final void b(AndroidComposeView androidComposeView) {
        F f10;
        if (!(this.f1487t == null)) {
            l9.d.h0("Cannot attach " + this + " as it already is attached.  Tree: " + h(0));
            throw null;
        }
        F f11 = this.f1486s;
        if (f11 != null && !A7.m.a(f11.f1487t, androidComposeView)) {
            StringBuilder sb = new StringBuilder("Attaching to a different owner(");
            sb.append(androidComposeView);
            sb.append(") than the parent's owner(");
            F fG = G();
            sb.append(fG != null ? fG.f1487t : null);
            sb.append("). This tree: ");
            sb.append(h(0));
            sb.append(" Parent tree: ");
            F f12 = this.f1486s;
            sb.append(f12 != null ? f12.h(0) : null);
            l9.d.h0(sb.toString());
            throw null;
        }
        F fG2 = G();
        if (fG2 == null) {
            N n10 = this.layoutDelegate;
            n10.f1568r.f1527D = true;
            K k10 = n10.f1569s;
            if (k10 != null) {
                k10.B = true;
            }
        }
        Y y10 = this.f1469H;
        ((d0) y10.d).f1650y = fG2 != null ? (C0170u) fG2.f1469H.c : null;
        this.f1487t = androidComposeView;
        this.f1489v = (fG2 != null ? fG2.f1489v : -1) + 1;
        g0.o oVar = this.f1474M;
        if (oVar != null) {
            a(oVar);
        }
        this.f1474M = null;
        if (y10.f(8)) {
            T();
        }
        androidComposeView.getClass();
        F f13 = this.f1486s;
        if (f13 == null || (f10 = f13.f1482n) == null) {
            f10 = this.f1482n;
        }
        w0(f10);
        if (this.f1482n == null && y10.f(512)) {
            w0(this);
        }
        if (!this.f1478Q) {
            for (g0.n nVar = (g0.n) y10.f1603f; nVar != null; nVar = nVar.f14688q) {
                nVar.x0();
            }
        }
        V.d dVar = (V.d) this.f1483p.f16569m;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            do {
                ((F) objArr[i10]).b(androidComposeView);
                i10++;
            } while (i10 < i7);
        }
        if (!this.f1478Q) {
            y10.i();
        }
        Q();
        if (fG2 != null) {
            fG2.Q();
        }
        d0 d0Var = ((C0170u) y10.c).f1649x;
        for (d0 d0Var2 = (d0) y10.d; !A7.m.a(d0Var2, d0Var) && d0Var2 != null; d0Var2 = d0Var2.f1649x) {
            d0Var2.l1(d0Var2.B, true);
            i0 i0Var = d0Var2.f1646O;
            if (i0Var != null) {
                i0Var.invalidate();
            }
        }
        C1058b c1058b = this.f1475N;
        if (c1058b != null) {
            c1058b.b(androidComposeView);
        }
        this.layoutDelegate.h();
        if (this.f1478Q) {
            return;
        }
        g0.n nVar2 = (g0.n) y10.f1603f;
        if ((nVar2.o & 7168) != 0) {
            while (nVar2 != null) {
                int i11 = nVar2.f14686n;
                if (((i11 & 4096) != 0) | ((i11 & 1024) != 0) | ((i11 & 2048) != 0)) {
                    e0.a(nVar2);
                }
                nVar2 = nVar2.f14688q;
            }
        }
    }

    public final void b0() {
        F fG;
        if (this.f1479R == 3) {
            g();
        }
        K k10 = this.layoutDelegate.f1569s;
        A7.m.c(k10);
        try {
            k10.f1515q = true;
            if (!k10.f1520v) {
                l9.d.h0("replace() called on item that was not placed");
                throw null;
            }
            k10.f1513I = false;
            boolean z6 = k10.B;
            k10.y0(k10.f1523y, k10.f1524z, k10.f1506A);
            if (z6 && !k10.f1513I && (fG = k10.f1514J.f1555a.G()) != null) {
                fG.p0(false);
            }
        } finally {
            k10.f1515q = false;
        }
    }

    public final void c() {
        this.f1480S = this.f1479R;
        this.f1479R = 3;
        V.d dVarL = L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f10 = (F) objArr[i10];
                if (f10.f1479R != 3) {
                    f10.c();
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final void c0() {
        N n10 = this.layoutDelegate;
        n10.f1557e = true;
        n10.f1558f = true;
    }

    @Override // T.InterfaceC0749k
    public final void d() {
        if (!V()) {
            l9.d.g0("onReuse is only expected on attached node");
            throw null;
        }
        d1.o oVar = this.f1488u;
        if (oVar != null) {
            oVar.d();
        }
        C0.G g7 = this.f1470I;
        if (g7 != null) {
            g7.c(false);
        }
        boolean z6 = this.f1478Q;
        Y y10 = this.f1469H;
        if (z6) {
            this.f1478Q = false;
            T();
        } else {
            for (g0.n nVar = (q0) y10.f1602e; nVar != null; nVar = nVar.f14687p) {
                if (nVar.f14695x) {
                    nVar.C0();
                }
            }
            g0.n nVar2 = (q0) y10.f1602e;
            for (g0.n nVar3 = nVar2; nVar3 != null; nVar3 = nVar3.f14687p) {
                if (nVar3.f14695x) {
                    nVar3.E0();
                }
            }
            while (nVar2 != null) {
                if (nVar2.f14695x) {
                    nVar2.y0();
                }
                nVar2 = nVar2.f14687p;
            }
        }
        this.f1481m = L0.k.f3911a.addAndGet(1);
        for (g0.n nVar4 = (g0.n) y10.f1603f; nVar4 != null; nVar4 = nVar4.f14688q) {
            nVar4.x0();
        }
        y10.i();
        t0(this);
    }

    public final void d0() {
        N n10 = this.layoutDelegate;
        n10.f1560h = true;
        n10.f1561i = true;
    }

    @Override // T.InterfaceC0749k
    public final void e() {
        d1.o oVar = this.f1488u;
        if (oVar != null) {
            oVar.e();
        }
        C0.G g7 = this.f1470I;
        if (g7 != null) {
            g7.e();
        }
        Y y10 = this.f1469H;
        d0 d0Var = ((C0170u) y10.c).f1649x;
        for (d0 d0Var2 = (d0) y10.d; !A7.m.a(d0Var2, d0Var) && d0Var2 != null; d0Var2 = d0Var2.f1649x) {
            d0Var2.f1651z = true;
            d0Var2.f1644M.a();
            if (d0Var2.f1646O != null) {
                if (d0Var2.f1647P != null) {
                    d0Var2.f1647P = null;
                }
                d0Var2.l1(null, false);
                d0Var2.f1648w.r0(false);
            }
        }
    }

    public final void e0() {
        this.layoutDelegate.f1559g = true;
    }

    @Override // T.InterfaceC0749k
    public final void f() {
        d1.o oVar = this.f1488u;
        if (oVar != null) {
            oVar.f();
        }
        C0.G g7 = this.f1470I;
        if (g7 != null) {
            g7.c(true);
        }
        this.f1478Q = true;
        Y y10 = this.f1469H;
        for (g0.n nVar = (q0) y10.f1602e; nVar != null; nVar = nVar.f14687p) {
            if (nVar.f14695x) {
                nVar.C0();
            }
        }
        g0.n nVar2 = (q0) y10.f1602e;
        for (g0.n nVar3 = nVar2; nVar3 != null; nVar3 = nVar3.f14687p) {
            if (nVar3.f14695x) {
                nVar3.E0();
            }
        }
        while (nVar2 != null) {
            if (nVar2.f14695x) {
                nVar2.y0();
            }
            nVar2 = nVar2.f14687p;
        }
        if (V()) {
            T();
        }
    }

    public final void f0() {
        this.layoutDelegate.d = true;
    }

    public final void g() {
        this.f1480S = this.f1479R;
        this.f1479R = 3;
        V.d dVarL = L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f10 = (F) objArr[i10];
                if (f10.f1479R == 2) {
                    f10.g();
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final void g0(int i7, int i10, int i11) {
        if (i7 == i10) {
            return;
        }
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = i7 > i10 ? i7 + i12 : i7;
            int i14 = i7 > i10 ? i10 + i12 : (i10 + i11) - 2;
            io.sentry.internal.debugmeta.c cVar = this.f1483p;
            Object objO = ((V.d) cVar.f16569m).o(i13);
            I0 i02 = (I0) cVar.f16570n;
            i02.a();
            ((V.d) cVar.f16569m).a(i14, (F) objO);
            i02.a();
        }
        i0();
        U();
        Q();
    }

    public final String h(int i7) {
        StringBuilder sb = new StringBuilder();
        for (int i10 = 0; i10 < i7; i10++) {
            sb.append("  ");
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        V.d dVarL = L();
        int i11 = dVarL.f9279n;
        if (i11 > 0) {
            Object[] objArr = dVarL.l;
            int i12 = 0;
            do {
                sb.append(((F) objArr[i12]).h(i7 + 1));
                i12++;
            } while (i12 < i11);
        }
        String string = sb.toString();
        if (i7 != 0) {
            return string;
        }
        String strSubstring = string.substring(0, string.length() - 1);
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final void h0(F f10) {
        if (f10.layoutDelegate.f1565n > 0) {
            this.layoutDelegate.b(r0.f1565n - 1);
        }
        if (this.f1487t != null) {
            f10.i();
        }
        f10.f1486s = null;
        ((d0) f10.f1469H.d).f1650y = null;
        if (f10.l) {
            this.o--;
            V.d dVar = (V.d) f10.f1483p.f16569m;
            int i7 = dVar.f9279n;
            if (i7 > 0) {
                Object[] objArr = dVar.l;
                int i10 = 0;
                do {
                    ((d0) ((F) objArr[i10]).f1469H.d).f1650y = null;
                    i10++;
                } while (i10 < i7);
            }
        }
        U();
        i0();
    }

    public final void i() {
        G g7;
        AndroidComposeView androidComposeView = this.f1487t;
        if (androidComposeView == null) {
            StringBuilder sb = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            F fG = G();
            sb.append(fG != null ? fG.h(0) : null);
            l9.d.i0(sb.toString());
            throw null;
        }
        F fG2 = G();
        if (fG2 != null) {
            fG2.O();
            fG2.Q();
            N n10 = this.layoutDelegate;
            n10.f1568r.f1548v = 3;
            K k10 = n10.f1569s;
            if (k10 != null) {
                k10.f1518t = 3;
            }
        }
        N n11 = this.layoutDelegate;
        G g10 = n11.f1568r.f1529F;
        g10.f1495b = true;
        g10.c = false;
        g10.f1496e = false;
        g10.d = false;
        g10.f1497f = false;
        g10.f1498g = false;
        g10.f1499h = null;
        K k11 = n11.f1569s;
        if (k11 != null && (g7 = k11.f1507C) != null) {
            g7.f1495b = true;
            g7.c = false;
            g7.f1496e = false;
            g7.d = false;
            g7.f1497f = false;
            g7.f1498g = false;
            g7.f1499h = null;
        }
        C1059c c1059c = this.f1476O;
        if (c1059c != null) {
            c1059c.b(androidComposeView);
        }
        Y y10 = this.f1469H;
        if (y10.f(8)) {
            T();
        }
        g0.n nVar = (q0) y10.f1602e;
        for (g0.n nVar2 = nVar; nVar2 != null; nVar2 = nVar2.f14687p) {
            if (nVar2.f14695x) {
                nVar2.E0();
            }
        }
        this.f1490w = true;
        V.d dVar = (V.d) this.f1483p.f16569m;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            do {
                ((F) objArr[i10]).i();
                i10++;
            } while (i10 < i7);
        }
        this.f1490w = false;
        while (nVar != null) {
            if (nVar.f14695x) {
                nVar.y0();
            }
            nVar = nVar.f14687p;
        }
        U u4 = androidComposeView.f11643S;
        M.t tVar = u4.f1589b;
        ((io.sentry.internal.debugmeta.c) tVar.l).R(this);
        ((io.sentry.internal.debugmeta.c) tVar.f4151m).R(this);
        ((V.d) u4.f1590e.l).n(this);
        androidComposeView.f11635K = true;
        this.f1487t = null;
        w0(null);
        this.f1489v = 0;
        N n12 = this.layoutDelegate;
        L l = n12.f1568r;
        l.f1545s = Integer.MAX_VALUE;
        l.f1544r = Integer.MAX_VALUE;
        l.f1527D = false;
        K k12 = n12.f1569s;
        if (k12 != null) {
            k12.f1517s = Integer.MAX_VALUE;
            k12.f1516r = Integer.MAX_VALUE;
            k12.B = false;
        }
    }

    public final void i0() {
        if (!this.l) {
            this.f1493z = true;
            return;
        }
        F fG = G();
        if (fG != null) {
            fG.i0();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v2, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v5 */
    public final void j() {
        N n10 = this.layoutDelegate;
        if (n10.c != 5 || n10.f1557e || n10.d || this.f1478Q || !W()) {
            return;
        }
        g0.n nVar = (g0.n) this.f1469H.f1603f;
        if ((nVar.o & 256) != 0) {
            while (nVar != null) {
                if ((nVar.f14686n & 256) != 0) {
                    ?? F9 = nVar;
                    ?? dVar = 0;
                    while (F9 != 0) {
                        if (F9 instanceof InterfaceC0166p) {
                            InterfaceC0166p interfaceC0166p = (InterfaceC0166p) F9;
                            interfaceC0166p.l0(AbstractC0156f.r(interfaceC0166p, 256));
                        } else if ((F9.f14686n & 256) != 0 && (F9 instanceof AbstractC0164n)) {
                            g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                            int i7 = 0;
                            F9 = F9;
                            dVar = dVar;
                            while (nVar2 != null) {
                                if ((nVar2.f14686n & 256) != 0) {
                                    i7++;
                                    dVar = dVar;
                                    if (i7 == 1) {
                                        F9 = nVar2;
                                    } else {
                                        if (dVar == 0) {
                                            dVar = new V.d(new g0.n[16]);
                                        }
                                        if (F9 != 0) {
                                            dVar.b(F9);
                                            F9 = 0;
                                        }
                                        dVar.b(nVar2);
                                    }
                                }
                                nVar2 = nVar2.f14688q;
                                F9 = F9;
                                dVar = dVar;
                            }
                            if (i7 == 1) {
                            }
                        }
                        F9 = AbstractC0156f.f(dVar);
                    }
                }
                if ((nVar.o & 256) == 0) {
                    return;
                } else {
                    nVar = nVar.f14688q;
                }
            }
        }
    }

    public final void j0() {
        C0.U placementScope;
        C0170u c0170u;
        if (this.f1479R == 3) {
            g();
        }
        F fG = G();
        if (fG == null || (c0170u = (C0170u) fG.f1469H.c) == null || (placementScope = c0170u.f1578t) == null) {
            placementScope = I.a(this).getPlacementScope();
        }
        C0.U.f(placementScope, this.layoutDelegate.f1568r, 0, 0);
    }

    public final void k(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        ((d0) this.f1469H.d).I0(interfaceC1889r, c2164c);
    }

    public final boolean k0(a1.a aVar) {
        if (aVar == null) {
            return false;
        }
        if (this.f1479R == 3) {
            c();
        }
        return this.layoutDelegate.f1568r.B0(aVar.f11027a);
    }

    public final void l() {
        if (this.f1482n != null) {
            q0(this, false, 5);
        } else {
            s0(this, false, 5);
        }
        L l = this.layoutDelegate.f1568r;
        a1.a aVar = l.f1546t ? new a1.a(l.o) : null;
        if (aVar != null) {
            AndroidComposeView androidComposeView = this.f1487t;
            if (androidComposeView != null) {
                androidComposeView.q(this, aVar.f11027a);
                return;
            }
            return;
        }
        AndroidComposeView androidComposeView2 = this.f1487t;
        if (androidComposeView2 != null) {
            androidComposeView2.p(true);
        }
    }

    public final boolean m() {
        G g7;
        N n10 = this.layoutDelegate;
        if (n10.f1568r.f1529F.e()) {
            return true;
        }
        K k10 = n10.f1569s;
        return (k10 == null || (g7 = k10.f1507C) == null || !g7.e()) ? false : true;
    }

    public final void m0() {
        io.sentry.internal.debugmeta.c cVar = this.f1483p;
        int i7 = ((V.d) cVar.f16569m).f9279n;
        while (true) {
            i7--;
            V.d dVar = (V.d) cVar.f16569m;
            if (-1 >= i7) {
                dVar.g();
                ((I0) cVar.f16570n).a();
                return;
            }
            h0((F) dVar.l[i7]);
        }
    }

    public final List n() {
        K k10 = this.layoutDelegate.f1569s;
        A7.m.c(k10);
        N n10 = k10.f1514J;
        n10.f1555a.p();
        boolean z6 = k10.f1509E;
        V.d dVar = k10.f1508D;
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
                    K k11 = f11.layoutDelegate.f1569s;
                    A7.m.c(k11);
                    dVar.b(k11);
                } else {
                    K k12 = f11.layoutDelegate.f1569s;
                    A7.m.c(k12);
                    Object[] objArr2 = dVar.l;
                    Object obj = objArr2[i10];
                    objArr2[i10] = k12;
                }
                i10++;
            } while (i10 < i7);
        }
        dVar.p(((V.a) f10.p()).l.f9279n, dVar.f9279n);
        k10.f1509E = false;
        return dVar.f();
    }

    public final void n0(int i7, int i10) {
        if (i10 < 0) {
            l9.d.g0("count (" + i10 + ") must be greater than 0");
            throw null;
        }
        int i11 = (i10 + i7) - 1;
        if (i7 > i11) {
            return;
        }
        while (true) {
            io.sentry.internal.debugmeta.c cVar = this.f1483p;
            h0((F) ((V.d) cVar.f16569m).l[i11]);
            Object objO = ((V.d) cVar.f16569m).o(i11);
            ((I0) cVar.f16570n).a();
            if (i11 == i7) {
                return;
            } else {
                i11--;
            }
        }
    }

    public final List o() {
        return this.layoutDelegate.f1568r.q0();
    }

    public final void o0() {
        F fG;
        if (this.f1479R == 3) {
            g();
        }
        L l = this.layoutDelegate.f1568r;
        l.getClass();
        try {
            l.f1543q = true;
            if (!l.f1547u) {
                l9.d.h0("replace called on unplaced item");
                throw null;
            }
            boolean z6 = l.f1527D;
            l.z0(l.f1550x, l.f1525A, l.f1551y, l.f1552z);
            if (z6 && !l.f1535L && (fG = l.f1542S.f1555a.G()) != null) {
                fG.r0(false);
            }
        } finally {
            l.f1543q = false;
        }
    }

    public final List p() {
        return L().f();
    }

    public final void p0(boolean z6) {
        AndroidComposeView androidComposeView;
        if (this.l || (androidComposeView = this.f1487t) == null) {
            return;
        }
        androidComposeView.w(this, true, z6);
    }

    public final L0.j q() {
        if (!V() || this.f1478Q) {
            return null;
        }
        if (!this.f1469H.f(8) || this.f1491x != null) {
            return this.f1491x;
        }
        A7.z zVar = new A7.z();
        zVar.l = new L0.j();
        k0 snapshotObserver = I.a(this).getSnapshotObserver();
        snapshotObserver.a(this, snapshotObserver.d, new B8.A(this, 2, zVar));
        L0.j jVar = (L0.j) zVar.l;
        this.f1491x = jVar;
        return jVar;
    }

    public final List r() {
        return ((V.d) this.f1483p.f16569m).f();
    }

    public final void r0(boolean z6) {
        AndroidComposeView androidComposeView;
        if (this.l || (androidComposeView = this.f1487t) == null) {
            return;
        }
        androidComposeView.w(this, false, z6);
    }

    public final int s() {
        return this.layoutDelegate.f1568r.f871m;
    }

    public final N t() {
        return this.layoutDelegate;
    }

    public final String toString() {
        return F0.T.z(this) + " children: " + ((V.a) p()).l.f9279n + " measurePolicy: " + this.f1463A;
    }

    public final boolean u() {
        return this.layoutDelegate.f1557e;
    }

    public final void u0() {
        V.d dVarL = L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                F f10 = (F) objArr[i10];
                int i11 = f10.f1480S;
                f10.f1479R = i11;
                if (i11 != 3) {
                    f10.u0();
                }
                i10++;
            } while (i10 < i7);
        }
    }

    public final int v() {
        return this.layoutDelegate.c;
    }

    public final void v0(a1.b bVar) {
        if (A7.m.a(this.f1464C, bVar)) {
            return;
        }
        this.f1464C = bVar;
        Q();
        F fG = G();
        if (fG != null) {
            fG.O();
        }
        P();
        for (g0.n nVar = (g0.n) this.f1469H.f1603f; nVar != null; nVar = nVar.f14688q) {
            if ((nVar.f14686n & 16) != 0) {
                ((n0) nVar).k();
            } else if (nVar instanceof C1590b) {
                ((C1590b) nVar).H0();
            }
        }
    }

    public final boolean w() {
        return this.layoutDelegate.f1560h;
    }

    public final void w0(F f10) {
        if (A7.m.a(f10, this.f1482n)) {
            return;
        }
        this.f1482n = f10;
        if (f10 != null) {
            N n10 = this.layoutDelegate;
            if (n10.f1569s == null) {
                n10.f1569s = new K(n10);
            }
            Y y10 = this.f1469H;
            d0 d0Var = ((C0170u) y10.c).f1649x;
            for (d0 d0Var2 = (d0) y10.d; !A7.m.a(d0Var2, d0Var) && d0Var2 != null; d0Var2 = d0Var2.f1649x) {
                d0Var2.L0();
            }
        }
        Q();
    }

    public final boolean x() {
        return this.layoutDelegate.f1559g;
    }

    public final void x0(C0.K k10) {
        if (A7.m.a(this.f1463A, k10)) {
            return;
        }
        this.f1463A = k10;
        C1489n1 c1489n1 = this.B;
        if (c1489n1 != null) {
            ((C0738e0) c1489n1.f16625n).setValue(k10);
        }
        Q();
    }

    public final K y() {
        return this.layoutDelegate.f1569s;
    }

    public final void y0(g0.o oVar) {
        if (!(!this.l || this.f1473L == g0.l.f14684a)) {
            l9.d.g0("Modifiers are not supported on virtual LayoutNodes");
            throw null;
        }
        if (this.f1478Q) {
            l9.d.g0("modifier is updated when deactivated");
            throw null;
        }
        if (V()) {
            a(oVar);
        } else {
            this.f1474M = oVar;
        }
    }

    public final L z() {
        return this.layoutDelegate.f1568r;
    }

    public final void z0() {
        if (this.o <= 0 || !this.f1485r) {
            return;
        }
        int i7 = 0;
        this.f1485r = false;
        V.d dVar = this.f1484q;
        if (dVar == null) {
            dVar = new V.d(new F[16]);
            this.f1484q = dVar;
        }
        dVar.g();
        V.d dVar2 = (V.d) this.f1483p.f16569m;
        int i10 = dVar2.f9279n;
        if (i10 > 0) {
            Object[] objArr = dVar2.l;
            do {
                F f10 = (F) objArr[i7];
                if (f10.l) {
                    dVar.c(dVar.f9279n, f10.L());
                } else {
                    dVar.b(f10);
                }
                i7++;
            } while (i7 < i10);
        }
        N n10 = this.layoutDelegate;
        n10.f1568r.f1531H = true;
        K k10 = n10.f1569s;
        if (k10 != null) {
            k10.f1509E = true;
        }
    }

    public F(int i7, boolean z6) {
        this.l = z6;
        this.f1481m = i7;
        this.f1483p = new io.sentry.internal.debugmeta.c(new V.d(new F[16]), 4, new I0(this, 8));
        this.f1492y = new V.d(new F[16]);
        this.f1493z = true;
        this.f1463A = f1460T;
        this.f1464C = I.f1503a;
        this.f1465D = a1.k.l;
        this.f1466E = f1461U;
        InterfaceC0771w.d.getClass();
        this.f1467F = C0770v.f8384b;
        this.f1479R = 3;
        this.f1480S = 3;
        this.f1469H = new Y(this);
        this.layoutDelegate = new N(this);
        this.f1472K = true;
        this.f1473L = g0.l.f14684a;
    }
}
