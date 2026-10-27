package s0;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: s0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2357x extends AbstractC2359z {
    public final float c;

    public C2357x(float f10) {
        super(3, false, false);
        this.c = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C2357x) && Float.compare(this.c, ((C2357x) obj).c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.c);
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("RelativeVerticalTo(dy="), this.c, ')');
    }
}
