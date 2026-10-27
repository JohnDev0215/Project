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
    private static final long f2903a = TimeUnit.SECONDS.toMillis(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile int f2904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ExecutorService f2905c;

    /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GlideExecutor.java */
    public static final class C0090a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f2906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2907b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c f2909d = c.f2917d;
        private String e;
        private long f;

        C0090a(boolean z) {
            this.f2906a = z;
        }

        public a a() {
            if (TextUtils.isEmpty(this.e)) {
                throw new IllegalArgumentException("Name must be non-null and non-empty, but given: " + this.e);
            }
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.f2907b, this.f2908c, this.f, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new b(this.e, this.f2909d, this.f2906a));
            if (this.f != 0) {
                threadPoolExecutor.allowCoreThreadTimeOut(true);
            }
            return new a(threadPoolExecutor);
        }

        public C0090a b(String str) {
            this.e = str;
            return this;
        }

        public C0090a c(int i) {
            this.f2907b = i;
            this.f2908c = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: GlideExecutor.java */
    private static final class b implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f2910b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c f2911c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2912d;
        private int e;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0091a extends Thread {
            C0091a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(9);
                if (b.this.f2912d) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    b.this.f2911c.a(th);
                }
            }
        }

        b(String str, c cVar, boolean z) {
            this.f2910b = str;
            this.f2911c = cVar;
            this.f2912d = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            C0091a c0091a;
            c0091a = new C0091a(runnable, "glide-" + this.f2910b + "-thread-" + this.e);
            this.e = this.e + 1;
            return c0091a;
        }
    }

    /* JADX INFO: compiled from: GlideExecutor.java */
    public interface c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2914a = new C0092a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final c f2915b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final c f2916c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final c f2917d;

        /* JADX INFO: renamed from: com.bumptech.glide.load.engine.z.a$c$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0092a implements c {
            C0092a() {
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
        class C0093c implements c {
            C0093c() {
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
            f2915b = bVar;
            f2916c = new C0093c();
            f2917d = bVar;
        }

        void a(Throwable th);
    }

    a(ExecutorService executorService) {
        this.f2905c = executorService;
    }

    public static int a() {
        if (f2904b == 0) {
            f2904b = Math.min(4, com.bumptech.glide.load.engine.z.b.a());
        }
        return f2904b;
    }

    public static C0090a b() {
        return new C0090a(true).c(a() >= 4 ? 2 : 1).b("animation");
    }

    public static a c() {
        return b().a();
    }

    public static C0090a d() {
        return new C0090a(true).c(1).b("disk-cache");
    }

    public static a e() {
        return d().a();
    }

    public static C0090a f() {
        return new C0090a(false).c(a()).b(BYDAutoMultimediaDevice.PARAM_SOURCE);
    }

    public static a g() {
        return f().a();
    }

    public static a h() {
        return new a(new ThreadPoolExecutor(0, Integer.MAX_VALUE, f2903a, TimeUnit.MILLISECONDS, new SynchronousQueue(), new b("source-unlimited", c.f2917d, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f2905c.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f2905c.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.f2905c.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.f2905c.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.f2905c.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.f2905c.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.f2905c.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.f2905c.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.f2905c.submit(runnable);
    }

    public String toString() {
        return this.f2905c.toString();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.f2905c.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.f2905c.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.f2905c.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.f2905c.submit(callable);
    }
}
