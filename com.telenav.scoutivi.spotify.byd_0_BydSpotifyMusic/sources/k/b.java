package k;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DefaultTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f7185a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f7186b = Executors.newFixedThreadPool(4, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f7187c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    class a implements ThreadFactory {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final AtomicInteger f7188e = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f7188e.getAndIncrement())));
            return thread;
        }
    }

    private static Handler d(Looper looper) {
        return Handler.createAsync(looper);
    }

    @Override // k.c
    public void a(Runnable runnable) {
        this.f7186b.execute(runnable);
    }

    @Override // k.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // k.c
    public void c(Runnable runnable) {
        if (this.f7187c == null) {
            synchronized (this.f7185a) {
                if (this.f7187c == null) {
                    this.f7187c = d(Looper.getMainLooper());
                }
            }
        }
        this.f7187c.post(runnable);
    }
}
