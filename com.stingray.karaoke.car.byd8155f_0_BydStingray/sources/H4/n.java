package H4;

import android.graphics.drawable.Drawable;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable f2814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f2815b;
    public final q c;
    public final r d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final F4.a f2816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f2817f;

    public n(Drawable drawable, float f10, p pVar, P8.n nVar, int i7) {
        drawable = (i7 & 1) != 0 ? null : drawable;
        f10 = (i7 & 2) != 0 ? 0.2f : f10;
        q qVar = (i7 & 4) != 0 ? o.f2818a : pVar;
        r rVar = (i7 & 8) != 0 ? c.d : nVar;
        F4.a aVar = F4.a.f2409a;
        h hVar = h.f2806a;
        A7.m.f(qVar, "padding");
        A7.m.f(rVar, "shape");
        this.f2814a = drawable;
        this.f2815b = f10;
        this.c = qVar;
        this.d = rVar;
        this.f2816e = aVar;
        this.f2817f = hVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return A7.m.a(this.f2814a, nVar.f2814a) && Float.valueOf(this.f2815b).equals(Float.valueOf(nVar.f2815b)) && A7.m.a(this.c, nVar.c) && A7.m.a(this.d, nVar.d) && A7.m.a(this.f2816e, nVar.f2816e) && A7.m.a(this.f2817f, nVar.f2817f);
    }

    public final int hashCode() {
        Drawable drawable = this.f2814a;
        return this.f2817f.hashCode() + ((this.f2816e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + AbstractC1347d.b((drawable == null ? 0 : drawable.hashCode()) * 31, 31, this.f2815b)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "QrVectorLogo(drawable=" + this.f2814a + ", size=" + this.f2815b + ", padding=" + this.c + ", shape=" + this.d + ", scale=" + this.f2816e + ", backgroundColor=" + this.f2817f + ')';
    }
}
