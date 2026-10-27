package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2345l extends AbstractC2359z {
    public final float c;
    public final float d;

    public C2345l(float f10, float f11) {
        super(3, false, false);
        this.c = f10;
        this.d = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2345l)) {
            return false;
        }
        C2345l c2345l = (C2345l) obj;
        return Float.compare(this.c, c2345l.c) == 0 && Float.compare(this.d, c2345l.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + (Float.hashCode(this.c) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MoveTo(x=");
        sb.append(this.c);
        sb.append(", y=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
