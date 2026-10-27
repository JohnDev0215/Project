package q1;

import android.util.Log;
import java.util.Map;
import java.util.concurrent.Executor;
import l2.a;
import q1.h;
import q1.p;
import s1.a;
import s1.h;

/* JADX INFO: compiled from: Engine.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements m, h.a, p.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final boolean f9026i = Log.isLoggable("Engine", 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final s f9027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final o f9028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final s1.h f9029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b f9030d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final y f9031e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c f9032f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final a f9033g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final q1.a f9034h;

    /* JADX INFO: compiled from: Engine.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h.e f9035a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final h0.e<h<?>> f9036b = l2.a.d(150, new C0140a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f9037c;

        /* JADX INFO: renamed from: q1.k$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Engine.java */
        class C0140a implements a.d<h<?>> {
            C0140a() {
            }

            @Override // l2.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public h<?> a() {
                a aVar = a.this;
                return new h<>(aVar.f9035a, aVar.f9036b);
            }
        }

        a(h.e eVar) {
            this.f9035a = eVar;
        }

        <R> h<R> a(com.bumptech.glide.e eVar, Object obj, n nVar, n1.f fVar, int i7, int i8, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar, j jVar, Map<Class<?>, n1.m<?>> map, boolean z6, boolean z7, boolean z8, n1.i iVar, h.b<R> bVar) {
            h hVar = (h) k2.j.d(this.f9036b.b());
            int i9 = this.f9037c;
            this.f9037c = i9 + 1;
            return hVar.n(eVar, obj, nVar, fVar, i7, i8, cls, cls2, gVar, jVar, map, z6, z7, z8, iVar, bVar, i9);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final t1.a f9039a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final t1.a f9040b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final t1.a f9041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final t1.a f9042d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final m f9043e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final p.a f9044f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final h0.e<l<?>> f9045g = l2.a.d(150, new a());

        /* JADX INFO: compiled from: Engine.java */
        class a implements a.d<l<?>> {
            a() {
            }

            @Override // l2.a.d
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public l<?> a() {
                b bVar = b.this;
                return new l<>(bVar.f9039a, bVar.f9040b, bVar.f9041c, bVar.f9042d, bVar.f9043e, bVar.f9044f, bVar.f9045g);
            }
        }

        b(t1.a aVar, t1.a aVar2, t1.a aVar3, t1.a aVar4, m mVar, p.a aVar5) {
            this.f9039a = aVar;
            this.f9040b = aVar2;
            this.f9041c = aVar3;
            this.f9042d = aVar4;
            this.f9043e = mVar;
            this.f9044f = aVar5;
        }

        <R> l<R> a(n1.f fVar, boolean z6, boolean z7, boolean z8, boolean z9) {
            return ((l) k2.j.d(this.f9045g.b())).l(fVar, z6, z7, z8, z9);
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    private static class c implements h.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.InterfaceC0144a f9047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile s1.a f9048b;

        c(a.InterfaceC0144a interfaceC0144a) {
            this.f9047a = interfaceC0144a;
        }

        @Override // q1.h.e
        public s1.a a() {
            if (this.f9048b == null) {
                synchronized (this) {
                    if (this.f9048b == null) {
                        this.f9048b = this.f9047a.a();
                    }
                    if (this.f9048b == null) {
                        this.f9048b = new s1.b();
                    }
                }
            }
            return this.f9048b;
        }
    }

    /* JADX INFO: compiled from: Engine.java */
    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final l<?> f9049a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final g2.g f9050b;

        d(g2.g gVar, l<?> lVar) {
            this.f9050b = gVar;
            this.f9049a = lVar;
        }

        public void a() {
            synchronized (k.this) {
                this.f9049a.r(this.f9050b);
            }
        }
    }

    public k(s1.h hVar, a.InterfaceC0144a interfaceC0144a, t1.a aVar, t1.a aVar2, t1.a aVar3, t1.a aVar4, boolean z6) {
        this(hVar, interfaceC0144a, aVar, aVar2, aVar3, aVar4, null, null, null, null, null, null, z6);
    }

    private p<?> e(n1.f fVar) {
        v<?> vVarC = this.f9029c.c(fVar);
        if (vVarC == null) {
            return null;
        }
        return vVarC instanceof p ? (p) vVarC : new p<>(vVarC, true, true, fVar, this);
    }

    private p<?> g(n1.f fVar) {
        p<?> pVarE = this.f9034h.e(fVar);
        if (pVarE != null) {
            pVarE.b();
        }
        return pVarE;
    }

    private p<?> h(n1.f fVar) {
        p<?> pVarE = e(fVar);
        if (pVarE != null) {
            pVarE.b();
            this.f9034h.a(fVar, pVarE);
        }
        return pVarE;
    }

    private p<?> i(n nVar, boolean z6, long j7) {
        if (!z6) {
            return null;
        }
        p<?> pVarG = g(nVar);
        if (pVarG != null) {
            if (f9026i) {
                j("Loaded resource from active resources", j7, nVar);
            }
            return pVarG;
        }
        p<?> pVarH = h(nVar);
        if (pVarH == null) {
            return null;
        }
        if (f9026i) {
            j("Loaded resource from cache", j7, nVar);
        }
        return pVarH;
    }

    private static void j(String str, long j7, n1.f fVar) {
        Log.v("Engine", str + " in " + k2.f.a(j7) + "ms, key: " + fVar);
    }

    private <R> d l(com.bumptech.glide.e eVar, Object obj, n1.f fVar, int i7, int i8, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar, j jVar, Map<Class<?>, n1.m<?>> map, boolean z6, boolean z7, n1.i iVar, boolean z8, boolean z9, boolean z10, boolean z11, g2.g gVar2, Executor executor, n nVar, long j7) {
        l<?> lVarA = this.f9027a.a(nVar, z11);
        if (lVarA != null) {
            lVarA.e(gVar2, executor);
            if (f9026i) {
                j("Added to existing load", j7, nVar);
            }
            return new d(gVar2, lVarA);
        }
        l<R> lVarA2 = this.f9030d.a(nVar, z8, z9, z10, z11);
        h<R> hVarA = this.f9033g.a(eVar, obj, nVar, fVar, i7, i8, cls, cls2, gVar, jVar, map, z6, z7, z11, iVar, lVarA2);
        this.f9027a.c(nVar, lVarA2);
        lVarA2.e(gVar2, executor);
        lVarA2.s(hVarA);
        if (f9026i) {
            j("Started new load", j7, nVar);
        }
        return new d(gVar2, lVarA2);
    }

    @Override // q1.m
    public synchronized void a(l<?> lVar, n1.f fVar) {
        this.f9027a.d(fVar, lVar);
    }

    @Override // q1.p.a
    public void b(n1.f fVar, p<?> pVar) {
        this.f9034h.d(fVar);
        if (pVar.f()) {
            this.f9029c.d(fVar, pVar);
        } else {
            this.f9031e.a(pVar);
        }
    }

    @Override // q1.m
    public synchronized void c(l<?> lVar, n1.f fVar, p<?> pVar) {
        if (pVar != null) {
            if (pVar.f()) {
                this.f9034h.a(fVar, pVar);
            }
            this.f9027a.d(fVar, lVar);
        } else {
            this.f9027a.d(fVar, lVar);
        }
    }

    @Override // s1.h.a
    public void d(v<?> vVar) {
        this.f9031e.a(vVar);
    }

    public <R> d f(com.bumptech.glide.e eVar, Object obj, n1.f fVar, int i7, int i8, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar, j jVar, Map<Class<?>, n1.m<?>> map, boolean z6, boolean z7, n1.i iVar, boolean z8, boolean z9, boolean z10, boolean z11, g2.g gVar2, Executor executor) {
        long jB = f9026i ? k2.f.b() : 0L;
        n nVarA = this.f9028b.a(obj, fVar, i7, i8, map, cls, cls2, iVar);
        synchronized (this) {
            p<?> pVarI = i(nVarA, z8, jB);
            if (pVarI == null) {
                return l(eVar, obj, fVar, i7, i8, cls, cls2, gVar, jVar, map, z6, z7, iVar, z8, z9, z10, z11, gVar2, executor, nVarA, jB);
            }
            gVar2.a(pVarI, n1.a.MEMORY_CACHE);
            return null;
        }
    }

    public void k(v<?> vVar) {
        if (!(vVar instanceof p)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((p) vVar).g();
    }

    k(s1.h hVar, a.InterfaceC0144a interfaceC0144a, t1.a aVar, t1.a aVar2, t1.a aVar3, t1.a aVar4, s sVar, o oVar, q1.a aVar5, b bVar, a aVar6, y yVar, boolean z6) {
        this.f9029c = hVar;
        c cVar = new c(interfaceC0144a);
        this.f9032f = cVar;
        q1.a aVar7 = aVar5 == null ? new q1.a(z6) : aVar5;
        this.f9034h = aVar7;
        aVar7.f(this);
        this.f9028b = oVar == null ? new o() : oVar;
        this.f9027a = sVar == null ? new s() : sVar;
        this.f9030d = bVar == null ? new b(aVar, aVar2, aVar3, aVar4, this, this) : bVar;
        this.f9033g = aVar6 == null ? new a(cVar) : aVar6;
        this.f9031e = yVar == null ? new y() : yVar;
        hVar.e(this);
    }
}
