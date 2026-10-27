package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import spi.bk;
import spi.g;
import spi.k4;
import spi.r6;
import spi.x8;

/* JADX INFO: loaded from: classes2.dex */
public final class DisposableAutoReleaseObserver<T> extends a implements bk<T> {
    private static final long serialVersionUID = 8924480688481408726L;
    public final k4<? super T> onNext;

    public DisposableAutoReleaseObserver(r6 r6Var, k4<? super T> k4Var, k4<? super Throwable> k4Var2, g gVar) {
        super(r6Var, k4Var2, gVar);
        this.onNext = k4Var;
    }

    @Override // spi.bk
    public void onNext(T t) {
        if (get() != DisposableHelper.DISPOSED) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                x8.a(th);
                get().dispose();
                onError(th);
            }
        }
    }
}
