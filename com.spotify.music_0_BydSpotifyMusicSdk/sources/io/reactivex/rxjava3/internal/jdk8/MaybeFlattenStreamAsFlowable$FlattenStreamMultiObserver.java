package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import p.ci1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
final class MaybeFlattenStreamAsFlowable$FlattenStreamMultiObserver<T, R> extends BasicIntQueueSubscription<R> implements MaybeObserver<T>, SingleObserver<T> {
    private static final long serialVersionUID = 7363336003027148283L;
    volatile boolean cancelled;
    AutoCloseable close;
    final rg2 downstream;
    long emitted;
    volatile Iterator<? extends R> iterator;
    final io.reactivex.rxjava3.functions.o mapper;
    boolean once;
    boolean outputFused;
    final AtomicLong requested = new AtomicLong();
    Disposable upstream;

    public MaybeFlattenStreamAsFlowable$FlattenStreamMultiObserver(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
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
        long j = this.emitted;
        long j2 = this.requested.get();
        Iterator<? extends R> it = this.iterator;
        int iAddAndGet = 1;
        while (true) {
            if (this.cancelled) {
                clear();
            } else if (this.outputFused) {
                if (it != null) {
                    rg2Var.onNext(null);
                    rg2Var.onComplete();
                }
            } else if (it != null && j != j2) {
                try {
                    R next = it.next();
                    if (!this.cancelled) {
                        rg2Var.onNext(next);
                        j++;
                        if (!this.cancelled) {
                            try {
                                boolean zHasNext = it.hasNext();
                                if (!this.cancelled && !zHasNext) {
                                    rg2Var.onComplete();
                                    this.cancelled = true;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                rg2Var.onError(th);
                                this.cancelled = true;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    rg2Var.onError(th2);
                    this.cancelled = true;
                }
            }
            this.emitted = j;
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
            j2 = this.requested.get();
            if (it == null) {
                it = this.iterator;
            }
        }
    }

    @Override // p.tg2
    public final void cancel() {
        this.cancelled = true;
        this.upstream.dispose();
        if (this.outputFused) {
            return;
        }
        c();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.iterator = null;
        AutoCloseable autoCloseable = this.close;
        this.close = null;
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        Iterator<? extends R> it = this.iterator;
        if (it == null) {
            return true;
        }
        if (!this.once || it.hasNext()) {
            return false;
        }
        clear();
        return true;
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null Stream");
            Stream stream = (Stream) objApply;
            Iterator<? extends R> it = stream.iterator();
            if (it.hasNext()) {
                this.iterator = it;
                this.close = stream;
                c();
            } else {
                this.downstream.onComplete();
                try {
                    stream.close();
                } catch (Throwable th) {
                    he1.R(th);
                    RxJavaPlugins.b(th);
                }
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(th2);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator<? extends R> it = this.iterator;
        if (it == null) {
            return null;
        }
        if (!this.once) {
            this.once = true;
        } else if (!it.hasNext()) {
            clear();
            return null;
        }
        return it.next();
    }
}
