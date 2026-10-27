package defpackage;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class D implements Executor, Closeable {
    private volatile /* synthetic */ int _isTerminated;
    public final int c;
    volatile /* synthetic */ long controlState;
    public final int d;
    public final long e;
    public final String f;
    public final C0174a0 g;
    public final C0174a0 h;
    public final J0 i;
    private volatile /* synthetic */ long parkedWorkersStack;
    public static final T0 m = new T0("NOT_IN_STACK");
    private static final /* synthetic */ AtomicLongFieldUpdater j = AtomicLongFieldUpdater.newUpdater(D.class, "parkedWorkersStack");
    static final /* synthetic */ AtomicLongFieldUpdater k = AtomicLongFieldUpdater.newUpdater(D.class, "controlState");
    private static final /* synthetic */ AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(D.class, "_isTerminated");

    public D(int i, int i2, long j2, String str) {
        this.c = i;
        this.d = i2;
        this.e = j2;
        this.f = str;
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("Core pool size " + i + " should be at least 1").toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should be greater than or equals to core pool size " + i).toString());
        }
        if (!(i2 <= 2097150)) {
            throw new IllegalArgumentException(("Max pool size " + i2 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (!(j2 > 0)) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
        }
        this.g = new C0174a0();
        this.h = new C0174a0();
        this.parkedWorkersStack = 0L;
        this.i = new J0(i + 1);
        this.controlState = ((long) i) << 42;
        this._isTerminated = 0;
    }

    private final int a() {
        synchronized (this.i) {
            if (this._isTerminated != 0) {
                return -1;
            }
            long j2 = this.controlState;
            int i = (int) (j2 & 2097151);
            int i2 = i - ((int) ((j2 & 4398044413952L) >> 21));
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= this.c) {
                return 0;
            }
            if (i >= this.d) {
                return 0;
            }
            int i3 = ((int) (this.controlState & 2097151)) + 1;
            if (!(i3 > 0 && this.i.b(i3) == null)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            B b = new B(this, i3);
            this.i.c(i3, b);
            if (!(i3 == ((int) (2097151 & k.incrementAndGet(this))))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            b.start();
            return i2 + 1;
        }
    }

    private final boolean g(long j2) {
        int i = ((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        int i2 = this.c;
        if (i < i2) {
            int iA = a();
            if (iA == 1 && i2 > 1) {
                a();
            }
            if (iA > 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean h() {
        T0 t0;
        int iB;
        while (true) {
            long j2 = this.parkedWorkersStack;
            B b = (B) this.i.b((int) (2097151 & j2));
            if (b == null) {
                b = null;
            } else {
                long j3 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j2) & (-2097152);
                B b2 = b;
                while (true) {
                    Object objC = b2.c();
                    t0 = m;
                    if (objC == t0) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    b2 = (B) objC;
                    iB = b2.b();
                    if (iB != 0) {
                        break;
                    }
                }
                if (iB >= 0 && j.compareAndSet(this, j2, ((long) iB) | j3)) {
                    b.g(t0);
                }
            }
            if (b == null) {
                return false;
            }
            if (B.j.compareAndSet(b, -1, 0)) {
                LockSupport.unpark(b);
                return true;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(Runnable runnable, X0 x0, boolean z) {
        W0 z0;
        W0 w0A;
        a1.e.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof W0) {
            z0 = (W0) runnable;
            z0.c = jNanoTime;
            z0.d = x0;
        } else {
            z0 = new Z0(runnable, jNanoTime, x0);
        }
        Thread threadCurrentThread = Thread.currentThread();
        B b = null;
        B b2 = threadCurrentThread instanceof B ? (B) threadCurrentThread : null;
        if (b2 != null && C0186g0.a(b2.i, this)) {
            b = b2;
        }
        if (b == null || b.d == 5 || (((Y0) z0.d).a() == 0 && b.d == 2)) {
            w0A = z0;
        } else {
            b.h = true;
            w0A = b.c.a(z0, z);
        }
        if (w0A != null) {
            if (!(((Y0) w0A.d).a() == 1 ? this.h : this.g).a(w0A)) {
                throw new RejectedExecutionException(C0186g0.g(" was terminated", this.f));
            }
        }
        boolean z2 = z && b != null;
        if (((Y0) z0.d).a() == 0) {
            if (z2) {
                return;
            }
            f();
        } else {
            long jAddAndGet = k.addAndGet(this, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
            if (z2 || h() || g(jAddAndGet)) {
                return;
            }
            h();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean c() {
        return this._isTerminated;
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [a0, q0] */
    /* JADX WARN: Type inference failed for: r1v13, types: [a0, q0] */
    /* JADX WARN: Type inference failed for: r1v3, types: [a0, q0] */
    /* JADX WARN: Type inference failed for: r1v4, types: [a0, q0] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws InterruptedException {
        int i;
        if (l.compareAndSet(this, 0, 1)) {
            Thread threadCurrentThread = Thread.currentThread();
            B b = threadCurrentThread instanceof B ? (B) threadCurrentThread : null;
            if (b == null || !C0186g0.a(b.i, this)) {
                b = null;
            }
            synchronized (this.i) {
                i = (int) (this.controlState & 2097151);
            }
            if (1 <= i) {
                int i2 = 1;
                while (true) {
                    int i3 = i2 + 1;
                    Object objB = this.i.b(i2);
                    C0186g0.b(objB);
                    B b2 = (B) objB;
                    if (b2 != b) {
                        while (b2.isAlive()) {
                            LockSupport.unpark(b2);
                            b2.join(10000L);
                        }
                        b2.c.d(this.h);
                    }
                    if (i2 == i) {
                        break;
                    } else {
                        i2 = i3;
                    }
                }
            }
            this.h.b();
            this.g.b();
            while (true) {
                W0 w0A = b == null ? null : b.a(true);
                if (w0A == null && (w0A = (W0) this.g.d()) == null && (w0A = (W0) this.h.d()) == null) {
                    break;
                }
                try {
                    ((Z0) w0A).run();
                } catch (Throwable th) {
                    Thread threadCurrentThread2 = Thread.currentThread();
                    threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
                }
            }
            if (b != null) {
                b.h(5);
            }
            this.parkedWorkersStack = 0L;
            this.controlState = 0L;
        }
    }

    public final void d(B b) {
        long j2;
        long j3;
        int iB;
        if (b.c() != m) {
            return;
        }
        do {
            j2 = this.parkedWorkersStack;
            j3 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j2) & (-2097152);
            iB = b.b();
            b.g(this.i.b((int) (2097151 & j2)));
        } while (!j.compareAndSet(this, j2, ((long) iB) | j3));
    }

    public final void e(B b, int i, int i2) {
        while (true) {
            long j2 = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j2);
            long j3 = (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE + j2) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    B b2 = b;
                    while (true) {
                        Object objC = b2.c();
                        if (objC == m) {
                            i3 = -1;
                            break;
                        }
                        if (objC == null) {
                            i3 = 0;
                            break;
                        }
                        b2 = (B) objC;
                        int iB = b2.b();
                        if (iB != 0) {
                            i3 = iB;
                            break;
                        }
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0 && j.compareAndSet(this, j2, j3 | ((long) i3))) {
                return;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        b(runnable, a1.f, false);
    }

    public final void f() {
        if (h() || g(this.controlState)) {
            return;
        }
        h();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a0, q0] */
    /* JADX WARN: Type inference failed for: r0v5, types: [a0, q0] */
    public final String toString() {
        StringBuilder sb;
        char c;
        ArrayList arrayList = new ArrayList();
        int iA = this.i.a();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (i6 < iA) {
            int i7 = i6 + 1;
            B b = (B) this.i.b(i6);
            if (b != null) {
                int iC = b.c.c();
                int iA2 = C.a(b.d);
                if (iA2 == 0) {
                    i++;
                    sb = new StringBuilder();
                    sb.append(iC);
                    c = 'c';
                } else if (iA2 == 1) {
                    i2++;
                    sb = new StringBuilder();
                    sb.append(iC);
                    c = 'b';
                } else if (iA2 == 2) {
                    i3++;
                } else if (iA2 == 3) {
                    i4++;
                    if (iC > 0) {
                        sb = new StringBuilder();
                        sb.append(iC);
                        c = 'd';
                    }
                } else if (iA2 == 4) {
                    i5++;
                }
                sb.append(c);
                arrayList.add(sb.toString());
            }
            i6 = i7;
        }
        long j2 = this.controlState;
        return this.f + '@' + Integer.toHexString(System.identityHashCode(this)) + "[Pool Size {core = " + this.c + ", max = " + this.d + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.g.c() + ", global blocking queue size = " + this.h.c() + ", Control State {created workers= " + ((int) (2097151 & j2)) + ", blocking tasks = " + ((int) ((4398044413952L & j2) >> 21)) + ", CPUs acquired = " + (this.c - ((int) ((9223367638808264704L & j2) >> 42))) + "}]";
    }
}
