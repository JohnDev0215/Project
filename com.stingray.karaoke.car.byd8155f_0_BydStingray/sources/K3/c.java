package K3;

import S8.AbstractC0721s;
import S8.E;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0721s f3513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0721s f3514b;
    public final AbstractC0721s c;
    public final AbstractC0721s d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final M3.e f3515e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final L3.d f3516f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bitmap.Config f3517g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3518h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f3519i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Drawable f3520j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Drawable f3521k;
    public final Drawable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f3522m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f3523n;
    public final b o;

    public c() {
        Z8.e eVar = E.f8084a;
        T8.d dVar = X8.m.f10573a.f8932q;
        Z8.d dVar2 = Z8.d.f11010n;
        M3.c cVar = M3.e.f4195a;
        L3.d dVar3 = L3.d.f4002n;
        Bitmap.Config config = N3.d.f4575a;
        b bVar = b.f3510n;
        this.f3513a = dVar;
        this.f3514b = dVar2;
        this.c = dVar2;
        this.d = dVar2;
        this.f3515e = cVar;
        this.f3516f = dVar3;
        this.f3517g = config;
        this.f3518h = true;
        this.f3519i = false;
        this.f3520j = null;
        this.f3521k = null;
        this.l = null;
        this.f3522m = bVar;
        this.f3523n = bVar;
        this.o = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            if (A7.m.a(this.f3513a, cVar.f3513a) && A7.m.a(this.f3514b, cVar.f3514b) && A7.m.a(this.c, cVar.c) && A7.m.a(this.d, cVar.d) && A7.m.a(this.f3515e, cVar.f3515e) && this.f3516f == cVar.f3516f && this.f3517g == cVar.f3517g && this.f3518h == cVar.f3518h && this.f3519i == cVar.f3519i && A7.m.a(this.f3520j, cVar.f3520j) && A7.m.a(this.f3521k, cVar.f3521k) && A7.m.a(this.l, cVar.l) && this.f3522m == cVar.f3522m && this.f3523n == cVar.f3523n && this.o == cVar.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d(AbstractC1347d.d((this.f3517g.hashCode() + ((this.f3516f.hashCode() + ((this.f3515e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.f3514b.hashCode() + (this.f3513a.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31, 31, this.f3518h), 31, this.f3519i);
        Drawable drawable = this.f3520j;
        int iHashCode = (iD + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Drawable drawable2 = this.f3521k;
        int iHashCode2 = (iHashCode + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Drawable drawable3 = this.l;
        return this.o.hashCode() + ((this.f3523n.hashCode() + ((this.f3522m.hashCode() + ((iHashCode2 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31)) * 31)) * 31);
    }
}
