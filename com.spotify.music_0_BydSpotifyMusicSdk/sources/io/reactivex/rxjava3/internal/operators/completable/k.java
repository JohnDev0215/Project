package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class k implements CompletableObserver, Disposable {
    public final CompletableObserver c;
    public Disposable e;
    public final /* synthetic */ l k;

    public k(l lVar, CompletableObserver completableObserver) {
        this.k = lVar;
        this.c = completableObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        try {
            this.k.o.run();
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        CompletableObserver completableObserver = this.c;
        l lVar = this.k;
        if (this.e == DisposableHelper.c) {
            return;
        }
        try {
            lVar.l.run();
            lVar.m.run();
            completableObserver.onComplete();
            try {
                lVar.n.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            completableObserver.onError(th2);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        l lVar = this.k;
        if (this.e == DisposableHelper.c) {
            RxJavaPlugins.b(th);
            return;
        }
        try {
            lVar.k.accept(th);
            lVar.m.run();
        } catch (Throwable th2) {
            he1.R(th2);
            th = new CompositeException(th, th2);
        }
        this.c.onError(th);
        try {
            lVar.n.run();
        } catch (Throwable th3) {
            he1.R(th3);
            RxJavaPlugins.b(th3);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        CompletableObserver completableObserver = this.c;
        try {
            this.k.e.accept(disposable);
            if (DisposableHelper.g(this.e, disposable)) {
                this.e = disposable;
                completableObserver.onSubscribe(this);
            }
        } catch (Throwable th) {
            he1.R(th);
            disposable.dispose();
            this.e = DisposableHelper.c;
            completableObserver.onSubscribe(EmptyDisposable.c);
            completableObserver.onError(th);
        }
    }
}
