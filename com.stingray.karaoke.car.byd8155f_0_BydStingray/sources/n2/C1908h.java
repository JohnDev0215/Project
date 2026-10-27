package n2;

/* JADX INFO: renamed from: n2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1908h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19662b;
    public long c = -9223372036854775807L;
    public long d = -9223372036854775807L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f19664f = -9223372036854775807L;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f19665g = -9223372036854775807L;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f19668j = 0.97f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f19667i = 1.03f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f19669k = 1.0f;
    public long l = -9223372036854775807L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f19663e = -9223372036854775807L;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f19666h = -9223372036854775807L;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f19670m = -9223372036854775807L;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f19671n = -9223372036854775807L;

    public C1908h(long j3, long j10) {
        this.f19661a = j3;
        this.f19662b = j10;
    }

    public final void a() {
        long j3;
        long j10 = this.c;
        if (j10 != -9223372036854775807L) {
            j3 = this.d;
            if (j3 == -9223372036854775807L) {
                long j11 = this.f19664f;
                if (j11 != -9223372036854775807L && j10 < j11) {
                    j10 = j11;
                }
                j3 = this.f19665g;
                if (j3 == -9223372036854775807L || j10 <= j3) {
                    j3 = j10;
                }
            }
        } else {
            j3 = -9223372036854775807L;
        }
        if (this.f19663e == j3) {
            return;
        }
        this.f19663e = j3;
        this.f19666h = j3;
        this.f19670m = -9223372036854775807L;
        this.f19671n = -9223372036854775807L;
        this.l = -9223372036854775807L;
    }
}
