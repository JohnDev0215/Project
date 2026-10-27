package e.a.n.e.b;

import e.a.g;
import e.a.h;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSubscribeOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends e.a.n.e.b.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final h f4894b;

    /* JADX INFO: compiled from: ObservableSubscribeOn.java */
    static final class a<T> extends AtomicReference<e.a.k.b> implements g<T>, e.a.k.b {
        private static final long serialVersionUID = 8094547886072529208L;
        final g<? super T> actual;
        final AtomicReference<e.a.k.b> s = new AtomicReference<>();

        a(g<? super T> gVar) {
            this.actual = gVar;
        }

        @Override // e.a.g
        public void a() {
            this.actual.a();
        }

        void b(e.a.k.b bVar) {
            e.a.n.a.b.e(this, bVar);
        }

        @Override // e.a.k.b
        public void d() {
            e.a.n.a.b.a(this.s);
            e.a.n.a.b.a(this);
        }

        @Override // e.a.g
        public void g(Throwable th) {
            this.actual.g(th);
        }

        @Override // e.a.g
        public void h(T t) {
            this.actual.h(t);
        }

        @Override // e.a.g
        public void i(e.a.k.b bVar) {
            e.a.n.a.b.e(this.s, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableSubscribeOn.java */
    final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final a<T> f4895b;

        b(a<T> aVar) {
            this.f4895b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f4886a.a(this.f4895b);
        }
    }

    public f(e.a.f<T> fVar, h hVar) {
        super(fVar);
        this.f4894b = hVar;
    }

    @Override // e.a.e
    public void h(g<? super T> gVar) {
        a aVar = new a(gVar);
        gVar.i(aVar);
        aVar.b(this.f4894b.b(new b(aVar)));
    }
}
