package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class DisposableAutoReleaseSubscriber<T> extends AtomicReference<tg2> implements FlowableSubscriber<T>, Disposable, io.reactivex.rxjava3.observers.b {
    private static final long serialVersionUID = 8924480688481408726L;
    final AtomicReference<io.reactivex.rxjava3.disposables.b> composite;
    final io.reactivex.rxjava3.functions.a onComplete;
    final g onError;
    final g onNext;

    public DisposableAutoReleaseSubscriber(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.disposables.b bVar) {
        this.onNext = gVar;
        this.onError = gVar2;
        this.onComplete = aVar;
        this.composite = new AtomicReference<>(bVar);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.b(this);
        io.reactivex.rxjava3.disposables.b andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.b(this);
        }
    }

    @Override // io.reactivex.rxjava3.observers.b
    public final boolean hasCustomOnError() {
        return this.onError != k.f;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.c == get();
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
        io.reactivex.rxjava3.disposables.b andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.b(this);
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        tg2 tg2Var = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.c;
        if (tg2Var != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(th, th2));
            }
        } else {
            RxJavaPlugins.b(th);
        }
        io.reactivex.rxjava3.disposables.b andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.b(this);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (get() != SubscriptionHelper.c) {
            try {
                this.onNext.accept(obj);
            } catch (Throwable th) {
                he1.R(th);
                get().cancel();
                onError(th);
            }
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.e(this, tg2Var)) {
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
