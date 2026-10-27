package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Observer {
    public Disposable c;
    public volatile Object e;

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.e = NotificationLite.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.e = NotificationLite.f(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        Disposable disposable2 = this.c;
        Objects.requireNonNull(disposable, "next is null");
        if (disposable2 == null) {
            this.c = disposable;
            return;
        }
        disposable.dispose();
        if (disposable2 != DisposableHelper.c) {
            he1.M(f.class);
        }
    }
}
