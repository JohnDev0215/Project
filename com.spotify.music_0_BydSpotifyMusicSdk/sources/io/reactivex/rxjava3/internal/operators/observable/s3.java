package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class s3 implements ObservableSource {
    public final AtomicReference c;
    public final p3 e;

    public s3(AtomicReference atomicReference, p3 p3Var) {
        this.c = atomicReference;
        this.e = p3Var;
    }

    @Override // io.reactivex.rxjava3.core.ObservableSource
    public void subscribe(Observer<Object> observer) {
        ObservableReplay$ReplayObserver observableReplay$ReplayObserver;
        loop0: while (true) {
            AtomicReference atomicReference = this.c;
            observableReplay$ReplayObserver = (ObservableReplay$ReplayObserver) atomicReference.get();
            if (observableReplay$ReplayObserver != null) {
                break;
            }
            ObservableReplay$ReplayObserver observableReplay$ReplayObserver2 = new ObservableReplay$ReplayObserver(this.e.call(), atomicReference);
            while (!atomicReference.compareAndSet(null, observableReplay$ReplayObserver2)) {
                if (atomicReference.get() != null) {
                    break;
                }
            }
            observableReplay$ReplayObserver = observableReplay$ReplayObserver2;
            break loop0;
        }
        ObservableReplay$InnerDisposable observableReplay$InnerDisposable = new ObservableReplay$InnerDisposable(observableReplay$ReplayObserver, observer);
        observer.onSubscribe(observableReplay$InnerDisposable);
        loop2: while (true) {
            ObservableReplay$InnerDisposable[] observableReplay$InnerDisposableArr = observableReplay$ReplayObserver.observers.get();
            if (observableReplay$InnerDisposableArr != ObservableReplay$ReplayObserver.e) {
                int length = observableReplay$InnerDisposableArr.length;
                ObservableReplay$InnerDisposable[] observableReplay$InnerDisposableArr2 = new ObservableReplay$InnerDisposable[length + 1];
                System.arraycopy(observableReplay$InnerDisposableArr, 0, observableReplay$InnerDisposableArr2, 0, length);
                observableReplay$InnerDisposableArr2[length] = observableReplay$InnerDisposable;
                AtomicReference<ObservableReplay$InnerDisposable[]> atomicReference2 = observableReplay$ReplayObserver.observers;
                while (!atomicReference2.compareAndSet(observableReplay$InnerDisposableArr, observableReplay$InnerDisposableArr2)) {
                    if (atomicReference2.get() != observableReplay$InnerDisposableArr) {
                        break;
                    }
                }
                break loop2;
            }
            break;
        }
        if (observableReplay$InnerDisposable.isDisposed()) {
            observableReplay$ReplayObserver.a(observableReplay$InnerDisposable);
        } else {
            observableReplay$ReplayObserver.buffer.h(observableReplay$InnerDisposable);
        }
    }
}
