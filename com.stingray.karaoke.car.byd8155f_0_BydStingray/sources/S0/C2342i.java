package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2342i extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22042e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22043f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f22044g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22045h;

    public C2342i(float f10, float f11, float f12, float f13, float f14, float f15) {
        super(2, true, false);
        this.c = f10;
        this.d = f11;
        this.f22042e = f12;
        this.f22043f = f13;
        this.f22044g = f14;
        this.f22045h = f15;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2342i)) {
            return false;
        }
        C2342i c2342i = (C2342i) obj;
        return Float.compare(this.c, c2342i.c) == 0 && Float.compare(this.d, c2342i.d) == 0 && Float.compare(this.f22042e, c2342i.f22042e) == 0 && Float.compare(this.f22043f, c2342i.f22043f) == 0 && Float.compare(this.f22044g, c2342i.f22044g) == 0 && Float.compare(this.f22045h, c2342i.f22045h) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22045h) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22042e), 31, this.f22043f), 31, this.f22044g);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CurveTo(x1=");
        sb.append(this.c);
        sb.append(", y1=");
        sb.append(this.d);
        sb.append(", x2=");
        sb.append(this.f22042e);
        sb.append(", y2=");
        sb.append(this.f22043f);
        sb.append(", x3=");
        sb.append(this.f22044g);
        sb.append(", y3=");
        return AbstractC1347d.h(sb, this.f22045h, ')');
    }
}
