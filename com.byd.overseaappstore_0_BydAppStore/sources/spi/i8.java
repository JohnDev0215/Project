package spi;

import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import spi.g6;
import spi.h;
import spi.n8;
import spi.ni;
import spi.ta;
import spi.w5;

/* JADX INFO: loaded from: classes.dex */
public final class i8 implements k8, ni.a, n8.a {
    public static final boolean h = Log.isLoggable("Engine", 2);
    public final hg a;
    public final m8 b;
    public final ni c;
    public final b d;
    public final hn e;
    public final a f;
    public final h g;

    @VisibleForTesting
    public static class a {
        public final w5.e a;
        public final Pools.Pool<w5<?>> b = (ta.c) ta.a(150, new C0128a());
        public int c;

        /* JADX INFO: renamed from: spi.i8$a$a, reason: collision with other inner class name */
        public class C0128a implements ta.b<w5<?>> {
            public C0128a() {
            }

            @Override // spi.ta.b
            public final w5<?> a() {
                a aVar = a.this;
                return new w5<>(aVar.a, aVar.b);
            }
        }

        public a(w5.e eVar) {
            this.a = eVar;
        }
    }

    @VisibleForTesting
    public static class b {
        public final ld a;
        public final ld b;
        public final ld c;
        public final ld d;
        public final k8 e;
        public final n8.a f;
        public final Pools.Pool<j8<?>> g = (ta.c) ta.a(150, new a());

        public class a implements ta.b<j8<?>> {
            public a() {
            }

            @Override // spi.ta.b
            public final j8<?> a() {
                b bVar = b.this;
                return new j8<>(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e, bVar.f, bVar.g);
            }
        }

        public b(ld ldVar, ld ldVar2, ld ldVar3, ld ldVar4, k8 k8Var, n8.a aVar) {
            this.a = ldVar;
            this.b = ldVar2;
            this.c = ldVar3;
            this.d = ldVar4;
            this.e = k8Var;
            this.f = aVar;
        }
    }

    public static class c implements w5.e {
        public final g6.a a;
        public volatile g6 b;

        public c(g6.a aVar) {
            this.a = aVar;
        }

        public final g6 a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        n6 n6Var = (n6) this.a;
                        File fileA = n6Var.b.a();
                        o6 o6Var = null;
                        if (fileA != null && (fileA.isDirectory() || fileA.mkdirs())) {
                            o6Var = new o6(fileA, n6Var.a);
                        }
                        this.b = o6Var;
                    }
                    if (this.b == null) {
                        this.b = new h6();
                    }
                }
            }
            return this.b;
        }
    }

    public class d {
        public final j8<?> a;
        public final an b;

        public d(an anVar, j8<?> j8Var) {
            this.b = anVar;
            this.a = j8Var;
        }
    }

    @VisibleForTesting
    public i8(ni niVar, g6.a aVar, ld ldVar, ld ldVar2, ld ldVar3, ld ldVar4, hg hgVar, m8 m8Var, h hVar, b bVar, a aVar2, hn hnVar, boolean z) {
        this.c = niVar;
        c cVar = new c(aVar);
        h hVar2 = hVar == null ? new h(z) : hVar;
        this.g = hVar2;
        synchronized (this) {
            synchronized (hVar2) {
                hVar2.d = this;
            }
        }
        this.b = m8Var == null ? new m8() : m8Var;
        this.a = hgVar == null ? new hg() : hgVar;
        this.d = bVar == null ? new b(ldVar, ldVar2, ldVar3, ldVar4, this, this) : bVar;
        this.f = aVar2 == null ? new a(cVar) : aVar2;
        this.e = hnVar == null ? new hn() : hnVar;
        niVar.c(this);
    }

    public i8(ni niVar, g6.a aVar, ld ldVar, ld ldVar2, ld ldVar3, ld ldVar4, boolean z) {
        this(niVar, aVar, ldVar, ldVar2, ldVar3, ldVar4, null, null, null, null, null, null, z);
    }

    public static void d(String str, long j, ig igVar) {
        StringBuilder sbM = e.m(str, " in ");
        sbM.append(ch.a(j));
        sbM.append("ms, key: ");
        sbM.append(igVar);
        Log.v("Engine", sbM.toString());
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<spi.ig, spi.h$c>] */
    @Override // spi.n8.a
    public final void a(ig igVar, n8<?> n8Var) {
        h hVar = this.g;
        synchronized (hVar) {
            h.c cVar = (h.c) hVar.b.remove(igVar);
            if (cVar != null) {
                cVar.c = null;
                cVar.clear();
            }
        }
        if (n8Var.a) {
            this.c.e(igVar, n8Var);
        } else {
            this.e.a(n8Var, false);
        }
    }

    public final <R> d b(com.bumptech.glide.c cVar, Object obj, ig igVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, i6 i6Var, Map<Class<?>, qt<?>> map, boolean z, boolean z2, lk lkVar, boolean z3, boolean z4, boolean z5, boolean z6, an anVar, Executor executor) {
        long jElapsedRealtimeNanos;
        if (h) {
            int i3 = ch.b;
            jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        } else {
            jElapsedRealtimeNanos = 0;
        }
        long j = jElapsedRealtimeNanos;
        Objects.requireNonNull(this.b);
        l8 l8Var = new l8(obj, igVar, i, i2, map, cls, cls2, lkVar);
        synchronized (this) {
            n8<?> n8VarC = c(l8Var, z3, j);
            if (n8VarC == null) {
                return g(cVar, obj, igVar, i, i2, cls, cls2, priority, i6Var, map, z, z2, lkVar, z3, z4, z5, z6, anVar, executor, l8Var, j);
            }
            ((np) anVar).p(n8VarC, DataSource.MEMORY_CACHE, false);
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<spi.ig, spi.h$c>] */
    @Nullable
    public final n8<?> c(l8 l8Var, boolean z, long j) {
        n8<?> n8Var;
        if (!z) {
            return null;
        }
        h hVar = this.g;
        synchronized (hVar) {
            h.c cVar = (h.c) hVar.b.get(l8Var);
            if (cVar == null) {
                n8Var = null;
            } else {
                n8Var = cVar.get();
                if (n8Var == null) {
                    hVar.b(cVar);
                }
            }
        }
        if (n8Var != null) {
            n8Var.b();
        }
        if (n8Var != null) {
            if (h) {
                d("Loaded resource from active resources", j, l8Var);
            }
            return n8Var;
        }
        wm<?> wmVarD = this.c.d(l8Var);
        n8<?> n8Var2 = wmVarD == null ? null : wmVarD instanceof n8 ? (n8) wmVarD : new n8<>(wmVarD, true, true, l8Var, this);
        if (n8Var2 != null) {
            n8Var2.b();
            this.g.a(l8Var, n8Var2);
        }
        if (n8Var2 == null) {
            return null;
        }
        if (h) {
            d("Loaded resource from cache", j, l8Var);
        }
        return n8Var2;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0021 A[Catch: all -> 0x0026, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000c, B:9:0x0021), top: B:15:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void e(spi.j8<?> r2, spi.ig r3, spi.n8<?> r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r4 == 0) goto Lc
            boolean r0 = r4.a     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto Lc
            spi.h r0 = r1.g     // Catch: java.lang.Throwable -> L26
            r0.a(r3, r4)     // Catch: java.lang.Throwable -> L26
        Lc:
            spi.hg r4 = r1.a     // Catch: java.lang.Throwable -> L26
            java.util.Objects.requireNonNull(r4)     // Catch: java.lang.Throwable -> L26
            boolean r0 = r2.p     // Catch: java.lang.Throwable -> L26
            java.util.Map r4 = r4.a(r0)     // Catch: java.lang.Throwable -> L26
            java.lang.Object r0 = r4.get(r3)     // Catch: java.lang.Throwable -> L26
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Throwable -> L26
            if (r2 == 0) goto L24
            r4.remove(r3)     // Catch: java.lang.Throwable -> L26
        L24:
            monitor-exit(r1)
            return
        L26:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i8.e(spi.j8, spi.ig, spi.n8):void");
    }

    public final void f(wm<?> wmVar) {
        if (!(wmVar instanceof n8)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((n8) wmVar).d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> d g(com.bumptech.glide.c cVar, Object obj, ig igVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, i6 i6Var, Map<Class<?>, qt<?>> map, boolean z, boolean z2, lk lkVar, boolean z3, boolean z4, boolean z5, boolean z6, an anVar, Executor executor, l8 l8Var, long j) {
        j8<?> j8Var = this.a.a(z6).get(l8Var);
        if (j8Var != null) {
            j8Var.a(anVar, executor);
            if (h) {
                d("Added to existing load", j, l8Var);
            }
            return new d(anVar, j8Var);
        }
        j8<?> j8VarAcquire = this.d.g.acquire();
        Objects.requireNonNull(j8VarAcquire, "Argument must not be null");
        synchronized (j8VarAcquire) {
            j8VarAcquire.l = l8Var;
            j8VarAcquire.m = z3;
            j8VarAcquire.n = z4;
            j8VarAcquire.o = z5;
            j8VarAcquire.p = z6;
        }
        a aVar = this.f;
        w5<R> w5Var = (w5) aVar.b.acquire();
        Objects.requireNonNull(w5Var, "Argument must not be null");
        int i3 = aVar.c;
        aVar.c = i3 + 1;
        v5<R> v5Var = w5Var.a;
        w5.e eVar = w5Var.d;
        v5Var.c = cVar;
        v5Var.d = obj;
        v5Var.n = igVar;
        v5Var.e = i;
        v5Var.f = i2;
        v5Var.p = i6Var;
        v5Var.g = cls;
        v5Var.h = eVar;
        v5Var.k = cls2;
        v5Var.o = priority;
        v5Var.i = lkVar;
        v5Var.j = map;
        v5Var.q = z;
        v5Var.r = z2;
        w5Var.h = cVar;
        w5Var.i = igVar;
        w5Var.j = priority;
        w5Var.k = l8Var;
        w5Var.l = i;
        w5Var.m = i2;
        w5Var.n = i6Var;
        w5Var.u = z6;
        w5Var.o = lkVar;
        w5Var.p = j8VarAcquire;
        w5Var.q = i3;
        w5Var.s = w5.g.INITIALIZE;
        w5Var.v = obj;
        hg hgVar = this.a;
        Objects.requireNonNull(hgVar);
        hgVar.a(j8VarAcquire.p).put(l8Var, j8VarAcquire);
        j8VarAcquire.a(anVar, executor);
        synchronized (j8VarAcquire) {
            j8VarAcquire.w = w5Var;
            w5.h hVarI = w5Var.i(w5.h.INITIALIZE);
            (hVarI == w5.h.RESOURCE_CACHE || hVarI == w5.h.DATA_CACHE ? j8VarAcquire.g : j8VarAcquire.n ? j8VarAcquire.i : j8VarAcquire.o ? j8VarAcquire.j : j8VarAcquire.h).execute(w5Var);
        }
        if (h) {
            d("Started new load", j, l8Var);
        }
        return new d(anVar, j8VarAcquire);
    }
}
