package p;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public final class c20 extends Thread {
    public static final AtomicIntegerFieldUpdater q = AtomicIntegerFieldUpdater.newUpdater(c20.class, "workerCtl");
    public final ey2 c;
    public final Ref$ObjectRef e;
    private volatile int indexInArray;
    public CoroutineScheduler$WorkerState k;
    public long l;
    public long m;
    public int n;
    private volatile Object nextParkedWorker;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ d20 f110p;
    private volatile int workerCtl;

    public c20(d20 d20Var, int i) {
        this.f110p = d20Var;
        setDaemon(true);
        this.c = new ey2();
        this.e = new Ref$ObjectRef();
        this.k = CoroutineScheduler$WorkerState.l;
        this.nextParkedWorker = d20.s;
        b02.c.getClass();
        this.n = b02.e.a();
        f(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        r14 = p.ey2.d.get(r3);
        r0 = p.ey2.c.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0051, code lost:
    
        if (r14 == r0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
    
        if (p.ey2.e.get(r3) != 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        r0 = r0 - 1;
        r1 = r3.c(r0, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        if (r1 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
    
        r2 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.zi2 a(boolean r14) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.c20.a(boolean):p.zi2");
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i) {
        int i2 = this.n;
        int i3 = i2 ^ (i2 << 13);
        int i4 = i3 ^ (i3 >> 17);
        int i5 = i4 ^ (i4 << 5);
        this.n = i5;
        int i6 = i - 1;
        return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
    }

    public final zi2 e() {
        int iD = d(2);
        d20 d20Var = this.f110p;
        if (iD == 0) {
            zi2 zi2Var = (zi2) d20Var.m.d();
            return zi2Var != null ? zi2Var : (zi2) d20Var.n.d();
        }
        zi2 zi2Var2 = (zi2) d20Var.n.d();
        return zi2Var2 != null ? zi2Var2 : (zi2) d20Var.m.d();
    }

    public final void f(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f110p.l);
        sb.append("-worker-");
        sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
        setName(sb.toString());
        this.indexInArray = i;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(CoroutineScheduler$WorkerState coroutineScheduler$WorkerState) {
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = this.k;
        boolean z = coroutineScheduler$WorkerState2 == CoroutineScheduler$WorkerState.c;
        if (z) {
            d20.q.addAndGet(this.f110p, 4398046511104L);
        }
        if (coroutineScheduler$WorkerState2 != coroutineScheduler$WorkerState) {
            this.k = coroutineScheduler$WorkerState;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0082, code lost:
    
        r19 = r6;
        r6 = -2;
        r5 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1, types: [T, java.lang.Object, p.zi2] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [p.zi2] */
    /* JADX WARN: Type inference failed for: r7v9, types: [p.zi2] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.zi2 i(int r24) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.c20.i(int):p.zi2");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        boolean z;
        boolean z2 = false;
        loop0: while (true) {
            boolean z3 = z2;
            while (true) {
                d20 d20Var = this.f110p;
                d20Var.getClass();
                if (d20.r.get(d20Var) == 0) {
                    CoroutineScheduler$WorkerState coroutineScheduler$WorkerState = this.k;
                    CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = CoroutineScheduler$WorkerState.m;
                    if (coroutineScheduler$WorkerState == coroutineScheduler$WorkerState2) {
                        break loop0;
                    }
                    zi2 zi2VarA = a(this.o);
                    long j = -2097152;
                    if (zi2VarA != null) {
                        this.m = 0L;
                        int i = zi2VarA.e.c;
                        this.l = 0L;
                        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState3 = this.k;
                        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState4 = CoroutineScheduler$WorkerState.k;
                        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState5 = CoroutineScheduler$WorkerState.e;
                        if (coroutineScheduler$WorkerState3 == coroutineScheduler$WorkerState4) {
                            this.k = coroutineScheduler$WorkerState5;
                        }
                        d20 d20Var2 = this.f110p;
                        if (i != 0 && h(coroutineScheduler$WorkerState5) && !d20Var2.J() && !d20Var2.E(d20.q.get(d20Var2))) {
                            d20Var2.J();
                        }
                        d20Var2.getClass();
                        try {
                            zi2VarA.run();
                        } catch (Throwable th) {
                            Thread threadCurrentThread = Thread.currentThread();
                            threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                        }
                        if (i != 0) {
                            d20.q.addAndGet(d20Var2, -2097152L);
                            if (this.k != coroutineScheduler$WorkerState2) {
                                this.k = CoroutineScheduler$WorkerState.l;
                            }
                        }
                    } else {
                        this.o = z2;
                        if (this.m == 0) {
                            Object obj = this.nextParkedWorker;
                            tz2 tz2Var = d20.s;
                            if (obj != tz2Var ? true : z2) {
                                q.set(this, -1);
                                while (this.nextParkedWorker != d20.s) {
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = q;
                                    if (atomicIntegerFieldUpdater2.get(this) != -1) {
                                        break;
                                    }
                                    d20 d20Var3 = this.f110p;
                                    d20Var3.getClass();
                                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater3 = d20.r;
                                    if (atomicIntegerFieldUpdater3.get(d20Var3) != 0) {
                                        break;
                                    }
                                    CoroutineScheduler$WorkerState coroutineScheduler$WorkerState6 = this.k;
                                    CoroutineScheduler$WorkerState coroutineScheduler$WorkerState7 = CoroutineScheduler$WorkerState.m;
                                    if (coroutineScheduler$WorkerState6 == coroutineScheduler$WorkerState7) {
                                        break;
                                    }
                                    h(CoroutineScheduler$WorkerState.k);
                                    Thread.interrupted();
                                    if (this.l == 0) {
                                        atomicIntegerFieldUpdater = atomicIntegerFieldUpdater2;
                                        this.l = System.nanoTime() + this.f110p.k;
                                    } else {
                                        atomicIntegerFieldUpdater = atomicIntegerFieldUpdater2;
                                    }
                                    LockSupport.parkNanos(this.f110p.k);
                                    if (System.nanoTime() - this.l >= 0) {
                                        this.l = 0L;
                                        d20 d20Var4 = this.f110p;
                                        synchronized (d20Var4.o) {
                                            try {
                                                if (!(atomicIntegerFieldUpdater3.get(d20Var4) != 0)) {
                                                    AtomicLongFieldUpdater atomicLongFieldUpdater = d20.q;
                                                    if (((int) (atomicLongFieldUpdater.get(d20Var4) & 2097151)) > d20Var4.c && atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                        int i2 = this.indexInArray;
                                                        z = false;
                                                        f(0);
                                                        d20Var4.q(this, i2, 0);
                                                        int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(d20Var4) & 2097151);
                                                        if (andDecrement != i2) {
                                                            Object objB = d20Var4.o.b(andDecrement);
                                                            v03.h(objB);
                                                            c20 c20Var = (c20) objB;
                                                            d20Var4.o.c(i2, c20Var);
                                                            c20Var.f(i2);
                                                            d20Var4.q(c20Var, andDecrement, i2);
                                                        }
                                                        d20Var4.o.c(andDecrement, null);
                                                        this.k = coroutineScheduler$WorkerState7;
                                                    }
                                                }
                                                z = false;
                                            } catch (Throwable th2) {
                                                throw th2;
                                            }
                                        }
                                    } else {
                                        z = false;
                                    }
                                    z2 = z;
                                }
                            } else {
                                d20 d20Var5 = this.f110p;
                                d20Var5.getClass();
                                if (this.nextParkedWorker == tz2Var) {
                                    while (true) {
                                        AtomicLongFieldUpdater atomicLongFieldUpdater2 = d20.f114p;
                                        long j2 = atomicLongFieldUpdater2.get(d20Var5);
                                        int i3 = this.indexInArray;
                                        this.nextParkedWorker = d20Var5.o.b((int) (j2 & 2097151));
                                        if (atomicLongFieldUpdater2.compareAndSet(d20Var5, j2, ((2097152 + j2) & j) | ((long) i3))) {
                                            break;
                                        } else {
                                            j = -2097152;
                                        }
                                    }
                                }
                            }
                            z2 = z2;
                        } else {
                            if (z3) {
                                h(CoroutineScheduler$WorkerState.k);
                                Thread.interrupted();
                                LockSupport.parkNanos(this.m);
                                this.m = 0L;
                                break;
                            }
                            z3 = true;
                        }
                    }
                } else {
                    break loop0;
                }
            }
        }
        h(CoroutineScheduler$WorkerState.m);
    }
}
