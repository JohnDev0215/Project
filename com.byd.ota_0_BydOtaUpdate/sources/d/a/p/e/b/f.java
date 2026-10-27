package d.a.p.e.b;

import io.reactivex.internal.operators.observable.ObservableScalarXMap;

/* JADX INFO: compiled from: ObservableJust.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends d.a.g<T> implements d.a.p.c.c<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final T f3202c;

    public f(T t) {
        this.f3202c = t;
    }

    @Override // d.a.p.c.c, java.util.concurrent.Callable
    public T call() {
        return this.f3202c;
    }

    @Override // d.a.g
    public void t(d.a.i<? super T> iVar) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(iVar, this.f3202c);
        iVar.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }
}
