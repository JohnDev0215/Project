package io.reactivex.internal.operators.observable;

import d.a.e;
import d.a.i;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;

/* JADX INFO: compiled from: ObservableJust.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends e<T> implements d.a.n.b.c<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f4294b;

    public c(T t) {
        this.f4294b = t;
    }

    @Override // d.a.n.b.c, java.util.concurrent.Callable
    public T call() {
        return this.f4294b;
    }

    @Override // d.a.e
    protected void o(i<? super T> iVar) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(iVar, this.f4294b);
        iVar.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }
}
