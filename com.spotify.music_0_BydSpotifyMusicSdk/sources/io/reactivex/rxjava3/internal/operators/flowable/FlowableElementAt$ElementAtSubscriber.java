package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableElementAt$ElementAtSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 4066607327284737757L;
    long count;
    final T defaultValue;
    boolean done;
    final boolean errorOnFewer;
    final long index;
    tg2 upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowableElementAt$ElementAtSubscriber(rg2 rg2Var, long j, Object obj, boolean z) {
        super(rg2Var);
        this.index = j;
        this.defaultValue = obj;
        this.errorOnFewer = z;
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, p.tg2
    public final void cancel() {
        super.cancel();
        this.upstream.cancel();
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        T t = this.defaultValue;
        if (t != null) {
            c(t);
        } else if (this.errorOnFewer) {
            this.downstream.onError(new NoSuchElementException());
        } else {
            this.downstream.onComplete();
        }
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
        if (this.done) {
            return;
        }
        long j = this.count;
        if (j != this.index) {
            this.count = j + 1;
            return;
        }
        this.done = true;
        this.upstream.cancel();
        c(obj);
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
