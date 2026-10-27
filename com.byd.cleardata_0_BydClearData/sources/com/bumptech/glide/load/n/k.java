package com.bumptech.glide.load.n;

import android.util.Log;
import com.bumptech.glide.load.n.b0.a;
import com.bumptech.glide.load.n.b0.h;
import com.bumptech.glide.load.n.h;
import com.bumptech.glide.load.n.p;
import com.bumptech.glide.t.l.a;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class k implements m, h.a, p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f2145a = Log.isLoggable("Engine", 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final s f2146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final o f2147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.n.b0.h f2148d;
    private final b e;
    private final y f;
    private final c g;
    private final a h;
    private final com.bumptech.glide.load.n.a i;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h.e f2149a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final a.g.j.e<h<?>> f2150b = com.bumptech.glide.t.l.a.d(150, new C0073a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2151c;

        /* JADX INFO: renamed from: com.bumptech.glide.load.n.k$a$a, reason: collision with other inner class name */
        class C0073a implements a.d<h<?>> {
            C0073a() {
            }

            @Override // com.bumptech.glide.t.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public h<?> a() {
                a aVar = a.this;
                return new h<>(aVar.f2149a, aVar.f2150b);
            }
        }

        a(h.e eVar) {
            this.f2149a = eVar;
        }

        <R> h<R> a(com.bumptech.glide.e eVar, Object obj, n nVar, com.bumptech.glide.load.g gVar, int i, int i2, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar2, j jVar, Map<Class<?>, com.bumptech.glide.load.m<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.i iVar, h.b<R> bVar) {
            h hVar = (h) com.bumptech.glide.t.j.d(this.f2150b.b());
            int i3 = this.f2151c;
            this.f2151c = i3 + 1;
            return hVar.n(eVar, obj, nVar, gVar, i, i2, cls, cls2, gVar2, jVar, map, z, z2, z3, iVar, bVar, i3);
        }
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.load.n.c0.a f2153a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.n.c0.a f2154b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final com.bumptech.glide.load.n.c0.a f2155c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final com.bumptech.glide.load.n.c0.a f2156d;
        final m e;
        final p.a f;
        final a.g.j.e<l<?>> g = com.bumptech.glide.t.l.a.d(150, new a());

        class a implements a.d<l<?>> {
            a() {
            }

            @Override // com.bumptech.glide.t.l.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public l<?> a() {
                b bVar = b.this;
                return new l<>(bVar.f2153a, bVar.f2154b, bVar.f2155c, bVar.f2156d, bVar.e, bVar.f, bVar.g);
            }
        }

        b(com.bumptech.glide.load.n.c0.a aVar, com.bumptech.glide.load.n.c0.a aVar2, com.bumptech.glide.load.n.c0.a aVar3, com.bumptech.glide.load.n.c0.a aVar4, m mVar, p.a aVar5) {
            this.f2153a = aVar;
            this.f2154b = aVar2;
            this.f2155c = aVar3;
            this.f2156d = aVar4;
            this.e = mVar;
            this.f = aVar5;
        }

        <R> l<R> a(com.bumptech.glide.load.g gVar, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((l) com.bumptech.glide.t.j.d(this.g.b())).l(gVar, z, z2, z3, z4);
        }
    }

    private static class c implements h.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.InterfaceC0067a f2158a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile com.bumptech.glide.load.n.b0.a f2159b;

        c(a.InterfaceC0067a interfaceC0067a) {
            this.f2158a = interfaceC0067a;
        }

        @Override // com.bumptech.glide.load.n.h.e
        public com.bumptech.glide.load.n.b0.a a() {
            if (this.f2159b == null) {
                synchronized (this) {
                    if (this.f2159b == null) {
                        this.f2159b = this.f2158a.a();
                    }
                    if (this.f2159b == null) {
                        this.f2159b = new com.bumptech.glide.load.n.b0.b();
                    }
                }
            }
            return this.f2159b;
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final l<?> f2160a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.r.g f2161b;

        d(com.bumptech.glide.r.g gVar, l<?> lVar) {
            this.f2161b = gVar;
            this.f2160a = lVar;
        }

        public void a() {
            synchronized (k.this) {
                this.f2160a.r(this.f2161b);
            }
        }
    }

    k(com.bumptech.glide.load.n.b0.h hVar, a.InterfaceC0067a interfaceC0067a, com.bumptech.glide.load.n.c0.a aVar, com.bumptech.glide.load.n.c0.a aVar2, com.bumptech.glide.load.n.c0.a aVar3, com.bumptech.glide.load.n.c0.a aVar4, s sVar, o oVar, com.bumptech.glide.load.n.a aVar5, b bVar, a aVar6, y yVar, boolean z) {
        this.f2148d = hVar;
        c cVar = new c(interfaceC0067a);
        this.g = cVar;
        com.bumptech.glide.load.n.a aVar7 = aVar5 == null ? new com.bumptech.glide.load.n.a(z) : aVar5;
        this.i = aVar7;
        aVar7.f(this);
        this.f2147c = oVar == null ? new o() : oVar;
        this.f2146b = sVar == null ? new s() : sVar;
        this.e = bVar == null ? new b(aVar, aVar2, aVar3, aVar4, this, this) : bVar;
        this.h = aVar6 == null ? new a(cVar) : aVar6;
        this.f = yVar == null ? new y() : yVar;
        hVar.e(this);
    }

    public k(com.bumptech.glide.load.n.b0.h hVar, a.InterfaceC0067a interfaceC0067a, com.bumptech.glide.load.n.c0.a aVar, com.bumptech.glide.load.n.c0.a aVar2, com.bumptech.glide.load.n.c0.a aVar3, com.bumptech.glide.load.n.c0.a aVar4, boolean z) {
        this(hVar, interfaceC0067a, aVar, aVar2, aVar3, aVar4, null, null, null, null, null, null, z);
    }

    private p<?> e(com.bumptech.glide.load.g gVar) {
        v<?> vVarD = this.f2148d.d(gVar);
        if (vVarD == null) {
            return null;
        }
        return vVarD instanceof p ? (p) vVarD : new p<>(vVarD, true, true, gVar, this);
    }

    private p<?> g(com.bumptech.glide.load.g gVar) {
        p<?> pVarE = this.i.e(gVar);
        if (pVarE != null) {
            pVarE.a();
        }
        return pVarE;
    }

    private p<?> h(com.bumptech.glide.load.g gVar) {
        p<?> pVarE = e(gVar);
        if (pVarE != null) {
            pVarE.a();
            this.i.a(gVar, pVarE);
        }
        return pVarE;
    }

    private p<?> i(n nVar, boolean z, long j) {
        if (!z) {
            return null;
        }
        p<?> pVarG = g(nVar);
        if (pVarG != null) {
            if (f2145a) {
                j("Loaded resource from active resources", j, nVar);
            }
            return pVarG;
        }
        p<?> pVarH = h(nVar);
        if (pVarH == null) {
            return null;
        }
        if (f2145a) {
            j("Loaded resource from cache", j, nVar);
        }
        return pVarH;
    }

    private static void j(String str, long j, com.bumptech.glide.load.g gVar) {
        Log.v("Engine", str + " in " + com.bumptech.glide.t.f.a(j) + "ms, key: " + gVar);
    }

    private <R> d l(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.g gVar, int i, int i2, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar2, j jVar, Map<Class<?>, com.bumptech.glide.load.m<?>> map, boolean z, boolean z2, com.bumptech.glide.load.i iVar, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.r.g gVar3, Executor executor, n nVar, long j) {
        l<?> lVarA = this.f2146b.a(nVar, z6);
        if (lVarA != null) {
            lVarA.b(gVar3, executor);
            if (f2145a) {
                j("Added to existing load", j, nVar);
            }
            return new d(gVar3, lVarA);
        }
        l<R> lVarA2 = this.e.a(nVar, z3, z4, z5, z6);
        h<R> hVarA = this.h.a(eVar, obj, nVar, gVar, i, i2, cls, cls2, gVar2, jVar, map, z, z2, z6, iVar, lVarA2);
        this.f2146b.c(nVar, lVarA2);
        lVarA2.b(gVar3, executor);
        lVarA2.s(hVarA);
        if (f2145a) {
            j("Started new load", j, nVar);
        }
        return new d(gVar3, lVarA2);
    }

    @Override // com.bumptech.glide.load.n.p.a
    public void a(com.bumptech.glide.load.g gVar, p<?> pVar) {
        this.i.d(gVar);
        if (pVar.g()) {
            this.f2148d.c(gVar, pVar);
        } else {
            this.f.a(pVar, false);
        }
    }

    @Override // com.bumptech.glide.load.n.b0.h.a
    public void b(v<?> vVar) {
        this.f.a(vVar, true);
    }

    @Override // com.bumptech.glide.load.n.m
    public synchronized void c(l<?> lVar, com.bumptech.glide.load.g gVar, p<?> pVar) {
        if (pVar != null) {
            if (pVar.g()) {
                this.i.a(gVar, pVar);
            }
            this.f2146b.d(gVar, lVar);
        } else {
            this.f2146b.d(gVar, lVar);
        }
    }

    @Override // com.bumptech.glide.load.n.m
    public synchronized void d(l<?> lVar, com.bumptech.glide.load.g gVar) {
        this.f2146b.d(gVar, lVar);
    }

    public <R> d f(com.bumptech.glide.e eVar, Object obj, com.bumptech.glide.load.g gVar, int i, int i2, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar2, j jVar, Map<Class<?>, com.bumptech.glide.load.m<?>> map, boolean z, boolean z2, com.bumptech.glide.load.i iVar, boolean z3, boolean z4, boolean z5, boolean z6, com.bumptech.glide.r.g gVar3, Executor executor) {
        long jB = f2145a ? com.bumptech.glide.t.f.b() : 0L;
        n nVarA = this.f2147c.a(obj, gVar, i, i2, map, cls, cls2, iVar);
        synchronized (this) {
            p<?> pVarI = i(nVarA, z3, jB);
            if (pVarI == null) {
                return l(eVar, obj, gVar, i, i2, cls, cls2, gVar2, jVar, map, z, z2, iVar, z3, z4, z5, z6, gVar3, executor, nVarA, jB);
            }
            gVar3.c(pVarI, com.bumptech.glide.load.a.MEMORY_CACHE);
            return null;
        }
    }

    public void k(v<?> vVar) {
        if (!(vVar instanceof p)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((p) vVar).h();
    }
}
