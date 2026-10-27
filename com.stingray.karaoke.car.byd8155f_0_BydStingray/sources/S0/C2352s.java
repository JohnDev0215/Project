package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2352s extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2352s(float f10, float f11) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2352s)) {
            return false;
        }
        C2352s c2352s = (C2352s) obj;
        return Float.compare(this.c, c2352s.c) == 0 && Float.compare(this.d, c2352s.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeLineTo(dx=");
        sb.append(this.c);
        sb.append(", dy=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
