package d.a.p.e.b;

import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableError.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> extends d.a.g<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Callable<? extends Throwable> f3200c;

    public d(Callable<? extends Throwable> callable) {
        this.f3200c = callable;
    }

    @Override // d.a.g
    public void t(d.a.i<? super T> iVar) {
        try {
            th = (Throwable) d.a.p.b.b.d(this.f3200c.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th) {
            th = th;
            d.a.n.a.a(th);
        }
        EmptyDisposable.error(th, iVar);
    }
}
