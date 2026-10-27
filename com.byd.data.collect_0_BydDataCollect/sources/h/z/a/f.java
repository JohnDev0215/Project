package h.z.a;

import h.t;

/* JADX INFO: compiled from: ResultObservable.java */
/* JADX INFO: loaded from: classes.dex */
final class f<T> extends e.a.e<e<T>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.a.e<t<T>> f5727a;

    /* JADX INFO: compiled from: ResultObservable.java */
    private static class a<R> implements e.a.g<t<R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.a.g<? super e<R>> f5728b;

        a(e.a.g<? super e<R>> gVar) {
            this.f5728b = gVar;
        }

        @Override // e.a.g
        public void a() {
            this.f5728b.a();
        }

        @Override // e.a.g
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void h(t<R> tVar) {
            this.f5728b.h(e.b(tVar));
        }

        @Override // e.a.g
        public void g(Throwable th) {
            try {
                this.f5728b.h(e.a(th));
                this.f5728b.a();
            } catch (Throwable th2) {
                try {
                    this.f5728b.g(th2);
                } catch (Throwable th3) {
                    e.a.l.b.b(th3);
                    e.a.p.a.o(new e.a.l.a(th2, th3));
                }
            }
        }

        @Override // e.a.g
        public void i(e.a.k.b bVar) {
            this.f5728b.i(bVar);
        }
    }

    f(e.a.e<t<T>> eVar) {
        this.f5727a = eVar;
    }

    @Override // e.a.e
    protected void h(e.a.g<? super e<T>> gVar) {
        this.f5727a.a(new a(gVar));
    }
}
