package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2350q extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22055e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22056f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f22057g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f22058h;

    public C2350q(float f10, float f11, float f12, float f13, float f14, float f15) {
        super(2, true, false);
        this.c = f10;
        this.d = f11;
        this.f22055e = f12;
        this.f22056f = f13;
        this.f22057g = f14;
        this.f22058h = f15;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2350q)) {
            return false;
        }
        C2350q c2350q = (C2350q) obj;
        return Float.compare(this.c, c2350q.c) == 0 && Float.compare(this.d, c2350q.d) == 0 && Float.compare(this.f22055e, c2350q.f22055e) == 0 && Float.compare(this.f22056f, c2350q.f22056f) == 0 && Float.compare(this.f22057g, c2350q.f22057g) == 0 && Float.compare(this.f22058h, c2350q.f22058h) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22058h) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22055e), 31, this.f22056f), 31, this.f22057g);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeCurveTo(dx1=");
        sb.append(this.c);
        sb.append(", dy1=");
        sb.append(this.d);
        sb.append(", dx2=");
        sb.append(this.f22055e);
        sb.append(", dy2=");
        sb.append(this.f22056f);
        sb.append(", dx3=");
        sb.append(this.f22057g);
        sb.append(", dy3=");
        return AbstractC1347d.h(sb, this.f22058h, ')');
    }
}
