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
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSwitchMap$SwitchMapObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    public static final ObservableSwitchMap$SwitchMapInnerObserver c;
    private static final long serialVersionUID = -3491074160481096299L;
    final int bufferSize;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final Observer<? super R> downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    volatile long unique;
    Disposable upstream;
    final AtomicReference<ObservableSwitchMap$SwitchMapInnerObserver<T, R>> active = new AtomicReference<>();
    final AtomicThrowable errors = new AtomicThrowable();

    static {
        ObservableSwitchMap$SwitchMapInnerObserver observableSwitchMap$SwitchMapInnerObserver = new ObservableSwitchMap$SwitchMapInnerObserver(null, -1L, 1);
        c = observableSwitchMap$SwitchMapInnerObserver;
        DisposableHelper.a(observableSwitchMap$SwitchMapInnerObserver);
    }

    public ObservableSwitchMap$SwitchMapObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.bufferSize = i;
        this.delayErrors = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x000f, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchMap$SwitchMapObserver.a():void");
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.dispose();
        ObservableSwitchMap$SwitchMapInnerObserver<T, R> andSet = this.active.getAndSet(c);
        if (andSet != null) {
            DisposableHelper.a(andSet);
        }
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ObservableSwitchMap$SwitchMapInnerObserver<T, R> andSet;
        if (!this.done) {
            AtomicThrowable atomicThrowable = this.errors;
            atomicThrowable.getClass();
            if (io.reactivex.rxjava3.internal.util.c.a(atomicThrowable, th)) {
                if (!this.delayErrors && (andSet = this.active.getAndSet(c)) != null) {
                    DisposableHelper.a(andSet);
                }
                this.done = true;
                a();
                return;
            }
        }
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j = this.unique + 1;
        this.unique = j;
        ObservableSwitchMap$SwitchMapInnerObserver<T, R> observableSwitchMap$SwitchMapInnerObserver = this.active.get();
        if (observableSwitchMap$SwitchMapInnerObserver != null) {
            DisposableHelper.a(observableSwitchMap$SwitchMapInnerObserver);
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The ObservableSource returned is null");
            ObservableSource observableSource = (ObservableSource) objApply;
            ObservableSwitchMap$SwitchMapInnerObserver<T, R> observableSwitchMap$SwitchMapInnerObserver2 = new ObservableSwitchMap$SwitchMapInnerObserver<>(this, j, this.bufferSize);
            while (true) {
                ObservableSwitchMap$SwitchMapInnerObserver<T, R> observableSwitchMap$SwitchMapInnerObserver3 = this.active.get();
                if (observableSwitchMap$SwitchMapInnerObserver3 == c) {
                    return;
                }
                AtomicReference<ObservableSwitchMap$SwitchMapInnerObserver<T, R>> atomicReference = this.active;
                while (!atomicReference.compareAndSet(observableSwitchMap$SwitchMapInnerObserver3, observableSwitchMap$SwitchMapInnerObserver2)) {
                    if (atomicReference.get() != observableSwitchMap$SwitchMapInnerObserver3) {
                        break;
                    }
                }
                observableSource.subscribe(observableSwitchMap$SwitchMapInnerObserver2);
                return;
            }
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
