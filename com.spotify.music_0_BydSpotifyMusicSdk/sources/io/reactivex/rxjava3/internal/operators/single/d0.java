package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class d0 implements FlowableSubscriber, Disposable {
    public final SingleObserver c;
    public tg2 e;
    public Object k;
    public boolean l;
    public volatile boolean m;

    public d0(SingleObserver singleObserver) {
        this.c = singleObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m = true;
        this.e.cancel();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        Object obj = this.k;
        this.k = null;
        SingleObserver singleObserver = this.c;
        if (obj == null) {
            singleObserver.onError(new NoSuchElementException("The source Publisher is empty"));
        } else {
            singleObserver.onSuccess(obj);
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
            return;
        }
        this.l = true;
        this.k = null;
        this.c.onError(th);
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        if (this.k == null) {
            this.k = obj;
            return;
        }
        this.e.cancel();
        this.l = true;
        this.k = null;
        this.c.onError(new IndexOutOfBoundsException("Too many elements in the Publisher"));
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
