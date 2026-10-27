package b.b.g.n.h;

import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import com.byd.carsettings.ui.fragment.carsettings.CarLightsFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CarlightsPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b.b.m.e.g<CarLightsFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.d.j f1011a = new b.b.g.d.j();

    public boolean A() {
        return this.f1011a.n();
    }

    public boolean B() {
        return this.f1011a.o();
    }

    public final boolean C() {
        int frontLightDimmingCtrlConfigBy3AC = BYDAutoStudy.getInstance(b.b.a.a.a.b().a()).getFrontLightDimmingCtrlConfigBy3AC();
        int frontLightDimmingCtrlConfigBy3AD = BYDAutoStudy.getInstance(b.b.a.a.a.b().a()).getFrontLightDimmingCtrlConfigBy3AD();
        BydLogHelper.logD(e.class.getSimpleName(), " value3Ac ： " + frontLightDimmingCtrlConfigBy3AC + " value3Ad ： " + frontLightDimmingCtrlConfigBy3AD);
        return frontLightDimmingCtrlConfigBy3AC == 1 || frontLightDimmingCtrlConfigBy3AD == 1;
    }

    public void D(boolean z) {
        b.b.g.n.q.i.k().o(z ? 1 : 2);
    }

    public void E(boolean z) {
        b.b.g.n.q.i.k().s(z ? 1 : 2);
    }

    public void F(boolean z) {
        BydLogHelper.logD(e.class.getSimpleName(), "setFrontFogLightSwitchState state : " + z);
        b.b.g.n.q.i.k().t(z ? 1 : 2);
    }

    public void G(boolean z) {
        b.b.g.n.q.m.p0().t3(z ? 1 : 2);
    }

    public void H(int i) {
        b.b.g.n.q.m.p0().Z1(i);
    }

    public void I(int i) {
        b.b.g.n.q.m.p0().K2(i);
    }

    public void J(int i) {
        b.b.g.n.q.m.p0().L2(i);
    }

    public void K(int i) {
        b.b.g.n.q.m.p0().w2(i);
    }

    public void L(boolean z) {
        BydLogHelper.logD(e.class.getSimpleName(), "setRearFogLightSwitchState state : " + z);
        b.b.g.n.q.i.k().v(z ? 1 : 2);
    }

    public boolean c() {
        return b.b.g.n.q.i.k().a() == 1;
    }

    public boolean d() {
        return this.f1011a.b();
    }

    public int e() {
        int iP = b.b.g.n.q.m.p0().p();
        if (iP == 0 || iP == 7) {
            return 0;
        }
        return iP;
    }

    public boolean f() {
        int iG = b.b.g.n.q.i.k().g();
        return (iG == 1 || iG == 2) ? iG == 1 : b.b.g.n.q.i.k().f();
    }

    public int g() {
        return b.b.g.n.q.i.k().g();
    }

    public boolean h() {
        return this.f1011a.c();
    }

    public int i() {
        int i = b.b.g.n.q.i.k().i();
        BydLogHelper.logD(e.class.getSimpleName(), "getLightControlMode mode : " + i);
        return i;
    }

    public boolean j() {
        return b.b.g.n.q.m.p0().p1() == 1;
    }

    public boolean l() {
        return this.f1011a.d();
    }

    public int m() {
        return this.f1011a.e();
    }

    public int n() {
        int iU0 = b.b.g.n.q.m.p0().u0();
        if (iU0 < 1 || iU0 > 3) {
            return 1;
        }
        return iU0;
    }

    public boolean o() {
        return this.f1011a.g();
    }

    public int q() {
        int iW0 = b.b.g.n.q.m.p0().w0();
        if (iW0 == 0 || iW0 == 7) {
            return 0;
        }
        return iW0;
    }

    public boolean r() {
        return this.f1011a.h();
    }

    public int s() {
        int iJ = b.b.g.n.q.i.k().j();
        BydLogHelper.logD(e.class.getSimpleName(), "getLightControlMode mode : " + iJ);
        return Math.max(1, iJ);
    }

    public boolean t() {
        return this.f1011a.i();
    }

    public boolean u() {
        return this.f1011a.j();
    }

    public int v() {
        if (y() && C()) {
            int iV0 = b.b.g.n.q.m.p0().v0();
            BydLogHelper.logD(e.class.getSimpleName(), " leftLevel ： " + iV0);
            return iV0;
        }
        int iD0 = b.b.g.n.q.m.p0().d0();
        BydLogHelper.logD(e.class.getSimpleName(), " level ： " + iD0);
        return Math.max(iD0, 1);
    }

    public int w() {
        int iL = b.b.g.n.q.i.k().l();
        BydLogHelper.logD(e.class.getSimpleName(), "getLightControlMode mode : " + iL);
        return iL;
    }

    public boolean x() {
        return this.f1011a.p();
    }

    public final boolean y() {
        boolean z = b.b.g.n.q.b.c().e(37) == 1;
        boolean z2 = b.b.g.n.q.b.c().e(38) == 1;
        BydLogHelper.logD(e.class.getSimpleName(), " is3AdOnline ： " + z2 + " is3AcOnline ： " + z);
        return z || z2;
    }

    public boolean z() {
        return this.f1011a.m();
    }
}
