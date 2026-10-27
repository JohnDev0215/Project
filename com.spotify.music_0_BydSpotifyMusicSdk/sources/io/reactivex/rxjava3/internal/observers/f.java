package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public final class f extends CountDownLatch implements SingleObserver, CompletableObserver, MaybeObserver {
    public Object c;
    public Throwable e;
    public Disposable k;
    public volatile boolean l;

    public final Object a() {
        if (getCount() != 0) {
            try {
                await();
            } catch (InterruptedException e) {
                this.l = true;
                Disposable disposable = this.k;
                if (disposable != null) {
                    disposable.dispose();
                }
                throw io.reactivex.rxjava3.internal.util.c.f(e);
            }
        }
        Throwable th = this.e;
        if (th == null) {
            return this.c;
        }
        throw io.reactivex.rxjava3.internal.util.c.f(th);
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.e = th;
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        this.k = disposable;
        if (this.l) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.c = obj;
        countDown();
    }
}
