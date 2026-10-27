package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Observer, Disposable {
    public final Observer c;
    public final long e;
    public final TimeUnit k;
    public final Scheduler.Worker l;
    public Disposable m;
    public Disposable n;
    public volatile long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f79p;

    public u0(io.reactivex.rxjava3.observers.d dVar, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
        this.c = dVar;
        this.e = j;
        this.k = timeUnit;
        this.l = worker;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m.dispose();
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.f79p) {
            return;
        }
        this.f79p = true;
        Disposable disposable = this.n;
        if (disposable != null) {
            disposable.dispose();
        }
        ObservableDebounceTimed$DebounceEmitter observableDebounceTimed$DebounceEmitter = (ObservableDebounceTimed$DebounceEmitter) disposable;
        if (observableDebounceTimed$DebounceEmitter != null) {
            observableDebounceTimed$DebounceEmitter.run();
        }
        this.c.onComplete();
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.f79p) {
            RxJavaPlugins.b(th);
            return;
        }
        Disposable disposable = this.n;
        if (disposable != null) {
            disposable.dispose();
        }
        this.f79p = true;
        this.c.onError(th);
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.f79p) {
            return;
        }
        long j = this.o + 1;
        this.o = j;
        Disposable disposable = this.n;
        if (disposable != null) {
            disposable.dispose();
        }
        ObservableDebounceTimed$DebounceEmitter observableDebounceTimed$DebounceEmitter = new ObservableDebounceTimed$DebounceEmitter(obj, j, this);
        this.n = observableDebounceTimed$DebounceEmitter;
        DisposableHelper.c(observableDebounceTimed$DebounceEmitter, this.l.b(observableDebounceTimed$DebounceEmitter, this.e, this.k));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.m, disposable)) {
            this.m = disposable;
            this.c.onSubscribe(this);
        }
    }
}
