package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class j extends CompletableFuture implements Observer {
    public final AtomicReference c = new AtomicReference();
    public Object e;

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        DisposableHelper.a(this.c);
        return super.cancel(z);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean complete(Object obj) {
        DisposableHelper.a(this.c);
        return super.complete(obj);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean completeExceptionally(Throwable th) {
        DisposableHelper.a(this.c);
        return super.completeExceptionally(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.e = null;
        this.c.lazySet(DisposableHelper.c);
        if (completeExceptionally(th)) {
            return;
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.c, disposable);
    }
}
