package n0;

/* JADX INFO: renamed from: n0.M, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1863M implements a1.b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f19382A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f19383C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public a1.b f19384D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public a1.k f19385E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public AbstractC1858H f19386F;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f19387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f19388n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f19389p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f19390q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f19391r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f19392s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f19393t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f19394u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f19395v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f19396w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f19397x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f19398y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC1866P f19399z;

    public final void a(float f10) {
        if (this.o == f10) {
            return;
        }
        this.l |= 4;
        this.o = f10;
    }

    public final void b(long j3) {
        if (C1891t.c(this.f19392s, j3)) {
            return;
        }
        this.l |= 64;
        this.f19392s = j3;
    }

    public final void c(boolean z6) {
        if (this.f19382A != z6) {
            this.l |= 16384;
            this.f19382A = z6;
        }
    }

    public final void d(float f10) {
        if (this.f19394u == f10) {
            return;
        }
        this.l |= 256;
        this.f19394u = f10;
    }

    public final void f(float f10) {
        if (this.f19395v == f10) {
            return;
        }
        this.l |= 512;
        this.f19395v = f10;
    }

    public final void g(float f10) {
        if (this.f19396w == f10) {
            return;
        }
        this.l |= 1024;
        this.f19396w = f10;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.f19384D.getDensity();
    }

    public final void i(float f10) {
        if (this.f19387m == f10) {
            return;
        }
        this.l |= 1;
        this.f19387m = f10;
    }

    public final void j(float f10) {
        if (this.f19388n == f10) {
            return;
        }
        this.l |= 2;
        this.f19388n = f10;
    }

    public final void k(float f10) {
        if (this.f19391r == f10) {
            return;
        }
        this.l |= 32;
        this.f19391r = f10;
    }

    public final void l(InterfaceC1866P interfaceC1866P) {
        if (A7.m.a(this.f19399z, interfaceC1866P)) {
            return;
        }
        this.l |= 8192;
        this.f19399z = interfaceC1866P;
    }

    public final void n(long j3) {
        if (C1891t.c(this.f19393t, j3)) {
            return;
        }
        this.l |= 128;
        this.f19393t = j3;
    }

    public final void o(long j3) {
        if (C1870U.a(this.f19398y, j3)) {
            return;
        }
        this.l |= 4096;
        this.f19398y = j3;
    }

    @Override // a1.b
    public final float p() {
        return this.f19384D.p();
    }

    public final void r(float f10) {
        if (this.f19389p == f10) {
            return;
        }
        this.l |= 8;
        this.f19389p = f10;
    }

    public final void u(float f10) {
        if (this.f19390q == f10) {
            return;
        }
        this.l |= 16;
        this.f19390q = f10;
    }
}
