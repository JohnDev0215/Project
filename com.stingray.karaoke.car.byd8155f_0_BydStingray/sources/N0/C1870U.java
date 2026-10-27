package n0;

/* JADX INFO: renamed from: n0.U, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1870U {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f19422b = AbstractC1861K.j(0.5f, 0.5f);
    public static final /* synthetic */ int c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19423a;

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static final float b(long j3) {
        return Float.intBitsToFloat((int) (j3 >> 32));
    }

    public static final float c(long j3) {
        return Float.intBitsToFloat((int) (j3 & 4294967295L));
    }

    public static String d(long j3) {
        return "TransformOrigin(packedValue=" + j3 + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1870U) {
            return this.f19423a == ((C1870U) obj).f19423a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f19423a);
    }

    public final String toString() {
        return d(this.f19423a);
    }
}
