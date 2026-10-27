package b.b.g.n.n;

import b.b.g.n.q.e;
import b.b.g.n.q.u;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.newenergy.ITACManageFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ITACManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends g<ITACManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1227a = d.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1228b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1229c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1230d = true;

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(ITACManageFragment iTACManageFragment) {
        super.bindV(iTACManageFragment);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return e.j().A();
    }

    public int e() {
        return e.j().z();
    }

    public int f() {
        return e.j().B();
    }

    public void g() {
        this.f1230d = b.b.g.n.q.b.c().g();
        this.f1228b = u.I().m0() == 0;
        this.f1229c = u.I().n0();
        if (getV() != null) {
            getV().T();
        }
    }

    public boolean h() {
        boolean z = b.b.g.n.q.b.c().e(124) == 1;
        b.b.m.c.a.d(this.f1227a, "isSuperiTACShow", "---supportiTACConfig---isOnline = " + z + ", supportiTACConfig = " + this.f1229c);
        if (!z) {
            return false;
        }
        int i = this.f1229c;
        return i == 0 || i == 2;
    }

    public boolean i() {
        b.b.m.c.a.d(this.f1227a, "getSupportiTAC", "---isEV---" + this.f1230d);
        if (this.f1230d) {
            return l() || h();
        }
        return false;
    }

    public boolean j() {
        return this.f1229c == 2;
    }

    public boolean l() {
        boolean z = b.b.g.n.q.b.c().e(124) == 1;
        b.b.m.c.a.d(this.f1227a, "isiTACShow", "---supportiTAC---isOnline = " + z + ", supportiTAC = " + this.f1228b);
        return z && this.f1228b;
    }

    public int m(int i) {
        return e.j().O(i);
    }

    public int n(int i) {
        return e.j().P(i);
    }

    public void o() {
        if (l()) {
            getV().R(f());
            getV().P(d());
        }
        if (h()) {
            getV().Q(e(), false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        switch (aVar.h()) {
            case 150024:
                getV().P(aVar.c());
                break;
            case 150025:
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = f() != 0 ? 1 : 0;
                }
                getV().R(iC);
                break;
            case 150028:
                int iC2 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iE = e();
                    iC2 = iE <= 4 ? iE : 0;
                }
                getV().Q(iC2, true);
                break;
        }
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }
}
