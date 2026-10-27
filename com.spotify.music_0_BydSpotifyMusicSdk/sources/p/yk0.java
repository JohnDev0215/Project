package p;

import android.util.Log;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class yk0 extends zk {
    public final /* synthetic */ String c;
    public final /* synthetic */ ExecutorService e;
    public final /* synthetic */ long k = 2;
    public final /* synthetic */ TimeUnit l;

    public yk0(String str, ExecutorService executorService, TimeUnit timeUnit) {
        this.c = str;
        this.e = executorService;
        this.l = timeUnit;
    }

    @Override // p.zk
    public final void a() {
        ExecutorService executorService = this.e;
        try {
            Log.isLoggable("FirebaseCrashlytics", 3);
            executorService.shutdown();
            if (executorService.awaitTermination(this.k, this.l)) {
                return;
            }
            Log.isLoggable("FirebaseCrashlytics", 3);
            executorService.shutdownNow();
        } catch (InterruptedException unused) {
            String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", this.c);
            Log.isLoggable("FirebaseCrashlytics", 3);
            executorService.shutdownNow();
        }
    }
}
