package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class t5 implements Observer {
    public final ObservableZip$ZipCoordinator c;
    public final io.reactivex.rxjava3.operators.h e;
    public volatile boolean k;
    public Throwable l;
    public final AtomicReference m = new AtomicReference();

    public t5(ObservableZip$ZipCoordinator observableZip$ZipCoordinator, int i) {
        this.c = observableZip$ZipCoordinator;
        this.e = new io.reactivex.rxjava3.operators.h(i);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.k = true;
        this.c.b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.l = th;
        this.k = true;
        this.c.b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.e.offer(obj);
        this.c.b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.m, disposable);
    }
}
