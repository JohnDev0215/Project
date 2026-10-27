package b.b.g.n.l;

import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import b.b.g.n.q.g;
import com.byd.carsettings.ui.fragment.dipilot.ActiveSafeFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ActiveSafePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b<ActiveSafeFragment> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f1159e = g.g();

    public int A() {
        return this.f1160a.y();
    }

    public void A0(int i) {
        this.f1160a.N0(i);
    }

    public int B() {
        return this.f1160a.z();
    }

    public void B0(int i) {
        this.f1160a.O0(i);
    }

    public int C() {
        return this.f1160a.A();
    }

    public void C0(int i) {
        this.f1160a.P0(i);
    }

    public int D() {
        return this.f1160a.B();
    }

    public void D0(int i) {
        this.f1160a.Q0(i);
    }

    public int E() {
        return this.f1160a.E();
    }

    public void E0(int i) {
        this.f1160a.R0(i);
    }

    public byte F() {
        BYDAutoDiPilotDevice bYDAutoDiPilotDeviceD = d();
        if (bYDAutoDiPilotDeviceD != null) {
            return (byte) bYDAutoDiPilotDeviceD.getLDWSensitivity();
        }
        return (byte) 0;
    }

    public void F0(boolean z) {
        this.f1160a.S0(z);
    }

    public int G() {
        return this.f1160a.F();
    }

    public void G0(int i) {
        this.f1160a.T0(i);
    }

    public boolean H() {
        b.b.g.n.q.d dVar = this.f1160a;
        return dVar != null && dVar.G();
    }

    public void H0(int i) {
        this.f1160a.U0(i);
    }

    public int I() {
        b.b.g.n.q.d dVar = this.f1160a;
        if (dVar != null) {
            return dVar.H();
        }
        return 0;
    }

    public void I0(int i) {
        this.f1160a.W0(i);
    }

    public int J() {
        return this.f1160a.I();
    }

    public int K() {
        return this.f1160a.J();
    }

    public int L() {
        return this.f1160a.K();
    }

    public int M() {
        return this.f1160a.L();
    }

    public int N() {
        return this.f1160a.M();
    }

    public int O() {
        return this.f1160a.N();
    }

    public int P() {
        return this.f1160a.O();
    }

    public int Q() {
        return this.f1160a.P();
    }

    public int R() {
        return this.f1160a.Q();
    }

    public int S() {
        return this.f1160a.R();
    }

    public int T() {
        return this.f1160a.S();
    }

    public int U() {
        return this.f1160a.T();
    }

    public int V() {
        return this.f1161b.j0();
    }

    public boolean W() {
        return this.f1161b.r0();
    }

    public boolean X() {
        return this.f1160a.U();
    }

    public int Y() {
        return this.f1160a.V();
    }

    public int Z() {
        return this.f1160a.W();
    }

    public int a0() {
        return this.f1160a.X();
    }

    public int b0() {
        return this.f1160a.Y();
    }

    public int c0() {
        return this.f1160a.Z();
    }

    public int d0() {
        return this.f1160a.a0();
    }

    public void e0(int i) {
        this.f1160a.g0(i);
    }

    public void f0(int i) {
        this.f1160a.h0(i);
    }

    public int g0(int i) {
        int iI0 = this.f1160a.i0(i);
        BydLogHelper.logD("setAiMatrixLightState " + iI0);
        return iI0;
    }

    public int h() {
        return this.f1160a.d();
    }

    public void h0(int i) {
        this.f1160a.k0(i);
    }

    public int i() {
        return this.f1160a.e();
    }

    public void i0(int i) {
        this.f1160a.l0(i);
    }

    public int j() {
        return this.f1160a.f();
    }

    public void j0(int i) {
        this.f1160a.m0(i);
    }

    public void k0(int i) {
        b.b.g.n.q.d dVar = this.f1160a;
        if (dVar != null) {
            dVar.n0(i);
        }
    }

    public int l() {
        int iG = this.f1160a.g();
        BydLogHelper.logD("getAiMatrixLight3ACState " + iG);
        return iG;
    }

    public void l0(int i) {
        this.f1159e.j(i);
    }

    public int m() {
        int iH = this.f1160a.h();
        BydLogHelper.logD("getAiMatrixLight3ADState " + iH);
        return iH;
    }

    public void m0(int i) {
        this.f1160a.s0(i);
    }

    public int n() {
        return this.f1160a.i();
    }

    public void n0(int i) {
        this.f1160a.t0(i);
    }

    public int o() {
        return this.f1160a.j();
    }

    public void o0(int i) {
        this.f1160a.w0(i);
    }

    public void p0(int i) {
        this.f1160a.x0(i);
    }

    public int q() {
        return this.f1160a.k();
    }

    public void q0(int i) {
        this.f1160a.A0(i);
    }

    public int r() {
        return this.f1160a.l();
    }

    public void r0(int i) {
        this.f1160a.B0(i);
    }

    public boolean s() {
        b.b.g.n.q.d dVar = this.f1160a;
        return dVar != null && dVar.m();
    }

    public void s0() {
        this.f1160a.E0();
    }

    public int t() {
        b.b.g.n.q.d dVar = this.f1160a;
        if (dVar != null) {
            return dVar.n();
        }
        return 0;
    }

    public void t0(int i) {
        this.f1160a.F0(i);
    }

    public int u() {
        return this.f1159e.a();
    }

    public void u0(int i) {
        this.f1160a.G0(i);
    }

    public int v() {
        return this.f1160a.r();
    }

    public void v0(int i) {
        b.b.g.n.q.d dVar = this.f1160a;
        if (dVar != null) {
            dVar.H0(i);
        }
    }

    public int w() {
        return this.f1160a.s();
    }

    public void w0(int i) {
        this.f1160a.I0(i);
    }

    public int x() {
        return this.f1160a.t();
    }

    public void x0(int i) {
        this.f1160a.J0(i);
    }

    public int y() {
        return this.f1160a.u();
    }

    public void y0(int i) {
        this.f1160a.L0(i);
    }

    public int z() {
        return this.f1160a.v();
    }

    public void z0(int i) {
        this.f1160a.M0(i);
    }
}
