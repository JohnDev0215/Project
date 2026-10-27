package E0;

import m0.AbstractC1792a;
import n0.AbstractC1887p;
import n0.C1877f;
import n0.C1883l;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;
import p0.C2071b;
import q0.C2164c;

/* JADX INFO: loaded from: classes.dex */
public final class H implements p0.d {
    public final C2071b l = new C2071b();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC0165o f1502m;

    @Override // p0.d
    public final h3.y F() {
        return this.l.f20638m;
    }

    @Override // p0.d
    public final void I(InterfaceC1859I interfaceC1859I, AbstractC1887p abstractC1887p, float f10, p0.e eVar, C1883l c1883l, int i7) {
        this.l.I(interfaceC1859I, abstractC1887p, f10, eVar, c1883l, i7);
    }

    @Override // p0.d
    public final void J(InterfaceC1859I interfaceC1859I, long j3, float f10, p0.e eVar, C1883l c1883l, int i7) {
        this.l.J(interfaceC1859I, j3, f10, eVar, c1883l, i7);
    }

    @Override // a1.b
    public final float K(long j3) {
        return this.l.K(j3);
    }

    @Override // a1.b
    public final int O(float f10) {
        return this.l.O(f10);
    }

    @Override // p0.d
    public final void Q(long j3, float f10, long j10, float f11, p0.e eVar, C1883l c1883l, int i7) {
        this.l.Q(j3, f10, j10, f11, eVar, c1883l, i7);
    }

    @Override // p0.d
    public final long S() {
        return this.l.S();
    }

    @Override // a1.b
    public final long Y(long j3) {
        return this.l.Y(j3);
    }

    public final void a() {
        C2071b c2071b = this.l;
        InterfaceC1889r interfaceC1889rM = c2071b.f20638m.m();
        InterfaceC0163m interfaceC0163m = this.f1502m;
        A7.m.c(interfaceC0163m);
        g0.n nVar = (g0.n) interfaceC0163m;
        g0.n nVarF = nVar.l.f14688q;
        if (nVarF == null || (nVarF.o & 4) == 0) {
            nVarF = null;
        } else {
            while (nVarF != null) {
                int i7 = nVarF.f14686n;
                if ((i7 & 2) != 0) {
                    break;
                } else if ((i7 & 4) != 0) {
                    break;
                } else {
                    nVarF = nVarF.f14688q;
                }
            }
            nVarF = null;
        }
        if (nVarF == null) {
            d0 d0VarR = AbstractC0156f.r(interfaceC0163m, 4);
            if (d0VarR.Q0() == nVar.l) {
                d0VarR = d0VarR.f1649x;
                A7.m.c(d0VarR);
            }
            d0VarR.c1(interfaceC1889rM, (C2164c) c2071b.f20638m.f15500n);
            return;
        }
        V.d dVar = null;
        while (nVarF != null) {
            if (nVarF instanceof InterfaceC0165o) {
                InterfaceC0165o interfaceC0165o = (InterfaceC0165o) nVarF;
                C2164c c2164c = (C2164c) c2071b.f20638m.f15500n;
                d0 d0VarR2 = AbstractC0156f.r(interfaceC0165o, 4);
                long jS = H7.H.S(d0VarR2.f872n);
                F f10 = d0VarR2.f1648w;
                f10.getClass();
                I.a(f10).getSharedDrawScope().b(interfaceC1889rM, jS, d0VarR2, interfaceC0165o, c2164c);
            } else if ((nVarF.f14686n & 4) != 0 && (nVarF instanceof AbstractC0164n)) {
                int i10 = 0;
                for (g0.n nVar2 = ((AbstractC0164n) nVarF).f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
                    if ((nVar2.f14686n & 4) != 0) {
                        i10++;
                        if (i10 == 1) {
                            nVarF = nVar2;
                        } else {
                            if (dVar == null) {
                                dVar = new V.d(new g0.n[16]);
                            }
                            if (nVarF != null) {
                                dVar.b(nVarF);
                                nVarF = null;
                            }
                            dVar.b(nVar2);
                        }
                    }
                }
                if (i10 == 1) {
                }
            }
            nVarF = AbstractC0156f.f(dVar);
        }
    }

    public final void b(InterfaceC1889r interfaceC1889r, long j3, d0 d0Var, InterfaceC0165o interfaceC0165o, C2164c c2164c) {
        InterfaceC0165o interfaceC0165o2 = this.f1502m;
        this.f1502m = interfaceC0165o;
        a1.k kVar = d0Var.f1648w.f1465D;
        C2071b c2071b = this.l;
        a1.b bVarR = c2071b.f20638m.r();
        h3.y yVar = c2071b.f20638m;
        a1.k kVarV = yVar.v();
        InterfaceC1889r interfaceC1889rM = yVar.m();
        long jY = yVar.y();
        C2164c c2164c2 = (C2164c) yVar.f15500n;
        yVar.J(d0Var);
        yVar.K(kVar);
        yVar.I(interfaceC1889r);
        yVar.L(j3);
        yVar.f15500n = c2164c;
        interfaceC1889r.m();
        try {
            interfaceC0165o.a(this);
            interfaceC1889r.k();
            yVar.J(bVarR);
            yVar.K(kVarV);
            yVar.I(interfaceC1889rM);
            yVar.L(jY);
            yVar.f15500n = c2164c2;
            this.f1502m = interfaceC0165o2;
        } catch (Throwable th) {
            interfaceC1889r.k();
            yVar.J(bVarR);
            yVar.K(kVarV);
            yVar.I(interfaceC1889rM);
            yVar.L(jY);
            yVar.f15500n = c2164c2;
            throw th;
        }
    }

    @Override // a1.b
    public final float b0(long j3) {
        return this.l.b0(j3);
    }

    public final void c(AbstractC1887p abstractC1887p, long j3, long j10, float f10, p0.e eVar, C1883l c1883l, int i7) {
        C2071b c2071b = this.l;
        c2071b.l.c.l(m0.c.d(j3), m0.c.e(j3), m0.f.d(j10) + m0.c.d(j3), m0.f.b(j10) + m0.c.e(j3), c2071b.b(abstractC1887p, eVar, f10, c1883l, i7, 1));
    }

    @Override // p0.d
    public final void c0(long j3, long j10, long j11, float f10, p0.e eVar, C1883l c1883l, int i7) {
        this.l.c0(j3, j10, j11, f10, eVar, c1883l, i7);
    }

    public final void d(AbstractC1887p abstractC1887p, long j3, long j10, long j11, float f10, p0.e eVar, C1883l c1883l, int i7) {
        C2071b c2071b = this.l;
        c2071b.l.c.e(m0.c.d(j3), m0.c.e(j3), m0.f.d(j10) + m0.c.d(j3), m0.f.b(j10) + m0.c.e(j3), AbstractC1792a.b(j11), AbstractC1792a.c(j11), c2071b.b(abstractC1887p, eVar, f10, c1883l, i7, 1));
    }

    @Override // p0.d
    public final void d0(long j3, float f10, float f11, long j10, long j11, float f12, p0.e eVar, C1883l c1883l, int i7) {
        this.l.d0(j3, f10, f11, j10, j11, f12, eVar, c1883l, i7);
    }

    @Override // p0.d
    public final long e() {
        return this.l.e();
    }

    @Override // p0.d
    public final void g0(C1877f c1877f, long j3, long j10, long j11, long j12, float f10, p0.e eVar, C1883l c1883l, int i7, int i10) {
        this.l.g0(c1877f, j3, j10, j11, j12, f10, eVar, c1883l, i7, i10);
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l.getDensity();
    }

    @Override // p0.d
    public final a1.k getLayoutDirection() {
        return this.l.l.f20637b;
    }

    @Override // a1.b
    public final long j0(float f10) {
        return this.l.j0(f10);
    }

    @Override // a1.b
    public final float p() {
        return this.l.p();
    }

    @Override // a1.b
    public final float p0(int i7) {
        return this.l.p0(i7);
    }

    @Override // a1.b
    public final float r0(float f10) {
        return f10 / this.l.getDensity();
    }

    @Override // p0.d
    public final void u0(long j3, long j10, long j11, long j12, p0.e eVar, float f10, C1883l c1883l, int i7) {
        this.l.u0(j3, j10, j11, j12, eVar, f10, c1883l, i7);
    }

    @Override // a1.b
    public final long w(float f10) {
        return this.l.w(f10);
    }

    @Override // a1.b
    public final long x(long j3) {
        return this.l.x(j3);
    }

    @Override // a1.b
    public final float y(float f10) {
        return this.l.getDensity() * f10;
    }
}
