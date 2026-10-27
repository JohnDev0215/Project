package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.el;
import spi.g;
import spi.k4;
import spi.os;
import spi.ps;
import spi.q6;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class ForEachWhileSubscriber<T> extends AtomicReference<ps> implements os, q6 {
    private static final long serialVersionUID = -4403180040475402120L;
    public boolean done;
    public final g onComplete;
    public final k4<? super Throwable> onError;
    public final el<? super T> onNext;

    public ForEachWhileSubscriber(el<? super T> elVar, k4<? super Throwable> k4Var, g gVar) {
        this.onNext = elVar;
        this.onError = k4Var;
        this.onComplete = gVar;
    }

    @Override // spi.q6
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    @Override // spi.os
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            Objects.requireNonNull(this.onComplete);
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }

    @Override // spi.os
    public void onError(Throwable th) {
        if (this.done) {
            vn.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(new CompositeException(th, th2));
        }
    }

    @Override // spi.os
    public void onNext(T t) {
        if (this.done) {
            return;
        }
        try {
            if (this.onNext.a()) {
                return;
            }
            dispose();
            onComplete();
        } catch (Throwable th) {
            x8.a(th);
            dispose();
            onError(th);
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        SubscriptionHelper.setOnce(this, psVar, Long.MAX_VALUE);
    }
}
