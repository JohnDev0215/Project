package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Disposable {
    public final AtomicThrowable c;

    public i(AtomicThrowable atomicThrowable) {
        this.c = atomicThrowable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.c.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.c.get() == io.reactivex.rxjava3.internal.util.c.a;
    }
}
