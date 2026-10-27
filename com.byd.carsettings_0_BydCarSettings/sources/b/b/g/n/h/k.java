package b.b.g.n.h;

import b.b.g.n.q.o;
import com.byd.carsettings.ui.fragment.carsettings.InstrumentFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: InstrumentPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class k extends b.b.m.e.g<InstrumentFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.d.i f1016a = new b.b.g.d.i();

    public int c() {
        int iF = b.b.g.n.q.h.m().f();
        BydLogHelper.logD(k.class.getSimpleName(), " getDistanceMileageUnit  value : " + iF);
        return iF;
    }

    public int d() {
        int iL = b.b.g.n.q.h.m().l();
        BydLogHelper.logD("getINSNavDisplayMode : " + iL);
        return iL;
    }

    public int e() {
        int iD = b.b.g.n.q.h.m().d();
        if (iD <= 0) {
            iD = 8;
        }
        return iD > 11 ? iD - 11 : iD;
    }

    public int f() {
        int iV = b.b.g.n.q.h.m().v();
        BydLogHelper.logD(k.class.getSimpleName(), " getMileageUnit  value : " + iV);
        return iV;
    }

    public float g(int i) {
        BydLogHelper.logD(k.class.getSimpleName(), " getMilesByType  type : " + i);
        if (i == 1) {
            return o.m().u();
        }
        if (i == 2) {
            return o.m().o(0);
        }
        if (i == 3) {
            return o.m().o(1);
        }
        if (i == 4) {
            return o.m().l();
        }
        if (i != 5) {
            return 0.0f;
        }
        return o.m().e();
    }

    public int h() {
        return b.b.g.n.q.h.m().w();
    }

    public int i() {
        return this.f1016a.b();
    }

    public int j() {
        return b.b.g.n.q.h.m().x();
    }

    public boolean l() {
        return this.f1016a.c();
    }

    public boolean m() {
        return this.f1016a.d();
    }

    public boolean n() {
        return this.f1016a.e();
    }

    public boolean o() {
        boolean z = this.f1016a.f() && b.b.a.i.a.b() == 180;
        BydLogHelper.logD(k.class.getSimpleName(), " isMileDisplaySup    : " + z);
        return z;
    }

    public boolean q() {
        return this.f1016a.g();
    }

    public void r(int i) {
        BydLogHelper.logD("setINSNavDisplayMode : " + i);
        b.b.g.n.q.h.m().R(i);
    }

    public void s(int i) {
        b.b.g.n.q.h.m().N(i);
    }

    public void t(int i) {
        b.b.g.n.q.h.m().U(i);
    }
}
