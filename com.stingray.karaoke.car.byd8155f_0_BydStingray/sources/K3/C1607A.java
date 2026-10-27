package k3;

/* JADX INFO: renamed from: k3.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1607A implements X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f17711m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17712n;
    public final /* synthetic */ int o;

    public /* synthetic */ C1607A(C1628b0 c1628b0, int i7, int i10, int i11) {
        this.l = i11;
        this.f17711m = c1628b0;
        this.f17712n = i7;
        this.o = i10;
    }

    @Override // k3.X
    public final void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                interfaceC1655m.U0(this.f17711m.c, i7, this.f17712n, this.o);
                break;
            case 1:
                interfaceC1655m.a1(this.f17711m.c, i7, this.f17712n, this.o);
                break;
            default:
                interfaceC1655m.W0(this.f17711m.c, i7, this.f17712n, this.o);
                break;
        }
    }
}
