package p;

import android.content.Context;
import androidx.work.WorkerParameters;

/* JADX INFO: loaded from: classes.dex */
public abstract class qb1 {
    public final Context c;
    public final WorkerParameters e;
    public volatile int k = -256;
    public boolean l;

    public qb1(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if (workerParameters == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.c = context;
        this.e = workerParameters;
    }

    public lb1 a() {
        androidx.work.impl.utils.futures.b bVar = new androidx.work.impl.utils.futures.b();
        bVar.k(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return bVar;
    }

    public void b() {
    }

    public abstract androidx.work.impl.utils.futures.b d();

    public final void e(int i) {
        this.k = i;
        b();
    }
}
