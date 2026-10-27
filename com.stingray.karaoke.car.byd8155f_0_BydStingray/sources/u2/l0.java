package u2;

import l5.C1778f;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements a0 {
    public final a0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22866m;

    public l0(a0 a0Var, long j3) {
        this.l = a0Var;
        this.f22866m = j3;
    }

    @Override // u2.a0
    public final boolean k() {
        return this.l.k();
    }

    @Override // u2.a0
    public final int p(C1778f c1778f, m2.g gVar, int i7) {
        int iP = this.l.p(c1778f, gVar, i7);
        if (iP == -4) {
            gVar.f18957r += this.f22866m;
        }
        return iP;
    }

    @Override // u2.a0
    public final void r() {
        this.l.r();
    }

    @Override // u2.a0
    public final int s(long j3) {
        return this.l.s(j3 - this.f22866m);
    }
}
