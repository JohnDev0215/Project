package W2;

import B2.F;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class b implements g {
    public final f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f9717m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f9718n;
    public final i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9719p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f9720q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f9721r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f9722s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f9723t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f9724u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f9725v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f9726w;

    public b(i iVar, long j3, long j10, long j11, long j12, boolean z6) {
        AbstractC1346c.c(j3 >= 0 && j10 > j3);
        this.o = iVar;
        this.f9717m = j3;
        this.f9718n = j10;
        if (j11 == j10 - j3 || z6) {
            this.f9720q = j12;
            this.f9719p = 4;
        } else {
            this.f9719p = 0;
        }
        this.l = new f();
    }

    @Override // W2.g
    public final F f() {
        if (this.f9720q != 0) {
            return new a(this);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c2  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v8 */
    @Override // W2.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long g(B2.C0073m r25) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W2.b.g(B2.m):long");
    }

    @Override // W2.g
    public final void i(long j3) {
        this.f9722s = AbstractC1343A.j(j3, 0L, this.f9720q - 1);
        this.f9719p = 2;
        this.f9723t = this.f9717m;
        this.f9724u = this.f9718n;
        this.f9725v = 0L;
        this.f9726w = this.f9720q;
    }
}
