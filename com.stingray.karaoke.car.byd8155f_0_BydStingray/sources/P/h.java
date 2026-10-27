package P;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5230b;
    public final float c;
    public final float d;

    public h(float f10, float f11, float f12, float f13) {
        this.f5229a = f10;
        this.f5230b = f11;
        this.c = f12;
        this.d = f13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f5229a == hVar.f5229a && this.f5230b == hVar.f5230b && this.c == hVar.c && this.d == hVar.d;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f5229a) * 31, 31, this.f5230b), 31, this.c);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RippleAlpha(draggedAlpha=");
        sb.append(this.f5229a);
        sb.append(", focusedAlpha=");
        sb.append(this.f5230b);
        sb.append(", hoveredAlpha=");
        sb.append(this.c);
        sb.append(", pressedAlpha=");
        return AbstractC1347d.h(sb, this.d, ')');
    }
}
