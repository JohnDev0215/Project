package spi;

import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ld implements ExecutorService {
    public static final long b = TimeUnit.SECONDS.toMillis(10);
    public static volatile int c;
    public final ExecutorService a;

    public static final class b {
        public final boolean a;
        public int b;
        public int c;

        @NonNull
        public final c d = new c();

        @NonNull
        public e.b e = e.b;
        public String f;

        public b(boolean z) {
            this.a = z;
        }

        public final ld a() {
            if (!TextUtils.isEmpty(this.f)) {
                return new ld(new ThreadPoolExecutor(this.b, this.c, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new d(this.d, this.f, this.e, this.a)));
            }
            StringBuilder sbK = spi.e.k("Name must be non-null and non-empty, but given: ");
            sbK.append(this.f);
            throw new IllegalArgumentException(sbK.toString());
        }
    }

    public static final class c implements ThreadFactory {

        public class a extends Thread {
            public a(c cVar, Runnable runnable) {
                super(runnable);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                Process.setThreadPriority(9);
                super.run();
            }
        }

        private c() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull Runnable runnable) {
            return new a(this, runnable);
        }
    }

    public static final class d implements ThreadFactory {
        public final ThreadFactory a;
        public final String b;
        public final e c;
        public final boolean d;
        public final AtomicInteger e = new AtomicInteger();

        public class a implements Runnable {
            public final /* synthetic */ Runnable a;

            public a(Runnable runnable) {
                this.a = runnable;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (d.this.d) {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                }
                try {
                    this.a.run();
                } catch (Throwable th) {
                    d.this.c.a(th);
                }
            }
        }

        public d(ThreadFactory threadFactory, String str, e eVar, boolean z) {
            this.a = threadFactory;
            this.b = str;
            this.c = eVar;
            this.d = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull Runnable runnable) {
            Thread threadNewThread = this.a.newThread(new a(runnable));
            StringBuilder sbK = spi.e.k("glide-");
            sbK.append(this.b);
            sbK.append("-thread-");
            sbK.append(this.e.getAndIncrement());
            threadNewThread.setName(sbK.toString());
            return threadNewThread;
        }
    }

    public interface e {
        public static final b a;
        public static final b b;

        public class a implements e {
            @Override // spi.ld.e
            public final void a(Throwable th) {
            }
        }

        public class b implements e {
            @Override // spi.ld.e
            public final void a(Throwable th) {
                if (Log.isLoggable("GlideExecutor", 6)) {
                    Log.e("GlideExecutor", "Request threw uncaught throwable", th);
                }
            }
        }

        public class c implements e {
            @Override // spi.ld.e
            public final void a(Throwable th) {
                throw new RuntimeException("Request threw uncaught throwable", th);
            }
        }

        static {
            new a();
            b bVar = new b();
            a = bVar;
            new c();
            b = bVar;
        }

        void a(Throwable th);
    }

    @VisibleForTesting
    public ld(ExecutorService executorService) {
        this.a = executorService;
    }

    public static int a() {
        if (c == 0) {
            c = Math.min(4, Runtime.getRuntime().availableProcessors());
        }
        return c;
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.a.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(@NonNull Runnable runnable) {
        this.a.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.a.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> collection, long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        return this.a.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.a.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> T invokeAny(@NonNull Collection<? extends Callable<T>> collection, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.a.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return this.a.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return this.a.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        this.a.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final List<Runnable> shutdownNow() {
        return this.a.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final Future<?> submit(@NonNull Runnable runnable) {
        return this.a.submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public final <T> Future<T> submit(@NonNull Runnable runnable, T t) {
        return this.a.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> Future<T> submit(@NonNull Callable<T> callable) {
        return this.a.submit(callable);
    }

    public final String toString() {
        return this.a.toString();
    }
}
