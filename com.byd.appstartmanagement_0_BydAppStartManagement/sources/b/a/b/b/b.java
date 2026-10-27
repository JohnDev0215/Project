package b.a.b.b;

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
    public static final int f156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Runnable, c> f158c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ScheduledExecutorService f159d;

    /* JADX INFO: compiled from: BizExecutor.java */
    public class a extends f {
        public a() {
        }

        @Override // b.a.b.b.f
        public int a() {
            return 19;
        }
    }

    /* JADX INFO: renamed from: b.a.b.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BizExecutor.java */
    public class RunnableC0017b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f161b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f162c;

        public RunnableC0017b(String str, long j) {
            this.f161b = str;
            this.f162c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.a("BizExecutor", "CheckExec -> < %s > Task may cause too much time, please review the code!!! TIME_PAST: %s", this.f161b, Long.valueOf(SystemClock.uptimeMillis() - this.f162c));
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Future<?> f164a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f165b;

        public c(Future<?> future, long j) {
            this.f164a = future;
            this.f165b = j;
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class d<T> extends FutureTask<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f166b;

        public d(Runnable runnable, T t, String str) {
            super(runnable, t);
            this.f166b = str;
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f156a = iAvailableProcessors;
        f157b = Math.max(1, Math.min(iAvailableProcessors - 2, 8));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(BlockingQueue<Runnable> blockingQueue) {
        int i = f157b;
        super(i, i, 10L, TimeUnit.SECONDS, blockingQueue, new b.a.b.b.a());
        this.f158c = new WeakHashMap();
        this.f159d = Executors.newSingleThreadScheduledExecutor(new a());
        e.b("BizExecutor", "BizExecutor -> [ CPU_COUNT: %d, POOL_SIZE: %d, KEEP_ALIVE_SECONDS: %d ]", Integer.valueOf(f156a), Integer.valueOf(i), 10);
    }

    public static String a(Runnable runnable) {
        return runnable instanceof d ? ((d) runnable).f166b : String.valueOf(runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        String strA = a(runnable);
        c cVarRemove = this.f158c.remove(runnable);
        if (cVarRemove != null) {
            e.b("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) time: %s ms", strA, th, Long.valueOf(SystemClock.uptimeMillis() - cVarRemove.f165b));
            cVarRemove.f164a.cancel(true);
        } else {
            e.b("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) ", strA, th);
        }
        if (th != null) {
            e.a("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) Exception happened !!! Msg: %s", strA, th, th.getMessage());
            th.printStackTrace();
        }
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void beforeExecute(Thread thread, Runnable runnable) {
        String strA = a(runnable);
        e.b("BizExecutor", "beforeExecute( runnable: %s ) ", strA);
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.f158c.put(runnable, new c(this.f159d.scheduleWithFixedDelay(new RunnableC0017b(strA, jUptimeMillis), 10L, 10L, TimeUnit.SECONDS), jUptimeMillis));
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new d(runnable, t, String.valueOf(runnable));
    }
}
