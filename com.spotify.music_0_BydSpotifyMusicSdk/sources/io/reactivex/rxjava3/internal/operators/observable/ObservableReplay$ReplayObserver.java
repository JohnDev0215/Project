package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableReplay$ReplayObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    public static final ObservableReplay$InnerDisposable[] c = new ObservableReplay$InnerDisposable[0];
    public static final ObservableReplay$InnerDisposable[] e = new ObservableReplay$InnerDisposable[0];
    private static final long serialVersionUID = -533785617179540163L;
    final q3 buffer;
    final AtomicReference<ObservableReplay$ReplayObserver<T>> current;
    boolean done;
    final AtomicReference<ObservableReplay$InnerDisposable[]> observers = new AtomicReference<>(c);
    final AtomicBoolean shouldConnect = new AtomicBoolean();

    public ObservableReplay$ReplayObserver(q3 q3Var, AtomicReference atomicReference) {
        this.buffer = q3Var;
        this.current = atomicReference;
    }

    public final void a(ObservableReplay$InnerDisposable observableReplay$InnerDisposable) {
        ObservableReplay$InnerDisposable[] observableReplay$InnerDisposableArr;
        while (true) {
            ObservableReplay$InnerDisposable[] observableReplay$InnerDisposableArr2 = this.observers.get();
            int length = observableReplay$InnerDisposableArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (observableReplay$InnerDisposableArr2[i].equals(observableReplay$InnerDisposable)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                observableReplay$InnerDisposableArr = c;
            } else {
                ObservableReplay$InnerDisposable[] observableReplay$InnerDisposableArr3 = new ObservableReplay$InnerDisposable[length - 1];
                System.arraycopy(observableReplay$InnerDisposableArr2, 0, observableReplay$InnerDisposableArr3, 0, i);
                System.arraycopy(observableReplay$InnerDisposableArr2, i + 1, observableReplay$InnerDisposableArr3, i, (length - i) - 1);
                observableReplay$InnerDisposableArr = observableReplay$InnerDisposableArr3;
            }
            AtomicReference<ObservableReplay$InnerDisposable[]> atomicReference = this.observers;
            while (!atomicReference.compareAndSet(observableReplay$InnerDisposableArr2, observableReplay$InnerDisposableArr)) {
                if (atomicReference.get() != observableReplay$InnerDisposableArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.observers.set(e);
        AtomicReference<ObservableReplay$ReplayObserver<T>> atomicReference = this.current;
        while (!atomicReference.compareAndSet(this, null) && atomicReference.get() == this) {
        }
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.observers.get() == e;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.buffer.a();
        for (ObservableReplay$InnerDisposable observableReplay$InnerDisposable : this.observers.getAndSet(e)) {
            this.buffer.h(observableReplay$InnerDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.done = true;
        this.buffer.e(th);
        for (ObservableReplay$InnerDisposable observableReplay$InnerDisposable : this.observers.getAndSet(e)) {
            this.buffer.h(observableReplay$InnerDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        this.buffer.d(obj);
        for (ObservableReplay$InnerDisposable observableReplay$InnerDisposable : this.observers.get()) {
            this.buffer.h(observableReplay$InnerDisposable);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this, disposable)) {
            for (ObservableReplay$InnerDisposable observableReplay$InnerDisposable : this.observers.get()) {
                this.buffer.h(observableReplay$InnerDisposable);
            }
        }
    }
}
