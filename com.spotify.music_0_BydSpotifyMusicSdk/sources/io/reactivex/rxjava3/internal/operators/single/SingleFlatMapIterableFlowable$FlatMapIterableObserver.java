package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import p.ci1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapIterableFlowable$FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements SingleObserver<T> {
    private static final long serialVersionUID = -8938804753851907758L;
    volatile boolean cancelled;
    final rg2 downstream;
    volatile Iterator<? extends R> it;
    final io.reactivex.rxjava3.functions.o mapper;
    boolean outputFused;
    final AtomicLong requested = new AtomicLong();
    Disposable upstream;

    public SingleFlatMapIterableFlowable$FlatMapIterableObserver(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = rg2Var;
        this.mapper = oVar;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            c();
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    public final void c() {
        if (getAndIncrement() != 0) {
            return;
        }
        rg2 rg2Var = this.downstream;
        Iterator<? extends R> it = this.it;
        if (this.outputFused && it != null) {
            rg2Var.onNext(null);
            rg2Var.onComplete();
            return;
        }
        int iAddAndGet = 1;
        while (true) {
            if (it != null) {
                long j = this.requested.get();
                if (j == Long.MAX_VALUE) {
                    while (!this.cancelled) {
                        try {
                            rg2Var.onNext(it.next());
                            if (this.cancelled) {
                                return;
                            }
                            try {
                                if (!it.hasNext()) {
                                    rg2Var.onComplete();
                                    return;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                rg2Var.onError(th);
                                return;
                            }
                        } catch (Throwable th2) {
                            he1.R(th2);
                            rg2Var.onError(th2);
                            return;
                        }
                    }
                    return;
                }
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        return;
                    }
                    try {
                        R next = it.next();
                        Objects.requireNonNull(next, "The iterator returned a null value");
                        rg2Var.onNext(next);
                        if (this.cancelled) {
                            return;
                        }
                        j2++;
                        try {
                            if (!it.hasNext()) {
                                rg2Var.onComplete();
                                return;
                            }
                        } catch (Throwable th3) {
                            he1.R(th3);
                            rg2Var.onError(th3);
                            return;
                        }
                    } catch (Throwable th4) {
                        he1.R(th4);
                        rg2Var.onError(th4);
                        return;
                    }
                }
                if (j2 != 0) {
                    ci1.A(this.requested, j2);
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
            if (it == null) {
                it = this.it;
            }
        }
    }

    @Override // p.tg2
    public final void cancel() {
        this.cancelled = true;
        this.upstream.dispose();
        this.upstream = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.it = null;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.it == null;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.upstream = DisposableHelper.c;
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Iterator<? extends R> it = ((Iterable) this.mapper.apply(obj)).iterator();
            if (!it.hasNext()) {
                this.downstream.onComplete();
            } else {
                this.it = it;
                c();
            }
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator<? extends R> it = this.it;
        if (it == null) {
            return null;
        }
        R next = it.next();
        Objects.requireNonNull(next, "The iterator returned a null value");
        if (!it.hasNext()) {
            this.it = null;
        }
        return next;
    }
}
