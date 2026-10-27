package w;

import A.C0022k;
import C0.V;
import C0.X;
import E0.InterfaceC0174y;
import E0.Q;
import x.q0;
import x.r0;
import x.x0;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: w.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2569E extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public r0 f23055A;
    public r0 B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public F f23056C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public G f23057D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public InterfaceC2772a f23058E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public x f23059F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public long f23060G = s.f23138a;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public g0.c f23061H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C2568D f23062I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C2568D f23063J;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public x0 f23064y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public r0 f23065z;

    public C2569E(x0 x0Var, r0 r0Var, r0 r0Var2, r0 r0Var3, F f10, G g7, InterfaceC2772a interfaceC2772a, x xVar) {
        this.f23064y = x0Var;
        this.f23065z = r0Var;
        this.f23055A = r0Var2;
        this.B = r0Var3;
        this.f23056C = f10;
        this.f23057D = g7;
        this.f23058E = interfaceC2772a;
        this.f23059F = xVar;
        io.sentry.config.a.f(0, 0, 15);
        this.f23062I = new C2568D(this, 0);
        this.f23063J = new C2568D(this, 1);
    }

    public final g0.c H0() {
        g0.c cVar;
        if (this.f23064y.f().b(w.l, w.f23145m)) {
            t tVar = this.f23056C.f23067a.c;
            if (tVar == null || (cVar = tVar.f23139a) == null) {
                t tVar2 = this.f23057D.f23069a.c;
                if (tVar2 != null) {
                    return tVar2.f23139a;
                }
                return null;
            }
        } else {
            t tVar3 = this.f23057D.f23069a.c;
            if (tVar3 == null || (cVar = tVar3.f23139a) == null) {
                t tVar4 = this.f23056C.f23067a.c;
                if (tVar4 != null) {
                    return tVar4.f23139a;
                }
                return null;
            }
        }
        return cVar;
    }

    @Override // E0.InterfaceC0174y
    public final int b(Q q4, C0.J j3, int i7) {
        return j3.c(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int c(Q q4, C0.J j3, int i7) {
        return j3.P(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int d(Q q4, C0.J j3, int i7) {
        return j3.V(i7);
    }

    @Override // E0.InterfaceC0174y
    public final int f(Q q4, C0.J j3, int i7) {
        return j3.a0(i7);
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        if (this.f23064y.f23661a.P0() == this.f23064y.d.getValue()) {
            this.f23061H = null;
        } else if (this.f23061H == null) {
            g0.c cVarH0 = H0();
            if (cVarH0 == null) {
                cVarH0 = g0.b.l;
            }
            this.f23061H = cVarH0;
        }
        boolean zV = m10.v();
        n7.v vVar = n7.v.l;
        if (zV) {
            V vB = j3.b(j10);
            long jC = H7.H.c(vB.l, vB.f871m);
            this.f23060G = jC;
            return m10.m0((int) (jC >> 32), (int) (jC & 4294967295L), vVar, new X(vB, 13));
        }
        if (!((Boolean) this.f23058E.a()).booleanValue()) {
            V vB2 = j3.b(j10);
            return m10.m0(vB2.l, vB2.f871m, vVar, new X(vB2, 14));
        }
        x xVar = this.f23059F;
        r0 r0Var = xVar.f23147a;
        F f10 = xVar.d;
        G g7 = xVar.f23149e;
        q0 q0VarA = r0Var != null ? r0Var.a(new y(f10, g7, 0), new y(f10, g7, 1)) : null;
        r0 r0Var2 = xVar.f23148b;
        q0 q0VarA2 = r0Var2 != null ? r0Var2.a(new y(f10, g7, 2), new y(f10, g7, 3)) : null;
        if (xVar.c.f23661a.P0() == w.l) {
            P p10 = g7.f23069a;
        } else {
            P p11 = g7.f23069a;
        }
        r0 r0Var3 = xVar.f23150f;
        C0022k c0022k = new C0022k(q0VarA, q0VarA2, r0Var3 != null ? r0Var3.a(C2584o.f23125t, new C0022k(null, f10, g7, 21)) : null, 20);
        V vB3 = j3.b(j10);
        long jC2 = H7.H.c(vB3.l, vB3.f871m);
        long j11 = !a1.j.a(this.f23060G, s.f23138a) ? this.f23060G : jC2;
        r0 r0Var4 = this.f23065z;
        q0 q0VarA3 = r0Var4 != null ? r0Var4.a(this.f23062I, new C2567C(0, j11, this)) : null;
        if (q0VarA3 != null) {
            jC2 = ((a1.j) q0VarA3.getValue()).f11037a;
        }
        long jP = io.sentry.config.a.P(j10, jC2);
        r0 r0Var5 = this.f23055A;
        long j12 = r0Var5 != null ? ((a1.h) r0Var5.a(C2584o.f23128w, new C2567C(1, j11, this)).getValue()).f11033a : 0L;
        r0 r0Var6 = this.B;
        long j13 = r0Var6 != null ? ((a1.h) r0Var6.a(this.f23063J, new C2567C(2, j11, this)).getValue()).f11033a : 0L;
        g0.c cVar = this.f23061H;
        return m10.m0((int) (jP >> 32), (int) (4294967295L & jP), vVar, new C2566B(vB3, a1.h.d(cVar != null ? cVar.a(j11, jP, a1.k.l) : 0L, j13), j12, c0022k, 0));
    }

    @Override // g0.n
    public final void z0() {
        this.f23060G = s.f23138a;
    }
}
