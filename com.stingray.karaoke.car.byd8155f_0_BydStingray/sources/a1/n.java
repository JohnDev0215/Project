package a1;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11043a;

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static String b(long j3) {
        return a(j3, 0L) ? "Unspecified" : a(j3, 4294967296L) ? "Sp" : a(j3, 8589934592L) ? "Em" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            return this.f11043a == ((n) obj).f11043a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11043a);
    }

    public final String toString() {
        return b(this.f11043a);
    }
}
