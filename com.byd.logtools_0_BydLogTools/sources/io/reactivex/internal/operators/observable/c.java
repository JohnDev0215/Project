package io.reactivex.internal.operators.observable;

import e.a.e;
import e.a.i;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;

/* JADX INFO: compiled from: ObservableJust.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends e<T> implements e.a.n.b.c<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f5011b;

    public c(T t) {
        this.f5011b = t;
    }

    @Override // e.a.n.b.c, java.util.concurrent.Callable
    public T call() {
        return this.f5011b;
    }

    @Override // e.a.e
    protected void m(i<? super T> iVar) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(iVar, this.f5011b);
        iVar.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }
}
