package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class g3 extends io.reactivex.rxjava3.observables.a {
    public final ObservableSource c;
    public final AtomicReference e = new AtomicReference();

    public g3(ObservableSource observableSource) {
        this.c = observableSource;
    }

    @Override // io.reactivex.rxjava3.observables.a
    public final void a(io.reactivex.rxjava3.functions.g gVar) {
        ObservablePublish$PublishConnection observablePublish$PublishConnection;
        loop0: while (true) {
            AtomicReference atomicReference = this.e;
            observablePublish$PublishConnection = (ObservablePublish$PublishConnection) atomicReference.get();
            if (observablePublish$PublishConnection != null && !observablePublish$PublishConnection.isDisposed()) {
                break;
            }
            ObservablePublish$PublishConnection observablePublish$PublishConnection2 = new ObservablePublish$PublishConnection(atomicReference);
            while (!atomicReference.compareAndSet(observablePublish$PublishConnection, observablePublish$PublishConnection2)) {
                if (atomicReference.get() != observablePublish$PublishConnection) {
                    break;
                }
            }
            observablePublish$PublishConnection = observablePublish$PublishConnection2;
            break loop0;
        }
        boolean z = false;
        if (!observablePublish$PublishConnection.connect.get() && observablePublish$PublishConnection.connect.compareAndSet(false, true)) {
            z = true;
        }
        try {
            gVar.accept(observablePublish$PublishConnection);
            if (z) {
                this.c.subscribe(observablePublish$PublishConnection);
            }
        } catch (Throwable th) {
            he1.R(th);
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
    }

    @Override // io.reactivex.rxjava3.observables.a
    public final void b() {
        AtomicReference atomicReference = this.e;
        ObservablePublish$PublishConnection observablePublish$PublishConnection = (ObservablePublish$PublishConnection) atomicReference.get();
        if (observablePublish$PublishConnection == null || !observablePublish$PublishConnection.isDisposed()) {
            return;
        }
        while (!atomicReference.compareAndSet(observablePublish$PublishConnection, null) && atomicReference.get() == observablePublish$PublishConnection) {
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        ObservablePublish$PublishConnection observablePublish$PublishConnection;
        ObservablePublish$InnerDisposable[] observablePublish$InnerDisposableArr;
        ObservablePublish$InnerDisposable[] observablePublish$InnerDisposableArr2;
        loop0: while (true) {
            AtomicReference atomicReference = this.e;
            observablePublish$PublishConnection = (ObservablePublish$PublishConnection) atomicReference.get();
            if (observablePublish$PublishConnection != null) {
                break;
            }
            ObservablePublish$PublishConnection observablePublish$PublishConnection2 = new ObservablePublish$PublishConnection(atomicReference);
            while (!atomicReference.compareAndSet(observablePublish$PublishConnection, observablePublish$PublishConnection2)) {
                if (atomicReference.get() != observablePublish$PublishConnection) {
                    break;
                }
            }
            observablePublish$PublishConnection = observablePublish$PublishConnection2;
            break loop0;
        }
        ObservablePublish$InnerDisposable observablePublish$InnerDisposable = new ObservablePublish$InnerDisposable(observer, observablePublish$PublishConnection);
        observer.onSubscribe(observablePublish$InnerDisposable);
        do {
            observablePublish$InnerDisposableArr = observablePublish$PublishConnection.get();
            if (observablePublish$InnerDisposableArr == ObservablePublish$PublishConnection.e) {
                Throwable th = observablePublish$PublishConnection.error;
                if (th != null) {
                    observer.onError(th);
                    return;
                } else {
                    observer.onComplete();
                    return;
                }
            }
            int length = observablePublish$InnerDisposableArr.length;
            observablePublish$InnerDisposableArr2 = new ObservablePublish$InnerDisposable[length + 1];
            System.arraycopy(observablePublish$InnerDisposableArr, 0, observablePublish$InnerDisposableArr2, 0, length);
            observablePublish$InnerDisposableArr2[length] = observablePublish$InnerDisposable;
        } while (!observablePublish$PublishConnection.compareAndSet(observablePublish$InnerDisposableArr, observablePublish$InnerDisposableArr2));
        if (observablePublish$InnerDisposable.isDisposed()) {
            observablePublish$PublishConnection.a(observablePublish$InnerDisposable);
        }
    }
}
