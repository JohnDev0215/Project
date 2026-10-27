package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Scheduler.Worker {
    public final io.reactivex.rxjava3.internal.disposables.a c;
    public final io.reactivex.rxjava3.disposables.a e;
    public final io.reactivex.rxjava3.internal.disposables.a k;
    public final c l;
    public volatile boolean m;

    public a(c cVar) {
        this.l = cVar;
        io.reactivex.rxjava3.internal.disposables.a aVar = new io.reactivex.rxjava3.internal.disposables.a();
        this.c = aVar;
        io.reactivex.rxjava3.disposables.a aVar2 = new io.reactivex.rxjava3.disposables.a();
        this.e = aVar2;
        io.reactivex.rxjava3.internal.disposables.a aVar3 = new io.reactivex.rxjava3.internal.disposables.a();
        this.k = aVar3;
        aVar3.c(aVar);
        aVar3.c(aVar2);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable a(Runnable runnable) {
        return this.m ? EmptyDisposable.c : this.l.d(runnable, 0L, TimeUnit.MILLISECONDS, this.c);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.m ? EmptyDisposable.c : this.l.d(runnable, j, timeUnit, this.e);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.k.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m;
    }
}
