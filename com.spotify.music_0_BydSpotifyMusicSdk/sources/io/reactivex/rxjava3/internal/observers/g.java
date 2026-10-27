package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.g e;
    public final io.reactivex.rxjava3.functions.a k;
    public Disposable l;

    public g(Observer observer, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.a aVar) {
        this.c = observer;
        this.e = gVar;
        this.k = aVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Disposable disposable = this.l;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            this.l = disposableHelper;
            try {
                this.k.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Disposable disposable = this.l;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            this.l = disposableHelper;
            this.c.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Disposable disposable = this.l;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
        } else {
            this.l = disposableHelper;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.c.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        Observer observer = this.c;
        try {
            this.e.accept(disposable);
            if (DisposableHelper.g(this.l, disposable)) {
                this.l = disposable;
                observer.onSubscribe(this);
            }
        } catch (Throwable th) {
            he1.R(th);
            disposable.dispose();
            this.l = DisposableHelper.c;
            EmptyDisposable.a(th, observer);
        }
    }
}
