package k3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Z implements X {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SurfaceHolderCallbackC1625a0 f17874m;

    public /* synthetic */ Z(SurfaceHolderCallbackC1625a0 surfaceHolderCallbackC1625a0, int i7) {
        this.l = i7;
        this.f17874m = surfaceHolderCallbackC1625a0;
    }

    @Override // k3.X
    public final void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                C1628b0 c1628b0 = this.f17874m.l;
                interfaceC1655m.s(c1628b0.c, i7, c1628b0.f17922w);
                break;
            case 1:
                interfaceC1655m.s(this.f17874m.l.c, i7, null);
                break;
            case 2:
                C1628b0 c1628b02 = this.f17874m.l;
                interfaceC1655m.s(c1628b02.c, i7, c1628b02.f17922w);
                break;
            default:
                interfaceC1655m.s(this.f17874m.l.c, i7, null);
                break;
        }
    }
}
