package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends CountDownLatch implements Observer, Disposable {
    public Object c;
    public Throwable e;
    public Disposable k;
    public volatile boolean l;

    public final Object a() {
        if (getCount() != 0) {
            try {
                await();
            } catch (InterruptedException e) {
                dispose();
                throw io.reactivex.rxjava3.internal.util.c.f(e);
            }
        }
        Throwable th = this.e;
        if (th == null) {
            return this.c;
        }
        throw io.reactivex.rxjava3.internal.util.c.f(th);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        this.l = true;
        Disposable disposable = this.k;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        this.k = disposable;
        if (this.l) {
            disposable.dispose();
        }
    }
}
