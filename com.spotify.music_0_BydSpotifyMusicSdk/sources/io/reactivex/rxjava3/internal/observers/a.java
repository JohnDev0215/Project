package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Observer, io.reactivex.rxjava3.operators.b {
    public final Observer c;
    public Disposable e;
    public io.reactivex.rxjava3.operators.b k;
    public boolean l;
    public int m;

    public a(Observer observer) {
        this.c = observer;
    }

    public final void a(Throwable th) {
        he1.R(th);
        this.e.dispose();
        onError(th);
    }

    public int b(int i) {
        return c(i);
    }

    public final int c(int i) {
        io.reactivex.rxjava3.operators.b bVar = this.k;
        if (bVar == null || (i & 4) != 0) {
            return 0;
        }
        int iB = bVar.b(i);
        if (iB != 0) {
            this.m = iB;
        }
        return iB;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public void clear() {
        this.k.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.k.isEmpty();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
        } else {
            this.l = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                this.k = (io.reactivex.rxjava3.operators.b) disposable;
            }
            this.c.onSubscribe(this);
        }
    }
}
