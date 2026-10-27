package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
final class ObservableRetryWhen$RepeatWhenObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = 802743776666017014L;
    volatile boolean active;
    final Observer<? super T> downstream;
    final io.reactivex.rxjava3.subjects.e signaller;
    final ObservableSource<T> source;
    final AtomicInteger wip = new AtomicInteger();
    final AtomicThrowable error = new AtomicThrowable();
    final ObservableRetryWhen$RepeatWhenObserver<T>.InnerRepeatObserver inner = new InnerRepeatObserver();
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public final class InnerRepeatObserver extends AtomicReference<Disposable> implements Observer<Object> {
        private static final long serialVersionUID = 3254781284376480842L;

        public InnerRepeatObserver() {
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            ObservableRetryWhen$RepeatWhenObserver observableRetryWhen$RepeatWhenObserver = ObservableRetryWhen$RepeatWhenObserver.this;
            DisposableHelper.a(observableRetryWhen$RepeatWhenObserver.upstream);
            ux2.t(observableRetryWhen$RepeatWhenObserver.downstream, observableRetryWhen$RepeatWhenObserver, observableRetryWhen$RepeatWhenObserver.error);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            ObservableRetryWhen$RepeatWhenObserver observableRetryWhen$RepeatWhenObserver = ObservableRetryWhen$RepeatWhenObserver.this;
            DisposableHelper.a(observableRetryWhen$RepeatWhenObserver.upstream);
            ux2.u(observableRetryWhen$RepeatWhenObserver.downstream, th, observableRetryWhen$RepeatWhenObserver, observableRetryWhen$RepeatWhenObserver.error);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(Object obj) {
            ObservableRetryWhen$RepeatWhenObserver.this.a();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableRetryWhen$RepeatWhenObserver(Observer observer, io.reactivex.rxjava3.subjects.e eVar, ObservableSource observableSource) {
        this.downstream = observer;
        this.signaller = eVar;
        this.source = observableSource;
    }

    public final void a() {
        if (this.wip.getAndIncrement() == 0) {
            while (!isDisposed()) {
                if (!this.active) {
                    this.active = true;
                    this.source.subscribe(this);
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        DisposableHelper.a(this.inner);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper.a(this.inner);
        ux2.t(this.downstream, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.c(this.upstream, null);
        this.active = false;
        this.signaller.onNext(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        ux2.v(this.downstream, obj, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.c(this.upstream, disposable);
    }
}
