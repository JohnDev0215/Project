package v6;

/* JADX INFO: renamed from: v6.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2555c implements w6.d {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23022m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.c f23023n;
    public final w6.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w6.c f23024p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w6.b f23025q;

    public C2555c(int i7, int i10) {
        this.l = i7;
        this.f23022m = i10;
        Float fValueOf = Float.valueOf(0.0f);
        this.f23023n = new w6.c(fValueOf);
        this.o = new w6.c(-1);
        this.f23024p = new w6.c(fValueOf);
        this.f23025q = new w6.b();
        for (int i11 = 0; i11 < i7; i11++) {
            this.f23025q.add(new C2558f(this.f23022m));
        }
        l();
    }

    @Override // w6.d
    public final boolean d() {
        return this.f23025q.d() || this.o.f23341m || this.f23024p.f23341m || this.f23023n.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.f23025q.l();
        this.f23023n.f23341m = false;
        this.o.f23341m = false;
        this.f23024p.f23341m = false;
    }
}
