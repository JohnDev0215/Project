package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import spi.bk;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeferredScalarObserver<T, R> extends DeferredScalarDisposable<R> implements bk<T> {
    private static final long serialVersionUID = -266195175408988651L;
    public q6 upstream;

    public DeferredScalarObserver(bk<? super R> bkVar) {
        super(bkVar);
    }

    @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable, spi.q6
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }

    @Override // spi.bk
    public void onComplete() {
        T t = this.value;
        if (t == null) {
            complete();
        } else {
            this.value = null;
            complete(t);
        }
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        this.value = null;
        error(th);
    }

    @Override // spi.bk
    public abstract /* synthetic */ void onNext(T t);

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        if (DisposableHelper.validate(this.upstream, q6Var)) {
            this.upstream = q6Var;
            this.downstream.onSubscribe(this);
        }
    }
}
