package h.z.a;

import h.t;

/* JADX INFO: compiled from: BodyObservable.java */
/* JADX INFO: loaded from: classes.dex */
final class a<T> extends e.a.e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.a.e<t<T>> f5714a;

    /* JADX INFO: renamed from: h.z.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BodyObservable.java */
    private static class C0109a<R> implements e.a.g<t<R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.a.g<? super R> f5715b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f5716c;

        C0109a(e.a.g<? super R> gVar) {
            this.f5715b = gVar;
        }

        @Override // e.a.g
        public void a() {
            if (this.f5716c) {
                return;
            }
            this.f5715b.a();
        }

        @Override // e.a.g
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void h(t<R> tVar) {
            if (tVar.d()) {
                this.f5715b.h(tVar.a());
                return;
            }
            this.f5716c = true;
            d dVar = new d(tVar);
            try {
                this.f5715b.g(dVar);
            } catch (Throwable th) {
                e.a.l.b.b(th);
                e.a.p.a.o(new e.a.l.a(dVar, th));
            }
        }

        @Override // e.a.g
        public void g(Throwable th) {
            if (!this.f5716c) {
                this.f5715b.g(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            e.a.p.a.o(assertionError);
        }

        @Override // e.a.g
        public void i(e.a.k.b bVar) {
            this.f5715b.i(bVar);
        }
    }

    a(e.a.e<t<T>> eVar) {
        this.f5714a = eVar;
    }

    @Override // e.a.e
    protected void h(e.a.g<? super T> gVar) {
        this.f5714a.a(new C0109a(gVar));
    }
}
