package a1;

import a.AbstractC0904a;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n[] f11041b = {new n(0), new n(4294967296L), new n(8589934592L)};
    public static final long c = AbstractC0904a.O(Float.NaN, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11042a;

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static final long b(long j3) {
        return f11041b[(int) ((j3 & 1095216660480L) >>> 32)].f11043a;
    }

    public static final float c(long j3) {
        return Float.intBitsToFloat((int) (j3 & 4294967295L));
    }

    public static String d(long j3) {
        long jB = b(j3);
        if (n.a(jB, 0L)) {
            return "Unspecified";
        }
        if (n.a(jB, 4294967296L)) {
            return c(j3) + ".sp";
        }
        if (!n.a(jB, 8589934592L)) {
            return "Invalid";
        }
        return c(j3) + ".em";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return this.f11042a == ((m) obj).f11042a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11042a);
    }

    public final String toString() {
        return d(this.f11042a);
    }
}
