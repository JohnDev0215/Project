package spi;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.byd.car.locale.ccTLD;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: AppExecutors.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static n b = new n();
    public final b a;

    /* JADX INFO: compiled from: AppExecutors.java */
    public static class b implements Executor {
        public Handler a;

        private b() {
            this.a = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public final void execute(@NonNull Runnable runnable) {
            this.a.post(runnable);
        }
    }

    /* JADX INFO: compiled from: AppExecutors.java */
    public class c implements ThreadFactory {
        public final String a;
        public long b = 0;

        public c(n nVar, String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull Runnable runnable) {
            this.b++;
            return new Thread(runnable, this.a + "-" + this.b + "-Thread");
        }
    }

    public n() {
        Executors.newSingleThreadExecutor(new c(this, "single"));
        Executors.newFixedThreadPool(3, new c(this, "fixed"));
        this.a = new b();
        new ScheduledThreadPoolExecutor(3, new c(this, ccTLD.SEYCHELLES), new ThreadPoolExecutor.AbortPolicy());
    }
}
