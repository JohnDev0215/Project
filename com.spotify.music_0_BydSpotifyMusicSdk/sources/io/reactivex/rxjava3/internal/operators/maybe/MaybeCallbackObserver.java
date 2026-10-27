package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class MaybeCallbackObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable, io.reactivex.rxjava3.observers.b {
    private static final long serialVersionUID = -6076952298809384986L;
    final io.reactivex.rxjava3.functions.a onComplete;
    final io.reactivex.rxjava3.functions.g onError;
    final io.reactivex.rxjava3.functions.g onSuccess;

    public MaybeCallbackObserver(io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        this.onSuccess = gVar;
        this.onError = gVar2;
        this.onComplete = aVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public final boolean hasCustomOnError() {
        return this.onError != k.f;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        lazySet(DisposableHelper.c);
        try {
            this.onComplete.run();
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        lazySet(DisposableHelper.c);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        lazySet(DisposableHelper.c);
        try {
            this.onSuccess.accept(obj);
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }
}
