package k3;

/* JADX INFO: renamed from: k3.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1676x implements X, h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1628b0 f18155m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f18156n;

    public /* synthetic */ C1676x(C1628b0 c1628b0, int i7, int i10) {
        this.l = i10;
        this.f18155m = c1628b0;
        this.f18156n = i7;
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 1:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
            case 2:
            case 4:
            case 6:
            case 7:
            default:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
            case 3:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
            case 5:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
            case 8:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
            case 9:
                u4.S(this.f18156n, this.f18155m.o.f18085s);
                break;
        }
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                interfaceC1655m.l1(this.f18155m.c, i7, this.f18156n);
                break;
            case 1:
            case 3:
            case 5:
            default:
                interfaceC1655m.l0(this.f18155m.c, i7, this.f18156n);
                break;
            case 2:
                interfaceC1655m.k0(this.f18155m.c, i7, this.f18156n);
                break;
            case 4:
                interfaceC1655m.Y(this.f18155m.c, i7, this.f18156n);
                break;
            case 6:
                interfaceC1655m.d0(this.f18155m.c, i7, this.f18156n);
                break;
            case 7:
                interfaceC1655m.C(this.f18155m.c, i7, this.f18156n);
                break;
        }
    }
}
