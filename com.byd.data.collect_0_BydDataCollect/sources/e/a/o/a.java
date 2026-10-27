package e.a.o;

import e.a.g;
import e.a.k.b;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DisposableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements g<T>, b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<b> f4976b = new AtomicReference<>();

    protected abstract void b();

    @Override // e.a.k.b
    public final void d() {
        e.a.n.a.b.a(this.f4976b);
    }

    @Override // e.a.g
    public final void i(b bVar) {
        if (e.a.n.h.a.c(this.f4976b, bVar, getClass())) {
            b();
        }
    }
}
