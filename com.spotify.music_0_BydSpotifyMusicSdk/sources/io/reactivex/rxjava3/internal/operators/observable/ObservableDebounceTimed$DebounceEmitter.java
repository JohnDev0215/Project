package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableDebounceTimed$DebounceEmitter<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
    private static final long serialVersionUID = 6812032969491025141L;
    final long idx;
    final AtomicBoolean once = new AtomicBoolean();
    final u0 parent;
    final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableDebounceTimed$DebounceEmitter(Object obj, long j, u0 u0Var) {
        this.value = obj;
        this.idx = j;
        this.parent = u0Var;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.once.compareAndSet(false, true)) {
            u0 u0Var = this.parent;
            long j = this.idx;
            T t = this.value;
            if (j == u0Var.o) {
                u0Var.c.onNext(t);
                dispose();
            }
        }
    }
}
