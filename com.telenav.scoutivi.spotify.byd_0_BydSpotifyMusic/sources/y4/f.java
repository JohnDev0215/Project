package y4;

import java.util.concurrent.atomic.AtomicReference;
import q4.g;
import q4.i;

/* JADX INFO: compiled from: SingleSubscribeOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends q4.e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final i<? extends T> f9986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final q4.d f9987b;

    /* JADX INFO: compiled from: SingleSubscribeOn.java */
    static final class a<T> extends AtomicReference<t4.b> implements g<T>, t4.b, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g<? super T> f9988e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final w4.e f9989g = new w4.e();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final i<? extends T> f9990h;

        a(g<? super T> gVar, i<? extends T> iVar) {
            this.f9988e = gVar;
            this.f9990h = iVar;
        }

        @Override // q4.g
        public void a(Throwable th) {
            this.f9988e.a(th);
        }

        @Override // q4.g
        public void b(T t6) {
            this.f9988e.b(t6);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
            w4.b.setOnce(this, bVar);
        }

        @Override // t4.b
        public void dispose() {
            w4.b.dispose(this);
            this.f9989g.dispose();
        }

        @Override // t4.b
        public boolean isDisposed() {
            return w4.b.isDisposed(get());
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9990h.a(this);
        }
    }

    public f(i<? extends T> iVar, q4.d dVar) {
        this.f9986a = iVar;
        this.f9987b = dVar;
    }

    @Override // q4.e
    protected void g(g<? super T> gVar) {
        a aVar = new a(gVar, this.f9986a);
        gVar.c(aVar);
        aVar.f9989g.a(this.f9987b.b(aVar));
    }
}
