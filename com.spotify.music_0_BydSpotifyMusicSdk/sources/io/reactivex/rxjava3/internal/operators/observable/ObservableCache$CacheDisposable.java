package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCache$CacheDisposable<T> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = 6770240836423125754L;
    volatile boolean disposed;
    final Observer<? super T> downstream;
    long index;
    d0 node;
    int offset;
    final e0 parent;

    public ObservableCache$CacheDisposable(Observer observer, e0 e0Var) {
        this.downstream = observer;
        this.parent = e0Var;
        this.node = e0Var.n;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        ObservableCache$CacheDisposable[] observableCache$CacheDisposableArr;
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        e0 e0Var = this.parent;
        while (true) {
            AtomicReference atomicReference = e0Var.l;
            ObservableCache$CacheDisposable<T>[] observableCache$CacheDisposableArr2 = (ObservableCache$CacheDisposable[]) atomicReference.get();
            int length = observableCache$CacheDisposableArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (observableCache$CacheDisposableArr2[i] == this) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                observableCache$CacheDisposableArr = e0.s;
            } else {
                ObservableCache$CacheDisposable[] observableCache$CacheDisposableArr3 = new ObservableCache$CacheDisposable[length - 1];
                System.arraycopy(observableCache$CacheDisposableArr2, 0, observableCache$CacheDisposableArr3, 0, i);
                System.arraycopy(observableCache$CacheDisposableArr2, i + 1, observableCache$CacheDisposableArr3, i, (length - i) - 1);
                observableCache$CacheDisposableArr = observableCache$CacheDisposableArr3;
            }
            while (!atomicReference.compareAndSet(observableCache$CacheDisposableArr2, observableCache$CacheDisposableArr)) {
                if (atomicReference.get() != observableCache$CacheDisposableArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }
}
