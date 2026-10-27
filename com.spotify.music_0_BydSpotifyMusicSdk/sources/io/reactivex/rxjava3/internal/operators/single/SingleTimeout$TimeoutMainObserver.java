package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class SingleTimeout$TimeoutMainObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Runnable, Disposable {
    private static final long serialVersionUID = 37497744973048446L;
    final SingleObserver<? super T> downstream;
    final TimeoutFallbackObserver<T> fallback;
    SingleSource<? extends T> other;
    final AtomicReference<Disposable> task = new AtomicReference<>();
    final long timeout;
    final TimeUnit unit;

    public static final class TimeoutFallbackObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
        private static final long serialVersionUID = 2071387740092105509L;
        final SingleObserver<? super T> downstream;

        public TimeoutFallbackObserver(SingleObserver singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            this.downstream.onSuccess(obj);
        }
    }

    public SingleTimeout$TimeoutMainObserver(SingleObserver singleObserver, SingleSource singleSource, long j, TimeUnit timeUnit) {
        this.downstream = singleObserver;
        this.other = singleSource;
        this.timeout = j;
        this.unit = timeUnit;
        if (singleSource != null) {
            this.fallback = new TimeoutFallbackObserver<>(singleObserver);
        } else {
            this.fallback = null;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
        DisposableHelper.a(this.task);
        TimeoutFallbackObserver<T> timeoutFallbackObserver = this.fallback;
        if (timeoutFallbackObserver != null) {
            DisposableHelper.a(timeoutFallbackObserver);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || !compareAndSet(disposable, disposableHelper)) {
            RxJavaPlugins.b(th);
        } else {
            DisposableHelper.a(this.task);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || !compareAndSet(disposable, disposableHelper)) {
            return;
        }
        DisposableHelper.a(this.task);
        this.downstream.onSuccess(obj);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Disposable disposable = get();
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (disposable == disposableHelper || !compareAndSet(disposable, disposableHelper)) {
            return;
        }
        if (disposable != null) {
            disposable.dispose();
        }
        SingleSource<? extends T> singleSource = this.other;
        if (singleSource == null) {
            this.downstream.onError(new TimeoutException(io.reactivex.rxjava3.internal.util.c.e(this.timeout, this.unit)));
        } else {
            this.other = null;
            singleSource.subscribe(this.fallback);
        }
    }
}
