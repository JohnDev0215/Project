package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class c1 implements Observer, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.o e;
    public boolean k;
    public Disposable l;

    public c1(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.c = observer;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.k) {
            return;
        }
        this.k = true;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.k) {
            RxJavaPlugins.b(th);
        } else {
            this.k = true;
            this.c.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.k) {
            if (obj instanceof Notification) {
                Notification notification = (Notification) obj;
                if (NotificationLite.i(notification.a)) {
                    RxJavaPlugins.b(notification.b());
                    return;
                }
                return;
            }
            return;
        }
        try {
            Object objApply = this.e.apply(obj);
            Objects.requireNonNull(objApply, "The selector returned a null Notification");
            Notification notification2 = (Notification) objApply;
            Object obj2 = notification2.a;
            if (NotificationLite.i(obj2)) {
                this.l.dispose();
                onError(notification2.b());
            } else if (obj2 == null) {
                this.l.dispose();
                onComplete();
            } else {
                if (obj2 == null || NotificationLite.i(obj2)) {
                    obj2 = null;
                }
                this.c.onNext(obj2);
            }
        } catch (Throwable th) {
            he1.R(th);
            this.l.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.onSubscribe(this);
        }
    }
}
