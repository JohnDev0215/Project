package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p.bk0;
import p.cb2;
import p.eb2;
import p.fb2;
import p.gb2;
import p.lq2;
import p.ow;
import p.ry0;
import p.tz2;
import p.u4;
import p.yp;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public final class a implements eb2 {
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "head");
    public static final AtomicLongFieldUpdater d = AtomicLongFieldUpdater.newUpdater(a.class, "deqIdx");
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(a.class, Object.class, "tail");
    public static final AtomicLongFieldUpdater f = AtomicLongFieldUpdater.newUpdater(a.class, "enqIdx");
    public static final AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(a.class, "_availablePermits");
    private volatile int _availablePermits;
    public final int a;
    public final ry0 b;
    private volatile long deqIdx;
    private volatile long enqIdx;
    private volatile Object head;
    private volatile Object tail;

    public a(int i, int i2) {
        this.a = i;
        if (i <= 0) {
            throw new IllegalArgumentException(u4.e("Semaphore should have at least 1 permit, but had ", i).toString());
        }
        if (i2 < 0 || i2 > i) {
            throw new IllegalArgumentException(u4.e("The number of acquired permits should be in 0..", i).toString());
        }
        gb2 gb2Var = new gb2(0L, null, 2);
        this.head = gb2Var;
        this.tail = gb2Var;
        this._availablePermits = i - i2;
        this.b = new ry0() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                this.this$0.b();
                return lq2.a;
            }
        };
    }

    public final boolean a(zp zpVar) {
        Object objA;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        gb2 gb2Var = (gb2) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f.getAndIncrement(this);
        SemaphoreImpl$addAcquireToQueue$createNewSegment$1 semaphoreImpl$addAcquireToQueue$createNewSegment$1 = SemaphoreImpl$addAcquireToQueue$createNewSegment$1.k;
        long j = andIncrement / ((long) fb2.f);
        loop0: while (true) {
            objA = ow.a(gb2Var, j, semaphoreImpl$addAcquireToQueue$createNewSegment$1);
            if (!bk0.H(objA)) {
                cb2 cb2VarY = bk0.y(objA);
                while (true) {
                    cb2 cb2Var = (cb2) atomicReferenceFieldUpdater.get(this);
                    if (cb2Var.k >= cb2VarY.k) {
                        break loop0;
                    }
                    if (!cb2VarY.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, cb2Var, cb2VarY)) {
                        if (atomicReferenceFieldUpdater.get(this) != cb2Var) {
                            if (cb2VarY.e()) {
                                cb2VarY.d();
                            }
                        }
                    }
                    if (cb2Var.e()) {
                        cb2Var.d();
                    }
                }
            } else {
                break;
            }
        }
        gb2 gb2Var2 = (gb2) bk0.y(objA);
        int i = (int) (andIncrement % ((long) fb2.f));
        AtomicReferenceArray atomicReferenceArray = gb2Var2.m;
        while (!atomicReferenceArray.compareAndSet(i, null, zpVar)) {
            if (atomicReferenceArray.get(i) != null) {
                tz2 tz2Var = fb2.b;
                tz2 tz2Var2 = fb2.c;
                while (!atomicReferenceArray.compareAndSet(i, tz2Var, tz2Var2)) {
                    if (atomicReferenceArray.get(i) != tz2Var) {
                        return false;
                    }
                }
                zpVar.z(lq2.a, this.b);
                return true;
            }
        }
        zpVar.a(gb2Var2, i);
        return true;
    }

    public final void b() {
        int i;
        Object objA;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i2 = this.a;
            if (andIncrement >= i2) {
                do {
                    i = atomicIntegerFieldUpdater.get(this);
                    if (i <= i2) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, i2));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i2).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
            gb2 gb2Var = (gb2) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = d.getAndIncrement(this);
            long j = andIncrement2 / ((long) fb2.f);
            SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 = SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1.k;
            while (true) {
                objA = ow.a(gb2Var, j, semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1);
                if (bk0.H(objA)) {
                    break;
                }
                cb2 cb2VarY = bk0.y(objA);
                while (true) {
                    cb2 cb2Var = (cb2) atomicReferenceFieldUpdater.get(this);
                    if (cb2Var.k >= cb2VarY.k) {
                        break;
                    }
                    if (!cb2VarY.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, cb2Var, cb2VarY)) {
                        if (atomicReferenceFieldUpdater.get(this) != cb2Var) {
                            if (cb2VarY.e()) {
                                cb2VarY.d();
                            }
                        }
                    }
                    if (cb2Var.e()) {
                        cb2Var.d();
                    }
                }
            }
            gb2 gb2Var2 = (gb2) bk0.y(objA);
            gb2Var2.a();
            if (gb2Var2.k <= j) {
                int i3 = (int) (andIncrement2 % ((long) fb2.f));
                tz2 tz2Var = fb2.b;
                AtomicReferenceArray atomicReferenceArray = gb2Var2.m;
                Object andSet = atomicReferenceArray.getAndSet(i3, tz2Var);
                if (andSet == null) {
                    int i4 = fb2.a;
                    boolean z = false;
                    for (int i5 = 0; i5 < i4; i5++) {
                        if (atomicReferenceArray.get(i3) == fb2.c) {
                            return;
                        }
                    }
                    tz2 tz2Var2 = fb2.b;
                    tz2 tz2Var3 = fb2.d;
                    while (true) {
                        if (!atomicReferenceArray.compareAndSet(i3, tz2Var2, tz2Var3)) {
                            if (atomicReferenceArray.get(i3) != tz2Var2) {
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        return;
                    }
                } else if (andSet == fb2.e) {
                    continue;
                } else {
                    if (!(andSet instanceof yp)) {
                        throw new IllegalStateException(("unexpected: " + andSet).toString());
                    }
                    zp zpVar = (zp) ((yp) andSet);
                    if (zpVar.D(lq2.a, this.b) != null) {
                        zpVar.o(zpVar.k);
                        return;
                    }
                }
            }
        }
    }
}
