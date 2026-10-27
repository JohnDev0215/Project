package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.d;
import java.util.concurrent.atomic.AtomicInteger;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class ScalarSubscription<T> extends AtomicInteger implements d {
    private static final long serialVersionUID = -3830916580126663321L;
    final rg2 subscriber;
    final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public ScalarSubscription(Object obj, rg2 rg2Var) {
        this.subscriber = rg2Var;
        this.value = obj;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j) && compareAndSet(0, 1)) {
            rg2 rg2Var = this.subscriber;
            rg2Var.onNext(this.value);
            if (get() != 2) {
                rg2Var.onComplete();
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 1;
    }

    @Override // p.tg2
    public final void cancel() {
        lazySet(2);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return get() != 0;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }
}
