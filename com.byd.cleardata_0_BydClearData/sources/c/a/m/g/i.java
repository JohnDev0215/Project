package c.a.m.g;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1886b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final AtomicReference<ScheduledExecutorService> f1887c = new AtomicReference<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Map<ScheduledThreadPoolExecutor, Object> f1888d = new ConcurrentHashMap();

    static final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                for (ScheduledThreadPoolExecutor scheduledThreadPoolExecutor : new ArrayList(i.f1888d.keySet())) {
                    if (scheduledThreadPoolExecutor.isShutdown()) {
                        i.f1888d.remove(scheduledThreadPoolExecutor);
                    } else {
                        scheduledThreadPoolExecutor.purge();
                    }
                }
            } catch (Throwable th) {
                c.a.n.a.l(th);
            }
        }
    }

    static {
        Properties properties = System.getProperties();
        int iIntValue = 1;
        boolean z = properties.containsKey("rx2.purge-enabled") ? Boolean.getBoolean("rx2.purge-enabled") : true;
        if (z && properties.containsKey("rx2.purge-period-seconds")) {
            iIntValue = Integer.getInteger("rx2.purge-period-seconds", 1).intValue();
        }
        f1885a = z;
        f1886b = iIntValue;
        b();
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (f1885a && (scheduledExecutorServiceNewScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            f1888d.put((ScheduledThreadPoolExecutor) scheduledExecutorServiceNewScheduledThreadPool, scheduledExecutorServiceNewScheduledThreadPool);
        }
        return scheduledExecutorServiceNewScheduledThreadPool;
    }

    public static void b() {
        if (!f1885a) {
            return;
        }
        while (true) {
            AtomicReference<ScheduledExecutorService> atomicReference = f1887c;
            ScheduledExecutorService scheduledExecutorService = atomicReference.get();
            if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
                return;
            }
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new f("RxSchedulerPurge"));
            if (atomicReference.compareAndSet(scheduledExecutorService, scheduledExecutorServiceNewScheduledThreadPool)) {
                a aVar = new a();
                int i = f1886b;
                scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(aVar, i, i, TimeUnit.SECONDS);
                return;
            }
            scheduledExecutorServiceNewScheduledThreadPool.shutdownNow();
        }
    }
}
