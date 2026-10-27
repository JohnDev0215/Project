package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class SingleTakeUntil$TakeUntilMainObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -622603812305745221L;
    final SingleObserver<? super T> downstream;
    final SingleTakeUntil$TakeUntilOtherSubscriber other = new SingleTakeUntil$TakeUntilOtherSubscriber(this);

    public SingleTakeUntil$TakeUntilMainObserver(SingleObserver singleObserver) {
        this.downstream = singleObserver;
    }

    public final void a(Throwable th) {
        Disposable andSet;
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || (andSet = getAndSet(disposableHelper)) == disposableHelper) {
            RxJavaPlugins.b(th);
            return;
        }
        if (andSet != null) {
            andSet.dispose();
        }
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
        SingleTakeUntil$TakeUntilOtherSubscriber singleTakeUntil$TakeUntilOtherSubscriber = this.other;
        singleTakeUntil$TakeUntilOtherSubscriber.getClass();
        SubscriptionHelper.b(singleTakeUntil$TakeUntilOtherSubscriber);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        SingleTakeUntil$TakeUntilOtherSubscriber singleTakeUntil$TakeUntilOtherSubscriber = this.other;
        singleTakeUntil$TakeUntilOtherSubscriber.getClass();
        SubscriptionHelper.b(singleTakeUntil$TakeUntilOtherSubscriber);
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || getAndSet(disposableHelper) == disposableHelper) {
            RxJavaPlugins.b(th);
        } else {
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        SingleTakeUntil$TakeUntilOtherSubscriber singleTakeUntil$TakeUntilOtherSubscriber = this.other;
        singleTakeUntil$TakeUntilOtherSubscriber.getClass();
        SubscriptionHelper.b(singleTakeUntil$TakeUntilOtherSubscriber);
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (getAndSet(disposableHelper) != disposableHelper) {
            this.downstream.onSuccess(obj);
        }
    }
}
