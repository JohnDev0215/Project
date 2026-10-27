package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2344k extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2344k(float f10, float f11) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2344k)) {
            return false;
        }
        C2344k c2344k = (C2344k) obj;
        return Float.compare(this.c, c2344k.c) == 0 && Float.compare(this.d, c2344k.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LineTo(x=");
        sb.append(this.c);
        sb.append(", y=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
