package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
    private static final long serialVersionUID = -8003404460084760287L;
    final g parent;

    public ObservableSwitchMapCompletable$SwitchMapCompletableObserver$SwitchMapInnerObserver(g gVar) {
        this.parent = gVar;
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        g gVar = this.parent;
        AtomicReference atomicReference = gVar.m;
        while (!atomicReference.compareAndSet(this, null)) {
            if (atomicReference.get() != this) {
                return;
            }
        }
        if (gVar.n) {
            gVar.l.c(gVar.c);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        g gVar = this.parent;
        AtomicReference atomicReference = gVar.m;
        while (!atomicReference.compareAndSet(this, null)) {
            if (atomicReference.get() != this) {
                RxJavaPlugins.b(th);
                return;
            }
        }
        if (gVar.l.a(th)) {
            if (gVar.k) {
                if (gVar.n) {
                    gVar.l.c(gVar.c);
                }
            } else {
                gVar.o.dispose();
                gVar.a();
                gVar.l.c(gVar.c);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }
}
