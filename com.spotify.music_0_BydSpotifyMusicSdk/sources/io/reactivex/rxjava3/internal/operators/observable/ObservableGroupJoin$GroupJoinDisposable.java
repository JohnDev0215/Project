package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableGroupJoin$GroupJoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, b2 {
    public static final Integer c = 1;
    public static final Integer e = 2;
    public static final Integer k = 3;
    public static final Integer l = 4;
    private static final long serialVersionUID = -6071216598687999801L;
    volatile boolean cancelled;
    final Observer<? super R> downstream;
    final io.reactivex.rxjava3.functions.o leftEnd;
    int leftIndex;
    final io.reactivex.rxjava3.functions.c resultSelector;
    final io.reactivex.rxjava3.functions.o rightEnd;
    int rightIndex;
    final io.reactivex.rxjava3.disposables.a disposables = new io.reactivex.rxjava3.disposables.a();
    final io.reactivex.rxjava3.operators.h queue = new io.reactivex.rxjava3.operators.h(Observable.bufferSize());
    final Map<Integer, io.reactivex.rxjava3.subjects.f> lefts = new LinkedHashMap();
    final Map<Integer, TRight> rights = new LinkedHashMap();
    final AtomicReference<Throwable> error = new AtomicReference<>();
    final AtomicInteger active = new AtomicInteger(2);

    public ObservableGroupJoin$GroupJoinDisposable(Observer observer, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, io.reactivex.rxjava3.functions.c cVar) {
        this.downstream = observer;
        this.leftEnd = oVar;
        this.rightEnd = oVar2;
        this.resultSelector = cVar;
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b2
    public final void a(Throwable th) {
        if (io.reactivex.rxjava3.internal.util.c.a(this.error, th)) {
            g();
        } else {
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b2
    public final void b(Throwable th) {
        if (!io.reactivex.rxjava3.internal.util.c.a(this.error, th)) {
            RxJavaPlugins.b(th);
        } else {
            this.active.decrementAndGet();
            g();
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b2
    public final void c(boolean z, ObservableGroupJoin$LeftRightEndObserver observableGroupJoin$LeftRightEndObserver) {
        synchronized (this) {
            try {
                this.queue.a(z ? k : l, observableGroupJoin$LeftRightEndObserver);
            } catch (Throwable th) {
                throw th;
            }
        }
        g();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b2
    public final void d(ObservableGroupJoin$LeftRightObserver observableGroupJoin$LeftRightObserver) {
        this.disposables.b(observableGroupJoin$LeftRightObserver);
        this.active.decrementAndGet();
        g();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.disposables.dispose();
        if (getAndIncrement() == 0) {
            this.queue.clear();
        }
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.b2
    public final void f(Object obj, boolean z) {
        synchronized (this) {
            try {
                this.queue.a(z ? c : e, obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        g();
    }

    public final void g() {
        if (getAndIncrement() != 0) {
            return;
        }
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        Observer<? super R> observer = this.downstream;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (this.error.get() != null) {
                hVar.clear();
                this.disposables.dispose();
                h(observer);
                return;
            }
            boolean z = this.active.get() == 0;
            Integer num = (Integer) hVar.poll();
            boolean z2 = num == null;
            if (z && z2) {
                Iterator<io.reactivex.rxjava3.subjects.f> it = this.lefts.values().iterator();
                while (it.hasNext()) {
                    it.next().onComplete();
                }
                this.lefts.clear();
                this.rights.clear();
                this.disposables.dispose();
                observer.onComplete();
                return;
            }
            if (z2) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                Object objPoll = hVar.poll();
                if (num == c) {
                    io.reactivex.rxjava3.subjects.f fVar = new io.reactivex.rxjava3.subjects.f(Observable.bufferSize(), null);
                    int i = this.leftIndex;
                    this.leftIndex = i + 1;
                    this.lefts.put(Integer.valueOf(i), fVar);
                    try {
                        Object objApply = this.leftEnd.apply(objPoll);
                        Objects.requireNonNull(objApply, "The leftEnd returned a null ObservableSource");
                        ObservableSource observableSource = (ObservableSource) objApply;
                        ObservableGroupJoin$LeftRightEndObserver observableGroupJoin$LeftRightEndObserver = new ObservableGroupJoin$LeftRightEndObserver(this, true, i);
                        this.disposables.c(observableGroupJoin$LeftRightEndObserver);
                        observableSource.subscribe(observableGroupJoin$LeftRightEndObserver);
                        if (this.error.get() != null) {
                            hVar.clear();
                            this.disposables.dispose();
                            h(observer);
                            return;
                        }
                        try {
                            Object objK = this.resultSelector.k(objPoll, fVar);
                            Objects.requireNonNull(objK, "The resultSelector returned a null value");
                            observer.onNext(objK);
                            Iterator<TRight> it2 = this.rights.values().iterator();
                            while (it2.hasNext()) {
                                fVar.onNext(it2.next());
                            }
                        } catch (Throwable th) {
                            i(th, observer, hVar);
                            return;
                        }
                    } catch (Throwable th2) {
                        i(th2, observer, hVar);
                        return;
                    }
                } else if (num == e) {
                    int i2 = this.rightIndex;
                    this.rightIndex = i2 + 1;
                    this.rights.put(Integer.valueOf(i2), (TRight) objPoll);
                    try {
                        Object objApply2 = this.rightEnd.apply(objPoll);
                        Objects.requireNonNull(objApply2, "The rightEnd returned a null ObservableSource");
                        ObservableSource observableSource2 = (ObservableSource) objApply2;
                        ObservableGroupJoin$LeftRightEndObserver observableGroupJoin$LeftRightEndObserver2 = new ObservableGroupJoin$LeftRightEndObserver(this, false, i2);
                        this.disposables.c(observableGroupJoin$LeftRightEndObserver2);
                        observableSource2.subscribe(observableGroupJoin$LeftRightEndObserver2);
                        if (this.error.get() != null) {
                            hVar.clear();
                            this.disposables.dispose();
                            h(observer);
                            return;
                        } else {
                            Iterator<io.reactivex.rxjava3.subjects.f> it3 = this.lefts.values().iterator();
                            while (it3.hasNext()) {
                                it3.next().onNext(objPoll);
                            }
                        }
                    } catch (Throwable th3) {
                        i(th3, observer, hVar);
                        return;
                    }
                } else if (num == k) {
                    ObservableGroupJoin$LeftRightEndObserver observableGroupJoin$LeftRightEndObserver3 = (ObservableGroupJoin$LeftRightEndObserver) objPoll;
                    io.reactivex.rxjava3.subjects.f fVarRemove = this.lefts.remove(Integer.valueOf(observableGroupJoin$LeftRightEndObserver3.index));
                    this.disposables.a(observableGroupJoin$LeftRightEndObserver3);
                    if (fVarRemove != null) {
                        fVarRemove.onComplete();
                    }
                } else {
                    ObservableGroupJoin$LeftRightEndObserver observableGroupJoin$LeftRightEndObserver4 = (ObservableGroupJoin$LeftRightEndObserver) objPoll;
                    this.rights.remove(Integer.valueOf(observableGroupJoin$LeftRightEndObserver4.index));
                    this.disposables.a(observableGroupJoin$LeftRightEndObserver4);
                }
            }
        }
        hVar.clear();
    }

    public final void h(Observer observer) {
        Throwable thD = io.reactivex.rxjava3.internal.util.c.d(this.error);
        Iterator<io.reactivex.rxjava3.subjects.f> it = this.lefts.values().iterator();
        while (it.hasNext()) {
            it.next().onError(thD);
        }
        this.lefts.clear();
        this.rights.clear();
        observer.onError(thD);
    }

    public final void i(Throwable th, Observer observer, io.reactivex.rxjava3.operators.h hVar) {
        he1.R(th);
        io.reactivex.rxjava3.internal.util.c.a(this.error, th);
        hVar.clear();
        this.disposables.dispose();
        h(observer);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }
}
