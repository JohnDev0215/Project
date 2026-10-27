package b.b.g.n.g;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import b.b.g.n.q.h;
import b.b.g.n.q.m;
import b.b.g.n.q.r;
import b.b.g.n.q.s;
import b.b.g.n.q.u;
import b.b.g.r.e;
import b.b.m.e.g;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.ui.fragment.carhealthy.OverhaulFragment;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: OverhaulPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends g<OverhaulFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1002a = c.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1003b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1004c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1005d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1006e = false;

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(OverhaulFragment overhaulFragment) {
        super.bindV(overhaulFragment);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return s.g().d();
    }

    public int e() {
        return s.g().f();
    }

    public int f() {
        return m.p0().x0();
    }

    public int g() {
        return s.g().m();
    }

    public void h() {
        boolean z = false;
        this.f1003b = b.b.g.n.q.b.c().a() == 42;
        this.f1004c = h.m().p() == 2;
        if (s.g().b() == 1 && s.g().e() == 0) {
            z = true;
        }
        this.f1005d = z;
        if (getV() != null) {
            getV().V();
        }
    }

    public boolean i() {
        return this.f1005d && (b.b.g.n.q.b.c().e(43) == 1);
    }

    public boolean j() {
        return e() != 0;
    }

    public boolean l() {
        boolean z = true;
        boolean z2 = b.b.g.n.q.b.c().e(47) == 1;
        int iP = s.g().p();
        if (iP != 2 && iP != 1) {
            z = false;
        }
        if (!this.f1003b || !this.f1004c) {
            if (z2) {
                return false;
            }
            return z;
        }
        if (u.I().f0() != 0 || z2) {
            return false;
        }
        return z;
    }

    public boolean m() {
        int iF = f();
        return iF == 1 || iF == 2;
    }

    public boolean n() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.auto_emanual", "com.byd.auto_emanual.MainActivity"));
        intent.setFlags(337641472);
        List<ResolveInfo> listQueryIntentActivities = BydApp.getContext().getPackageManager().queryIntentActivities(intent, 65536);
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }

    public boolean o() {
        return g() != 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        int i = 2;
        switch (aVar.h()) {
            case 190001:
                getV().R(aVar.c());
                break;
            case 200000:
                int iC = aVar.c();
                if (e.e(aVar)) {
                    int iE = e();
                    if (iE == 1 || iE == 2 || iE == 3) {
                        i = iE;
                    }
                } else {
                    i = iC;
                }
                getV().Q(i, false);
                break;
            case 200001:
                int iC2 = aVar.c();
                if (e.e(aVar)) {
                    int iG = g();
                    if (iG == 1 || iG == 2 || iG == 3) {
                        i = iG;
                    }
                } else {
                    i = iC2;
                }
                getV().T(i, false);
                break;
            case 200006:
                int iC3 = aVar.c();
                if (e.e(aVar)) {
                    iC3 = d() == 1 ? 1 : 0;
                }
                getV().P(iC3, e.e(aVar));
                break;
            case 200008:
                int iC4 = aVar.c();
                if (e.e(aVar)) {
                    iC4 = f();
                }
                getV().S(iC4);
                break;
            case 200010:
                int iC5 = aVar.c();
                if (getV().W()) {
                    getV().O(iC5);
                }
                break;
        }
    }

    public int q(int i, int i2) {
        return s.g().t(i, i2);
    }

    public int r(int i) {
        return s.g().u(i);
    }

    public int s(int i) {
        return r.b().r(i);
    }

    public int t(int i) {
        return m.p0().M2(i);
    }

    public int u(int i) {
        return s.g().x(i);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }

    public void v() {
        getV().Q(e(), true);
        getV().T(g(), true);
        getV().S(f());
        if (i()) {
            getV().P(d(), false);
        }
    }
}
