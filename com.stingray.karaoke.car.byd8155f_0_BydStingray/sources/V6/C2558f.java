package v6;

/* JADX INFO: renamed from: v6.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2558f implements w6.d {
    public final C2553a l = new C2553a();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2554b f23033m = new C2554b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.c f23034n = new w6.c(Float.valueOf(0.0f));
    public final w6.b o = new w6.b();

    public C2558f(int i7) {
        for (int i10 = 0; i10 < i7; i10++) {
            this.o.add(new C2557e());
        }
    }

    @Override // w6.d
    public final boolean d() {
        return this.o.d() || this.f23034n.f23341m || this.f23033m.d() || this.l.d();
    }

    @Override // w6.d
    public final void l() {
        this.o.l();
        this.f23034n.f23341m = false;
        this.l.l();
        this.f23033m.l();
    }
}
