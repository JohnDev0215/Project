package b.b.a.f;

import android.os.SystemClock;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: BizExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends ThreadPoolExecutor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Runnable, c> f494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ScheduledExecutorService f495d;

    /* JADX INFO: compiled from: BizExecutor.java */
    public class a extends g {
        public a() {
        }

        @Override // b.b.a.f.g
        public int a() {
            return 19;
        }
    }

    /* JADX INFO: renamed from: b.b.a.f.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BizExecutor.java */
    public class RunnableC0020b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f497a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f498b;

        public RunnableC0020b(String str, long j) {
            this.f497a = str;
            this.f498b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.a("BizExecutor", "CheckExec -> < %s > Task may cause too much time, please review the code!!! TIME_PAST: %s", this.f497a, Long.valueOf(SystemClock.uptimeMillis() - this.f498b));
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Future<?> f500a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f501b;

        public c(Future<?> future, long j) {
            this.f500a = future;
            this.f501b = j;
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class d<T> extends FutureTask<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f502a;

        public d(Runnable runnable, T t, String str) {
            super(runnable, t);
            this.f502a = str;
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f492a = iAvailableProcessors;
        f493b = Math.max(1, Math.min(iAvailableProcessors - 2, 8));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(BlockingQueue<Runnable> blockingQueue) {
        int i = f493b;
        super(i, i, 10L, TimeUnit.SECONDS, blockingQueue, new b.b.a.f.a());
        this.f494c = new WeakHashMap();
        this.f495d = Executors.newSingleThreadScheduledExecutor(new a());
        f.b("BizExecutor", "BizExecutor -> [ CPU_COUNT: %d, POOL_SIZE: %d, KEEP_ALIVE_SECONDS: %d ]", Integer.valueOf(f492a), Integer.valueOf(i), 10);
    }

    public static String a(Runnable runnable) {
        return runnable instanceof d ? ((d) runnable).f502a : String.valueOf(runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        String strA = a(runnable);
        c cVarRemove = this.f494c.remove(runnable);
        if (cVarRemove != null) {
            f.b("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) time: %s ms", strA, th, Long.valueOf(SystemClock.uptimeMillis() - cVarRemove.f501b));
            cVarRemove.f500a.cancel(true);
        } else {
            f.b("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) ", strA, th);
        }
        if (th != null) {
            f.a("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) Exception happened !!! Msg: %s", strA, th, th.getMessage());
            th.printStackTrace();
        }
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void beforeExecute(Thread thread, Runnable runnable) {
        String strA = a(runnable);
        f.b("BizExecutor", "beforeExecute( runnable: %s ) ", strA);
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.f494c.put(runnable, new c(this.f495d.scheduleWithFixedDelay(new RunnableC0020b(strA, jUptimeMillis), 10L, 10L, TimeUnit.SECONDS), jUptimeMillis));
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new d(runnable, t, String.valueOf(runnable));
    }
}
