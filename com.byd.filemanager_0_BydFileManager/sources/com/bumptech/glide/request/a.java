package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.i;
import com.bumptech.glide.load.resource.bitmap.l;
import com.bumptech.glide.o.j;
import com.bumptech.glide.o.k;
import com.bumptech.glide.request.a;
import java.util.Map;

/* JADX INFO: compiled from: BaseRequestOptions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends a<T>> implements Cloneable {
    private boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3483b;
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
    private float f3484c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h f3485d = h.f3025e;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Priority f3486e = Priority.NORMAL;
    private boolean j = true;
    private int k = -1;
    private int l = -1;
    private com.bumptech.glide.load.c m = com.bumptech.glide.n.a.c();
    private boolean o = true;
    private com.bumptech.glide.load.e r = new com.bumptech.glide.load.e();
    private Map<Class<?>, com.bumptech.glide.load.h<?>> s = new com.bumptech.glide.o.b();
    private Class<?> t = Object.class;
    private boolean z = true;

    private boolean H(int i) {
        return I(this.f3483b, i);
    }

    private static boolean I(int i, int i2) {
        return (i & i2) != 0;
    }

    private T P() {
        return this;
    }

    private T Q() {
        if (this.u) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return (T) P();
    }

    public final Resources.Theme A() {
        return this.v;
    }

    public final Map<Class<?>, com.bumptech.glide.load.h<?>> B() {
        return this.s;
    }

    public final boolean C() {
        return this.A;
    }

    public final boolean D() {
        return this.x;
    }

    public final boolean E() {
        return this.j;
    }

    public final boolean F() {
        return H(8);
    }

    boolean G() {
        return this.z;
    }

    public final boolean J() {
        return this.n;
    }

    public final boolean K() {
        return k.r(this.l, this.k);
    }

    public T L() {
        this.u = true;
        return (T) P();
    }

    public T M(int i, int i2) {
        if (this.w) {
            return (T) clone().M(i, i2);
        }
        this.l = i;
        this.k = i2;
        this.f3483b |= 512;
        return (T) Q();
    }

    public T N(int i) {
        if (this.w) {
            return (T) clone().N(i);
        }
        this.i = i;
        int i2 = this.f3483b | 128;
        this.f3483b = i2;
        this.h = null;
        this.f3483b = i2 & (-65);
        return (T) Q();
    }

    public T O(Priority priority) {
        if (this.w) {
            return (T) clone().O(priority);
        }
        this.f3486e = (Priority) j.d(priority);
        this.f3483b |= 8;
        return (T) Q();
    }

    public <Y> T R(com.bumptech.glide.load.d<Y> dVar, Y y) {
        if (this.w) {
            return (T) clone().R(dVar, y);
        }
        j.d(dVar);
        j.d(y);
        this.r.e(dVar, y);
        return (T) Q();
    }

    public T S(com.bumptech.glide.load.c cVar) {
        if (this.w) {
            return (T) clone().S(cVar);
        }
        this.m = (com.bumptech.glide.load.c) j.d(cVar);
        this.f3483b |= 1024;
        return (T) Q();
    }

    public T T(float f) {
        if (this.w) {
            return (T) clone().T(f);
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.f3484c = f;
        this.f3483b |= 2;
        return (T) Q();
    }

    public T U(boolean z) {
        if (this.w) {
            return (T) clone().U(true);
        }
        this.j = !z;
        this.f3483b |= 256;
        return (T) Q();
    }

    public T V(com.bumptech.glide.load.h<Bitmap> hVar) {
        return (T) W(hVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    T W(com.bumptech.glide.load.h<Bitmap> hVar, boolean z) {
        if (this.w) {
            return (T) clone().W(hVar, z);
        }
        l lVar = new l(hVar, z);
        Y(Bitmap.class, hVar, z);
        Y(Drawable.class, lVar, z);
        Y(BitmapDrawable.class, lVar.c(), z);
        Y(com.bumptech.glide.load.j.g.c.class, new com.bumptech.glide.load.j.g.f(hVar), z);
        return (T) Q();
    }

    final T X(DownsampleStrategy downsampleStrategy, com.bumptech.glide.load.h<Bitmap> hVar) {
        if (this.w) {
            return (T) clone().X(downsampleStrategy, hVar);
        }
        i(downsampleStrategy);
        return (T) V(hVar);
    }

    <Y> T Y(Class<Y> cls, com.bumptech.glide.load.h<Y> hVar, boolean z) {
        if (this.w) {
            return (T) clone().Y(cls, hVar, z);
        }
        j.d(cls);
        j.d(hVar);
        this.s.put(cls, hVar);
        int i = this.f3483b | 2048;
        this.f3483b = i;
        this.o = true;
        int i2 = i | 65536;
        this.f3483b = i2;
        this.z = false;
        if (z) {
            this.f3483b = i2 | 131072;
            this.n = true;
        }
        return (T) Q();
    }

    public T Z(boolean z) {
        if (this.w) {
            return (T) clone().Z(z);
        }
        this.A = z;
        this.f3483b |= 1048576;
        return (T) Q();
    }

    public T a(a<?> aVar) {
        if (this.w) {
            return (T) clone().a(aVar);
        }
        if (I(aVar.f3483b, 2)) {
            this.f3484c = aVar.f3484c;
        }
        if (I(aVar.f3483b, 262144)) {
            this.x = aVar.x;
        }
        if (I(aVar.f3483b, 1048576)) {
            this.A = aVar.A;
        }
        if (I(aVar.f3483b, 4)) {
            this.f3485d = aVar.f3485d;
        }
        if (I(aVar.f3483b, 8)) {
            this.f3486e = aVar.f3486e;
        }
        if (I(aVar.f3483b, 16)) {
            this.f = aVar.f;
            this.g = 0;
            this.f3483b &= -33;
        }
        if (I(aVar.f3483b, 32)) {
            this.g = aVar.g;
            this.f = null;
            this.f3483b &= -17;
        }
        if (I(aVar.f3483b, 64)) {
            this.h = aVar.h;
            this.i = 0;
            this.f3483b &= -129;
        }
        if (I(aVar.f3483b, 128)) {
            this.i = aVar.i;
            this.h = null;
            this.f3483b &= -65;
        }
        if (I(aVar.f3483b, 256)) {
            this.j = aVar.j;
        }
        if (I(aVar.f3483b, 512)) {
            this.l = aVar.l;
            this.k = aVar.k;
        }
        if (I(aVar.f3483b, 1024)) {
            this.m = aVar.m;
        }
        if (I(aVar.f3483b, 4096)) {
            this.t = aVar.t;
        }
        if (I(aVar.f3483b, 8192)) {
            this.p = aVar.p;
            this.q = 0;
            this.f3483b &= -16385;
        }
        if (I(aVar.f3483b, 16384)) {
            this.q = aVar.q;
            this.p = null;
            this.f3483b &= -8193;
        }
        if (I(aVar.f3483b, 32768)) {
            this.v = aVar.v;
        }
        if (I(aVar.f3483b, 65536)) {
            this.o = aVar.o;
        }
        if (I(aVar.f3483b, 131072)) {
            this.n = aVar.n;
        }
        if (I(aVar.f3483b, 2048)) {
            this.s.putAll(aVar.s);
            this.z = aVar.z;
        }
        if (I(aVar.f3483b, 524288)) {
            this.y = aVar.y;
        }
        if (!this.o) {
            this.s.clear();
            int i = this.f3483b & (-2049);
            this.f3483b = i;
            this.n = false;
            this.f3483b = i & (-131073);
            this.z = true;
        }
        this.f3483b |= aVar.f3483b;
        this.r.d(aVar.r);
        return (T) Q();
    }

    public T b() {
        if (this.u && !this.w) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.w = true;
        return (T) L();
    }

    public T c() {
        return (T) X(DownsampleStrategy.f3341e, new i());
    }

    @Override // 
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public T clone() {
        try {
            T t = (T) super.clone();
            com.bumptech.glide.load.e eVar = new com.bumptech.glide.load.e();
            t.r = eVar;
            eVar.d(this.r);
            com.bumptech.glide.o.b bVar = new com.bumptech.glide.o.b();
            t.s = bVar;
            bVar.putAll(this.s);
            t.u = false;
            t.w = false;
            return t;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public T e(Class<?> cls) {
        if (this.w) {
            return (T) clone().e(cls);
        }
        this.t = (Class) j.d(cls);
        this.f3483b |= 4096;
        return (T) Q();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Float.compare(aVar.f3484c, this.f3484c) == 0 && this.g == aVar.g && k.c(this.f, aVar.f) && this.i == aVar.i && k.c(this.h, aVar.h) && this.q == aVar.q && k.c(this.p, aVar.p) && this.j == aVar.j && this.k == aVar.k && this.l == aVar.l && this.n == aVar.n && this.o == aVar.o && this.x == aVar.x && this.y == aVar.y && this.f3485d.equals(aVar.f3485d) && this.f3486e == aVar.f3486e && this.r.equals(aVar.r) && this.s.equals(aVar.s) && this.t.equals(aVar.t) && k.c(this.m, aVar.m) && k.c(this.v, aVar.v);
    }

    public T f(h hVar) {
        if (this.w) {
            return (T) clone().f(hVar);
        }
        this.f3485d = (h) j.d(hVar);
        this.f3483b |= 4;
        return (T) Q();
    }

    public T g() {
        return (T) R(com.bumptech.glide.load.j.g.i.f3317b, Boolean.TRUE);
    }

    public T h() {
        if (this.w) {
            return (T) clone().h();
        }
        this.s.clear();
        int i = this.f3483b & (-2049);
        this.f3483b = i;
        this.n = false;
        int i2 = i & (-131073);
        this.f3483b = i2;
        this.o = false;
        this.f3483b = i2 | 65536;
        this.z = true;
        return (T) Q();
    }

    public int hashCode() {
        return k.m(this.v, k.m(this.m, k.m(this.t, k.m(this.s, k.m(this.r, k.m(this.f3486e, k.m(this.f3485d, k.n(this.y, k.n(this.x, k.n(this.o, k.n(this.n, k.l(this.l, k.l(this.k, k.n(this.j, k.m(this.p, k.l(this.q, k.m(this.h, k.l(this.i, k.m(this.f, k.l(this.g, k.j(this.f3484c)))))))))))))))))))));
    }

    public T i(DownsampleStrategy downsampleStrategy) {
        return (T) R(DownsampleStrategy.h, j.d(downsampleStrategy));
    }

    public T j(int i) {
        if (this.w) {
            return (T) clone().j(i);
        }
        this.g = i;
        int i2 = this.f3483b | 32;
        this.f3483b = i2;
        this.f = null;
        this.f3483b = i2 & (-17);
        return (T) Q();
    }

    public T k(DecodeFormat decodeFormat) {
        j.d(decodeFormat);
        return (T) R(com.bumptech.glide.load.resource.bitmap.j.f3360a, decodeFormat).R(com.bumptech.glide.load.j.g.i.f3316a, decodeFormat);
    }

    public final h l() {
        return this.f3485d;
    }

    public final int m() {
        return this.g;
    }

    public final Drawable n() {
        return this.f;
    }

    public final Drawable o() {
        return this.p;
    }

    public final int p() {
        return this.q;
    }

    public final boolean q() {
        return this.y;
    }

    public final com.bumptech.glide.load.e r() {
        return this.r;
    }

    public final int s() {
        return this.k;
    }

    public final int t() {
        return this.l;
    }

    public final Drawable u() {
        return this.h;
    }

    public final int v() {
        return this.i;
    }

    public final Priority w() {
        return this.f3486e;
    }

    public final Class<?> x() {
        return this.t;
    }

    public final com.bumptech.glide.load.c y() {
        return this.m;
    }

    public final float z() {
        return this.f3484c;
    }
}
