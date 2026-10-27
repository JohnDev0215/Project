package e0;

import android.os.Handler;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: RequestExecutor.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: compiled from: RequestExecutor.java */
    private static class a implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f6035e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f6036g;

        /* JADX INFO: renamed from: e0.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: RequestExecutor.java */
        private static class C0067a extends Thread {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private final int f6037e;

            C0067a(Runnable runnable, String str, int i7) {
                super(runnable, str);
                this.f6037e = i7;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.f6037e);
                super.run();
            }
        }

        a(String str, int i7) {
            this.f6035e = str;
            this.f6036g = i7;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new C0067a(runnable, this.f6035e, this.f6036g);
        }
    }

    /* JADX INFO: compiled from: RequestExecutor.java */
    private static class b<T> implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Callable<T> f6038e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private h0.a<T> f6039g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Handler f6040h;

        /* JADX INFO: compiled from: RequestExecutor.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ h0.a f6041e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ Object f6042g;

            a(h0.a aVar, Object obj) {
                this.f6041e = aVar;
                this.f6042g = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                this.f6041e.accept(this.f6042g);
            }
        }

        b(Handler handler, Callable<T> callable, h0.a<T> aVar) {
            this.f6038e = callable;
            this.f6039g = aVar;
            this.f6040h = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            T tCall;
            try {
                tCall = this.f6038e.call();
            } catch (Exception unused) {
                tCall = null;
            }
            this.f6040h.post(new a(this.f6039g, tCall));
        }
    }

    static ThreadPoolExecutor a(String str, int i7, int i8) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i8, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new a(str, i7));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static <T> void b(Executor executor, Callable<T> callable, h0.a<T> aVar) {
        executor.execute(new b(e0.b.a(), callable, aVar));
    }

    static <T> T c(ExecutorService executorService, Callable<T> callable, int i7) throws InterruptedException {
        try {
            return executorService.submit(callable).get(i7, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e7) {
            throw e7;
        } catch (ExecutionException e8) {
            throw new RuntimeException(e8);
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }
}
