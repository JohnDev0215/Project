package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.n;
import com.bumptech.glide.m.l.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: EngineJob.java */
/* JADX INFO: loaded from: classes.dex */
class j<R> implements DecodeJob.b<R>, a.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c f2806b = new c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final e f2807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.m.l.c f2808d;
    private final n.a e;
    private final c.g.j.e<j<?>> f;
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
        private final com.bumptech.glide.request.f f2809b;

        a(com.bumptech.glide.request.f fVar) {
            this.f2809b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f2809b.f()) {
                synchronized (j.this) {
                    if (j.this.f2807c.b(this.f2809b)) {
                        j.this.f(this.f2809b);
                    }
                    j.this.i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.request.f f2811b;

        b(com.bumptech.glide.request.f fVar) {
            this.f2811b = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f2811b.f()) {
                synchronized (j.this) {
                    if (j.this.f2807c.b(this.f2811b)) {
                        j.this.x.d();
                        j.this.g(this.f2811b);
                        j.this.r(this.f2811b);
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
        final com.bumptech.glide.request.f f2813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f2814b;

        d(com.bumptech.glide.request.f fVar, Executor executor) {
            this.f2813a = fVar;
            this.f2814b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.f2813a.equals(((d) obj).f2813a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2813a.hashCode();
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static final class e implements Iterable<d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<d> f2815b;

        e() {
            this(new ArrayList(2));
        }

        private static d d(com.bumptech.glide.request.f fVar) {
            return new d(fVar, com.bumptech.glide.m.e.a());
        }

        void a(com.bumptech.glide.request.f fVar, Executor executor) {
            this.f2815b.add(new d(fVar, executor));
        }

        boolean b(com.bumptech.glide.request.f fVar) {
            return this.f2815b.contains(d(fVar));
        }

        e c() {
            return new e(new ArrayList(this.f2815b));
        }

        void clear() {
            this.f2815b.clear();
        }

        void e(com.bumptech.glide.request.f fVar) {
            this.f2815b.remove(d(fVar));
        }

        boolean isEmpty() {
            return this.f2815b.isEmpty();
        }

        @Override // java.lang.Iterable
        public Iterator<d> iterator() {
            return this.f2815b.iterator();
        }

        int size() {
            return this.f2815b.size();
        }

        e(List<d> list) {
            this.f2815b = list;
        }
    }

    j(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5, c.g.j.e<j<?>> eVar) {
        this(aVar, aVar2, aVar3, aVar4, kVar, aVar5, eVar, f2806b);
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
        this.f2807c.clear();
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
        this.f2808d.c();
        this.f2807c.a(fVar, executor);
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
            com.bumptech.glide.m.j.a(z, "Cannot add callbacks to a cancelled EngineJob");
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

    @Override // com.bumptech.glide.m.l.a.f
    public com.bumptech.glide.m.l.c d() {
        return this.f2808d;
    }

    @Override // com.bumptech.glide.load.engine.DecodeJob.b
    public void e(DecodeJob<?> decodeJob) {
        j().execute(decodeJob);
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
        this.y.e();
        this.h.c(this, this.n);
    }

    void i() {
        n<?> nVar;
        synchronized (this) {
            this.f2808d.c();
            com.bumptech.glide.m.j.a(m(), "Not yet complete!");
            int iDecrementAndGet = this.m.decrementAndGet();
            com.bumptech.glide.m.j.a(iDecrementAndGet >= 0, "Can't decrement below 0");
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
        com.bumptech.glide.m.j.a(m(), "Not yet complete!");
        if (this.m.getAndAdd(i) == 0 && (nVar = this.x) != null) {
            nVar.d();
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
            this.f2808d.c();
            if (this.z) {
                q();
                return;
            }
            if (this.f2807c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            if (this.w) {
                throw new IllegalStateException("Already failed once");
            }
            this.w = true;
            com.bumptech.glide.load.c cVar = this.n;
            e eVarC = this.f2807c.c();
            k(eVarC.size() + 1);
            this.h.b(this, cVar, null);
            for (d dVar : eVarC) {
                dVar.f2814b.execute(new a(dVar.f2813a));
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
            this.f2808d.c();
            if (this.z) {
                this.s.a();
                q();
                return;
            }
            if (this.f2807c.isEmpty()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (this.u) {
                throw new IllegalStateException("Already have resource");
            }
            this.x = this.g.a(this.s, this.o, this.n, this.e);
            this.u = true;
            e eVarC = this.f2807c.c();
            k(eVarC.size() + 1);
            this.h.b(this, this.n, this.x);
            for (d dVar : eVarC) {
                dVar.f2814b.execute(new b(dVar.f2813a));
            }
            i();
        }
    }

    boolean p() {
        return this.r;
    }

    synchronized void r(com.bumptech.glide.request.f fVar) {
        this.f2808d.c();
        this.f2807c.e(fVar);
        if (this.f2807c.isEmpty()) {
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

    j(com.bumptech.glide.load.engine.z.a aVar, com.bumptech.glide.load.engine.z.a aVar2, com.bumptech.glide.load.engine.z.a aVar3, com.bumptech.glide.load.engine.z.a aVar4, k kVar, n.a aVar5, c.g.j.e<j<?>> eVar, c cVar) {
        this.f2807c = new e();
        this.f2808d = com.bumptech.glide.m.l.c.a();
        this.m = new AtomicInteger();
        this.i = aVar;
        this.j = aVar2;
        this.k = aVar3;
        this.l = aVar4;
        this.h = kVar;
        this.e = aVar5;
        this.f = eVar;
        this.g = cVar;
    }
}
