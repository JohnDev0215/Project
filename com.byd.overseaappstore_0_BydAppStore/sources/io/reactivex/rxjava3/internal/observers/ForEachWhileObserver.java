package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.bk;
import spi.el;
import spi.g;
import spi.k4;
import spi.q6;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class ForEachWhileObserver<T> extends AtomicReference<q6> implements bk<T>, q6 {
    private static final long serialVersionUID = -4403180040475402120L;
    public boolean done;
    public final g onComplete;
    public final k4<? super Throwable> onError;
    public final el<? super T> onNext;

    public ForEachWhileObserver(el<? super T> elVar, k4<? super Throwable> k4Var, g gVar) {
        this.onNext = elVar;
        this.onError = k4Var;
        this.onComplete = gVar;
    }

    @Override // spi.q6
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // spi.bk
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            Objects.requireNonNull(this.onComplete);
        } catch (Throwable th) {
            x8.a(th);
            vn.a(th);
        }
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        if (this.done) {
            vn.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            x8.a(th2);
            vn.a(new CompositeException(th, th2));
        }
    }

    @Override // spi.bk
    public void onNext(T t) {
        if (this.done) {
            return;
        }
        try {
            if (this.onNext.a()) {
                return;
            }
            dispose();
            onComplete();
        } catch (Throwable th) {
            x8.a(th);
            dispose();
            onError(th);
        }
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        DisposableHelper.setOnce(this, q6Var);
    }
}
