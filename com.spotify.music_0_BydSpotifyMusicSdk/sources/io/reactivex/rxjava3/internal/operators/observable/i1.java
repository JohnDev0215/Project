package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class i1 implements Observer, Disposable {
    public final Observer c;
    public final long e;
    public final Object k;
    public final boolean l;
    public Disposable m;
    public long n;
    public boolean o;

    public i1(Observer observer, long j, Object obj, boolean z) {
        this.c = observer;
        this.e = j;
        this.k = obj;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.o) {
            return;
        }
        this.o = true;
        Observer observer = this.c;
        Object obj = this.k;
        if (obj == null && this.l) {
            observer.onError(new NoSuchElementException());
            return;
        }
        if (obj != null) {
            observer.onNext(obj);
        }
        observer.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.o) {
            RxJavaPlugins.b(th);
        } else {
            this.o = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.o) {
            return;
        }
        long j = this.n;
        if (j != this.e) {
            this.n = j + 1;
            return;
        }
        this.o = true;
        this.m.dispose();
        Observer observer = this.c;
        observer.onNext(obj);
        observer.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.c.onSubscribe(this);
        }
    }
}
