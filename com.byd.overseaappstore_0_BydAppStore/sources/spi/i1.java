package spi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.util.Map;
import java.util.Objects;
import spi.i1;

/* JADX INFO: loaded from: classes.dex */
public abstract class i1<T extends i1<T>> implements Cloneable {
    public int a;

    @Nullable
    public Drawable e;
    public int f;

    @Nullable
    public Drawable g;
    public int h;
    public boolean m;

    @Nullable
    public Drawable o;
    public int p;
    public boolean t;

    @Nullable
    public Resources.Theme u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean z;
    public float b = 1.0f;

    @NonNull
    public i6 c = i6.d;

    @NonNull
    public Priority d = Priority.NORMAL;
    public boolean i = true;
    public int j = -1;
    public int k = -1;

    @NonNull
    public ig l = e8.b;
    public boolean n = true;

    @NonNull
    public lk q = new lk();

    @NonNull
    public Map<Class<?>, qt<?>> r = new CachedHashCodeArrayMap();

    @NonNull
    public Class<?> s = Object.class;
    public boolean y = true;

    public static boolean i(int i, int i2) {
        return (i & i2) != 0;
    }

    /* JADX WARN: Type inference failed for: r0v45, types: [com.bumptech.glide.util.CachedHashCodeArrayMap, java.util.Map<java.lang.Class<?>, spi.qt<?>>] */
    @NonNull
    @CheckResult
    public T a(@NonNull i1<?> i1Var) {
        if (this.v) {
            return (T) clone().a(i1Var);
        }
        if (i(i1Var.a, 2)) {
            this.b = i1Var.b;
        }
        if (i(i1Var.a, 262144)) {
            this.w = i1Var.w;
        }
        if (i(i1Var.a, 1048576)) {
            this.z = i1Var.z;
        }
        if (i(i1Var.a, 4)) {
            this.c = i1Var.c;
        }
        if (i(i1Var.a, 8)) {
            this.d = i1Var.d;
        }
        if (i(i1Var.a, 16)) {
            this.e = i1Var.e;
            this.f = 0;
            this.a &= -33;
        }
        if (i(i1Var.a, 32)) {
            this.f = i1Var.f;
            this.e = null;
            this.a &= -17;
        }
        if (i(i1Var.a, 64)) {
            this.g = i1Var.g;
            this.h = 0;
            this.a &= -129;
        }
        if (i(i1Var.a, 128)) {
            this.h = i1Var.h;
            this.g = null;
            this.a &= -65;
        }
        if (i(i1Var.a, 256)) {
            this.i = i1Var.i;
        }
        if (i(i1Var.a, 512)) {
            this.k = i1Var.k;
            this.j = i1Var.j;
        }
        if (i(i1Var.a, 1024)) {
            this.l = i1Var.l;
        }
        if (i(i1Var.a, 4096)) {
            this.s = i1Var.s;
        }
        if (i(i1Var.a, 8192)) {
            this.o = i1Var.o;
            this.p = 0;
            this.a &= -16385;
        }
        if (i(i1Var.a, 16384)) {
            this.p = i1Var.p;
            this.o = null;
            this.a &= -8193;
        }
        if (i(i1Var.a, 32768)) {
            this.u = i1Var.u;
        }
        if (i(i1Var.a, 65536)) {
            this.n = i1Var.n;
        }
        if (i(i1Var.a, 131072)) {
            this.m = i1Var.m;
        }
        if (i(i1Var.a, 2048)) {
            this.r.putAll(i1Var.r);
            this.y = i1Var.y;
        }
        if (i(i1Var.a, 524288)) {
            this.x = i1Var.x;
        }
        if (!this.n) {
            this.r.clear();
            int i = this.a & (-2049);
            this.m = false;
            this.a = i & (-131073);
            this.y = true;
        }
        this.a |= i1Var.a;
        this.q.d(i1Var.q);
        r();
        return this;
    }

    @NonNull
    public T b() {
        if (this.t && !this.v) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.v = true;
        return (T) j();
    }

    @Override // 
    @CheckResult
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public T clone() {
        try {
            T t = (T) super.clone();
            lk lkVar = new lk();
            t.q = lkVar;
            lkVar.d(this.q);
            CachedHashCodeArrayMap cachedHashCodeArrayMap = new CachedHashCodeArrayMap();
            t.r = cachedHashCodeArrayMap;
            cachedHashCodeArrayMap.putAll(this.r);
            t.t = false;
            t.v = false;
            return t;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @CheckResult
    public T d(@NonNull Class<?> cls) {
        if (this.v) {
            return (T) clone().d(cls);
        }
        this.s = cls;
        this.a |= 4096;
        r();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i1) {
            i1 i1Var = (i1) obj;
            if (Float.compare(i1Var.b, this.b) == 0 && this.f == i1Var.f && tu.b(this.e, i1Var.e) && this.h == i1Var.h && tu.b(this.g, i1Var.g) && this.p == i1Var.p && tu.b(this.o, i1Var.o) && this.i == i1Var.i && this.j == i1Var.j && this.k == i1Var.k && this.m == i1Var.m && this.n == i1Var.n && this.w == i1Var.w && this.x == i1Var.x && this.c.equals(i1Var.c) && this.d == i1Var.d && this.q.equals(i1Var.q) && this.r.equals(i1Var.r) && this.s.equals(i1Var.s) && tu.b(this.l, i1Var.l) && tu.b(this.u, i1Var.u)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @CheckResult
    public T f(@NonNull i6 i6Var) {
        if (this.v) {
            return (T) clone().f(i6Var);
        }
        Objects.requireNonNull(i6Var, "Argument must not be null");
        this.c = i6Var;
        this.a |= 4;
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T g(@NonNull DownsampleStrategy downsampleStrategy) {
        jk jkVar = DownsampleStrategy.f;
        Objects.requireNonNull(downsampleStrategy, "Argument must not be null");
        return (T) s(jkVar, downsampleStrategy);
    }

    @NonNull
    @CheckResult
    public T h(@DrawableRes int i) {
        if (this.v) {
            return (T) clone().h(i);
        }
        this.f = i;
        int i2 = this.a | 32;
        this.e = null;
        this.a = i2 & (-17);
        r();
        return this;
    }

    public final int hashCode() {
        float f = this.b;
        char[] cArr = tu.a;
        return tu.g(this.u, tu.g(this.l, tu.g(this.s, tu.g(this.r, tu.g(this.q, tu.g(this.d, tu.g(this.c, (((((((((((((tu.g(this.o, (tu.g(this.g, (tu.g(this.e, ((Float.floatToIntBits(f) + 527) * 31) + this.f) * 31) + this.h) * 31) + this.p) * 31) + (this.i ? 1 : 0)) * 31) + this.j) * 31) + this.k) * 31) + (this.m ? 1 : 0)) * 31) + (this.n ? 1 : 0)) * 31) + (this.w ? 1 : 0)) * 31) + (this.x ? 1 : 0))))))));
    }

    @NonNull
    public T j() {
        this.t = true;
        return this;
    }

    @NonNull
    @CheckResult
    public T k() {
        return (T) n(DownsampleStrategy.c, new j3());
    }

    @NonNull
    @CheckResult
    public T l() {
        T t = (T) n(DownsampleStrategy.b, new k3());
        t.y = true;
        return t;
    }

    @NonNull
    @CheckResult
    public T m() {
        T t = (T) n(DownsampleStrategy.a, new cc());
        t.y = true;
        return t;
    }

    @NonNull
    public final T n(@NonNull DownsampleStrategy downsampleStrategy, @NonNull qt<Bitmap> qtVar) {
        if (this.v) {
            return (T) clone().n(downsampleStrategy, qtVar);
        }
        g(downsampleStrategy);
        return (T) x(qtVar, false);
    }

    @NonNull
    @CheckResult
    public T o(int i, int i2) {
        if (this.v) {
            return (T) clone().o(i, i2);
        }
        this.k = i;
        this.j = i2;
        this.a |= 512;
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T p(@DrawableRes int i) {
        if (this.v) {
            return (T) clone().p(i);
        }
        this.h = i;
        int i2 = this.a | 128;
        this.g = null;
        this.a = i2 & (-65);
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T q(@NonNull Priority priority) {
        if (this.v) {
            return (T) clone().q(priority);
        }
        Objects.requireNonNull(priority, "Argument must not be null");
        this.d = priority;
        this.a |= 8;
        r();
        return this;
    }

    @NonNull
    public final T r() {
        if (this.t) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.collection.ArrayMap<spi.jk<?>, java.lang.Object>, com.bumptech.glide.util.CachedHashCodeArrayMap] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @NonNull
    @CheckResult
    public <Y> T s(@NonNull jk<Y> jkVar, @NonNull Y y) {
        if (this.v) {
            return (T) clone().s(jkVar, y);
        }
        Objects.requireNonNull(jkVar, "Argument must not be null");
        Objects.requireNonNull(y, "Argument must not be null");
        this.q.b.put((jk<?>) jkVar, y);
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T t(@NonNull ig igVar) {
        if (this.v) {
            return (T) clone().t(igVar);
        }
        this.l = igVar;
        this.a |= 1024;
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public i1 u() {
        if (this.v) {
            return clone().u();
        }
        this.i = false;
        this.a |= 256;
        r();
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.bumptech.glide.util.CachedHashCodeArrayMap, java.util.Map<java.lang.Class<?>, spi.qt<?>>] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @NonNull
    public final <Y> T v(@NonNull Class<Y> cls, @NonNull qt<Y> qtVar, boolean z) {
        if (this.v) {
            return (T) clone().v(cls, qtVar, z);
        }
        Objects.requireNonNull(qtVar, "Argument must not be null");
        this.r.put((Class<?>) cls, (qt<?>) qtVar);
        int i = this.a | 2048;
        this.n = true;
        int i2 = i | 65536;
        this.a = i2;
        this.y = false;
        if (z) {
            this.a = i2 | 131072;
            this.m = true;
        }
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T w(@NonNull qt<Bitmap> qtVar) {
        return (T) x(qtVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public final T x(@NonNull qt<Bitmap> qtVar, boolean z) {
        if (this.v) {
            return (T) clone().x(qtVar, z);
        }
        w7 w7Var = new w7(qtVar, z);
        v(Bitmap.class, qtVar, z);
        v(Drawable.class, w7Var, z);
        v(BitmapDrawable.class, w7Var, z);
        v(GifDrawable.class, new fd(qtVar), z);
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public T y(@NonNull qt<Bitmap>... qtVarArr) {
        if (qtVarArr.length > 1) {
            return (T) x(new bj(qtVarArr), true);
        }
        if (qtVarArr.length == 1) {
            return (T) w(qtVarArr[0]);
        }
        r();
        return this;
    }

    @NonNull
    @CheckResult
    public i1 z() {
        if (this.v) {
            return clone().z();
        }
        this.z = true;
        this.a |= 1048576;
        r();
        return this;
    }
}
