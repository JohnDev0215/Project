package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import spi.di;
import spi.g;
import spi.k4;
import spi.mp;
import spi.q6;
import spi.r6;
import spi.s3;
import spi.vn;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class DisposableAutoReleaseMultiObserver<T> extends a implements mp<T>, di<T>, s3 {
    private static final long serialVersionUID = 8924480688481408726L;
    public final k4<? super T> onSuccess;

    public DisposableAutoReleaseMultiObserver(r6 r6Var, k4<? super T> k4Var, k4<? super Throwable> k4Var2, g gVar) {
        super(r6Var, k4Var2, gVar);
        this.onSuccess = k4Var;
    }

    public void onSuccess(T t) {
        q6 q6Var = get();
        DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (q6Var != disposableHelper) {
            lazySet(disposableHelper);
            try {
                this.onSuccess.accept(t);
            } catch (Throwable th) {
                x8.a(th);
                vn.a(th);
            }
        }
        removeSelf();
    }
}
