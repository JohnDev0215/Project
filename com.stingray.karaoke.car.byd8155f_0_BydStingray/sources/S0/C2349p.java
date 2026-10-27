package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2349p extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f22051f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f22052g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22053h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f22054i;

    public C2349p(float f10, float f11, float f12, boolean z6, boolean z10, float f13, float f14) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
        this.f22050e = f12;
        this.f22051f = z6;
        this.f22052g = z10;
        this.f22053h = f13;
        this.f22054i = f14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2349p)) {
            return false;
        }
        C2349p c2349p = (C2349p) obj;
        return Float.compare(this.c, c2349p.c) == 0 && Float.compare(this.d, c2349p.d) == 0 && Float.compare(this.f22050e, c2349p.f22050e) == 0 && this.f22051f == c2349p.f22051f && this.f22052g == c2349p.f22052g && Float.compare(this.f22053h, c2349p.f22053h) == 0 && Float.compare(this.f22054i, c2349p.f22054i) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22054i) + AbstractC1347d.b(AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22050e), 31, this.f22051f), 31, this.f22052g), 31, this.f22053h);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeArcTo(horizontalEllipseRadius=");
        sb.append(this.c);
        sb.append(", verticalEllipseRadius=");
        sb.append(this.d);
        sb.append(", theta=");
        sb.append(this.f22050e);
        sb.append(", isMoreThanHalf=");
        sb.append(this.f22051f);
        sb.append(", isPositiveArc=");
        sb.append(this.f22052g);
        sb.append(", arcStartDx=");
        sb.append(this.f22053h);
        sb.append(", arcStartDy=");
        return AbstractC1347d.h(sb, this.f22054i, ')');
    }
}
