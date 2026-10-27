package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindowTimed$WindowExactUnboundedObserver<T> extends ObservableWindowTimed$AbstractWindowObserver<T> implements Runnable {
    public static final Object c = new Object();
    private static final long serialVersionUID = 1155822639622580836L;
    final Scheduler scheduler;
    final SequentialDisposable timer;
    io.reactivex.rxjava3.subjects.f window;
    final Runnable windowRunnable;

    public ObservableWindowTimed$WindowExactUnboundedObserver(Observer observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        super(observer, j, timeUnit, i);
        this.scheduler = scheduler;
        this.timer = new SequentialDisposable();
        this.windowRunnable = new h5(1, this);
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void a() {
        this.timer.dispose();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void b() {
        if (this.downstreamCancelled.get()) {
            return;
        }
        this.windowCount.getAndIncrement();
        io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this.windowRunnable);
        this.window = fVarA;
        this.emitted = 1L;
        o oVar = new o(fVarA);
        this.downstream.onNext(oVar);
        SequentialDisposable sequentialDisposable = this.timer;
        Scheduler scheduler = this.scheduler;
        long j = this.timespan;
        Disposable disposableE = scheduler.e(this, j, j, this.unit);
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposableE);
        if (oVar.a()) {
            this.window.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void c() {
        if (getAndIncrement() != 0) {
            return;
        }
        io.reactivex.rxjava3.operators.f fVar = this.queue;
        Observer<? super Observable<T>> observer = this.downstream;
        io.reactivex.rxjava3.subjects.f fVarA = this.window;
        int iAddAndGet = 1;
        while (true) {
            if (this.upstreamCancelled) {
                fVar.clear();
                this.window = null;
                fVarA = null;
            } else {
                boolean z = this.done;
                Object objPoll = fVar.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        if (fVarA != null) {
                            fVarA.onError(th);
                        }
                        observer.onError(th);
                    } else {
                        if (fVarA != null) {
                            fVarA.onComplete();
                        }
                        observer.onComplete();
                    }
                    a();
                    this.upstreamCancelled = true;
                } else if (!z2) {
                    if (objPoll == c) {
                        if (fVarA != null) {
                            fVarA.onComplete();
                            this.window = null;
                            fVarA = null;
                        }
                        if (this.downstreamCancelled.get()) {
                            this.timer.dispose();
                        } else {
                            this.emitted++;
                            this.windowCount.getAndIncrement();
                            fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this.windowRunnable);
                            this.window = fVarA;
                            o oVar = new o(fVarA);
                            observer.onNext(oVar);
                            if (oVar.a()) {
                                fVarA.onComplete();
                            }
                        }
                    } else if (fVarA != null) {
                        fVarA.onNext(objPoll);
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver, java.lang.Runnable
    public final void run() {
        this.queue.offer(c);
        c();
    }
}
