package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class r0 implements FlowableSubscriber, Disposable {
    public final SingleObserver c;
    public final Object e;
    public tg2 k;
    public boolean l;
    public Object m;

    public r0(SingleObserver singleObserver, Object obj) {
        this.c = singleObserver;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.cancel();
        this.k = SubscriptionHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k == SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.k = SubscriptionHelper.c;
        Object obj = this.m;
        this.m = null;
        if (obj == null) {
            obj = this.e;
        }
        SingleObserver singleObserver = this.c;
        if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NoSuchElementException());
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
            return;
        }
        this.l = true;
        this.k = SubscriptionHelper.c;
        this.c.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        if (this.m == null) {
            this.m = obj;
            return;
        }
        this.l = true;
        this.k.cancel();
        this.k = SubscriptionHelper.c;
        this.c.onError(new IllegalArgumentException("Sequence contains more than one element!"));
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.k, tg2Var)) {
            this.k = tg2Var;
            this.c.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
