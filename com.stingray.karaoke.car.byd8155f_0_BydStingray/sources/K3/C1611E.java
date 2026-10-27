package k3;

/* JADX INFO: renamed from: k3.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1611E implements h2.l, X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f17753m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f17754n;

    public /* synthetic */ C1611E(C1628b0 c1628b0, boolean z6, int i7) {
        this.l = i7;
        this.f17753m = c1628b0;
        this.f17754n = z6;
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.S(this.f17753m.o.f18084r, this.f17754n);
                break;
            default:
                u4.S(this.f17753m.o.f18084r, this.f17754n);
                break;
        }
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 1:
                interfaceC1655m.o(this.f17753m.c, i7, this.f17754n);
                break;
            case 2:
                interfaceC1655m.a0(this.f17753m.c, i7, this.f17754n);
                break;
            default:
                interfaceC1655m.g1(this.f17753m.c, i7, this.f17754n);
                break;
        }
    }
}
