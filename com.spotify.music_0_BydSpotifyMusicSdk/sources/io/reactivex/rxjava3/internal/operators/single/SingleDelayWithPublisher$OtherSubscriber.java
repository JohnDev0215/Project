package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class SingleDelayWithPublisher$OtherSubscriber<T, U> extends AtomicReference<Disposable> implements FlowableSubscriber<U>, Disposable {
    private static final long serialVersionUID = -8565274649390031272L;
    boolean done;
    final SingleObserver<? super T> downstream;
    final SingleSource<T> source;
    tg2 upstream;

    public SingleDelayWithPublisher$OtherSubscriber(SingleObserver singleObserver, SingleSource singleSource) {
        this.downstream = singleObserver;
        this.source = singleSource;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.cancel();
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.source.subscribe(new io.reactivex.rxjava3.internal.observers.l(this, this.downstream, 0));
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
        } else {
            this.done = true;
            this.downstream.onError(th);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        this.upstream.cancel();
        onComplete();
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
