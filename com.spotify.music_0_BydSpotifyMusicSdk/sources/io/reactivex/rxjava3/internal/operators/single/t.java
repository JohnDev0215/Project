package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class t implements SingleObserver {
    public final int c;
    public final io.reactivex.rxjava3.disposables.a e;
    public final Object[] k;
    public final SingleObserver l;
    public final AtomicInteger m;

    public t(int i, io.reactivex.rxjava3.disposables.a aVar, Object[] objArr, SingleObserver singleObserver, AtomicInteger atomicInteger) {
        this.c = i;
        this.e = aVar;
        this.k = objArr;
        this.l = singleObserver;
        this.m = atomicInteger;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        int andSet = this.m.getAndSet(-1);
        if (andSet != 0 && andSet != 1) {
            RxJavaPlugins.b(th);
        } else {
            this.e.dispose();
            this.l.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        this.e.c(disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        int i = this.c;
        Object[] objArr = this.k;
        objArr[i] = obj;
        if (this.m.incrementAndGet() == 2) {
            this.l.onSuccess(Boolean.valueOf(Objects.equals(objArr[0], objArr[1])));
        }
    }
}
