package defpackage;

import defpackage.bi0;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: DefaultExecutor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class q70 extends bi0 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;
    public static final q70 k;
    public static final long l;

    static {
        Long l2;
        q70 q70Var = new q70();
        k = q70Var;
        q70Var.f = 1 + q70Var.f;
        q70Var.g = true;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l2 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l2 = 1000L;
        }
        l = timeUnit.toNanos(l2.longValue());
    }

    @Override // defpackage.ci0
    public final Thread m1() {
        Thread thread = _thread;
        if (thread == null) {
            synchronized (this) {
                thread = _thread;
                if (thread == null) {
                    thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                    _thread = thread;
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }
        return thread;
    }

    @Override // defpackage.ci0
    public final void n1(long j, bi0.a aVar) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // defpackage.bi0
    public final void o1(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.o1(runnable);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        w83.a.set(this);
        try {
            synchronized (this) {
                int i = debugStatus;
                if (i == 2 || i == 3) {
                    z = false;
                } else {
                    debugStatus = 1;
                    notifyAll();
                    z = true;
                }
            }
            if (!z) {
                _thread = null;
                u1();
                if (q1()) {
                    return;
                }
                m1();
                return;
            }
            long j = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long jR1 = r1();
                if (jR1 == Long.MAX_VALUE) {
                    long jNanoTime = System.nanoTime();
                    if (j == Long.MAX_VALUE) {
                        j = l + jNanoTime;
                    }
                    long j2 = j - jNanoTime;
                    if (j2 <= 0) {
                        _thread = null;
                        u1();
                        if (q1()) {
                            return;
                        }
                        m1();
                        return;
                    }
                    if (jR1 > j2) {
                        jR1 = j2;
                    }
                } else {
                    j = Long.MAX_VALUE;
                }
                if (jR1 > 0) {
                    int i2 = debugStatus;
                    if (i2 == 2 || i2 == 3) {
                        _thread = null;
                        u1();
                        if (q1()) {
                            return;
                        }
                        m1();
                        return;
                    }
                    LockSupport.parkNanos(this, jR1);
                }
            }
        } catch (Throwable th) {
            _thread = null;
            u1();
            if (!q1()) {
                m1();
            }
            throw th;
        }
    }

    @Override // defpackage.bi0, defpackage.ai0
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    public final synchronized void u1() {
        int i = debugStatus;
        if (i == 2 || i == 3) {
            debugStatus = 3;
            s1();
            notifyAll();
        }
    }
}
