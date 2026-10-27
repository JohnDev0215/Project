package B2;

import h2.AbstractC1343A;

/* JADX INFO: renamed from: B2.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0067g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f570a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f571b;
    public final long c;
    public long d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f572e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f573f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f574g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f575h;

    public C0067g(long j3, long j10, long j11, long j12, long j13, long j14) {
        this.f570a = j3;
        this.f571b = j10;
        this.f572e = j11;
        this.f573f = j12;
        this.f574g = j13;
        this.c = j14;
        this.f575h = a(j10, 0L, j11, j12, j13, j14);
    }

    public static long a(long j3, long j10, long j11, long j12, long j13, long j14) {
        if (j12 + 1 >= j13 || j10 + 1 >= j11) {
            return j12;
        }
        long j15 = (long) ((j3 - j10) * ((j13 - j12) / (j11 - j10)));
        return AbstractC1343A.j(((j15 + j12) - j14) - (j15 / 20), j12, j13 - 1);
    }
}
