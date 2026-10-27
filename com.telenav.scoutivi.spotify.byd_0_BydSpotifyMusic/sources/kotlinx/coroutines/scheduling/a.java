package kotlinx.coroutines.scheduling;

import g5.r;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.i2;
import kotlinx.coroutines.internal.c0;
import kotlinx.coroutines.j2;
import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: CoroutineScheduler.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Executor, Closeable {
    private volatile int _isTerminated;
    volatile long controlState;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f7745e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f7746g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicReferenceArray<b> f7747h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7748i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f7749j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f7750k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f7751l;
    private volatile long parkedWorkersStack;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0110a f7744q = new C0110a(null);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final c0 f7743p = new c0("NOT_IN_STACK");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final AtomicLongFieldUpdater f7740m = AtomicLongFieldUpdater.newUpdater(a.class, "parkedWorkersStack");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    static final AtomicLongFieldUpdater f7741n = AtomicLongFieldUpdater.newUpdater(a.class, "controlState");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7742o = AtomicIntegerFieldUpdater.newUpdater(a.class, "_isTerminated");

    /* JADX INFO: renamed from: kotlinx.coroutines.scheduling.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CoroutineScheduler.kt */
    public static final class C0110a {
        private C0110a() {
        }

        public /* synthetic */ C0110a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: CoroutineScheduler.kt */
    public enum c {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public a(int i7, int i8, long j7, String str) {
        this.f7748i = i7;
        this.f7749j = i8;
        this.f7750k = j7;
        this.f7751l = str;
        if (!(i7 >= 1)) {
            throw new IllegalArgumentException(("Core pool size " + i7 + " should be at least 1").toString());
        }
        if (!(i8 >= i7)) {
            throw new IllegalArgumentException(("Max pool size " + i8 + " should be greater than or equals to core pool size " + i7).toString());
        }
        if (!(i8 <= 2097150)) {
            throw new IllegalArgumentException(("Max pool size " + i8 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (!(j7 > 0)) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j7 + " must be positive").toString());
        }
        this.f7745e = new e();
        this.f7746g = new e();
        this.parkedWorkersStack = 0L;
        this.f7747h = new AtomicReferenceArray<>(i8 + 1);
        this.controlState = ((long) i7) << 42;
        this._isTerminated = 0;
    }

    private final int A() {
        synchronized (this.f7747h) {
            if (isTerminated()) {
                return -1;
            }
            long j7 = this.controlState;
            int i7 = (int) (j7 & 2097151);
            int iB = v5.h.b(i7 - ((int) ((j7 & 4398044413952L) >> 21)), 0);
            if (iB >= this.f7748i) {
                return 0;
            }
            if (i7 >= this.f7749j) {
                return 0;
            }
            int i8 = ((int) (this.controlState & 2097151)) + 1;
            if (!(i8 > 0 && this.f7747h.get(i8) == null)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            b bVar = new b(this, i8);
            this.f7747h.set(i8, bVar);
            if (!(i8 == ((int) (2097151 & f7741n.incrementAndGet(this))))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            bVar.start();
            return iB + 1;
        }
    }

    private final b I() {
        Thread threadCurrentThread = Thread.currentThread();
        if (!(threadCurrentThread instanceof b)) {
            threadCurrentThread = null;
        }
        b bVar = (b) threadCurrentThread;
        if (bVar == null || !kotlin.jvm.internal.l.a(a.this, this)) {
            return null;
        }
        return bVar;
    }

    public static /* synthetic */ void N(a aVar, Runnable runnable, j jVar, boolean z6, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            jVar = h.f7777g;
        }
        if ((i7 & 4) != 0) {
            z6 = false;
        }
        aVar.K(runnable, jVar, z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int P() {
        return (int) (this.controlState & 2097151);
    }

    private final int U(b bVar) {
        Object objG = bVar.g();
        while (objG != f7743p) {
            if (objG == null) {
                return 0;
            }
            b bVar2 = (b) objG;
            int iF = bVar2.f();
            if (iF != 0) {
                return iF;
            }
            objG = bVar2.g();
        }
        return -1;
    }

    private final b V() {
        while (true) {
            long j7 = this.parkedWorkersStack;
            b bVar = this.f7747h.get((int) (2097151 & j7));
            if (bVar == null) {
                return null;
            }
            long j8 = (2097152 + j7) & (-2097152);
            int iU = U(bVar);
            if (iU >= 0 && f7740m.compareAndSet(this, j7, ((long) iU) | j8)) {
                bVar.o(f7743p);
                return bVar;
            }
        }
    }

    private final void d0(boolean z6) {
        long jAddAndGet = f7741n.addAndGet(this, 2097152L);
        if (z6 || i0() || g0(jAddAndGet)) {
            return;
        }
        i0();
    }

    private final i f0(b bVar, i iVar, boolean z6) {
        if (bVar == null || bVar.f7754g == c.TERMINATED) {
            return iVar;
        }
        if (iVar.f7779g.P() == 0 && bVar.f7754g == c.BLOCKING) {
            return iVar;
        }
        bVar.f7758k = true;
        return bVar.f7753e.a(iVar, z6);
    }

    private final boolean g0(long j7) {
        if (v5.h.b(((int) (2097151 & j7)) - ((int) ((j7 & 4398044413952L) >> 21)), 0) < this.f7748i) {
            int iA = A();
            if (iA == 1 && this.f7748i > 1) {
                A();
            }
            if (iA > 0) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ boolean h0(a aVar, long j7, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            j7 = aVar.controlState;
        }
        return aVar.g0(j7);
    }

    private final boolean i0() {
        b bVarV;
        do {
            bVarV = V();
            if (bVarV == null) {
                return false;
            }
        } while (!b.f7752m.compareAndSet(bVarV, -1, 0));
        LockSupport.unpark(bVarV);
        return true;
    }

    private final boolean z(i iVar) {
        return iVar.f7779g.P() == 1 ? this.f7746g.a(iVar) : this.f7745e.a(iVar);
    }

    public final i G(Runnable runnable, j jVar) {
        long jA = l.f7786f.a();
        if (!(runnable instanceof i)) {
            return new k(runnable, jA, jVar);
        }
        i iVar = (i) runnable;
        iVar.f7778e = jA;
        iVar.f7779g = jVar;
        return iVar;
    }

    public final void K(Runnable runnable, j jVar, boolean z6) {
        i2 i2VarA = j2.a();
        if (i2VarA != null) {
            i2VarA.h();
        }
        i iVarG = G(runnable, jVar);
        b bVarI = I();
        i iVarF0 = f0(bVarI, iVarG, z6);
        if (iVarF0 != null && !z(iVarF0)) {
            throw new RejectedExecutionException(this.f7751l + " was terminated");
        }
        boolean z7 = z6 && bVarI != null;
        if (iVarG.f7779g.P() != 0) {
            d0(z7);
        } else {
            if (z7) {
                return;
            }
            e0();
        }
    }

    public final boolean Z(b bVar) {
        long j7;
        int iF;
        if (bVar.g() != f7743p) {
            return false;
        }
        do {
            j7 = this.parkedWorkersStack;
            iF = bVar.f();
            bVar.o(this.f7747h.get((int) (2097151 & j7)));
        } while (!f7740m.compareAndSet(this, j7, ((2097152 + j7) & (-2097152)) | ((long) iF)));
        return true;
    }

    public final void a0(b bVar, int i7, int i8) {
        while (true) {
            long j7 = this.parkedWorkersStack;
            int iU = (int) (2097151 & j7);
            long j8 = (2097152 + j7) & (-2097152);
            if (iU == i7) {
                iU = i8 == 0 ? U(bVar) : i8;
            }
            if (iU >= 0 && f7740m.compareAndSet(this, j7, j8 | ((long) iU))) {
                return;
            }
        }
    }

    public final void b0(i iVar) {
        try {
            iVar.run();
        } catch (Throwable th) {
            try {
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                i2 i2VarA = j2.a();
                if (i2VarA == null) {
                }
            } finally {
                i2 i2VarA2 = j2.a();
                if (i2VarA2 != null) {
                    i2VarA2.g();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c0(long r8) throws java.lang.InterruptedException {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = kotlinx.coroutines.scheduling.a.f7742o
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r7, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            kotlinx.coroutines.scheduling.a$b r0 = r7.I()
            java.util.concurrent.atomic.AtomicReferenceArray<kotlinx.coroutines.scheduling.a$b> r1 = r7.f7747h
            monitor-enter(r1)
            long r3 = r7.controlState     // Catch: java.lang.Throwable -> L7d
            r5 = 2097151(0x1fffff, double:1.0361303E-317)
            long r3 = r3 & r5
            int r3 = (int) r3
            monitor-exit(r1)
            if (r2 > r3) goto L43
            r1 = r2
        L1d:
            java.util.concurrent.atomic.AtomicReferenceArray<kotlinx.coroutines.scheduling.a$b> r4 = r7.f7747h
            java.lang.Object r4 = r4.get(r1)
            kotlin.jvm.internal.l.c(r4)
            kotlinx.coroutines.scheduling.a$b r4 = (kotlinx.coroutines.scheduling.a.b) r4
            if (r4 == r0) goto L3e
        L2a:
            boolean r5 = r4.isAlive()
            if (r5 == 0) goto L37
            java.util.concurrent.locks.LockSupport.unpark(r4)
            r4.join(r8)
            goto L2a
        L37:
            kotlinx.coroutines.scheduling.n r4 = r4.f7753e
            kotlinx.coroutines.scheduling.e r5 = r7.f7746g
            r4.g(r5)
        L3e:
            if (r1 == r3) goto L43
            int r1 = r1 + 1
            goto L1d
        L43:
            kotlinx.coroutines.scheduling.e r8 = r7.f7746g
            r8.b()
            kotlinx.coroutines.scheduling.e r8 = r7.f7745e
            r8.b()
        L4d:
            if (r0 == 0) goto L56
            kotlinx.coroutines.scheduling.i r8 = r0.e(r2)
            if (r8 == 0) goto L56
            goto L5e
        L56:
            kotlinx.coroutines.scheduling.e r8 = r7.f7745e
            java.lang.Object r8 = r8.d()
            kotlinx.coroutines.scheduling.i r8 = (kotlinx.coroutines.scheduling.i) r8
        L5e:
            if (r8 == 0) goto L61
            goto L69
        L61:
            kotlinx.coroutines.scheduling.e r8 = r7.f7746g
            java.lang.Object r8 = r8.d()
            kotlinx.coroutines.scheduling.i r8 = (kotlinx.coroutines.scheduling.i) r8
        L69:
            if (r8 == 0) goto L6f
            r7.b0(r8)
            goto L4d
        L6f:
            if (r0 == 0) goto L76
            kotlinx.coroutines.scheduling.a$c r8 = kotlinx.coroutines.scheduling.a.c.TERMINATED
            r0.r(r8)
        L76:
            r8 = 0
            r7.parkedWorkersStack = r8
            r7.controlState = r8
            return
        L7d:
            r8 = move-exception
            monitor-exit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.a.c0(long):void");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        c0(10000L);
    }

    public final void e0() {
        if (i0() || h0(this, 0L, 1, null)) {
            return;
        }
        i0();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        N(this, runnable, null, false, 6, null);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean isTerminated() {
        return this._isTerminated;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        int length = this.f7747h.length();
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            b bVar = this.f7747h.get(i12);
            if (bVar != null) {
                int iF = bVar.f7753e.f();
                int i13 = kotlinx.coroutines.scheduling.b.f7761a[bVar.f7754g.ordinal()];
                if (i13 == 1) {
                    i9++;
                } else if (i13 == 2) {
                    i8++;
                    arrayList.add(String.valueOf(iF) + "b");
                } else if (i13 == 3) {
                    i7++;
                    arrayList.add(String.valueOf(iF) + "c");
                } else if (i13 == 4) {
                    i10++;
                    if (iF > 0) {
                        arrayList.add(String.valueOf(iF) + "d");
                    }
                } else if (i13 == 5) {
                    i11++;
                }
            }
        }
        long j7 = this.controlState;
        return this.f7751l + '@' + m0.b(this) + "[Pool Size {core = " + this.f7748i + ", max = " + this.f7749j + "}, Worker States {CPU = " + i7 + ", blocking = " + i8 + ", parked = " + i9 + ", dormant = " + i10 + ", terminated = " + i11 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.f7745e.c() + ", global blocking queue size = " + this.f7746g.c() + ", Control State {created workers= " + ((int) (2097151 & j7)) + ", blocking tasks = " + ((int) ((4398044413952L & j7) >> 21)) + ", CPUs acquired = " + (this.f7748i - ((int) ((9223367638808264704L & j7) >> 42))) + "}]";
    }

    /* JADX INFO: compiled from: CoroutineScheduler.kt */
    public final class b extends Thread {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        static final AtomicIntegerFieldUpdater f7752m = AtomicIntegerFieldUpdater.newUpdater(b.class, "workerCtl");

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final n f7753e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public c f7754g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private long f7755h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private long f7756i;
        private volatile int indexInArray;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f7757j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f7758k;
        private volatile Object nextParkedWorker;
        volatile int workerCtl;

        private b() {
            setDaemon(true);
            this.f7753e = new n();
            this.f7754g = c.DORMANT;
            this.workerCtl = 0;
            this.nextParkedWorker = a.f7743p;
            this.f7757j = t5.c.f9560e.b();
        }

        private final void a(int i7) {
            if (i7 == 0) {
                return;
            }
            a.f7741n.addAndGet(a.this, -2097152L);
            if (this.f7754g != c.TERMINATED) {
                this.f7754g = c.DORMANT;
            }
        }

        private final void b(int i7) {
            if (i7 != 0 && r(c.BLOCKING)) {
                a.this.e0();
            }
        }

        private final void c(i iVar) {
            int iP = iVar.f7779g.P();
            h(iP);
            b(iP);
            a.this.b0(iVar);
            a(iP);
        }

        private final i d(boolean z6) {
            i iVarL;
            i iVarL2;
            if (z6) {
                boolean z7 = j(a.this.f7748i * 2) == 0;
                if (z7 && (iVarL2 = l()) != null) {
                    return iVarL2;
                }
                i iVarH = this.f7753e.h();
                if (iVarH != null) {
                    return iVarH;
                }
                if (!z7 && (iVarL = l()) != null) {
                    return iVarL;
                }
            } else {
                i iVarL3 = l();
                if (iVarL3 != null) {
                    return iVarL3;
                }
            }
            return s(false);
        }

        private final void h(int i7) {
            this.f7755h = 0L;
            if (this.f7754g == c.PARKING) {
                this.f7754g = c.BLOCKING;
            }
        }

        private final boolean i() {
            return this.nextParkedWorker != a.f7743p;
        }

        private final void k() {
            if (this.f7755h == 0) {
                this.f7755h = System.nanoTime() + a.this.f7750k;
            }
            LockSupport.parkNanos(a.this.f7750k);
            if (System.nanoTime() - this.f7755h >= 0) {
                this.f7755h = 0L;
                t();
            }
        }

        private final i l() {
            if (j(2) == 0) {
                i iVarD = a.this.f7745e.d();
                return iVarD != null ? iVarD : a.this.f7746g.d();
            }
            i iVarD2 = a.this.f7746g.d();
            return iVarD2 != null ? iVarD2 : a.this.f7745e.d();
        }

        private final void m() {
            loop0: while (true) {
                boolean z6 = false;
                while (!a.this.isTerminated() && this.f7754g != c.TERMINATED) {
                    i iVarE = e(this.f7758k);
                    if (iVarE != null) {
                        this.f7756i = 0L;
                        c(iVarE);
                    } else {
                        this.f7758k = false;
                        if (this.f7756i == 0) {
                            q();
                        } else if (z6) {
                            r(c.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f7756i);
                            this.f7756i = 0L;
                        } else {
                            z6 = true;
                        }
                    }
                }
                break loop0;
            }
            r(c.TERMINATED);
        }

        private final boolean p() {
            boolean z6;
            if (this.f7754g != c.CPU_ACQUIRED) {
                a aVar = a.this;
                while (true) {
                    long j7 = aVar.controlState;
                    if (((int) ((9223367638808264704L & j7) >> 42)) == 0) {
                        z6 = false;
                        break;
                    }
                    if (a.f7741n.compareAndSet(aVar, j7, j7 - 4398046511104L)) {
                        z6 = true;
                        break;
                    }
                }
                if (!z6) {
                    return false;
                }
                this.f7754g = c.CPU_ACQUIRED;
            }
            return true;
        }

        private final void q() {
            if (!i()) {
                a.this.Z(this);
                return;
            }
            this.workerCtl = -1;
            while (i() && !a.this.isTerminated() && this.f7754g != c.TERMINATED) {
                r(c.PARKING);
                Thread.interrupted();
                k();
            }
        }

        private final i s(boolean z6) {
            int iP = a.this.P();
            if (iP < 2) {
                return null;
            }
            int iJ = j(iP);
            long jMin = Long.MAX_VALUE;
            for (int i7 = 0; i7 < iP; i7++) {
                iJ++;
                if (iJ > iP) {
                    iJ = 1;
                }
                b bVar = a.this.f7747h.get(iJ);
                if (bVar != null && bVar != this) {
                    long jK = z6 ? this.f7753e.k(bVar.f7753e) : this.f7753e.l(bVar.f7753e);
                    if (jK == -1) {
                        return this.f7753e.h();
                    }
                    if (jK > 0) {
                        jMin = Math.min(jMin, jK);
                    }
                }
            }
            if (jMin == Long.MAX_VALUE) {
                jMin = 0;
            }
            this.f7756i = jMin;
            return null;
        }

        private final void t() {
            synchronized (a.this.f7747h) {
                if (a.this.isTerminated()) {
                    return;
                }
                if (a.this.P() <= a.this.f7748i) {
                    return;
                }
                if (f7752m.compareAndSet(this, -1, 1)) {
                    int i7 = this.indexInArray;
                    n(0);
                    a.this.a0(this, i7, 0);
                    int andDecrement = (int) (a.f7741n.getAndDecrement(a.this) & 2097151);
                    if (andDecrement != i7) {
                        b bVar = a.this.f7747h.get(andDecrement);
                        kotlin.jvm.internal.l.c(bVar);
                        b bVar2 = bVar;
                        a.this.f7747h.set(i7, bVar2);
                        bVar2.n(i7);
                        a.this.a0(bVar2, andDecrement, i7);
                    }
                    a.this.f7747h.set(andDecrement, null);
                    r rVar = r.f6563a;
                    this.f7754g = c.TERMINATED;
                }
            }
        }

        public final i e(boolean z6) {
            i iVarD;
            if (p()) {
                return d(z6);
            }
            if (!z6 || (iVarD = this.f7753e.h()) == null) {
                iVarD = a.this.f7746g.d();
            }
            return iVarD != null ? iVarD : s(true);
        }

        public final int f() {
            return this.indexInArray;
        }

        public final Object g() {
            return this.nextParkedWorker;
        }

        public final int j(int i7) {
            int i8 = this.f7757j;
            int i9 = i8 ^ (i8 << 13);
            int i10 = i9 ^ (i9 >> 17);
            int i11 = i10 ^ (i10 << 5);
            this.f7757j = i11;
            int i12 = i7 - 1;
            return (i12 & i7) == 0 ? i11 & i12 : (i11 & Integer.MAX_VALUE) % i7;
        }

        public final void n(int i7) {
            StringBuilder sb = new StringBuilder();
            sb.append(a.this.f7751l);
            sb.append("-worker-");
            sb.append(i7 == 0 ? "TERMINATED" : String.valueOf(i7));
            setName(sb.toString());
            this.indexInArray = i7;
        }

        public final void o(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean r(c cVar) {
            c cVar2 = this.f7754g;
            boolean z6 = cVar2 == c.CPU_ACQUIRED;
            if (z6) {
                a.f7741n.addAndGet(a.this, 4398046511104L);
            }
            if (cVar2 != cVar) {
                this.f7754g = cVar;
            }
            return z6;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            m();
        }

        public b(a aVar, int i7) {
            this();
            n(i7);
        }
    }
}
