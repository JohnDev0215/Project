package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class q1 extends a {
    public final /* synthetic */ int e;
    public final Object k;
    public final boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q1(ObservableSource observableSource, Object obj, boolean z, int i) {
        super(observableSource);
        this.e = i;
        this.k = obj;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        Object obj = this.k;
        boolean z = this.l;
        switch (i) {
            case 0:
                observableSource.subscribe(new ObservableFlatMapCompletable$FlatMapCompletableMainObserver(observer, (io.reactivex.rxjava3.functions.o) obj, z));
                break;
            case 1:
                observableSource.subscribe(new ObservableFlatMapMaybe$FlatMapMaybeObserver(observer, (io.reactivex.rxjava3.functions.o) obj, z));
                break;
            case 2:
                observableSource.subscribe(new ObservableFlatMapSingle$FlatMapSingleObserver(observer, (io.reactivex.rxjava3.functions.o) obj, z));
                break;
            default:
                final io.reactivex.rxjava3.observers.d dVar = new io.reactivex.rxjava3.observers.d(observer);
                if (!z) {
                    observableSource.subscribe(new ObservableSampleWithObservable$SampleMainNoLast((ObservableSource) obj, dVar));
                } else {
                    final ObservableSource observableSource2 = (ObservableSource) obj;
                    observableSource.subscribe(new ObservableSampleWithObservable$SampleMainObserver<T>(observableSource2, dVar) { // from class: io.reactivex.rxjava3.internal.operators.observable.ObservableSampleWithObservable$SampleMainEmitLast
                        private static final long serialVersionUID = -3029755663834015785L;
                        volatile boolean done;
                        final AtomicInteger wip = new AtomicInteger();

                        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleWithObservable$SampleMainObserver
                        public final void a() {
                            this.done = true;
                            if (this.wip.getAndIncrement() == 0) {
                                T andSet = getAndSet(null);
                                if (andSet != null) {
                                    this.downstream.onNext(andSet);
                                }
                                this.downstream.onComplete();
                            }
                        }

                        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleWithObservable$SampleMainObserver
                        public final void b() {
                            if (this.wip.getAndIncrement() == 0) {
                                do {
                                    boolean z2 = this.done;
                                    T andSet = getAndSet(null);
                                    if (andSet != null) {
                                        this.downstream.onNext(andSet);
                                    }
                                    if (z2) {
                                        this.downstream.onComplete();
                                        return;
                                    }
                                } while (this.wip.decrementAndGet() != 0);
                            }
                        }
                    });
                }
                break;
        }
    }
}
