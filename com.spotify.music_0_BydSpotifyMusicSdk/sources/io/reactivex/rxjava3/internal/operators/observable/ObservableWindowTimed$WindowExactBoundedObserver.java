package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindowTimed$WindowExactBoundedObserver<T> extends ObservableWindowTimed$AbstractWindowObserver<T> implements Runnable {
    private static final long serialVersionUID = -6130475889925953722L;
    long count;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final SequentialDisposable timer;
    io.reactivex.rxjava3.subjects.f window;
    final Scheduler.Worker worker;

    public ObservableWindowTimed$WindowExactBoundedObserver(int i, long j, long j2, Observer observer, Scheduler scheduler, TimeUnit timeUnit, boolean z) {
        super(observer, j, timeUnit, i);
        this.scheduler = scheduler;
        this.maxSize = j2;
        this.restartTimerOnMaxSize = z;
        if (z) {
            this.worker = scheduler.b();
        } else {
            this.worker = null;
        }
        this.timer = new SequentialDisposable();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void a() {
        this.timer.dispose();
        Scheduler.Worker worker = this.worker;
        if (worker != null) {
            worker.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void b() {
        if (this.downstreamCancelled.get()) {
            return;
        }
        this.emitted = 1L;
        this.windowCount.getAndIncrement();
        io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this);
        this.window = fVarA;
        o oVar = new o(fVarA);
        this.downstream.onNext(oVar);
        o5 o5Var = new o5(this, 1L);
        if (this.restartTimerOnMaxSize) {
            SequentialDisposable sequentialDisposable = this.timer;
            Scheduler.Worker worker = this.worker;
            long j = this.timespan;
            Disposable disposableC = worker.c(o5Var, j, j, this.unit);
            sequentialDisposable.getClass();
            DisposableHelper.c(sequentialDisposable, disposableC);
        } else {
            SequentialDisposable sequentialDisposable2 = this.timer;
            Scheduler scheduler = this.scheduler;
            long j2 = this.timespan;
            Disposable disposableE = scheduler.e(o5Var, j2, j2, this.unit);
            sequentialDisposable2.getClass();
            DisposableHelper.c(sequentialDisposable2, disposableE);
        }
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
        io.reactivex.rxjava3.subjects.f fVarF = this.window;
        int iAddAndGet = 1;
        while (true) {
            if (this.upstreamCancelled) {
                fVar.clear();
                fVarF = null;
                this.window = null;
            } else {
                boolean z = this.done;
                Object objPoll = fVar.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        if (fVarF != null) {
                            fVarF.onError(th);
                        }
                        observer.onError(th);
                    } else {
                        if (fVarF != null) {
                            fVarF.onComplete();
                        }
                        observer.onComplete();
                    }
                    a();
                    this.upstreamCancelled = true;
                } else if (!z2) {
                    if (objPoll instanceof o5) {
                        if (((o5) objPoll).e == this.emitted || !this.restartTimerOnMaxSize) {
                            this.count = 0L;
                            fVarF = f(fVarF);
                        }
                    } else if (fVarF != null) {
                        fVarF.onNext(objPoll);
                        long j = this.count + 1;
                        if (j == this.maxSize) {
                            this.count = 0L;
                            fVarF = f(fVarF);
                        } else {
                            this.count = j;
                        }
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    public final io.reactivex.rxjava3.subjects.f f(io.reactivex.rxjava3.subjects.f fVar) {
        if (fVar != null) {
            fVar.onComplete();
            fVar = null;
        }
        if (this.downstreamCancelled.get()) {
            a();
        } else {
            long j = this.emitted + 1;
            this.emitted = j;
            this.windowCount.getAndIncrement();
            fVar = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this);
            this.window = fVar;
            o oVar = new o(fVar);
            this.downstream.onNext(oVar);
            if (this.restartTimerOnMaxSize) {
                SequentialDisposable sequentialDisposable = this.timer;
                Scheduler.Worker worker = this.worker;
                o5 o5Var = new o5(this, j);
                long j2 = this.timespan;
                Disposable disposableC = worker.c(o5Var, j2, j2, this.unit);
                sequentialDisposable.getClass();
                DisposableHelper.d(sequentialDisposable, disposableC);
            }
            if (oVar.a()) {
                fVar.onComplete();
            }
        }
        return fVar;
    }
}
