package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.Optional;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class n implements SingleObserver, Disposable {
    public final MaybeObserver c;
    public final io.reactivex.rxjava3.functions.o e;
    public Disposable k;

    public n(MaybeObserver maybeObserver, io.reactivex.rxjava3.functions.o oVar) {
        this.c = maybeObserver;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Disposable disposable = this.k;
        this.k = DisposableHelper.c;
        disposable.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.k, disposable)) {
            this.k = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        MaybeObserver maybeObserver = this.c;
        try {
            Object objApply = this.e.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null item");
            Optional optional = (Optional) objApply;
            if (optional.isPresent()) {
                maybeObserver.onSuccess(optional.get());
            } else {
                maybeObserver.onComplete();
            }
        } catch (Throwable th) {
            he1.R(th);
            maybeObserver.onError(th);
        }
    }
}
