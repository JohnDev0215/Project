package com.bumptech.glide.r;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.m;
import com.bumptech.glide.load.n.j;
import com.bumptech.glide.load.p.d.l;
import com.bumptech.glide.r.a;
import com.bumptech.glide.t.k;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends a<T>> implements Cloneable {
    private boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2495b;
    private Drawable f;
    private int g;
    private Drawable h;
    private int i;
    private boolean n;
    private Drawable p;
    private int q;
    private boolean u;
    private Resources.Theme v;
    private boolean w;
    private boolean x;
    private boolean y;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2496c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private j f2497d = j.e;
    private com.bumptech.glide.g e = com.bumptech.glide.g.NORMAL;
    private boolean j = true;
    private int k = -1;
    private int l = -1;
    private com.bumptech.glide.load.g m = com.bumptech.glide.s.a.c();
    private boolean o = true;
    private com.bumptech.glide.load.i r = new com.bumptech.glide.load.i();
    private Map<Class<?>, m<?>> s = new com.bumptech.glide.t.b();
    private Class<?> t = Object.class;
    private boolean z = true;

    private boolean D(int i) {
        return E(this.f2495b, i);
    }

    private static boolean E(int i, int i2) {
        return (i & i2) != 0;
    }

    private T L() {
        return this;
    }

    private T M() {
        if (this.u) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return (T) L();
    }

    public final boolean A() {
        return this.j;
    }

    public final boolean B() {
        return D(8);
    }

    boolean C() {
        return this.z;
    }

    public final boolean F() {
        return this.n;
    }

    public final boolean G() {
        return k.r(this.l, this.k);
    }

    public T H() {
        this.u = true;
        return (T) L();
    }

    public T I(int i, int i2) {
        if (this.w) {
            return (T) clone().I(i, i2);
        }
        this.l = i;
        this.k = i2;
        this.f2495b |= 512;
        return (T) M();
    }

    public T J(int i) {
        if (this.w) {
            return (T) clone().J(i);
        }
        this.i = i;
        int i2 = this.f2495b | 128;
        this.f2495b = i2;
        this.h = null;
        this.f2495b = i2 & (-65);
        return (T) M();
    }

    public T K(com.bumptech.glide.g gVar) {
        if (this.w) {
            return (T) clone().K(gVar);
        }
        this.e = (com.bumptech.glide.g) com.bumptech.glide.t.j.d(gVar);
        this.f2495b |= 8;
        return (T) M();
    }

    public <Y> T N(com.bumptech.glide.load.h<Y> hVar, Y y) {
        if (this.w) {
            return (T) clone().N(hVar, y);
        }
        com.bumptech.glide.t.j.d(hVar);
        com.bumptech.glide.t.j.d(y);
        this.r.e(hVar, y);
        return (T) M();
    }

    public T O(com.bumptech.glide.load.g gVar) {
        if (this.w) {
            return (T) clone().O(gVar);
        }
        this.m = (com.bumptech.glide.load.g) com.bumptech.glide.t.j.d(gVar);
        this.f2495b |= 1024;
        return (T) M();
    }

    public T P(float f) {
        if (this.w) {
            return (T) clone().P(f);
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.f2496c = f;
        this.f2495b |= 2;
        return (T) M();
    }

    public T Q(boolean z) {
        if (this.w) {
            return (T) clone().Q(true);
        }
        this.j = !z;
        this.f2495b |= 256;
        return (T) M();
    }

    public T R(m<Bitmap> mVar) {
        return (T) S(mVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    T S(m<Bitmap> mVar, boolean z) {
        if (this.w) {
            return (T) clone().S(mVar, z);
        }
        l lVar = new l(mVar, z);
        T(Bitmap.class, mVar, z);
        T(Drawable.class, lVar, z);
        T(BitmapDrawable.class, lVar.c(), z);
        T(com.bumptech.glide.load.p.h.c.class, new com.bumptech.glide.load.p.h.f(mVar), z);
        return (T) M();
    }

    <Y> T T(Class<Y> cls, m<Y> mVar, boolean z) {
        if (this.w) {
            return (T) clone().T(cls, mVar, z);
        }
        com.bumptech.glide.t.j.d(cls);
        com.bumptech.glide.t.j.d(mVar);
        this.s.put(cls, mVar);
        int i = this.f2495b | 2048;
        this.f2495b = i;
        this.o = true;
        int i2 = i | 65536;
        this.f2495b = i2;
        this.z = false;
        if (z) {
            this.f2495b = i2 | 131072;
            this.n = true;
        }
        return (T) M();
    }

    public T U(boolean z) {
        if (this.w) {
            return (T) clone().U(z);
        }
        this.A = z;
        this.f2495b |= 1048576;
        return (T) M();
    }

    public T a(a<?> aVar) {
        if (this.w) {
            return (T) clone().a(aVar);
        }
        if (E(aVar.f2495b, 2)) {
            this.f2496c = aVar.f2496c;
        }
        if (E(aVar.f2495b, 262144)) {
            this.x = aVar.x;
        }
        if (E(aVar.f2495b, 1048576)) {
            this.A = aVar.A;
        }
        if (E(aVar.f2495b, 4)) {
            this.f2497d = aVar.f2497d;
        }
        if (E(aVar.f2495b, 8)) {
            this.e = aVar.e;
        }
        if (E(aVar.f2495b, 16)) {
            this.f = aVar.f;
            this.g = 0;
            this.f2495b &= -33;
        }
        if (E(aVar.f2495b, 32)) {
            this.g = aVar.g;
            this.f = null;
            this.f2495b &= -17;
        }
        if (E(aVar.f2495b, 64)) {
            this.h = aVar.h;
            this.i = 0;
            this.f2495b &= -129;
        }
        if (E(aVar.f2495b, 128)) {
            this.i = aVar.i;
            this.h = null;
            this.f2495b &= -65;
        }
        if (E(aVar.f2495b, 256)) {
            this.j = aVar.j;
        }
        if (E(aVar.f2495b, 512)) {
            this.l = aVar.l;
            this.k = aVar.k;
        }
        if (E(aVar.f2495b, 1024)) {
            this.m = aVar.m;
        }
        if (E(aVar.f2495b, 4096)) {
            this.t = aVar.t;
        }
        if (E(aVar.f2495b, 8192)) {
            this.p = aVar.p;
            this.q = 0;
            this.f2495b &= -16385;
        }
        if (E(aVar.f2495b, 16384)) {
            this.q = aVar.q;
            this.p = null;
            this.f2495b &= -8193;
        }
        if (E(aVar.f2495b, 32768)) {
            this.v = aVar.v;
        }
        if (E(aVar.f2495b, 65536)) {
            this.o = aVar.o;
        }
        if (E(aVar.f2495b, 131072)) {
            this.n = aVar.n;
        }
        if (E(aVar.f2495b, 2048)) {
            this.s.putAll(aVar.s);
            this.z = aVar.z;
        }
        if (E(aVar.f2495b, 524288)) {
            this.y = aVar.y;
        }
        if (!this.o) {
            this.s.clear();
            int i = this.f2495b & (-2049);
            this.f2495b = i;
            this.n = false;
            this.f2495b = i & (-131073);
            this.z = true;
        }
        this.f2495b |= aVar.f2495b;
        this.r.d(aVar.r);
        return (T) M();
    }

    public T b() {
        if (this.u && !this.w) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.w = true;
        return (T) H();
    }

    @Override // 
    /* JADX INFO: renamed from: c */
    public T clone() {
        try {
            T t = (T) super.clone();
            com.bumptech.glide.load.i iVar = new com.bumptech.glide.load.i();
            t.r = iVar;
            iVar.d(this.r);
            com.bumptech.glide.t.b bVar = new com.bumptech.glide.t.b();
            t.s = bVar;
            bVar.putAll(this.s);
            t.u = false;
            t.w = false;
            return t;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public T d(Class<?> cls) {
        if (this.w) {
            return (T) clone().d(cls);
        }
        this.t = (Class) com.bumptech.glide.t.j.d(cls);
        this.f2495b |= 4096;
        return (T) M();
    }

    public T e(j jVar) {
        if (this.w) {
            return (T) clone().e(jVar);
        }
        this.f2497d = (j) com.bumptech.glide.t.j.d(jVar);
        this.f2495b |= 4;
        return (T) M();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Float.compare(aVar.f2496c, this.f2496c) == 0 && this.g == aVar.g && k.c(this.f, aVar.f) && this.i == aVar.i && k.c(this.h, aVar.h) && this.q == aVar.q && k.c(this.p, aVar.p) && this.j == aVar.j && this.k == aVar.k && this.l == aVar.l && this.n == aVar.n && this.o == aVar.o && this.x == aVar.x && this.y == aVar.y && this.f2497d.equals(aVar.f2497d) && this.e == aVar.e && this.r.equals(aVar.r) && this.s.equals(aVar.s) && this.t.equals(aVar.t) && k.c(this.m, aVar.m) && k.c(this.v, aVar.v);
    }

    public T f(int i) {
        if (this.w) {
            return (T) clone().f(i);
        }
        this.g = i;
        int i2 = this.f2495b | 32;
        this.f2495b = i2;
        this.f = null;
        this.f2495b = i2 & (-17);
        return (T) M();
    }

    public T g(com.bumptech.glide.load.b bVar) {
        com.bumptech.glide.t.j.d(bVar);
        return (T) N(com.bumptech.glide.load.p.d.j.f2338a, bVar).N(com.bumptech.glide.load.p.h.i.f2404a, bVar);
    }

    public final j h() {
        return this.f2497d;
    }

    public int hashCode() {
        return k.m(this.v, k.m(this.m, k.m(this.t, k.m(this.s, k.m(this.r, k.m(this.e, k.m(this.f2497d, k.n(this.y, k.n(this.x, k.n(this.o, k.n(this.n, k.l(this.l, k.l(this.k, k.n(this.j, k.m(this.p, k.l(this.q, k.m(this.h, k.l(this.i, k.m(this.f, k.l(this.g, k.j(this.f2496c)))))))))))))))))))));
    }

    public final int i() {
        return this.g;
    }

    public final Drawable j() {
        return this.f;
    }

    public final Drawable k() {
        return this.p;
    }

    public final int l() {
        return this.q;
    }

    public final boolean m() {
        return this.y;
    }

    public final com.bumptech.glide.load.i n() {
        return this.r;
    }

    public final int o() {
        return this.k;
    }

    public final int p() {
        return this.l;
    }

    public final Drawable q() {
        return this.h;
    }

    public final int r() {
        return this.i;
    }

    public final com.bumptech.glide.g s() {
        return this.e;
    }

    public final Class<?> t() {
        return this.t;
    }

    public final com.bumptech.glide.load.g u() {
        return this.m;
    }

    public final float v() {
        return this.f2496c;
    }

    public final Resources.Theme w() {
        return this.v;
    }

    public final Map<Class<?>, m<?>> x() {
        return this.s;
    }

    public final boolean y() {
        return this.A;
    }

    public final boolean z() {
        return this.x;
    }
}
