package k3;

/* JADX INFO: loaded from: classes.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e2.F f18142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18143b;
    public final long c;

    public v1(e2.F f10, long j3, long j10) {
        this.f18142a = f10;
        this.f18143b = j3;
        this.c = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v1)) {
            return false;
        }
        v1 v1Var = (v1) obj;
        return this.f18143b == v1Var.f18143b && this.f18142a.equals(v1Var.f18142a) && this.c == v1Var.c;
    }

    public final int hashCode() {
        long j3 = this.f18143b;
        int iHashCode = (this.f18142a.hashCode() + ((217 + ((int) (j3 ^ (j3 >>> 32)))) * 31)) * 31;
        long j10 = this.c;
        return iHashCode + ((int) ((j10 >>> 32) ^ j10));
    }
}
