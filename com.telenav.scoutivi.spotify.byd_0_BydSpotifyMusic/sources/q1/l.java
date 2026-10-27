package q1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import l2.a;
import q1.h;
import q1.p;

/* JADX INFO: compiled from: EngineJob.java */
/* JADX INFO: loaded from: classes.dex */
class l<R> implements h.b<R>, a.f {
    private static final c D = new c();
    p<?> A;
    private h<R> B;
    private volatile boolean C;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final e f9052e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final l2.c f9053g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final p.a f9054h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final h0.e<l<?>> f9055i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final c f9056j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final m f9057k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final t1.a f9058l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final t1.a f9059m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final t1.a f9060n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final t1.a f9061o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final AtomicInteger f9062p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private n1.f f9063q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f9064r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f9065s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f9066t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f9067u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private v<?> f9068v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    n1.a f9069w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f9070x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    q f9071y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f9072z;

    /* JADX INFO: compiled from: EngineJob.java */
    private class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final g2.g f9073e;

        a(g2.g gVar) {
            this.f9073e = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f9073e.e()) {
                synchronized (l.this) {
                    if (l.this.f9052e.d(this.f9073e)) {
                        l.this.f(this.f9073e);
                    }
                    l.this.i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    private class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final g2.g f9075e;

        b(g2.g gVar) {
            this.f9075e = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.f9075e.e()) {
                synchronized (l.this) {
                    if (l.this.f9052e.d(this.f9075e)) {
                        l.this.A.b();
                        l.this.g(this.f9075e);
                        l.this.r(this.f9075e);
                    }
                    l.this.i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static class c {
        c() {
        }

        public <R> p<R> a(v<R> vVar, boolean z6, n1.f fVar, p.a aVar) {
            return new p<>(vVar, z6, true, fVar, aVar);
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g2.g f9077a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Executor f9078b;

        d(g2.g gVar, Executor executor) {
            this.f9077a = gVar;
            this.f9078b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.f9077a.equals(((d) obj).f9077a);
            }
            return false;
        }

        public int hashCode() {
            return this.f9077a.hashCode();
        }
    }

    /* JADX INFO: compiled from: EngineJob.java */
    static final class e implements Iterable<d> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final List<d> f9079e;

        e() {
            this(new ArrayList(2));
        }

        private static d f(g2.g gVar) {
            return new d(gVar, k2.e.a());
        }

        void c(g2.g gVar, Executor executor) {
            this.f9079e.add(new d(gVar, executor));
        }

        void clear() {
            this.f9079e.clear();
        }

        boolean d(g2.g gVar) {
            return this.f9079e.contains(f(gVar));
        }

        e e() {
            return new e(new ArrayList(this.f9079e));
        }

        void g(g2.g gVar) {
            this.f9079e.remove(f(gVar));
        }

        boolean isEmpty() {
            return this.f9079e.isEmpty();
        }

        @Override // java.lang.Iterable
        public Iterator<d> iterator() {
            return this.f9079e.iterator();
        }

        int size() {
            return this.f9079e.size();
        }

        e(List<d> list) {
            this.f9079e = list;
        }
    }

    l(t1.a aVar, t1.a aVar2, t1.a aVar3, t1.a aVar4, m mVar, p.a aVar5, h0.e<l<?>> eVar) {
        this(aVar, aVar2, aVar3, aVar4, mVar, aVar5, eVar, D);
    }

    private t1.a j() {
        return this.f9065s ? this.f9060n : this.f9066t ? this.f9061o : this.f9059m;
    }

    private boolean m() {
        return this.f9072z || this.f9070x || this.C;
    }

    private synchronized void q() {
        if (this.f9063q == null) {
            throw new IllegalArgumentException();
        }
        this.f9052e.clear();
        this.f9063q = null;
        this.A = null;
        this.f9068v = null;
        this.f9072z = false;
        this.C = false;
        this.f9070x = false;
        this.B.w(false);
        this.B = null;
        this.f9071y = null;
        this.f9069w = null;
        this.f9055i.a(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // q1.h.b
    public void a(v<R> vVar, n1.a aVar) {
        synchronized (this) {
            this.f9068v = vVar;
            this.f9069w = aVar;
        }
        o();
    }

    @Override // q1.h.b
    public void b(q qVar) {
        synchronized (this) {
            this.f9071y = qVar;
        }
        n();
    }

    @Override // q1.h.b
    public void c(h<?> hVar) {
        j().execute(hVar);
    }

    @Override // l2.a.f
    public l2.c d() {
        return this.f9053g;
    }

    synchronized void e(g2.g gVar, Executor executor) {
        this.f9053g.c();
        this.f9052e.c(gVar, executor);
        boolean z6 = true;
        if (this.f9070x) {
            k(1);
            executor.execute(new b(gVar));
        } else if (this.f9072z) {
            k(1);
            executor.execute(new a(gVar));
        } else {
            if (this.C) {
                z6 = false;
            }
            k2.j.a(z6, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    void f(g2.g gVar) {
        try {
            gVar.b(this.f9071y);
        } catch (Throwable th) {
            throw new q1.b(th);
        }
    }

    void g(g2.g gVar) {
        try {
            gVar.a(this.A, this.f9069w);
        } catch (Throwable th) {
            throw new q1.b(th);
        }
    }

    void h() {
        if (m()) {
            return;
        }
        this.C = true;
        this.B.b();
        this.f9057k.a(this, this.f9063q);
    }

    void i() {
        p<?> pVar;
        synchronized (this) {
            this.f9053g.c();
            k2.j.a(m(), "Not yet complete!");
            int iDecrementAndGet = this.f9062p.decrementAndGet();
            k2.j.a(iDecrementAndGet >= 0, "Can't decrement below 0");
            if (iDecrementAndGet == 0) {
                pVar = this.A;
                q();
            } else {
                pVar = null;
            }
        }
        if (pVar != null) {
            pVar.g();
        }
    }

    synchronized void k(int i7) {
        p<?> pVar;
        k2.j.a(m(), "Not yet complete!");
        if (this.f9062p.getAndAdd(i7) == 0 && (pVar = this.A) != null) {
            pVar.b();
        }
    }

    synchronized l<R> l(n1.f fVar, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.f9063q = fVar;
        this.f9064r = z6;
        this.f9065s = z7;
        this.f9066t = z8;
        this.f9067u = z9;
        return this;
    }

    void n() {
        synchronized (this) {
            this.f9053g.c();
            if (this.C) {
                q();
                return;
            }
            if (this.f9052e.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            if (this.f9072z) {
                throw new IllegalStateException("Already failed once");
            }
            this.f9072z = true;
            n1.f fVar = this.f9063q;
            e eVarE = this.f9052e.e();
            k(eVarE.size() + 1);
            this.f9057k.c(this, fVar, null);
            for (d dVar : eVarE) {
                dVar.f9078b.execute(new a(dVar.f9077a));
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
            this.f9053g.c();
            if (this.C) {
                this.f9068v.a();
                q();
                return;
            }
            if (this.f9052e.isEmpty()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (this.f9070x) {
                throw new IllegalStateException("Already have resource");
            }
            this.A = this.f9056j.a(this.f9068v, this.f9064r, this.f9063q, this.f9054h);
            this.f9070x = true;
            e eVarE = this.f9052e.e();
            k(eVarE.size() + 1);
            this.f9057k.c(this, this.f9063q, this.A);
            for (d dVar : eVarE) {
                dVar.f9078b.execute(new b(dVar.f9077a));
            }
            i();
        }
    }

    boolean p() {
        return this.f9067u;
    }

    synchronized void r(g2.g gVar) {
        this.f9053g.c();
        this.f9052e.g(gVar);
        if (this.f9052e.isEmpty()) {
            h();
            if ((this.f9070x || this.f9072z) && this.f9062p.get() == 0) {
                q();
            }
        }
    }

    public synchronized void s(h<R> hVar) {
        this.B = hVar;
        (hVar.C() ? this.f9058l : j()).execute(hVar);
    }

    l(t1.a aVar, t1.a aVar2, t1.a aVar3, t1.a aVar4, m mVar, p.a aVar5, h0.e<l<?>> eVar, c cVar) {
        this.f9052e = new e();
        this.f9053g = l2.c.a();
        this.f9062p = new AtomicInteger();
        this.f9058l = aVar;
        this.f9059m = aVar2;
        this.f9060n = aVar3;
        this.f9061o = aVar4;
        this.f9057k = mVar;
        this.f9054h = aVar5;
        this.f9055i = eVar;
        this.f9056j = cVar;
    }
}
