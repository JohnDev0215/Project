package b.b.g.n.h;

import b.b.g.n.q.t;
import com.byd.carsettings.ui.fragment.carsettings.CockpitPerFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CockpitPerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.m.e.g<CockpitPerFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.d.c f1012a = new b.b.g.d.c();

    public int c() {
        int iA = b.b.g.n.q.b.c().a();
        BydLogHelper.logD(f.class.getSimpleName(), "carType : " + iA);
        if (iA == 196) {
            return b.b.g.n.q.m.p0().x();
        }
        if (iA == 182 || iA == 208) {
            return b.b.g.n.q.m.p0().y();
        }
        BydLogHelper.logD(f.class.getSimpleName(), "not return valid value ");
        return 0;
    }

    public byte d() {
        return (byte) b.b.g.n.q.d.x().o();
    }

    public boolean e() {
        b.b.g.d.c cVar = this.f1012a;
        if (cVar != null) {
            return cVar.b();
        }
        return false;
    }

    public void f() {
        b.b.a.h.a.b(this, " getDmsSwitchChanged  ");
        t.b().a();
    }

    public boolean g() {
        b.b.g.d.c cVar = this.f1012a;
        if (cVar != null) {
            return cVar.c();
        }
        return false;
    }

    public boolean h() {
        return this.f1012a.d();
    }

    public void i() {
        b.b.a.h.a.b(this, " queryAttentionDetect : ");
        t.b().d();
    }

    public void j() {
        b.b.a.h.a.b(this, " queryDmsMode  ");
        t.b().e();
    }

    public void l() {
        b.b.a.h.a.b(this, " queryDmsServiceStatus ");
        t.b().f();
        b.b.g.r.e.i(240004);
    }

    public void m() {
        b.b.a.h.a.b(this, " queryFatigueDetect ");
        t.b().g();
    }

    public void n(int i, int i2) {
        b.b.a.h.a.b(this, " setAttentionDetect : " + i + " lastState : " + i2);
        t.b().i(i);
        b.b.g.r.e.j(240003, i2);
        i();
    }

    public void o(int i) {
        int iA = b.b.g.n.q.b.c().a();
        BydLogHelper.logD(f.class.getSimpleName(), "carType : " + iA);
        if (iA == 196) {
            b.b.g.n.q.m.p0().h2(i);
        }
        if (iA == 182 || iA == 208) {
            b.b.g.n.q.m.p0().i2(i);
        }
    }

    public void q(int i) {
        b.b.g.n.q.d.x().p0(i);
    }

    public void r(int i, int i2) {
        b.b.a.h.a.b(this, " setDmsMode :  " + i + " lastState : " + i2);
        t.b().j(i);
        b.b.g.r.e.j(240001, i2);
    }

    public void s(int i, int i2) {
        b.b.a.h.a.b(this, " setDmsSwtichStatus:  " + i + " lastState : " + i2);
        t.b().k(i);
        b.b.g.r.e.j(240000, i2);
        f();
    }

    public void t(int i, int i2) {
        b.b.a.h.a.b(this, " setFatigueDetect : " + i + " lastState : " + i2);
        t.b().l(i);
        b.b.g.r.e.j(240002, i2);
        m();
    }
}
