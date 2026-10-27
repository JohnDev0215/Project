package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRefCount$RefCountObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
    private static final long serialVersionUID = -7419642935409022375L;
    final ObservableRefCount$RefConnection connection;
    final Observer<? super T> downstream;
    final m3 parent;
    Disposable upstream;

    public ObservableRefCount$RefCountObserver(Observer observer, m3 m3Var, ObservableRefCount$RefConnection observableRefCount$RefConnection) {
        this.downstream = observer;
        this.parent = m3Var;
        this.connection = observableRefCount$RefConnection;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        if (compareAndSet(false, true)) {
            m3 m3Var = this.parent;
            ObservableRefCount$RefConnection observableRefCount$RefConnection = this.connection;
            synchronized (m3Var) {
                try {
                    ObservableRefCount$RefConnection observableRefCount$RefConnection2 = m3Var.k;
                    if (observableRefCount$RefConnection2 != null && observableRefCount$RefConnection2 == observableRefCount$RefConnection) {
                        long j = observableRefCount$RefConnection.subscriberCount - 1;
                        observableRefCount$RefConnection.subscriberCount = j;
                        if (j == 0 && observableRefCount$RefConnection.connected) {
                            m3Var.b(observableRefCount$RefConnection);
                        }
                    }
                } finally {
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (compareAndSet(false, true)) {
            this.parent.a(this.connection);
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (!compareAndSet(false, true)) {
            RxJavaPlugins.b(th);
        } else {
            this.parent.a(this.connection);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
