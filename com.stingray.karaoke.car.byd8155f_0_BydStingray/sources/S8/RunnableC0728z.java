package S8;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: S8.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0728z extends M implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final RunnableC0728z f8152u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final long f8153v;

    static {
        Long l;
        RunnableC0728z runnableC0728z = new RunnableC0728z();
        f8152u = runnableC0728z;
        runnableC0728z.S(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        f8153v = timeUnit.toNanos(l.longValue());
    }

    @Override // S8.M, S8.B
    public final F I(long j3, q0 q0Var, InterfaceC2194h interfaceC2194h) {
        long j10 = j3 > 0 ? j3 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j3 : 0L;
        if (j10 >= 4611686018427387903L) {
            return h0.l;
        }
        long jNanoTime = System.nanoTime();
        J j11 = new J(j10 + jNanoTime, q0Var);
        b0(jNanoTime, j11);
        return j11;
    }

    @Override // S8.N
    public final Thread R() {
        Thread thread = _thread;
        if (thread == null) {
            synchronized (this) {
                thread = _thread;
                if (thread == null) {
                    thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                    _thread = thread;
                    thread.setContextClassLoader(f8152u.getClass().getClassLoader());
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        return thread;
    }

    @Override // S8.N
    public final void W(long j3, K k10) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // S8.M
    public final void X(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.X(runnable);
    }

    public final synchronized void c0() {
        int i7 = debugStatus;
        if (i7 == 2 || i7 == 3) {
            debugStatus = 3;
            M.f8090r.set(this, null);
            M.f8091s.set(this, null);
            notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zA0;
        n0.f8122a.set(this);
        try {
            synchronized (this) {
                int i7 = debugStatus;
                if (i7 == 2 || i7 == 3) {
                    if (zA0) {
                        return;
                    } else {
                        return;
                    }
                }
                debugStatus = 1;
                notifyAll();
                long j3 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jU = U();
                    if (jU == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j3 == Long.MAX_VALUE) {
                            j3 = f8153v + jNanoTime;
                        }
                        long j10 = j3 - jNanoTime;
                        if (j10 <= 0) {
                            _thread = null;
                            c0();
                            if (a0()) {
                                return;
                            }
                            R();
                            return;
                        }
                        if (jU > j10) {
                            jU = j10;
                        }
                    } else {
                        j3 = Long.MAX_VALUE;
                    }
                    if (jU > 0) {
                        int i10 = debugStatus;
                        if (i10 == 2 || i10 == 3) {
                            _thread = null;
                            c0();
                            if (a0()) {
                                return;
                            }
                            R();
                            return;
                        }
                        LockSupport.parkNanos(this, jU);
                    }
                }
            }
        } finally {
            _thread = null;
            c0();
            if (!a0()) {
                R();
            }
        }
    }

    @Override // S8.M, S8.N
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        return "DefaultExecutor";
    }
}
