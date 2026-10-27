package a1;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11031a;

    public final boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f11031a == ((f) obj).f11031a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11031a);
    }

    public final String toString() {
        long j3 = this.f11031a;
        if (j3 == 9205357640488583168L) {
            return "DpOffset.Unspecified";
        }
        return "(" + ((Object) e.b(Float.intBitsToFloat((int) (j3 >> 32)))) + ", " + ((Object) e.b(Float.intBitsToFloat((int) (j3 & 4294967295L)))) + ')';
    }
}
