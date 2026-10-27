package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class e5 extends Observable {
    public final /* synthetic */ int c = 0;
    public final long e;
    public final TimeUnit k;
    public final Object l;

    public e5(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        long j = this.e;
        Object obj = this.l;
        TimeUnit timeUnit = this.k;
        switch (i) {
            case 0:
                ObservableTimer$TimerObserver observableTimer$TimerObserver = new ObservableTimer$TimerObserver(observer);
                observer.onSubscribe(observableTimer$TimerObserver);
                Disposable disposableD = ((Scheduler) obj).d(observableTimer$TimerObserver, j, timeUnit);
                while (!observableTimer$TimerObserver.compareAndSet(null, disposableD)) {
                    if (observableTimer$TimerObserver.get() != null) {
                        if (observableTimer$TimerObserver.get() == DisposableHelper.c) {
                            disposableD.dispose();
                            return;
                        }
                        return;
                    }
                }
                return;
            default:
                DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(observer);
                observer.onSubscribe(deferredScalarDisposable);
                if (deferredScalarDisposable.isDisposed()) {
                    return;
                }
                try {
                    Object obj2 = timeUnit != null ? ((Future) obj).get(j, timeUnit) : ((Future) obj).get();
                    if (obj2 == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("Future returned a null value.");
                    }
                    Throwable th = io.reactivex.rxjava3.internal.util.c.a;
                    deferredScalarDisposable.c(obj2);
                    return;
                } catch (Throwable th2) {
                    he1.R(th2);
                    if (deferredScalarDisposable.isDisposed()) {
                        return;
                    }
                    observer.onError(th2);
                    return;
                }
        }
    }

    public e5(Future future, long j, TimeUnit timeUnit) {
        this.l = future;
        this.e = j;
        this.k = timeUnit;
    }
}
