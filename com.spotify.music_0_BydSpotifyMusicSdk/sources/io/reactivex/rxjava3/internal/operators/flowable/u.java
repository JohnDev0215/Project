package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class u extends io.reactivex.rxjava3.internal.fuseable.a implements CompletableObserver {
    public final rg2 c;
    public Disposable e;

    public u(rg2 rg2Var) {
        this.c = rg2Var;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.a, p.tg2
    public final void cancel() {
        this.e.dispose();
        this.e = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        this.e = DisposableHelper.c;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        this.e = DisposableHelper.c;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }
}
