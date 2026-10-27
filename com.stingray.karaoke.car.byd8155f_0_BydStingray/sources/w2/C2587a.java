package w2;

/* JADX INFO: renamed from: w2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2587a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f23170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f23171b;

    public C2587a(long j3, long j10) {
        this.f23170a = j3;
        this.f23171b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2587a)) {
            return false;
        }
        C2587a c2587a = (C2587a) obj;
        return this.f23170a == c2587a.f23170a && this.f23171b == c2587a.f23171b;
    }

    public final int hashCode() {
        return (((int) this.f23170a) * 31) + ((int) this.f23171b);
    }
}
