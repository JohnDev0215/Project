package Q;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: renamed from: Q.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0498c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5842b;
    public final long c;
    public final long d;

    public C0498c(long j3, long j10, long j11, long j12) {
        this.f5841a = j3;
        this.f5842b = j10;
        this.c = j11;
        this.d = j12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C0498c)) {
            return false;
        }
        C0498c c0498c = (C0498c) obj;
        return C1891t.c(this.f5841a, c0498c.f5841a) && C1891t.c(this.f5842b, c0498c.f5842b) && C1891t.c(this.c, c0498c.c) && C1891t.c(this.d, c0498c.d);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.d) + AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f5841a) * 31, 31, this.f5842b), 31, this.c);
    }
}
