package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2347n extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22049f;

    public C2347n(float f10, float f11, float f12, float f13) {
        super(2, true, false);
        this.c = f10;
        this.d = f11;
        this.f22048e = f12;
        this.f22049f = f13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2347n)) {
            return false;
        }
        C2347n c2347n = (C2347n) obj;
        return Float.compare(this.c, c2347n.c) == 0 && Float.compare(this.d, c2347n.d) == 0 && Float.compare(this.f22048e, c2347n.f22048e) == 0 && Float.compare(this.f22049f, c2347n.f22049f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22049f) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22048e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ReflectiveCurveTo(x1=");
        sb.append(this.c);
        sb.append(", y1=");
        sb.append(this.d);
        sb.append(", x2=");
        sb.append(this.f22048e);
        sb.append(", y2=");
        return AbstractC1347d.h(sb, this.f22049f, ')');
    }
}
