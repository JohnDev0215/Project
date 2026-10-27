package b.b.g.n.h;

import b.b.g.d.p;
import b.b.g.r.v;
import com.byd.carsettings.ui.fragment.carsettings.CarSetSeatFragment;

/* JADX INFO: compiled from: CarSetSeatPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.g<CarSetSeatFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f1010a = new p();

    public boolean A() {
        return this.f1010a.I();
    }

    public boolean B() {
        for (int i = 1; i <= 4; i++) {
            if (l(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean C() {
        return this.f1010a.J();
    }

    public boolean D() {
        return this.f1010a.K();
    }

    public void E() {
    }

    public void F(int i, int i2) {
        b.b.g.n.q.m.p0().J1(i, i2);
    }

    public void G(int i, int i2) {
        b.b.g.n.q.m.p0().M1(i, i2);
    }

    public void H(int i, boolean z) {
        b.b.g.n.q.m.p0().z3(i, z ? 2 : 0);
    }

    public void I(int i, boolean z) {
        b.b.g.n.q.m.p0().z3(i, z ? 1 : 0);
    }

    public void J(int i, boolean z) {
        b.b.g.n.q.m.p0().A3(i, z ? 4 : 0);
    }

    public void K(int i, boolean z) {
        b.b.g.n.q.m.p0().A3(i, z ? 3 : 0);
    }

    public void L(int i) {
        v.d().i("main_selected", i);
    }

    public void M(int i) {
        v.d().i("mass_position", i);
    }

    public void N(int i, int i2) {
        b.b.g.n.q.m.p0().s3(i, i2);
    }

    public void O(int i, int i2) {
        b.b.g.n.q.m.p0().P2(i, i2);
    }

    public void P(int i, boolean z) {
        b.b.g.n.q.m.p0().D3(i, z ? 2 : 0);
    }

    public void Q(int i, boolean z) {
        b.b.g.n.q.m.p0().D3(i, z ? 1 : 0);
    }

    public void R(int i, boolean z) {
        b.b.g.n.q.m.p0().E3(i, z ? 2 : 0);
    }

    public void S(int i, boolean z) {
        b.b.g.n.q.m.p0().E3(i, z ? 1 : 0);
    }

    public void T(int i, boolean z) {
        b.b.g.n.q.m.p0().F3(i, z ? 2 : 0);
    }

    public void U(int i, boolean z) {
        b.b.g.n.q.m.p0().F3(i, z ? 1 : 0);
    }

    public void V(int i, boolean z) {
        b.b.g.n.q.m.p0().B3(i, z ? 2 : 0);
    }

    public void W(int i, boolean z) {
        b.b.g.n.q.m.p0().B3(i, z ? 1 : 0);
    }

    public void X(int i, boolean z) {
        b.b.g.n.q.m.p0().C3(i, z ? 2 : 0);
    }

    public void Y(int i, boolean z) {
        b.b.g.n.q.m.p0().C3(i, z ? 1 : 0);
    }

    public void Z(boolean z) {
        b.b.g.n.q.m.p0().G3(z ? 2 : 0);
    }

    public void a0(boolean z) {
        b.b.g.n.q.m.p0().G3(z ? 1 : 0);
    }

    public void b0(boolean z) {
        b.b.g.n.q.m.p0().G3(z ? 4 : 0);
    }

    public boolean c(int i) {
        return this.f1010a.b(i);
    }

    public void c0(boolean z) {
        b.b.g.n.q.m.p0().G3(z ? 3 : 0);
    }

    public boolean d(int i) {
        return this.f1010a.c(i);
    }

    public boolean e(int i) {
        return this.f1010a.d(i);
    }

    public boolean f(int i) {
        return this.f1010a.e(i);
    }

    public int g() {
        return v.d().e("main_selected", 1);
    }

    public boolean h(int i) {
        return this.f1010a.g(i);
    }

    public int i(int i) {
        int iJ;
        return ((i == 3 || i == 4) && (iJ = b.b.g.n.q.m.p0().J(i)) > 0 && iJ <= 3) ? iJ : b.b.g.n.q.m.p0().m1(i);
    }

    public int j(int i) {
        int iK;
        return ((i == 3 || i == 4) && (iK = b.b.g.n.q.m.p0().K(i)) > 0 && iK <= 7) ? iK : b.b.g.n.q.m.p0().n1(i);
    }

    public boolean l(int i) {
        p pVar;
        if (i == 1) {
            p pVar2 = this.f1010a;
            if (pVar2 != null) {
                return pVar2.v();
            }
            return false;
        }
        if (i == 2) {
            p pVar3 = this.f1010a;
            if (pVar3 != null) {
                return pVar3.A();
            }
            return false;
        }
        if (i != 3) {
            if (i == 4 && (pVar = this.f1010a) != null) {
                return pVar.q();
            }
            return false;
        }
        p pVar4 = this.f1010a;
        if (pVar4 != null) {
            return pVar4.p();
        }
        return false;
    }

    public boolean m(int i) {
        return this.f1010a.i(i);
    }

    public boolean n(int i) {
        return this.f1010a.k(i);
    }

    public boolean o(int i) {
        return this.f1010a.l(i);
    }

    public boolean q() {
        return this.f1010a.m();
    }

    public boolean r() {
        return this.f1010a.r();
    }

    public boolean s() {
        return this.f1010a.u();
    }

    public boolean t() {
        return this.f1010a.w();
    }

    public boolean u() {
        return this.f1010a.z();
    }

    public boolean v() {
        return this.f1010a.B();
    }

    public boolean w() {
        return this.f1010a.C();
    }

    public boolean x() {
        return this.f1010a.D();
    }

    public boolean y() {
        return this.f1010a.E();
    }

    public boolean z() {
        return this.f1010a.F();
    }
}
