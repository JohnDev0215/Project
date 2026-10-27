package k3;

import e2.C1194b;
import e2.C1217z;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class t1 extends e2.f0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f18118j = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e2.F f18119e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f18120f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f18121g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1217z f18122h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f18123i;

    public t1(u1 u1Var) {
        this.f18119e = u1Var.Q0();
        this.f18120f = u1Var.P0();
        this.f18121g = u1Var.S0();
        this.f18122h = u1Var.V0() ? C1217z.f14370f : null;
        this.f18123i = AbstractC1343A.H(u1Var.P());
    }

    @Override // e2.f0
    public final int b(Object obj) {
        return f18118j.equals(obj) ? 0 : -1;
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        Object obj = f18118j;
        c0Var.getClass();
        c0Var.i(obj, obj, 0, this.f18123i, 0L, C1194b.f14037f, false);
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return 1;
    }

    @Override // e2.f0
    public final Object l(int i7) {
        return f18118j;
    }

    @Override // e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        e0Var.b(f18118j, this.f18119e, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.f18120f, this.f18121g, this.f18122h, 0L, this.f18123i, 0, 0, 0L);
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return 1;
    }
}
