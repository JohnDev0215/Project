package kotlinx.coroutines.scheduling;

import android.support.v4.media.session.PlaybackStateCompat;
import defpackage.a41;
import defpackage.bt0;
import defpackage.d70;
import defpackage.e73;
import defpackage.gq2;
import defpackage.i21;
import defpackage.mb2;
import defpackage.v63;
import defpackage.vi3;
import defpackage.vt3;
import defpackage.w63;
import defpackage.x82;
import defpackage.z63;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: CoroutineScheduler.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Executor, Closeable {
    private volatile /* synthetic */ int _isTerminated;
    volatile /* synthetic */ long controlState;
    public final int e;
    public final int f;
    public final long g;
    public final String h;
    public final bt0 i;
    public final bt0 j;
    public final mb2<C0061a> k;
    private volatile /* synthetic */ long parkedWorkersStack;
    public static final a41 o = new a41(2, "NOT_IN_STACK");
    public static final /* synthetic */ AtomicLongFieldUpdater l = AtomicLongFieldUpdater.newUpdater(a.class, "parkedWorkersStack");
    public static final /* synthetic */ AtomicLongFieldUpdater m = AtomicLongFieldUpdater.newUpdater(a.class, "controlState");
    public static final /* synthetic */ AtomicIntegerFieldUpdater n = AtomicIntegerFieldUpdater.newUpdater(a.class, "_isTerminated");

    /* JADX INFO: renamed from: kotlinx.coroutines.scheduling.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CoroutineScheduler.kt */
    public final class C0061a extends Thread {
        public static final /* synthetic */ AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(C0061a.class, "workerCtl");
        public final vt3 e;
        public int f;
        public long g;
        public long h;
        public int i;
        private volatile int indexInArray;
        public boolean j;
        private volatile Object nextParkedWorker;
        volatile /* synthetic */ int workerCtl;

        public C0061a() {
            throw null;
        }

        public C0061a(int i) {
            a.this = a.this;
            setDaemon(true);
            this.e = new vt3();
            this.f = 4;
            this.workerCtl = 0;
            this.nextParkedWorker = a.o;
            this.i = x82.e.a();
            f(i);
        }

        public final v63 a(boolean z) {
            boolean z2;
            v63 v63VarD;
            v63 v63VarE;
            v63 v63VarE2;
            boolean z3;
            if (this.f == 1) {
                z2 = true;
            } else {
                a aVar = a.this;
                while (true) {
                    long j = aVar.controlState;
                    if (((int) ((9223367638808264704L & j) >> 42)) == 0) {
                        z3 = false;
                        break;
                    }
                    if (a.m.compareAndSet(aVar, j, j - 4398046511104L)) {
                        z3 = true;
                        break;
                    }
                }
                if (z3) {
                    this.f = 1;
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (!z2) {
                if (z) {
                    vt3 vt3Var = this.e;
                    vt3Var.getClass();
                    v63VarD = (v63) vt3.b.getAndSet(vt3Var, null);
                    if (v63VarD == null) {
                        v63VarD = vt3Var.d();
                    }
                    if (v63VarD == null) {
                        v63VarD = a.this.j.d();
                    }
                } else {
                    v63VarD = a.this.j.d();
                }
                return v63VarD == null ? i(true) : v63VarD;
            }
            if (z) {
                boolean z4 = d(a.this.e * 2) == 0;
                if (z4 && (v63VarE2 = e()) != null) {
                    return v63VarE2;
                }
                vt3 vt3Var2 = this.e;
                vt3Var2.getClass();
                v63 v63Var = (v63) vt3.b.getAndSet(vt3Var2, null);
                v63 v63VarD2 = v63Var == null ? vt3Var2.d() : v63Var;
                if (v63VarD2 != null) {
                    return v63VarD2;
                }
                if (!z4 && (v63VarE = e()) != null) {
                    return v63VarE;
                }
            } else {
                v63 v63VarE3 = e();
                if (v63VarE3 != null) {
                    return v63VarE3;
                }
            }
            return i(false);
        }

        public final int b() {
            return this.indexInArray;
        }

        public final Object c() {
            return this.nextParkedWorker;
        }

        public final int d(int i) {
            int i2 = this.i;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.i = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i6 & i5 : (Integer.MAX_VALUE & i5) % i;
        }

        public final v63 e() {
            int iD = d(2);
            a aVar = a.this;
            if (iD == 0) {
                v63 v63VarD = aVar.i.d();
                return v63VarD == null ? aVar.j.d() : v63VarD;
            }
            v63 v63VarD2 = aVar.j.d();
            return v63VarD2 == null ? aVar.i.d() : v63VarD2;
        }

        public final void f(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(a.this.h);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public final void g(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean h(int i) {
            int i2 = this.f;
            boolean z = i2 == 1;
            if (z) {
                a.m.addAndGet(a.this, 4398046511104L);
            }
            if (i2 != i) {
                this.f = i;
            }
            return z;
        }

        public final v63 i(boolean z) {
            long jF;
            int i = (int) (a.this.controlState & 2097151);
            if (i < 2) {
                return null;
            }
            int iD = d(i);
            a aVar = a.this;
            int i2 = 0;
            long jMin = Long.MAX_VALUE;
            while (i2 < i) {
                i2++;
                iD++;
                if (iD > i) {
                    iD = 1;
                }
                C0061a c0061aB = aVar.k.b(iD);
                if (c0061aB != null && c0061aB != this) {
                    if (z) {
                        jF = this.e.e(c0061aB.e);
                    } else {
                        vt3 vt3Var = this.e;
                        vt3 vt3Var2 = c0061aB.e;
                        vt3Var.getClass();
                        v63 v63VarD = vt3Var2.d();
                        if (v63VarD != null) {
                            vt3Var.a(v63VarD, false);
                            jF = -1;
                        } else {
                            jF = vt3Var.f(vt3Var2, false);
                        }
                    }
                    if (jF == -1) {
                        vt3 vt3Var3 = this.e;
                        vt3Var3.getClass();
                        v63 v63Var = (v63) vt3.b.getAndSet(vt3Var3, null);
                        return v63Var == null ? vt3Var3.d() : v63Var;
                    }
                    if (jF > 0) {
                        jMin = Math.min(jMin, jF);
                    }
                }
            }
            if (jMin == Long.MAX_VALUE) {
                jMin = 0;
            }
            this.h = jMin;
            return null;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            loop0: while (true) {
                boolean z = false;
                while (!a.this.isTerminated() && this.f != 5) {
                    v63 v63VarA = a(this.j);
                    if (v63VarA != null) {
                        this.h = 0L;
                        int iB = v63VarA.f.b();
                        this.g = 0L;
                        if (this.f == 3) {
                            this.f = 2;
                        }
                        a aVar = a.this;
                        if (iB != 0 && h(2) && !aVar.v0() && !aVar.R(aVar.controlState)) {
                            aVar.v0();
                        }
                        aVar.getClass();
                        try {
                            v63VarA.run();
                        } finally {
                        }
                        if (iB != 0) {
                            a.m.addAndGet(aVar, -2097152L);
                            if (this.f != 5) {
                                this.f = 4;
                            }
                        }
                    } else {
                        this.j = false;
                        if (this.h == 0) {
                            if (this.nextParkedWorker != a.o) {
                                this.workerCtl = -1;
                                while (true) {
                                    if (!(this.nextParkedWorker != a.o) || this.workerCtl != -1 || a.this.isTerminated() || this.f == 5) {
                                        break;
                                    }
                                    h(3);
                                    Thread.interrupted();
                                    if (this.g == 0) {
                                        this.g = System.nanoTime() + a.this.g;
                                    }
                                    LockSupport.parkNanos(a.this.g);
                                    if (System.nanoTime() - this.g >= 0) {
                                        this.g = 0L;
                                        a aVar2 = a.this;
                                        synchronized (aVar2.k) {
                                            if (!aVar2.isTerminated()) {
                                                if (((int) (aVar2.controlState & 2097151)) > aVar2.e) {
                                                    if (l.compareAndSet(this, -1, 1)) {
                                                        int i = this.indexInArray;
                                                        f(0);
                                                        aVar2.F(this, i, 0);
                                                        int andDecrement = (int) (a.m.getAndDecrement(aVar2) & 2097151);
                                                        if (andDecrement != i) {
                                                            C0061a c0061aB = aVar2.k.b(andDecrement);
                                                            i21.b(c0061aB);
                                                            C0061a c0061a = c0061aB;
                                                            aVar2.k.c(i, c0061a);
                                                            c0061a.f(i);
                                                            aVar2.F(c0061a, andDecrement, i);
                                                        }
                                                        aVar2.k.c(andDecrement, null);
                                                        vi3 vi3Var = vi3.a;
                                                        this.f = 5;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                a.this.C(this);
                            }
                        } else if (z) {
                            h(3);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.h);
                            this.h = 0L;
                        } else {
                            z = true;
                        }
                    }
                }
                break loop0;
            }
            h(5);
        }
    }

    public a(String str, int i, int i2, long j) {
        this.e = i;
        this.f = i2;
        this.g = j;
        this.h = str;
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("Core pool size " + i + " should be at least 1").toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should be greater than or equals to core pool size " + i).toString());
        }
        if (!(i2 <= 2097150)) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (!(j > 0)) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.i = new bt0();
        this.j = new bt0();
        this.parkedWorkersStack = 0L;
        this.k = new mb2<>(i + 1);
        this.controlState = ((long) i) << 42;
        this._isTerminated = 0;
    }

    public final void C(C0061a c0061a) {
        long j;
        long j2;
        int iB;
        if (c0061a.c() != o) {
            return;
        }
        do {
            j = this.parkedWorkersStack;
            j2 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152);
            iB = c0061a.b();
            c0061a.g(this.k.b((int) (2097151 & j)));
        } while (!l.compareAndSet(this, j, ((long) iB) | j2));
    }

    public final void F(C0061a c0061a, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j);
            long j2 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object objC = c0061a.c();
                    while (true) {
                        if (objC == o) {
                            i3 = -1;
                            break;
                        }
                        if (objC == null) {
                            i3 = 0;
                            break;
                        }
                        C0061a c0061a2 = (C0061a) objC;
                        int iB = c0061a2.b();
                        if (iB != 0) {
                            i3 = iB;
                            break;
                        }
                        objC = c0061a2.c();
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0 && l.compareAndSet(this, j, j2 | ((long) i3))) {
                return;
            }
        }
    }

    public final boolean R(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        int i2 = this.e;
        if (i < i2) {
            int iQ = q();
            if (iQ == 1 && i2 > 1) {
                q();
            }
            if (iQ > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws InterruptedException {
        int i;
        boolean z;
        if (n.compareAndSet(this, 0, 1)) {
            Thread threadCurrentThread = Thread.currentThread();
            C0061a c0061a = threadCurrentThread instanceof C0061a ? (C0061a) threadCurrentThread : null;
            if (c0061a == null || !i21.a(a.this, this)) {
                c0061a = null;
            }
            synchronized (this.k) {
                i = (int) (this.controlState & 2097151);
            }
            if (1 <= i) {
                int i2 = 1;
                while (true) {
                    int i3 = i2 + 1;
                    C0061a c0061aB = this.k.b(i2);
                    i21.b(c0061aB);
                    C0061a c0061a2 = c0061aB;
                    if (c0061a2 != c0061a) {
                        while (c0061a2.isAlive()) {
                            LockSupport.unpark(c0061a2);
                            c0061a2.join(10000L);
                        }
                        vt3 vt3Var = c0061a2.e;
                        bt0 bt0Var = this.j;
                        vt3Var.getClass();
                        v63 v63Var = (v63) vt3.b.getAndSet(vt3Var, null);
                        if (v63Var != null) {
                            bt0Var.a(v63Var);
                        }
                        do {
                            v63 v63VarD = vt3Var.d();
                            if (v63VarD == null) {
                                z = false;
                            } else {
                                bt0Var.a(v63VarD);
                                z = true;
                            }
                        } while (z);
                    }
                    if (i2 == i) {
                        break;
                    } else {
                        i2 = i3;
                    }
                }
            }
            this.j.b();
            this.i.b();
            while (true) {
                v63 v63VarA = c0061a == null ? null : c0061a.a(true);
                if (v63VarA == null && (v63VarA = this.i.d()) == null && (v63VarA = this.j.d()) == null) {
                    break;
                }
                try {
                    v63VarA.run();
                } finally {
                }
            }
            if (c0061a != null) {
                c0061a.h(5);
            }
            this.parkedWorkersStack = 0L;
            this.controlState = 0L;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        t(runnable, e73.f, false);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public final boolean isTerminated() {
        return this._isTerminated;
    }

    public final int q() {
        synchronized (this.k) {
            if (this._isTerminated != 0) {
                return -1;
            }
            long j = this.controlState;
            int i = (int) (j & 2097151);
            int i2 = i - ((int) ((j & 4398044413952L) >> 21));
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= this.e) {
                return 0;
            }
            if (i >= this.f) {
                return 0;
            }
            int i3 = ((int) (this.controlState & 2097151)) + 1;
            if (!(i3 > 0 && this.k.b(i3) == null)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            C0061a c0061a = new C0061a(i3);
            this.k.c(i3, c0061a);
            if (!(i3 == ((int) (2097151 & m.incrementAndGet(this))))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            c0061a.start();
            return i2 + 1;
        }
    }

    public final void t(Runnable runnable, w63 w63Var, boolean z) {
        v63 z63Var;
        v63 v63VarA;
        e73.e.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof v63) {
            z63Var = (v63) runnable;
            z63Var.e = jNanoTime;
            z63Var.f = w63Var;
        } else {
            z63Var = new z63(runnable, jNanoTime, w63Var);
        }
        Thread threadCurrentThread = Thread.currentThread();
        C0061a c0061a = null;
        C0061a c0061a2 = threadCurrentThread instanceof C0061a ? (C0061a) threadCurrentThread : null;
        if (c0061a2 != null && i21.a(a.this, this)) {
            c0061a = c0061a2;
        }
        if (c0061a == null || c0061a.f == 5 || (z63Var.f.b() == 0 && c0061a.f == 2)) {
            v63VarA = z63Var;
        } else {
            c0061a.j = true;
            v63VarA = c0061a.e.a(z63Var, z);
        }
        if (v63VarA != null) {
            if (!(v63VarA.f.b() == 1 ? this.j.a(v63VarA) : this.i.a(v63VarA))) {
                throw new RejectedExecutionException(i21.g(" was terminated", this.h));
            }
        }
        boolean z2 = z && c0061a != null;
        if (z63Var.f.b() == 0) {
            if (z2 || v0() || R(this.controlState)) {
                return;
            }
            v0();
            return;
        }
        long jAddAndGet = m.addAndGet(this, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
        if (z2 || v0() || R(jAddAndGet)) {
            return;
        }
        v0();
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        int iA = this.k.a();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (i6 < iA) {
            int i7 = i6 + 1;
            C0061a c0061aB = this.k.b(i6);
            if (c0061aB != null) {
                int iC = c0061aB.e.c();
                int iM = gq2.m(c0061aB.f);
                if (iM == 0) {
                    i++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(iC);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iM == 1) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(iC);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iM == 2) {
                    i3++;
                } else if (iM == 3) {
                    i4++;
                    if (iC > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(iC);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (iM == 4) {
                    i5++;
                }
            }
            i6 = i7;
        }
        long j = this.controlState;
        return this.h + '@' + d70.d(this) + "[Pool Size {core = " + this.e + ", max = " + this.f + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.i.c() + ", global blocking queue size = " + this.j.c() + ", Control State {created workers= " + ((int) (2097151 & j)) + ", blocking tasks = " + ((int) ((4398044413952L & j) >> 21)) + ", CPUs acquired = " + (this.e - ((int) ((9223367638808264704L & j) >> 42))) + "}]";
    }

    public final boolean v0() {
        a41 a41Var;
        int iB;
        while (true) {
            long j = this.parkedWorkersStack;
            C0061a c0061aB = this.k.b((int) (2097151 & j));
            if (c0061aB == null) {
                c0061aB = null;
            } else {
                long j2 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j) & (-2097152);
                Object objC = c0061aB.c();
                while (true) {
                    a41Var = o;
                    if (objC == a41Var) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    C0061a c0061a = (C0061a) objC;
                    iB = c0061a.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = c0061a.c();
                }
                if (iB >= 0 && l.compareAndSet(this, j, ((long) iB) | j2)) {
                    c0061aB.g(a41Var);
                }
            }
            if (c0061aB == null) {
                return false;
            }
            if (C0061a.l.compareAndSet(c0061aB, -1, 0)) {
                LockSupport.unpark(c0061aB);
                return true;
            }
        }
    }
}
