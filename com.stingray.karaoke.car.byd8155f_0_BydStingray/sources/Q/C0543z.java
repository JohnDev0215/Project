package Q;

import h2.AbstractC1347d;
import n0.C1891t;

/* JADX INFO: renamed from: Q.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0543z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6073a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6074b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f6075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6076f;

    public C0543z(long j3, long j10, long j11, long j12, long j13, long j14) {
        this.f6073a = j3;
        this.f6074b = j10;
        this.c = j11;
        this.d = j12;
        this.f6075e = j13;
        this.f6076f = j14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C0543z)) {
            return false;
        }
        C0543z c0543z = (C0543z) obj;
        return C1891t.c(this.f6073a, c0543z.f6073a) && C1891t.c(this.f6074b, c0543z.f6074b) && C1891t.c(this.c, c0543z.c) && C1891t.c(this.d, c0543z.d) && C1891t.c(this.f6075e, c0543z.f6075e) && C1891t.c(this.f6076f, c0543z.f6076f);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f6076f) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f6073a) * 31, 31, this.f6074b), 31, this.c), 31, this.d), 31, this.f6075e);
    }
}
