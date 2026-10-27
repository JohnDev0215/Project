package spi;

import io.reactivex.rxjava3.internal.operators.flowable.FlowableGroupBy$GroupBySubscriber;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class fc<T, K> extends BasicIntQueueSubscription<T> implements ml<T> {
    private static final long serialVersionUID = -3852313036005250360L;
    public final K a;
    public final zr<T> b;
    public final FlowableGroupBy$GroupBySubscriber<?, K, T> c;
    public final boolean d;
    public volatile boolean f;
    public Throwable g;
    public int j;
    public final AtomicLong e = new AtomicLong();
    public final AtomicBoolean h = new AtomicBoolean();
    public final AtomicReference<os<? super T>> i = new AtomicReference<>();
    public final AtomicInteger k = new AtomicInteger();
    public final AtomicBoolean l = new AtomicBoolean();

    public fc(int i, FlowableGroupBy$GroupBySubscriber<?, K, T> flowableGroupBy$GroupBySubscriber, K k, boolean z) {
        this.b = new zr<>(i);
        this.c = flowableGroupBy$GroupBySubscriber;
        this.a = k;
        this.d = z;
    }

    public final boolean a(boolean z, boolean z2, os<? super T> osVar, boolean z3, long j, boolean z4) {
        if (this.h.get()) {
            while (this.b.b() != null) {
                j++;
            }
            c(j, z4);
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            this.h.lazySet(true);
            Throwable th = this.g;
            if (th != null) {
                osVar.onError(th);
            } else {
                osVar.onComplete();
                c(j, z4);
            }
            return true;
        }
        Throwable th2 = this.g;
        if (th2 != null) {
            this.b.clear();
            this.h.lazySet(true);
            osVar.onError(th2);
            return true;
        }
        if (!z2) {
            return false;
        }
        this.h.lazySet(true);
        osVar.onComplete();
        c(j, z4);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0019, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r27 = this;
            r8 = r27
            int r0 = r27.getAndIncrement()
            if (r0 == 0) goto L9
            return
        L9:
            spi.zr<T> r9 = r8.b
            boolean r10 = r8.d
            java.util.concurrent.atomic.AtomicReference<spi.os<? super T>> r0 = r8.i
            java.lang.Object r0 = r0.get()
            spi.os r0 = (spi.os) r0
            java.util.concurrent.atomic.AtomicBoolean r11 = r8.h
            r13 = r0
            r14 = 1
        L19:
            boolean r0 = r11.get()
            r15 = 1
            r7 = 0
            r17 = 0
            if (r0 == 0) goto L35
            r0 = r17
        L26:
            spi.zr<T> r2 = r8.b
            java.lang.Object r2 = r2.b()
            if (r2 == 0) goto L30
            long r0 = r0 + r15
            goto L26
        L30:
            r8.c(r0, r7)
            goto L98
        L35:
            if (r13 == 0) goto L98
            java.util.concurrent.atomic.AtomicLong r0 = r8.e
            long r19 = r0.get()
            r5 = r17
        L3f:
            int r21 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r21 == 0) goto L74
            boolean r1 = r8.f
            java.lang.Object r4 = r9.b()
            if (r4 != 0) goto L4e
            r22 = 1
            goto L50
        L4e:
            r22 = r7
        L50:
            r23 = r22 ^ 1
            r0 = r27
            r2 = r22
            r3 = r13
            r12 = r4
            r4 = r10
            r24 = r5
            r26 = r7
            r7 = r23
            boolean r0 = r0.a(r1, r2, r3, r4, r5, r7)
            if (r0 == 0) goto L66
            goto L19
        L66:
            if (r22 == 0) goto L6b
            r5 = r24
            goto L74
        L6b:
            r13.onNext(r12)
            r5 = r24
            long r5 = r5 + r15
            r7 = r26
            goto L3f
        L74:
            if (r21 != 0) goto L8c
            boolean r1 = r8.f
            boolean r2 = r9.a()
            r7 = 0
            r0 = r27
            r3 = r13
            r4 = r10
            r24 = r5
            boolean r0 = r0.a(r1, r2, r3, r4, r5, r7)
            if (r0 == 0) goto L8a
            goto L19
        L8a:
            r5 = r24
        L8c:
            int r0 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
            if (r0 == 0) goto L98
            java.util.concurrent.atomic.AtomicLong r0 = r8.e
            spi.z0.c(r0, r5)
            r8.d(r5)
        L98:
            int r0 = -r14
            int r14 = r8.addAndGet(r0)
            if (r14 != 0) goto La0
            return
        La0:
            if (r13 != 0) goto L19
            java.util.concurrent.atomic.AtomicReference<spi.os<? super T>> r0 = r8.i
            java.lang.Object r0 = r0.get()
            r13 = r0
            spi.os r13 = (spi.os) r13
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.fc.b():void");
    }

    public final void c(long j, boolean z) {
        if (z) {
            j++;
        }
        if (j != 0) {
            d(j);
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription, spi.ps
    public final void cancel() {
        if (this.h.compareAndSet(false, true)) {
            if ((this.k.get() & 2) == 0 && this.l.compareAndSet(false, true)) {
                this.c.cancel(this.a);
            }
            b();
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription, spi.kp
    public final void clear() {
        zr<T> zrVar = this.b;
        while (zrVar.b() != null) {
            this.j++;
        }
        e();
    }

    public final void d(long j) {
        if ((this.k.get() & 2) == 0) {
            this.c.requestGroup(j);
        }
    }

    public final void e() {
        int i = this.j;
        if (i != 0) {
            this.j = 0;
            d(i);
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription
    public final boolean isEmpty() {
        boolean zA = this.b.a();
        e();
        return zA;
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription
    public final T poll() {
        T tB = this.b.b();
        if (tB != null) {
            this.j++;
            return tB;
        }
        e();
        return null;
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription, spi.ps
    public final void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            z0.a(this.e, j);
            b();
        }
    }

    @Override // io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription, spi.rl
    public final int requestFusion(int i) {
        return 0;
    }
}
