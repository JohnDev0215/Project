package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: WorkQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class vt3 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(vt3.class, Object.class, "lastScheduledTask");
    public static final /* synthetic */ AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(vt3.class, "producerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater d = AtomicIntegerFieldUpdater.newUpdater(vt3.class, "consumerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater e = AtomicIntegerFieldUpdater.newUpdater(vt3.class, "blockingTasksInBuffer");
    public final AtomicReferenceArray<v63> a = new AtomicReferenceArray<>(128);
    private volatile /* synthetic */ Object lastScheduledTask = null;
    private volatile /* synthetic */ int producerIndex = 0;
    private volatile /* synthetic */ int consumerIndex = 0;
    private volatile /* synthetic */ int blockingTasksInBuffer = 0;

    public final v63 a(v63 v63Var, boolean z) {
        if (z) {
            return b(v63Var);
        }
        v63 v63Var2 = (v63) b.getAndSet(this, v63Var);
        if (v63Var2 == null) {
            return null;
        }
        return b(v63Var2);
    }

    public final v63 b(v63 v63Var) {
        if (v63Var.f.b() == 1) {
            e.incrementAndGet(this);
        }
        if (this.producerIndex - this.consumerIndex == 127) {
            return v63Var;
        }
        int i = this.producerIndex & 127;
        while (this.a.get(i) != null) {
            Thread.yield();
        }
        this.a.lazySet(i, v63Var);
        c.incrementAndGet(this);
        return null;
    }

    public final int c() {
        return this.lastScheduledTask != null ? (this.producerIndex - this.consumerIndex) + 1 : this.producerIndex - this.consumerIndex;
    }

    public final v63 d() {
        v63 andSet;
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & 127;
            if (d.compareAndSet(this, i, i + 1) && (andSet = this.a.getAndSet(i2, null)) != null) {
                if (andSet.f.b() == 1) {
                    e.decrementAndGet(this);
                }
                return andSet;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
    
        return f(r9, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long e(defpackage.vt3 r9) {
        /*
            r8 = this;
            int r0 = r9.consumerIndex
            int r1 = r9.producerIndex
            java.util.concurrent.atomic.AtomicReferenceArray<v63> r2 = r9.a
        L6:
            r3 = 1
            if (r0 == r1) goto L45
            r4 = r0 & 127(0x7f, float:1.78E-43)
            int r5 = r9.blockingTasksInBuffer
            if (r5 != 0) goto L10
            goto L45
        L10:
            java.lang.Object r5 = r2.get(r4)
            v63 r5 = (defpackage.v63) r5
            if (r5 == 0) goto L42
            w63 r6 = r5.f
            int r6 = r6.b()
            r7 = 0
            if (r6 != r3) goto L23
            r6 = r3
            goto L24
        L23:
            r6 = r7
        L24:
            if (r6 == 0) goto L42
        L26:
            r6 = 0
            boolean r6 = r2.compareAndSet(r4, r5, r6)
            if (r6 == 0) goto L2e
            goto L35
        L2e:
            java.lang.Object r6 = r2.get(r4)
            if (r6 == r5) goto L26
            r3 = r7
        L35:
            if (r3 == 0) goto L42
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = defpackage.vt3.e
            r0.decrementAndGet(r9)
            r8.a(r5, r7)
            r8 = -1
            return r8
        L42:
            int r0 = r0 + 1
            goto L6
        L45:
            long r8 = r8.f(r9, r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vt3.e(vt3):long");
    }

    public final long f(vt3 vt3Var, boolean z) {
        v63 v63Var;
        boolean z2;
        do {
            v63Var = (v63) vt3Var.lastScheduledTask;
            if (v63Var != null) {
                z2 = true;
                if (z) {
                    if (!(v63Var.f.b() == 1)) {
                        return -2L;
                    }
                }
                e73.e.getClass();
                long jNanoTime = System.nanoTime() - v63Var.e;
                long j = e73.a;
                if (jNanoTime >= j) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
                    while (true) {
                        if (atomicReferenceFieldUpdater.compareAndSet(vt3Var, v63Var, null)) {
                            break;
                        }
                        if (atomicReferenceFieldUpdater.get(vt3Var) != v63Var) {
                            z2 = false;
                            break;
                        }
                    }
                } else {
                    return j - jNanoTime;
                }
            } else {
                return -2L;
            }
        } while (!z2);
        a(v63Var, false);
        return -1L;
    }
}
