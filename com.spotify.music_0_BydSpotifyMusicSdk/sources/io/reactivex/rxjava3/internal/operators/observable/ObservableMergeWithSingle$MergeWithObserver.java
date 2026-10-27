package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableMergeWithSingle$MergeWithObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -4592979584110982903L;
    volatile boolean disposed;
    final Observer<? super T> downstream;
    volatile boolean mainDone;
    volatile int otherState;
    volatile io.reactivex.rxjava3.operators.f queue;
    T singleItem;
    final AtomicReference<Disposable> mainDisposable = new AtomicReference<>();
    final OtherObserver<T> otherObserver = new OtherObserver<>(this);
    final AtomicThrowable errors = new AtomicThrowable();

    public static final class OtherObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
        private static final long serialVersionUID = -2935427570954647017L;
        final ObservableMergeWithSingle$MergeWithObserver<T> parent;

        public OtherObserver(ObservableMergeWithSingle$MergeWithObserver observableMergeWithSingle$MergeWithObserver) {
            this.parent = observableMergeWithSingle$MergeWithObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableMergeWithSingle$MergeWithObserver<T> observableMergeWithSingle$MergeWithObserver = this.parent;
            if (observableMergeWithSingle$MergeWithObserver.errors.a(th)) {
                DisposableHelper.a(observableMergeWithSingle$MergeWithObserver.mainDisposable);
                if (observableMergeWithSingle$MergeWithObserver.getAndIncrement() == 0) {
                    observableMergeWithSingle$MergeWithObserver.a();
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            ObservableMergeWithSingle$MergeWithObserver<T> observableMergeWithSingle$MergeWithObserver = this.parent;
            if (observableMergeWithSingle$MergeWithObserver.compareAndSet(0, 1)) {
                observableMergeWithSingle$MergeWithObserver.downstream.onNext(obj);
                observableMergeWithSingle$MergeWithObserver.otherState = 2;
            } else {
                observableMergeWithSingle$MergeWithObserver.singleItem = obj;
                observableMergeWithSingle$MergeWithObserver.otherState = 1;
                if (observableMergeWithSingle$MergeWithObserver.getAndIncrement() != 0) {
                    return;
                }
            }
            observableMergeWithSingle$MergeWithObserver.a();
        }
    }

    public ObservableMergeWithSingle$MergeWithObserver(Observer observer) {
        this.downstream = observer;
    }

    public final void a() {
        Observer<? super T> observer = this.downstream;
        int iAddAndGet = 1;
        while (!this.disposed) {
            if (this.errors.get() != null) {
                this.singleItem = null;
                this.queue = null;
                this.errors.d(observer);
                return;
            }
            int i = this.otherState;
            if (i == 1) {
                T t = this.singleItem;
                this.singleItem = null;
                this.otherState = 2;
                observer.onNext(t);
                i = 2;
            }
            boolean z = this.mainDone;
            io.reactivex.rxjava3.operators.f fVar = this.queue;
            Object objPoll = fVar != null ? fVar.poll() : null;
            boolean z2 = objPoll == null;
            if (z && z2 && i == 2) {
                this.queue = null;
                observer.onComplete();
                return;
            } else if (z2) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                observer.onNext(objPoll);
            }
        }
        this.singleItem = null;
        this.queue = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        DisposableHelper.a(this.mainDisposable);
        DisposableHelper.a(this.otherObserver);
        this.errors.b();
        if (getAndIncrement() == 0) {
            this.queue = null;
            this.singleItem = null;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.mainDisposable.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.mainDone = true;
        if (getAndIncrement() == 0) {
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            DisposableHelper.a(this.otherObserver);
            if (getAndIncrement() == 0) {
                a();
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (compareAndSet(0, 1)) {
            this.downstream.onNext(obj);
            if (decrementAndGet() == 0) {
                return;
            }
        } else {
            io.reactivex.rxjava3.operators.f hVar = this.queue;
            if (hVar == null) {
                hVar = new io.reactivex.rxjava3.operators.h(Observable.bufferSize());
                this.queue = hVar;
            }
            hVar.offer(obj);
            if (getAndIncrement() != 0) {
                return;
            }
        }
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.mainDisposable, disposable);
    }
}
