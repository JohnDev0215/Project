package g0;

import h2.AbstractC1347d;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class g implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f14678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f14679b;

    public g(float f10, float f11) {
        this.f14678a = f10;
        this.f14679b = f11;
    }

    @Override // g0.c
    public final long a(long j3, long j10, a1.k kVar) {
        float f10 = (((int) (j10 >> 32)) - ((int) (j3 >> 32))) / 2.0f;
        float f11 = (((int) (j10 & 4294967295L)) - ((int) (j3 & 4294967295L))) / 2.0f;
        a1.k kVar2 = a1.k.l;
        float f12 = this.f14678a;
        if (kVar != kVar2) {
            f12 *= -1;
        }
        float f13 = 1;
        return AbstractC2673a.d(Math.round((f12 + f13) * f10), Math.round((f13 + this.f14679b) * f11));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return Float.compare(this.f14678a, gVar.f14678a) == 0 && Float.compare(this.f14679b, gVar.f14679b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f14679b) + (Float.hashCode(this.f14678a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BiasAlignment(horizontalBias=");
        sb.append(this.f14678a);
        sb.append(", verticalBias=");
        return AbstractC1347d.h(sb, this.f14679b, ')');
    }
}
