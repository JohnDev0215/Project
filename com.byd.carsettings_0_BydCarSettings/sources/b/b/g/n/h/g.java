package b.b.g.n.h;

import com.byd.carsettings.ui.fragment.carsettings.ComfortDrFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ComfortDrPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.g<ComfortDrFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.d.d f1013a = new b.b.g.d.d();

    public boolean A() {
        return this.f1013a.j();
    }

    public int B() {
        return this.f1013a.k();
    }

    public void C(int i) {
        b.b.g.n.q.m.p0().T1(i);
    }

    public void D(int i) {
        b.b.g.n.q.m.p0().X1(i);
    }

    public void E(int i) {
        b.b.g.n.q.m.p0().o2(i);
    }

    public void F(int i) {
        b.b.g.n.q.m.p0().S2(i);
    }

    public void G(int i) {
        int i2 = i();
        if (i2 == 1) {
            BydLogHelper.logD(g.class.getSimpleName(), "left " + i);
            b.b.g.n.q.m.p0().b3(i);
            return;
        }
        if (i2 == 2) {
            BydLogHelper.logD(g.class.getSimpleName(), "right " + i);
            b.b.g.n.q.m.p0().a3(i);
            return;
        }
        if (i2 != 3) {
            return;
        }
        BydLogHelper.logD(g.class.getSimpleName(), "left and right " + i);
        b.b.g.n.q.m.p0().b3(i);
        b.b.g.n.q.m.p0().a3(i);
    }

    public void H(int i) {
        b.b.g.n.q.m.p0().c3(i);
    }

    public void I(int i) {
        b.b.g.n.q.m.p0().r3(i);
    }

    public void J(int i) {
        b.b.g.n.q.m.p0().v3(i);
    }

    public boolean c() {
        return b.b.g.n.q.m.p0().i() == 1;
    }

    public boolean d() {
        return b.b.g.n.q.m.p0().n() == 1;
    }

    public boolean e() {
        return f() == 1;
    }

    public int f() {
        int iH = b.b.g.n.q.m.p0().H();
        return (iH == 1 || iH == 2) ? iH : b.b.g.n.q.m.p0().G();
    }

    public int g() {
        int iB0 = b.b.g.n.q.m.p0().b0();
        BydLogHelper.logD(g.class.getSimpleName(), "getGoalRoadMode : " + iB0);
        return iB0;
    }

    public boolean h() {
        return b.b.g.n.q.m.p0().C0() == 0;
    }

    public int i() {
        return this.f1013a.b();
    }

    public int j() {
        int i = i();
        if (i == 1) {
            int iQ0 = b.b.g.n.q.m.p0().Q0();
            BydLogHelper.logD(g.class.getSimpleName(), "leftValue " + iQ0);
            return iQ0;
        }
        if (i == 2) {
            int iP0 = b.b.g.n.q.m.p0().P0();
            BydLogHelper.logD(g.class.getSimpleName(), "rightValue " + iP0);
            return iP0;
        }
        if (i != 3) {
            return 2;
        }
        int iQ02 = b.b.g.n.q.m.p0().Q0();
        int iP02 = b.b.g.n.q.m.p0().P0();
        BydLogHelper.logD(g.class.getSimpleName(), "left " + iQ02 + ", right " + iP02);
        return (iQ02 == 1 && iP02 == 1) ? 1 : 2;
    }

    public int l() {
        return b.b.g.n.q.m.p0().R0();
    }

    public boolean m() {
        return b.b.g.n.q.m.p0().S0() == 1;
    }

    public int n() {
        int iT0 = b.b.g.n.q.m.p0().T0();
        if (iT0 == 1 || iT0 == 2) {
            return iT0;
        }
        return 0;
    }

    public int o() {
        int iL = l();
        return (iL == 2 || iL == 1) ? iL : b.b.g.n.q.m.p0().S0();
    }

    public int q() {
        int iK1 = b.b.g.n.q.m.p0().k1();
        BydLogHelper.logD(g.class.getSimpleName(), "getSandAbsState  : " + iK1);
        return iK1;
    }

    public boolean r() {
        return b.b.g.n.q.m.p0().u1() == 1;
    }

    public boolean s() {
        return this.f1013a.d();
    }

    public boolean t() {
        return this.f1013a.e();
    }

    public boolean u() {
        return this.f1013a.c();
    }

    public boolean v() {
        return this.f1013a.f();
    }

    public boolean w() {
        return this.f1013a.h();
    }

    public boolean x() {
        int iF = b.b.g.n.q.m.p0().F();
        BydLogHelper.logD(g.class.getSimpleName(), "isSandMode  : " + iF);
        return iF == 5;
    }

    public boolean y() {
        return this.f1013a.g();
    }

    public boolean z() {
        return this.f1013a.i();
    }
}
