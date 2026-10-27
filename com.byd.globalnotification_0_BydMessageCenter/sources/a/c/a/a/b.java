package a.c.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f420a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExecutorService f421b = Executors.newFixedThreadPool(4, new a(this));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Handler f422c;

    public class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f423a = new AtomicInteger(0);

        public a(b bVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f423a.getAndIncrement())));
            return thread;
        }
    }

    @Override // a.c.a.a.c
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // a.c.a.a.c
    public void b(Runnable runnable) {
        if (this.f422c == null) {
            synchronized (this.f420a) {
                if (this.f422c == null) {
                    this.f422c = Handler.createAsync(Looper.getMainLooper());
                }
            }
        }
        this.f422c.post(runnable);
    }
}
