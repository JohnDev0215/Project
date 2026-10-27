package m0;

import n7.AbstractC1985B;
import n7.z;

/* JADX INFO: renamed from: m0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1792a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f18876a = z.a(0.0f, 0.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f18877b = 0;

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
        if (b(j3) == c(j3)) {
            return "CornerRadius.circular(" + AbstractC1985B.v(b(j3)) + ')';
        }
        return "CornerRadius.elliptical(" + AbstractC1985B.v(b(j3)) + ", " + AbstractC1985B.v(c(j3)) + ')';
    }
}
