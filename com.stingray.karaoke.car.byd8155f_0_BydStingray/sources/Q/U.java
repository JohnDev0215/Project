package Q;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5681b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f5682e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5683f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f5684g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5685h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f5686i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f5687j;

    public U(long j3, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18) {
        this.f5680a = j3;
        this.f5681b = j10;
        this.c = j11;
        this.d = j12;
        this.f5682e = j13;
        this.f5683f = j14;
        this.f5684g = j15;
        this.f5685h = j16;
        this.f5686i = j17;
        this.f5687j = j18;
    }

    public final long a(boolean z6, boolean z10) {
        return z6 ? z10 ? this.f5681b : this.d : z10 ? this.f5684g : this.f5686i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof U)) {
            return false;
        }
        U u4 = (U) obj;
        return C1891t.c(this.f5680a, u4.f5680a) && C1891t.c(this.f5681b, u4.f5681b) && C1891t.c(this.c, u4.c) && C1891t.c(this.d, u4.d) && C1891t.c(this.f5682e, u4.f5682e) && C1891t.c(this.f5683f, u4.f5683f) && C1891t.c(this.f5684g, u4.f5684g) && C1891t.c(this.f5685h, u4.f5685h) && C1891t.c(this.f5686i, u4.f5686i) && C1891t.c(this.f5687j, u4.f5687j);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f5687j) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f5680a) * 31, 31, this.f5681b), 31, this.c), 31, this.d), 31, this.f5682e), 31, this.f5683f), 31, this.f5684g), 31, this.f5685h), 31, this.f5686i);
    }
}
