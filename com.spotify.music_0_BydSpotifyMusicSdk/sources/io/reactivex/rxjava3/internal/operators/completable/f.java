package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class f implements FlowableSubscriber, Disposable {
    public final CompletableObserver c;
    public tg2 e;

    public f(CompletableObserver completableObserver) {
        this.c = completableObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.cancel();
        this.e = SubscriptionHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e == SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.e, tg2Var)) {
            this.e = tg2Var;
            this.c.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
