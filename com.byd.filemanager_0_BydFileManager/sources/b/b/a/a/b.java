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
    private final Object f1701a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f1702b = Executors.newFixedThreadPool(2, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f1703c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    class a implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final AtomicInteger f1704b = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f1704b.getAndIncrement())));
            return thread;
        }
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f1702b.execute(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        if (this.f1703c == null) {
            synchronized (this.f1701a) {
                if (this.f1703c == null) {
                    this.f1703c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f1703c.post(runnable);
    }
}
