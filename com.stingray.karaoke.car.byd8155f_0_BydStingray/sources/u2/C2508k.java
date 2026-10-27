package u2;

import java.util.List;

/* JADX INFO: renamed from: u2.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2508k implements b0 {
    public final b0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d5.I f22857m;

    public C2508k(b0 b0Var, List list) {
        this.l = b0Var;
        this.f22857m = d5.I.w(list);
    }

    public final d5.I c() {
        return this.f22857m;
    }

    @Override // u2.b0
    public final boolean d() {
        return this.l.d();
    }

    @Override // u2.b0
    public final long g() {
        return this.l.g();
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        return this.l.i(g7);
    }

    @Override // u2.b0
    public final long l() {
        return this.l.l();
    }

    @Override // u2.b0
    public final void q(long j3) {
        this.l.q(j3);
    }
}
