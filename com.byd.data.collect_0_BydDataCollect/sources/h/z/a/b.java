package h.z.a;

import h.t;

/* JADX INFO: compiled from: CallEnqueueObservable.java */
/* JADX INFO: loaded from: classes.dex */
final class b<T> extends e.a.e<t<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h.d<T> f5717a;

    /* JADX INFO: compiled from: CallEnqueueObservable.java */
    private static final class a<T> implements e.a.k.b, h.f<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h.d<?> f5718b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final e.a.g<? super t<T>> f5719c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f5720d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f5721e = false;

        a(h.d<?> dVar, e.a.g<? super t<T>> gVar) {
            this.f5718b = dVar;
            this.f5719c = gVar;
        }

        @Override // h.f
        public void A(h.d<T> dVar, t<T> tVar) {
            if (this.f5720d) {
                return;
            }
            try {
                this.f5719c.h(tVar);
                if (this.f5720d) {
                    return;
                }
                this.f5721e = true;
                this.f5719c.a();
            } catch (Throwable th) {
                e.a.l.b.b(th);
                if (this.f5721e) {
                    e.a.p.a.o(th);
                    return;
                }
                if (this.f5720d) {
                    return;
                }
                try {
                    this.f5719c.g(th);
                } catch (Throwable th2) {
                    e.a.l.b.b(th2);
                    e.a.p.a.o(new e.a.l.a(th, th2));
                }
            }
        }

        public boolean a() {
            return this.f5720d;
        }

        @Override // e.a.k.b
        public void d() {
            this.f5720d = true;
            this.f5718b.cancel();
        }

        @Override // h.f
        public void k(h.d<T> dVar, Throwable th) {
            if (dVar.isCanceled()) {
                return;
            }
            try {
                this.f5719c.g(th);
            } catch (Throwable th2) {
                e.a.l.b.b(th2);
                e.a.p.a.o(new e.a.l.a(th, th2));
            }
        }
    }

    b(h.d<T> dVar) {
        this.f5717a = dVar;
    }

    @Override // e.a.e
    protected void h(e.a.g<? super t<T>> gVar) {
        h.d<T> dVarClone = this.f5717a.clone();
        a aVar = new a(dVarClone, gVar);
        gVar.i(aVar);
        if (aVar.a()) {
            return;
        }
        dVarClone.C(aVar);
    }
}
