package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.o.l.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: EngineJob.java */
/* JADX INFO: loaded from: classes.dex */
class j<R> implements DecodeJob.b<R>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c f3046b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final e f3047c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.o.l.c f3048d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final n.a f3049e;
    private final b.g.j.e<j<?>> f;
    private final c g;
    private final k h;
    private final com.bumptech.glide.load.engine.z.a i;
    private final com.bumptech.glide.load.engine.z.a j;
    private final com.bumptech.glide.load.engine.z.a k;
    private final com.bumptech.glide.load.engine.z.a l;
    private final AtomicInteger m;
    private com.bumptech.glide.load.c n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private s<?> s;
    DataSource t;
    private boolean u;
    GlideException v;
    private boolean w;
    n<?> x;
    private DecodeJob<R> y;
    private volatile boolean z;

    /* JADX INFO: compiled from: EngineJob.java */
    private class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.request.f f3050b;

        a(com.bumptech.glide.request.f fVar) {
            this.f3050b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f3050b.d()) {
                synchronized (j.this) {
                    if (j.this.f3047c.b(this.f3050b)) {
                        j.this.f(this.f3050b);
                    }
                    j.this.i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.request.f f3052b;

        b(com.bumptech.glide.request.f fVar) {
            this.f3052b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f3052b.d()) {
                synchronized (j.this) {
                    if (j.this.f3047c.b(this.f3052b)) {
                        j.this.x.b();
                        j.this.g(this.f3052b);
                        j.this.r(this.f3052b);
                    }
                    j.this.i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static class c {
        c() {
        }

        public <R> n<R> a(s<R> sVar, boolean z, com.bumptech.glide.load.c cVar, n.a aVar) {
            return new n<>(sVar, z, true, cVar, aVar);
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final com.bumptech.glide.request.f f3054a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f3055b;

        d(com.bumptech.glide.request.f fVar, Executor executor) {
            this.f3054a = fVar;
            this.f3055b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.f3054a.equals(((d) obj).f3054a);
            }
            return false;
        }

        public int hashCode() {
            return this.f3054a.hashCode();
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static final class e implements Iterable<d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<d> f3056b;

        e() {
            this(new ArrayList(2));
        }

        private static d d(com.bumptech.glide.request.f fVar) {
            return new d(fVar, com.bumptech.glide.o.e.a());
        }

        void a(com.bumptech.glide.request.f fVar, Executor executor) {
            this.f3056b.add(new d(fVar, executor));
        }

        boolean b(com.bumptech.glide.request.f fVar) {
            return this.f3056b.contains(d(fVar));
        }

        e c() {
            return new e(new ArrayList(this.f3056b));
        }

        void clear() {
            this.f3056b.clear();
        }

        void e(com.bumptech.glide.request.f fVar) {
            this.f3056b.remove(d(fVar));
        }

        boolean isEmpty() {
            return this.f3056b.isEmpty();
        }

        @Override // java.lang.Iterable
        public Iterator<d> iterator() {
            return this.f3056b.iterator();
        }

        int size() {
            return this.f3056b.size();
        }

        e(List<d> list) {
            this.f3056b = list;
        }
    }

    j(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5, b.g.j.e<j<?>> eVar) {
        this(aVar, aVar2, aVar3, aVar4, kVar, aVar5, eVar, f3046b);
    }

    private com.bumptech.glide.load.engine.z.a j() {
        return this.p ? this.k : this.q ? this.l : this.j;
    }

    private boolean m() {
        return this.w || this.u || this.z;
    }

    private synchronized void q() {
        if (this.n == null) {
            throw new IllegalArgumentException();
        }
        this.f3047c.clear();
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

    @Override // com.bumptech.glide.load.engine.DecodeJob.b
    public void a(GlideException glideException) {
        synchronized (this) {
            this.v = glideException;
        }
        n();
    }

    synchronized void b(com.bumptech.glide.request.f fVar, Executor executor) {
        this.f3048d.c();
        this.f3047c.a(fVar, executor);
        boolean z = true;
        if (this.u) {
            k(1);
            executor.execute(new b(fVar));
        } else if (this.w) {
            k(1);
            executor.execute(new a(fVar));
        } else {
            if (this.z) {
                z = false;
            }
            com.bumptech.glide.o.j.a(z, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.load.engine.DecodeJob.b
    public void c(s<R> sVar, DataSource dataSource) {
        synchronized (this) {
            this.s = sVar;
            this.t = dataSource;
        }
        o();
    }

    @Override // com.bumptech.glide.load.engine.DecodeJob.b
    public void d(DecodeJob<?> decodeJob) {
        j().execute(decodeJob);
    }

    @Override // com.bumptech.glide.o.l.a.f
    public com.bumptech.glide.o.l.c e() {
        return this.f3048d;
    }

    void f(com.bumptech.glide.request.f fVar) {
        try {
            fVar.a(this.v);
        } catch (Throwable th) {
            throw new CallbackException(th);
        }
    }

    void g(com.bumptech.glide.request.f fVar) {
        try {
            fVar.c(this.x, this.t);
        } catch (Throwable th) {
            throw new CallbackException(th);
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
        n<?> nVar;
        synchronized (this) {
            this.f3048d.c();
            com.bumptech.glide.o.j.a(m(), "Not yet complete!");
            int iDecrementAndGet = this.m.decrementAndGet();
            com.bumptech.glide.o.j.a(iDecrementAndGet >= 0, "Can't decrement below 0");
            if (iDecrementAndGet == 0) {
                nVar = this.x;
                q();
            } else {
                nVar = null;
            }
        }
        if (nVar != null) {
            nVar.g();
        }
    }

    synchronized void k(int i) {
        n<?> nVar;
        com.bumptech.glide.o.j.a(m(), "Not yet complete!");
        if (this.m.getAndAdd(i) == 0 && (nVar = this.x) != null) {
            nVar.b();
        }
    }

    synchronized j<R> l(com.bumptech.glide.load.c cVar, boolean z, boolean z2, boolean z3, boolean z4) {
        this.n = cVar;
        this.o = z;
        this.p = z2;
        this.q = z3;
        this.r = z4;
        return this;
    }

    void n() {
        synchronized (this) {
            this.f3048d.c();
            if (this.z) {
                q();
                return;
            }
            if (this.f3047c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            if (this.w) {
                throw new IllegalStateException("Already failed once");
            }
            this.w = true;
            com.bumptech.glide.load.c cVar = this.n;
            e eVarC = this.f3047c.c();
            k(eVarC.size() + 1);
            this.h.c(this, cVar, null);
            for (d dVar : eVarC) {
                dVar.f3055b.execute(new a(dVar.f3054a));
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
            this.f3048d.c();
            if (this.z) {
                this.s.a();
                q();
                return;
            }
            if (this.f3047c.isEmpty()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (this.u) {
                throw new IllegalStateException("Already have resource");
            }
            this.x = this.g.a(this.s, this.o, this.n, this.f3049e);
            this.u = true;
            e eVarC = this.f3047c.c();
            k(eVarC.size() + 1);
            this.h.c(this, this.n, this.x);
            for (d dVar : eVarC) {
                dVar.f3055b.execute(new b(dVar.f3054a));
            }
            i();
        }
    }

    boolean p() {
        return this.r;
    }

    synchronized void r(com.bumptech.glide.request.f fVar) {
        this.f3048d.c();
        this.f3047c.e(fVar);
        if (this.f3047c.isEmpty()) {
            h();
            if ((this.u || this.w) && this.m.get() == 0) {
                q();
            }
        }
    }

    public synchronized void s(DecodeJob<R> decodeJob) {
        this.y = decodeJob;
        (decodeJob.C() ? this.i : j()).execute(decodeJob);
    }

    j(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5, b.g.j.e<j<?>> eVar, c cVar) {
        this.f3047c = new e();
        this.f3048d = com.bumptech.glide.o.l.c.a();
        this.m = new AtomicInteger();
        this.i = aVar;
        this.j = aVar2;
        this.k = aVar3;
        this.l = aVar4;
        this.h = kVar;
        this.f3049e = aVar5;
        this.f = eVar;
        this.g = cVar;
    }
}
