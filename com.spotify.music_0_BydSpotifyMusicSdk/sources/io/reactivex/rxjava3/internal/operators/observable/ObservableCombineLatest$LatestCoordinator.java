package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCombineLatest$LatestCoordinator<T, R> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = 8567835998786448817L;
    int active;
    volatile boolean cancelled;
    final io.reactivex.rxjava3.functions.o combiner;
    int complete;
    final boolean delayError;
    volatile boolean done;
    final Observer<? super R> downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    Object[] latest;
    final ObservableCombineLatest$CombinerObserver<T, R>[] observers;
    final io.reactivex.rxjava3.operators.h queue;

    public ObservableCombineLatest$LatestCoordinator(int i, int i2, Observer observer, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.downstream = observer;
        this.combiner = oVar;
        this.delayError = z;
        this.latest = new Object[i];
        ObservableCombineLatest$CombinerObserver<T, R>[] observableCombineLatest$CombinerObserverArr = new ObservableCombineLatest$CombinerObserver[i];
        for (int i3 = 0; i3 < i; i3++) {
            observableCombineLatest$CombinerObserverArr[i3] = new ObservableCombineLatest$CombinerObserver<>(this, i3);
        }
        this.observers = observableCombineLatest$CombinerObserverArr;
        this.queue = new io.reactivex.rxjava3.operators.h(i2);
    }

    public final void a() {
        for (ObservableCombineLatest$CombinerObserver<T, R> observableCombineLatest$CombinerObserver : this.observers) {
            observableCombineLatest$CombinerObserver.getClass();
            DisposableHelper.a(observableCombineLatest$CombinerObserver);
        }
    }

    public final void b(io.reactivex.rxjava3.operators.h hVar) {
        synchronized (this) {
            this.latest = null;
        }
        hVar.clear();
    }

    public final void c() {
        if (getAndIncrement() != 0) {
            return;
        }
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        Observer<? super R> observer = this.downstream;
        boolean z = this.delayError;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (!z && this.errors.get() != null) {
                a();
                b(hVar);
                this.errors.d(observer);
                return;
            }
            boolean z2 = this.done;
            Object[] objArr = (Object[]) hVar.poll();
            boolean z3 = objArr == null;
            if (z2 && z3) {
                b(hVar);
                this.errors.d(observer);
                return;
            }
            if (z3) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                try {
                    Object objApply = this.combiner.apply(objArr);
                    Objects.requireNonNull(objApply, "The combiner returned a null value");
                    observer.onNext(objApply);
                } catch (Throwable th) {
                    he1.R(th);
                    this.errors.a(th);
                    a();
                    b(hVar);
                    this.errors.d(observer);
                    return;
                }
            }
        }
        b(hVar);
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        a();
        c();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    public void subscribe(ObservableSource<? extends T>[] observableSourceArr) {
        ObservableCombineLatest$CombinerObserver<T, R>[] observableCombineLatest$CombinerObserverArr = this.observers;
        int length = observableCombineLatest$CombinerObserverArr.length;
        this.downstream.onSubscribe(this);
        for (int i = 0; i < length && !this.done && !this.cancelled; i++) {
            observableSourceArr[i].subscribe(observableCombineLatest$CombinerObserverArr[i]);
        }
    }
}
