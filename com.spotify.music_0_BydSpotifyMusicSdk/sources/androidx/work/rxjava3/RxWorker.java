package androidx.work.rxjava3;

import android.content.Context;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.futures.b;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.i;
import io.reactivex.rxjava3.schedulers.e;
import java.util.concurrent.Executor;
import p.c62;
import p.lb1;
import p.qb1;
import p.wa;

/* JADX INFO: loaded from: classes.dex */
public abstract class RxWorker extends qb1 {
    public static final wa n = new wa(1);
    public c62 m;

    public RxWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // p.qb1
    public final lb1 a() {
        return f(new c62(), Single.error(new IllegalStateException("Expedited WorkRequests require a RxWorker to provide an implementation for `getForegroundInfo()`")));
    }

    @Override // p.qb1
    public final void b() {
        c62 c62Var = this.m;
        if (c62Var != null) {
            Disposable disposable = c62Var.e;
            if (disposable != null) {
                disposable.dispose();
            }
            this.m = null;
        }
    }

    @Override // p.qb1
    public final b d() {
        c62 c62Var = new c62();
        this.m = c62Var;
        return f(c62Var, g());
    }

    public final b f(c62 c62Var, Single single) {
        WorkerParameters workerParameters = this.e;
        Executor executor = workerParameters.c;
        Scheduler scheduler = e.a;
        single.subscribeOn(new i(executor, true, true)).observeOn(new i(workerParameters.d.a, true, true)).subscribe(c62Var);
        return c62Var.c;
    }

    public abstract Single g();
}
