package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class k implements SingleObserver, Disposable {
    public final MaybeObserver c;
    public final io.reactivex.rxjava3.functions.o e;
    public Disposable k;

    public k(MaybeObserver maybeObserver, io.reactivex.rxjava3.functions.o oVar) {
        this.c = maybeObserver;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k.dispose();
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
            Objects.requireNonNull(objApply, "The selector returned a null Notification");
            Notification notification = (Notification) objApply;
            Object obj2 = notification.a;
            if (notification.c()) {
                if (obj2 == null || NotificationLite.i(obj2)) {
                    obj2 = null;
                }
                maybeObserver.onSuccess(obj2);
                return;
            }
            if (obj2 == null) {
                maybeObserver.onComplete();
            } else {
                maybeObserver.onError(notification.b());
            }
        } catch (Throwable th) {
            he1.R(th);
            maybeObserver.onError(th);
        }
    }
}
