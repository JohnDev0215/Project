package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class p5 implements Runnable {
    public final ObservableWindowTimed$WindowSkipObserver c;
    public final boolean e;

    public p5(ObservableWindowTimed$WindowSkipObserver observableWindowTimed$WindowSkipObserver, boolean z) {
        this.c = observableWindowTimed$WindowSkipObserver;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ObservableWindowTimed$WindowSkipObserver observableWindowTimed$WindowSkipObserver = this.c;
        observableWindowTimed$WindowSkipObserver.queue.offer(this.e ? ObservableWindowTimed$WindowSkipObserver.c : ObservableWindowTimed$WindowSkipObserver.e);
        observableWindowTimed$WindowSkipObserver.c();
    }
}
