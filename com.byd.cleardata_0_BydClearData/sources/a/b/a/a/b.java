package a.b.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f101a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f102b = Executors.newFixedThreadPool(2, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f103c;

    class a implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final AtomicInteger f104b = new AtomicInteger(0);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f104b.getAndIncrement())));
            return thread;
        }
    }

    @Override // a.b.a.a.c
    public void a(Runnable runnable) {
        this.f102b.execute(runnable);
    }

    @Override // a.b.a.a.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // a.b.a.a.c
    public void c(Runnable runnable) {
        if (this.f103c == null) {
            synchronized (this.f101a) {
                if (this.f103c == null) {
                    this.f103c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f103c.post(runnable);
    }
}
