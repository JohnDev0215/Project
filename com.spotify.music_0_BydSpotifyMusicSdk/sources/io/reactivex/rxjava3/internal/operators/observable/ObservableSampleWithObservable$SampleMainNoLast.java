package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSampleWithObservable$SampleMainNoLast<T> extends ObservableSampleWithObservable$SampleMainObserver<T> {
    private static final long serialVersionUID = -3029755663834015785L;

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleWithObservable$SampleMainObserver
    public final void a() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleWithObservable$SampleMainObserver
    public final void b() {
        T andSet = getAndSet(null);
        if (andSet != null) {
            this.downstream.onNext(andSet);
        }
    }
}
