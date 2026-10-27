package b.b.g.n.i;

import b.b.g.n.q.m;
import b.b.g.q.f.a.i;

/* JADX INFO: compiled from: Switch2Presenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.m.e.g<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.n.q.i f1043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f1044b;

    public int c() {
        return e(this.f1043a.c(this.f1044b.m0()));
    }

    public int d() {
        return g(this.f1043a.e(this.f1044b.m0()));
    }

    public int e(int i) {
        if (i != 1) {
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
        }
        return 0;
    }

    public boolean f() {
        return b.b.g.n.q.i.k().b() == 1;
    }

    public int g(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            if (i == 3) {
                return 2;
            }
            if (i == 4) {
                return 3;
            }
        }
        return 0;
    }

    public boolean h() {
        int iH = m.p0().h(0);
        return iH == 1 || iH == 2;
    }

    public void i() {
        this.f1043a = b.b.g.n.q.i.k();
        this.f1044b = m.p0();
    }

    public boolean j() {
        return this.f1043a.d(this.f1044b.m0()) == 1;
    }

    public boolean l() {
        return this.f1044b.h(this.f1044b.m0()) == 1;
    }

    public void m(boolean z) {
        int iM0 = this.f1044b.m0();
        int i = z ? 1 : 2;
        b.b.g.r.e.i(20003);
        this.f1043a.q(iM0, i);
    }

    public void n(int i) {
        int i2 = 2;
        if (i == 0) {
            i2 = 1;
        } else if (i != 1) {
            i2 = i != 2 ? 0 : 3;
        }
        int iM0 = this.f1044b.m0();
        b.b.g.r.e.i(20007);
        this.f1043a.p(iM0, i2);
    }

    public void o(boolean z) {
        int iM0 = this.f1044b.m0();
        int i = z ? 1 : 2;
        b.b.g.r.e.i(20002);
        this.f1044b.S1(iM0, i);
    }

    public void q(int i) {
        int i2 = 3;
        if (i == 0) {
            i2 = 2;
        } else if (i == 1) {
            i2 = 1;
        } else if (i != 2) {
            i2 = i != 3 ? 0 : 4;
        }
        int iM0 = this.f1044b.m0();
        b.b.g.r.e.i(20006);
        this.f1043a.r(iM0, i2);
    }
}
