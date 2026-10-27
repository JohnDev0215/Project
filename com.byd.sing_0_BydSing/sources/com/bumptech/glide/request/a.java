package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.m.j;
import com.bumptech.glide.m.k;
import com.bumptech.glide.request.a;
import java.util.Map;

/* JADX INFO: compiled from: BaseRequestOptions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends a<T>> implements Cloneable {
    private boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3220b;
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
    private float f3221c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h f3222d = h.e;
    private Priority e = Priority.NORMAL;
    private boolean j = true;
    private int k = -1;
    private int l = -1;
    private com.bumptech.glide.load.c m = com.bumptech.glide.l.a.c();
    private boolean o = true;
    private com.bumptech.glide.load.e r = new com.bumptech.glide.load.e();
    private Map<Class<?>, com.bumptech.glide.load.h<?>> s = new com.bumptech.glide.m.b();
    private Class<?> t = Object.class;
    private boolean z = true;

    private boolean B(int i) {
        return C(this.f3220b, i);
    }

    private static boolean C(int i, int i2) {
        return (i & i2) != 0;
    }

    private T I() {
        return this;
    }

    private T J() {
        if (this.u) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return (T) I();
    }

    boolean A() {
        return this.z;
    }

    public final boolean D() {
        return this.n;
    }

    public final boolean E() {
        return k.r(this.l, this.k);
    }

    public T F() {
        this.u = true;
        return (T) I();
    }

    public T G(int i, int i2) {
        if (this.w) {
            return (T) clone().G(i, i2);
        }
        this.l = i;
        this.k = i2;
        this.f3220b |= 512;
        return (T) J();
    }

    public T H(Priority priority) {
        if (this.w) {
            return (T) clone().H(priority);
        }
        this.e = (Priority) j.d(priority);
        this.f3220b |= 8;
        return (T) J();
    }

    public T K(com.bumptech.glide.load.c cVar) {
        if (this.w) {
            return (T) clone().K(cVar);
        }
        this.m = (com.bumptech.glide.load.c) j.d(cVar);
        this.f3220b |= 1024;
        return (T) J();
    }

    public T L(float f) {
        if (this.w) {
            return (T) clone().L(f);
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.f3221c = f;
        this.f3220b |= 2;
        return (T) J();
    }

    public T M(boolean z) {
        if (this.w) {
            return (T) clone().M(true);
        }
        this.j = !z;
        this.f3220b |= 256;
        return (T) J();
    }

    public T N(com.bumptech.glide.load.h<Bitmap> hVar) {
        return (T) O(hVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    T O(com.bumptech.glide.load.h<Bitmap> hVar, boolean z) {
        if (this.w) {
            return (T) clone().O(hVar, z);
        }
        com.bumptech.glide.load.resource.bitmap.j jVar = new com.bumptech.glide.load.resource.bitmap.j(hVar, z);
        P(Bitmap.class, hVar, z);
        P(Drawable.class, jVar, z);
        P(BitmapDrawable.class, jVar.c(), z);
        P(com.bumptech.glide.load.k.g.c.class, new com.bumptech.glide.load.k.g.f(hVar), z);
        return (T) J();
    }

    <Y> T P(Class<Y> cls, com.bumptech.glide.load.h<Y> hVar, boolean z) {
        if (this.w) {
            return (T) clone().P(cls, hVar, z);
        }
        j.d(cls);
        j.d(hVar);
        this.s.put(cls, hVar);
        int i = this.f3220b | 2048;
        this.f3220b = i;
        this.o = true;
        int i2 = i | 65536;
        this.f3220b = i2;
        this.z = false;
        if (z) {
            this.f3220b = i2 | 131072;
            this.n = true;
        }
        return (T) J();
    }

    public T Q(boolean z) {
        if (this.w) {
            return (T) clone().Q(z);
        }
        this.A = z;
        this.f3220b |= 1048576;
        return (T) J();
    }

    public T a(a<?> aVar) {
        if (this.w) {
            return (T) clone().a(aVar);
        }
        if (C(aVar.f3220b, 2)) {
            this.f3221c = aVar.f3221c;
        }
        if (C(aVar.f3220b, 262144)) {
            this.x = aVar.x;
        }
        if (C(aVar.f3220b, 1048576)) {
            this.A = aVar.A;
        }
        if (C(aVar.f3220b, 4)) {
            this.f3222d = aVar.f3222d;
        }
        if (C(aVar.f3220b, 8)) {
            this.e = aVar.e;
        }
        if (C(aVar.f3220b, 16)) {
            this.f = aVar.f;
            this.g = 0;
            this.f3220b &= -33;
        }
        if (C(aVar.f3220b, 32)) {
            this.g = aVar.g;
            this.f = null;
            this.f3220b &= -17;
        }
        if (C(aVar.f3220b, 64)) {
            this.h = aVar.h;
            this.i = 0;
            this.f3220b &= -129;
        }
        if (C(aVar.f3220b, 128)) {
            this.i = aVar.i;
            this.h = null;
            this.f3220b &= -65;
        }
        if (C(aVar.f3220b, 256)) {
            this.j = aVar.j;
        }
        if (C(aVar.f3220b, 512)) {
            this.l = aVar.l;
            this.k = aVar.k;
        }
        if (C(aVar.f3220b, 1024)) {
            this.m = aVar.m;
        }
        if (C(aVar.f3220b, 4096)) {
            this.t = aVar.t;
        }
        if (C(aVar.f3220b, IdentityHashMap.DEFAULT_SIZE)) {
            this.p = aVar.p;
            this.q = 0;
            this.f3220b &= -16385;
        }
        if (C(aVar.f3220b, 16384)) {
            this.q = aVar.q;
            this.p = null;
            this.f3220b &= -8193;
        }
        if (C(aVar.f3220b, 32768)) {
            this.v = aVar.v;
        }
        if (C(aVar.f3220b, 65536)) {
            this.o = aVar.o;
        }
        if (C(aVar.f3220b, 131072)) {
            this.n = aVar.n;
        }
        if (C(aVar.f3220b, 2048)) {
            this.s.putAll(aVar.s);
            this.z = aVar.z;
        }
        if (C(aVar.f3220b, 524288)) {
            this.y = aVar.y;
        }
        if (!this.o) {
            this.s.clear();
            int i = this.f3220b & (-2049);
            this.f3220b = i;
            this.n = false;
            this.f3220b = i & (-131073);
            this.z = true;
        }
        this.f3220b |= aVar.f3220b;
        this.r.d(aVar.r);
        return (T) J();
    }

    public T b() {
        if (this.u && !this.w) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.w = true;
        return (T) F();
    }

    @Override // 
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public T clone() {
        try {
            T t = (T) super.clone();
            com.bumptech.glide.load.e eVar = new com.bumptech.glide.load.e();
            t.r = eVar;
            eVar.d(this.r);
            com.bumptech.glide.m.b bVar = new com.bumptech.glide.m.b();
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
        this.t = (Class) j.d(cls);
        this.f3220b |= 4096;
        return (T) J();
    }

    public T e(h hVar) {
        if (this.w) {
            return (T) clone().e(hVar);
        }
        this.f3222d = (h) j.d(hVar);
        this.f3220b |= 4;
        return (T) J();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Float.compare(aVar.f3221c, this.f3221c) == 0 && this.g == aVar.g && k.c(this.f, aVar.f) && this.i == aVar.i && k.c(this.h, aVar.h) && this.q == aVar.q && k.c(this.p, aVar.p) && this.j == aVar.j && this.k == aVar.k && this.l == aVar.l && this.n == aVar.n && this.o == aVar.o && this.x == aVar.x && this.y == aVar.y && this.f3222d.equals(aVar.f3222d) && this.e == aVar.e && this.r.equals(aVar.r) && this.s.equals(aVar.s) && this.t.equals(aVar.t) && k.c(this.m, aVar.m) && k.c(this.v, aVar.v);
    }

    public final h f() {
        return this.f3222d;
    }

    public final int g() {
        return this.g;
    }

    public final Drawable h() {
        return this.f;
    }

    public int hashCode() {
        return k.m(this.v, k.m(this.m, k.m(this.t, k.m(this.s, k.m(this.r, k.m(this.e, k.m(this.f3222d, k.n(this.y, k.n(this.x, k.n(this.o, k.n(this.n, k.l(this.l, k.l(this.k, k.n(this.j, k.m(this.p, k.l(this.q, k.m(this.h, k.l(this.i, k.m(this.f, k.l(this.g, k.j(this.f3221c)))))))))))))))))))));
    }

    public final Drawable i() {
        return this.p;
    }

    public final int j() {
        return this.q;
    }

    public final boolean k() {
        return this.y;
    }

    public final com.bumptech.glide.load.e l() {
        return this.r;
    }

    public final int m() {
        return this.k;
    }

    public final int n() {
        return this.l;
    }

    public final Drawable o() {
        return this.h;
    }

    public final int p() {
        return this.i;
    }

    public final Priority q() {
        return this.e;
    }

    public final Class<?> r() {
        return this.t;
    }

    public final com.bumptech.glide.load.c s() {
        return this.m;
    }

    public final float t() {
        return this.f3221c;
    }

    public final Resources.Theme u() {
        return this.v;
    }

    public final Map<Class<?>, com.bumptech.glide.load.h<?>> v() {
        return this.s;
    }

    public final boolean w() {
        return this.A;
    }

    public final boolean x() {
        return this.x;
    }

    public final boolean y() {
        return this.j;
    }

    public final boolean z() {
        return B(8);
    }
}
