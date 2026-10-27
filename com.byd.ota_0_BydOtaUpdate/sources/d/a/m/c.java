package d.a.m;

import io.reactivex.disposables.FutureDisposable;
import io.reactivex.disposables.RunnableDisposable;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: Disposables.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static b a(Future<?> future) {
        d.a.p.b.b.d(future, "future is null");
        return b(future, true);
    }

    public static b b(Future<?> future, boolean z) {
        d.a.p.b.b.d(future, "future is null");
        return new FutureDisposable(future, z);
    }

    public static b c(Runnable runnable) {
        d.a.p.b.b.d(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }
}
