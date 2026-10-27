package b.b.g.n.g;

import b.b.g.n.q.h;
import b.b.g.n.q.o;
import b.b.g.n.q.s;
import b.b.g.r.j;
import b.b.g.r.v;
import b.b.m.e.g;
import com.byd.carsettings.db.model.MaintainMileBean;
import com.byd.carsettings.db.model.MaintainTimeBean;
import com.byd.carsettings.ui.fragment.carhealthy.MaintainServiceFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MaintainServicePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends g<MaintainServiceFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f998a = b.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f999b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1000c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1001d = false;

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(MaintainServiceFragment maintainServiceFragment) {
        super.bindV(maintainServiceFragment);
        EventBus.getDefault().register(this);
    }

    public int d(int i) {
        if (i != 2) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jF = v.d().f("KEY_MAINTAIN_TIME_TARGET", jCurrentTimeMillis);
            int iE = v.d().e("KEY_MAINTAIN_TIME", 90);
            if (iE == 90) {
                v.d().i("KEY_MAINTAIN_TIME", 90);
            }
            if (jCurrentTimeMillis > jF) {
                iE -= Long.valueOf((jCurrentTimeMillis - jF) / 86400000).intValue();
            }
            if (iE < 0) {
                return 0;
            }
            return iE;
        }
        double dI = i();
        double dC = v.d().c("KEY_MAINTAIN_MILE_TARGET", dI);
        double dC2 = v.d().c("KEY_MAINTAIN_MILE_1", j.c());
        boolean z = e() == 2;
        if (z) {
            dI *= 1.6093000173568726d;
        }
        if (!v.d().a("KEY_MAINTAIN_MILE_1")) {
            v.d().h("KEY_MAINTAIN_MILE_1", j.c());
        }
        if (dI > dC) {
            dC2 -= (double) ((int) Math.round((dI - dC) - 0.5d));
        }
        if (dC2 < 0.0d) {
            dC2 = 0.0d;
        }
        if (z) {
            dC2 = Math.round((dC2 * 0.621399998664856d) - 0.5d);
        }
        return (int) dC2;
    }

    public int e() {
        return h.m().v();
    }

    public int f() {
        return s.g().j();
    }

    public int g(int i) {
        int iK = s.g().k(i);
        if (iK < -100 || iK > 100) {
            return -999;
        }
        return iK;
    }

    public int h() {
        return s.g().l();
    }

    public double i() {
        return o.m().u();
    }

    public void j() {
        this.f1000c = h.m().p() == 2;
        this.f1001d = b.b.g.n.q.b.c().g();
        if (getV() != null) {
            getV().n0();
        }
    }

    public boolean l() {
        return this.f1001d;
    }

    public boolean m() {
        return (b.b.g.n.q.b.c().e(46) == 1) && f() != 255;
    }

    public boolean n() {
        if (!(b.b.g.n.q.b.c().e(193) == 1)) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < 5; i++) {
            z = z || g(i) != -999;
            if (z) {
                break;
            }
        }
        return z;
    }

    public int o(int i) {
        return s.g().s(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        switch (aVar.h()) {
            case 70013:
                getV().f0(e());
                break;
            case 180000:
                getV().k0(aVar.c());
                break;
            case 200003:
                getV().j0(aVar.c(), aVar.d());
                break;
            case 200004:
                int iD = aVar.d();
                if (iD < -100 || iD > 100) {
                    iD = -999;
                }
                getV().h0(aVar.c(), iD);
                break;
            case 200005:
                getV().g0(aVar.c());
                break;
            case 200007:
                getV().i0(aVar.c());
                break;
            case 200009:
                getV().f0(e());
                getV().e0(1, d(1), v.d().b("KEY_MAINTAIN_TIME_STATE", false));
                break;
        }
    }

    public int q(int i, int i2, b.b.g.h.a aVar) {
        boolean z = true;
        try {
            if (i == 2) {
                v.d().g("KEY_MAINTAIN_MILE_STATE", i2 != 34001);
                if (i2 != 34001) {
                    boolean z2 = e() == 2;
                    double dI = i();
                    double d2 = i2;
                    if (z2) {
                        dI *= 1.6093000173568726d;
                        d2 *= 1.6093000173568726d;
                    }
                    v.d().h("KEY_MAINTAIN_MILE_1", d2);
                    v.d().h("KEY_MAINTAIN_MILE_TARGET", dI);
                    if (aVar != null) {
                        MaintainMileBean maintainMileBean = new MaintainMileBean();
                        maintainMileBean.e(d2);
                        maintainMileBean.f(true);
                        maintainMileBean.g(dI);
                        aVar.E(maintainMileBean);
                    }
                } else if (aVar != null) {
                    MaintainMileBean maintainMileBean2 = new MaintainMileBean();
                    maintainMileBean2.e(v.d().c("KEY_MAINTAIN_MILE_1", j.c()));
                    maintainMileBean2.f(false);
                    maintainMileBean2.g(v.d().c("KEY_MAINTAIN_MILE_TARGET", 0.0d));
                    aVar.E(maintainMileBean2);
                }
                MaintainServiceFragment v = getV();
                if (i2 == 34001) {
                    z = false;
                }
                v.e0(i, i2, z);
            } else {
                v.d().g("KEY_MAINTAIN_TIME_STATE", i2 != 991);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (i2 != 991) {
                    v.d().i("KEY_MAINTAIN_TIME", i2);
                    v.d().j("KEY_MAINTAIN_TIME_TARGET", jCurrentTimeMillis);
                    if (aVar != null) {
                        MaintainTimeBean maintainTimeBean = new MaintainTimeBean();
                        maintainTimeBean.e(i2);
                        maintainTimeBean.f(true);
                        maintainTimeBean.g(jCurrentTimeMillis);
                        aVar.A(maintainTimeBean);
                    }
                } else if (aVar != null) {
                    MaintainTimeBean maintainTimeBean2 = new MaintainTimeBean();
                    maintainTimeBean2.e(v.d().e("KEY_MAINTAIN_TIME", 90));
                    maintainTimeBean2.f(false);
                    maintainTimeBean2.g(v.d().f("KEY_MAINTAIN_TIME_TARGET", jCurrentTimeMillis));
                    aVar.A(maintainTimeBean2);
                }
                MaintainServiceFragment v2 = getV();
                if (i2 == 991) {
                    z = false;
                }
                v2.e0(i, i2, z);
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
        return 0;
    }

    public int r(int i) {
        return s.g().v(i);
    }

    public void s() {
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }
}
