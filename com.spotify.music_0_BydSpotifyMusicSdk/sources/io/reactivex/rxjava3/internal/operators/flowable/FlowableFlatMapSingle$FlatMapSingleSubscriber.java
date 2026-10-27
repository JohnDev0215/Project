package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableFlatMapSingle$FlatMapSingleSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = 8600231336733376951L;
    volatile boolean cancelled;
    final boolean delayErrors;
    final rg2 downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    final int maxConcurrency;
    tg2 upstream;
    final AtomicLong requested = new AtomicLong();
    final io.reactivex.rxjava3.disposables.a set = new io.reactivex.rxjava3.disposables.a();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicInteger active = new AtomicInteger(1);
    final AtomicReference<io.reactivex.rxjava3.operators.h> queue = new AtomicReference<>();

    public final class InnerObserver extends AtomicReference<Disposable> implements SingleObserver<R>, Disposable {
        private static final long serialVersionUID = -502562646270949838L;

        public InnerObserver() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.a(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.b(get());
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            FlowableFlatMapSingle$FlatMapSingleSubscriber flowableFlatMapSingle$FlatMapSingleSubscriber = FlowableFlatMapSingle$FlatMapSingleSubscriber.this;
            flowableFlatMapSingle$FlatMapSingleSubscriber.set.b(this);
            if (flowableFlatMapSingle$FlatMapSingleSubscriber.errors.a(th)) {
                if (!flowableFlatMapSingle$FlatMapSingleSubscriber.delayErrors) {
                    flowableFlatMapSingle$FlatMapSingleSubscriber.upstream.cancel();
                    flowableFlatMapSingle$FlatMapSingleSubscriber.set.dispose();
                } else if (flowableFlatMapSingle$FlatMapSingleSubscriber.maxConcurrency != Integer.MAX_VALUE) {
                    flowableFlatMapSingle$FlatMapSingleSubscriber.upstream.a(1L);
                }
                flowableFlatMapSingle$FlatMapSingleSubscriber.active.decrementAndGet();
                if (flowableFlatMapSingle$FlatMapSingleSubscriber.getAndIncrement() == 0) {
                    flowableFlatMapSingle$FlatMapSingleSubscriber.c();
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0070  */
        @Override // io.reactivex.rxjava3.core.SingleObserver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onSuccess(java.lang.Object r7) {
            /*
                r6 = this;
                io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapSingle$FlatMapSingleSubscriber r0 = io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapSingle$FlatMapSingleSubscriber.this
                io.reactivex.rxjava3.disposables.a r1 = r0.set
                r1.b(r6)
                int r1 = r0.get()
                if (r1 != 0) goto L70
                r1 = 0
                r2 = 1
                boolean r3 = r0.compareAndSet(r1, r2)
                if (r3 == 0) goto L70
                java.util.concurrent.atomic.AtomicInteger r3 = r0.active
                int r3 = r3.decrementAndGet()
                if (r3 != 0) goto L1e
                r1 = r2
            L1e:
                java.util.concurrent.atomic.AtomicLong r2 = r0.requested
                long r2 = r2.get()
                r4 = 0
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 == 0) goto L5d
                p.rg2 r2 = r0.downstream
                r2.onNext(r7)
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.operators.h> r7 = r0.queue
                java.lang.Object r7 = r7.get()
                io.reactivex.rxjava3.operators.h r7 = (io.reactivex.rxjava3.operators.h) r7
                if (r1 == 0) goto L49
                if (r7 == 0) goto L41
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L49
            L41:
                io.reactivex.rxjava3.internal.util.AtomicThrowable r7 = r0.errors
                p.rg2 r0 = r0.downstream
                r7.e(r0)
                goto L88
            L49:
                java.util.concurrent.atomic.AtomicLong r7 = r0.requested
                r1 = 1
                p.ci1.A(r7, r1)
                int r7 = r0.maxConcurrency
                r3 = 2147483647(0x7fffffff, float:NaN)
                if (r7 == r3) goto L66
                p.tg2 r7 = r0.upstream
                r7.a(r1)
                goto L66
            L5d:
                io.reactivex.rxjava3.operators.h r1 = r0.e()
                monitor-enter(r1)
                r1.offer(r7)     // Catch: java.lang.Throwable -> L6d
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L6d
            L66:
                int r7 = r0.decrementAndGet()
                if (r7 != 0) goto L85
                goto L88
            L6d:
                r7 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L6d
                throw r7
            L70:
                io.reactivex.rxjava3.operators.h r1 = r0.e()
                monitor-enter(r1)
                r1.offer(r7)     // Catch: java.lang.Throwable -> L89
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L89
                java.util.concurrent.atomic.AtomicInteger r7 = r0.active
                r7.decrementAndGet()
                int r7 = r0.getAndIncrement()
                if (r7 == 0) goto L85
                goto L88
            L85:
                r0.c()
            L88:
                return
            L89:
                r7 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L89
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMapSingle$FlatMapSingleSubscriber.InnerObserver.onSuccess(java.lang.Object):void");
        }
    }

    public FlowableFlatMapSingle$FlatMapSingleSubscriber(int i, io.reactivex.rxjava3.functions.o oVar, rg2 rg2Var, boolean z) {
        this.downstream = rg2Var;
        this.mapper = oVar;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            if (getAndIncrement() == 0) {
                c();
            }
        }
    }

    public final void b() {
        io.reactivex.rxjava3.operators.h hVar = this.queue.get();
        if (hVar != null) {
            hVar.clear();
        }
    }

    public final void c() {
        rg2 rg2Var = this.downstream;
        AtomicInteger atomicInteger = this.active;
        AtomicReference<io.reactivex.rxjava3.operators.h> atomicReference = this.queue;
        int iAddAndGet = 1;
        do {
            long j = this.requested.get();
            long j2 = 0;
            while (true) {
                if (j2 == j) {
                    break;
                }
                if (this.cancelled) {
                    b();
                    return;
                }
                if (!this.delayErrors && this.errors.get() != null) {
                    b();
                    this.errors.e(this.downstream);
                    return;
                }
                boolean z = atomicInteger.get() == 0;
                io.reactivex.rxjava3.operators.h hVar = atomicReference.get();
                Object objPoll = hVar != null ? hVar.poll() : null;
                boolean z2 = objPoll == null;
                if (z && z2) {
                    this.errors.e(rg2Var);
                    return;
                } else {
                    if (z2) {
                        break;
                    }
                    rg2Var.onNext(objPoll);
                    j2++;
                }
            }
            if (j2 == j) {
                if (this.cancelled) {
                    b();
                    return;
                }
                if (!this.delayErrors && this.errors.get() != null) {
                    b();
                    this.errors.e(rg2Var);
                    return;
                }
                boolean z3 = atomicInteger.get() == 0;
                io.reactivex.rxjava3.operators.h hVar2 = atomicReference.get();
                boolean z4 = hVar2 == null || hVar2.isEmpty();
                if (z3 && z4) {
                    this.errors.e(rg2Var);
                    return;
                }
            }
            if (j2 != 0) {
                ci1.A(this.requested, j2);
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.upstream.a(j2);
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
    }

    @Override // p.tg2
    public final void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        this.set.dispose();
        this.errors.b();
    }

    public final io.reactivex.rxjava3.operators.h e() {
        io.reactivex.rxjava3.operators.h hVar = this.queue.get();
        if (hVar != null) {
            return hVar;
        }
        io.reactivex.rxjava3.operators.h hVar2 = new io.reactivex.rxjava3.operators.h(Flowable.c);
        AtomicReference<io.reactivex.rxjava3.operators.h> atomicReference = this.queue;
        while (!atomicReference.compareAndSet(null, hVar2)) {
            if (atomicReference.get() != null) {
                return this.queue.get();
            }
        }
        return hVar2;
    }

    @Override // p.rg2
    public final void onComplete() {
        this.active.decrementAndGet();
        if (getAndIncrement() == 0) {
            c();
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.active.decrementAndGet();
        if (this.errors.a(th)) {
            if (!this.delayErrors) {
                this.set.dispose();
            }
            if (getAndIncrement() == 0) {
                c();
            }
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null SingleSource");
            SingleSource singleSource = (SingleSource) objApply;
            this.active.getAndIncrement();
            InnerObserver innerObserver = new InnerObserver();
            if (this.cancelled || !this.set.c(innerObserver)) {
                return;
            }
            singleSource.subscribe(innerObserver);
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
            int i = this.maxConcurrency;
            if (i == Integer.MAX_VALUE) {
                tg2Var.a(Long.MAX_VALUE);
            } else {
                tg2Var.a(i);
            }
        }
    }
}
