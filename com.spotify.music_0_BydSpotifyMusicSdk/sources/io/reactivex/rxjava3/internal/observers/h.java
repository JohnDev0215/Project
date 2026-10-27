package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class h extends CountDownLatch implements MaybeObserver, SingleObserver, CompletableObserver, Future, Disposable {
    public Object c;
    public Throwable e;
    public final AtomicReference k;

    public h() {
        super(1);
        this.k = new AtomicReference();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        DisposableHelper disposableHelper;
        while (true) {
            AtomicReference atomicReference = this.k;
            Disposable disposable = (Disposable) atomicReference.get();
            if (disposable == this || disposable == (disposableHelper = DisposableHelper.c)) {
                return false;
            }
            while (!atomicReference.compareAndSet(disposable, disposableHelper)) {
                if (atomicReference.get() != disposable) {
                    break;
                }
            }
            if (disposable != null) {
                disposable.dispose();
            }
            countDown();
            return true;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        if (getCount() != 0) {
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.e;
        if (th == null) {
            return this.c;
        }
        throw new ExecutionException(th);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return DisposableHelper.b((Disposable) this.k.get());
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return isDone();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return getCount() == 0;
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        AtomicReference atomicReference = this.k;
        Disposable disposable = (Disposable) atomicReference.get();
        if (disposable == DisposableHelper.c) {
            return;
        }
        while (!atomicReference.compareAndSet(disposable, this) && atomicReference.get() == disposable) {
        }
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        while (true) {
            AtomicReference atomicReference = this.k;
            Disposable disposable = (Disposable) atomicReference.get();
            if (disposable == DisposableHelper.c) {
                RxJavaPlugins.b(th);
                return;
            }
            this.e = th;
            while (!atomicReference.compareAndSet(disposable, this)) {
                if (atomicReference.get() != disposable) {
                    break;
                }
            }
            countDown();
            return;
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.k, disposable);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        AtomicReference atomicReference = this.k;
        Disposable disposable = (Disposable) atomicReference.get();
        if (disposable == DisposableHelper.c) {
            return;
        }
        this.c = obj;
        while (!atomicReference.compareAndSet(disposable, this) && atomicReference.get() == disposable) {
        }
        countDown();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        if (getCount() != 0 && !await(j, timeUnit)) {
            throw new TimeoutException(io.reactivex.rxjava3.internal.util.c.e(j, timeUnit));
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.e;
        if (th == null) {
            return this.c;
        }
        throw new ExecutionException(th);
    }
}
