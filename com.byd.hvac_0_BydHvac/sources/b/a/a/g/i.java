package b.a.a.g;

import b.a.a.f.f1;
import b.a.a.f.g1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.a.a.b.c<b.a.a.d.f> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f1 f229b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g1 f230c;

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.f fVar) {
        super.d(fVar);
        EventBus.getDefault().register(this);
        this.f229b = f1.l0();
        this.f230c = g1.e();
    }

    public int g() {
        return this.f230c.c();
    }

    public int h() {
        return this.f229b.Q();
    }

    public int i() {
        return this.f229b.u0();
    }

    public int[] j() {
        return this.f229b.v0();
    }

    public int k() {
        return this.f229b.w0();
    }

    public int[] l() {
        return this.f229b.x0();
    }

    public int m() {
        return this.f229b.y0();
    }

    public int[] n() {
        return this.f229b.z0();
    }

    public int o() {
        return this.f229b.K0();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int iC = aVar.c();
        if (iC == 1308) {
            int iA = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iO = o();
                iA = iO != 0 ? iO : 2;
            }
            e().Q(iA);
        }
        if (iC == 1315) {
            int iA2 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int i = i();
                iA2 = i != 0 ? i : 2;
            }
            e().O(iA2);
            return;
        }
        if (iC == 1801) {
            e().f(166, aVar.a());
            return;
        }
        if (iC == 1312) {
            int iA3 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA3 = j()[0];
            }
            e().t(1, iA3);
            return;
        }
        if (iC == 1313) {
            int iA4 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA4 = j()[1];
            }
            e().t(2, iA4);
            return;
        }
        switch (iC) {
            case 1301:
                e().j0(aVar.a(), aVar.b());
                break;
            case 1302:
                e().k0(aVar.a(), aVar.b());
                break;
            case 1303:
                e().R(aVar.a());
                break;
            case 1304:
                int iA5 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iK = k();
                    iA5 = iK != 0 ? iK : 2;
                }
                e().C(iA5);
                break;
        }
    }

    public int p() {
        return this.f229b.b1();
    }

    public int q() {
        return this.f229b.m1();
    }

    public int r() {
        return this.f230c.k();
    }

    public int s() {
        return this.f230c.l();
    }

    public int t(int i) {
        return this.f229b.q1(i);
    }

    public int u(int i) {
        e().O(i);
        b.a.a.h.a.g(1315);
        return this.f229b.j4(i);
    }

    public int v(int i, int i2) {
        if (i2 == 3) {
            e().t(1, 2);
            e().t(2, 2);
        }
        if (i == 1) {
            e().t(1, i2 == 1 ? 1 : 2);
        } else if (i == 2) {
            e().t(2, i2 != 2 ? 2 : 1);
        }
        return this.f229b.k4(i2);
    }

    public int w(int i) {
        e().C(i);
        return this.f229b.l4(i);
    }

    public int x(int i) {
        e().Q(i);
        return this.f229b.n4(i);
    }
}
