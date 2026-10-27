package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver<T> extends AtomicInteger implements Disposable, Observer<T> {
    private static final long serialVersionUID = 8443155186132538303L;
    final boolean delayErrors;
    volatile boolean disposed;
    final CompletableObserver downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    Disposable upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final io.reactivex.rxjava3.disposables.a set = new io.reactivex.rxjava3.disposables.a();

    public final class InnerObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 8606673141535671828L;

        public InnerObserver() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.a(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.b(get());
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onComplete() {
            ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver = ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.this;
            observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.set.b(this);
            observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onError(Throwable th) {
            ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver = ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.this;
            observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.set.b(this);
            observableFlatMapCompletableCompletable$FlatMapCompletableMainObserver.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableFlatMapCompletableCompletable$FlatMapCompletableMainObserver(CompletableObserver completableObserver, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.downstream = completableObserver;
        this.mapper = oVar;
        this.delayErrors = z;
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        this.upstream.dispose();
        this.set.dispose();
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (decrementAndGet() == 0) {
            this.errors.c(this.downstream);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (this.delayErrors) {
                if (decrementAndGet() == 0) {
                    this.errors.c(this.downstream);
                }
            } else {
                this.disposed = true;
                this.upstream.dispose();
                this.set.dispose();
                this.errors.c(this.downstream);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null CompletableSource");
            CompletableSource completableSource = (CompletableSource) objApply;
            getAndIncrement();
            InnerObserver innerObserver = new InnerObserver();
            if (this.disposed || !this.set.c(innerObserver)) {
                return;
            }
            completableSource.subscribe(innerObserver);
        } catch (Throwable th) {
            he1.R(th);
            this.upstream.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
