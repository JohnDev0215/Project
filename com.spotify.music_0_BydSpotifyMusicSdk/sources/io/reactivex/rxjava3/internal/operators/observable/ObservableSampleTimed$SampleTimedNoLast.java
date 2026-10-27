package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSampleTimed$SampleTimedNoLast<T> extends ObservableSampleTimed$SampleTimedObserver<T> {
    private static final long serialVersionUID = -7139995637533111443L;

    @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableSampleTimed$SampleTimedObserver
    public final void a() {
        this.downstream.onComplete();
    }

    @Override // java.lang.Runnable
    public final void run() {
        T andSet = getAndSet(null);
        if (andSet != null) {
            this.downstream.onNext(andSet);
        }
    }
}
