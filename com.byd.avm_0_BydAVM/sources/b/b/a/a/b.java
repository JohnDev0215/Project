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
    private final Object f1500a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f1501b = Executors.newFixedThreadPool(2, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f1502c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f1503a = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f1503a.getAndIncrement())));
            return thread;
        }
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f1501b.execute(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        if (this.f1502c == null) {
            synchronized (this.f1500a) {
                if (this.f1502c == null) {
                    this.f1502c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f1502c.post(runnable);
    }
}
