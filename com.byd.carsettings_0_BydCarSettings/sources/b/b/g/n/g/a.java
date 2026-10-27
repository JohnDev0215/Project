package b.b.g.n.g;

import b.b.g.n.q.h;
import b.b.g.n.q.s;
import b.b.g.n.q.u;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.carhealthy.CarMessageFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: CarMessagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends g<CarMessageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f996a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f997b = false;

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(CarMessageFragment carMessageFragment) {
        super.bindV(carMessageFragment);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return s.g().a();
    }

    public int[] e() {
        return h.m().u();
    }

    public void f() {
        this.f997b = u.I().e() == 2;
        if (getV() != null) {
            getV().N();
        }
    }

    public boolean g() {
        return (b.b.g.n.q.b.c().e(223) == 1) && this.f997b;
    }

    public void h() {
        getV().M(e());
        if (g()) {
            getV().K(d());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
            return;
        }
        int iH = aVar.h();
        if (iH == 70004) {
            getV().L(aVar.c(), aVar.d());
        } else {
            if (iH != 200011) {
                return;
            }
            getV().K(aVar.c());
        }
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }
}
