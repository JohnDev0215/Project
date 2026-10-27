package x;

/* JADX INFO: loaded from: classes.dex */
public final class I0 implements F0 {
    public final G0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23417m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f23418n;
    public final long o;

    public I0(G0 g02, int i7, long j3) {
        this.l = g02;
        this.f23417m = i7;
        this.f23418n = ((long) (g02.r() + g02.n())) * 1000000;
        this.o = j3 * 1000000;
    }

    @Override // x.F0
    public final boolean a() {
        return true;
    }

    @Override // x.F0
    public final long b(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return Long.MAX_VALUE;
    }

    @Override // x.F0
    public final AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return this.l.c(d(j3), abstractC2652s, abstractC2652s2, e(j3, abstractC2652s, abstractC2652s3, abstractC2652s2));
    }

    public final long d(long j3) {
        long j10 = j3 + this.o;
        if (j10 <= 0) {
            return 0L;
        }
        long j11 = this.f23418n;
        long j12 = j10 / j11;
        return (this.f23417m == 1 || j12 % ((long) 2) == 0) ? j10 - (j12 * j11) : ((j12 + 1) * j11) - j10;
    }

    public final AbstractC2652s e(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        long j10 = this.o;
        long j11 = j3 + j10;
        long j12 = this.f23418n;
        return j11 > j12 ? this.l.c(j12 - j10, abstractC2652s, abstractC2652s3, abstractC2652s2) : abstractC2652s2;
    }

    @Override // x.F0
    public final AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        return this.l.o(d(j3), abstractC2652s, abstractC2652s2, e(j3, abstractC2652s, abstractC2652s3, abstractC2652s2));
    }
}
