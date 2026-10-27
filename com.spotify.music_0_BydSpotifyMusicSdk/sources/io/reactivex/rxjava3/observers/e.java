package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.VolatileSizeArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Observer, Disposable, MaybeObserver, SingleObserver, CompletableObserver {
    public final CountDownLatch c;
    public final VolatileSizeArrayList e;
    public final VolatileSizeArrayList k;
    public boolean l;
    public final Observer m;
    public final AtomicReference n;

    public e() {
        TestObserver$EmptyObserver testObserver$EmptyObserver = TestObserver$EmptyObserver.c;
        this.e = new VolatileSizeArrayList();
        this.k = new VolatileSizeArrayList();
        this.c = new CountDownLatch(1);
        this.n = new AtomicReference();
        this.m = testObserver$EmptyObserver;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        DisposableHelper.a(this.n);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.b((Disposable) this.n.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        CountDownLatch countDownLatch = this.c;
        if (!this.l) {
            this.l = true;
            if (this.n.get() == null) {
                this.k.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            this.m.onComplete();
        } finally {
            countDownLatch.countDown();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        CountDownLatch countDownLatch = this.c;
        boolean z = this.l;
        VolatileSizeArrayList volatileSizeArrayList = this.k;
        if (!z) {
            this.l = true;
            if (this.n.get() == null) {
                volatileSizeArrayList.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            if (th == null) {
                volatileSizeArrayList.add(new NullPointerException("onError received a null Throwable"));
            } else {
                volatileSizeArrayList.add(th);
            }
            this.m.onError(th);
            countDownLatch.countDown();
        } catch (Throwable th2) {
            countDownLatch.countDown();
            throw th2;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        boolean z = this.l;
        VolatileSizeArrayList volatileSizeArrayList = this.k;
        if (!z) {
            this.l = true;
            if (this.n.get() == null) {
                volatileSizeArrayList.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        Thread.currentThread();
        this.e.add(obj);
        if (obj == null) {
            volatileSizeArrayList.add(new NullPointerException("onNext received a null value"));
        }
        this.m.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        Thread.currentThread();
        VolatileSizeArrayList volatileSizeArrayList = this.k;
        if (disposable == null) {
            volatileSizeArrayList.add(new NullPointerException("onSubscribe received a null Subscription"));
            return;
        }
        AtomicReference atomicReference = this.n;
        while (!atomicReference.compareAndSet(null, disposable)) {
            if (atomicReference.get() != null) {
                disposable.dispose();
                if (atomicReference.get() != DisposableHelper.c) {
                    volatileSizeArrayList.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + disposable));
                    return;
                }
                return;
            }
        }
        this.m.onSubscribe(disposable);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        onNext(obj);
        onComplete();
    }
}
