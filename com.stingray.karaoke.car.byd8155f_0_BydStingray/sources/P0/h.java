package p0;

import A7.m;
import h2.AbstractC1347d;
import n0.AbstractC1861K;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class h extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f20642a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f20643b;
    public final int c;
    public final int d;

    public h(float f10, float f11, int i7, int i10, int i11) {
        f10 = (i11 & 1) != 0 ? 0.0f : f10;
        f11 = (i11 & 2) != 0 ? 4.0f : f11;
        i7 = (i11 & 4) != 0 ? 0 : i7;
        i10 = (i11 & 8) != 0 ? 0 : i10;
        this.f20642a = f10;
        this.f20643b = f11;
        this.c = i7;
        this.d = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f20642a == hVar.f20642a && this.f20643b == hVar.f20643b && AbstractC1861K.s(this.c, hVar.c) && AbstractC1861K.t(this.d, hVar.d) && m.a(null, null);
    }

    public final int hashCode() {
        return AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC1347d.b(Float.hashCode(this.f20642a) * 31, 31, this.f20643b), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Stroke(width=");
        sb.append(this.f20642a);
        sb.append(", miter=");
        sb.append(this.f20643b);
        sb.append(", cap=");
        int i7 = this.c;
        String str = "Unknown";
        sb.append((Object) (AbstractC1861K.s(i7, 0) ? "Butt" : AbstractC1861K.s(i7, 1) ? "Round" : AbstractC1861K.s(i7, 2) ? "Square" : "Unknown"));
        sb.append(", join=");
        int i10 = this.d;
        if (AbstractC1861K.t(i10, 0)) {
            str = "Miter";
        } else if (AbstractC1861K.t(i10, 1)) {
            str = "Round";
        } else if (AbstractC1861K.t(i10, 2)) {
            str = "Bevel";
        }
        sb.append((Object) str);
        sb.append(", pathEffect=null)");
        return sb.toString();
    }
}
