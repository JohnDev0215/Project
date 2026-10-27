package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public final boolean a(Throwable th) {
        if (c.a(this, th)) {
            return true;
        }
        RxJavaPlugins.b(th);
        return false;
    }

    public final void b() {
        Throwable thD = c.d(this);
        if (thD == null || thD == c.a) {
            return;
        }
        RxJavaPlugins.b(thD);
    }

    public final void c(CompletableObserver completableObserver) {
        Throwable thD = c.d(this);
        if (thD == null) {
            completableObserver.onComplete();
        } else if (thD != c.a) {
            completableObserver.onError(thD);
        }
    }

    public final void d(Observer observer) {
        Throwable thD = c.d(this);
        if (thD == null) {
            observer.onComplete();
        } else if (thD != c.a) {
            observer.onError(thD);
        }
    }

    public final void e(rg2 rg2Var) {
        Throwable thD = c.d(this);
        if (thD == null) {
            rg2Var.onComplete();
        } else if (thD != c.a) {
            rg2Var.onError(thD);
        }
    }
}
