package kotlinx.coroutines;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: DefaultExecutor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends z0 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final long f7709l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final n0 f7710m;

    static {
        Long l7;
        n0 n0Var = new n0();
        f7710m = n0Var;
        y0.i0(n0Var, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l7 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l7 = 1000L;
        }
        f7709l = timeUnit.toNanos(l7.longValue());
    }

    private n0() {
    }

    private final synchronized void E0() {
        if (G0()) {
            debugStatus = 3;
            y0();
            notifyAll();
        }
    }

    private final synchronized Thread F0() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    private final boolean G0() {
        int i7 = debugStatus;
        return i7 == 2 || i7 == 3;
    }

    private final synchronized boolean H0() {
        if (G0()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    @Override // kotlinx.coroutines.z0, kotlinx.coroutines.q0
    public v0 N(long j7, Runnable runnable, k5.g gVar) {
        return B0(j7, runnable);
    }

    @Override // kotlinx.coroutines.a1
    protected Thread m0() {
        Thread thread = _thread;
        return thread != null ? thread : F0();
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zV0;
        h2.f7614b.c(this);
        i2 i2VarA = j2.a();
        if (i2VarA != null) {
            i2VarA.f();
        }
        try {
            if (!H0()) {
                if (zV0) {
                    return;
                } else {
                    return;
                }
            }
            long j7 = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long jW0 = w0();
                if (jW0 == Long.MAX_VALUE) {
                    i2 i2VarA2 = j2.a();
                    long jA = i2VarA2 != null ? i2VarA2.a() : System.nanoTime();
                    if (j7 == Long.MAX_VALUE) {
                        j7 = f7709l + jA;
                    }
                    long j8 = j7 - jA;
                    if (j8 <= 0) {
                        _thread = null;
                        E0();
                        i2 i2VarA3 = j2.a();
                        if (i2VarA3 != null) {
                            i2VarA3.e();
                        }
                        if (v0()) {
                            return;
                        }
                        m0();
                        return;
                    }
                    jW0 = v5.h.e(jW0, j8);
                } else {
                    j7 = Long.MAX_VALUE;
                }
                if (jW0 > 0) {
                    if (G0()) {
                        _thread = null;
                        E0();
                        i2 i2VarA4 = j2.a();
                        if (i2VarA4 != null) {
                            i2VarA4.e();
                        }
                        if (v0()) {
                            return;
                        }
                        m0();
                        return;
                    }
                    i2 i2VarA5 = j2.a();
                    if (i2VarA5 != null) {
                        i2VarA5.c(this, jW0);
                    } else {
                        LockSupport.parkNanos(this, jW0);
                    }
                }
            }
        } finally {
            _thread = null;
            E0();
            i2 i2VarA6 = j2.a();
            if (i2VarA6 != null) {
                i2VarA6.e();
            }
            if (!v0()) {
                m0();
            }
        }
    }
}
