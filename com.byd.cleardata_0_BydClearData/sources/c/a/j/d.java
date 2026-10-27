package c.a.j;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class d<T> extends AtomicReference<T> implements b {
    d(T t) {
        super(c.a.m.b.b.c(t, "value is null"));
    }

    protected abstract void a(T t);

    @Override // c.a.j.b
    public final void e() {
        T andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        a(andSet);
    }

    @Override // c.a.j.b
    public final boolean g() {
        return get() == null;
    }
}
