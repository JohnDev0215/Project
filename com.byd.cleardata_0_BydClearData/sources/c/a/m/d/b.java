package c.a.m.d;

import c.a.g;
import c.a.l.d;

/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements g<T>, c.a.j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final g<? super T> f1828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final d<? super c.a.j.b> f1829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.l.a f1830d;
    c.a.j.b e;

    public b(g<? super T> gVar, d<? super c.a.j.b> dVar, c.a.l.a aVar) {
        this.f1828b = gVar;
        this.f1829c = dVar;
        this.f1830d = aVar;
    }

    @Override // c.a.g
    public void a() {
        if (this.e != c.a.m.a.b.DISPOSED) {
            this.f1828b.a();
        }
    }

    @Override // c.a.g
    public void b(Throwable th) {
        if (this.e != c.a.m.a.b.DISPOSED) {
            this.f1828b.b(th);
        } else {
            c.a.n.a.l(th);
        }
    }

    @Override // c.a.g
    public void c(T t) {
        this.f1828b.c(t);
    }

    @Override // c.a.j.b
    public void e() {
        try {
            this.f1830d.run();
        } catch (Throwable th) {
            c.a.k.b.b(th);
            c.a.n.a.l(th);
        }
        this.e.e();
    }

    @Override // c.a.j.b
    public boolean g() {
        return this.e.g();
    }

    @Override // c.a.g
    public void h(c.a.j.b bVar) {
        try {
            this.f1829c.a(bVar);
            if (c.a.m.a.b.f(this.e, bVar)) {
                this.e = bVar;
                this.f1828b.h(this);
            }
        } catch (Throwable th) {
            c.a.k.b.b(th);
            bVar.e();
            this.e = c.a.m.a.b.DISPOSED;
            c.a.m.a.c.a(th, this.f1828b);
        }
    }
}
