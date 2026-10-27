package o0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: o0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2023s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f20231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f20232b;

    public C2023s(float f10, float f11) {
        this.f20231a = f10;
        this.f20232b = f11;
    }

    public final float[] a() {
        float f10 = this.f20231a;
        float f11 = this.f20232b;
        return new float[]{f10 / f11, 1.0f, ((1.0f - f10) - f11) / f11};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2023s)) {
            return false;
        }
        C2023s c2023s = (C2023s) obj;
        return Float.compare(this.f20231a, c2023s.f20231a) == 0 && Float.compare(this.f20232b, c2023s.f20232b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f20232b) + (Float.hashCode(this.f20231a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WhitePoint(x=");
        sb.append(this.f20231a);
        sb.append(", y=");
        return AbstractC1347d.h(sb, this.f20232b, ')');
    }
}
