package Q;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class E0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5514b;
    public final float c;

    public E0(float f10, float f11, float f12) {
        this.f5513a = f10;
        this.f5514b = f11;
        this.c = f12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof E0)) {
            return false;
        }
        E0 e02 = (E0) obj;
        return a1.e.a(this.f5513a, e02.f5513a) && a1.e.a(this.f5514b, e02.f5514b) && a1.e.a(this.c, e02.c);
    }

    public final int hashCode() {
        return Float.hashCode(this.c) + AbstractC1347d.b(Float.hashCode(this.f5513a) * 31, 31, this.f5514b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TabPosition(left=");
        float f10 = this.f5513a;
        sb.append((Object) a1.e.b(f10));
        sb.append(", right=");
        float f11 = this.f5514b;
        sb.append((Object) a1.e.b(f10 + f11));
        sb.append(", width=");
        sb.append((Object) a1.e.b(f11));
        sb.append(", contentWidth=");
        sb.append((Object) a1.e.b(this.c));
        sb.append(')');
        return sb.toString();
    }
}
