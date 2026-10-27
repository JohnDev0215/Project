package p2;

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
    public static final int f7000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f7001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Runnable, c> f7002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ScheduledExecutorService f7003d;

    /* JADX INFO: compiled from: BizExecutor.java */
    public class a extends f {
        public a() {
        }

        @Override // p2.f
        public int a() {
            return 19;
        }
    }

    /* JADX INFO: renamed from: p2.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BizExecutor.java */
    public class RunnableC0103b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f7005d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ long f7006e;

        public RunnableC0103b(String str, long j8) {
            this.f7005d = str;
            this.f7006e = j8;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.a("BizExecutor", "CheckExec -> < %s > Task may cause too much time, please review the code!!! TIME_PAST: %s", this.f7005d, Long.valueOf(SystemClock.uptimeMillis() - this.f7006e));
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Future<?> f7008a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f7009b;

        public c(Future<?> future, long j8) {
            this.f7008a = future;
            this.f7009b = j8;
        }
    }

    /* JADX INFO: compiled from: BizExecutor.java */
    public static class d<T> extends FutureTask<T> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final String f7010d;

        public d(Runnable runnable, T t7, String str) {
            super(runnable, t7);
            this.f7010d = str;
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f7000a = iAvailableProcessors;
        f7001b = Math.max(1, Math.min(iAvailableProcessors - 2, 8));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public b(BlockingQueue<Runnable> blockingQueue) {
        int i8 = f7001b;
        super(i8, i8, 10L, TimeUnit.SECONDS, blockingQueue, new p2.a());
        this.f7002c = new WeakHashMap();
        this.f7003d = Executors.newSingleThreadScheduledExecutor(new a());
        e.b("BizExecutor", "BizExecutor -> [ CPU_COUNT: %d, POOL_SIZE: %d, KEEP_ALIVE_SECONDS: %d ]", Integer.valueOf(f7000a), Integer.valueOf(i8), 10);
    }

    public static String a(Runnable runnable) {
        return runnable instanceof d ? ((d) runnable).f7010d : String.valueOf(runnable);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        String strA = a(runnable);
        c cVarRemove = this.f7002c.remove(runnable);
        if (cVarRemove != null) {
            e.b("BizExecutor", "afterExecute( runnable: %s, throwable: %s ) time: %s ms", strA, th, Long.valueOf(SystemClock.uptimeMillis() - cVarRemove.f7009b));
            cVarRemove.f7008a.cancel(true);
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
        this.f7002c.put(runnable, new c(this.f7003d.scheduleWithFixedDelay(new RunnableC0103b(strA, jUptimeMillis), 10L, 10L, TimeUnit.SECONDS), jUptimeMillis));
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t7) {
        return new d(runnable, t7, String.valueOf(runnable));
    }
}
