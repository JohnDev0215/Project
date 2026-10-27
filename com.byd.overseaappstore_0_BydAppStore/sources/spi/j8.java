package spi;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import spi.es;
import spi.n8;
import spi.ta;
import spi.w5;

/* JADX INFO: loaded from: classes.dex */
public final class j8<R> implements w5.b<R>, ta.d {
    public static final c z = new c();
    public final e a;
    public final es.b b;
    public final n8.a c;
    public final Pools.Pool<j8<?>> d;
    public final c e;
    public final k8 f;
    public final ld g;
    public final ld h;
    public final ld i;
    public final ld j;
    public final AtomicInteger k;
    public ig l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public wm<?> q;
    public DataSource r;
    public boolean s;
    public GlideException t;
    public boolean u;
    public n8<?> v;
    public w5<R> w;
    public volatile boolean x;
    public boolean y;

    public class a implements Runnable {
        public final an a;

        public a(an anVar) {
            this.a = anVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            np npVar = (np) this.a;
            npVar.b.b();
            synchronized (npVar.c) {
                synchronized (j8.this) {
                    if (j8.this.a.a.contains(new d(this.a, pa.b))) {
                        j8 j8Var = j8.this;
                        an anVar = this.a;
                        Objects.requireNonNull(j8Var);
                        try {
                            ((np) anVar).o(j8Var.t, 5);
                        } catch (Throwable th) {
                            throw new f3(th);
                        }
                    }
                    j8.this.d();
                }
            }
        }
    }

    public class b implements Runnable {
        public final an a;

        public b(an anVar) {
            this.a = anVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            np npVar = (np) this.a;
            npVar.b.b();
            synchronized (npVar.c) {
                synchronized (j8.this) {
                    if (j8.this.a.a.contains(new d(this.a, pa.b))) {
                        j8.this.v.b();
                        j8 j8Var = j8.this;
                        an anVar = this.a;
                        Objects.requireNonNull(j8Var);
                        try {
                            ((np) anVar).p(j8Var.v, j8Var.r, j8Var.y);
                            j8.this.h(this.a);
                        } catch (Throwable th) {
                            throw new f3(th);
                        }
                    }
                    j8.this.d();
                }
            }
        }
    }

    @VisibleForTesting
    public static class c {
    }

    public static final class d {
        public final an a;
        public final Executor b;

        public d(an anVar, Executor executor) {
            this.a = anVar;
            this.b = executor;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.a.equals(((d) obj).a);
            }
            return false;
        }

        public final int hashCode() {
            return this.a.hashCode();
        }
    }

    public static final class e implements Iterable<d> {
        public final List<d> a;

        public e() {
            this(new ArrayList(2));
        }

        public e(List<d> list) {
            this.a = list;
        }

        public final boolean isEmpty() {
            return this.a.isEmpty();
        }

        @Override // java.lang.Iterable
        @NonNull
        public final Iterator<d> iterator() {
            return this.a.iterator();
        }
    }

    public j8(ld ldVar, ld ldVar2, ld ldVar3, ld ldVar4, k8 k8Var, n8.a aVar, Pools.Pool<j8<?>> pool) {
        this(ldVar, ldVar2, ldVar3, ldVar4, k8Var, aVar, pool, z);
    }

    @VisibleForTesting
    public j8(ld ldVar, ld ldVar2, ld ldVar3, ld ldVar4, k8 k8Var, n8.a aVar, Pools.Pool<j8<?>> pool, c cVar) {
        this.a = new e();
        this.b = new es.b();
        this.k = new AtomicInteger();
        this.g = ldVar;
        this.h = ldVar2;
        this.i = ldVar3;
        this.j = ldVar4;
        this.f = k8Var;
        this.c = aVar;
        this.d = pool;
        this.e = cVar;
    }

    public final synchronized void a(an anVar, Executor executor) {
        Runnable aVar;
        this.b.b();
        this.a.a.add(new d(anVar, executor));
        boolean z2 = true;
        if (this.s) {
            e(1);
            aVar = new b(anVar);
        } else if (this.u) {
            e(1);
            aVar = new a(anVar);
        } else {
            if (this.x) {
                z2 = false;
            }
            dl.a(z2, "Cannot add callbacks to a cancelled EngineJob");
        }
        executor.execute(aVar);
    }

    @Override // spi.ta.d
    @NonNull
    public final es b() {
        return this.b;
    }

    public final void c() {
        if (f()) {
            return;
        }
        this.x = true;
        w5<R> w5Var = this.w;
        w5Var.E = true;
        n5 n5Var = w5Var.C;
        if (n5Var != null) {
            n5Var.cancel();
        }
        k8 k8Var = this.f;
        ig igVar = this.l;
        i8 i8Var = (i8) k8Var;
        synchronized (i8Var) {
            hg hgVar = i8Var.a;
            Objects.requireNonNull(hgVar);
            Map<ig, j8<?>> mapA = hgVar.a(this.p);
            if (equals(mapA.get(igVar))) {
                mapA.remove(igVar);
            }
        }
    }

    public final void d() {
        n8<?> n8Var;
        synchronized (this) {
            this.b.b();
            dl.a(f(), "Not yet complete!");
            int iDecrementAndGet = this.k.decrementAndGet();
            dl.a(iDecrementAndGet >= 0, "Can't decrement below 0");
            if (iDecrementAndGet == 0) {
                n8Var = this.v;
                g();
            } else {
                n8Var = null;
            }
        }
        if (n8Var != null) {
            n8Var.d();
        }
    }

    public final synchronized void e(int i) {
        n8<?> n8Var;
        dl.a(f(), "Not yet complete!");
        if (this.k.getAndAdd(i) == 0 && (n8Var = this.v) != null) {
            n8Var.b();
        }
    }

    public final boolean f() {
        return this.u || this.s || this.x;
    }

    public final synchronized void g() {
        boolean zA;
        if (this.l == null) {
            throw new IllegalArgumentException();
        }
        this.a.a.clear();
        this.l = null;
        this.v = null;
        this.q = null;
        this.u = false;
        this.x = false;
        this.s = false;
        this.y = false;
        w5<R> w5Var = this.w;
        w5.f fVar = w5Var.g;
        synchronized (fVar) {
            fVar.a = true;
            zA = fVar.a();
        }
        if (zA) {
            w5Var.m();
        }
        this.w = null;
        this.t = null;
        this.r = null;
        this.d.release(this);
    }

    public final synchronized void h(an anVar) {
        this.b.b();
        this.a.a.remove(new d(anVar, pa.b));
        if (this.a.isEmpty()) {
            c();
            if ((this.s || this.u) && this.k.get() == 0) {
                g();
            }
        }
    }

    public final void i(w5<?> w5Var) {
        (this.n ? this.i : this.o ? this.j : this.h).execute(w5Var);
    }
}
