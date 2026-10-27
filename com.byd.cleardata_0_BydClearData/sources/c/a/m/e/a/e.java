package c.a.m.e.a;

import c.a.f;
import c.a.g;
import c.a.h;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends c.a.m.e.a.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final h f1845b;

    static final class a<T> extends AtomicReference<c.a.j.b> implements g<T>, c.a.j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final g<? super T> f1846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicReference<c.a.j.b> f1847c = new AtomicReference<>();

        a(g<? super T> gVar) {
            this.f1846b = gVar;
        }

        @Override // c.a.g
        public void a() {
            this.f1846b.a();
        }

        @Override // c.a.g
        public void b(Throwable th) {
            this.f1846b.b(th);
        }

        @Override // c.a.g
        public void c(T t) {
            this.f1846b.c(t);
        }

        void d(c.a.j.b bVar) {
            c.a.m.a.b.d(this, bVar);
        }

        @Override // c.a.j.b
        public void e() {
            c.a.m.a.b.a(this.f1847c);
            c.a.m.a.b.a(this);
        }

        @Override // c.a.j.b
        public boolean g() {
            return c.a.m.a.b.b(get());
        }

        @Override // c.a.g
        public void h(c.a.j.b bVar) {
            c.a.m.a.b.d(this.f1847c, bVar);
        }
    }

    final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final a<T> f1848b;

        b(a<T> aVar) {
            this.f1848b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f1834a.a(this.f1848b);
        }
    }

    public e(f<T> fVar, h hVar) {
        super(fVar);
        this.f1845b = hVar;
    }

    @Override // c.a.c
    public void j(g<? super T> gVar) {
        a aVar = new a(gVar);
        gVar.h(aVar);
        aVar.d(this.f1845b.b(new b(aVar)));
    }
}
