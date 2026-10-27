package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class t0 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.o e;
    public Disposable k;
    public final AtomicReference l = new AtomicReference();
    public volatile long m;
    public boolean n;

    public t0(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.functions.o oVar) {
        this.c = dVar;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
        DisposableHelper.a(this.l);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.n) {
            return;
        }
        this.n = true;
        AtomicReference atomicReference = this.l;
        Disposable disposable = (Disposable) atomicReference.get();
        if (disposable != DisposableHelper.c) {
            s0 s0Var = (s0) disposable;
            if (s0Var != null) {
                s0Var.a();
            }
            DisposableHelper.a(atomicReference);
            this.c.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.l);
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.n) {
            return;
        }
        long j = this.m + 1;
        this.m = j;
        Disposable disposable = (Disposable) this.l.get();
        if (disposable != null) {
            disposable.dispose();
        }
        try {
            Object objApply = this.e.apply(obj);
            Objects.requireNonNull(objApply, "The ObservableSource supplied is null");
            ObservableSource observableSource = (ObservableSource) objApply;
            s0 s0Var = new s0(this, j, obj);
            AtomicReference atomicReference = this.l;
            while (!atomicReference.compareAndSet(disposable, s0Var)) {
                if (atomicReference.get() != disposable) {
                    return;
                }
            }
            observableSource.subscribe(s0Var);
        } catch (Throwable th) {
            he1.R(th);
            dispose();
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }
}
