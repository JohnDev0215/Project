package y4;

import java.util.concurrent.atomic.AtomicReference;
import q4.g;
import q4.i;

/* JADX INFO: compiled from: SingleObserveOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends q4.e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final i<T> f9980a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final q4.d f9981b;

    /* JADX INFO: compiled from: SingleObserveOn.java */
    static final class a<T> extends AtomicReference<t4.b> implements g<T>, t4.b, Runnable {
        private static final long serialVersionUID = 3528003840217436037L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final g<? super T> f9982e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final q4.d f9983g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        T f9984h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Throwable f9985i;

        a(g<? super T> gVar, q4.d dVar) {
            this.f9982e = gVar;
            this.f9983g = dVar;
        }

        @Override // q4.g
        public void a(Throwable th) {
            this.f9985i = th;
            w4.b.replace(this, this.f9983g.b(this));
        }

        @Override // q4.g
        public void b(T t6) {
            this.f9984h = t6;
            w4.b.replace(this, this.f9983g.b(this));
        }

        @Override // q4.g
        public void c(t4.b bVar) {
            if (w4.b.setOnce(this, bVar)) {
                this.f9982e.c(this);
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

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.f9985i;
            if (th != null) {
                this.f9982e.a(th);
            } else {
                this.f9982e.b(this.f9984h);
            }
        }
    }

    public e(i<T> iVar, q4.d dVar) {
        this.f9980a = iVar;
        this.f9981b = dVar;
    }

    @Override // q4.e
    protected void g(g<? super T> gVar) {
        this.f9980a.a(new a(gVar, this.f9981b));
    }
}
