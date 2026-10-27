package O3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f5087b;

    public /* synthetic */ e(j jVar, int i7) {
        this.f5086a = i7;
        this.f5087b = jVar;
    }

    @Override // O3.i
    public final void run() {
        switch (this.f5086a) {
            case 0:
                this.f5087b.j();
                break;
            default:
                this.f5087b.h();
                break;
        }
    }
}
