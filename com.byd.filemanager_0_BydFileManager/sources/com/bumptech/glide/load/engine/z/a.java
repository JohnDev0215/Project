package com.bumptech.glide.load.engine.z;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: GlideExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ExecutorService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final long f3156a = TimeUnit.SECONDS.toMillis(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile int f3157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ExecutorService f3158c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GlideExecutor.java */
    public static final class C0066a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f3159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3161c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c f3162d = c.f3172d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f3163e;
        private long f;

        C0066a(boolean z) {
            this.f3159a = z;
        }

        public a a() {
            if (TextUtils.isEmpty(this.f3163e)) {
                throw new IllegalArgumentException("Name must be non-null and non-empty, but given: " + this.f3163e);
            }
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.f3160b, this.f3161c, this.f, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new b(this.f3163e, this.f3162d, this.f3159a));
            if (this.f != 0) {
                threadPoolExecutor.allowCoreThreadTimeOut(true);
            }
            return new a(threadPoolExecutor);
        }

        public C0066a b(String str) {
            this.f3163e = str;
            return this;
        }

        public C0066a c(int i) {
            this.f3160b = i;
            this.f3161c = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: GlideExecutor.java */
    private static final class b implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f3164b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c f3165c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f3166d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3167e;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0067a extends Thread {
            C0067a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(9);
                if (b.this.f3166d) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    b.this.f3165c.a(th);
                }
            }
        }

        b(String str, c cVar, boolean z) {
            this.f3164b = str;
            this.f3165c = cVar;
            this.f3166d = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            C0067a c0067a;
            c0067a = new C0067a(runnable, "glide-" + this.f3164b + "-thread-" + this.f3167e);
            this.f3167e = this.f3167e + 1;
            return c0067a;
        }
    }

    /* JADX INFO: compiled from: GlideExecutor.java */
    public interface c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f3169a = new C0068a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f3170b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final c f3171c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final c f3172d;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0068a implements c {
            C0068a() {
            }

            @Override // com.bumptech.glide.load.engine.z.a.c
            public void a(Throwable th) {
            }
        }

        /* JADX INFO: compiled from: GlideExecutor.java */
        class b implements c {
            b() {
            }

            @Override // com.bumptech.glide.load.engine.z.a.c
            public void a(Throwable th) {
                if (th == null || !Log.isLoggable("GlideExecutor", 6)) {
                    return;
                }
                Log.e("GlideExecutor", "Request threw uncaught throwable", th);
            }
        }

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0069c implements c {
            C0069c() {
            }

            @Override // com.bumptech.glide.load.engine.z.a.c
            public void a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        }

        static {
            b bVar = new b();
            f3170b = bVar;
            f3171c = new C0069c();
            f3172d = bVar;
        }

        void a(Throwable th);
    }

    a(ExecutorService executorService) {
        this.f3158c = executorService;
    }

    public static int a() {
        if (f3157b == 0) {
            f3157b = Math.min(4, com.bumptech.glide.load.engine.z.b.a());
        }
        return f3157b;
    }

    public static C0066a b() {
        return new C0066a(true).c(a() >= 4 ? 2 : 1).b("animation");
    }

    public static a c() {
        return b().a();
    }

    public static C0066a d() {
        return new C0066a(true).c(1).b("disk-cache");
    }

    public static a e() {
        return d().a();
    }

    public static C0066a f() {
        return new C0066a(false).c(a()).b(BYDAutoMultimediaDevice.PARAM_SOURCE);
    }

    public static a g() {
        return f().a();
    }

    public static a h() {
        return new a(new ThreadPoolExecutor(0, Integer.MAX_VALUE, f3156a, TimeUnit.MILLISECONDS, new SynchronousQueue(), new b("source-unlimited", c.f3172d, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f3158c.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f3158c.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.f3158c.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.f3158c.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.f3158c.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.f3158c.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.f3158c.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.f3158c.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.f3158c.submit(runnable);
    }

    public String toString() {
        return this.f3158c.toString();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f3158c.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.f3158c.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.f3158c.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.f3158c.submit(callable);
    }
}
