package c5;

import java.util.concurrent.atomic.AtomicReference;
import q4.g;
import t4.b;

/* JADX INFO: compiled from: DisposableSingleObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements g<T>, b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final AtomicReference<b> f3628e = new AtomicReference<>();

    @Override // q4.g
    public final void c(b bVar) {
        if (b5.a.c(this.f3628e, bVar, getClass())) {
            e();
        }
    }

    @Override // t4.b
    public final void dispose() {
        w4.b.dispose(this.f3628e);
    }

    protected abstract void e();

    @Override // t4.b
    public final boolean isDisposed() {
        return this.f3628e.get() == w4.b.DISPOSED;
    }
}
