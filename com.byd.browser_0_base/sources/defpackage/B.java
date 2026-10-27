package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class B extends Thread {
    static final /* synthetic */ AtomicIntegerFieldUpdater j = AtomicIntegerFieldUpdater.newUpdater(B.class, "workerCtl");
    public final h1 c;
    public int d;
    private long e;
    private long f;
    private int g;
    public boolean h;
    final /* synthetic */ D i;
    private volatile int indexInArray;
    private volatile Object nextParkedWorker;
    volatile /* synthetic */ int workerCtl;

    public B(D d, int i) {
        this.i = d;
        this.i = d;
        setDaemon(true);
        this.c = new h1();
        this.d = 4;
        this.workerCtl = 0;
        this.nextParkedWorker = D.m;
        this.g = G0.c.b();
        f(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [a0, q0] */
    /* JADX WARN: Type inference failed for: r0v9, types: [a0, q0] */
    private final W0 e() {
        DialogInterfaceOnClickListenerC0206q0 dialogInterfaceOnClickListenerC0206q0;
        int iD = d(2);
        D d = this.i;
        if (iD == 0) {
            W0 w0 = (W0) d.g.d();
            if (w0 != null) {
                return w0;
            }
            dialogInterfaceOnClickListenerC0206q0 = d.h;
        } else {
            W0 w02 = (W0) d.h.d();
            if (w02 != null) {
                return w02;
            }
            dialogInterfaceOnClickListenerC0206q0 = d.g;
        }
        return (W0) dialogInterfaceOnClickListenerC0206q0.d();
    }

    private final W0 i(boolean z) {
        int i = (int) (this.i.controlState & 2097151);
        if (i < 2) {
            return null;
        }
        int iD = d(i);
        D d = this.i;
        int i2 = 0;
        long jMin = Long.MAX_VALUE;
        while (i2 < i) {
            i2++;
            iD++;
            if (iD > i) {
                iD = 1;
            }
            B b = (B) d.i.b(iD);
            if (b != null && b != this) {
                h1 h1Var = this.c;
                h1 h1Var2 = b.c;
                long jG = z ? h1Var.g(h1Var2) : h1Var.h(h1Var2);
                if (jG == -1) {
                    return this.c.e();
                }
                if (jG > 0) {
                    jMin = Math.min(jMin, jG);
                }
            }
        }
        if (jMin == Long.MAX_VALUE) {
            jMin = 0;
        }
        this.f = jMin;
        return null;
    }

    /* JADX WARN: Type inference failed for: r10v2, types: [a0, q0] */
    public final W0 a(boolean z) {
        boolean z2;
        W0 w0E;
        W0 w0E2;
        W0 w0E3;
        boolean z3;
        if (this.d == 1) {
            z2 = true;
        } else {
            D d = this.i;
            while (true) {
                long j2 = d.controlState;
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    z3 = false;
                    break;
                }
                if (D.k.compareAndSet(d, j2, j2 - 4398046511104L)) {
                    z3 = true;
                    break;
                }
            }
            if (z3) {
                this.d = 1;
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (!z2) {
            if (!z || (w0E = this.c.e()) == null) {
                w0E = (W0) this.i.h.d();
            }
            return w0E == null ? i(true) : w0E;
        }
        if (z) {
            boolean z4 = d(this.i.c * 2) == 0;
            if (z4 && (w0E3 = e()) != null) {
                return w0E3;
            }
            W0 w0E4 = this.c.e();
            if (w0E4 != null) {
                return w0E4;
            }
            if (!z4 && (w0E2 = e()) != null) {
                return w0E2;
            }
        } else {
            W0 w0E5 = e();
            if (w0E5 != null) {
                return w0E5;
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
        int i2 = this.g;
        int i3 = i2 ^ (i2 << 13);
        int i4 = i3 ^ (i3 >> 17);
        int i5 = i4 ^ (i4 << 5);
        this.g = i5;
        int i6 = i - 1;
        return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
    }

    public final void f(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.i.f);
        sb.append("-worker-");
        sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
        setName(sb.toString());
        this.indexInArray = i;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(int i) {
        int i2 = this.d;
        boolean z = i2 == 1;
        if (z) {
            D.k.addAndGet(this.i, 4398046511104L);
        }
        if (i2 != i) {
            this.d = i;
        }
        return z;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        loop0: while (true) {
            boolean z = false;
            while (!this.i.c() && this.d != 5) {
                W0 w0A = a(this.h);
                if (w0A != null) {
                    this.f = 0L;
                    int iA = ((Y0) w0A.d).a();
                    this.e = 0L;
                    if (this.d == 3) {
                        this.d = 2;
                    }
                    D d = this.i;
                    if (iA != 0 && h(2)) {
                        d.f();
                    }
                    d.getClass();
                    try {
                        ((Z0) w0A).run();
                    } catch (Throwable th) {
                        Thread threadCurrentThread = Thread.currentThread();
                        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                    }
                    if (iA != 0) {
                        D.k.addAndGet(d, -2097152L);
                        if (this.d != 5) {
                            this.d = 4;
                        }
                    }
                } else {
                    this.h = false;
                    if (this.f == 0) {
                        if (this.nextParkedWorker != D.m) {
                            this.workerCtl = -1;
                            while (true) {
                                if (!(this.nextParkedWorker != D.m) || this.workerCtl != -1 || this.i.c() || this.d == 5) {
                                    break;
                                }
                                h(3);
                                Thread.interrupted();
                                if (this.e == 0) {
                                    this.e = System.nanoTime() + this.i.e;
                                }
                                LockSupport.parkNanos(this.i.e);
                                if (System.nanoTime() - this.e >= 0) {
                                    this.e = 0L;
                                    D d2 = this.i;
                                    synchronized (d2.i) {
                                        if (!d2.c() && ((int) (d2.controlState & 2097151)) > d2.c && j.compareAndSet(this, -1, 1)) {
                                            int i = this.indexInArray;
                                            f(0);
                                            d2.e(this, i, 0);
                                            int andDecrement = (int) (D.k.getAndDecrement(d2) & 2097151);
                                            if (andDecrement != i) {
                                                Object objB = d2.i.b(andDecrement);
                                                C0186g0.b(objB);
                                                B b = (B) objB;
                                                d2.i.c(i, b);
                                                b.f(i);
                                                d2.e(b, andDecrement, i);
                                            }
                                            d2.i.c(andDecrement, null);
                                            this.d = 5;
                                        }
                                    }
                                }
                            }
                        } else {
                            this.i.d(this);
                        }
                    } else if (z) {
                        h(3);
                        Thread.interrupted();
                        LockSupport.parkNanos(this.f);
                        this.f = 0L;
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
