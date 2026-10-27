package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class u5 implements Observer, Disposable {
    public final Observer c;
    public final Iterator e;
    public final io.reactivex.rxjava3.functions.c k;
    public Disposable l;
    public boolean m;

    public u5(Observer observer, Iterator it, io.reactivex.rxjava3.functions.c cVar) {
        this.c = observer;
        this.e = it;
        this.k = cVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
        } else {
            this.m = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Observer observer = this.c;
        Iterator it = this.e;
        if (this.m) {
            return;
        }
        try {
            Object next = it.next();
            Objects.requireNonNull(next, "The iterator returned a null value");
            try {
                Object objK = this.k.k(obj, next);
                Objects.requireNonNull(objK, "The zipper function returned a null value");
                observer.onNext(objK);
                try {
                    if (it.hasNext()) {
                        return;
                    }
                    this.m = true;
                    this.l.dispose();
                    observer.onComplete();
                } catch (Throwable th) {
                    he1.R(th);
                    this.m = true;
                    this.l.dispose();
                    observer.onError(th);
                }
            } catch (Throwable th2) {
                he1.R(th2);
                this.m = true;
                this.l.dispose();
                observer.onError(th2);
            }
        } catch (Throwable th3) {
            he1.R(th3);
            this.m = true;
            this.l.dispose();
            observer.onError(th3);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.onSubscribe(this);
        }
    }
}
