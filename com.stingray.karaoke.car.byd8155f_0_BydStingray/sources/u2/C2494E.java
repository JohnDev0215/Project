package u2;

/* JADX INFO: renamed from: u2.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2494E implements h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ B5.u f22666m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2516t f22667n;
    public final /* synthetic */ C2521y o;

    public /* synthetic */ C2494E(B5.u uVar, C2516t c2516t, C2521y c2521y, int i7) {
        this.l = i7;
        this.f22666m = uVar;
        this.f22667n = c2516t;
        this.o = c2521y;
    }

    @Override // h2.g
    public final void accept(Object obj) {
        InterfaceC2497H interfaceC2497H = (InterfaceC2497H) obj;
        switch (this.l) {
            case 0:
                B5.u uVar = this.f22666m;
                interfaceC2497H.u(uVar.f735m, (C2492C) uVar.f736n, this.f22667n, this.o);
                break;
            case 1:
                B5.u uVar2 = this.f22666m;
                interfaceC2497H.v(uVar2.f735m, (C2492C) uVar2.f736n, this.f22667n, this.o);
                break;
            default:
                B5.u uVar3 = this.f22666m;
                interfaceC2497H.B(uVar3.f735m, (C2492C) uVar3.f736n, this.f22667n, this.o);
                break;
        }
    }
}
