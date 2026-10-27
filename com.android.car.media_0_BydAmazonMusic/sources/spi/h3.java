package spi;

import androidx.annotation.IntRange;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DiExecutors.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h3 {
    public static volatile h3 b;
    public static final int c;
    public static final int d;
    public static final a e;
    public ThreadPoolExecutor a;

    /* JADX INFO: compiled from: DiExecutors.java */
    public static class a implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Executors.newCachedThreadPool().execute(runnable);
        }
    }

    /* JADX INFO: compiled from: DiExecutors.java */
    public static class b implements ThreadFactory {
        public static final AtomicInteger d = new AtomicInteger(1);
        public final ThreadGroup a;
        public final AtomicInteger b = new AtomicInteger(1);
        public final String c;

        public b(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.a = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            StringBuilder sbN = spi.c.n(str, "-");
            sbN.append(d.getAndIncrement());
            this.c = sbN.toString();
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.a, runnable, this.c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    /* JADX INFO: compiled from: DiExecutors.java */
    public static class c implements Runnable {
        public final Runnable a;
        public final int b;

        public c(@IntRange(from = 1, to = 10) int i, Runnable runnable) {
            this.a = runnable;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Thread.currentThread().setPriority(this.b);
            this.a.run();
        }
    }

    static {
        int iMax = Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 5));
        c = iMax;
        d = iMax;
        e = new a();
    }

    private h3() {
        int i = c;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, d, 5L, TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("CPU thread"), e);
        this.a = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        Executors.newFixedThreadPool(i * 2, new b("IO thread"));
    }

    public static h3 a() {
        if (b == null) {
            synchronized (h3.class) {
                if (b == null) {
                    b = new h3();
                }
            }
        }
        return b;
    }
}
