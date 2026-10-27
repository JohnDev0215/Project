package E0;

import A.G0;
import A.I0;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import java.util.LinkedHashMap;
import n0.AbstractC1851A;
import n0.AbstractC1861K;
import n0.C1854D;
import n0.C1863M;
import n0.C1870U;
import n0.C1878g;
import n0.InterfaceC1889r;
import n7.AbstractC1996k;
import n7.AbstractC1999n;
import q0.C2164c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 extends Q implements C0.J, C0.r, j0 {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final C1863M f1628Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final C0172w f1629R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final float[] f1630S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final C0154d f1631T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final C0154d f1632U;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f1633A;
    public InterfaceC2782k B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public a1.b f1634C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public a1.k f1635D;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C0.L f1637F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public LinkedHashMap f1638G;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public float f1640I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public m0.b f1641J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C0172w f1642K;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f1645N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public i0 f1646O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public C2164c f1647P;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final F f1648w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public d0 f1649x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public d0 f1650y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1651z;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f1636E = 0.8f;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public long f1639H = 0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final G0 f1643L = new G0(this, 4);

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final I0 f1644M = new I0(this, 11);

    static {
        C1863M c1863m = new C1863M();
        c1863m.f19387m = 1.0f;
        c1863m.f19388n = 1.0f;
        c1863m.o = 1.0f;
        long j3 = AbstractC1851A.f19369a;
        c1863m.f19392s = j3;
        c1863m.f19393t = j3;
        c1863m.f19397x = 8.0f;
        c1863m.f19398y = C1870U.f19422b;
        c1863m.f19399z = AbstractC1861K.f19381a;
        c1863m.B = 0;
        c1863m.f19383C = 9205357640488583168L;
        c1863m.f19384D = l9.d.g();
        c1863m.f19385E = a1.k.l;
        f1628Q = c1863m;
        f1629R = new C0172w();
        f1630S = C1854D.a();
        f1631T = new C0154d(1);
        f1632U = new C0154d(2);
    }

    public d0(F f10) {
        this.f1648w = f10;
        this.f1634C = f10.f1464C;
        this.f1635D = f10.f1465D;
    }

    public static d0 h1(C0.r rVar) {
        d0 d0Var;
        C0.I i7 = rVar instanceof C0.I ? (C0.I) rVar : null;
        if (i7 != null && (d0Var = i7.l.f1582w) != null) {
            return d0Var;
        }
        A7.m.d(rVar, "null cannot be cast to non-null type androidx.compose.ui.node.NodeCoordinator");
        return (d0) rVar;
    }

    @Override // E0.j0
    public final boolean A() {
        return (this.f1646O == null || this.f1651z || !this.f1648w.V()) ? false : true;
    }

    @Override // E0.Q
    public final Q A0() {
        return this.f1650y;
    }

    @Override // C0.r
    public final long B(long j3) {
        if (Q0().f14695x) {
            return Y0(C0.c0.h(this), ((AndroidComposeView) I.a(this.f1648w)).B(j3));
        }
        l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
        throw null;
    }

    @Override // E0.Q
    public final long B0() {
        return this.f1639H;
    }

    @Override // C0.r
    public final boolean D() {
        return Q0().f14695x;
    }

    @Override // E0.Q
    public final void D0() {
        C2164c c2164c = this.f1647P;
        if (c2164c != null) {
            k0(this.f1639H, this.f1640I, c2164c);
        } else {
            l0(this.f1639H, this.f1640I, this.B);
        }
    }

    @Override // C0.r
    public final void E(float[] fArr) {
        Owner ownerA = I.a(this.f1648w);
        k1(h1(C0.c0.h(this)), fArr);
        AndroidComposeView androidComposeView = (AndroidComposeView) ownerA;
        androidComposeView.y();
        C1854D.g(fArr, androidComposeView.f11648a0);
        float fD = m0.c.d(androidComposeView.f11652e0);
        float fE = m0.c.e(androidComposeView.f11652e0);
        float[] fArr2 = androidComposeView.f11647W;
        C1854D.d(fArr2);
        C1854D.h(fD, fE, 0.0f, fArr2);
        float fO = F0.T.o(0, 0, fArr2, fArr);
        float fO2 = F0.T.o(0, 1, fArr2, fArr);
        float fO3 = F0.T.o(0, 2, fArr2, fArr);
        float fO4 = F0.T.o(0, 3, fArr2, fArr);
        float fO5 = F0.T.o(1, 0, fArr2, fArr);
        float fO6 = F0.T.o(1, 1, fArr2, fArr);
        float fO7 = F0.T.o(1, 2, fArr2, fArr);
        float fO8 = F0.T.o(1, 3, fArr2, fArr);
        float fO9 = F0.T.o(2, 0, fArr2, fArr);
        float fO10 = F0.T.o(2, 1, fArr2, fArr);
        float fO11 = F0.T.o(2, 2, fArr2, fArr);
        float fO12 = F0.T.o(2, 3, fArr2, fArr);
        float fO13 = F0.T.o(3, 0, fArr2, fArr);
        float fO14 = F0.T.o(3, 1, fArr2, fArr);
        float fO15 = F0.T.o(3, 2, fArr2, fArr);
        float fO16 = F0.T.o(3, 3, fArr2, fArr);
        fArr[0] = fO;
        fArr[1] = fO2;
        fArr[2] = fO3;
        fArr[3] = fO4;
        fArr[4] = fO5;
        fArr[5] = fO6;
        fArr[6] = fO7;
        fArr[7] = fO8;
        fArr[8] = fO9;
        fArr[9] = fO10;
        fArr[10] = fO11;
        fArr[11] = fO12;
        fArr[12] = fO13;
        fArr[13] = fO14;
        fArr[14] = fO15;
        fArr[15] = fO16;
    }

    public final void E0(d0 d0Var, m0.b bVar, boolean z6) {
        if (d0Var == this) {
            return;
        }
        d0 d0Var2 = this.f1650y;
        if (d0Var2 != null) {
            d0Var2.E0(d0Var, bVar, z6);
        }
        long j3 = this.f1639H;
        float f10 = (int) (j3 >> 32);
        bVar.f18878a -= f10;
        bVar.c -= f10;
        float f11 = (int) (j3 & 4294967295L);
        bVar.f18879b -= f11;
        bVar.d -= f11;
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            i0Var.g(bVar, true);
            if (this.f1633A && z6) {
                long j10 = this.f872n;
                bVar.a(0.0f, 0.0f, (int) (j10 >> 32), (int) (j10 & 4294967295L));
            }
        }
    }

    public final long F0(d0 d0Var, long j3) {
        if (d0Var == this) {
            return j3;
        }
        d0 d0Var2 = this.f1650y;
        return (d0Var2 == null || A7.m.a(d0Var, d0Var2)) ? N0(j3, true) : N0(d0Var2.F0(d0Var, j3), true);
    }

    public final long G0(long j3) {
        return h6.j.f(Math.max(0.0f, (m0.f.d(j3) - h0()) / 2.0f), Math.max(0.0f, (m0.f.b(j3) - f0()) / 2.0f));
    }

    public final float H0(long j3, long j10) {
        if (h0() >= m0.f.d(j10) && f0() >= m0.f.b(j10)) {
            return Float.POSITIVE_INFINITY;
        }
        long jG0 = G0(j10);
        float fD = m0.f.d(jG0);
        float fB = m0.f.b(jG0);
        float fD2 = m0.c.d(j3);
        float fMax = Math.max(0.0f, fD2 < 0.0f ? -fD2 : fD2 - h0());
        float fE = m0.c.e(j3);
        long jA = h6.e.a(fMax, Math.max(0.0f, fE < 0.0f ? -fE : fE - f0()));
        if ((fD <= 0.0f && fB <= 0.0f) || m0.c.d(jA) > fD || m0.c.e(jA) > fB) {
            return Float.POSITIVE_INFINITY;
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jA >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jA & 4294967295L));
        return (fIntBitsToFloat2 * fIntBitsToFloat2) + (fIntBitsToFloat * fIntBitsToFloat);
    }

    public final void I0(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            i0Var.f(interfaceC1889r, c2164c);
            return;
        }
        long j3 = this.f1639H;
        float f10 = (int) (j3 >> 32);
        float f11 = (int) (j3 & 4294967295L);
        interfaceC1889r.j(f10, f11);
        K0(interfaceC1889r, c2164c);
        interfaceC1889r.j(-f10, -f11);
    }

    public final void J0(InterfaceC1889r interfaceC1889r, C1878g c1878g) {
        long j3 = this.f872n;
        interfaceC1889r.getClass();
        interfaceC1889r.l(0.5f, 0.5f, ((int) (j3 >> 32)) - 0.5f, ((int) (j3 & 4294967295L)) - 0.5f, c1878g);
    }

    public final void K0(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        g0.n nVarR0 = R0(4);
        if (nVarR0 == null) {
            c1(interfaceC1889r, c2164c);
            return;
        }
        F f10 = this.f1648w;
        f10.getClass();
        H sharedDrawScope = I.a(f10).getSharedDrawScope();
        long jS = H7.H.S(this.f872n);
        sharedDrawScope.getClass();
        V.d dVar = null;
        while (nVarR0 != null) {
            if (nVarR0 instanceof InterfaceC0165o) {
                sharedDrawScope.b(interfaceC1889r, jS, this, (InterfaceC0165o) nVarR0, c2164c);
            } else if ((nVarR0.f14686n & 4) != 0 && (nVarR0 instanceof AbstractC0164n)) {
                int i7 = 0;
                for (g0.n nVar = ((AbstractC0164n) nVarR0).f1690z; nVar != null; nVar = nVar.f14688q) {
                    if ((nVar.f14686n & 4) != 0) {
                        i7++;
                        if (i7 == 1) {
                            nVarR0 = nVar;
                        } else {
                            if (dVar == null) {
                                dVar = new V.d(new g0.n[16]);
                            }
                            if (nVarR0 != null) {
                                dVar.b(nVarR0);
                                nVarR0 = null;
                            }
                            dVar.b(nVar);
                        }
                    }
                }
                if (i7 == 1) {
                }
            }
            nVarR0 = AbstractC0156f.f(dVar);
        }
    }

    @Override // C0.r
    public final long L() {
        return this.f872n;
    }

    public abstract void L0();

    public final d0 M0(d0 d0Var) {
        F fG = d0Var.f1648w;
        F f10 = this.f1648w;
        if (fG == f10) {
            g0.n nVarQ0 = d0Var.Q0();
            g0.n nVar = Q0().l;
            if (!nVar.f14695x) {
                l9.d.h0("visitLocalAncestors called on an unattached node");
                throw null;
            }
            for (g0.n nVar2 = nVar.f14687p; nVar2 != null; nVar2 = nVar2.f14687p) {
                if ((nVar2.f14686n & 2) != 0 && nVar2 == nVarQ0) {
                    return d0Var;
                }
            }
            return this;
        }
        while (fG.f1489v > f10.f1489v) {
            fG = fG.G();
            A7.m.c(fG);
        }
        F fG2 = f10;
        while (fG2.f1489v > fG.f1489v) {
            fG2 = fG2.G();
            A7.m.c(fG2);
        }
        while (fG != fG2) {
            fG = fG.G();
            fG2 = fG2.G();
            if (fG == null || fG2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        return fG2 == f10 ? this : fG == d0Var.f1648w ? d0Var : (C0170u) fG.f1469H.c;
    }

    @Override // C0.r
    public final long N(long j3) {
        if (!Q0().f14695x) {
            l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        Z0();
        while (this != null) {
            j3 = this.i1(j3, true);
            this = this.f1650y;
        }
        return j3;
    }

    public final long N0(long j3, boolean z6) {
        if (z6 || !this.f1575q) {
            long j10 = this.f1639H;
            j3 = h6.e.a(m0.c.d(j3) - ((int) (j10 >> 32)), m0.c.e(j3) - ((int) (j10 & 4294967295L)));
        }
        i0 i0Var = this.f1646O;
        return i0Var != null ? i0Var.b(j3, true) : j3;
    }

    public abstract S O0();

    public final long P0() {
        return this.f1634C.Y(this.f1648w.f1466E.g());
    }

    public abstract g0.n Q0();

    public final g0.n R0(int i7) {
        boolean zH = e0.h(i7);
        g0.n nVarQ0 = Q0();
        if (!zH && (nVarQ0 = nVarQ0.f14687p) == null) {
            return null;
        }
        for (g0.n nVarS0 = S0(zH); nVarS0 != null && (nVarS0.o & i7) != 0; nVarS0 = nVarS0.f14688q) {
            if ((nVarS0.f14686n & i7) != 0) {
                return nVarS0;
            }
            if (nVarS0 == nVarQ0) {
                return null;
            }
        }
        return null;
    }

    public final g0.n S0(boolean z6) {
        g0.n nVarQ0;
        Y y10 = this.f1648w.f1469H;
        if (((d0) y10.d) == this) {
            return (g0.n) y10.f1603f;
        }
        if (z6) {
            d0 d0Var = this.f1650y;
            if (d0Var != null && (nVarQ0 = d0Var.Q0()) != null) {
                return nVarQ0.f14688q;
            }
        } else {
            d0 d0Var2 = this.f1650y;
            if (d0Var2 != null) {
                return d0Var2.Q0();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
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
    /* JADX WARN: Type inference failed for: r6v5 */
    public final void T0(g0.n nVar, C0154d c0154d, long j3, C0168s c0168s, boolean z6, boolean z10) {
        if (nVar == null) {
            V0(c0154d, j3, c0168s, z6, z10);
            return;
        }
        c0168s.l(nVar, -1.0f, z10, new b0(this, nVar, c0154d, j3, c0168s, z6, z10));
        d0 d0Var = nVar.f14690s;
        if (d0Var != null) {
            g0.n nVarS0 = d0Var.S0(e0.h(16));
            if (nVarS0 != null && nVarS0.f14695x) {
                g0.n nVar2 = nVarS0.l;
                if (!nVar2.f14695x) {
                    l9.d.h0("visitLocalDescendants called on an unattached node");
                    throw null;
                }
                if ((nVar2.o & 16) != 0) {
                    while (nVar2 != null) {
                        if ((nVar2.f14686n & 16) != 0) {
                            ?? F9 = nVar2;
                            ?? dVar = 0;
                            while (F9 != 0) {
                                if (F9 instanceof n0) {
                                    if (((n0) F9).V()) {
                                        return;
                                    }
                                } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                                    g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                    int i7 = 0;
                                    F9 = F9;
                                    dVar = dVar;
                                    while (nVar3 != null) {
                                        if ((nVar3.f14686n & 16) != 0) {
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
                                F9 = AbstractC0156f.f(dVar);
                            }
                        }
                        nVar2 = nVar2.f14688q;
                    }
                }
            }
            c0168s.f1701p = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ec, code lost:
    
        if (E0.AbstractC0156f.h(r20.d(), E0.AbstractC0156f.a(r14, r22)) > 0) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void U0(E0.C0154d r17, long r18, E0.C0168s r20, boolean r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.d0.U0(E0.d, long, E0.s, boolean, boolean):void");
    }

    public void V0(C0154d c0154d, long j3, C0168s c0168s, boolean z6, boolean z10) {
        d0 d0Var = this.f1649x;
        if (d0Var != null) {
            d0Var.U0(c0154d, d0Var.N0(j3, true), c0168s, z6, z10);
        }
    }

    public final void W0() {
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            i0Var.invalidate();
            return;
        }
        d0 d0Var = this.f1650y;
        if (d0Var != null) {
            d0Var.W0();
        }
    }

    public final boolean X0() {
        if (this.f1646O != null && this.f1636E <= 0.0f) {
            return true;
        }
        d0 d0Var = this.f1650y;
        if (d0Var != null) {
            return d0Var.X0();
        }
        return false;
    }

    public final long Y0(C0.r rVar, long j3) {
        if (rVar instanceof C0.I) {
            ((C0.I) rVar).l.f1582w.Z0();
            return ((C0.I) rVar).b(this, j3 ^ (-9223372034707292160L)) ^ (-9223372034707292160L);
        }
        d0 d0VarH1 = h1(rVar);
        d0VarH1.Z0();
        d0 d0VarM0 = M0(d0VarH1);
        while (d0VarH1 != d0VarM0) {
            j3 = d0VarH1.i1(j3, true);
            d0VarH1 = d0VarH1.f1650y;
            A7.m.c(d0VarH1);
        }
        return F0(d0VarM0, j3);
    }

    public final void Z0() {
        N nT = this.f1648w.t();
        int iV = nT.f1555a.v();
        if (iV == 3 || iV == 4) {
            if (nT.f1568r.f1532I) {
                nT.e(true);
            } else {
                nT.d(true);
            }
        }
        if (iV == 4) {
            K k10 = nT.f1569s;
            if (k10 == null || !k10.f1510F) {
                nT.f(true);
            } else {
                nT.g(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v7, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v5 */
    public final void a1() {
        g0.n nVarQ0;
        g0.n nVarS0 = S0(e0.h(128));
        if (nVarS0 == null || (nVarS0.l.o & 128) == 0) {
            return;
        }
        AbstractC1167g abstractC1167gC = AbstractC1179s.c();
        InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
        AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
        try {
            boolean zH = e0.h(128);
            if (!zH) {
                nVarQ0 = Q0().f14687p;
                if (nVarQ0 == null) {
                }
            }
            nVarQ0 = Q0();
            for (g0.n nVarS02 = S0(zH); nVarS02 != null; nVarS02 = nVarS02.f14688q) {
                if ((nVarS02.o & 128) == 0) {
                    break;
                }
                if ((nVarS02.f14686n & 128) != 0) {
                    ?? dVar = 0;
                    ?? F9 = nVarS02;
                    while (F9 != 0) {
                        if (F9 instanceof InterfaceC0173x) {
                            ((InterfaceC0173x) F9).u(this.f872n);
                        } else if ((F9.f14686n & 128) != 0 && (F9 instanceof AbstractC0164n)) {
                            g0.n nVar = ((AbstractC0164n) F9).f1690z;
                            int i7 = 0;
                            F9 = F9;
                            dVar = dVar;
                            while (nVar != null) {
                                if ((nVar.f14686n & 128) != 0) {
                                    i7++;
                                    dVar = dVar;
                                    if (i7 == 1) {
                                        F9 = nVar;
                                    } else {
                                        if (dVar == 0) {
                                            dVar = new V.d(new g0.n[16]);
                                        }
                                        if (F9 != 0) {
                                            dVar.b(F9);
                                            F9 = 0;
                                        }
                                        dVar.b(nVar);
                                    }
                                }
                                nVar = nVar.f14688q;
                                F9 = F9;
                                dVar = dVar;
                            }
                            if (i7 == 1) {
                            }
                        }
                        F9 = AbstractC0156f.f(dVar);
                    }
                }
                if (nVarS02 == nVarQ0) {
                    break;
                }
            }
        } finally {
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
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
    /* JADX WARN: Type inference failed for: r6v4 */
    public final void b1() {
        boolean zH = e0.h(128);
        g0.n nVarQ0 = Q0();
        if (!zH && (nVarQ0 = nVarQ0.f14687p) == null) {
            return;
        }
        for (g0.n nVarS0 = S0(zH); nVarS0 != null && (nVarS0.o & 128) != 0; nVarS0 = nVarS0.f14688q) {
            if ((nVarS0.f14686n & 128) != 0) {
                ?? F9 = nVarS0;
                ?? dVar = 0;
                while (F9 != 0) {
                    if (F9 instanceof InterfaceC0173x) {
                        ((InterfaceC0173x) F9).t0(this);
                    } else if ((F9.f14686n & 128) != 0 && (F9 instanceof AbstractC0164n)) {
                        g0.n nVar = ((AbstractC0164n) F9).f1690z;
                        int i7 = 0;
                        F9 = F9;
                        dVar = dVar;
                        while (nVar != null) {
                            if ((nVar.f14686n & 128) != 0) {
                                i7++;
                                dVar = dVar;
                                if (i7 == 1) {
                                    F9 = nVar;
                                } else {
                                    if (dVar == 0) {
                                        dVar = new V.d(new g0.n[16]);
                                    }
                                    if (F9 != 0) {
                                        dVar.b(F9);
                                        F9 = 0;
                                    }
                                    dVar.b(nVar);
                                }
                            }
                            nVar = nVar.f14688q;
                            F9 = F9;
                            dVar = dVar;
                        }
                        if (i7 == 1) {
                        }
                    }
                    F9 = AbstractC0156f.f(dVar);
                }
            }
            if (nVarS0 == nVarQ0) {
                return;
            }
        }
    }

    public abstract void c1(InterfaceC1889r interfaceC1889r, C2164c c2164c);

    @Override // C0.r
    public final long d(long j3) {
        if (!Q0().f14695x) {
            l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        C0.r rVarH = C0.c0.h(this);
        AndroidComposeView androidComposeView = (AndroidComposeView) I.a(this.f1648w);
        androidComposeView.y();
        return Y0(rVarH, m0.c.g(C1854D.b(j3, androidComposeView.f11649b0), rVarH.N(0L)));
    }

    public final void d1(long j3, float f10, InterfaceC2782k interfaceC2782k, C2164c c2164c) {
        F f11 = this.f1648w;
        if (c2164c == null) {
            if (this.f1647P != null) {
                this.f1647P = null;
                l1(null, false);
            }
            l1(interfaceC2782k, false);
        } else {
            if (interfaceC2782k != null) {
                l9.d.g0("both ways to create layers shouldn't be used together");
                throw null;
            }
            if (this.f1647P != c2164c) {
                this.f1647P = null;
                l1(null, false);
                this.f1647P = c2164c;
            }
            if (this.f1646O == null) {
                AndroidComposeView androidComposeView = (AndroidComposeView) I.a(f11);
                G0 g02 = this.f1643L;
                I0 i02 = this.f1644M;
                i0 i0VarH = androidComposeView.h(g02, i02, c2164c);
                i0VarH.c(this.f872n);
                i0VarH.h(j3);
                this.f1646O = i0VarH;
                f11.f1472K = true;
                i02.a();
            }
        }
        if (!a1.h.b(this.f1639H, j3)) {
            this.f1639H = j3;
            f11.t().f1568r.w0();
            i0 i0Var = this.f1646O;
            if (i0Var != null) {
                i0Var.h(j3);
            } else {
                d0 d0Var = this.f1650y;
                if (d0Var != null) {
                    d0Var.W0();
                }
            }
            Q.C0(this);
            AndroidComposeView androidComposeView2 = f11.f1487t;
            if (androidComposeView2 != null) {
                androidComposeView2.u(f11);
            }
        }
        this.f1640I = f10;
        if (this.f1577s) {
            return;
        }
        t0(new m0(z0(), this));
    }

    public final void e1(m0.b bVar, boolean z6, boolean z10) {
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            if (this.f1633A) {
                if (z10) {
                    long jP0 = P0();
                    float fD = m0.f.d(jP0) / 2.0f;
                    float fB = m0.f.b(jP0) / 2.0f;
                    long j3 = this.f872n;
                    bVar.a(-fD, -fB, ((int) (j3 >> 32)) + fD, ((int) (j3 & 4294967295L)) + fB);
                } else if (z6) {
                    long j10 = this.f872n;
                    bVar.a(0.0f, 0.0f, (int) (j10 >> 32), (int) (j10 & 4294967295L));
                }
                if (bVar.b()) {
                    return;
                }
            }
            i0Var.g(bVar, false);
        }
        long j11 = this.f1639H;
        float f10 = (int) (j11 >> 32);
        bVar.f18878a += f10;
        bVar.c += f10;
        float f11 = (int) (j11 & 4294967295L);
        bVar.f18879b += f11;
        bVar.d += f11;
    }

    @Override // C0.r
    public final void f(C0.r rVar, float[] fArr) {
        d0 d0VarH1 = h1(rVar);
        d0VarH1.Z0();
        d0 d0VarM0 = M0(d0VarH1);
        C1854D.d(fArr);
        d0VarH1.k1(d0VarM0, fArr);
        j1(d0VarM0, fArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v4 */
    public final void f1(C0.L l) {
        d0 d0Var;
        C0.L l10 = this.f1637F;
        if (l != l10) {
            this.f1637F = l;
            F f10 = this.f1648w;
            if (l10 == null || l.e() != l10.e() || l.c() != l10.c()) {
                int iE = l.e();
                int iC = l.c();
                i0 i0Var = this.f1646O;
                if (i0Var != null) {
                    i0Var.c(H7.H.c(iE, iC));
                } else if (f10.W() && (d0Var = this.f1650y) != null) {
                    d0Var.W0();
                }
                n0(H7.H.c(iE, iC));
                if (this.B != null) {
                    m1(false);
                }
                boolean zH = e0.h(4);
                g0.n nVarQ0 = Q0();
                if (zH || (nVarQ0 = nVarQ0.f14687p) != null) {
                    for (g0.n nVarS0 = S0(zH); nVarS0 != null && (nVarS0.o & 4) != 0; nVarS0 = nVarS0.f14688q) {
                        if ((nVarS0.f14686n & 4) != 0) {
                            ?? F9 = nVarS0;
                            ?? dVar = 0;
                            while (F9 != 0) {
                                if (F9 instanceof InterfaceC0165o) {
                                    ((InterfaceC0165o) F9).n0();
                                } else if ((F9.f14686n & 4) != 0 && (F9 instanceof AbstractC0164n)) {
                                    g0.n nVar = ((AbstractC0164n) F9).f1690z;
                                    int i7 = 0;
                                    F9 = F9;
                                    dVar = dVar;
                                    while (nVar != null) {
                                        if ((nVar.f14686n & 4) != 0) {
                                            i7++;
                                            dVar = dVar;
                                            if (i7 == 1) {
                                                F9 = nVar;
                                            } else {
                                                if (dVar == 0) {
                                                    dVar = new V.d(new g0.n[16]);
                                                }
                                                if (F9 != 0) {
                                                    dVar.b(F9);
                                                    F9 = 0;
                                                }
                                                dVar.b(nVar);
                                            }
                                        }
                                        nVar = nVar.f14688q;
                                        F9 = F9;
                                        dVar = dVar;
                                    }
                                    if (i7 == 1) {
                                    }
                                }
                                F9 = AbstractC0156f.f(dVar);
                            }
                        }
                        if (nVarS0 == nVarQ0) {
                            break;
                        }
                    }
                }
                AndroidComposeView androidComposeView = f10.f1487t;
                if (androidComposeView != null) {
                    androidComposeView.u(f10);
                }
            }
            LinkedHashMap linkedHashMap = this.f1638G;
            if (((linkedHashMap == null || linkedHashMap.isEmpty()) && l.a().isEmpty()) || A7.m.a(l.a(), this.f1638G)) {
                return;
            }
            f10.t().f1568r.f1529F.f();
            LinkedHashMap linkedHashMap2 = this.f1638G;
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                this.f1638G = linkedHashMap2;
            }
            linkedHashMap2.clear();
            linkedHashMap2.putAll(l.a());
        }
    }

    @Override // C0.r
    public final long g(long j3) {
        long jN = N(j3);
        AndroidComposeView androidComposeView = (AndroidComposeView) I.a(this.f1648w);
        androidComposeView.y();
        return C1854D.b(jN, androidComposeView.f11648a0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v4 */
    public final void g1(g0.n nVar, C0154d c0154d, long j3, C0168s c0168s, boolean z6, boolean z10, float f10) {
        boolean z11;
        if (nVar == null) {
            V0(c0154d, j3, c0168s, z6, z10);
            return;
        }
        switch (c0154d.f1627a) {
            case 1:
                ?? dVar = 0;
                ?? F9 = nVar;
                while (true) {
                    int i7 = 0;
                    if (F9 == 0) {
                        z11 = false;
                    } else {
                        if (F9 instanceof n0) {
                            ((n0) F9).o0();
                        } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                            g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                            F9 = F9;
                            dVar = dVar;
                            while (nVar2 != null) {
                                if ((nVar2.f14686n & 16) != 0) {
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
                    break;
                }
                break;
            default:
                z11 = false;
                break;
        }
        if (!z11) {
            g1(AbstractC0156f.e(nVar, c0154d.a()), c0154d, j3, c0168s, z6, z10, f10);
            return;
        }
        c0 c0Var = new c0(this, nVar, c0154d, j3, c0168s, z6, z10, f10, 1);
        if (c0168s.f1700n == AbstractC1999n.C(c0168s)) {
            c0168s.l(nVar, f10, z10, c0Var);
            if (c0168s.f1700n + 1 == AbstractC1999n.C(c0168s)) {
                c0168s.q();
                return;
            }
            return;
        }
        long jD = c0168s.d();
        int i10 = c0168s.f1700n;
        c0168s.f1700n = AbstractC1999n.C(c0168s);
        c0168s.l(nVar, f10, z10, c0Var);
        if (c0168s.f1700n + 1 < AbstractC1999n.C(c0168s) && AbstractC0156f.h(jD, c0168s.d()) > 0) {
            int i11 = c0168s.f1700n + 1;
            int i12 = i10 + 1;
            Object[] objArr = c0168s.l;
            AbstractC1996k.D(i12, i11, c0168s.o, objArr, objArr);
            long[] jArr = c0168s.f1699m;
            System.arraycopy(jArr, i11, jArr, i12, c0168s.o - i11);
            c0168s.f1700n = ((c0168s.o + i10) - c0168s.f1700n) - 1;
        }
        c0168s.q();
        c0168s.f1700n = i10;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f1648w.f1464C.getDensity();
    }

    @Override // C0.InterfaceC0108o
    public final a1.k getLayoutDirection() {
        return this.f1648w.f1465D;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
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
    /* JADX WARN: Type inference failed for: r6v5 */
    @Override // C0.V, C0.J
    public final Object i() {
        F f10 = this.f1648w;
        if (!f10.f1469H.f(64)) {
            return null;
        }
        Q0();
        Object objJ = null;
        for (g0.n nVar = (q0) f10.f1469H.f1602e; nVar != null; nVar = nVar.f14687p) {
            if ((nVar.f14686n & 64) != 0) {
                ?? F9 = nVar;
                ?? dVar = 0;
                while (F9 != 0) {
                    if (F9 instanceof l0) {
                        objJ = ((l0) F9).j(f10.f1464C, objJ);
                    } else if ((F9.f14686n & 64) != 0 && (F9 instanceof AbstractC0164n)) {
                        g0.n nVar2 = ((AbstractC0164n) F9).f1690z;
                        int i7 = 0;
                        F9 = F9;
                        dVar = dVar;
                        while (nVar2 != null) {
                            if ((nVar2.f14686n & 64) != 0) {
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
        }
        return objJ;
    }

    public final long i1(long j3, boolean z6) {
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            j3 = i0Var.b(j3, false);
        }
        if (!z6 && this.f1575q) {
            return j3;
        }
        long j10 = this.f1639H;
        return h6.e.a(m0.c.d(j3) + ((int) (j10 >> 32)), m0.c.e(j3) + ((int) (4294967295L & j10)));
    }

    @Override // C0.r
    public final C0.r j() {
        if (Q0().f14695x) {
            Z0();
            return ((d0) this.f1648w.f1469H.d).f1650y;
        }
        l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
        throw null;
    }

    public final void j1(d0 d0Var, float[] fArr) {
        if (A7.m.a(d0Var, this)) {
            return;
        }
        d0 d0Var2 = this.f1650y;
        A7.m.c(d0Var2);
        d0Var2.j1(d0Var, fArr);
        if (!a1.h.b(this.f1639H, 0L)) {
            float[] fArr2 = f1630S;
            C1854D.d(fArr2);
            long j3 = this.f1639H;
            C1854D.h(-((int) (j3 >> 32)), -((int) (j3 & 4294967295L)), 0.0f, fArr2);
            C1854D.g(fArr, fArr2);
        }
        i0 i0Var = this.f1646O;
        if (i0Var != null) {
            i0Var.e(fArr);
        }
    }

    @Override // C0.r
    public final long k(C0.r rVar, long j3) {
        return Y0(rVar, j3);
    }

    @Override // C0.V
    public abstract void k0(long j3, float f10, C2164c c2164c);

    public final void k1(d0 d0Var, float[] fArr) {
        while (!this.equals(d0Var)) {
            i0 i0Var = this.f1646O;
            if (i0Var != null) {
                i0Var.d(fArr);
            }
            if (!a1.h.b(this.f1639H, 0L)) {
                float[] fArr2 = f1630S;
                C1854D.d(fArr2);
                C1854D.h((int) (r0 >> 32), (int) (r0 & 4294967295L), 0.0f, fArr2);
                C1854D.g(fArr, fArr2);
            }
            this = this.f1650y;
            A7.m.c(this);
        }
    }

    public final void l1(InterfaceC2782k interfaceC2782k, boolean z6) {
        AndroidComposeView androidComposeView;
        if (!(interfaceC2782k == null || this.f1647P == null)) {
            l9.d.g0("layerBlock can't be provided when explicitLayer is provided");
            throw null;
        }
        F f10 = this.f1648w;
        boolean z10 = (!z6 && this.B == interfaceC2782k && A7.m.a(this.f1634C, f10.f1464C) && this.f1635D == f10.f1465D) ? false : true;
        this.f1634C = f10.f1464C;
        this.f1635D = f10.f1465D;
        boolean zV = f10.V();
        I0 i02 = this.f1644M;
        if (!zV || interfaceC2782k == null) {
            this.B = null;
            i0 i0Var = this.f1646O;
            if (i0Var != null) {
                i0Var.a();
                f10.f1472K = true;
                i02.a();
                if (Q0().f14695x && (androidComposeView = f10.f1487t) != null) {
                    androidComposeView.u(f10);
                }
            }
            this.f1646O = null;
            this.f1645N = false;
            return;
        }
        this.B = interfaceC2782k;
        if (this.f1646O != null) {
            if (z10) {
                m1(true);
                return;
            }
            return;
        }
        i0 i0VarH = ((AndroidComposeView) I.a(f10)).h(this.f1643L, i02, null);
        i0VarH.c(this.f872n);
        i0VarH.h(this.f1639H);
        this.f1646O = i0VarH;
        m1(true);
        f10.f1472K = true;
        i02.a();
    }

    public final void m1(boolean z6) {
        AndroidComposeView androidComposeView;
        if (this.f1647P != null) {
            return;
        }
        i0 i0Var = this.f1646O;
        if (i0Var == null) {
            if (this.B == null) {
                return;
            }
            l9.d.h0("null layer with a non-null layerBlock");
            throw null;
        }
        InterfaceC2782k interfaceC2782k = this.B;
        if (interfaceC2782k == null) {
            l9.d.i0("updateLayerParameters requires a non-null layerBlock");
            throw null;
        }
        C1863M c1863m = f1628Q;
        c1863m.i(1.0f);
        c1863m.j(1.0f);
        c1863m.a(1.0f);
        c1863m.r(0.0f);
        c1863m.u(0.0f);
        c1863m.k(0.0f);
        long j3 = AbstractC1851A.f19369a;
        c1863m.b(j3);
        c1863m.n(j3);
        c1863m.d(0.0f);
        c1863m.f(0.0f);
        c1863m.g(0.0f);
        if (c1863m.f19397x != 8.0f) {
            c1863m.l |= 2048;
            c1863m.f19397x = 8.0f;
        }
        c1863m.o(C1870U.f19422b);
        c1863m.l(AbstractC1861K.f19381a);
        c1863m.c(false);
        if (!A7.m.a(null, null)) {
            c1863m.l |= 131072;
        }
        if (!AbstractC1861K.q(c1863m.B, 0)) {
            c1863m.l |= 32768;
            c1863m.B = 0;
        }
        c1863m.f19383C = 9205357640488583168L;
        c1863m.f19386F = null;
        c1863m.l = 0;
        F f10 = this.f1648w;
        c1863m.f19384D = f10.f1464C;
        c1863m.f19385E = f10.f1465D;
        c1863m.f19383C = H7.H.S(this.f872n);
        I.a(f10).getSnapshotObserver().a(this, C0155e.f1654q, new I0(interfaceC2782k, 12));
        C0172w c0172w = this.f1642K;
        if (c0172w == null) {
            c0172w = new C0172w();
            this.f1642K = c0172w;
        }
        c0172w.f1707a = c1863m.f19387m;
        c0172w.f1708b = c1863m.f19388n;
        c0172w.c = c1863m.f19389p;
        c0172w.d = c1863m.f19390q;
        c0172w.f1709e = c1863m.f19394u;
        c0172w.f1710f = c1863m.f19395v;
        c0172w.f1711g = c1863m.f19396w;
        c0172w.f1712h = c1863m.f19397x;
        c0172w.f1713i = c1863m.f19398y;
        i0Var.k(c1863m);
        this.f1633A = c1863m.f19382A;
        this.f1636E = c1863m.o;
        if (!z6 || (androidComposeView = f10.f1487t) == null) {
            return;
        }
        androidComposeView.u(f10);
    }

    @Override // C0.r
    public final m0.d o(C0.r rVar, boolean z6) {
        if (!Q0().f14695x) {
            l9.d.h0("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        if (!rVar.D()) {
            l9.d.h0("LayoutCoordinates " + rVar + " is not attached!");
            throw null;
        }
        d0 d0VarH1 = h1(rVar);
        d0VarH1.Z0();
        d0 d0VarM0 = M0(d0VarH1);
        m0.b bVar = this.f1641J;
        if (bVar == null) {
            bVar = new m0.b();
            bVar.f18878a = 0.0f;
            bVar.f18879b = 0.0f;
            bVar.c = 0.0f;
            bVar.d = 0.0f;
            this.f1641J = bVar;
        }
        bVar.f18878a = 0.0f;
        bVar.f18879b = 0.0f;
        bVar.c = (int) (rVar.L() >> 32);
        bVar.d = (int) (rVar.L() & 4294967295L);
        while (d0VarH1 != d0VarM0) {
            d0VarH1.e1(bVar, z6, false);
            if (bVar.b()) {
                return m0.d.f18881e;
            }
            d0VarH1 = d0VarH1.f1650y;
            A7.m.c(d0VarH1);
        }
        E0(d0VarM0, bVar, z6);
        return new m0.d(bVar.f18878a, bVar.f18879b, bVar.c, bVar.d);
    }

    @Override // a1.b
    public final float p() {
        return this.f1648w.f1464C.p();
    }

    @Override // E0.Q
    public final Q v0() {
        return this.f1649x;
    }

    @Override // E0.Q
    public final C0.r w0() {
        return this;
    }

    @Override // E0.Q
    public final boolean x0() {
        return this.f1637F != null;
    }

    @Override // E0.Q
    public final F y0() {
        return this.f1648w;
    }

    @Override // E0.Q
    public final C0.L z0() {
        C0.L l = this.f1637F;
        if (l != null) {
            return l;
        }
        throw new IllegalStateException("Asking for measurement result of unmeasured layout modifier");
    }
}
