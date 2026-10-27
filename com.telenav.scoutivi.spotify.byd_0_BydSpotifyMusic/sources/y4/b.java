package y4;

import java.util.concurrent.atomic.AtomicReference;
import q4.g;
import q4.i;

/* JADX INFO: compiled from: SingleFlatMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T, R> extends q4.e<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final i<? extends T> f9969a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final v4.e<? super T, ? extends i<? extends R>> f9970b;

    /* JADX INFO: compiled from: SingleFlatMap.java */
    static final class a<T, R> extends AtomicReference<t4.b> implements g<T>, t4.b {
        private static final long serialVersionUID = 3258103020495908596L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g<? super R> f9971e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final v4.e<? super T, ? extends i<? extends R>> f9972g;

        /* JADX INFO: renamed from: y4.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SingleFlatMap.java */
        static final class C0180a<R> implements g<R> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final AtomicReference<t4.b> f9973e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final g<? super R> f9974g;

            C0180a(AtomicReference<t4.b> atomicReference, g<? super R> gVar) {
                this.f9973e = atomicReference;
                this.f9974g = gVar;
            }

            @Override // q4.g
            public void a(Throwable th) {
                this.f9974g.a(th);
            }

            @Override // q4.g
            public void b(R r7) {
                this.f9974g.b(r7);
            }

            @Override // q4.g
            public void c(t4.b bVar) {
                w4.b.replace(this.f9973e, bVar);
            }
        }

        a(g<? super R> gVar, v4.e<? super T, ? extends i<? extends R>> eVar) {
            this.f9971e = gVar;
            this.f9972g = eVar;
        }

        @Override // q4.g
        public void a(Throwable th) {
            this.f9971e.a(th);
        }

        @Override // q4.g
        public void b(T t6) {
            try {
                i iVar = (i) x4.b.c(this.f9972g.a(t6), "The single returned by the mapper is null");
                if (isDisposed()) {
                    return;
                }
                iVar.a(new C0180a(this, this.f9971e));
            } catch (Throwable th) {
                u4.b.b(th);
                this.f9971e.a(th);
            }
        }

        @Override // q4.g
        public void c(t4.b bVar) {
            if (w4.b.setOnce(this, bVar)) {
                this.f9971e.c(this);
            }
        }

        @Override // t4.b
        public void dispose() {
            w4.b.dispose(this);
        }

        @Override // t4.b
        public boolean isDisposed() {
            return w4.b.isDisposed(get());
        }
    }

    public b(i<? extends T> iVar, v4.e<? super T, ? extends i<? extends R>> eVar) {
        this.f9970b = eVar;
        this.f9969a = iVar;
    }

    @Override // q4.e
    protected void g(g<? super R> gVar) {
        this.f9969a.a(new a(gVar, this.f9970b));
    }
}
