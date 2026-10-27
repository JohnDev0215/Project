package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTimeout$TimeoutConsumer extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
    private static final long serialVersionUID = 8708641127342403073L;
    final long idx;
    final b5 parent;

    public ObservableTimeout$TimeoutConsumer(long j, b5 b5Var) {
        this.idx = j;
        this.parent = b5Var;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            this.parent.b(this.idx);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
        } else {
            lazySet(disposableHelper);
            this.parent.a(this.idx, th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            disposable.dispose();
            lazySet(disposableHelper);
            this.parent.b(this.idx);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
