package b.b.a.a;

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
    private final Object f2219a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f2220b = Executors.newFixedThreadPool(2, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f2221c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    class a implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final AtomicInteger f2222b = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f2222b.getAndIncrement())));
            return thread;
        }
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f2220b.execute(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        if (this.f2221c == null) {
            synchronized (this.f2219a) {
                if (this.f2221c == null) {
                    this.f2221c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f2221c.post(runnable);
    }
}
