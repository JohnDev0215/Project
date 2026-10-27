package u2;

import e2.C1194b;

/* JADX INFO: renamed from: u2.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2519w extends e2.f0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e2.F f22909e;

    public C2519w(e2.F f10) {
        this.f22909e = f10;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        return obj == C2518v.f22906h ? 0 : -1;
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        c0Var.i(z6 ? 0 : null, z6 ? C2518v.f22906h : null, 0, -9223372036854775807L, 0L, C1194b.f14037f, true);
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return 1;
    }

    @Override // e2.f0
    public final Object l(int i7) {
        return C2518v.f22906h;
    }

    @Override // e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        e0Var.b(e2.e0.f14075q, this.f22909e, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, false, true, null, 0L, -9223372036854775807L, 0, 0, 0L);
        e0Var.f14093k = true;
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return 1;
    }
}
