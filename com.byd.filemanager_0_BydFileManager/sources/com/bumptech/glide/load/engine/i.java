package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.load.engine.y.a;
import com.bumptech.glide.load.engine.y.h;
import com.bumptech.glide.o.l.a;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Engine.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements k, h.a, n.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3026a = Log.isLoggable("Engine", 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f3027b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final m f3028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.y.h f3029d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b f3030e;
    private final v f;
    private final c g;
    private final a h;
    private final com.bumptech.glide.load.engine.a i;

    /* JADX INFO: compiled from: Engine.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final DecodeJob.e f3031a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b.g.j.e<DecodeJob<?>> f3032b = com.bumptech.glide.o.l.a.d(150, new C0064a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3033c;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.i$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Engine.java */
        class C0064a implements a.d<DecodeJob<?>> {
            C0064a() {
            }

            @Override // com.bumptech.glide.o.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public DecodeJob<?> a() {
                a aVar = a.this;
                return new DecodeJob<>(aVar.f3031a, aVar.f3032b);
            }
        }

        a(DecodeJob.e eVar) {
            this.f3031a = eVar;
        }

        <R> DecodeJob<R> a(com.bumptech.glide.e eVar, Object obj, l lVar, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.e eVar2, DecodeJob.b<R> bVar) {
            DecodeJob decodeJob = (DecodeJob) com.bumptech.glide.o.j.d(this.f3032b.b());
            int i3 = this.f3033c;
            this.f3033c = i3 + 1;
            return decodeJob.n(eVar, obj, lVar, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, z3, eVar2, bVar, i3);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f3035a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f3036b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f3037c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f3038d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final k f3039e;
        final n.a f;
        final b.g.j.e<j<?>> g = com.bumptech.glide.o.l.a.d(150, new a());

        /* JADX INFO: compiled from: Engine.java */
        class a implements a.d<j<?>> {
            a() {
            }

            @Override // com.bumptech.glide.o.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public j<?> a() {
                b bVar = b.this;
                return new j<>(bVar.f3035a, bVar.f3036b, bVar.f3037c, bVar.f3038d, bVar.f3039e, bVar.f, bVar.g);
            }
        }

        b(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5) {
            this.f3035a = aVar;
            this.f3036b = aVar2;
            this.f3037c = aVar3;
            this.f3038d = aVar4;
            this.f3039e = kVar;
            this.f = aVar5;
        }

        <R> j<R> a(com.bumptech.glide.load.c cVar, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((j) com.bumptech.glide.o.j.d(this.g.b())).l(cVar, z, z2, z3, z4);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    private static class c implements DecodeJob.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.InterfaceC0065a f3041a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile com.bumptech.glide.load.engine.y.a f3042b;

        c(a.InterfaceC0065a interfaceC0065a) {
            this.f3041a = interfaceC0065a;
        }

        @Override // com.bumptech.glide.load.engine.DecodeJob.e
        public com.bumptech.glide.load.engine.y.a a() {
            if (this.f3042b == null) {
                synchronized (this) {
                    if (this.f3042b == null) {
                        this.f3042b = this.f3041a.a();
                    }
                    if (this.f3042b == null) {
                        this.f3042b = new com.bumptech.glide.load.engine.y.b();
                    }
                }
            }
            return this.f3042b;
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final j<?> f3043a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.request.f f3044b;

        d(com.bumptech.glide.request.f fVar, j<?> jVar) {
            this.f3044b = fVar;
            this.f3043a = jVar;
        }

        public void a() {
            synchronized (i.this) {
                this.f3043a.r(this.f3044b);
            }
        }
    }

    public i(com.bumptech.glide.load.engine.y.h hVar, a.InterfaceC0065a interfaceC0065a, com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, boolean z) {
        this(hVar, interfaceC0065a, aVar, aVar2, aVar3, aVar4, null, null, null, null, null, null, z);
    }

    private n<?> e(com.bumptech.glide.load.c cVar) {
        s<?> sVarD = this.f3029d.d(cVar);
        if (sVarD == null) {
            return null;
        }
        return sVarD instanceof n ? (n) sVarD : new n<>(sVarD, true, true, cVar, this);
    }

    private n<?> g(com.bumptech.glide.load.c cVar) {
        n<?> nVarE = this.i.e(cVar);
        if (nVarE != null) {
            nVarE.b();
        }
        return nVarE;
    }

    private n<?> h(com.bumptech.glide.load.c cVar) {
        n<?> nVarE = e(cVar);
        if (nVarE != null) {
            nVarE.b();
            this.i.a(cVar, nVarE);
        }
        return nVarE;
    }

    private n<?> i(l lVar, boolean z, long j) {
        if (!z) {
            return null;
        }
        n<?> nVarG = g(lVar);
        if (nVarG != null) {
            if (f3026a) {
                j("Loaded resource from active resources", j, lVar);
            }
            return nVarG;
        }
        n<?> nVarH = h(lVar);
        if (nVarH == null) {
            return null;
        }
        if (f3026a) {
            j("Loaded resource from cache", j, lVar);
        }
        return nVarH;
    }

    private static void j(String str, long j, com.bumptech.glide.load.c cVar) {
        Log.v("Engine", str + " in " + com.bumptech.glide.o.f.a(j) + "ms, key: " + cVar);
    }

    private <R> d l(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, com.bumptech.glide.load.e eVar2, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.request.f fVar, Executor executor, l lVar, long j) {
        j<?> jVarA = this.f3027b.a(lVar, z6);
        if (jVarA != null) {
            jVarA.b(fVar, executor);
            if (f3026a) {
                j("Added to existing load", j, lVar);
            }
            return new d(fVar, jVarA);
        }
        j<R> jVarA2 = this.f3030e.a(lVar, z3, z4, z5, z6);
        DecodeJob<R> decodeJobA = this.h.a(eVar, obj, lVar, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, z6, eVar2, jVarA2);
        this.f3027b.c(lVar, jVarA2);
        jVarA2.b(fVar, executor);
        jVarA2.s(decodeJobA);
        if (f3026a) {
            j("Started new load", j, lVar);
        }
        return new d(fVar, jVarA2);
    }

    @Override // com.bumptech.glide.load.engine.n.a
    public void a(com.bumptech.glide.load.c cVar, n<?> nVar) {
        this.i.d(cVar);
        if (nVar.f()) {
            this.f3029d.c(cVar, nVar);
        } else {
            this.f.a(nVar, false);
        }
    }

    @Override // com.bumptech.glide.load.engine.y.h.a
    public void b(s<?> sVar) {
        this.f.a(sVar, true);
    }

    @Override // com.bumptech.glide.load.engine.k
    public synchronized void c(j<?> jVar, com.bumptech.glide.load.c cVar, n<?> nVar) {
        if (nVar != null) {
            if (nVar.f()) {
                this.i.a(cVar, nVar);
            }
            this.f3027b.d(cVar, jVar);
        } else {
            this.f3027b.d(cVar, jVar);
        }
    }

    @Override // com.bumptech.glide.load.engine.k
    public synchronized void d(j<?> jVar, com.bumptech.glide.load.c cVar) {
        this.f3027b.d(cVar, jVar);
    }

    public <R> d f(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, com.bumptech.glide.load.e eVar2, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.request.f fVar, Executor executor) {
        long jB = f3026a ? com.bumptech.glide.o.f.b() : 0L;
        l lVarA = this.f3028c.a(obj, cVar, i, i2, map, cls, cls2, eVar2);
        synchronized (this) {
            n<?> nVarI = i(lVarA, z3, jB);
            if (nVarI == null) {
                return l(eVar, obj, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, eVar2, z3, z4, z5, z6, fVar, executor, lVarA, jB);
            }
            fVar.c(nVarI, DataSource.MEMORY_CACHE);
            return null;
        }
    }

    public void k(s<?> sVar) {
        if (!(sVar instanceof n)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((n) sVar).g();
    }

    i(com.bumptech.glide.load.engine.y.h hVar, a.InterfaceC0065a interfaceC0065a, com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, p pVar, m mVar, com.bumptech.glide.load.engine.a aVar5, b bVar, a aVar6, v vVar, boolean z) {
        this.f3029d = hVar;
        c cVar = new c(interfaceC0065a);
        this.g = cVar;
        com.bumptech.glide.load.engine.a aVar7 = aVar5 == null ? new com.bumptech.glide.load.engine.a(z) : aVar5;
        this.i = aVar7;
        aVar7.f(this);
        this.f3028c = mVar == null ? new m() : mVar;
        this.f3027b = pVar == null ? new p() : pVar;
        this.f3030e = bVar == null ? new b(aVar, aVar2, aVar3, aVar4, this, this) : bVar;
        this.h = aVar6 == null ? new a(cVar) : aVar6;
        this.f = vVar == null ? new v() : vVar;
        hVar.e(this);
    }
}
