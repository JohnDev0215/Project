package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.g;
import spi.k4;
import spi.os;
import spi.ps;
import spi.q6;
import spi.r6;
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class DisposableAutoReleaseSubscriber<T> extends AtomicReference<ps> implements os, q6 {
    private static final long serialVersionUID = 8924480688481408726L;
    public final AtomicReference<r6> composite;
    public final g onComplete;
    public final k4<? super Throwable> onError;
    public final k4<? super T> onNext;

    public DisposableAutoReleaseSubscriber(r6 r6Var, k4<? super T> k4Var, k4<? super Throwable> k4Var2, g gVar) {
        this.onNext = k4Var;
        this.onError = k4Var2;
        this.onComplete = gVar;
        this.composite = new AtomicReference<>(r6Var);
    }

    @Override // spi.q6
    public void dispose() {
        SubscriptionHelper.cancel(this);
        removeSelf();
    }

    public boolean hasCustomOnError() {
        return this.onError != vc.b;
    }

    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == get();
    }

    @Override // spi.os
    public void onComplete() {
        ps psVar = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (psVar != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                Objects.requireNonNull(this.onComplete);
            } catch (Throwable th) {
                x8.a(th);
                vn.a(th);
            }
        }
        removeSelf();
    }

    @Override // spi.os
    public void onError(Throwable th) {
        ps psVar = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (psVar != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                x8.a(th2);
                vn.a(new CompositeException(th, th2));
            }
        } else {
            vn.a(th);
        }
        removeSelf();
    }

    @Override // spi.os
    public void onNext(T t) {
        if (get() != SubscriptionHelper.CANCELLED) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                x8.a(th);
                get().cancel();
                onError(th);
            }
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.setOnce(this, psVar)) {
            psVar.request(Long.MAX_VALUE);
        }
    }

    public void removeSelf() {
        r6 andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.a();
        }
    }
}
