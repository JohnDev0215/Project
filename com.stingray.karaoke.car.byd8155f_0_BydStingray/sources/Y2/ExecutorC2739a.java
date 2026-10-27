package y2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import u2.I;

/* JADX INFO: renamed from: y2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ExecutorC2739a implements Executor {
    public final /* synthetic */ ExecutorService l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I f24118m;

    public ExecutorC2739a(ExecutorService executorService, I i7) {
        this.l = executorService;
        this.f24118m = i7;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.l.execute(runnable);
    }
}
