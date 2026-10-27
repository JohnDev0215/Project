package t1;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Process;
import android.os.StrictMode;
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

/* JADX INFO: compiled from: GlideExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ExecutorService {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final long f9542g = TimeUnit.SECONDS.toMillis(10);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static volatile int f9543h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ExecutorService f9544e;

    /* JADX INFO: renamed from: t1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GlideExecutor.java */
    private static final class ThreadFactoryC0154a implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f9545e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final b f9546g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final boolean f9547h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f9548i;

        /* JADX INFO: renamed from: t1.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0155a extends Thread {
            C0155a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(9);
                if (ThreadFactoryC0154a.this.f9547h) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    super.run();
                } catch (Throwable th) {
                    ThreadFactoryC0154a.this.f9546g.a(th);
                }
            }
        }

        ThreadFactoryC0154a(String str, b bVar, boolean z6) {
            this.f9545e = str;
            this.f9546g = bVar;
            this.f9547h = z6;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            C0155a c0155a;
            c0155a = new C0155a(runnable, "glide-" + this.f9545e + "-thread-" + this.f9548i);
            this.f9548i = this.f9548i + 1;
            return c0155a;
        }
    }

    /* JADX INFO: compiled from: GlideExecutor.java */
    public interface b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9550a = new C0156a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f9551b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final b f9552c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final b f9553d;

        /* JADX INFO: renamed from: t1.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0156a implements b {
            C0156a() {
            }

            @Override // t1.a.b
            public void a(Throwable th) {
            }
        }

        /* JADX INFO: renamed from: t1.a$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GlideExecutor.java */
        class C0157b implements b {
            C0157b() {
            }

            @Override // t1.a.b
            public void a(Throwable th) {
                if (th == null || !Log.isLoggable("GlideExecutor", 6)) {
                    return;
                }
                Log.e("GlideExecutor", "Request threw uncaught throwable", th);
            }
        }

        /* JADX INFO: compiled from: GlideExecutor.java */
        class c implements b {
            c() {
            }

            @Override // t1.a.b
            public void a(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        }

        static {
            C0157b c0157b = new C0157b();
            f9551b = c0157b;
            f9552c = new c();
            f9553d = c0157b;
        }

        void a(Throwable th);
    }

    a(ExecutorService executorService) {
        this.f9544e = executorService;
    }

    public static int a() {
        if (f9543h == 0) {
            f9543h = Math.min(4, t1.b.a());
        }
        return f9543h;
    }

    public static a b() {
        return c(a() >= 4 ? 2 : 1, b.f9553d);
    }

    public static a c(int i7, b bVar) {
        return new a(new ThreadPoolExecutor(i7, i7, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new ThreadFactoryC0154a("animation", bVar, true)));
    }

    public static a d() {
        return e(1, "disk-cache", b.f9553d);
    }

    public static a e(int i7, String str, b bVar) {
        return new a(new ThreadPoolExecutor(i7, i7, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new ThreadFactoryC0154a(str, bVar, true)));
    }

    public static a f() {
        return g(a(), BYDAutoMultimediaDevice.PARAM_SOURCE, b.f9553d);
    }

    public static a g(int i7, String str, b bVar) {
        return new a(new ThreadPoolExecutor(i7, i7, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new ThreadFactoryC0154a(str, bVar, false)));
    }

    public static a h() {
        return new a(new ThreadPoolExecutor(0, Integer.MAX_VALUE, f9542g, TimeUnit.MILLISECONDS, new SynchronousQueue(), new ThreadFactoryC0154a("source-unlimited", b.f9553d, false)));
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j7, TimeUnit timeUnit) {
        return this.f9544e.awaitTermination(j7, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f9544e.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.f9544e.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.f9544e.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.f9544e.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.f9544e.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.f9544e.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.f9544e.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.f9544e.submit(runnable);
    }

    public String toString() {
        return this.f9544e.toString();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j7, TimeUnit timeUnit) {
        return this.f9544e.invokeAll(collection, j7, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j7, TimeUnit timeUnit) {
        return (T) this.f9544e.invokeAny(collection, j7, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t6) {
        return this.f9544e.submit(runnable, t6);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.f9544e.submit(callable);
    }
}
