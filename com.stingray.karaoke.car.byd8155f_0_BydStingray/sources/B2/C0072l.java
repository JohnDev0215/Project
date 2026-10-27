package B2;

/* JADX INFO: renamed from: B2.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0072l implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f582a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f583b;
    public final int c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f584e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f585f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f586g;

    public C0072l(long j3, long j10, int i7, int i10, boolean z6) {
        this.f582a = j3;
        this.f583b = j10;
        this.c = i10 == -1 ? 1 : i10;
        this.f584e = i7;
        this.f586g = z6;
        if (j3 == -1) {
            this.d = -1L;
            this.f585f = -9223372036854775807L;
        } else {
            long j11 = j3 - j10;
            this.d = j11;
            this.f585f = (Math.max(0L, j11) * 8000000) / ((long) i7);
        }
    }

    @Override // B2.F
    public final boolean h() {
        return this.d != -1 || this.f586g;
    }

    @Override // B2.F
    public final E j(long j3) {
        long j10 = this.d;
        long j11 = this.f583b;
        if (j10 == -1 && !this.f586g) {
            G g7 = new G(0L, j11);
            return new E(g7, g7);
        }
        int i7 = this.f584e;
        long j12 = this.c;
        long jMin = (((((long) i7) * j3) / 8000000) / j12) * j12;
        if (j10 != -1) {
            jMin = Math.min(jMin, j10 - j12);
        }
        long jMax = Math.max(jMin, 0L) + j11;
        long jMax2 = (Math.max(0L, jMax - j11) * 8000000) / ((long) i7);
        G g10 = new G(jMax2, jMax);
        if (j10 != -1 && jMax2 < j3) {
            long j13 = jMax + j12;
            if (j13 < this.f582a) {
                return new E(g10, new G((Math.max(0L, j13 - j11) * 8000000) / ((long) i7), j13));
            }
        }
        return new E(g10, g10);
    }

    @Override // B2.F
    public final long l() {
        return this.f585f;
    }
}
