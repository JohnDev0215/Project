package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractBackpressureThrottlingSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, tg2 {
    private static final long serialVersionUID = -5050301752721603566L;
    volatile boolean cancelled;
    volatile boolean done;
    final rg2 downstream;
    Throwable error;
    tg2 upstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<R> current = new AtomicReference<>();

    public AbstractBackpressureThrottlingSubscriber(rg2 rg2Var) {
        this.downstream = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        if (SubscriptionHelper.f(j)) {
            ci1.d(this.requested, j);
            c();
        }
    }

    public final boolean b(boolean z, boolean z2, rg2 rg2Var, AtomicReference atomicReference) {
        if (this.cancelled) {
            atomicReference.lazySet(null);
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (th != null) {
            atomicReference.lazySet(null);
            rg2Var.onError(th);
            return true;
        }
        if (!z2) {
            return false;
        }
        rg2Var.onComplete();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        if (r7 != r1.get()) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
    
        r9 = r13.done;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
    
        if (r2.get() != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
    
        if (b(r9, r10, r0, r2) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
    
        if (r7 == 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
    
        p.ci1.A(r1, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
    
        r4 = addAndGet(-r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r13 = this;
            int r0 = r13.getAndIncrement()
            if (r0 == 0) goto L7
            return
        L7:
            p.rg2 r0 = r13.downstream
            java.util.concurrent.atomic.AtomicLong r1 = r13.requested
            java.util.concurrent.atomic.AtomicReference<R> r2 = r13.current
            r3 = 1
            r4 = r3
        Lf:
            r5 = 0
            r7 = r5
        L12:
            long r9 = r1.get()
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r10 = 0
            if (r9 == 0) goto L38
            boolean r9 = r13.done
            r11 = 0
            java.lang.Object r11 = r2.getAndSet(r11)
            if (r11 != 0) goto L26
            r12 = r3
            goto L27
        L26:
            r12 = r10
        L27:
            boolean r9 = r13.b(r9, r12, r0, r2)
            if (r9 == 0) goto L2e
            return
        L2e:
            if (r12 == 0) goto L31
            goto L38
        L31:
            r0.onNext(r11)
            r9 = 1
            long r7 = r7 + r9
            goto L12
        L38:
            long r11 = r1.get()
            int r9 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r9 != 0) goto L50
            boolean r9 = r13.done
            java.lang.Object r11 = r2.get()
            if (r11 != 0) goto L49
            r10 = r3
        L49:
            boolean r9 = r13.b(r9, r10, r0, r2)
            if (r9 == 0) goto L50
            return
        L50:
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 == 0) goto L57
            p.ci1.A(r1, r7)
        L57:
            int r4 = -r4
            int r4 = r13.addAndGet(r4)
            if (r4 != 0) goto Lf
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber.c():void");
    }

    @Override // p.tg2
    public final void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.cancel();
        if (getAndIncrement() == 0) {
            this.current.lazySet(null);
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        c();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        c();
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
