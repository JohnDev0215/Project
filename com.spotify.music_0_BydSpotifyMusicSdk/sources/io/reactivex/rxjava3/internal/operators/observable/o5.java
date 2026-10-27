package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class o5 implements Runnable {
    public final ObservableWindowTimed$WindowExactBoundedObserver c;
    public final long e;

    public o5(ObservableWindowTimed$WindowExactBoundedObserver observableWindowTimed$WindowExactBoundedObserver, long j) {
        this.c = observableWindowTimed$WindowExactBoundedObserver;
        this.e = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ObservableWindowTimed$WindowExactBoundedObserver observableWindowTimed$WindowExactBoundedObserver = this.c;
        observableWindowTimed$WindowExactBoundedObserver.queue.offer(this);
        observableWindowTimed$WindowExactBoundedObserver.c();
    }
}
