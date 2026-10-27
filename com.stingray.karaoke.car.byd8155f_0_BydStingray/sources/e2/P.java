package e2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class P {
    public static final P d = new P(1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f13993e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f13994f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f13995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f13996b;
    public final int c;

    static {
        int i7 = AbstractC1343A.f15195a;
        f13993e = Integer.toString(0, 36);
        f13994f = Integer.toString(1, 36);
    }

    public P(float f10) {
        this(f10, 1.0f);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || P.class != obj.getClass()) {
            return false;
        }
        P p10 = (P) obj;
        return this.f13995a == p10.f13995a && this.f13996b == p10.f13996b;
    }

    public final int hashCode() {
        return Float.floatToRawIntBits(this.f13996b) + ((Float.floatToRawIntBits(this.f13995a) + 527) * 31);
    }

    public final String toString() {
        Object[] objArr = {Float.valueOf(this.f13995a), Float.valueOf(this.f13996b)};
        int i7 = AbstractC1343A.f15195a;
        return String.format(Locale.US, "PlaybackParameters(speed=%.2f, pitch=%.2f)", objArr);
    }

    public P(float f10, float f11) {
        AbstractC1346c.c(f10 > 0.0f);
        AbstractC1346c.c(f11 > 0.0f);
        this.f13995a = f10;
        this.f13996b = f11;
        this.c = Math.round(f10 * 1000.0f);
    }
}
