package k3;

/* JADX INFO: renamed from: k3.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1613G implements h2.l, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f17779m;

    public /* synthetic */ C1613G(int i7, boolean z6) {
        this.l = i7;
        this.f17779m = z6;
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 1:
                u1Var.q(this.f17779m);
                break;
            case 2:
                u1Var.h0(this.f17779m);
                break;
            default:
                u1Var.F(this.f17779m);
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.I(this.f17779m);
                break;
            case 4:
                u4.I(this.f17779m);
                break;
            default:
                u4.m(this.f17779m);
                break;
        }
    }
}
