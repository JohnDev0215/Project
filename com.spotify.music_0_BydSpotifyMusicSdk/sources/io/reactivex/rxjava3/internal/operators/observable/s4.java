package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class s4 implements Observer, Disposable {
    public final Observer c;
    public boolean e;
    public Disposable k;
    public long l;

    public s4(Observer observer, long j) {
        this.c = observer;
        this.l = j;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.k.dispose();
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.e) {
            RxJavaPlugins.b(th);
            return;
        }
        this.e = true;
        this.k.dispose();
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.e) {
            return;
        }
        long j = this.l;
        long j2 = j - 1;
        this.l = j2;
        if (j > 0) {
            boolean z = j2 == 0;
            this.c.onNext(obj);
            if (z) {
                onComplete();
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            long j = this.l;
            Observer observer = this.c;
            if (j != 0) {
                observer.onSubscribe(this);
                return;
            }
            this.e = true;
            disposable.dispose();
            observer.onSubscribe(EmptyDisposable.c);
            observer.onComplete();
        }
    }
}
