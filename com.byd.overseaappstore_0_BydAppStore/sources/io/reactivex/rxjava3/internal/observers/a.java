package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.g;
import spi.k4;
import spi.q6;
import spi.r6;
import spi.vc;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends AtomicReference<q6> implements q6 {
    private static final long serialVersionUID = 8924480688481408726L;
    public final AtomicReference<r6> composite;
    public final g onComplete;
    public final k4<? super Throwable> onError;

    public a(r6 r6Var, k4<? super Throwable> k4Var, g gVar) {
        this.onError = k4Var;
        this.onComplete = gVar;
        this.composite = new AtomicReference<>(r6Var);
    }

    @Override // spi.q6
    public final void dispose() {
        DisposableHelper.dispose(this);
        removeSelf();
    }

    public final boolean hasCustomOnError() {
        return this.onError != vc.b;
    }

    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public final void onComplete() {
        q6 q6Var = get();
        DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (q6Var != disposableHelper) {
            lazySet(disposableHelper);
            try {
                Objects.requireNonNull(this.onComplete);
            } catch (Throwable th) {
                x8.a(th);
                vn.a(th);
            }
        }
        removeSelf();
    }

    public final void onError(Throwable th) {
        q6 q6Var = get();
        DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (q6Var != disposableHelper) {
            lazySet(disposableHelper);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                x8.a(th2);
                vn.a(new CompositeException(th, th2));
            }
        } else {
            vn.a(th);
        }
        removeSelf();
    }

    public final void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }

    final void removeSelf() {
        r6 andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.a();
        }
    }
}
