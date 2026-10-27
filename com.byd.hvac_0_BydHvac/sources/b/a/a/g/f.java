package b.a.a.g;

import b.a.a.f.e1;
import b.a.a.f.f1;
import b.a.a.f.g1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f extends b.a.a.b.c<b.a.a.d.c> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f1 f223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g1 f224c;
    public e1 d;

    public int A(int i) {
        e().o(i, true);
        if (i == 0) {
            return this.f223b.F4();
        }
        if (i == 1) {
            return this.f223b.D4();
        }
        return -1;
    }

    public int B(int i, int i2) {
        e().d(i, i2);
        return this.f223b.Q3(i, i2);
    }

    public int C(int i) {
        e().A(i);
        return this.f223b.q4(i);
    }

    public int D(int i) {
        e().c(i, true);
        return this.f223b.r4(i);
    }

    public int E(int i) {
        e().N(i);
        return this.f223b.o4(i);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.c cVar) {
        super.d(cVar);
        EventBus.getDefault().register(this);
        this.f223b = f1.l0();
        this.d = e1.a();
        this.f224c = g1.e();
    }

    public int g() {
        return this.f223b.M0();
    }

    public int h() {
        return this.f223b.O0();
    }

    public int i(int i) {
        return this.f223b.B(i);
    }

    public int j() {
        return this.f223b.P0();
    }

    public int k() {
        return this.f223b.Q0();
    }

    public int l() {
        return this.f223b.H0();
    }

    public int m() {
        return this.f223b.L0();
    }

    public int n() {
        return this.f224c.g();
    }

    public int o() {
        return this.f223b.h1();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int iC = aVar.c();
        if (iC == 1017) {
            e().m(aVar.a());
        }
        if (iC == 1032) {
            e().i(aVar.a(), aVar.b());
            return;
        }
        if (iC == 1036) {
            int iA = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA = i(3);
            }
            e().d(3, iA);
            return;
        }
        if (iC == 1073) {
            e().a(((Boolean) aVar.d()).booleanValue());
            return;
        }
        if (iC == 1800) {
            e().f(185, aVar.a());
            return;
        }
        if (iC == 1802) {
            e().f(207, aVar.a());
            return;
        }
        switch (iC) {
            case 1102:
                int iA2 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA2 = g() != 0 ? 1 : 0;
                }
                e().c0(iA2);
                break;
            case 1103:
                int iA3 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA3 = k();
                }
                e().c(iA3, false);
                break;
            case 1104:
                int iA4 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA4 = j();
                }
                e().A(iA4);
                break;
            case 1105:
                int iA5 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA5 = m() != 1 ? 2 : 1;
                }
                e().N(iA5);
                break;
            case 1106:
                int iA6 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA6 = h() != 1 ? 0 : 1;
                }
                e().o(iA6, false);
                break;
        }
    }

    public int p() {
        return this.f223b.m1();
    }

    public int q() {
        return this.f224c.h();
    }

    public int r() {
        return this.f224c.n();
    }

    public int s() {
        return this.f224c.o();
    }

    public int t() {
        return this.f224c.p();
    }

    public int u() {
        return this.f224c.q();
    }

    public int v() {
        return this.f224c.r();
    }

    public int w() {
        return this.f224c.s();
    }

    public int x() {
        return this.f223b.p1();
    }

    public int y(int i) {
        return this.f223b.q1(i);
    }

    public int z(int i) {
        e().c0(i);
        return this.f223b.p4(i);
    }
}
