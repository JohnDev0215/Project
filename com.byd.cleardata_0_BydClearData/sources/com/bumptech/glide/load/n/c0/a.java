package com.bumptech.glide.load.n.c0;

import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ExecutorService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final long f2092a = TimeUnit.SECONDS.toMillis(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile int f2093b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ExecutorService f2094c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.n.c0.a$a, reason: collision with other inner class name */
    public static final class C0068a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f2095a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2096b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2097c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c f2098d = c.f2106d;
        private String e;
        private long f;

        C0068a(boolean z) {
            this.f2095a = z;
        }

        public a a() {
            if (TextUtils.isEmpty(this.e)) {
                throw new IllegalArgumentException("Name must be non-null and non-empty, but given: " + this.e);
            }
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.f2096b, this.f2097c, this.f, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new b(this.e, this.f2098d, this.f2095a));
            if (this.f != 0) {
                threadPoolExecutor.allowCoreThreadTimeOut(true);
            }
            return new a(threadPoolExecutor);
        }

        public C0068a b(String str) {
            this.e = str;
            return this;
        }

        public C0068a c(int i) {
            this.f2096b = i;
            this.f2097c = i;
            return this;
        }
    }

    private static final class b implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f2099b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c f2100c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2101d;
        private int e;

        /* JADX INFO: renamed from: com.bumptech.glide.load.n.c0.a$b$a, reason: collision with other inner class name */
        class C0069a extends Thread {
            C0069a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(9);
                if (b.this.f2101d) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    b.this.f2100c.a(th);
                }
            }
        }

        b(String str, c cVar, boolean z) {
            this.f2099b = str;
            this.f2100c = cVar;
            this.f2101d = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            C0069a c0069a;
            c0069a = new C0069a(runnable, "glide-" + this.f2099b + "-thread-" + this.e);
            this.e = this.e + 1;
            return c0069a;
        }
    }

    public interface c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2103a = new C0070a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f2104b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final c f2105c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final c f2106d;

        /* JADX INFO: renamed from: com.bumptech.glide.load.n.c0.a$c$a, reason: collision with other inner class name */
        class C0070a implements c {
            C0070a() {
            }

            @Override // com.bumptech.glide.load.n.c0.a.c
            public void a(Throwable th) {
            }
        }

        class b implements c {
            b() {
            }

            @Override // com.bumptech.glide.load.n.c0.a.c
            public void a(Throwable th) {
                if (th == null || !Log.isLoggable("GlideExecutor", 6)) {
                    return;
                }
                Log.e("GlideExecutor", "Request threw uncaught throwable", th);
            }
        }

        /* JADX INFO: renamed from: com.bumptech.glide.load.n.c0.a$c$c, reason: collision with other inner class name */
        class C0071c implements c {
            C0071c() {
            }

            @Override // com.bumptech.glide.load.n.c0.a.c
            public void a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        }

        static {
            b bVar = new b();
            f2104b = bVar;
            f2105c = new C0071c();
            f2106d = bVar;
        }

        void a(Throwable th);
    }

    a(ExecutorService executorService) {
        this.f2094c = executorService;
    }

    public static int a() {
        if (f2093b == 0) {
            f2093b = Math.min(4, com.bumptech.glide.load.n.c0.b.a());
        }
        return f2093b;
    }

    public static C0068a b() {
        return new C0068a(true).c(a() >= 4 ? 2 : 1).b("animation");
    }

    public static a c() {
        return b().a();
    }

    public static C0068a d() {
        return new C0068a(true).c(1).b("disk-cache");
    }

    public static a e() {
        return d().a();
    }

    public static C0068a f() {
        return new C0068a(false).c(a()).b("source");
    }

    public static a g() {
        return f().a();
    }

    public static a h() {
        return new a(new ThreadPoolExecutor(0, Integer.MAX_VALUE, f2092a, TimeUnit.MILLISECONDS, new SynchronousQueue(), new b("source-unlimited", c.f2106d, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.f2094c.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f2094c.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.f2094c.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.f2094c.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.f2094c.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return (T) this.f2094c.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.f2094c.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.f2094c.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.f2094c.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.f2094c.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.f2094c.submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.f2094c.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.f2094c.submit(callable);
    }

    public String toString() {
        return this.f2094c.toString();
    }
}
