package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.g;
import spi.k4;
import spi.q6;
import spi.s3;
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class CallbackCompletableObserver extends AtomicReference<q6> implements s3, q6 {
    private static final long serialVersionUID = -4361286194466301354L;
    public final g onComplete;
    public final k4<? super Throwable> onError;

    public CallbackCompletableObserver(k4<? super Throwable> k4Var, g gVar) {
        this.onError = k4Var;
        this.onComplete = gVar;
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

    @Override // spi.s3
    public void onComplete() {
        try {
            Objects.requireNonNull(this.onComplete);
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // spi.s3
    public void onError(Throwable th) {
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(th2);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // spi.s3
    public void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }
}
