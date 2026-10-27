package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2356w extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2356w(float f10, float f11) {
        super(1, false, true);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2356w)) {
            return false;
        }
        C2356w c2356w = (C2356w) obj;
        return Float.compare(this.c, c2356w.c) == 0 && Float.compare(this.d, c2356w.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeReflectiveQuadTo(dx=");
        sb.append(this.c);
        sb.append(", dy=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
