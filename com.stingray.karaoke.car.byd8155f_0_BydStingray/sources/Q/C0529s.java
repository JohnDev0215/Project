package Q;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: renamed from: Q.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0529s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6037a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6038b;
    public final long c;
    public final long d;

    public C0529s(long j3, long j10, long j11, long j12) {
        this.f6037a = j3;
        this.f6038b = j10;
        this.c = j11;
        this.d = j12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C0529s)) {
            return false;
        }
        C0529s c0529s = (C0529s) obj;
        return C1891t.c(this.f6037a, c0529s.f6037a) && C1891t.c(this.f6038b, c0529s.f6038b) && C1891t.c(this.c, c0529s.c) && C1891t.c(this.d, c0529s.d);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.d) + AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f6037a) * 31, 31, this.f6038b), 31, this.c);
    }
}
