package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservablePublish$InnerDisposable<T> extends AtomicReference<ObservablePublish$PublishConnection<T>> implements Disposable {
    private static final long serialVersionUID = 7463222674719692880L;
    final Observer<? super T> downstream;

    public ObservablePublish$InnerDisposable(Observer observer, ObservablePublish$PublishConnection observablePublish$PublishConnection) {
        this.downstream = observer;
        lazySet(observablePublish$PublishConnection);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        ObservablePublish$PublishConnection<T> andSet = getAndSet(null);
        if (andSet != null) {
            andSet.a(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == null;
    }
}
