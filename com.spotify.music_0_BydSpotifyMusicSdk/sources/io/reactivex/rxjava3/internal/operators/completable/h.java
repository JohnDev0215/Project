package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class h implements CompletableObserver {
    public final CompletableObserver c;
    public final io.reactivex.rxjava3.disposables.a e;
    public final AtomicThrowable k;
    public final AtomicInteger l;

    public h(CompletableObserver completableObserver, io.reactivex.rxjava3.disposables.a aVar, AtomicThrowable atomicThrowable, AtomicInteger atomicInteger) {
        this.c = completableObserver;
        this.e = aVar;
        this.k = atomicThrowable;
        this.l = atomicInteger;
    }

    public final void a() {
        if (this.l.decrementAndGet() == 0) {
            this.k.c(this.c);
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        a();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        if (this.k.a(th)) {
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        this.e.c(disposable);
    }
}
