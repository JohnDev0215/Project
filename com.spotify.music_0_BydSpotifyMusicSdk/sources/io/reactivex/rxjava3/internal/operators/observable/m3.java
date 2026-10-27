package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class m3 extends Observable {
    public final io.reactivex.rxjava3.observables.a c;
    public final int e;
    public ObservableRefCount$RefConnection k;

    public m3(io.reactivex.rxjava3.observables.a aVar) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.c = aVar;
        this.e = 1;
    }

    public final void a(ObservableRefCount$RefConnection observableRefCount$RefConnection) {
        synchronized (this) {
            try {
                if (this.k == observableRefCount$RefConnection) {
                    Disposable disposable = observableRefCount$RefConnection.timer;
                    if (disposable != null) {
                        disposable.dispose();
                        observableRefCount$RefConnection.timer = null;
                    }
                    long j = observableRefCount$RefConnection.subscriberCount - 1;
                    observableRefCount$RefConnection.subscriberCount = j;
                    if (j == 0) {
                        this.k = null;
                        this.c.b();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(ObservableRefCount$RefConnection observableRefCount$RefConnection) {
        synchronized (this) {
            try {
                if (observableRefCount$RefConnection.subscriberCount == 0 && observableRefCount$RefConnection == this.k) {
                    this.k = null;
                    Disposable disposable = observableRefCount$RefConnection.get();
                    DisposableHelper.a(observableRefCount$RefConnection);
                    if (disposable == null) {
                        observableRefCount$RefConnection.disconnectedEarly = true;
                    } else {
                        this.c.b();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservableRefCount$RefConnection observableRefCount$RefConnection;
        boolean z;
        Disposable disposable;
        synchronized (this) {
            try {
                observableRefCount$RefConnection = this.k;
                if (observableRefCount$RefConnection == null) {
                    observableRefCount$RefConnection = new ObservableRefCount$RefConnection(this);
                    this.k = observableRefCount$RefConnection;
                }
                long j = observableRefCount$RefConnection.subscriberCount;
                if (j == 0 && (disposable = observableRefCount$RefConnection.timer) != null) {
                    disposable.dispose();
                }
                long j2 = j + 1;
                observableRefCount$RefConnection.subscriberCount = j2;
                if (observableRefCount$RefConnection.connected || j2 != this.e) {
                    z = false;
                } else {
                    z = true;
                    observableRefCount$RefConnection.connected = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.c.subscribe(new ObservableRefCount$RefCountObserver(observer, this, observableRefCount$RefConnection));
        if (z) {
            this.c.a(observableRefCount$RefConnection);
        }
    }
}
