package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleDoOnDispose$DoOnDisposeObserver<T> extends AtomicReference<io.reactivex.rxjava3.functions.a> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -8583764624474935784L;
    final SingleObserver<? super T> downstream;
    Disposable upstream;

    public SingleDoOnDispose$DoOnDisposeObserver(SingleObserver singleObserver, io.reactivex.rxjava3.functions.a aVar) {
        this.downstream = singleObserver;
        lazySet(aVar);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        io.reactivex.rxjava3.functions.a andSet = getAndSet(null);
        if (andSet != null) {
            try {
                andSet.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
            this.upstream.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.downstream.onSuccess(obj);
    }
}
