package U2;

import B2.C0072l;

/* JADX INFO: loaded from: classes.dex */
public final class a extends C0072l implements f {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f8962h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f8963i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f8964j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f8965k;
    public final long l;

    public a(long j3, long j10, int i7, int i10, boolean z6) {
        super(j3, j10, i7, i10, z6);
        this.f8962h = j10;
        this.f8963i = i7;
        this.f8964j = i10;
        this.f8965k = z6;
        this.l = j3 == -1 ? -1L : j3;
    }

    @Override // U2.f
    public final long d() {
        return this.l;
    }

    @Override // U2.f
    public final long i(long j3) {
        return (Math.max(0L, j3 - this.f583b) * 8000000) / ((long) this.f584e);
    }

    @Override // U2.f
    public final int k() {
        return this.f8963i;
    }
}
