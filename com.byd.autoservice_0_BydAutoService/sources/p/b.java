package p;

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
    public final Object f4464a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExecutorService f4465b = Executors.newFixedThreadPool(2, new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Handler f4466c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f4467a = new AtomicInteger(0);

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f4467a.getAndIncrement())));
            return thread;
        }
    }

    @Override // p.c
    public void a(Runnable runnable) {
        this.f4465b.execute(runnable);
    }

    @Override // p.c
    public boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // p.c
    public void c(Runnable runnable) {
        if (this.f4466c == null) {
            synchronized (this.f4464a) {
                if (this.f4466c == null) {
                    this.f4466c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f4466c.post(runnable);
    }
}
