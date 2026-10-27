package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.EmptyComponent;

/* JADX INFO: loaded from: classes.dex */
public final class d1 implements Observer, Disposable {
    public Observer c;
    public Disposable e;

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Disposable disposable = this.e;
        EmptyComponent emptyComponent = EmptyComponent.c;
        this.e = emptyComponent;
        this.c = emptyComponent;
        disposable.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Observer observer = this.c;
        EmptyComponent emptyComponent = EmptyComponent.c;
        this.e = emptyComponent;
        this.c = emptyComponent;
        observer.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Observer observer = this.c;
        EmptyComponent emptyComponent = EmptyComponent.c;
        this.e = emptyComponent;
        this.c = emptyComponent;
        observer.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.c.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }
}
