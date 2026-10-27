package k3;

/* JADX INFO: renamed from: k3.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1608B implements X, P0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f17719m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f17720n;

    public /* synthetic */ C1608B(float f10, int i7, Object obj) {
        this.l = i7;
        this.f17719m = obj;
        this.f17720n = f10;
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        ((Q0) this.f17719m).f17843f.f17811t.l(this.f17720n);
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                interfaceC1655m.J(((C1628b0) this.f17719m).c, i7, this.f17720n);
                break;
            default:
                interfaceC1655m.I(((C1628b0) this.f17719m).c, i7, this.f17720n);
                break;
        }
    }
}
