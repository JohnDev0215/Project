package V5;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f9508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f9509b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f9510e;

    public h(float f10, float f11, float f12, float f13, float f14) {
        this.f9508a = f10;
        this.f9509b = f11;
        this.c = f12;
        this.d = f13;
        this.f9510e = f14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return a1.e.a(this.f9508a, hVar.f9508a) && a1.e.a(this.f9509b, hVar.f9509b) && a1.e.a(this.c, hVar.c) && a1.e.a(this.d, hVar.d) && a1.e.a(this.f9510e, hVar.f9510e);
    }

    public final int hashCode() {
        return Float.hashCode(this.f9510e) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f9508a) * 31, 31, this.f9509b), 31, this.c), 31, this.d);
    }

    public final String toString() {
        String strB = a1.e.b(this.f9508a);
        String strB2 = a1.e.b(this.f9509b);
        String strB3 = a1.e.b(this.c);
        String strB4 = a1.e.b(this.d);
        String strB5 = a1.e.b(this.f9510e);
        StringBuilder sb = new StringBuilder("SwipeRefreshIndicatorSizes(size=");
        sb.append(strB);
        sb.append(", arcRadius=");
        sb.append(strB2);
        sb.append(", strokeWidth=");
        AbstractC1347d.r(sb, strB3, ", arrowWidth=", strB4, ", arrowHeight=");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, strB5, ")");
    }
}
