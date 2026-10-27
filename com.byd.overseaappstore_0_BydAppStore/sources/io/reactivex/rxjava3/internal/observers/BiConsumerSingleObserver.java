package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import spi.mp;
import spi.n1;
import spi.q6;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class BiConsumerSingleObserver<T> extends AtomicReference<q6> implements mp<T>, q6 {
    private static final long serialVersionUID = 4943102778943297569L;
    public final n1<? super T, ? super Throwable> onCallback;

    public BiConsumerSingleObserver(n1<? super T, ? super Throwable> n1Var) {
        this.onCallback = n1Var;
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // spi.mp
    public void onError(Throwable th) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a();
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
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a();
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }
}
