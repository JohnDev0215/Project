package kotlinx.coroutines;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import p.mi0;
import p.ni0;
import p.oa0;
import p.qi0;
import p.rn1;
import p.tz2;
import p.ul2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class c extends e implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;
    public static final c r;
    public static final long s;

    static {
        Long l;
        c cVar = new c();
        r = cVar;
        cVar.N0(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        s = timeUnit.toNanos(l.longValue());
    }

    @Override // kotlinx.coroutines.e, p.o80
    public final oa0 J(long j, ul2 ul2Var, y10 y10Var) {
        tz2 tz2Var = qi0.a;
        long j2 = j > 0 ? j >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j : 0L;
        if (j2 >= 4611686018427387903L) {
            return rn1.c;
        }
        long jNanoTime = System.nanoTime();
        mi0 mi0Var = new mi0(ul2Var, j2 + jNanoTime);
        V0(jNanoTime, mi0Var);
        return mi0Var;
    }

    @Override // p.pi0
    public final Thread M0() {
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

    @Override // p.pi0
    public final void R0(long j, ni0 ni0Var) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.e
    public final void S0(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.S0(runnable);
    }

    public final synchronized void W0() {
        int i = debugStatus;
        if (i == 2 || i == 3) {
            debugStatus = 3;
            e.o.set(this, null);
            e.f89p.set(this, null);
            notifyAll();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0072, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r14 = this;
            java.lang.ThreadLocal r0 = p.kk2.a
            r0.set(r14)
            r0 = 0
            monitor-enter(r14)     // Catch: java.lang.Throwable -> L37
            int r1 = kotlinx.coroutines.c.debugStatus     // Catch: java.lang.Throwable -> L73
            r2 = 2
            if (r1 == r2) goto L75
            r3 = 3
            if (r1 != r3) goto L11
            goto L75
        L11:
            r1 = 1
            kotlinx.coroutines.c.debugStatus = r1     // Catch: java.lang.Throwable -> L73
            r14.notifyAll()     // Catch: java.lang.Throwable -> L73
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L37
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6 = r4
        L1e:
            java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L37
            long r8 = r14.P0()     // Catch: java.lang.Throwable -> L37
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            r10 = 0
            if (r1 != 0) goto L54
            long r12 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L37
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 != 0) goto L39
            long r6 = kotlinx.coroutines.c.s     // Catch: java.lang.Throwable -> L37
            long r6 = r6 + r12
            goto L39
        L37:
            r1 = move-exception
            goto L87
        L39:
            long r12 = r6 - r12
            int r1 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r1 > 0) goto L4e
            kotlinx.coroutines.c._thread = r0
            r14.W0()
            boolean r0 = r14.U0()
            if (r0 != 0) goto L4d
            r14.M0()
        L4d:
            return
        L4e:
            int r1 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r1 <= 0) goto L55
            r8 = r12
            goto L55
        L54:
            r6 = r4
        L55:
            int r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r1 <= 0) goto L1e
            int r1 = kotlinx.coroutines.c.debugStatus     // Catch: java.lang.Throwable -> L37
            if (r1 == r2) goto L64
            if (r1 != r3) goto L60
            goto L64
        L60:
            java.util.concurrent.locks.LockSupport.parkNanos(r14, r8)     // Catch: java.lang.Throwable -> L37
            goto L1e
        L64:
            kotlinx.coroutines.c._thread = r0
            r14.W0()
            boolean r0 = r14.U0()
            if (r0 != 0) goto L72
            r14.M0()
        L72:
            return
        L73:
            r1 = move-exception
            goto L85
        L75:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L37
            kotlinx.coroutines.c._thread = r0
            r14.W0()
            boolean r0 = r14.U0()
            if (r0 != 0) goto L84
            r14.M0()
        L84:
            return
        L85:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L37
            throw r1     // Catch: java.lang.Throwable -> L37
        L87:
            kotlinx.coroutines.c._thread = r0
            r14.W0()
            boolean r0 = r14.U0()
            if (r0 != 0) goto L95
            r14.M0()
        L95:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.c.run():void");
    }

    @Override // kotlinx.coroutines.e, p.pi0
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
