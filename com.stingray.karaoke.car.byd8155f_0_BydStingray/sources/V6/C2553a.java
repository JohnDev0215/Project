package v6;

/* JADX INFO: renamed from: v6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2553a implements w6.d {
    public final w6.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w6.c f23019m;

    public C2553a() {
        Float fValueOf = Float.valueOf(0.0f);
        this.l = new w6.c(fValueOf);
        this.f23019m = new w6.c(fValueOf);
    }

    @Override // w6.d
    public final boolean d() {
        return this.l.f23341m || this.f23019m.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.l.f23341m = false;
        this.f23019m.f23341m = false;
    }
}
