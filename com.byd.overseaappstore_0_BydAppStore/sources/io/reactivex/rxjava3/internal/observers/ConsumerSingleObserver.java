package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import spi.k4;
import spi.mp;
import spi.q6;
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class ConsumerSingleObserver<T> extends AtomicReference<q6> implements mp<T>, q6 {
    private static final long serialVersionUID = -7012088219455310787L;
    public final k4<? super Throwable> onError;
    public final k4<? super T> onSuccess;

    public ConsumerSingleObserver(k4<? super T> k4Var, k4<? super Throwable> k4Var2) {
        this.onSuccess = k4Var;
        this.onError = k4Var2;
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean hasCustomOnError() {
        return this.onError != vc.b;
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // spi.mp
    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(new CompositeException(th, th2));
        }
    }

    @Override // spi.mp
    public void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }

    public void onSuccess(T t) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(t);
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }
}
