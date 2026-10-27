package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMapCompletable$FlatMapCompletableMainObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
    private static final long serialVersionUID = 8443155186132538303L;
    final boolean delayErrors;
    volatile boolean disposed;
    final Observer<? super T> downstream;
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
            ObservableFlatMapCompletable$FlatMapCompletableMainObserver observableFlatMapCompletable$FlatMapCompletableMainObserver = ObservableFlatMapCompletable$FlatMapCompletableMainObserver.this;
            observableFlatMapCompletable$FlatMapCompletableMainObserver.set.b(this);
            observableFlatMapCompletable$FlatMapCompletableMainObserver.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onError(Throwable th) {
            ObservableFlatMapCompletable$FlatMapCompletableMainObserver observableFlatMapCompletable$FlatMapCompletableMainObserver = ObservableFlatMapCompletable$FlatMapCompletableMainObserver.this;
            observableFlatMapCompletable$FlatMapCompletableMainObserver.set.b(this);
            observableFlatMapCompletable$FlatMapCompletableMainObserver.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableFlatMapCompletable$FlatMapCompletableMainObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.delayErrors = z;
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 2;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
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

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (decrementAndGet() == 0) {
            this.errors.d(this.downstream);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (this.delayErrors) {
                if (decrementAndGet() == 0) {
                    this.errors.d(this.downstream);
                }
            } else {
                this.disposed = true;
                this.upstream.dispose();
                this.set.dispose();
                this.errors.d(this.downstream);
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

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        return null;
    }
}
