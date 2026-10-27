package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class SingleTakeUntil$TakeUntilOtherSubscriber extends AtomicReference<tg2> implements FlowableSubscriber<Object> {
    private static final long serialVersionUID = 5170026210238877381L;
    final SingleTakeUntil$TakeUntilMainObserver<?> parent;

    public SingleTakeUntil$TakeUntilOtherSubscriber(SingleTakeUntil$TakeUntilMainObserver singleTakeUntil$TakeUntilMainObserver) {
        this.parent = singleTakeUntil$TakeUntilMainObserver;
    }

    @Override // p.rg2
    public final void onComplete() {
        tg2 tg2Var = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var != subscriptionHelper) {
            lazySet(subscriptionHelper);
            this.parent.a(new CancellationException());
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.parent.a(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (SubscriptionHelper.b(this)) {
            this.parent.a(new CancellationException());
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
