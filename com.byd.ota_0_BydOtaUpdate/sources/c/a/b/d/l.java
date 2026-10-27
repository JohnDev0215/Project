package c.a.b.d;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: TaskExecuteUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static l f2342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ExecutorService f2344c = Executors.newFixedThreadPool(2);

    public static l d() {
        if (f2342a == null) {
            synchronized (l.class) {
                if (f2342a == null) {
                    f2342a = new l();
                }
            }
        }
        return f2342a;
    }

    public void a(Runnable runnable) {
        this.f2344c.execute(runnable);
    }

    public void b(Runnable runnable) {
        c(runnable, 0L);
    }

    public void c(Runnable runnable, long j) {
        if (j < 0) {
            j = 0;
        }
        if (0 == j) {
            h(runnable);
        } else {
            g(runnable, j);
        }
    }

    public Handler e() {
        if (this.f2343b == null) {
            synchronized (l.class) {
                if (this.f2343b == null) {
                    this.f2343b = new Handler(Looper.getMainLooper());
                }
            }
        }
        return this.f2343b;
    }

    public boolean f(Runnable runnable) {
        return e().hasCallbacks(runnable);
    }

    public void g(Runnable runnable, long j) {
        e().postDelayed(runnable, j);
    }

    public void h(Runnable runnable) {
        e().post(runnable);
    }

    public void i(Runnable runnable) {
        if (Build.VERSION.SDK_INT < 29 || !e().hasCallbacks(runnable)) {
            return;
        }
        e().removeCallbacks(runnable);
    }
}
