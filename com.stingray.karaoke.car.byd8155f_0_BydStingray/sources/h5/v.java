package h5;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public final class v implements y {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final v f15564m = new v(null);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final x f15565n = new x(v.class);
    public final Object l;

    public v(Object obj) {
        this.l = obj;
    }

    @Override // h5.y
    public final void b(Runnable runnable, Executor executor) {
        P8.n.q(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (Exception e10) {
            f15565n.a().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e10);
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z6) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.l;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.l + "]]";
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j3, TimeUnit timeUnit) {
        timeUnit.getClass();
        return this.l;
    }
}
