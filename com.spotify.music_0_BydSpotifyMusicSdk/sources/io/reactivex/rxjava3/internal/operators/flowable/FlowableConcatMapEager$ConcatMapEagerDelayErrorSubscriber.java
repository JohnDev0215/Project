package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p.az1;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableConcatMapEager$ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, tg2, io.reactivex.rxjava3.internal.subscribers.c {
    private static final long serialVersionUID = -4255299542215038287L;
    volatile boolean cancelled;
    volatile InnerQueuedSubscriber<R> current;
    volatile boolean done;
    final rg2 downstream;
    final ErrorMode errorMode;
    final io.reactivex.rxjava3.functions.o mapper;
    final int maxConcurrency;
    final int prefetch;
    final io.reactivex.rxjava3.operators.h subscribers;
    tg2 upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();

    public FlowableConcatMapEager$ConcatMapEagerDelayErrorSubscriber(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar, int i, int i2, ErrorMode errorMode) {
        this.downstream = rg2Var;
        this.mapper = oVar;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = errorMode;
        this.subscribers = new io.reactivex.rxjava3.operators.h(Math.min(i2, i));
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            b();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c1, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c2, code lost:
    
        if (r12 != r6) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c6, code lost:
    
        if (r18.cancelled == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c8, code lost:
    
        g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cb, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00cc, code lost:
    
        if (r3 != r15) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        if (r18.errors.get() == null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d8, code lost:
    
        r18.current = null;
        r8.getClass();
        io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper.b(r8);
        g();
        r18.errors.e(r18.downstream);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ea, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00eb, code lost:
    
        r10 = r8.b();
        r11 = r11.isEmpty();
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f3, code lost:
    
        if (r10 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00f5, code lost:
    
        if (r11 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f7, code lost:
    
        r18.current = null;
        r18.upstream.a(1);
        r8 = null;
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0100, code lost:
    
        r4 = 0;
     */
    @Override // io.reactivex.rxjava3.internal.subscribers.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMapEager$ConcatMapEagerDelayErrorSubscriber.b():void");
    }

    @Override // io.reactivex.rxjava3.internal.subscribers.c
    public final void c(InnerQueuedSubscriber innerQueuedSubscriber) {
        innerQueuedSubscriber.e();
        b();
    }

    @Override // p.tg2
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        this.errors.b();
        if (getAndIncrement() == 0) {
            do {
                g();
            } while (decrementAndGet() != 0);
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscribers.c
    public final void e(InnerQueuedSubscriber innerQueuedSubscriber, Throwable th) {
        if (this.errors.a(th)) {
            innerQueuedSubscriber.e();
            if (this.errorMode != ErrorMode.k) {
                this.upstream.cancel();
            }
            b();
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscribers.c
    public final void f(InnerQueuedSubscriber innerQueuedSubscriber, Object obj) {
        if (innerQueuedSubscriber.c().offer(obj)) {
            b();
        } else {
            SubscriptionHelper.b(innerQueuedSubscriber);
            e(innerQueuedSubscriber, new MissingBackpressureException());
        }
    }

    public final void g() {
        InnerQueuedSubscriber<R> innerQueuedSubscriber = this.current;
        this.current = null;
        if (innerQueuedSubscriber != null) {
            SubscriptionHelper.b(innerQueuedSubscriber);
        }
        while (true) {
            InnerQueuedSubscriber innerQueuedSubscriber2 = (InnerQueuedSubscriber) this.subscribers.poll();
            if (innerQueuedSubscriber2 == null) {
                return;
            } else {
                SubscriptionHelper.b(innerQueuedSubscriber2);
            }
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        b();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            this.done = true;
            b();
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
            az1 az1Var = (az1) objApply;
            InnerQueuedSubscriber innerQueuedSubscriber = new InnerQueuedSubscriber(this, this.prefetch);
            if (this.cancelled) {
                return;
            }
            this.subscribers.offer(innerQueuedSubscriber);
            az1Var.subscribe(innerQueuedSubscriber);
            if (this.cancelled) {
                SubscriptionHelper.b(innerQueuedSubscriber);
                if (getAndIncrement() == 0) {
                    do {
                        g();
                    } while (decrementAndGet() != 0);
                }
            }
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
            tg2Var.a(i == Integer.MAX_VALUE ? Long.MAX_VALUE : i);
        }
    }
}
