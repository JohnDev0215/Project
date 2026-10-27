package k3;

/* JADX INFO: renamed from: k3.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1609C implements h2.l, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e2.P f17740m;

    public /* synthetic */ C1609C(e2.P p10, int i7) {
        this.l = i7;
        this.f17740m = p10;
    }

    @Override // h2.g
    public void accept(Object obj) {
        ((u1) obj).f(this.f17740m);
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.G(this.f17740m);
                break;
            default:
                u4.G(this.f17740m);
                break;
        }
    }
}
