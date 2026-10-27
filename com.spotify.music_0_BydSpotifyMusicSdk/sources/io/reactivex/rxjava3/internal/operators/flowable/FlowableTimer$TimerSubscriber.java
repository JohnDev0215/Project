package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableTimer$TimerSubscriber extends AtomicReference<Disposable> implements tg2, Runnable {
    private static final long serialVersionUID = -2809475196591179431L;
    final rg2 downstream;
    volatile boolean requested;

    public FlowableTimer$TimerSubscriber(rg2 rg2Var) {
        this.downstream = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            this.requested = true;
        }
    }

    @Override // p.tg2
    public final void cancel() {
        DisposableHelper.a(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (get() != DisposableHelper.c) {
            boolean z = this.requested;
            EmptyDisposable emptyDisposable = EmptyDisposable.c;
            if (!z) {
                lazySet(emptyDisposable);
                this.downstream.onError(new MissingBackpressureException("Can't deliver value due to lack of requests"));
            } else {
                this.downstream.onNext(0L);
                lazySet(emptyDisposable);
                this.downstream.onComplete();
            }
        }
    }
}
