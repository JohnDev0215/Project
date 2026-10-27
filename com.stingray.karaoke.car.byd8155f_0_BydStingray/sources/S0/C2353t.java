package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2353t extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2353t(float f10, float f11) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2353t)) {
            return false;
        }
        C2353t c2353t = (C2353t) obj;
        return Float.compare(this.c, c2353t.c) == 0 && Float.compare(this.d, c2353t.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeMoveTo(dx=");
        sb.append(this.c);
        sb.append(", dy=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
