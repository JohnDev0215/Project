package c.a.m.d;

import c.a.g;
import c.a.l.d;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends AtomicReference<c.a.j.b> implements g<T>, c.a.j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final d<? super T> f1831b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final d<? super Throwable> f1832c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.l.a f1833d;
    final d<? super c.a.j.b> e;

    public c(d<? super T> dVar, d<? super Throwable> dVar2, c.a.l.a aVar, d<? super c.a.j.b> dVar3) {
        this.f1831b = dVar;
        this.f1832c = dVar2;
        this.f1833d = aVar;
        this.e = dVar3;
    }

    @Override // c.a.g
    public void a() {
        if (g()) {
            return;
        }
        lazySet(c.a.m.a.b.DISPOSED);
        try {
            this.f1833d.run();
        } catch (Throwable th) {
            c.a.k.b.b(th);
            c.a.n.a.l(th);
        }
    }

    @Override // c.a.g
    public void b(Throwable th) {
        if (g()) {
            return;
        }
        lazySet(c.a.m.a.b.DISPOSED);
        try {
            this.f1832c.a(th);
        } catch (Throwable th2) {
            c.a.k.b.b(th2);
            c.a.n.a.l(new c.a.k.a(th, th2));
        }
    }

    @Override // c.a.g
    public void c(T t) {
        if (g()) {
            return;
        }
        try {
            this.f1831b.a(t);
        } catch (Throwable th) {
            c.a.k.b.b(th);
            get().e();
            b(th);
        }
    }

    @Override // c.a.j.b
    public void e() {
        c.a.m.a.b.a(this);
    }

    @Override // c.a.j.b
    public boolean g() {
        return get() == c.a.m.a.b.DISPOSED;
    }

    @Override // c.a.g
    public void h(c.a.j.b bVar) {
        if (c.a.m.a.b.d(this, bVar)) {
            try {
                this.e.a(this);
            } catch (Throwable th) {
                c.a.k.b.b(th);
                bVar.e();
                b(th);
            }
        }
    }
}
