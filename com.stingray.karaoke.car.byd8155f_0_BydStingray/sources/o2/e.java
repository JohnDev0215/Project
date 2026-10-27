package o2;

import android.os.Looper;
import android.os.SystemClock;
import android.util.SparseArray;
import e2.C1195c;
import e2.C1202j;
import e2.F;
import e2.I;
import e2.K;
import e2.O;
import e2.P;
import e2.S;
import e2.T;
import e2.U;
import e2.V;
import e2.W;
import e2.c0;
import e2.e0;
import e2.f0;
import e2.l0;
import e2.n0;
import e2.q0;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.o;
import h2.v;
import h2.x;
import io.sentry.C1458d0;
import java.io.IOException;
import java.util.List;
import k3.C1616J;
import k3.X0;
import n2.C1913m;
import n2.C1919t;
import u2.C2492C;
import u2.C2516t;
import u2.C2521y;
import u2.InterfaceC2497H;

/* JADX INFO: loaded from: classes.dex */
public final class e implements U, InterfaceC2497H, q2.l {
    public final v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final c0 f20405m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e0 f20406n;
    public final H0.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final SparseArray f20407p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public o f20408q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public W f20409r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public x f20410s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f20411t;

    public e(v vVar) {
        vVar.getClass();
        this.l = vVar;
        this.f20408q = new o(AbstractC1343A.q(), vVar, new C2025b(14));
        c0 c0Var = new c0();
        this.f20405m = c0Var;
        this.f20406n = new e0();
        this.o = new H0.b(c0Var);
        this.f20407p = new SparseArray();
    }

    @Override // e2.U
    public final void A(C1202j c1202j) {
        Z(U(), 29, new d(9));
    }

    @Override // u2.InterfaceC2497H
    public final void B(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Z(X(i7, c2492c), 1002, new d(15));
    }

    @Override // q2.l
    public final void C(int i7, C2492C c2492c) {
        Z(X(i7, c2492c), 1023, new d(21));
    }

    @Override // e2.U
    public final void D(C1195c c1195c) {
        Z(Y(), 20, new d(2));
    }

    @Override // e2.U
    public final void E(T t10) {
    }

    @Override // e2.U
    public final void F(I i7) {
        Z(U(), 14, new d(5));
    }

    @Override // e2.U
    public final void G(P p10) {
        Z(U(), 12, new X0(27));
    }

    @Override // e2.U
    public final void H(O o) {
        C2492C c2492c;
        Z((!(o instanceof C1913m) || (c2492c = ((C1913m) o).f19698z) == null) ? U() : W(c2492c), 10, new C2025b(10));
    }

    @Override // e2.U
    public final void I(boolean z6) {
        Z(U(), 9, new d(7));
    }

    @Override // e2.U
    public final void J(n0 n0Var) {
        Z(U(), 2, new C2025b(7));
    }

    @Override // e2.U
    public final void K(int i7, int i10) {
        Z(Y(), 24, new C2025b(27));
    }

    @Override // e2.U
    public final void L(int i7, F f10) {
        Z(U(), 1, new X0(28));
    }

    @Override // e2.U
    public final void M(q0 q0Var) {
        C2024a c2024aY = Y();
        Z(c2024aY, 25, new C1919t(c2024aY, q0Var));
    }

    @Override // e2.U
    public final void N(f0 f0Var, int i7) {
        W w10 = this.f20409r;
        w10.getClass();
        H0.b bVar = this.o;
        bVar.f2767p = H0.b.v(w10, (d5.I) bVar.f2766n, (C2492C) bVar.f2768q, (c0) bVar.f2765m);
        bVar.P(w10.t0());
        Z(U(), 0, new d(25));
    }

    @Override // e2.U
    public final void O(K k10) {
        Z(U(), 28, new C2025b(2));
    }

    @Override // u2.InterfaceC2497H
    public final void P(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y, IOException iOException, boolean z6) {
        C2024a c2024aX = X(i7, c2492c);
        Z(c2024aX, 1003, new C2026c(c2024aX, c2516t, c2521y, iOException, z6));
    }

    @Override // e2.U
    public final void Q(S s6) {
        Z(U(), 13, new d(24));
    }

    @Override // e2.U
    public final void R(C1290c c1290c) {
        Z(U(), 27, new C2025b(25));
    }

    @Override // e2.U
    public final void S(int i7, boolean z6) {
        Z(U(), 30, new d(3));
    }

    @Override // e2.U
    public final void T(boolean z6) {
        Z(U(), 7, new C2025b(4));
    }

    public final C2024a U() {
        return W((C2492C) this.o.f2767p);
    }

    public final C2024a V(f0 f0Var, int i7, C2492C c2492c) {
        long jX;
        C2492C c2492c2 = f0Var.p() ? null : c2492c;
        this.l.getClass();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z6 = f0Var.equals(this.f20409r.t0()) && i7 == this.f20409r.b0();
        long jT = 0;
        if (c2492c2 == null || !c2492c2.c()) {
            if (z6) {
                jX = this.f20409r.x();
                return new C2024a(jElapsedRealtime, f0Var, i7, c2492c2, jX, this.f20409r.t0(), this.f20409r.b0(), (C2492C) this.o.f2767p, this.f20409r.N0(), this.f20409r.y());
            }
            if (!f0Var.p()) {
                jT = AbstractC1343A.T(f0Var.m(i7, this.f20406n, 0L).l);
            }
        } else if (z6 && this.f20409r.a0() == c2492c2.f22664b && this.f20409r.k0() == c2492c2.c) {
            jT = this.f20409r.N0();
        }
        jX = jT;
        return new C2024a(jElapsedRealtime, f0Var, i7, c2492c2, jX, this.f20409r.t0(), this.f20409r.b0(), (C2492C) this.o.f2767p, this.f20409r.N0(), this.f20409r.y());
    }

    public final C2024a W(C2492C c2492c) {
        this.f20409r.getClass();
        f0 f0Var = c2492c == null ? null : (f0) ((d5.K) this.o.o).get(c2492c);
        if (c2492c != null && f0Var != null) {
            return V(f0Var, f0Var.g(c2492c.f22663a, this.f20405m).c, c2492c);
        }
        int iB0 = this.f20409r.b0();
        f0 f0VarT0 = this.f20409r.t0();
        if (iB0 >= f0VarT0.o()) {
            f0VarT0 = f0.f14097a;
        }
        return V(f0VarT0, iB0, null);
    }

    public final C2024a X(int i7, C2492C c2492c) {
        this.f20409r.getClass();
        if (c2492c != null) {
            return ((f0) ((d5.K) this.o.o).get(c2492c)) != null ? W(c2492c) : V(f0.f14097a, i7, c2492c);
        }
        f0 f0VarT0 = this.f20409r.t0();
        if (i7 >= f0VarT0.o()) {
            f0VarT0 = f0.f14097a;
        }
        return V(f0VarT0, i7, null);
    }

    public final C2024a Y() {
        return W((C2492C) this.o.f2769r);
    }

    public final void Z(C2024a c2024a, int i7, h2.l lVar) {
        this.f20407p.put(i7, c2024a);
        this.f20408q.f(i7, lVar);
    }

    @Override // q2.l
    public final void a(int i7, C2492C c2492c, int i10) {
        Z(X(i7, c2492c), 1022, new d(16));
    }

    public final void a0(W w10, Looper looper) {
        AbstractC1346c.j(this.f20409r == null || ((d5.I) this.o.f2766n).isEmpty());
        this.f20409r = w10;
        this.f20410s = this.l.a(looper, null);
        o oVar = this.f20408q;
        this.f20408q = new o(oVar.d, looper, oVar.f15222a, new C1458d0(this, 23, w10), oVar.f15228i);
    }

    @Override // q2.l
    public final void b(int i7, C2492C c2492c) {
        Z(X(i7, c2492c), 1027, new d(19));
    }

    @Override // q2.l
    public final void c(int i7, C2492C c2492c, Exception exc) {
        Z(X(i7, c2492c), 1024, new d(17));
    }

    @Override // e2.U
    public final void d(int i7) {
        Z(U(), 8, new d(6));
    }

    @Override // e2.U
    public final void e(int i7) {
        Z(U(), 6, new C2025b(6));
    }

    @Override // e2.U
    public final void f(O o) {
        C2492C c2492c;
        C2024a c2024aU = (!(o instanceof C1913m) || (c2492c = ((C1913m) o).f19698z) == null) ? U() : W(c2492c);
        Z(c2024aU, 10, new k3.T(c2024aU, o));
    }

    @Override // e2.U
    public final void g(long j3) {
        Z(U(), 16, new d(11));
    }

    @Override // e2.U
    public final void h(boolean z6) {
    }

    @Override // e2.U
    public final void i(int i7) {
    }

    @Override // e2.U
    public final void j(long j3) {
        Z(U(), 17, new d(14));
    }

    @Override // e2.U
    public final void k(boolean z6) {
        Z(U(), 3, new d(22));
    }

    @Override // e2.U
    public final void l() {
    }

    @Override // e2.U
    public final void m(boolean z6) {
        Z(Y(), 23, new d(0));
    }

    @Override // q2.l
    public final void n(int i7, C2492C c2492c) {
        Z(X(i7, c2492c), 1025, new d(20));
    }

    @Override // e2.U
    public final void o(List list) {
        Z(U(), 27, new C2025b(13));
    }

    @Override // e2.U
    public final void p(int i7, boolean z6) {
        Z(U(), -1, new C2025b(1));
    }

    @Override // e2.U
    public final void q(int i7, boolean z6) {
        Z(U(), 5, new C2025b(11));
    }

    @Override // e2.U
    public final void r(float f10) {
        Z(Y(), 22, new d(1));
    }

    @Override // u2.InterfaceC2497H
    public final void s(int i7, C2492C c2492c, C2521y c2521y) {
        C2024a c2024aX = X(i7, c2492c);
        Z(c2024aX, 1004, new C1458d0(c2024aX, 24, c2521y));
    }

    @Override // e2.U
    public final void t(int i7, V v10, V v11) {
        if (i7 == 1) {
            this.f20411t = false;
        }
        W w10 = this.f20409r;
        w10.getClass();
        H0.b bVar = this.o;
        bVar.f2767p = H0.b.v(w10, (d5.I) bVar.f2766n, (C2492C) bVar.f2768q, (c0) bVar.f2765m);
        C2024a c2024aU = U();
        Z(c2024aU, 11, new C1616J(c2024aU, i7, v10, v11));
    }

    @Override // u2.InterfaceC2497H
    public final void u(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Z(X(i7, c2492c), 1000, new d(8));
    }

    @Override // u2.InterfaceC2497H
    public final void v(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Z(X(i7, c2492c), 1001, new d(18));
    }

    @Override // e2.U
    public final void w(l0 l0Var) {
        Z(U(), 19, new C2025b(28));
    }

    @Override // e2.U
    public final void x(int i7) {
        Z(U(), 4, new C2025b(17));
    }

    @Override // e2.U
    public final void y(long j3) {
        Z(U(), 18, new d(12));
    }

    @Override // e2.U
    public final void z(I i7) {
        Z(U(), 15, new d(4));
    }
}
