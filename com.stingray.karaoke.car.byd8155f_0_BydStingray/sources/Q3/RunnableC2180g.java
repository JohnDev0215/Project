package q3;

import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: q3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC2180g implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f21246m;

    public /* synthetic */ RunnableC2180g(Context context, int i7) {
        this.l = i7;
        this.f21246m = context;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        switch (this.l) {
            case 0:
                new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new RunnableC2180g(this.f21246m, 1));
                break;
            default:
                AbstractC2178e.t(this.f21246m, new ExecutorC2176c(0), AbstractC2178e.f21237a, false);
                break;
        }
    }
}
