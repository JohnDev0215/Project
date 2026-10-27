package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e implements SingleObserver, MaybeObserver, CompletableObserver, Disposable {
    public final SingleObserver c;
    public Disposable e;

    public e(SingleObserver singleObserver) {
        this.c = singleObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        this.c.onSuccess(Notification.b);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.onSuccess(Notification.a(th));
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.e, disposable)) {
            this.e = disposable;
            this.c.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        Objects.requireNonNull(obj, "value is null");
        this.c.onSuccess(new Notification(obj));
    }
}
