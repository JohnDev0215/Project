package S8;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends Z {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final d0 f8103p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final c0 f8104q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0716m f8105r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Object f8106s;

    public b0(d0 d0Var, c0 c0Var, C0716m c0716m, Object obj) {
        this.f8103p = d0Var;
        this.f8104q = c0Var;
        this.f8105r = c0716m;
        this.f8106s = obj;
    }

    @Override // S8.Z
    public final boolean k() {
        return false;
    }

    @Override // S8.Z
    public final void l(Throwable th) {
        C0716m c0716m = this.f8105r;
        d0 d0Var = this.f8103p;
        d0Var.getClass();
        C0716m c0716mW = d0.W(c0716m);
        c0 c0Var = this.f8104q;
        Object obj = this.f8106s;
        if (c0716mW == null || !d0Var.f0(c0Var, c0716mW, obj)) {
            c0Var.l.e(new X8.h(2), 2);
            C0716m c0716mW2 = d0.W(c0716m);
            if (c0716mW2 == null || !d0Var.f0(c0Var, c0716mW2, obj)) {
                d0Var.s(d0Var.I(c0Var, obj));
            }
        }
    }
}
