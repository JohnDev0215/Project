package b.b.g.n.h;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import com.byd.carsettings.ui.fragment.carsettings.AirConderFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: AirConderPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.e.g<AirConderFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.d.b f1008a = new b.b.g.d.b();

    public void A(int i) {
        b.b.g.n.q.m.p0().O1(i);
    }

    public void B(int i) {
        b.b.g.n.q.m.p0().P1(i);
    }

    public int C(int i) {
        return b.b.g.n.q.m.p0().Q1(i);
    }

    public void D(int i) {
        BydLogHelper.logD("setAcWarmMode mode ：" + i);
        b.b.g.n.q.m.p0().R1(i == 0 ? 1 : 2);
    }

    public void E(int i) {
        b.b.g.n.q.m.p0().W1(i);
    }

    public void F(int i) {
        b.b.g.n.q.m.p0().F2(i);
    }

    public void G(int i) {
        b.b.g.n.q.m.p0().U2(i);
    }

    public void H(int i) {
        b.b.g.n.q.m.p0().V2(i);
    }

    public void I(int i) {
        b.b.g.n.q.m.p0().W2(i);
    }

    public void J(int i) {
        BydLogHelper.logD("setRearPanelLockTime mode ：" + i);
        b.b.g.n.q.m.p0().Z2(i + 1);
    }

    public int K(int i) {
        return BYDAutoAcDevice.getInstance(b.b.a.a.a.b().a()).setAcRemoteCtrlTime(i + 1);
    }

    public int c() {
        int iA = b.b.g.n.q.m.p0().a();
        BydLogHelper.logD("getACAutoAir: value" + iA);
        return iA == 1 ? 0 : 1;
    }

    public boolean d() {
        return b.b.g.n.q.m.p0().b() == 1;
    }

    public boolean e() {
        return b.b.g.n.q.m.p0().c() == 1;
    }

    public boolean f() {
        return b.b.g.n.q.m.p0().d() == 1;
    }

    public int g() {
        int iE = b.b.g.n.q.m.p0().e();
        BydLogHelper.logD("getAcWarmMode value ：" + iE);
        return iE == 1 ? 0 : 1;
    }

    public Boolean h() {
        return Boolean.valueOf(b.b.g.n.q.m.p0().m() == 1);
    }

    public boolean i() {
        return b.b.g.n.q.m.p0().q0() == 1;
    }

    public boolean j() {
        return b.b.g.n.q.m.p0().E0() == 1;
    }

    public boolean l() {
        return b.b.g.n.q.m.p0().F0() == 1;
    }

    public boolean m() {
        return b.b.g.n.q.m.p0().G0() == 1;
    }

    public int n() {
        int iO0 = b.b.g.n.q.m.p0().O0();
        BydLogHelper.logD("getRearPanelLockTime value ：" + iO0);
        return Math.max(1, iO0);
    }

    public int o() {
        return Math.max(BYDAutoAcDevice.getInstance(b.b.a.a.a.b().a()).getAcRemoteCtrlTime() - 1, 0);
    }

    public boolean q() {
        return this.f1008a.b();
    }

    public boolean r() {
        return this.f1008a.c();
    }

    public boolean s() {
        return this.f1008a.d();
    }

    public boolean t() {
        return this.f1008a.e();
    }

    public boolean u() {
        return this.f1008a.f();
    }

    public boolean v() {
        return this.f1008a.g();
    }

    public boolean w() {
        return this.f1008a.h();
    }

    public boolean x() {
        return this.f1008a.i();
    }

    public boolean y() {
        return this.f1008a.j();
    }

    public void z(int i) {
        BydLogHelper.logD("setACAutoAir: value" + i);
        b.b.g.n.q.m.p0().N1(i == 0 ? 1 : 2);
    }
}
