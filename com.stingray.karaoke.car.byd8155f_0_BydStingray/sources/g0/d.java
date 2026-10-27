package g0;

import H7.H;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f14675a;

    public d(float f10) {
        this.f14675a = f10;
    }

    @Override // g0.c
    public final long a(long j3, long j10, a1.k kVar) {
        long jC = H.c(((int) (j10 >> 32)) - ((int) (j3 >> 32)), ((int) (j10 & 4294967295L)) - ((int) (j3 & 4294967295L)));
        float f10 = 1;
        return AbstractC2673a.d(Math.round((this.f14675a + f10) * (((int) (jC >> 32)) / 2.0f)), Math.round((f10 - 1.0f) * (((int) (jC & 4294967295L)) / 2.0f)));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            return Float.compare(this.f14675a, ((d) obj).f14675a) == 0 && Float.compare(-1.0f, -1.0f) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(-1.0f) + (Float.hashCode(this.f14675a) * 31);
    }

    public final String toString() {
        return "BiasAbsoluteAlignment(horizontalBias=" + this.f14675a + ", verticalBias=-1.0)";
    }
}
