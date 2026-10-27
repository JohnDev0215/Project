package p;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import coil.request.CachePolicy;
import coil.size.Precision;

/* JADX INFO: loaded from: classes.dex */
public final class p70 {
    public final kotlinx.coroutines.b a;
    public final kotlinx.coroutines.b b;
    public final kotlinx.coroutines.b c;
    public final kotlinx.coroutines.b d;
    public final un2 e;
    public final Precision f;
    public final Bitmap.Config g;
    public final boolean h;
    public final boolean i;
    public final Drawable j;
    public final Drawable k;
    public final Drawable l;
    public final CachePolicy m;
    public final CachePolicy n;
    public final CachePolicy o;

    public p70() {
        s70 s70Var = ha0.a;
        kotlinx.coroutines.android.a aVar = ((kotlinx.coroutines.android.a) de1.a).n;
        f70 f70Var = ha0.c;
        tn1 tn1Var = un2.a;
        Precision precision = Precision.e;
        Bitmap.Config config = i.b;
        CachePolicy cachePolicy = CachePolicy.c;
        this.a = aVar;
        this.b = f70Var;
        this.c = f70Var;
        this.d = f70Var;
        this.e = tn1Var;
        this.f = precision;
        this.g = config;
        this.h = true;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = cachePolicy;
        this.n = cachePolicy;
        this.o = cachePolicy;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p70) {
            p70 p70Var = (p70) obj;
            if (v03.b(this.a, p70Var.a) && v03.b(this.b, p70Var.b) && v03.b(this.c, p70Var.c) && v03.b(this.d, p70Var.d) && v03.b(this.e, p70Var.e) && this.f == p70Var.f && this.g == p70Var.g && this.h == p70Var.h && this.i == p70Var.i && v03.b(this.j, p70Var.j) && v03.b(this.k, p70Var.k) && v03.b(this.l, p70Var.l) && this.m == p70Var.m && this.n == p70Var.n && this.o == p70Var.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31;
        ((tn1) this.e).getClass();
        int iHashCode2 = (Boolean.hashCode(this.i) + ((Boolean.hashCode(this.h) + ((this.g.hashCode() + ((this.f.hashCode() + ((tn1.class.hashCode() + iHashCode) * 31)) * 31)) * 31)) * 31)) * 31;
        Drawable drawable = this.j;
        int iHashCode3 = (iHashCode2 + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Drawable drawable2 = this.k;
        int iHashCode4 = (iHashCode3 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Drawable drawable3 = this.l;
        return this.o.hashCode() + ((this.n.hashCode() + ((this.m.hashCode() + ((iHashCode4 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31)) * 31)) * 31);
    }
}
