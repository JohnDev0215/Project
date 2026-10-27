package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: WorkQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7787b = AtomicReferenceFieldUpdater.newUpdater(n.class, Object.class, "lastScheduledTask");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7788c = AtomicIntegerFieldUpdater.newUpdater(n.class, "producerIndex");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7789d = AtomicIntegerFieldUpdater.newUpdater(n.class, "consumerIndex");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7790e = AtomicIntegerFieldUpdater.newUpdater(n.class, "blockingTasksInBuffer");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReferenceArray<i> f7791a = new AtomicReferenceArray<>(128);
    private volatile Object lastScheduledTask = null;
    private volatile int producerIndex = 0;
    private volatile int consumerIndex = 0;
    private volatile int blockingTasksInBuffer = 0;

    public static /* synthetic */ i b(n nVar, i iVar, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            z6 = false;
        }
        return nVar.a(iVar, z6);
    }

    private final i c(i iVar) {
        if (iVar.f7779g.P() == 1) {
            f7790e.incrementAndGet(this);
        }
        if (e() == 127) {
            return iVar;
        }
        int i7 = this.producerIndex & 127;
        while (this.f7791a.get(i7) != null) {
            Thread.yield();
        }
        this.f7791a.lazySet(i7, iVar);
        f7788c.incrementAndGet(this);
        return null;
    }

    private final void d(i iVar) {
        if (iVar != null) {
            if (iVar.f7779g.P() == 1) {
                f7790e.decrementAndGet(this);
            }
        }
    }

    private final i i() {
        i andSet;
        while (true) {
            int i7 = this.consumerIndex;
            if (i7 - this.producerIndex == 0) {
                return null;
            }
            int i8 = i7 & 127;
            if (f7789d.compareAndSet(this, i7, i7 + 1) && (andSet = this.f7791a.getAndSet(i8, null)) != null) {
                d(andSet);
                return andSet;
            }
        }
    }

    private final boolean j(e eVar) {
        i iVarI = i();
        if (iVarI == null) {
            return false;
        }
        eVar.a(iVarI);
        return true;
    }

    private final long m(n nVar, boolean z6) {
        i iVar;
        do {
            iVar = (i) nVar.lastScheduledTask;
            if (iVar == null) {
                return -2L;
            }
            if (z6) {
                if (!(iVar.f7779g.P() == 1)) {
                    return -2L;
                }
            }
            long jA = l.f7786f.a() - iVar.f7778e;
            long j7 = l.f7781a;
            if (jA < j7) {
                return j7 - jA;
            }
        } while (!kotlinx.coroutines.j.a(f7787b, nVar, iVar, null));
        b(this, iVar, false, 2, null);
        return -1L;
    }

    public final i a(i iVar, boolean z6) {
        if (z6) {
            return c(iVar);
        }
        i iVar2 = (i) f7787b.getAndSet(this, iVar);
        if (iVar2 != null) {
            return c(iVar2);
        }
        return null;
    }

    public final int e() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int f() {
        return this.lastScheduledTask != null ? e() + 1 : e();
    }

    public final void g(e eVar) {
        i iVar = (i) f7787b.getAndSet(this, null);
        if (iVar != null) {
            eVar.a(iVar);
        }
        while (j(eVar)) {
        }
    }

    public final i h() {
        i iVar = (i) f7787b.getAndSet(this, null);
        return iVar != null ? iVar : i();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003f, code lost:
    
        return m(r9, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long k(kotlinx.coroutines.scheduling.n r9) {
        /*
            r8 = this;
            int r0 = r9.consumerIndex
            int r1 = r9.producerIndex
            java.util.concurrent.atomic.AtomicReferenceArray<kotlinx.coroutines.scheduling.i> r2 = r9.f7791a
        L6:
            r3 = 1
            if (r0 == r1) goto L3b
            r4 = r0 & 127(0x7f, float:1.78E-43)
            int r5 = r9.blockingTasksInBuffer
            if (r5 != 0) goto L10
            goto L3b
        L10:
            java.lang.Object r5 = r2.get(r4)
            kotlinx.coroutines.scheduling.i r5 = (kotlinx.coroutines.scheduling.i) r5
            if (r5 == 0) goto L38
            kotlinx.coroutines.scheduling.j r6 = r5.f7779g
            int r6 = r6.P()
            r7 = 0
            if (r6 != r3) goto L22
            goto L23
        L22:
            r3 = r7
        L23:
            if (r3 == 0) goto L38
            r3 = 0
            boolean r4 = r2.compareAndSet(r4, r5, r3)
            if (r4 == 0) goto L38
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = kotlinx.coroutines.scheduling.n.f7790e
            r0.decrementAndGet(r9)
            r9 = 2
            b(r8, r5, r7, r9, r3)
            r0 = -1
            return r0
        L38:
            int r0 = r0 + 1
            goto L6
        L3b:
            long r0 = r8.m(r9, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.n.k(kotlinx.coroutines.scheduling.n):long");
    }

    public final long l(n nVar) {
        i iVarI = nVar.i();
        if (iVarI == null) {
            return m(nVar, false);
        }
        b(this, iVarI, false, 2, null);
        return -1L;
    }
}
