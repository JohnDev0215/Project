package p;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class s10 extends Scheduler.Worker {
    public boolean c;
    public final /* synthetic */ u10 e;

    public s10(u10 u10Var) {
        this.e = u10Var;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        v03.j(runnable, "run");
        v03.j(timeUnit, "unit");
        this.e.a.post(runnable);
        return Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.c = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.c;
    }
}
