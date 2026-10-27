package a1;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11032a;

    public static final float a(long j3) {
        return Float.intBitsToFloat((int) (j3 & 4294967295L));
    }

    public static final float b(long j3) {
        return Float.intBitsToFloat((int) (j3 >> 32));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            return this.f11032a == ((g) obj).f11032a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11032a);
    }

    public final String toString() {
        long j3 = this.f11032a;
        if (j3 == 9205357640488583168L) {
            return "DpSize.Unspecified";
        }
        return ((Object) e.b(b(j3))) + " x " + ((Object) e.b(a(j3)));
    }
}
