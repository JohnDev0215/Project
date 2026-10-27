package c.b.h.e;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f2294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReference<ScheduledExecutorService> f2296c = new AtomicReference<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<ScheduledThreadPoolExecutor, Object> f2297d = new ConcurrentHashMap();

    public static final class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            for (ScheduledThreadPoolExecutor scheduledThreadPoolExecutor : new ArrayList(h.f2297d.keySet())) {
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    h.f2297d.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0039 A[LOOP:0: B:19:0x0039->B:25:0x0066, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            c.b.h.e.h.f2296c = r0
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            c.b.h.e.h.f2297d = r0
            java.lang.String r0 = "rx2.purge-enabled"
            r1 = 1
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch: java.lang.Throwable -> L1f
            if (r0 != 0) goto L18
            goto L1f
        L18:
            java.lang.String r2 = "true"
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Throwable -> L1f
            goto L20
        L1f:
            r0 = r1
        L20:
            c.b.h.e.h.f2294a = r0
            java.lang.String r2 = "rx2.purge-period-seconds"
            if (r0 == 0) goto L32
            java.lang.String r0 = java.lang.System.getProperty(r2)     // Catch: java.lang.Throwable -> L32
            if (r0 != 0) goto L2d
            goto L32
        L2d:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L32
            goto L33
        L32:
            r0 = r1
        L33:
            c.b.h.e.h.f2295b = r0
            boolean r0 = c.b.h.e.h.f2294a
            if (r0 == 0) goto L6a
        L39:
            java.util.concurrent.atomic.AtomicReference<java.util.concurrent.ScheduledExecutorService> r0 = c.b.h.e.h.f2296c
            java.lang.Object r0 = r0.get()
            java.util.concurrent.ScheduledExecutorService r0 = (java.util.concurrent.ScheduledExecutorService) r0
            if (r0 == 0) goto L44
            goto L6a
        L44:
            c.b.h.e.e r2 = new c.b.h.e.e
            java.lang.String r3 = "RxSchedulerPurge"
            r2.<init>(r3)
            java.util.concurrent.ScheduledExecutorService r4 = java.util.concurrent.Executors.newScheduledThreadPool(r1, r2)
            java.util.concurrent.atomic.AtomicReference<java.util.concurrent.ScheduledExecutorService> r2 = c.b.h.e.h.f2296c
            boolean r0 = r2.compareAndSet(r0, r4)
            if (r0 == 0) goto L66
            c.b.h.e.h$a r5 = new c.b.h.e.h$a
            r5.<init>()
            int r0 = c.b.h.e.h.f2295b
            long r8 = (long) r0
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.SECONDS
            r6 = r8
            r4.scheduleAtFixedRate(r5, r6, r8, r10)
            goto L6a
        L66:
            r4.shutdownNow()
            goto L39
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.h.e.h.<clinit>():void");
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (f2294a && (scheduledExecutorServiceNewScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            f2297d.put((ScheduledThreadPoolExecutor) scheduledExecutorServiceNewScheduledThreadPool, scheduledExecutorServiceNewScheduledThreadPool);
        }
        return scheduledExecutorServiceNewScheduledThreadPool;
    }
}
