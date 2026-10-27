package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableInternalHelper$RequestMax;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class LambdaSubscriber<T> extends AtomicReference<tg2> implements FlowableSubscriber<T>, tg2, Disposable, io.reactivex.rxjava3.observers.b {
    private static final long serialVersionUID = -7251123623727029452L;
    final io.reactivex.rxjava3.functions.a onComplete;
    final g onError;
    final g onNext;
    final g onSubscribe;

    public LambdaSubscriber(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        FlowableInternalHelper$RequestMax flowableInternalHelper$RequestMax = FlowableInternalHelper$RequestMax.c;
        this.onNext = gVar;
        this.onError = gVar2;
        this.onComplete = aVar;
        this.onSubscribe = flowableInternalHelper$RequestMax;
    }

    @Override // p.tg2
    public final void a(long j) {
        get().a(j);
    }

    @Override // p.tg2
    public final void cancel() {
        SubscriptionHelper.b(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.b(this);
    }

    @Override // io.reactivex.rxjava3.observers.b
    public final boolean hasCustomOnError() {
        return this.onError != k.f;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.c;
    }

    @Override // p.rg2
    public final void onComplete() {
        tg2 tg2Var = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                this.onComplete.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        tg2 tg2Var = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var == subscriptionHelper) {
            RxJavaPlugins.b(th);
            return;
        }
        lazySet(subscriptionHelper);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(new CompositeException(th, th2));
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (isDisposed()) {
            return;
        }
        try {
            this.onNext.accept(obj);
        } catch (Throwable th) {
            he1.R(th);
            get().cancel();
            onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                he1.R(th);
                tg2Var.cancel();
                onError(th);
            }
        }
    }
}
