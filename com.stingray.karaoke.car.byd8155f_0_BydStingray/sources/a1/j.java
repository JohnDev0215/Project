package a1;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11037a;

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static String b(long j3) {
        return ((int) (j3 >> 32)) + " x " + ((int) (j3 & 4294967295L));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f11037a == ((j) obj).f11037a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11037a);
    }

    public final String toString() {
        return b(this.f11037a);
    }
}
