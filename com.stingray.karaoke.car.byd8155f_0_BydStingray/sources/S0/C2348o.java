package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2348o extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2348o(float f10, float f11) {
        super(1, false, true);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2348o)) {
            return false;
        }
        C2348o c2348o = (C2348o) obj;
        return Float.compare(this.c, c2348o.c) == 0 && Float.compare(this.d, c2348o.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ReflectiveQuadTo(x=");
        sb.append(this.c);
        sb.append(", y=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
