package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class j implements FlowableSubscriber, Disposable {
    public final SingleObserver c;
    public final long e;
    public final Object k;
    public tg2 l;
    public long m;
    public boolean n;

    public j(SingleObserver singleObserver, long j, Object obj) {
        this.c = singleObserver;
        this.e = j;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.cancel();
        this.l = SubscriptionHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l == SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        this.l = SubscriptionHelper.c;
        if (this.n) {
            return;
        }
        this.n = true;
        SingleObserver singleObserver = this.c;
        Object obj = this.k;
        if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NoSuchElementException());
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.n) {
            RxJavaPlugins.b(th);
            return;
        }
        this.n = true;
        this.l = SubscriptionHelper.c;
        this.c.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.n) {
            return;
        }
        long j = this.m;
        if (j != this.e) {
            this.m = j + 1;
            return;
        }
        this.n = true;
        this.l.cancel();
        this.l = SubscriptionHelper.c;
        this.c.onSuccess(obj);
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.l, tg2Var)) {
            this.l = tg2Var;
            this.c.onSubscribe(this);
            tg2Var.a(this.e + 1);
        }
    }
}
