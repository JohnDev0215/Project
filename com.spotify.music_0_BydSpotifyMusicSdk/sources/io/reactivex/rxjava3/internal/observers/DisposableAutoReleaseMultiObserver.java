package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class DisposableAutoReleaseMultiObserver<T> extends AbstractDisposableAutoRelease implements SingleObserver<T>, MaybeObserver<T>, CompletableObserver {
    private static final long serialVersionUID = 8924480688481408726L;
    final io.reactivex.rxjava3.functions.g onSuccess;

    public DisposableAutoReleaseMultiObserver(io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.disposables.b bVar) {
        super(aVar, gVar2, bVar);
        this.onSuccess = gVar;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable != disposableHelper) {
            lazySet(disposableHelper);
            try {
                this.onSuccess.accept(obj);
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
        a();
    }
}
