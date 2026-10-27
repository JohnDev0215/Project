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
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class BoundedSubscriber<T> extends AtomicReference<ps> implements os, ps, q6 {
    private static final long serialVersionUID = -7251123623727029452L;
    public final int bufferSize;
    public int consumed;
    public final int limit;
    public final g onComplete;
    public final k4<? super Throwable> onError;
    public final k4<? super T> onNext;
    public final k4<? super ps> onSubscribe;

    public BoundedSubscriber(k4<? super T> k4Var, k4<? super Throwable> k4Var2, g gVar, k4<? super ps> k4Var3, int i) {
        this.onNext = k4Var;
        this.onError = k4Var2;
        this.onComplete = gVar;
        this.onSubscribe = k4Var3;
        this.bufferSize = i;
        this.limit = i - (i >> 2);
    }

    @Override // spi.ps
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    @Override // spi.q6
    public void dispose() {
        cancel();
    }

    public boolean hasCustomOnError() {
        return this.onError != vc.b;
    }

    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
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
    }

    @Override // spi.os
    public void onError(Throwable th) {
        ps psVar = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (psVar == subscriptionHelper) {
            vn.a(th);
            return;
        }
        lazySet(subscriptionHelper);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(new CompositeException(th, th2));
        }
    }

    @Override // spi.os
    public void onNext(T t) {
        if (isDisposed()) {
            return;
        }
        try {
            this.onNext.accept(t);
            int i = this.consumed + 1;
            if (i == this.limit) {
                this.consumed = 0;
                get().request(this.limit);
            } else {
                this.consumed = i;
            }
        } catch (Throwable th) {
            x8.a(th);
            get().cancel();
            onError(th);
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.setOnce(this, psVar)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                x8.a(th);
                psVar.cancel();
                onError(th);
            }
        }
    }

    @Override // spi.ps
    public void request(long j) {
        get().request(j);
    }
}
