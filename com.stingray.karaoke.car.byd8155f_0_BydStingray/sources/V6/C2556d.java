package v6;

/* JADX INFO: renamed from: v6.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2556d implements w6.d {
    public final char l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.c f23027n = new w6.c(Float.valueOf(0.0f));

    public C2556d(char c, int i7) {
        this.l = c;
        this.f23026m = i7;
    }

    @Override // w6.d
    public final boolean d() {
        return this.f23027n.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.f23027n.f23341m = false;
    }
}
