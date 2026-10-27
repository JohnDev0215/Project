package defpackage;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class E extends P implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;
    public static final E f = new E();
    private static final long g;

    static {
        Long l;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        g = timeUnit.toNanos(l.longValue());
    }

    private E() {
    }

    private final synchronized void j() {
        int i = debugStatus;
        if (i == 2 || i == 3) {
            debugStatus = 3;
            i();
            notifyAll();
        }
    }

    @Override // defpackage.Q
    protected final Thread e() {
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

    @Override // defpackage.P
    public final void f(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.f(runnable);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        b1.a(this);
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
                j();
                if (g()) {
                    return;
                }
                e();
                return;
            }
            long j = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long jH = h();
                if (jH == Long.MAX_VALUE) {
                    long jNanoTime = System.nanoTime();
                    if (j == Long.MAX_VALUE) {
                        j = g + jNanoTime;
                    }
                    long j2 = j - jNanoTime;
                    if (j2 <= 0) {
                        _thread = null;
                        j();
                        if (g()) {
                            return;
                        }
                        e();
                        return;
                    }
                    if (jH > j2) {
                        jH = j2;
                    }
                } else {
                    j = Long.MAX_VALUE;
                }
                if (jH > 0) {
                    int i2 = debugStatus;
                    if (i2 == 2 || i2 == 3) {
                        _thread = null;
                        j();
                        if (g()) {
                            return;
                        }
                        e();
                        return;
                    }
                    LockSupport.parkNanos(this, jH);
                }
            }
        } catch (Throwable th) {
            _thread = null;
            j();
            if (!g()) {
                e();
            }
            throw th;
        }
    }
}
