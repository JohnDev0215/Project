package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableBufferBoundary$BufferCloseObserver<T, C extends Collection<? super T>> extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
    private static final long serialVersionUID = -8498650778633225126L;
    final long index;
    final ObservableBufferBoundary$BufferBoundaryObserver<T, C, ?, ?> parent;

    public ObservableBufferBoundary$BufferCloseObserver(ObservableBufferBoundary$BufferBoundaryObserver observableBufferBoundary$BufferBoundaryObserver, long j) {
        this.parent = observableBufferBoundary$BufferBoundaryObserver;
        this.index = j;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            this.parent.a(this, this.index);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper) {
            RxJavaPlugins.b(th);
            return;
        }
        lazySet(disposableHelper);
        ObservableBufferBoundary$BufferBoundaryObserver<T, C, ?, ?> observableBufferBoundary$BufferBoundaryObserver = this.parent;
        DisposableHelper.a(observableBufferBoundary$BufferBoundaryObserver.upstream);
        observableBufferBoundary$BufferBoundaryObserver.observers.b(this);
        observableBufferBoundary$BufferBoundaryObserver.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            disposable.dispose();
            this.parent.a(this, this.index);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
