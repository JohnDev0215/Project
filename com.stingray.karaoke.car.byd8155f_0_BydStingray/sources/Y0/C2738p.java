package y0;

/* JADX INFO: renamed from: y0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2738p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24076a;

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static String b(long j3) {
        return "PointerId(value=" + j3 + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2738p) {
            return this.f24076a == ((C2738p) obj).f24076a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f24076a);
    }

    public final String toString() {
        return b(this.f24076a);
    }
}
