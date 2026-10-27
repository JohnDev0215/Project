package k3;

/* JADX INFO: renamed from: k3.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1680z implements h2.l, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e2.I f18169m;

    public /* synthetic */ C1680z(e2.I i7, int i10) {
        this.l = i10;
        this.f18169m = i7;
    }

    @Override // h2.g
    public void accept(Object obj) {
        ((u1) obj).G0(this.f18169m);
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 0:
                u4.z(this.f18169m);
                break;
            default:
                u4.F(this.f18169m);
                break;
        }
    }
}
