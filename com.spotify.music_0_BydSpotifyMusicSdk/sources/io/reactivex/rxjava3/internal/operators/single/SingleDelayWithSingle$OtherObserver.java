package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class SingleDelayWithSingle$OtherObserver<T, U> extends AtomicReference<Disposable> implements SingleObserver<U>, Disposable {
    private static final long serialVersionUID = -8565274649390031272L;
    final SingleObserver<? super T> downstream;
    final SingleSource<T> source;

    public SingleDelayWithSingle$OtherObserver(SingleObserver singleObserver, SingleSource singleSource) {
        this.downstream = singleObserver;
        this.source = singleSource;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.source.subscribe(new io.reactivex.rxjava3.internal.observers.l(this, this.downstream, 0));
    }
}
