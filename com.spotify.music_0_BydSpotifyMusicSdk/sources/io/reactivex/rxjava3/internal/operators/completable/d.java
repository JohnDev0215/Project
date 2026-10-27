package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Completable {
    public static final d c = new d();

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        completableObserver.onSubscribe(EmptyDisposable.c);
        completableObserver.onComplete();
    }
}
