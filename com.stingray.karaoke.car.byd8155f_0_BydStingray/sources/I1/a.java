package I1;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2909b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2910e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2911f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2912g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2913h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2914i;

    public final float a(long j3) {
        if (j3 < this.f2910e) {
            return 0.0f;
        }
        long j10 = this.f2912g;
        if (j10 < 0 || j3 < j10) {
            return d.b((j3 - r0) / this.f2908a, 0.0f, 1.0f) * 0.5f;
        }
        float f10 = this.f2913h;
        return (d.b((j3 - j10) / this.f2914i, 0.0f, 1.0f) * f10) + (1.0f - f10);
    }
}
