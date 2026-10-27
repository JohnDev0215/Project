package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class L0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q0 f17825m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u1 f17826n;

    public /* synthetic */ L0(Q0 q02, u1 u1Var, int i7) {
        this.l = i7;
        this.f17825m = q02;
        this.f17826n = u1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                Q0 q02 = this.f17825m;
                q02.getClass();
                u1 u1Var = this.f17826n;
                q02.f17847j.E(u1Var.Z0());
                q02.f17845h.w(u1Var.A().a(17) ? u1Var.t0() : e2.f0.f14097a);
                break;
            default:
                Q0 q03 = this.f17825m;
                q03.getClass();
                q03.f17847j.E(this.f17826n.Z0());
                break;
        }
    }
}
