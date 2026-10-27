package com.bumptech.glide.load.n;

import com.bumptech.glide.load.n.h;
import com.bumptech.glide.load.n.p;
import com.bumptech.glide.t.l.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
class l<R> implements h.b<R>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c f2163b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final e f2164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.t.l.c f2165d;
    private final p.a e;
    private final a.g.j.e<l<?>> f;
    private final c g;
    private final m h;
    private final com.bumptech.glide.load.n.c0.a i;
    private final com.bumptech.glide.load.n.c0.a j;
    private final com.bumptech.glide.load.n.c0.a k;
    private final com.bumptech.glide.load.n.c0.a l;
    private final AtomicInteger m;
    private com.bumptech.glide.load.g n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private v<?> s;
    com.bumptech.glide.load.a t;
    private boolean u;
    q v;
    private boolean w;
    p<?> x;
    private h<R> y;
    private volatile boolean z;

    private class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.r.g f2166b;

        a(com.bumptech.glide.r.g gVar) {
            this.f2166b = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f2166b.d()) {
                synchronized (l.this) {
                    if (l.this.f2164c.b(this.f2166b)) {
                        l.this.f(this.f2166b);
                    }
                    l.this.i();
                }
            }
        }
    }

    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.r.g f2168b;

        b(com.bumptech.glide.r.g gVar) {
            this.f2168b = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f2168b.d()) {
                synchronized (l.this) {
                    if (l.this.f2164c.b(this.f2168b)) {
                        l.this.x.a();
                        l.this.g(this.f2168b);
                        l.this.r(this.f2168b);
                    }
                    l.this.i();
                }
            }
        }
    }

    static class c {
        c() {
        }

        public <R> p<R> a(v<R> vVar, boolean z, com.bumptech.glide.load.g gVar, p.a aVar) {
            return new p<>(vVar, z, true, gVar, aVar);
        }
    }

    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.r.g f2170a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f2171b;

        d(com.bumptech.glide.r.g gVar, Executor executor) {
            this.f2170a = gVar;
            this.f2171b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.f2170a.equals(((d) obj).f2170a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2170a.hashCode();
        }
    }

    static final class e implements Iterable<d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<d> f2172b;

        e() {
            this(new ArrayList(2));
        }

        e(List<d> list) {
            this.f2172b = list;
        }

        private static d d(com.bumptech.glide.r.g gVar) {
            return new d(gVar, com.bumptech.glide.t.e.a());
        }

        void a(com.bumptech.glide.r.g gVar, Executor executor) {
            this.f2172b.add(new d(gVar, executor));
        }

        boolean b(com.bumptech.glide.r.g gVar) {
            return this.f2172b.contains(d(gVar));
        }

        e c() {
            return new e(new ArrayList(this.f2172b));
        }

        void clear() {
            this.f2172b.clear();
        }

        void e(com.bumptech.glide.r.g gVar) {
            this.f2172b.remove(d(gVar));
        }

        boolean isEmpty() {
            return this.f2172b.isEmpty();
        }

        @Override // java.lang.Iterable
        public Iterator<d> iterator() {
            return this.f2172b.iterator();
        }

        int size() {
            return this.f2172b.size();
        }
    }

    l(com.bumptech.glide.load.n.c0.a aVar, com.bumptech.glide.load.n.c0.a aVar2, com.bumptech.glide.load.n.c0.a aVar3, com.bumptech.glide.load.n.c0.a aVar4, m mVar, p.a aVar5, a.g.j.e<l<?>> eVar) {
        this(aVar, aVar2, aVar3, aVar4, mVar, aVar5, eVar, f2163b);
    }

    l(com.bumptech.glide.load.n.c0.a aVar, com.bumptech.glide.load.n.c0.a aVar2, com.bumptech.glide.load.n.c0.a aVar3, com.bumptech.glide.load.n.c0.a aVar4, m mVar, p.a aVar5, a.g.j.e<l<?>> eVar, c cVar) {
        this.f2164c = new e();
        this.f2165d = com.bumptech.glide.t.l.c.a();
        this.m = new AtomicInteger();
        this.i = aVar;
        this.j = aVar2;
        this.k = aVar3;
        this.l = aVar4;
        this.h = mVar;
        this.e = aVar5;
        this.f = eVar;
        this.g = cVar;
    }

    private com.bumptech.glide.load.n.c0.a j() {
        return this.p ? this.k : this.q ? this.l : this.j;
    }

    private boolean m() {
        return this.w || this.u || this.z;
    }

    private synchronized void q() {
        if (this.n == null) {
            throw new IllegalArgumentException();
        }
        this.f2164c.clear();
        this.n = null;
        this.x = null;
        this.s = null;
        this.w = false;
        this.z = false;
        this.u = false;
        this.y.w(false);
        this.y = null;
        this.v = null;
        this.t = null;
        this.f.a(this);
    }

    @Override // com.bumptech.glide.load.n.h.b
    public void a(q qVar) {
        synchronized (this) {
            this.v = qVar;
        }
        n();
    }

    synchronized void b(com.bumptech.glide.r.g gVar, Executor executor) {
        Runnable aVar;
        this.f2165d.c();
        this.f2164c.a(gVar, executor);
        boolean z = true;
        if (this.u) {
            k(1);
            aVar = new b(gVar);
        } else if (this.w) {
            k(1);
            aVar = new a(gVar);
        } else {
            if (this.z) {
                z = false;
            }
            com.bumptech.glide.t.j.a(z, "Cannot add callbacks to a cancelled EngineJob");
        }
        executor.execute(aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.load.n.h.b
    public void c(v<R> vVar, com.bumptech.glide.load.a aVar) {
        synchronized (this) {
            this.s = vVar;
            this.t = aVar;
        }
        o();
    }

    @Override // com.bumptech.glide.load.n.h.b
    public void d(h<?> hVar) {
        j().execute(hVar);
    }

    @Override // com.bumptech.glide.t.l.a.f
    public com.bumptech.glide.t.l.c e() {
        return this.f2165d;
    }

    void f(com.bumptech.glide.r.g gVar) {
        try {
            gVar.a(this.v);
        } catch (Throwable th) {
            throw new com.bumptech.glide.load.n.b(th);
        }
    }

    void g(com.bumptech.glide.r.g gVar) {
        try {
            gVar.c(this.x, this.t);
        } catch (Throwable th) {
            throw new com.bumptech.glide.load.n.b(th);
        }
    }

    void h() {
        if (m()) {
            return;
        }
        this.z = true;
        this.y.a();
        this.h.d(this, this.n);
    }

    void i() {
        p<?> pVar;
        synchronized (this) {
            this.f2165d.c();
            com.bumptech.glide.t.j.a(m(), "Not yet complete!");
            int iDecrementAndGet = this.m.decrementAndGet();
            com.bumptech.glide.t.j.a(iDecrementAndGet >= 0, "Can't decrement below 0");
            if (iDecrementAndGet == 0) {
                pVar = this.x;
                q();
            } else {
                pVar = null;
            }
        }
        if (pVar != null) {
            pVar.h();
        }
    }

    synchronized void k(int i) {
        p<?> pVar;
        com.bumptech.glide.t.j.a(m(), "Not yet complete!");
        if (this.m.getAndAdd(i) == 0 && (pVar = this.x) != null) {
            pVar.a();
        }
    }

    synchronized l<R> l(com.bumptech.glide.load.g gVar, boolean z, boolean z2, boolean z3, boolean z4) {
        this.n = gVar;
        this.o = z;
        this.p = z2;
        this.q = z3;
        this.r = z4;
        return this;
    }

    void n() {
        synchronized (this) {
            this.f2165d.c();
            if (this.z) {
                q();
                return;
            }
            if (this.f2164c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            if (this.w) {
                throw new IllegalStateException("Already failed once");
            }
            this.w = true;
            com.bumptech.glide.load.g gVar = this.n;
            e eVarC = this.f2164c.c();
            k(eVarC.size() + 1);
            this.h.c(this, gVar, null);
            for (d dVar : eVarC) {
                dVar.f2171b.execute(new a(dVar.f2170a));
            }
            i();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    void o() {
        synchronized (this) {
            this.f2165d.c();
            if (this.z) {
                this.s.f();
                q();
                return;
            }
            if (this.f2164c.isEmpty()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (this.u) {
                throw new IllegalStateException("Already have resource");
            }
            this.x = this.g.a(this.s, this.o, this.n, this.e);
            this.u = true;
            e eVarC = this.f2164c.c();
            k(eVarC.size() + 1);
            this.h.c(this, this.n, this.x);
            for (d dVar : eVarC) {
                dVar.f2171b.execute(new b(dVar.f2170a));
            }
            i();
        }
    }

    boolean p() {
        return this.r;
    }

    synchronized void r(com.bumptech.glide.r.g gVar) {
        this.f2165d.c();
        this.f2164c.e(gVar);
        if (this.f2164c.isEmpty()) {
            h();
            if ((this.u || this.w) && this.m.get() == 0) {
                q();
            }
        }
    }

    public synchronized void s(h<R> hVar) {
        this.y = hVar;
        (hVar.C() ? this.i : j()).execute(hVar);
    }
}
