package k3;

/* JADX INFO: renamed from: k3.I, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1615I implements h2.l, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f17791m;

    public /* synthetic */ C1615I(float f10, int i7) {
        this.l = i7;
        this.f17791m = f10;
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 1:
                u1Var.l(this.f17791m);
                break;
            default:
                u1Var.J0(this.f17791m);
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.r(this.f17791m);
                break;
            default:
                u4.r(this.f17791m);
                break;
        }
    }
}
