package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Observer, Disposable {
    public final Observer c;
    public Disposable e;
    public boolean k;

    public c(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.k) {
            return;
        }
        this.k = true;
        Disposable disposable = this.e;
        Observer observer = this.c;
        if (disposable != null) {
            try {
                observer.onComplete();
                return;
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
                return;
            }
        }
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            observer.onSubscribe(EmptyDisposable.c);
            try {
                observer.onError(nullPointerException);
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(nullPointerException, th2));
            }
        } catch (Throwable th3) {
            he1.R(th3);
            RxJavaPlugins.b(new CompositeException(nullPointerException, th3));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.b(th);
            return;
        }
        this.k = true;
        Disposable disposable = this.e;
        Observer observer = this.c;
        if (disposable != null) {
            if (th == null) {
                th = io.reactivex.rxjava3.internal.util.c.b("onError called with a null Throwable.");
            }
            try {
                observer.onError(th);
                return;
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(th, th2));
                return;
            }
        }
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            observer.onSubscribe(EmptyDisposable.c);
            try {
                observer.onError(new CompositeException(th, nullPointerException));
            } catch (Throwable th3) {
                he1.R(th3);
                RxJavaPlugins.b(new CompositeException(th, nullPointerException, th3));
            }
        } catch (Throwable th4) {
            he1.R(th4);
            RxJavaPlugins.b(new CompositeException(th, nullPointerException, th4));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.k) {
            return;
        }
        Disposable disposable = this.e;
        Observer observer = this.c;
        if (disposable == null) {
            this.k = true;
            NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                observer.onSubscribe(EmptyDisposable.c);
                try {
                    observer.onError(nullPointerException);
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    RxJavaPlugins.b(new CompositeException(nullPointerException, th));
                    return;
                }
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(nullPointerException, th2));
                return;
            }
        }
        if (obj == null) {
            NullPointerException nullPointerExceptionB = io.reactivex.rxjava3.internal.util.c.b("onNext called with a null value.");
            try {
                this.e.dispose();
                onError(nullPointerExceptionB);
                return;
            } catch (Throwable th3) {
                he1.R(th3);
                onError(new CompositeException(nullPointerExceptionB, th3));
                return;
            }
        }
        try {
            observer.onNext(obj);
        } catch (Throwable th4) {
            he1.R(th4);
            try {
                this.e.dispose();
                onError(th4);
            } catch (Throwable th5) {
                he1.R(th5);
                onError(new CompositeException(th4, th5));
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            try {
                this.c.onSubscribe(this);
            } catch (Throwable th) {
                he1.R(th);
                this.k = true;
                try {
                    disposable.dispose();
                    RxJavaPlugins.b(th);
                } catch (Throwable th2) {
                    he1.R(th2);
                    RxJavaPlugins.b(new CompositeException(th, th2));
                }
            }
        }
    }
}
