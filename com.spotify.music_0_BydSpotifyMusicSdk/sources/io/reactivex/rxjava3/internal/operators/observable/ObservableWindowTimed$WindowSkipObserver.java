package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindowTimed$WindowSkipObserver<T> extends ObservableWindowTimed$AbstractWindowObserver<T> implements Runnable {
    public static final Object c = new Object();
    public static final Object e = new Object();
    private static final long serialVersionUID = -7852870764194095894L;
    final long timeskip;
    final List<io.reactivex.rxjava3.subjects.f> windows;
    final Scheduler.Worker worker;

    public ObservableWindowTimed$WindowSkipObserver(Observer observer, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker, int i) {
        super(observer, j, timeUnit, i);
        this.timeskip = j2;
        this.worker = worker;
        this.windows = new LinkedList();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void a() {
        this.worker.dispose();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void b() {
        if (this.downstreamCancelled.get()) {
            return;
        }
        this.emitted = 1L;
        this.windowCount.getAndIncrement();
        io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this);
        this.windows.add(fVarA);
        o oVar = new o(fVarA);
        this.downstream.onNext(oVar);
        this.worker.b(new p5(this, false), this.timespan, this.unit);
        Scheduler.Worker worker = this.worker;
        p5 p5Var = new p5(this, true);
        long j = this.timeskip;
        worker.c(p5Var, j, j, this.unit);
        if (oVar.a()) {
            fVarA.onComplete();
            this.windows.remove(fVarA);
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed$AbstractWindowObserver
    public final void c() {
        if (getAndIncrement() != 0) {
            return;
        }
        io.reactivex.rxjava3.operators.f fVar = this.queue;
        Observer<? super Observable<T>> observer = this.downstream;
        List<io.reactivex.rxjava3.subjects.f> list = this.windows;
        int iAddAndGet = 1;
        while (true) {
            if (this.upstreamCancelled) {
                fVar.clear();
                list.clear();
            } else {
                boolean z = this.done;
                Object objPoll = fVar.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        Iterator<io.reactivex.rxjava3.subjects.f> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                        observer.onError(th);
                    } else {
                        Iterator<io.reactivex.rxjava3.subjects.f> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                        observer.onComplete();
                    }
                    a();
                    this.upstreamCancelled = true;
                } else if (!z2) {
                    if (objPoll == c) {
                        if (!this.downstreamCancelled.get()) {
                            this.emitted++;
                            this.windowCount.getAndIncrement();
                            io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this);
                            list.add(fVarA);
                            o oVar = new o(fVarA);
                            observer.onNext(oVar);
                            this.worker.b(new p5(this, false), this.timespan, this.unit);
                            if (oVar.a()) {
                                fVarA.onComplete();
                            }
                        }
                    } else if (objPoll != e) {
                        Iterator<io.reactivex.rxjava3.subjects.f> it3 = list.iterator();
                        while (it3.hasNext()) {
                            it3.next().onNext(objPoll);
                        }
                    } else if (!list.isEmpty()) {
                        list.remove(0).onComplete();
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }
}
