package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.g;
import spi.k4;
import spi.q6;
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class LambdaObserver<T> extends AtomicReference<q6> implements bk<T>, q6 {
    private static final long serialVersionUID = -7251123623727029452L;
    public final g onComplete;
    public final k4<? super Throwable> onError;
    public final k4<? super T> onNext;
    public final k4<? super q6> onSubscribe;

    public LambdaObserver(k4<? super T> k4Var, k4<? super Throwable> k4Var2, g gVar, k4<? super q6> k4Var3) {
        this.onNext = k4Var;
        this.onError = k4Var2;
        this.onComplete = gVar;
        this.onSubscribe = k4Var3;
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

    @Override // spi.bk
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        lazySet(DisposableHelper.DISPOSED);
        try {
            Objects.requireNonNull(this.onComplete);
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        if (isDisposed()) {
            vn.a(th);
            return;
        }
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(new CompositeException(th, th2));
        }
    }

    @Override // spi.bk
    public void onNext(T t) {
        if (isDisposed()) {
            return;
        }
        try {
            this.onNext.accept(t);
        } catch (Throwable th) {
            x8.a(th);
            get().dispose();
            onError(th);
        }
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        if (DisposableHelper.setOnce(this, q6Var)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                x8.a(th);
                q6Var.dispose();
                onError(th);
            }
        }
    }
}
