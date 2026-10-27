package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractDisposableAutoRelease extends AtomicReference<Disposable> implements Disposable, io.reactivex.rxjava3.observers.b {
    private static final long serialVersionUID = 8924480688481408726L;
    final AtomicReference<io.reactivex.rxjava3.disposables.b> composite;
    final io.reactivex.rxjava3.functions.a onComplete;
    final io.reactivex.rxjava3.functions.g onError;

    public AbstractDisposableAutoRelease(io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.disposables.b bVar) {
        this.onError = gVar;
        this.onComplete = aVar;
        this.composite = new AtomicReference<>(bVar);
    }

    public final void a() {
        io.reactivex.rxjava3.disposables.b andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.b(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        DisposableHelper.a(this);
        a();
    }

    @Override // io.reactivex.rxjava3.observers.b
    public final boolean hasCustomOnError() {
        return this.onError != io.reactivex.rxjava3.internal.functions.k.f;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    public final void onComplete() {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            try {
                this.onComplete.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
        a();
    }

    public final void onError(Throwable th) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(th, th2));
            }
        } else {
            RxJavaPlugins.b(th);
        }
        a();
    }

    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
