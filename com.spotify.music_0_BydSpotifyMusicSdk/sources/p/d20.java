package p;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public final class d20 implements Executor, Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f114p = AtomicLongFieldUpdater.newUpdater(d20.class, "parkedWorkersStack");
    public static final AtomicLongFieldUpdater q = AtomicLongFieldUpdater.newUpdater(d20.class, "controlState");
    public static final AtomicIntegerFieldUpdater r = AtomicIntegerFieldUpdater.newUpdater(d20.class, "_isTerminated");
    public static final tz2 s = new tz2("NOT_IN_STACK", 3);
    private volatile int _isTerminated;
    public final int c;
    private volatile long controlState;
    public final int e;
    public final long k;
    public final String l;
    public final t01 m;
    public final t01 n;
    public final v32 o;
    private volatile long parkedWorkersStack;

    public d20(int i, int i2, long j, String str) {
        this.c = i;
        this.e = i2;
        this.k = j;
        this.l = str;
        if (i < 1) {
            throw new IllegalArgumentException(u4.f("Core pool size ", i, " should be at least 1").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(hf2.c("Max pool size ", i2, " should be greater than or equals to core pool size ", i).toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(u4.f("Max pool size ", i2, " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.m = new t01();
        this.n = new t01();
        this.o = new v32((i + 1) * 2);
        this.controlState = ((long) i) << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ void i(d20 d20Var, Runnable runnable, boolean z, int i) {
        w1 w1Var = (i & 2) != 0 ? fj2.g : null;
        if ((i & 4) != 0) {
            z = false;
        }
        d20Var.c(runnable, w1Var, z);
    }

    public final boolean E(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        int i2 = this.c;
        if (i < i2) {
            int iB = b();
            if (iB == 1 && i2 > 1) {
                b();
            }
            if (iB > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean J() {
        tz2 tz2Var;
        int iB;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f114p;
            long j = atomicLongFieldUpdater.get(this);
            c20 c20Var = (c20) this.o.b((int) (2097151 & j));
            if (c20Var == null) {
                c20Var = null;
            } else {
                long j2 = (2097152 + j) & (-2097152);
                Object objC = c20Var.c();
                while (true) {
                    tz2Var = s;
                    if (objC == tz2Var) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    c20 c20Var2 = (c20) objC;
                    iB = c20Var2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = c20Var2.c();
                }
                if (iB >= 0 && atomicLongFieldUpdater.compareAndSet(this, j, j2 | ((long) iB))) {
                    c20Var.g(tz2Var);
                }
            }
            if (c20Var == null) {
                return false;
            }
            if (c20.q.compareAndSet(c20Var, -1, 0)) {
                LockSupport.unpark(c20Var);
                return true;
            }
        }
    }

    public final int b() {
        synchronized (this.o) {
            try {
                if (r.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = q;
                long j = atomicLongFieldUpdater.get(this);
                int i = (int) (j & 2097151);
                int i2 = i - ((int) ((j & 4398044413952L) >> 21));
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i2 >= this.c) {
                    return 0;
                }
                if (i >= this.e) {
                    return 0;
                }
                int i3 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i3 <= 0 || this.o.b(i3) != null) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                c20 c20Var = new c20(this, i3);
                this.o.c(i3, c20Var);
                if (i3 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                int i4 = i2 + 1;
                c20Var.start();
                return i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c(Runnable runnable, w1 w1Var, boolean z) {
        zi2 ej2Var;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState;
        fj2.f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof zi2) {
            ej2Var = (zi2) runnable;
            ej2Var.c = jNanoTime;
            ej2Var.e = w1Var;
        } else {
            ej2Var = new ej2(runnable, jNanoTime, w1Var);
        }
        boolean z2 = false;
        boolean z3 = ej2Var.e.c == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = q;
        long jAddAndGet = z3 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        c20 c20Var = threadCurrentThread instanceof c20 ? (c20) threadCurrentThread : null;
        if (c20Var == null || !v03.b(c20Var.f110p, this)) {
            c20Var = null;
        }
        if (c20Var != null && (coroutineScheduler$WorkerState = c20Var.k) != CoroutineScheduler$WorkerState.m && (ej2Var.e.c != 0 || coroutineScheduler$WorkerState != CoroutineScheduler$WorkerState.e)) {
            c20Var.o = true;
            ey2 ey2Var = c20Var.c;
            if (z) {
                ej2Var = ey2Var.a(ej2Var);
            } else {
                ey2Var.getClass();
                zi2 zi2Var = (zi2) ey2.b.getAndSet(ey2Var, ej2Var);
                ej2Var = zi2Var == null ? null : ey2Var.a(zi2Var);
            }
        }
        if (ej2Var != null) {
            if (!(ej2Var.e.c == 1 ? this.n.a(ej2Var) : this.m.a(ej2Var))) {
                throw new RejectedExecutionException(u4.m(new StringBuilder(), this.l, " was terminated"));
            }
        }
        if (z && c20Var != null) {
            z2 = true;
        }
        if (z3) {
            if (z2 || J() || E(jAddAndGet)) {
                return;
            }
            J();
            return;
        }
        if (z2 || J() || E(atomicLongFieldUpdater.get(this))) {
            return;
        }
        J();
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void close() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = p.d20.r
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lc
            goto Lb0
        Lc:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof p.c20
            r3 = 0
            if (r1 == 0) goto L18
            p.c20 r0 = (p.c20) r0
            goto L19
        L18:
            r0 = r3
        L19:
            if (r0 == 0) goto L24
            p.d20 r1 = r0.f110p
            boolean r1 = p.v03.b(r1, r8)
            if (r1 == 0) goto L24
            goto L25
        L24:
            r0 = r3
        L25:
            p.v32 r1 = r8.o
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = p.d20.q     // Catch: java.lang.Throwable -> Lc2
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lc2
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L77
            r1 = r2
        L37:
            p.v32 r5 = r8.o
            java.lang.Object r5 = r5.b(r1)
            p.v03.h(r5)
            p.c20 r5 = (p.c20) r5
            if (r5 == r0) goto L72
        L44:
            boolean r6 = r5.isAlive()
            if (r6 == 0) goto L53
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L44
        L53:
            p.ey2 r5 = r5.c
            p.t01 r6 = r8.n
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = p.ey2.b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            p.zi2 r7 = (p.zi2) r7
            if (r7 == 0) goto L67
            r6.a(r7)
        L67:
            p.zi2 r7 = r5.b()
            if (r7 != 0) goto L6e
            goto L72
        L6e:
            r6.a(r7)
            goto L67
        L72:
            if (r1 == r4) goto L77
            int r1 = r1 + 1
            goto L37
        L77:
            p.t01 r1 = r8.n
            r1.b()
            p.t01 r1 = r8.m
            r1.b()
        L81:
            if (r0 == 0) goto L89
            p.zi2 r1 = r0.a(r2)
            if (r1 != 0) goto Lb1
        L89:
            p.t01 r1 = r8.m
            java.lang.Object r1 = r1.d()
            p.zi2 r1 = (p.zi2) r1
            if (r1 != 0) goto Lb1
            p.t01 r1 = r8.n
            java.lang.Object r1 = r1.d()
            p.zi2 r1 = (p.zi2) r1
            if (r1 != 0) goto Lb1
            if (r0 == 0) goto La4
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState.m
            r0.h(r1)
        La4:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = p.d20.f114p
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = p.d20.q
            r0.set(r8, r1)
        Lb0:
            return
        Lb1:
            r1.run()     // Catch: java.lang.Throwable -> Lb5
            goto L81
        Lb5:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L81
        Lc2:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.d20.close():void");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        i(this, runnable, false, 6);
    }

    public final void q(c20 c20Var, int i, int i2) {
        while (true) {
            long j = f114p.get(this);
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object objC = c20Var.c();
                    while (true) {
                        if (objC == s) {
                            i3 = -1;
                            break;
                        }
                        if (objC == null) {
                            i3 = 0;
                            break;
                        }
                        c20 c20Var2 = (c20) objC;
                        int iB = c20Var2.b();
                        if (iB != 0) {
                            i3 = iB;
                            break;
                        }
                        objC = c20Var2.c();
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0 && f114p.compareAndSet(this, j, ((long) i3) | j2)) {
                return;
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        v32 v32Var = this.o;
        int iA = v32Var.a();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < iA; i6++) {
            c20 c20Var = (c20) v32Var.b(i6);
            if (c20Var != null) {
                ey2 ey2Var = c20Var.c;
                ey2Var.getClass();
                int i7 = ey2.b.get(ey2Var) != null ? (ey2.c.get(ey2Var) - ey2.d.get(ey2Var)) + 1 : ey2.c.get(ey2Var) - ey2.d.get(ey2Var);
                int iOrdinal = c20Var.k.ordinal();
                if (iOrdinal == 0) {
                    i++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i7);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iOrdinal == 1) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i7);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iOrdinal == 2) {
                    i3++;
                } else if (iOrdinal == 3) {
                    i4++;
                    if (i7 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i7);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (iOrdinal == 4) {
                    i5++;
                }
            }
        }
        long j = q.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.l);
        sb4.append('@');
        sb4.append(f60.s(this));
        sb4.append("[Pool Size {core = ");
        int i8 = this.c;
        sb4.append(i8);
        sb4.append(", max = ");
        sb4.append(this.e);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i);
        sb4.append(", blocking = ");
        sb4.append(i2);
        sb4.append(", parked = ");
        sb4.append(i3);
        sb4.append(", dormant = ");
        sb4.append(i4);
        sb4.append(", terminated = ");
        sb4.append(i5);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.m.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.n.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i8 - ((int) ((j & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
