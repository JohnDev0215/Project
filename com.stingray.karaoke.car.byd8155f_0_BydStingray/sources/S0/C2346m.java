package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2346m extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22047f;

    public C2346m(float f10, float f11, float f12, float f13) {
        super(1, false, true);
        this.c = f10;
        this.d = f11;
        this.f22046e = f12;
        this.f22047f = f13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2346m)) {
            return false;
        }
        C2346m c2346m = (C2346m) obj;
        return Float.compare(this.c, c2346m.c) == 0 && Float.compare(this.d, c2346m.d) == 0 && Float.compare(this.f22046e, c2346m.f22046e) == 0 && Float.compare(this.f22047f, c2346m.f22047f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22047f) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22046e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("QuadTo(x1=");
        sb.append(this.c);
        sb.append(", y1=");
        sb.append(this.d);
        sb.append(", x2=");
        sb.append(this.f22046e);
        sb.append(", y2=");
        return AbstractC1347d.h(sb, this.f22047f, ')');
    }
}
