package E0;

import A.I0;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1556b;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1557e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1558f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1559g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1560h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1561i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1562j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1563k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1564m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1565n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1566p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1567q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public K f1569s;
    public int c = 5;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final L f1568r = new L(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f1570t = io.sentry.config.a.f(0, 0, 15);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final I0 f1571u = new I0(this, 10);

    public N(F f10) {
        this.f1555a = f10;
    }

    public final d0 a() {
        return (d0) this.f1555a.f1469H.d;
    }

    public final void b(int i7) {
        int i10 = this.f1565n;
        this.f1565n = i7;
        if ((i10 == 0) != (i7 == 0)) {
            F fG = this.f1555a.G();
            N nT = fG != null ? fG.t() : null;
            if (nT != null) {
                if (i7 == 0) {
                    nT.b(nT.f1565n - 1);
                } else {
                    nT.b(nT.f1565n + 1);
                }
            }
        }
    }

    public final void c(int i7) {
        int i10 = this.f1567q;
        this.f1567q = i7;
        if ((i10 == 0) != (i7 == 0)) {
            F fG = this.f1555a.G();
            N nT = fG != null ? fG.t() : null;
            if (nT != null) {
                if (i7 == 0) {
                    nT.c(nT.f1567q - 1);
                } else {
                    nT.c(nT.f1567q + 1);
                }
            }
        }
    }

    public final void d(boolean z6) {
        if (this.f1564m != z6) {
            this.f1564m = z6;
            if (z6 && !this.l) {
                b(this.f1565n + 1);
            } else {
                if (z6 || this.l) {
                    return;
                }
                b(this.f1565n - 1);
            }
        }
    }

    public final void e(boolean z6) {
        if (this.l != z6) {
            this.l = z6;
            if (z6 && !this.f1564m) {
                b(this.f1565n + 1);
            } else {
                if (z6 || this.f1564m) {
                    return;
                }
                b(this.f1565n - 1);
            }
        }
    }

    public final void f(boolean z6) {
        if (this.f1566p != z6) {
            this.f1566p = z6;
            if (z6 && !this.o) {
                c(this.f1567q + 1);
            } else {
                if (z6 || this.o) {
                    return;
                }
                c(this.f1567q - 1);
            }
        }
    }

    public final void g(boolean z6) {
        if (this.o != z6) {
            this.o = z6;
            if (z6 && !this.f1566p) {
                c(this.f1567q + 1);
            } else {
                if (z6 || this.f1566p) {
                    return;
                }
                c(this.f1567q - 1);
            }
        }
    }

    public final void h() {
        L l = this.f1568r;
        Object obj = l.f1526C;
        F f10 = this.f1555a;
        N n10 = l.f1542S;
        if ((obj != null || n10.a().i() != null) && l.B) {
            l.B = false;
            l.f1526C = n10.a().i();
            F fG = f10.G();
            if (fG != null) {
                F.s0(fG, false, 7);
            }
        }
        K k10 = this.f1569s;
        if (k10 != null) {
            Object obj2 = k10.f1512H;
            N n11 = k10.f1514J;
            if (obj2 == null) {
                S sO0 = n11.a().O0();
                A7.m.c(sO0);
                if (sO0.f1582w.i() == null) {
                    return;
                }
            }
            if (k10.f1511G) {
                k10.f1511G = false;
                S sO02 = n11.a().O0();
                A7.m.c(sO02);
                k10.f1512H = sO02.f1582w.i();
                if (AbstractC0156f.p(f10)) {
                    F fG2 = f10.G();
                    if (fG2 != null) {
                        F.s0(fG2, false, 7);
                        return;
                    }
                    return;
                }
                F fG3 = f10.G();
                if (fG3 != null) {
                    F.q0(fG3, false, 7);
                }
            }
        }
    }
}
