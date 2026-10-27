package Y0;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static final n c = new n(1.0f, 0.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f10623a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f10624b;

    public n(float f10, float f11) {
        this.f10623a = f10;
        this.f10624b = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f10623a == nVar.f10623a && this.f10624b == nVar.f10624b;
    }

    public final int hashCode() {
        return Float.hashCode(this.f10624b) + (Float.hashCode(this.f10623a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextGeometricTransform(scaleX=");
        sb.append(this.f10623a);
        sb.append(", skewX=");
        return AbstractC1347d.h(sb, this.f10624b, ')');
    }
}
