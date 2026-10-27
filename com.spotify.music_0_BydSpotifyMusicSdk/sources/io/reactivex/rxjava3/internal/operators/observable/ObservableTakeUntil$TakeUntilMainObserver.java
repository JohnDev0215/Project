package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
final class ObservableTakeUntil$TakeUntilMainObserver<T, U> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = 1418547743690811973L;
    final Observer<? super T> downstream;
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    final ObservableTakeUntil$TakeUntilMainObserver<T, U>.OtherObserver otherObserver = new OtherObserver();
    final AtomicThrowable error = new AtomicThrowable();

    public final class OtherObserver extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -8693423678067375039L;

        public OtherObserver() {
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            ObservableTakeUntil$TakeUntilMainObserver observableTakeUntil$TakeUntilMainObserver = ObservableTakeUntil$TakeUntilMainObserver.this;
            DisposableHelper.a(observableTakeUntil$TakeUntilMainObserver.upstream);
            ux2.t(observableTakeUntil$TakeUntilMainObserver.downstream, observableTakeUntil$TakeUntilMainObserver, observableTakeUntil$TakeUntilMainObserver.error);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            ObservableTakeUntil$TakeUntilMainObserver observableTakeUntil$TakeUntilMainObserver = ObservableTakeUntil$TakeUntilMainObserver.this;
            DisposableHelper.a(observableTakeUntil$TakeUntilMainObserver.upstream);
            ux2.u(observableTakeUntil$TakeUntilMainObserver.downstream, th, observableTakeUntil$TakeUntilMainObserver, observableTakeUntil$TakeUntilMainObserver.error);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(Object obj) {
            DisposableHelper.a(this);
            ObservableTakeUntil$TakeUntilMainObserver observableTakeUntil$TakeUntilMainObserver = ObservableTakeUntil$TakeUntilMainObserver.this;
            DisposableHelper.a(observableTakeUntil$TakeUntilMainObserver.upstream);
            ux2.t(observableTakeUntil$TakeUntilMainObserver.downstream, observableTakeUntil$TakeUntilMainObserver, observableTakeUntil$TakeUntilMainObserver.error);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableTakeUntil$TakeUntilMainObserver(Observer observer) {
        this.downstream = observer;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        DisposableHelper.a(this.otherObserver);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        DisposableHelper.a(this.otherObserver);
        ux2.t(this.downstream, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        DisposableHelper.a(this.otherObserver);
        ux2.u(this.downstream, th, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        ux2.v(this.downstream, obj, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }
}
