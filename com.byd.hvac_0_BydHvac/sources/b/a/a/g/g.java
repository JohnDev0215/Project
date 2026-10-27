package b.a.a.g;

import android.os.Message;
import b.a.a.f.f1;
import b.a.a.f.g1;
import b.a.a.h.d;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g extends b.a.a.b.c<b.a.a.d.d> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.a.a.h.d f225b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f1 f226c;
    public g1 d;
    public d.a e = new d.a() { // from class: b.a.a.g.a
        @Override // b.a.a.h.d.a
        public final boolean a(Message message) {
            return g.u(message);
        }
    };

    public static /* synthetic */ boolean u(Message message) {
        int i = message.what;
        return false;
    }

    public int A(int i) {
        e().n(1, i);
        return this.f226c.U3(i);
    }

    public int B(int i) {
        e().c(i, true);
        return this.f226c.V3(i);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
        this.f225b.c(this.e);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.d dVar) {
        super.d(dVar);
        EventBus.getDefault().register(this);
        this.f226c = f1.l0();
        this.d = g1.e();
        b.a.a.h.d dVarB = b.a.a.h.d.b();
        this.f225b = dVarB;
        dVarB.a(this.e);
    }

    public int g() {
        return this.f226c.n();
    }

    public int h() {
        return this.f226c.p();
    }

    public int i(int i) {
        return this.f226c.q(i);
    }

    public int j() {
        return this.f226c.v();
    }

    public int k() {
        return this.f226c.A();
    }

    public int l(int i) {
        return this.f226c.B(i);
    }

    public int m() {
        return this.f226c.H();
    }

    public int n() {
        return this.f226c.J();
    }

    public int o() {
        return this.d.b();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int iC = aVar.c();
        if (iC == 1004) {
            int iA = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA = g() != 1 ? 0 : 1;
            }
            e().g(iA, false);
            return;
        }
        if (iC == 1009) {
            int iA2 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA2 = n();
            }
            e().c(iA2, false);
            return;
        }
        if (iC == 1020) {
            e().h(aVar.a());
            return;
        }
        if (iC == 1022) {
            int iA3 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA3 = k() != 1 ? 0 : 1;
            }
            e().o(iA3, false);
            return;
        }
        if (iC == 1038) {
            int iA4 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA4 = i(2) != 1 ? 0 : 1;
            }
            e().e(2, iA4, false);
            return;
        }
        if (iC == 1006) {
            int iA5 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA5 = i(1) == 1 ? 1 : 0;
            }
            e().e(1, iA5, false);
            return;
        }
        if (iC == 1007) {
            int iA6 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA6 = h() == 0 ? 0 : 1;
            }
            e().k(iA6, false);
            return;
        }
        if (iC == 1012) {
            int iA7 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA7 = m();
            }
            e().n(1, iA7);
            return;
        }
        if (iC == 1013) {
            e().l(aVar.a());
            return;
        }
        if (iC == 1034) {
            int iA8 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA8 = l(1);
            }
            e().d(1, iA8);
            return;
        }
        if (iC != 1035) {
            return;
        }
        int iA9 = aVar.a();
        if (b.a.a.h.a.c(aVar)) {
            iA9 = l(2);
        }
        e().d(2, iA9);
    }

    public int p() {
        return this.d.d();
    }

    public int q() {
        return this.f226c.i0();
    }

    public int r() {
        return this.f226c.H0();
    }

    public int s() {
        return this.f226c.h1();
    }

    public int t() {
        return this.f226c.m1();
    }

    public int v(int i) {
        e().g(i, true);
        return this.f226c.J3(i);
    }

    public int w(int i) {
        e().k(i, true);
        return this.f226c.L3(i);
    }

    public int x(int i, int i2) {
        e().e(i, i2, true);
        if (i == 2) {
            b.a.a.h.a.h(1038, i2);
        }
        return this.f226c.M3(i, i2);
    }

    public int y(int i) {
        e().o(i, true);
        if (i == 0) {
            return this.f226c.E4();
        }
        if (i == 1) {
            return this.f226c.C4();
        }
        return -1;
    }

    public int z(int i, int i2) {
        e().d(i, i2);
        return this.f226c.Q3(i, i2);
    }
}
