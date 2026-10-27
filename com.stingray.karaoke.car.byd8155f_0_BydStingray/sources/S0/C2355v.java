package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2355v extends AbstractC2359z {
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f22061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f22062f;

    public C2355v(float f10, float f11, float f12, float f13) {
        super(2, true, false);
        this.c = f10;
        this.d = f11;
        this.f22061e = f12;
        this.f22062f = f13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2355v)) {
            return false;
        }
        C2355v c2355v = (C2355v) obj;
        return Float.compare(this.c, c2355v.c) == 0 && Float.compare(this.d, c2355v.d) == 0 && Float.compare(this.f22061e, c2355v.f22061e) == 0 && Float.compare(this.f22062f, c2355v.f22062f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f22062f) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.c) * 31, 31, this.d), 31, this.f22061e);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeReflectiveCurveTo(dx1=");
        sb.append(this.c);
        sb.append(", dy1=");
        sb.append(this.d);
        sb.append(", dx2=");
        sb.append(this.f22061e);
        sb.append(", dy2=");
        return AbstractC1347d.h(sb, this.f22062f, ')');
    }
}
