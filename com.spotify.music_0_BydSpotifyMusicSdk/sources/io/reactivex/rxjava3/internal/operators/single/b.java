package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Single implements SingleObserver {
    public static final SingleCache$CacheDisposable[] n = new SingleCache$CacheDisposable[0];
    public static final SingleCache$CacheDisposable[] o = new SingleCache$CacheDisposable[0];
    public final SingleSource c;
    public final AtomicInteger e = new AtomicInteger();
    public final AtomicReference k = new AtomicReference(n);
    public Object l;
    public Throwable m;

    public b(SingleSource singleSource) {
        this.c = singleSource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(SingleCache$CacheDisposable singleCache$CacheDisposable) {
        SingleCache$CacheDisposable[] singleCache$CacheDisposableArr;
        while (true) {
            AtomicReference atomicReference = this.k;
            SingleCache$CacheDisposable[] singleCache$CacheDisposableArr2 = (SingleCache$CacheDisposable[]) atomicReference.get();
            int length = singleCache$CacheDisposableArr2.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (singleCache$CacheDisposableArr2[i] == singleCache$CacheDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                singleCache$CacheDisposableArr = n;
            } else {
                SingleCache$CacheDisposable[] singleCache$CacheDisposableArr3 = new SingleCache$CacheDisposable[length - 1];
                System.arraycopy(singleCache$CacheDisposableArr2, 0, singleCache$CacheDisposableArr3, 0, i);
                System.arraycopy(singleCache$CacheDisposableArr2, i + 1, singleCache$CacheDisposableArr3, i, (length - i) - 1);
                singleCache$CacheDisposableArr = singleCache$CacheDisposableArr3;
            }
            while (!atomicReference.compareAndSet(singleCache$CacheDisposableArr2, singleCache$CacheDisposableArr)) {
                if (atomicReference.get() != singleCache$CacheDisposableArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.m = th;
        for (SingleCache$CacheDisposable singleCache$CacheDisposable : (SingleCache$CacheDisposable[]) this.k.getAndSet(o)) {
            if (!singleCache$CacheDisposable.isDisposed()) {
                singleCache$CacheDisposable.downstream.onError(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(Object obj) {
        this.l = obj;
        for (SingleCache$CacheDisposable singleCache$CacheDisposable : (SingleCache$CacheDisposable[]) this.k.getAndSet(o)) {
            if (!singleCache$CacheDisposable.isDisposed()) {
                singleCache$CacheDisposable.downstream.onSuccess(obj);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        SingleCache$CacheDisposable singleCache$CacheDisposable = new SingleCache$CacheDisposable(singleObserver, this);
        singleObserver.onSubscribe(singleCache$CacheDisposable);
        while (true) {
            AtomicReference atomicReference = this.k;
            SingleCache$CacheDisposable[] singleCache$CacheDisposableArr = (SingleCache$CacheDisposable[]) atomicReference.get();
            if (singleCache$CacheDisposableArr == o) {
                Throwable th = this.m;
                if (th != null) {
                    singleObserver.onError(th);
                    return;
                } else {
                    singleObserver.onSuccess(this.l);
                    return;
                }
            }
            int length = singleCache$CacheDisposableArr.length;
            SingleCache$CacheDisposable[] singleCache$CacheDisposableArr2 = new SingleCache$CacheDisposable[length + 1];
            System.arraycopy(singleCache$CacheDisposableArr, 0, singleCache$CacheDisposableArr2, 0, length);
            singleCache$CacheDisposableArr2[length] = singleCache$CacheDisposable;
            while (!atomicReference.compareAndSet(singleCache$CacheDisposableArr, singleCache$CacheDisposableArr2)) {
                if (atomicReference.get() != singleCache$CacheDisposableArr) {
                    break;
                }
            }
            if (singleCache$CacheDisposable.isDisposed()) {
                c(singleCache$CacheDisposable);
            }
            if (this.e.getAndIncrement() == 0) {
                this.c.subscribe(this);
                return;
            }
            return;
        }
    }
}
