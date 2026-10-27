package io.reactivex.rxjava3.internal.subscriptions;

import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public class DeferredScalarSubscription<T> extends BasicIntQueueSubscription<T> {
    private static final long serialVersionUID = -2151279923272604993L;
    protected final rg2 downstream;
    protected T value;

    public DeferredScalarSubscription(rg2 rg2Var) {
        this.downstream = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        T t;
        if (SubscriptionHelper.f(j)) {
            do {
                int i = get();
                if ((i & (-2)) != 0) {
                    return;
                }
                if (i == 1) {
                    if (!compareAndSet(1, 3) || (t = this.value) == null) {
                        return;
                    }
                    this.value = null;
                    rg2 rg2Var = this.downstream;
                    rg2Var.onNext(t);
                    if (get() != 4) {
                        rg2Var.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet(0, 2));
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(Object obj) {
        int i = get();
        while (i != 8) {
            if ((i & (-3)) != 0) {
                return;
            }
            if (i == 2) {
                lazySet(3);
                rg2 rg2Var = this.downstream;
                rg2Var.onNext(obj);
                if (get() != 4) {
                    rg2Var.onComplete();
                    return;
                }
                return;
            }
            this.value = obj;
            if (compareAndSet(0, 1)) {
                return;
            }
            i = get();
            if (i == 4) {
                this.value = null;
                return;
            }
        }
        this.value = obj;
        lazySet(16);
        rg2 rg2Var2 = this.downstream;
        rg2Var2.onNext(null);
        if (get() != 4) {
            rg2Var2.onComplete();
        }
    }

    public void cancel() {
        set(4);
        this.value = null;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return get() != 16;
    }

    public void onSuccess(Object obj) {
        c(obj);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        if (get() != 16) {
            return null;
        }
        lazySet(32);
        T t = this.value;
        this.value = null;
        return t;
    }
}
