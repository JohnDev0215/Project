package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2340g extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22037e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f22038f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f22039g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22040h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f22041i;

    public C2340g(float f10, float f11, float f12, boolean z6, boolean z10, float f13, float f14) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
        this.f22037e = f12;
        this.f22038f = z6;
        this.f22039g = z10;
        this.f22040h = f13;
        this.f22041i = f14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2340g)) {
            return false;
        }
        C2340g c2340g = (C2340g) obj;
        return Float.compare(this.c, c2340g.c) == 0 && Float.compare(this.d, c2340g.d) == 0 && Float.compare(this.f22037e, c2340g.f22037e) == 0 && this.f22038f == c2340g.f22038f && this.f22039g == c2340g.f22039g && Float.compare(this.f22040h, c2340g.f22040h) == 0 && Float.compare(this.f22041i, c2340g.f22041i) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22041i) + AbstractC1347d.b(AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22037e), 31, this.f22038f), 31, this.f22039g), 31, this.f22040h);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ArcTo(horizontalEllipseRadius=");
        sb.append(this.c);
        sb.append(", verticalEllipseRadius=");
        sb.append(this.d);
        sb.append(", theta=");
        sb.append(this.f22037e);
        sb.append(", isMoreThanHalf=");
        sb.append(this.f22038f);
        sb.append(", isPositiveArc=");
        sb.append(this.f22039g);
        sb.append(", arcStartX=");
        sb.append(this.f22040h);
        sb.append(", arcStartY=");
        return AbstractC1347d.h(sb, this.f22041i, ')');
    }
}
