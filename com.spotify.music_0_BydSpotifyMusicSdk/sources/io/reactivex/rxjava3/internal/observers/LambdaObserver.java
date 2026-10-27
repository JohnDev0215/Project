package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;
import p.ik;

/* JADX INFO: loaded from: classes.dex */
public final class LambdaObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, io.reactivex.rxjava3.observers.b {
    private static final long serialVersionUID = -7251123623727029452L;
    final io.reactivex.rxjava3.functions.a onComplete;
    final io.reactivex.rxjava3.functions.g onError;
    final io.reactivex.rxjava3.functions.g onNext;
    final io.reactivex.rxjava3.functions.g onSubscribe;

    public LambdaObserver(io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        ik ikVar = io.reactivex.rxjava3.internal.functions.k.d;
        this.onNext = gVar;
        this.onError = gVar2;
        this.onComplete = aVar;
        this.onSubscribe = ikVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public final boolean hasCustomOnError() {
        return this.onError != io.reactivex.rxjava3.internal.functions.k.f;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (isDisposed()) {
            return;
        }
        lazySet(DisposableHelper.c);
        try {
            this.onComplete.run();
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (isDisposed()) {
            RxJavaPlugins.b(th);
            return;
        }
        lazySet(DisposableHelper.c);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (isDisposed()) {
            return;
        }
        try {
            this.onNext.accept(obj);
        } catch (Throwable th) {
            he1.R(th);
            get().dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                he1.R(th);
                disposable.dispose();
                onError(th);
            }
        }
    }
}
