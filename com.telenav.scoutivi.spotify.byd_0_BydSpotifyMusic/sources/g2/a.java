package g2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import g2.a;
import java.util.Map;
import k2.k;
import n1.m;
import q1.j;
import x1.l;
import x1.o;
import x1.q;

/* JADX INFO: compiled from: BaseRequestOptions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends a<T>> implements Cloneable {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean E;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f6463e;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Drawable f6467j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f6468k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Drawable f6469l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f6470m;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f6475r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Drawable f6477t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f6478u;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f6482y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private Resources.Theme f6483z;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f6464g = 1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private j f6465h = j.f9025e;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.bumptech.glide.g f6466i = com.bumptech.glide.g.NORMAL;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f6471n = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f6472o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f6473p = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private n1.f f6474q = j2.c.c();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f6476s = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private n1.i f6479v = new n1.i();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private Map<Class<?>, m<?>> f6480w = new k2.b();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private Class<?> f6481x = Object.class;
    private boolean D = true;

    private boolean E(int i7) {
        return F(this.f6463e, i7);
    }

    private static boolean F(int i7, int i8) {
        return (i7 & i8) != 0;
    }

    private T O(l lVar, m<Bitmap> mVar) {
        return (T) T(lVar, mVar, false);
    }

    private T T(l lVar, m<Bitmap> mVar, boolean z6) {
        T t6 = z6 ? (T) f0(lVar, mVar) : (T) P(lVar, mVar);
        t6.D = true;
        return t6;
    }

    private T U() {
        return this;
    }

    private T V() {
        if (this.f6482y) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return (T) U();
    }

    public final boolean A() {
        return this.B;
    }

    public final boolean B() {
        return this.f6471n;
    }

    public final boolean C() {
        return E(8);
    }

    boolean D() {
        return this.D;
    }

    public final boolean G() {
        return this.f6476s;
    }

    public final boolean H() {
        return this.f6475r;
    }

    public final boolean I() {
        return E(2048);
    }

    public final boolean J() {
        return k.s(this.f6473p, this.f6472o);
    }

    public T K() {
        this.f6482y = true;
        return (T) U();
    }

    public T L() {
        return (T) P(l.f9768e, new x1.i());
    }

    public T M() {
        return (T) O(l.f9767d, new x1.j());
    }

    public T N() {
        return (T) O(l.f9766c, new q());
    }

    final T P(l lVar, m<Bitmap> mVar) {
        if (this.A) {
            return (T) clone().P(lVar, mVar);
        }
        f(lVar);
        return (T) e0(mVar, false);
    }

    public T Q(int i7, int i8) {
        if (this.A) {
            return (T) clone().Q(i7, i8);
        }
        this.f6473p = i7;
        this.f6472o = i8;
        this.f6463e |= 512;
        return (T) V();
    }

    public T R(Drawable drawable) {
        if (this.A) {
            return (T) clone().R(drawable);
        }
        this.f6469l = drawable;
        int i7 = this.f6463e | 64;
        this.f6470m = 0;
        this.f6463e = i7 & (-129);
        return (T) V();
    }

    public T S(com.bumptech.glide.g gVar) {
        if (this.A) {
            return (T) clone().S(gVar);
        }
        this.f6466i = (com.bumptech.glide.g) k2.j.d(gVar);
        this.f6463e |= 8;
        return (T) V();
    }

    public <Y> T W(n1.h<Y> hVar, Y y6) {
        if (this.A) {
            return (T) clone().W(hVar, y6);
        }
        k2.j.d(hVar);
        k2.j.d(y6);
        this.f6479v.e(hVar, y6);
        return (T) V();
    }

    public T X(n1.f fVar) {
        if (this.A) {
            return (T) clone().X(fVar);
        }
        this.f6474q = (n1.f) k2.j.d(fVar);
        this.f6463e |= 1024;
        return (T) V();
    }

    public T Y(float f7) {
        if (this.A) {
            return (T) clone().Y(f7);
        }
        if (f7 < 0.0f || f7 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.f6464g = f7;
        this.f6463e |= 2;
        return (T) V();
    }

    public T Z(boolean z6) {
        if (this.A) {
            return (T) clone().Z(true);
        }
        this.f6471n = !z6;
        this.f6463e |= 256;
        return (T) V();
    }

    public T a(a<?> aVar) {
        if (this.A) {
            return (T) clone().a(aVar);
        }
        if (F(aVar.f6463e, 2)) {
            this.f6464g = aVar.f6464g;
        }
        if (F(aVar.f6463e, 262144)) {
            this.B = aVar.B;
        }
        if (F(aVar.f6463e, 1048576)) {
            this.E = aVar.E;
        }
        if (F(aVar.f6463e, 4)) {
            this.f6465h = aVar.f6465h;
        }
        if (F(aVar.f6463e, 8)) {
            this.f6466i = aVar.f6466i;
        }
        if (F(aVar.f6463e, 16)) {
            this.f6467j = aVar.f6467j;
            this.f6468k = 0;
            this.f6463e &= -33;
        }
        if (F(aVar.f6463e, 32)) {
            this.f6468k = aVar.f6468k;
            this.f6467j = null;
            this.f6463e &= -17;
        }
        if (F(aVar.f6463e, 64)) {
            this.f6469l = aVar.f6469l;
            this.f6470m = 0;
            this.f6463e &= -129;
        }
        if (F(aVar.f6463e, 128)) {
            this.f6470m = aVar.f6470m;
            this.f6469l = null;
            this.f6463e &= -65;
        }
        if (F(aVar.f6463e, 256)) {
            this.f6471n = aVar.f6471n;
        }
        if (F(aVar.f6463e, 512)) {
            this.f6473p = aVar.f6473p;
            this.f6472o = aVar.f6472o;
        }
        if (F(aVar.f6463e, 1024)) {
            this.f6474q = aVar.f6474q;
        }
        if (F(aVar.f6463e, 4096)) {
            this.f6481x = aVar.f6481x;
        }
        if (F(aVar.f6463e, 8192)) {
            this.f6477t = aVar.f6477t;
            this.f6478u = 0;
            this.f6463e &= -16385;
        }
        if (F(aVar.f6463e, 16384)) {
            this.f6478u = aVar.f6478u;
            this.f6477t = null;
            this.f6463e &= -8193;
        }
        if (F(aVar.f6463e, 32768)) {
            this.f6483z = aVar.f6483z;
        }
        if (F(aVar.f6463e, 65536)) {
            this.f6476s = aVar.f6476s;
        }
        if (F(aVar.f6463e, 131072)) {
            this.f6475r = aVar.f6475r;
        }
        if (F(aVar.f6463e, 2048)) {
            this.f6480w.putAll(aVar.f6480w);
            this.D = aVar.D;
        }
        if (F(aVar.f6463e, 524288)) {
            this.C = aVar.C;
        }
        if (!this.f6476s) {
            this.f6480w.clear();
            int i7 = this.f6463e & (-2049);
            this.f6475r = false;
            this.f6463e = i7 & (-131073);
            this.D = true;
        }
        this.f6463e |= aVar.f6463e;
        this.f6479v.d(aVar.f6479v);
        return (T) V();
    }

    public T a0(Resources.Theme theme) {
        if (this.A) {
            return (T) clone().a0(theme);
        }
        this.f6483z = theme;
        this.f6463e |= 32768;
        return (T) V();
    }

    public T b() {
        if (this.f6482y && !this.A) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.A = true;
        return (T) K();
    }

    public <Y> T b0(Class<Y> cls, m<Y> mVar) {
        return (T) c0(cls, mVar, true);
    }

    @Override // 
    /* JADX INFO: renamed from: c */
    public T clone() {
        try {
            T t6 = (T) super.clone();
            n1.i iVar = new n1.i();
            t6.f6479v = iVar;
            iVar.d(this.f6479v);
            k2.b bVar = new k2.b();
            t6.f6480w = bVar;
            bVar.putAll(this.f6480w);
            t6.f6482y = false;
            t6.A = false;
            return t6;
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }

    <Y> T c0(Class<Y> cls, m<Y> mVar, boolean z6) {
        if (this.A) {
            return (T) clone().c0(cls, mVar, z6);
        }
        k2.j.d(cls);
        k2.j.d(mVar);
        this.f6480w.put(cls, mVar);
        int i7 = this.f6463e | 2048;
        this.f6476s = true;
        int i8 = i7 | 65536;
        this.f6463e = i8;
        this.D = false;
        if (z6) {
            this.f6463e = i8 | 131072;
            this.f6475r = true;
        }
        return (T) V();
    }

    public T d(Class<?> cls) {
        if (this.A) {
            return (T) clone().d(cls);
        }
        this.f6481x = (Class) k2.j.d(cls);
        this.f6463e |= 4096;
        return (T) V();
    }

    public T d0(m<Bitmap> mVar) {
        return (T) e0(mVar, true);
    }

    public T e(j jVar) {
        if (this.A) {
            return (T) clone().e(jVar);
        }
        this.f6465h = (j) k2.j.d(jVar);
        this.f6463e |= 4;
        return (T) V();
    }

    /* JADX WARN: Multi-variable type inference failed */
    T e0(m<Bitmap> mVar, boolean z6) {
        if (this.A) {
            return (T) clone().e0(mVar, z6);
        }
        o oVar = new o(mVar, z6);
        c0(Bitmap.class, mVar, z6);
        c0(Drawable.class, oVar, z6);
        c0(BitmapDrawable.class, oVar.c(), z6);
        c0(b2.c.class, new b2.f(mVar), z6);
        return (T) V();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Float.compare(aVar.f6464g, this.f6464g) == 0 && this.f6468k == aVar.f6468k && k.c(this.f6467j, aVar.f6467j) && this.f6470m == aVar.f6470m && k.c(this.f6469l, aVar.f6469l) && this.f6478u == aVar.f6478u && k.c(this.f6477t, aVar.f6477t) && this.f6471n == aVar.f6471n && this.f6472o == aVar.f6472o && this.f6473p == aVar.f6473p && this.f6475r == aVar.f6475r && this.f6476s == aVar.f6476s && this.B == aVar.B && this.C == aVar.C && this.f6465h.equals(aVar.f6465h) && this.f6466i == aVar.f6466i && this.f6479v.equals(aVar.f6479v) && this.f6480w.equals(aVar.f6480w) && this.f6481x.equals(aVar.f6481x) && k.c(this.f6474q, aVar.f6474q) && k.c(this.f6483z, aVar.f6483z);
    }

    public T f(l lVar) {
        return (T) W(l.f9771h, k2.j.d(lVar));
    }

    final T f0(l lVar, m<Bitmap> mVar) {
        if (this.A) {
            return (T) clone().f0(lVar, mVar);
        }
        f(lVar);
        return (T) d0(mVar);
    }

    public T g(Drawable drawable) {
        if (this.A) {
            return (T) clone().g(drawable);
        }
        this.f6467j = drawable;
        int i7 = this.f6463e | 16;
        this.f6468k = 0;
        this.f6463e = i7 & (-33);
        return (T) V();
    }

    public T g0(m<Bitmap>... mVarArr) {
        return mVarArr.length > 1 ? (T) e0(new n1.g(mVarArr), true) : mVarArr.length == 1 ? (T) d0(mVarArr[0]) : (T) V();
    }

    public final j h() {
        return this.f6465h;
    }

    public T h0(boolean z6) {
        if (this.A) {
            return (T) clone().h0(z6);
        }
        this.E = z6;
        this.f6463e |= 1048576;
        return (T) V();
    }

    public int hashCode() {
        return k.n(this.f6483z, k.n(this.f6474q, k.n(this.f6481x, k.n(this.f6480w, k.n(this.f6479v, k.n(this.f6466i, k.n(this.f6465h, k.o(this.C, k.o(this.B, k.o(this.f6476s, k.o(this.f6475r, k.m(this.f6473p, k.m(this.f6472o, k.o(this.f6471n, k.n(this.f6477t, k.m(this.f6478u, k.n(this.f6469l, k.m(this.f6470m, k.n(this.f6467j, k.m(this.f6468k, k.j(this.f6464g)))))))))))))))))))));
    }

    public final int i() {
        return this.f6468k;
    }

    public final Drawable j() {
        return this.f6467j;
    }

    public final Drawable k() {
        return this.f6477t;
    }

    public final int l() {
        return this.f6478u;
    }

    public final boolean n() {
        return this.C;
    }

    public final n1.i o() {
        return this.f6479v;
    }

    public final int p() {
        return this.f6472o;
    }

    public final int q() {
        return this.f6473p;
    }

    public final Drawable r() {
        return this.f6469l;
    }

    public final int s() {
        return this.f6470m;
    }

    public final com.bumptech.glide.g t() {
        return this.f6466i;
    }

    public final Class<?> u() {
        return this.f6481x;
    }

    public final n1.f v() {
        return this.f6474q;
    }

    public final float w() {
        return this.f6464g;
    }

    public final Resources.Theme x() {
        return this.f6483z;
    }

    public final Map<Class<?>, m<?>> y() {
        return this.f6480w;
    }

    public final boolean z() {
        return this.E;
    }
}
