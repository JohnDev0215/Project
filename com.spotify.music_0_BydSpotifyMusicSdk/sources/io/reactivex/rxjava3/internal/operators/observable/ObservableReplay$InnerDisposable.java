package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableReplay$InnerDisposable<T> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = 2728361546769921047L;
    volatile boolean cancelled;
    final Observer<? super T> child;
    Object index;
    final ObservableReplay$ReplayObserver<T> parent;

    public ObservableReplay$InnerDisposable(ObservableReplay$ReplayObserver observableReplay$ReplayObserver, Observer observer) {
        this.parent = observableReplay$ReplayObserver;
        this.child = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.parent.a(this);
        this.index = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }
}
