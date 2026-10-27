package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class i extends CountDownLatch implements Observer, Future, Disposable {
    public Object c;
    public Throwable e;
    public final AtomicReference k;

    public i() {
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

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.c == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        AtomicReference atomicReference = this.k;
        Disposable disposable = (Disposable) atomicReference.get();
        if (disposable == this || disposable == DisposableHelper.c) {
            return;
        }
        while (!atomicReference.compareAndSet(disposable, this)) {
            if (atomicReference.get() != disposable) {
                return;
            }
        }
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.e == null) {
            AtomicReference atomicReference = this.k;
            Disposable disposable = (Disposable) atomicReference.get();
            if (disposable != this && disposable != DisposableHelper.c) {
                while (!atomicReference.compareAndSet(disposable, this)) {
                    if (atomicReference.get() != disposable) {
                    }
                }
                this.e = th;
                countDown();
                return;
            }
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.c == null) {
            this.c = obj;
        } else {
            ((Disposable) this.k.get()).dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.k, disposable);
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
