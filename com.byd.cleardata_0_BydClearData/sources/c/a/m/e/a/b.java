package c.a.m.e.a;

import c.a.g;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends c.a.c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.e<T> f1835a;

    static final class a<T> extends AtomicReference<c.a.j.b> implements c.a.d<T>, c.a.j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final g<? super T> f1836b;

        a(g<? super T> gVar) {
            this.f1836b = gVar;
        }

        @Override // c.a.a
        public void a() {
            if (g()) {
                return;
            }
            try {
                this.f1836b.a();
            } finally {
                e();
            }
        }

        @Override // c.a.a
        public void b(Throwable th) {
            if (d(th)) {
                return;
            }
            c.a.n.a.l(th);
        }

        @Override // c.a.a
        public void c(T t) {
            if (t == null) {
                b(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                if (g()) {
                    return;
                }
                this.f1836b.c(t);
            }
        }

        public boolean d(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (g()) {
                return false;
            }
            try {
                this.f1836b.b(th);
                e();
                return true;
            } catch (Throwable th2) {
                e();
                throw th2;
            }
        }

        @Override // c.a.j.b
        public void e() {
            c.a.m.a.b.a(this);
        }

        @Override // c.a.j.b
        public boolean g() {
            return c.a.m.a.b.b(get());
        }
    }

    public b(c.a.e<T> eVar) {
        this.f1835a = eVar;
    }

    @Override // c.a.c
    protected void j(g<? super T> gVar) {
        a aVar = new a(gVar);
        gVar.h(aVar);
        try {
            this.f1835a.a(aVar);
        } catch (Throwable th) {
            c.a.k.b.b(th);
            aVar.b(th);
        }
    }
}
