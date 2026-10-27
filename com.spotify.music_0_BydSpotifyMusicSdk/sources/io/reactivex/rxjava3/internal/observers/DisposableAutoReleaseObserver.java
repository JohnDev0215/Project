package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class DisposableAutoReleaseObserver<T> extends AbstractDisposableAutoRelease implements Observer<T> {
    private static final long serialVersionUID = 8924480688481408726L;
    final io.reactivex.rxjava3.functions.g onNext;

    public DisposableAutoReleaseObserver(io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.disposables.b bVar) {
        super(aVar, gVar2, bVar);
        this.onNext = gVar;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (get() != DisposableHelper.c) {
            try {
                this.onNext.accept(obj);
            } catch (Throwable th) {
                he1.R(th);
                get().dispose();
                onError(th);
            }
        }
    }
}
