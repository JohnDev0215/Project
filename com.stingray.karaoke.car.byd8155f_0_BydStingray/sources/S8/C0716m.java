package S8;

/* JADX INFO: renamed from: S8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0716m extends Z implements InterfaceC0715l {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final d0 f8121p;

    public C0716m(d0 d0Var) {
        this.f8121p = d0Var;
    }

    @Override // S8.InterfaceC0715l
    public final boolean c(Throwable th) {
        return j().D(th);
    }

    @Override // S8.Z
    public final boolean k() {
        return true;
    }

    @Override // S8.Z
    public final void l(Throwable th) {
        this.f8121p.w(j());
    }
}
