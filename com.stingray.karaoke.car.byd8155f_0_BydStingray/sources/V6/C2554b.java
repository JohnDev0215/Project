package v6;

/* JADX INFO: renamed from: v6.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2554b implements w6.d {
    public final w6.c l = new w6.c(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w6.c f23020m = new w6.c(Float.valueOf(0.0f));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.c f23021n = new w6.c(Boolean.FALSE);

    @Override // w6.d
    public final boolean d() {
        return this.l.f23341m || this.f23020m.f23341m || this.f23021n.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.l.f23341m = false;
        this.f23020m.f23341m = false;
        this.f23021n.f23341m = false;
    }
}
