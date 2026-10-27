package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableReduce$ReduceSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -4663883003264602070L;
    final io.reactivex.rxjava3.functions.c reducer;
    tg2 upstream;

    public FlowableReduce$ReduceSubscriber(rg2 rg2Var, io.reactivex.rxjava3.functions.c cVar) {
        super(rg2Var);
        this.reducer = cVar;
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, p.tg2
    public final void cancel() {
        super.cancel();
        this.upstream.cancel();
        this.upstream = SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        tg2 tg2Var = this.upstream;
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var == subscriptionHelper) {
            return;
        }
        this.upstream = subscriptionHelper;
        T t = this.value;
        if (t != null) {
            c(t);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        tg2 tg2Var = this.upstream;
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var == subscriptionHelper) {
            RxJavaPlugins.b(th);
        } else {
            this.upstream = subscriptionHelper;
            this.downstream.onError(th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.upstream == SubscriptionHelper.c) {
            return;
        }
        T t = this.value;
        if (t == null) {
            this.value = obj;
            return;
        }
        try {
            T t2 = (T) this.reducer.k(t, obj);
            Objects.requireNonNull(t2, "The reducer returned a null value");
            this.value = t2;
        } catch (Throwable th) {
            he1.R(th);
            this.upstream.cancel();
            onError(th);
        }
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
