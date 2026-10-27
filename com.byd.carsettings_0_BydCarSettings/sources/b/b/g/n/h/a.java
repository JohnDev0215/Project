package b.b.g.n.h;

import b.b.g.n.q.u;
import com.byd.carsettings.ui.fragment.carsettings.AiChassisFragment;

/* JADX INFO: compiled from: AiChassisPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b.b.m.e.g<AiChassisFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.d.a f1007a = new b.b.g.d.a();

    public int c() {
        int iQ = b.b.g.n.q.m.p0().q();
        if (iQ >= 2) {
            return 0;
        }
        return iQ;
    }

    public int d() {
        int iV = b.b.g.n.q.m.p0().v();
        return (iV < 1 || iV > 3) ? b.b.g.n.q.m.p0().O() : iV;
    }

    public boolean e() {
        int iW = b.b.g.n.q.m.p0().w();
        return iW == 1 || iW == 2;
    }

    public int f() {
        return b.b.g.n.q.m.p0().w();
    }

    public int g() {
        return this.f1007a.b();
    }

    public int h() {
        int I = u.I().A() ? b.b.g.n.q.m.p0().I() : b.b.g.n.q.m.p0().P();
        if (I > 0) {
            return I - 1;
        }
        return 0;
    }

    public int i() {
        int iS1 = b.b.g.n.q.m.p0().s1();
        return (iS1 > 4 || iS1 < 1 || iS1 < 3) ? 0 : 1;
    }

    public boolean j() {
        return this.f1007a.c();
    }

    public boolean l() {
        return this.f1007a.d();
    }

    public boolean m() {
        return this.f1007a.e();
    }

    public boolean n() {
        return b.b.g.n.q.m.p0().t1() == 0;
    }

    public boolean o() {
        return this.f1007a.f();
    }

    public boolean q() {
        return this.f1007a.g();
    }

    public void r(int i) {
        b.b.g.n.q.m.p0().a2(i);
    }

    public void s(int i) {
        b.b.g.n.q.m.p0().f2(i);
    }

    public void t(int i) {
        b.b.g.n.q.m.p0().g2(i);
    }

    public void u(int i) {
        b.b.g.n.q.m.p0().p2(i + 1);
    }

    public void v(int i) {
        b.b.g.n.q.m.p0().u3(i);
    }
}
