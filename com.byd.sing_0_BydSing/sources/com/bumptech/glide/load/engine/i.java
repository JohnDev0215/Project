package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.load.engine.y.a;
import com.bumptech.glide.load.engine.y.h;
import com.bumptech.glide.m.l.a;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Engine.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements k, h.a, n.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f2788a = Log.isLoggable("Engine", 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final p f2789b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final m f2790c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.y.h f2791d;
    private final b e;
    private final v f;
    private final c g;
    private final a h;
    private final com.bumptech.glide.load.engine.a i;

    /* JADX INFO: compiled from: Engine.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final DecodeJob.e f2792a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.g.j.e<DecodeJob<?>> f2793b = com.bumptech.glide.m.l.a.d(150, new C0088a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2794c;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.i$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Engine.java */
        class C0088a implements a.d<DecodeJob<?>> {
            C0088a() {
            }

            @Override // com.bumptech.glide.m.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public DecodeJob<?> a() {
                a aVar = a.this;
                return new DecodeJob<>(aVar.f2792a, aVar.f2793b);
            }
        }

        a(DecodeJob.e eVar) {
            this.f2792a = eVar;
        }

        <R> DecodeJob<R> a(com.bumptech.glide.d dVar, Object obj, l lVar, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.e eVar, DecodeJob.b<R> bVar) {
            DecodeJob decodeJob = (DecodeJob) com.bumptech.glide.m.j.d(this.f2793b.b());
            int i3 = this.f2794c;
            this.f2794c = i3 + 1;
            return decodeJob.n(dVar, obj, lVar, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, z3, eVar, bVar, i3);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f2796a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f2797b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f2798c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final com.bumptech.glide.load.engine.z.a f2799d;
        final k e;
        final n.a f;
        final c.g.j.e<j<?>> g = com.bumptech.glide.m.l.a.d(150, new a());

        /* JADX INFO: compiled from: Engine.java */
        class a implements a.d<j<?>> {
            a() {
            }

            @Override // com.bumptech.glide.m.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public j<?> a() {
                b bVar = b.this;
                return new j<>(bVar.f2796a, bVar.f2797b, bVar.f2798c, bVar.f2799d, bVar.e, bVar.f, bVar.g);
            }
        }

        b(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5) {
            this.f2796a = aVar;
            this.f2797b = aVar2;
            this.f2798c = aVar3;
            this.f2799d = aVar4;
            this.e = kVar;
            this.f = aVar5;
        }

        <R> j<R> a(com.bumptech.glide.load.c cVar, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((j) com.bumptech.glide.m.j.d(this.g.b())).l(cVar, z, z2, z3, z4);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    private static class c implements DecodeJob.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.InterfaceC0089a f2801a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile com.bumptech.glide.load.engine.y.a f2802b;

        c(a.InterfaceC0089a interfaceC0089a) {
            this.f2801a = interfaceC0089a;
        }

        @Override // com.bumptech.glide.load.engine.DecodeJob.e
        public com.bumptech.glide.load.engine.y.a a() {
            if (this.f2802b == null) {
                synchronized (this) {
                    if (this.f2802b == null) {
                        this.f2802b = this.f2801a.a();
                    }
                    if (this.f2802b == null) {
                        this.f2802b = new com.bumptech.glide.load.engine.y.b();
                    }
                }
            }
            return this.f2802b;
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final j<?> f2803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.request.f f2804b;

        d(com.bumptech.glide.request.f fVar, j<?> jVar) {
            this.f2804b = fVar;
            this.f2803a = jVar;
        }

        public void a() {
            synchronized (i.this) {
                this.f2803a.r(this.f2804b);
            }
        }
    }

    public i(com.bumptech.glide.load.engine.y.h hVar, a.InterfaceC0089a interfaceC0089a, com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, boolean z) {
        this(hVar, interfaceC0089a, aVar, aVar2, aVar3, aVar4, null, null, null, null, null, null, z);
    }

    private n<?> e(com.bumptech.glide.load.c cVar) {
        s<?> sVarE = this.f2791d.e(cVar);
        if (sVarE == null) {
            return null;
        }
        return sVarE instanceof n ? (n) sVarE : new n<>(sVarE, true, true, cVar, this);
    }

    private n<?> g(com.bumptech.glide.load.c cVar) {
        n<?> nVarE = this.i.e(cVar);
        if (nVarE != null) {
            nVarE.d();
        }
        return nVarE;
    }

    private n<?> h(com.bumptech.glide.load.c cVar) {
        n<?> nVarE = e(cVar);
        if (nVarE != null) {
            nVarE.d();
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
            if (f2788a) {
                j("Loaded resource from active resources", j, lVar);
            }
            return nVarG;
        }
        n<?> nVarH = h(lVar);
        if (nVarH == null) {
            return null;
        }
        if (f2788a) {
            j("Loaded resource from cache", j, lVar);
        }
        return nVarH;
    }

    private static void j(String str, long j, com.bumptech.glide.load.c cVar) {
        Log.v("Engine", str + " in " + com.bumptech.glide.m.f.a(j) + "ms, key: " + cVar);
    }

    private <R> d l(com.bumptech.glide.d dVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, com.bumptech.glide.load.e eVar, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.request.f fVar, Executor executor, l lVar, long j) {
        j<?> jVarA = this.f2789b.a(lVar, z6);
        if (jVarA != null) {
            jVarA.b(fVar, executor);
            if (f2788a) {
                j("Added to existing load", j, lVar);
            }
            return new d(fVar, jVarA);
        }
        j<R> jVarA2 = this.e.a(lVar, z3, z4, z5, z6);
        DecodeJob<R> decodeJobA = this.h.a(dVar, obj, lVar, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, z6, eVar, jVarA2);
        this.f2789b.c(lVar, jVarA2);
        jVarA2.b(fVar, executor);
        jVarA2.s(decodeJobA);
        if (f2788a) {
            j("Started new load", j, lVar);
        }
        return new d(fVar, jVarA2);
    }

    @Override // com.bumptech.glide.load.engine.y.h.a
    public void a(s<?> sVar) {
        this.f.a(sVar, true);
    }

    @Override // com.bumptech.glide.load.engine.k
    public synchronized void b(j<?> jVar, com.bumptech.glide.load.c cVar, n<?> nVar) {
        if (nVar != null) {
            if (nVar.f()) {
                this.i.a(cVar, nVar);
            }
            this.f2789b.d(cVar, jVar);
        } else {
            this.f2789b.d(cVar, jVar);
        }
    }

    @Override // com.bumptech.glide.load.engine.k
    public synchronized void c(j<?> jVar, com.bumptech.glide.load.c cVar) {
        this.f2789b.d(cVar, jVar);
    }

    @Override // com.bumptech.glide.load.engine.n.a
    public void d(com.bumptech.glide.load.c cVar, n<?> nVar) {
        this.i.d(cVar);
        if (nVar.f()) {
            this.f2791d.d(cVar, nVar);
        } else {
            this.f.a(nVar, false);
        }
    }

    public <R> d f(com.bumptech.glide.d dVar, Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, com.bumptech.glide.load.e eVar, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.request.f fVar, Executor executor) {
        long jB = f2788a ? com.bumptech.glide.m.f.b() : 0L;
        l lVarA = this.f2790c.a(obj, cVar, i, i2, map, cls, cls2, eVar);
        synchronized (this) {
            n<?> nVarI = i(lVarA, z3, jB);
            if (nVarI == null) {
                return l(dVar, obj, cVar, i, i2, cls, cls2, priority, hVar, map, z, z2, eVar, z3, z4, z5, z6, fVar, executor, lVarA, jB);
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

    i(com.bumptech.glide.load.engine.y.h hVar, a.InterfaceC0089a interfaceC0089a, com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, p pVar, m mVar, com.bumptech.glide.load.engine.a aVar5, b bVar, a aVar6, v vVar, boolean z) {
        this.f2791d = hVar;
        c cVar = new c(interfaceC0089a);
        this.g = cVar;
        com.bumptech.glide.load.engine.a aVar7 = aVar5 == null ? new com.bumptech.glide.load.engine.a(z) : aVar5;
        this.i = aVar7;
        aVar7.f(this);
        this.f2790c = mVar == null ? new m() : mVar;
        this.f2789b = pVar == null ? new p() : pVar;
        this.e = bVar == null ? new b(aVar, aVar2, aVar3, aVar4, this, this) : bVar;
        this.h = aVar6 == null ? new a(cVar) : aVar6;
        this.f = vVar == null ? new v() : vVar;
        hVar.c(this);
    }
}
