package b.b.g.n.n;

import android.util.Log;
import androidx.appcompat.widget.ActivityChooserView;
import b.b.g.n.q.e;
import b.b.g.n.q.j;
import b.b.g.n.q.m;
import b.b.g.n.q.u;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.newenergy.ElectricityManageFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ElectricityManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends g<ElectricityManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1215a = b.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1216b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1217c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1218d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1219e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1220f = false;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public int m = 0;

    public boolean A() {
        return this.f1218d;
    }

    public boolean B() {
        return (b.b.g.n.q.b.c().e(32) == 1) && this.f1216b && this.f1220f;
    }

    public boolean C() {
        return !this.f1216b && this.j;
    }

    public boolean D() {
        return this.k && (b.b.g.n.q.b.c().e(51) == 1);
    }

    public int E(int i) {
        return b.b.g.n.q.c.K().i0(i);
    }

    public int F(int i) {
        return b.b.g.n.q.c.K().m0(i);
    }

    public int G(int i, int i2) {
        return b.b.g.n.q.c.K().n0(i, i2);
    }

    public int H(int i) {
        int i2 = this.m;
        if (i2 == 1) {
            i = 5 - i;
        } else if (i2 == 2) {
            i = ((4 - i) * 2) + 1;
        }
        Log.i(this.f1215a, "[充电限值]----[setChargeStopCapacityStateNotLi]:" + i);
        return b.b.g.n.q.c.K().o0(i);
    }

    public int I(int i) {
        return b.b.g.n.q.c.K().p0(i);
    }

    public int J(int i, int i2) {
        return m.p0().e2(i, i2);
    }

    public int K(int i) {
        return e.j().N(i);
    }

    public void L() {
        getV().g0(h(), false);
        int iL = l();
        if (iL != 1 && iL != 2 && iL != 3) {
            iL = 2;
        }
        getV().j0(iL, true, false);
        if (this.f1220f) {
            getV().i0(j());
        }
        if (this.h) {
            getV().m0(q());
        }
        getV().k0(m());
        if (this.i) {
            getV().f0(g(), false);
        }
        if (this.l) {
            getV().d0(d(), false);
        }
        if (x()) {
            getV().h0(i(), false);
        }
    }

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(ElectricityManageFragment electricityManageFragment) {
        super.bindV(electricityManageFragment);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return b.b.g.n.q.c.K().a();
    }

    public int e() {
        return b.b.g.n.q.c.K().c();
    }

    public int f() {
        return b.b.g.n.q.c.K().l();
    }

    public int g() {
        return b.b.g.n.q.c.K().m();
    }

    public int h() {
        return b.b.g.n.q.c.K().n();
    }

    public int i() {
        int iO = b.b.g.n.q.c.K().o();
        int i = this.m;
        if (i == 1) {
            if (iO == 80 || iO == 85 || iO == 90 || iO == 95 || iO == 100) {
                return (iO - 80) / 5;
            }
            if (iO == 0) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            return 4;
        }
        if (i != 2) {
            Log.i(this.f1215a, "[充电限值]----[getChargeStopCapacityStateNotLi]:" + iO);
            return 4;
        }
        if (iO == 60 || iO == 70 || iO == 80 || iO == 90 || iO == 100) {
            return (iO - 60) / 10;
        }
        if (iO == 0) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return 4;
    }

    public int j() {
        return b.b.g.n.q.c.K().p();
    }

    public int l() {
        return this.f1216b ? m.p0().u() : m.p0().e0();
    }

    public int m() {
        return b.b.g.n.q.c.K().v();
    }

    public int n() {
        return j.a().b();
    }

    public int[] o() {
        return this.m == 2 ? new int[]{60, 70, 80, 90, 100} : new int[]{80, 85, 90, 95, 100};
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetCommonMsg(b.b.g.m.a aVar) {
        if (getV() != null && aVar.b() == 1 && aVar.a() == 2 && getV().r0()) {
            getV().onResume();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        int i = 4;
        int i2 = 5;
        switch (aVar.h()) {
            case 140000:
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = j() == 1 ? 1 : 2;
                }
                getV().i0(iC);
                break;
            case 140002:
                int iC2 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iH = h();
                    if (iH == 5 || iH == 4 || iH == 3 || iH == 2 || iH == 1) {
                        i2 = iH;
                    }
                } else {
                    i2 = iC2;
                }
                getV().g0(i2, true);
                break;
            case 140003:
                if (this.f1216b) {
                    int iC3 = aVar.c();
                    if (b.b.g.r.e.e(aVar)) {
                        int iL = l();
                        if (iL == 1 || iL == 2 || iL == 3) {
                            i = iL;
                        }
                    } else {
                        i = iC3;
                    }
                    getV().j0(i, true, true);
                }
                break;
            case 140009:
                getV().k0(aVar.c());
                break;
            case 140013:
                if (!this.f1216b) {
                    int iC4 = aVar.c();
                    if (b.b.g.r.e.e(aVar)) {
                        int iL2 = l();
                        if (iL2 == 1 || iL2 == 2 || iL2 == 3) {
                            i = iL2;
                        }
                    } else {
                        i = iC4;
                    }
                    getV().j0(i, true, true);
                }
                break;
            case 140016:
                getV().e0(aVar.c());
                break;
            case 140017:
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iG = g();
                    if (iG == 1 || iG == 2 || iG == 0 || iG == 3) {
                        i = iG;
                    }
                } else {
                    i = iC5;
                }
                getV().f0(i, true);
                break;
            case 140019:
                int iC6 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iD = d();
                    if (iD >= 1 && iD <= 5) {
                        i2 = iD;
                    }
                } else {
                    i2 = iC6;
                }
                getV().d0(i2, true);
                break;
            case 140020:
                int iC7 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int i3 = i();
                    if (i3 != Integer.MAX_VALUE) {
                        if (i3 >= 0 && i3 <= 4) {
                            i = i3;
                        }
                        getV().h0(i, true);
                    } else {
                        getV().h0(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, true);
                    }
                } else if (iC7 != 0) {
                    int i4 = this.m;
                    int i5 = -1;
                    if (i4 == 1) {
                        if (iC7 == 80 || iC7 == 85 || iC7 == 90 || iC7 == 95 || iC7 == 100) {
                            i5 = (iC7 - 80) / 5;
                        }
                    } else if (i4 == 2 && (iC7 == 60 || iC7 == 70 || iC7 == 80 || iC7 == 90 || iC7 == 100)) {
                        i5 = (iC7 - 60) / 10;
                    }
                    Log.i(this.f1215a, "[充电限值]----[ON_CHARGE_STOP_CAPACITY_STATE_NOTLI]:" + i5);
                    getV().h0(i5, true);
                } else {
                    getV().h0(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, true);
                }
                break;
            case 150020:
                int iC8 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC8 = q() != 0 ? 1 : 0;
                }
                getV().m0(iC8);
                break;
            case 280000:
                getV().l0(aVar.c());
                break;
        }
    }

    public int q() {
        return e.j().w();
    }

    public void r() {
        boolean zG = b.b.g.n.q.b.c().g();
        this.f1216b = zG;
        if (zG) {
            this.f1218d = b.b.g.n.q.b.c().e(53) == 1;
        } else {
            this.f1218d = u.I().l0() == 1;
        }
        this.f1219e = u.I().j() == 2;
        this.f1220f = u.I().L() == 2;
        if (this.f1216b) {
            this.g = b.b.g.n.q.b.c().e(53) == 1;
        } else if (this.f1217c) {
            this.g = (b.b.g.n.q.b.c().e(53) == 1) && u.I().m() == 0;
        } else {
            this.g = false;
        }
        this.h = u.I().W() == 0;
        this.i = u.I().l() == 0;
        this.j = u.I().S() == 1;
        this.k = u.I().w() == 1;
        this.l = u.I().d0() == 2;
        this.m = u.I().k();
        if (getV() != null) {
            getV().q0();
        }
    }

    public boolean s() {
        return this.l;
    }

    public boolean t() {
        return this.g;
    }

    public boolean u() {
        return this.f1216b && this.f1219e && !x();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }

    public boolean v() {
        return this.f1216b;
    }

    public boolean w() {
        return !this.f1216b;
    }

    public boolean x() {
        int i;
        Log.i(this.f1215a, "[充电限值]----[supportChargeLimitNotLi]:" + this.m + "----[isEV]:" + this.f1216b);
        return this.f1216b && ((i = this.m) == 1 || i == 2);
    }

    public boolean y() {
        return this.f1216b && this.i && (b.b.g.n.q.b.c().e(151) == 1);
    }

    public boolean z() {
        return this.f1216b && this.h;
    }
}
