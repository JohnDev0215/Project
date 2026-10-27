package b.b.g.n.n;

import android.content.Context;
import b.b.g.n.q.e;
import b.b.g.n.q.h;
import b.b.g.n.q.o;
import b.b.g.r.j;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.newenergy.ConsumptionManageFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ConsumptionManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends g<ConsumptionManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1209a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1210b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1211c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1212d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1213e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1214f = 0;

    public boolean A() {
        return !this.f1210b && this.f1213e;
    }

    public boolean B() {
        boolean z = this.f1210b;
        return (z && this.f1212d && !this.f1213e) || (!z && this.f1212d && this.f1213e);
    }

    public int C(int i) {
        return e.j().E(i);
    }

    public int D(int i, int i2, int i3, int i4) {
        return o.m().x(i, i2, i3, i4);
    }

    public void E() {
        getV().i0();
        getV().j0();
        if (!this.f1210b) {
            getV().g0(l());
        }
        getV().f0(j());
    }

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(ConsumptionManageFragment consumptionManageFragment) {
        super.bindV(consumptionManageFragment);
        EventBus.getDefault().register(this);
    }

    public boolean d() {
        return this.f1211c;
    }

    public String e() {
        double dB = o.m().b();
        return (dB <= -0.1d || dB >= 240.1d) ? "--" : String.format("%.0f", Double.valueOf(dB));
    }

    public String f(Context context) {
        return j.a(context, this.f1214f, this.f1210b);
    }

    public String g() {
        return j.b(this.f1214f, this.f1210b);
    }

    public String h() {
        double d2 = o.m().d();
        return (d2 <= -0.1d || d2 >= 100.0d) ? "--" : String.format("%.1f", Double.valueOf(d2));
    }

    public int i() {
        return h.m().C(2);
    }

    public float j() {
        float fM = e.j().m();
        double d2 = fM;
        if (d2 <= -100.0d || d2 >= 100.0d) {
            return -1.0f;
        }
        return fM;
    }

    public float l() {
        float fN = e.j().n();
        double d2 = fN;
        if (d2 <= -0.1d || d2 >= 30.1d) {
            return -1.0f;
        }
        return fN;
    }

    public int m() {
        return h.m().v();
    }

    public int n() {
        return h.m().C(3);
    }

    public int o() {
        return h.m().x();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        switch (aVar.h()) {
            case 70008:
            case 180006:
            case 180007:
            case 180008:
                getV().i0();
                break;
            case 70012:
                getV().i0();
                break;
            case 70013:
                getV().j0();
                getV().g0(l());
                getV().f0(j());
                break;
            case 70016:
                getV().j0();
                getV().i0();
                break;
            case 70017:
                getV().j0();
                getV().i0();
                getV().g0(l());
                getV().f0(j());
                break;
            case 150021:
                getV().h0(aVar.c(), aVar.d());
                break;
            case 150022:
                getV().f0(j());
                break;
            case 150023:
                getV().g0(l());
                break;
            case 180000:
            case 180001:
            case 180002:
            case 180003:
            case 180004:
                getV().j0();
                break;
        }
    }

    public String q() {
        double dQ = o.m().q();
        return (dQ <= -100.0d || dQ >= 100.0d) ? "--" : String.format("%.1f", Double.valueOf(dQ));
    }

    public String r() {
        double dH = h.m().h();
        return (dH <= -0.1d || dH >= 100000.0d) ? "--" : String.format("%.1f", Double.valueOf(dH));
    }

    public String s() {
        double dS = o.m().s();
        return (dS <= -0.1d || dS >= 100.0d) ? "--" : String.format("%.1f", Double.valueOf(dS));
    }

    public String t() {
        double dT = o.m().t();
        return (dT <= -0.1d || dT >= 10000.0d) ? "--" : String.format("%.1f", Double.valueOf(dT));
    }

    public String u() {
        double dU = o.m().u();
        return (dU <= -0.1d || dU >= 1.0E7d) ? "--" : String.format("%.0f", Double.valueOf(dU));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }

    public void v() {
        this.f1214f = b.b.g.n.q.b.c().a();
        this.f1210b = b.b.g.n.q.b.c().g();
        this.f1212d = b.b.g.n.q.b.c().e(52) == 1;
        this.f1213e = b.b.g.n.q.b.c().e(49) == 1;
        getV().m0();
    }

    public boolean w() {
        return this.f1210b;
    }

    public boolean x() {
        return !this.f1210b && this.f1212d && this.f1213e;
    }

    public boolean y() {
        return !this.f1210b;
    }

    public boolean z() {
        return !this.f1210b && this.f1213e;
    }
}
