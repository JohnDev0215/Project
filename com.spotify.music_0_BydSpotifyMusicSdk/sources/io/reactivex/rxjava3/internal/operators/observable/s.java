package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Collection;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class s implements Observer, Disposable {
    public final Observer c;
    public final int e;
    public final io.reactivex.rxjava3.functions.q k;
    public Collection l;
    public int m;
    public Disposable n;

    public s(Observer observer, int i, io.reactivex.rxjava3.functions.q qVar) {
        this.c = observer;
        this.e = i;
        this.k = qVar;
    }

    public final boolean a() {
        try {
            Object obj = this.k.get();
            Objects.requireNonNull(obj, "Empty buffer supplied");
            this.l = (Collection) obj;
            return true;
        } catch (Throwable th) {
            he1.R(th);
            this.l = null;
            Disposable disposable = this.n;
            Observer observer = this.c;
            if (disposable == null) {
                EmptyDisposable.a(th, observer);
                return false;
            }
            disposable.dispose();
            observer.onError(th);
            return false;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.n.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.n.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Collection collection = this.l;
        if (collection != null) {
            this.l = null;
            boolean zIsEmpty = collection.isEmpty();
            Observer observer = this.c;
            if (!zIsEmpty) {
                observer.onNext(collection);
            }
            observer.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.l = null;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Collection collection = this.l;
        if (collection != null) {
            collection.add(obj);
            int i = this.m + 1;
            this.m = i;
            if (i >= this.e) {
                this.c.onNext(collection);
                this.m = 0;
                a();
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.n, disposable)) {
            this.n = disposable;
            this.c.onSubscribe(this);
        }
    }
}
