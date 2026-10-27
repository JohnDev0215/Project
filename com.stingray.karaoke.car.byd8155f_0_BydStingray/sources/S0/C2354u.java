package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2354u extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22059e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22060f;

    public C2354u(float f10, float f11, float f12, float f13) {
        super(1, false, true);
        this.c = f10;
        this.d = f11;
        this.f22059e = f12;
        this.f22060f = f13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2354u)) {
            return false;
        }
        C2354u c2354u = (C2354u) obj;
        return Float.compare(this.c, c2354u.c) == 0 && Float.compare(this.d, c2354u.d) == 0 && Float.compare(this.f22059e, c2354u.f22059e) == 0 && Float.compare(this.f22060f, c2354u.f22060f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22060f) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22059e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeQuadTo(dx1=");
        sb.append(this.c);
        sb.append(", dy1=");
        sb.append(this.d);
        sb.append(", dx2=");
        sb.append(this.f22059e);
        sb.append(", dy2=");
        return AbstractC1347d.h(sb, this.f22060f, ')');
    }
}
