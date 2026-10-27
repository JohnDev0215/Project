package u2;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class N implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T f22691m;

    public /* synthetic */ N(T t10, int i7) {
        this.l = i7;
        this.f22691m = t10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f22691m.x();
                break;
            case 1:
                T t10 = this.f22691m;
                if (!t10.f22733a0) {
                    InterfaceC2522z interfaceC2522z = t10.f22710D;
                    interfaceC2522z.getClass();
                    interfaceC2522z.e(t10);
                }
                break;
            default:
                this.f22691m.f22727U = true;
                break;
        }
    }
}
