package w4;

import A4.q;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import e4.l;
import g4.k;
import n4.n;
import n4.s;
import p4.C2104c;
import r4.C2290c;
import r4.C2291d;
import z4.C2767c;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Cloneable {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f23274A;
    public int l;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f23280s;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f23284w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Resources.Theme f23285x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f23286y;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public k f23275m = k.d;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public com.bumptech.glide.f f23276n = com.bumptech.glide.f.f12685n;
    public boolean o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23277p = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f23278q = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public e4.e f23279r = C2767c.f24405b;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public e4.h f23281t = new e4.h();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public A4.d f23282u = new A4.d(0);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Class f23283v = Object.class;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f23287z = true;

    public static boolean f(int i7, int i10) {
        return (i7 & i10) != 0;
    }

    public a a(a aVar) {
        if (this.f23286y) {
            return clone().a(aVar);
        }
        int i7 = aVar.l;
        if (f(aVar.l, 1048576)) {
            this.f23274A = aVar.f23274A;
        }
        if (f(aVar.l, 4)) {
            this.f23275m = aVar.f23275m;
        }
        if (f(aVar.l, 8)) {
            this.f23276n = aVar.f23276n;
        }
        if (f(aVar.l, 16)) {
            this.l &= -33;
        }
        if (f(aVar.l, 32)) {
            this.l &= -17;
        }
        if (f(aVar.l, 64)) {
            this.l &= -129;
        }
        if (f(aVar.l, 128)) {
            this.l &= -65;
        }
        if (f(aVar.l, 256)) {
            this.o = aVar.o;
        }
        if (f(aVar.l, 512)) {
            this.f23278q = aVar.f23278q;
            this.f23277p = aVar.f23277p;
        }
        if (f(aVar.l, 1024)) {
            this.f23279r = aVar.f23279r;
        }
        if (f(aVar.l, 4096)) {
            this.f23283v = aVar.f23283v;
        }
        if (f(aVar.l, 8192)) {
            this.l &= -16385;
        }
        if (f(aVar.l, 16384)) {
            this.l &= -8193;
        }
        if (f(aVar.l, 32768)) {
            this.f23285x = aVar.f23285x;
        }
        if (f(aVar.l, 131072)) {
            this.f23280s = aVar.f23280s;
        }
        if (f(aVar.l, 2048)) {
            this.f23282u.putAll(aVar.f23282u);
            this.f23287z = aVar.f23287z;
        }
        this.l |= aVar.l;
        this.f23281t.f14434b.h(aVar.f23281t.f14434b);
        k();
        return this;
    }

    @Override // 
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public a clone() {
        try {
            a aVar = (a) super.clone();
            e4.h hVar = new e4.h();
            aVar.f23281t = hVar;
            hVar.f14434b.h(this.f23281t.f14434b);
            A4.d dVar = new A4.d(0);
            aVar.f23282u = dVar;
            dVar.putAll(this.f23282u);
            aVar.f23284w = false;
            aVar.f23286y = false;
            return aVar;
        } catch (CloneNotSupportedException e10) {
            throw new RuntimeException(e10);
        }
    }

    public final a c(Class cls) {
        if (this.f23286y) {
            return clone().c(cls);
        }
        this.f23283v = cls;
        this.l |= 4096;
        k();
        return this;
    }

    public final a d(k kVar) {
        if (this.f23286y) {
            return clone().d(kVar);
        }
        this.f23275m = kVar;
        this.l |= 4;
        k();
        return this;
    }

    public final boolean e(a aVar) {
        aVar.getClass();
        return Float.compare(1.0f, 1.0f) == 0 && q.b(null, null) && q.b(null, null) && q.b(null, null) && this.o == aVar.o && this.f23277p == aVar.f23277p && this.f23278q == aVar.f23278q && this.f23280s == aVar.f23280s && this.f23275m.equals(aVar.f23275m) && this.f23276n == aVar.f23276n && this.f23281t.equals(aVar.f23281t) && this.f23282u.equals(aVar.f23282u) && this.f23283v.equals(aVar.f23283v) && this.f23279r.equals(aVar.f23279r) && q.b(this.f23285x, aVar.f23285x);
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            return e((a) obj);
        }
        return false;
    }

    public final a g(n nVar, n4.e eVar) {
        if (this.f23286y) {
            return clone().g(nVar, eVar);
        }
        l(n.f19950g, nVar);
        return p(eVar, false);
    }

    public final a h(int i7, int i10) {
        if (this.f23286y) {
            return clone().h(i7, i10);
        }
        this.f23278q = i7;
        this.f23277p = i10;
        this.l |= 512;
        k();
        return this;
    }

    public int hashCode() {
        char[] cArr = q.f432a;
        return q.h(q.h(q.h(q.h(q.h(q.h(q.h(q.g(0, q.g(0, q.g(1, q.g(this.f23280s ? 1 : 0, q.g(this.f23278q, q.g(this.f23277p, q.g(this.o ? 1 : 0, q.h(q.g(0, q.h(q.g(0, q.h(q.g(0, q.g(Float.floatToIntBits(1.0f), 17)), null)), null)), null)))))))), this.f23275m), this.f23276n), this.f23281t), this.f23282u), this.f23283v), this.f23279r), this.f23285x);
    }

    public final a i() {
        com.bumptech.glide.f fVar = com.bumptech.glide.f.o;
        if (this.f23286y) {
            return clone().i();
        }
        this.f23276n = fVar;
        this.l |= 8;
        k();
        return this;
    }

    public final a j(e4.g gVar) {
        if (this.f23286y) {
            return clone().j(gVar);
        }
        this.f23281t.f14434b.remove(gVar);
        k();
        return this;
    }

    public final void k() {
        if (this.f23284w) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
    }

    public final a l(e4.g gVar, Object obj) {
        if (this.f23286y) {
            return clone().l(gVar, obj);
        }
        A4.h.b(gVar);
        A4.h.b(obj);
        this.f23281t.f14434b.put(gVar, obj);
        k();
        return this;
    }

    public final a m(e4.e eVar) {
        if (this.f23286y) {
            return clone().m(eVar);
        }
        this.f23279r = eVar;
        this.l |= 1024;
        k();
        return this;
    }

    public final a n() {
        if (this.f23286y) {
            return clone().n();
        }
        this.o = false;
        this.l |= 256;
        k();
        return this;
    }

    public final a o(Resources.Theme theme) {
        if (this.f23286y) {
            return clone().o(theme);
        }
        this.f23285x = theme;
        if (theme != null) {
            this.l |= 32768;
            return l(C2104c.f20898b, theme);
        }
        this.l &= -32769;
        return j(C2104c.f20898b);
    }

    public final a p(l lVar, boolean z6) {
        if (this.f23286y) {
            return clone().p(lVar, z6);
        }
        s sVar = new s(lVar, z6);
        q(Bitmap.class, lVar, z6);
        q(Drawable.class, sVar, z6);
        q(BitmapDrawable.class, sVar, z6);
        q(C2290c.class, new C2291d(lVar), z6);
        k();
        return this;
    }

    public final a q(Class cls, l lVar, boolean z6) {
        if (this.f23286y) {
            return clone().q(cls, lVar, z6);
        }
        A4.h.b(lVar);
        this.f23282u.put(cls, lVar);
        int i7 = this.l;
        this.l = 67584 | i7;
        this.f23287z = false;
        if (z6) {
            this.l = i7 | 198656;
            this.f23280s = true;
        }
        k();
        return this;
    }

    public final a r() {
        if (this.f23286y) {
            return clone().r();
        }
        this.f23274A = true;
        this.l |= 1048576;
        k();
        return this;
    }
}
