package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class ObserverResourceWrapper<T> extends AtomicReference<q6> implements bk<T>, q6 {
    private static final long serialVersionUID = -8612022020200669122L;
    public final bk<? super T> downstream;
    public final AtomicReference<q6> upstream = new AtomicReference<>();

    public ObserverResourceWrapper(bk<? super T> bkVar) {
        this.downstream = bkVar;
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this.upstream);
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return this.upstream.get() == DisposableHelper.DISPOSED;
    }

    @Override // spi.bk
    public void onComplete() {
        dispose();
        this.downstream.onComplete();
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        dispose();
        this.downstream.onError(th);
    }

    @Override // spi.bk
    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        if (DisposableHelper.setOnce(this.upstream, q6Var)) {
            this.downstream.onSubscribe(this);
        }
    }

    public void setResource(q6 q6Var) {
        DisposableHelper.set(this, q6Var);
    }
}
