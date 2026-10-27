package c.b.h.c;

import a.b.k.l;
import c.b.e.b;
import c.b.f.c;
import c.b.h.b.a;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends AtomicReference<b> implements c.b.b<T>, b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.g.b<? super T> f2235a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.g.b<? super Throwable> f2236b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.g.a f2237c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.g.b<? super b> f2238d;

    public a(c.b.g.b<? super T> bVar, c.b.g.b<? super Throwable> bVar2, c.b.g.a aVar, c.b.g.b<? super b> bVar3) {
        this.f2235a = bVar;
        this.f2236b = bVar2;
        this.f2237c = aVar;
        this.f2238d = bVar3;
    }

    @Override // c.b.b
    public void a() {
        if (f()) {
            return;
        }
        lazySet(c.b.h.a.b.DISPOSED);
        try {
            if (((a.C0051a) this.f2237c) != null) {
            } else {
                throw null;
            }
        } catch (Throwable th) {
            l.i.C0(th);
            c.b.j.a.c(th);
        }
    }

    @Override // c.b.b
    public void b(Throwable th) {
        if (f()) {
            c.b.j.a.c(th);
            return;
        }
        lazySet(c.b.h.a.b.DISPOSED);
        try {
            this.f2236b.accept(th);
        } catch (Throwable th2) {
            l.i.C0(th2);
            c.b.j.a.c(new c.b.f.a(Arrays.asList(th, th2)));
        }
    }

    @Override // c.b.b
    public void c(T t) {
        if (f()) {
            return;
        }
        try {
            this.f2235a.accept(t);
        } catch (Throwable th) {
            l.i.C0(th);
            get().d();
            b(th);
        }
    }

    @Override // c.b.e.b
    public void d() {
        b andSet;
        b bVar = get();
        c.b.h.a.b bVar2 = c.b.h.a.b.DISPOSED;
        if (bVar == bVar2 || (andSet = getAndSet(bVar2)) == bVar2 || andSet == null) {
            return;
        }
        andSet.d();
    }

    @Override // c.b.b
    public void e(b bVar) {
        boolean z;
        c.b.h.b.b.a(bVar, "d is null");
        if (compareAndSet(null, bVar)) {
            z = true;
        } else {
            bVar.d();
            if (get() != c.b.h.a.b.DISPOSED) {
                c.b.j.a.c(new c("Disposable already set!"));
            }
            z = false;
        }
        if (z) {
            try {
                this.f2238d.accept(this);
            } catch (Throwable th) {
                l.i.C0(th);
                bVar.d();
                b(th);
            }
        }
    }

    public boolean f() {
        return get() == c.b.h.a.b.DISPOSED;
    }
}
