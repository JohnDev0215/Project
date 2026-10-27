package x5;

import g5.l;
import g5.r;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: SequenceBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
final class e<T> extends f<T> implements Iterator<T>, k5.d<r>, s5.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f9858e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private T f9859g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Iterator<? extends T> f9860h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private k5.d<? super r> f9861i;

    private final Throwable c() {
        int i7 = this.f9858e;
        if (i7 == 4) {
            return new NoSuchElementException();
        }
        if (i7 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f9858e);
    }

    private final T f() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    @Override // x5.f
    public Object b(T t6, k5.d<? super r> dVar) {
        this.f9859g = t6;
        this.f9858e = 3;
        this.f9861i = dVar;
        Object objD = l5.d.d();
        if (objD == l5.d.d()) {
            kotlin.coroutines.jvm.internal.h.c(dVar);
        }
        return objD == l5.d.d() ? objD : r.f6563a;
    }

    public final void g(k5.d<? super r> dVar) {
        this.f9861i = dVar;
    }

    @Override // k5.d
    public k5.g getContext() {
        return k5.h.f7276e;
    }

    @Override // java.util.Iterator
    public boolean hasNext() throws Throwable {
        while (true) {
            int i7 = this.f9858e;
            if (i7 != 0) {
                if (i7 != 1) {
                    if (i7 == 2 || i7 == 3) {
                        return true;
                    }
                    if (i7 == 4) {
                        return false;
                    }
                    throw c();
                }
                Iterator<? extends T> it = this.f9860h;
                kotlin.jvm.internal.l.c(it);
                if (it.hasNext()) {
                    this.f9858e = 2;
                    return true;
                }
                this.f9860h = null;
            }
            this.f9858e = 5;
            k5.d<? super r> dVar = this.f9861i;
            kotlin.jvm.internal.l.c(dVar);
            this.f9861i = null;
            l.a aVar = g5.l.f6557e;
            dVar.resumeWith(g5.l.a(r.f6563a));
        }
    }

    @Override // java.util.Iterator
    public T next() throws Throwable {
        int i7 = this.f9858e;
        if (i7 == 0 || i7 == 1) {
            return f();
        }
        if (i7 == 2) {
            this.f9858e = 1;
            Iterator<? extends T> it = this.f9860h;
            kotlin.jvm.internal.l.c(it);
            return it.next();
        }
        if (i7 != 3) {
            throw c();
        }
        this.f9858e = 0;
        T t6 = this.f9859g;
        this.f9859g = null;
        return t6;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // k5.d
    public void resumeWith(Object obj) throws Throwable {
        g5.m.b(obj);
        this.f9858e = 4;
    }
}
