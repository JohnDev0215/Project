package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class W implements h2.l, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17868m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17869n;

    public /* synthetic */ W(int i7, int i10, int i11) {
        this.l = i11;
        this.f17868m = i7;
        this.f17869n = i10;
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 1:
                u1Var.g0(this.f17868m, this.f17869n);
                break;
            default:
                u1Var.n0(this.f17868m, this.f17869n);
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.K(this.f17868m, this.f17869n);
                break;
            default:
                u4.K(this.f17868m, this.f17869n);
                break;
        }
    }
}
