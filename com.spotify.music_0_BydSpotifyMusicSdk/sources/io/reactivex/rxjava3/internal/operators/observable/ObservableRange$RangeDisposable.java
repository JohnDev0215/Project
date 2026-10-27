package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRange$RangeDisposable extends BasicIntQueueDisposable<Integer> {
    private static final long serialVersionUID = 396518478098735504L;
    final Observer<? super Integer> downstream;
    final long end;
    boolean fused;
    long index;

    public ObservableRange$RangeDisposable(Observer observer, long j, long j2) {
        this.downstream = observer;
        this.index = j;
        this.end = j2;
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.fused = true;
        return 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.index = this.end;
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        set(1);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get() != 0;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.index == this.end;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        long j = this.index;
        if (j != this.end) {
            this.index = 1 + j;
            return Integer.valueOf((int) j);
        }
        lazySet(1);
        return null;
    }
}
