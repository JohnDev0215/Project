package m0;

import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18880a;

    public /* synthetic */ c(long j3) {
        this.f18880a = j3;
    }

    public static long a(long j3, float f10, int i7) {
        float fIntBitsToFloat = (i7 & 1) != 0 ? Float.intBitsToFloat((int) (j3 >> 32)) : 0.0f;
        if ((i7 & 2) != 0) {
            f10 = Float.intBitsToFloat((int) (j3 & 4294967295L));
        }
        return (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(f10)) & 4294967295L);
    }

    public static final boolean b(long j3, long j10) {
        return j3 == j10;
    }

    public static final float c(long j3) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j3 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j3 & 4294967295L));
        return (float) Math.sqrt((fIntBitsToFloat2 * fIntBitsToFloat2) + (fIntBitsToFloat * fIntBitsToFloat));
    }

    public static final float d(long j3) {
        return Float.intBitsToFloat((int) (j3 >> 32));
    }

    public static final float e(long j3) {
        return Float.intBitsToFloat((int) (j3 & 4294967295L));
    }

    public static final boolean f(long j3) {
        long j10 = j3 & 9223372034707292159L;
        return (((~j10) & (j10 - 9187343246269874177L)) & (-9223372034707292160L)) == -9223372034707292160L;
    }

    public static final long g(long j3, long j10) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j3 >> 32)) - Float.intBitsToFloat((int) (j10 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j3 & 4294967295L)) - Float.intBitsToFloat((int) (j10 & 4294967295L));
        return (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L);
    }

    public static final long h(long j3, long j10) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j10 >> 32)) + Float.intBitsToFloat((int) (j3 >> 32));
        return (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j10 & 4294967295L)) + Float.intBitsToFloat((int) (j3 & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32);
    }

    public static final long i(float f10, long j3) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j3 >> 32)) * f10;
        return (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j3 & 4294967295L)) * f10)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32);
    }

    public static String j(long j3) {
        if (!h6.e.m(j3)) {
            return "Offset.Unspecified";
        }
        return "Offset(" + AbstractC1985B.v(d(j3)) + ", " + AbstractC1985B.v(e(j3)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            return this.f18880a == ((c) obj).f18880a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f18880a);
    }

    public final String toString() {
        return j(this.f18880a);
    }
}
