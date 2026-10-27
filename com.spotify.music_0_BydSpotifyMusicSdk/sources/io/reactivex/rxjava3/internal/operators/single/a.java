package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class a implements SingleObserver {
    public final io.reactivex.rxjava3.disposables.a c;
    public final SingleObserver e;
    public final AtomicBoolean k;
    public Disposable l;

    public a(SingleObserver singleObserver, io.reactivex.rxjava3.disposables.a aVar, AtomicBoolean atomicBoolean) {
        this.e = singleObserver;
        this.c = aVar;
        this.k = atomicBoolean;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        if (!this.k.compareAndSet(false, true)) {
            RxJavaPlugins.b(th);
            return;
        }
        Disposable disposable = this.l;
        io.reactivex.rxjava3.disposables.a aVar = this.c;
        aVar.b(disposable);
        aVar.dispose();
        this.e.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        this.l = disposable;
        this.c.c(disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        if (this.k.compareAndSet(false, true)) {
            Disposable disposable = this.l;
            io.reactivex.rxjava3.disposables.a aVar = this.c;
            aVar.b(disposable);
            aVar.dispose();
            this.e.onSuccess(obj);
        }
    }
}
