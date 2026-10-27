package a1;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class c implements b {
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f11028m;

    public c(float f10, float f11) {
        this.l = f10;
        this.f11028m = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Float.compare(this.l, cVar.l) == 0 && Float.compare(this.f11028m, cVar.f11028m) == 0;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l;
    }

    public final int hashCode() {
        return Float.hashCode(this.f11028m) + (Float.hashCode(this.l) * 31);
    }

    @Override // a1.b
    public final float p() {
        return this.f11028m;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DensityImpl(density=");
        sb.append(this.l);
        sb.append(", fontScale=");
        return AbstractC1347d.h(sb, this.f11028m, ')');
    }
}
