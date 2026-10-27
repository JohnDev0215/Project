package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableSingle$SingleElementSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -5526049321428043809L;
    final T defaultValue;
    boolean done;
    final boolean failOnEmpty;
    tg2 upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public FlowableSingle$SingleElementSubscriber(rg2 rg2Var, Object obj, boolean z) {
        super(rg2Var);
        this.defaultValue = obj;
        this.failOnEmpty = z;
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
        T t = this.value;
        this.value = null;
        if (t == null) {
            t = this.defaultValue;
        }
        if (t != null) {
            c(t);
        } else if (this.failOnEmpty) {
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (this.value == null) {
            this.value = obj;
            return;
        }
        this.done = true;
        this.upstream.cancel();
        this.downstream.onError(new IllegalArgumentException("Sequence contains more than one element!"));
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
