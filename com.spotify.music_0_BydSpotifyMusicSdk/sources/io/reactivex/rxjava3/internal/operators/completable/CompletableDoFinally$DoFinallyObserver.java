package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class CompletableDoFinally$DoFinallyObserver extends AtomicInteger implements CompletableObserver, Disposable {
    private static final long serialVersionUID = 4109457741734051389L;
    final CompletableObserver downstream;
    final io.reactivex.rxjava3.functions.a onFinally;
    Disposable upstream;

    public CompletableDoFinally$DoFinallyObserver(CompletableObserver completableObserver, io.reactivex.rxjava3.functions.a aVar) {
        this.downstream = completableObserver;
        this.onFinally = aVar;
    }

    public final void a() {
        if (compareAndSet(0, 1)) {
            try {
                this.onFinally.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        a();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        this.downstream.onComplete();
        a();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
        a();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
