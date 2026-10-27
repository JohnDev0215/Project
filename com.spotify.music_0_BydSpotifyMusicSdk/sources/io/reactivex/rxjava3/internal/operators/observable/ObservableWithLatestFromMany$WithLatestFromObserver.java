package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p.he1;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWithLatestFromMany$WithLatestFromObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = 1577321883966341961L;
    final io.reactivex.rxjava3.functions.o combiner;
    volatile boolean done;
    final Observer<? super R> downstream;
    final AtomicThrowable error;
    final ObservableWithLatestFromMany$WithLatestInnerObserver[] observers;
    final AtomicReference<Disposable> upstream;
    final AtomicReferenceArray<Object> values;

    public ObservableWithLatestFromMany$WithLatestFromObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i) {
        this.downstream = observer;
        this.combiner = oVar;
        ObservableWithLatestFromMany$WithLatestInnerObserver[] observableWithLatestFromMany$WithLatestInnerObserverArr = new ObservableWithLatestFromMany$WithLatestInnerObserver[i];
        for (int i2 = 0; i2 < i; i2++) {
            observableWithLatestFromMany$WithLatestInnerObserverArr[i2] = new ObservableWithLatestFromMany$WithLatestInnerObserver(this, i2);
        }
        this.observers = observableWithLatestFromMany$WithLatestInnerObserverArr;
        this.values = new AtomicReferenceArray<>(i);
        this.upstream = new AtomicReference<>();
        this.error = new AtomicThrowable();
    }

    public final void a(int i) {
        ObservableWithLatestFromMany$WithLatestInnerObserver[] observableWithLatestFromMany$WithLatestInnerObserverArr = this.observers;
        for (int i2 = 0; i2 < observableWithLatestFromMany$WithLatestInnerObserverArr.length; i2++) {
            if (i2 != i) {
                ObservableWithLatestFromMany$WithLatestInnerObserver observableWithLatestFromMany$WithLatestInnerObserver = observableWithLatestFromMany$WithLatestInnerObserverArr[i2];
                observableWithLatestFromMany$WithLatestInnerObserver.getClass();
                DisposableHelper.a(observableWithLatestFromMany$WithLatestInnerObserver);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this.upstream);
        for (ObservableWithLatestFromMany$WithLatestInnerObserver observableWithLatestFromMany$WithLatestInnerObserver : this.observers) {
            observableWithLatestFromMany$WithLatestInnerObserver.getClass();
            DisposableHelper.a(observableWithLatestFromMany$WithLatestInnerObserver);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        a(-1);
        ux2.t(this.downstream, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.done = true;
        a(-1);
        ux2.u(this.downstream, th, this, this.error);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        AtomicReferenceArray<Object> atomicReferenceArray = this.values;
        int length = atomicReferenceArray.length();
        Object[] objArr = new Object[length + 1];
        int i = 0;
        objArr[0] = obj;
        while (i < length) {
            Object obj2 = atomicReferenceArray.get(i);
            if (obj2 == null) {
                return;
            }
            i++;
            objArr[i] = obj2;
        }
        try {
            Object objApply = this.combiner.apply(objArr);
            Objects.requireNonNull(objApply, "combiner returned a null value");
            ux2.v(this.downstream, objApply, this, this.error);
        } catch (Throwable th) {
            he1.R(th);
            dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.f(this.upstream, disposable);
    }

    public void subscribe(ObservableSource<?>[] observableSourceArr, int i) {
        ObservableWithLatestFromMany$WithLatestInnerObserver[] observableWithLatestFromMany$WithLatestInnerObserverArr = this.observers;
        AtomicReference<Disposable> atomicReference = this.upstream;
        for (int i2 = 0; i2 < i && !DisposableHelper.b(atomicReference.get()) && !this.done; i2++) {
            observableSourceArr[i2].subscribe(observableWithLatestFromMany$WithLatestInnerObserverArr[i2]);
        }
    }
}
