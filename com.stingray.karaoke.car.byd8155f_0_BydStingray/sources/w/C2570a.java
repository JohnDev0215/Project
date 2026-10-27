package w;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: w.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2570a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23087b;

    public C2570a(float f10, float f11) {
        this.f23086a = f10;
        this.f23087b = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2570a)) {
            return false;
        }
        C2570a c2570a = (C2570a) obj;
        return Float.compare(this.f23086a, c2570a.f23086a) == 0 && Float.compare(this.f23087b, c2570a.f23087b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f23087b) + (Float.hashCode(this.f23086a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FlingResult(distanceCoefficient=");
        sb.append(this.f23086a);
        sb.append(", velocityCoefficient=");
        return AbstractC1347d.h(sb, this.f23087b, ')');
    }
}
