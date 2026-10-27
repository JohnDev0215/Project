package io.sentry.android.core;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements Comparable {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f16126m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16127n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f16128p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16129q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final long f16130r;

    public Z(long j3) {
        this(j3, j3, 0L, 0L, false, false, 0L);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f16126m, ((Z) obj).f16126m);
    }

    public Z(long j3, long j10, long j11, long j12, boolean z6, boolean z10, long j13) {
        this.l = j3;
        this.f16126m = j10;
        this.f16127n = j11;
        this.o = j12;
        this.f16128p = z6;
        this.f16129q = z10;
        this.f16130r = j13;
    }
}
