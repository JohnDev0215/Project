package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableMergeWithMaybe$MergeWithObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
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

    public static final class OtherObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = -2935427570954647017L;
        final ObservableMergeWithMaybe$MergeWithObserver<T> parent;

        public OtherObserver(ObservableMergeWithMaybe$MergeWithObserver observableMergeWithMaybe$MergeWithObserver) {
            this.parent = observableMergeWithMaybe$MergeWithObserver;
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver
        public final void onComplete() {
            ObservableMergeWithMaybe$MergeWithObserver<T> observableMergeWithMaybe$MergeWithObserver = this.parent;
            observableMergeWithMaybe$MergeWithObserver.otherState = 2;
            if (observableMergeWithMaybe$MergeWithObserver.getAndIncrement() == 0) {
                observableMergeWithMaybe$MergeWithObserver.a();
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableMergeWithMaybe$MergeWithObserver<T> observableMergeWithMaybe$MergeWithObserver = this.parent;
            if (observableMergeWithMaybe$MergeWithObserver.errors.a(th)) {
                DisposableHelper.a(observableMergeWithMaybe$MergeWithObserver.mainDisposable);
                if (observableMergeWithMaybe$MergeWithObserver.getAndIncrement() == 0) {
                    observableMergeWithMaybe$MergeWithObserver.a();
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            ObservableMergeWithMaybe$MergeWithObserver<T> observableMergeWithMaybe$MergeWithObserver = this.parent;
            if (observableMergeWithMaybe$MergeWithObserver.compareAndSet(0, 1)) {
                observableMergeWithMaybe$MergeWithObserver.downstream.onNext(obj);
                observableMergeWithMaybe$MergeWithObserver.otherState = 2;
            } else {
                observableMergeWithMaybe$MergeWithObserver.singleItem = obj;
                observableMergeWithMaybe$MergeWithObserver.otherState = 1;
                if (observableMergeWithMaybe$MergeWithObserver.getAndIncrement() != 0) {
                    return;
                }
            }
            observableMergeWithMaybe$MergeWithObserver.a();
        }
    }

    public ObservableMergeWithMaybe$MergeWithObserver(Observer observer) {
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
