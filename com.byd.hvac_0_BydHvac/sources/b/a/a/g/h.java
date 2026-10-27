package b.a.a.g;

import b.a.a.f.f1;
import b.a.a.f.g1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class h extends b.a.a.b.c<b.a.a.d.e> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f1 f227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g1 f228c;

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        EventBus.getDefault().unregister(this);
        super.a();
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.e eVar) {
        super.d(eVar);
        this.f227b = f1.l0();
        this.f228c = g1.e();
        EventBus.getDefault().register(this);
    }

    public int g(int i) {
        return this.f227b.a0(i);
    }

    public int h(int i) {
        return this.f227b.b0(i);
    }

    public int i(int i) {
        return this.f227b.d0(i);
    }

    public int j() {
        return this.f227b.e0();
    }

    public int k(int i) {
        return this.f227b.f0(i);
    }

    public int l() {
        return this.f227b.T();
    }

    public int m(int i) {
        e().X(i);
        return this.f227b.d4(i);
    }

    public int n(int i) {
        e().T(i);
        return this.f227b.e4(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        switch (aVar.c()) {
            case 1500:
                int iA = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA = l();
                }
                e().T(iA);
                break;
            case 1501:
                int iB = aVar.b();
                if (b.a.a.h.a.c(aVar)) {
                    int iG = g(1);
                    if (iG == 0) {
                        iG = 4;
                    }
                    iB = iG;
                }
                e().X(iB);
                break;
            case 1502:
                e().m0(aVar.a(), aVar.b());
                break;
            case 1504:
                e().L(aVar.a());
                break;
            case 1505:
                e().a0(aVar.a(), aVar.b());
                break;
            case 1506:
                e().Z(aVar.a(), aVar.b());
                break;
        }
    }
}
