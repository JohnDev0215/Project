package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableSwitchMapSingle$SwitchMapSingleMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    public static final SwitchMapSingleObserver c = new SwitchMapSingleObserver(null);
    private static final long serialVersionUID = -5402190102429853762L;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final Observer<? super R> downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicReference<SwitchMapSingleObserver<R>> inner = new AtomicReference<>();
    final o mapper;
    Disposable upstream;

    public static final class SwitchMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
        private static final long serialVersionUID = 8042919737683345351L;
        volatile R item;
        final ObservableSwitchMapSingle$SwitchMapSingleMainObserver<?, R> parent;

        public SwitchMapSingleObserver(ObservableSwitchMapSingle$SwitchMapSingleMainObserver observableSwitchMapSingle$SwitchMapSingleMainObserver) {
            this.parent = observableSwitchMapSingle$SwitchMapSingleMainObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableSwitchMapSingle$SwitchMapSingleMainObserver<?, R> observableSwitchMapSingle$SwitchMapSingleMainObserver = this.parent;
            AtomicReference<SwitchMapSingleObserver<R>> atomicReference = observableSwitchMapSingle$SwitchMapSingleMainObserver.inner;
            while (!atomicReference.compareAndSet(this, null)) {
                if (atomicReference.get() != this) {
                    RxJavaPlugins.b(th);
                    return;
                }
            }
            if (observableSwitchMapSingle$SwitchMapSingleMainObserver.errors.a(th)) {
                if (!observableSwitchMapSingle$SwitchMapSingleMainObserver.delayErrors) {
                    observableSwitchMapSingle$SwitchMapSingleMainObserver.upstream.dispose();
                    observableSwitchMapSingle$SwitchMapSingleMainObserver.a();
                }
                observableSwitchMapSingle$SwitchMapSingleMainObserver.b();
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            this.item = obj;
            this.parent.b();
        }
    }

    public ObservableSwitchMapSingle$SwitchMapSingleMainObserver(Observer observer, o oVar, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.delayErrors = z;
    }

    public final void a() {
        AtomicReference<SwitchMapSingleObserver<R>> atomicReference = this.inner;
        SwitchMapSingleObserver<R> switchMapSingleObserver = c;
        SwitchMapSingleObserver<R> andSet = atomicReference.getAndSet(switchMapSingleObserver);
        if (andSet == null || andSet == switchMapSingleObserver) {
            return;
        }
        DisposableHelper.a(andSet);
    }

    public final void b() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super R> observer = this.downstream;
        AtomicThrowable atomicThrowable = this.errors;
        AtomicReference<SwitchMapSingleObserver<R>> atomicReference = this.inner;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (atomicThrowable.get() != null && !this.delayErrors) {
                atomicThrowable.d(observer);
                return;
            }
            boolean z = this.done;
            SwitchMapSingleObserver<R> switchMapSingleObserver = atomicReference.get();
            boolean z2 = switchMapSingleObserver == null;
            if (z && z2) {
                atomicThrowable.d(observer);
                return;
            }
            if (z2 || switchMapSingleObserver.item == null) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                while (!atomicReference.compareAndSet(switchMapSingleObserver, null) && atomicReference.get() == switchMapSingleObserver) {
                }
                observer.onNext(switchMapSingleObserver.item);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        a();
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (!this.delayErrors) {
                a();
            }
            this.done = true;
            b();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        SwitchMapSingleObserver<R> switchMapSingleObserver = c;
        SwitchMapSingleObserver<R> switchMapSingleObserver2 = this.inner.get();
        if (switchMapSingleObserver2 != null) {
            DisposableHelper.a(switchMapSingleObserver2);
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null SingleSource");
            SingleSource singleSource = (SingleSource) objApply;
            SwitchMapSingleObserver<R> switchMapSingleObserver3 = new SwitchMapSingleObserver<>(this);
            while (true) {
                SwitchMapSingleObserver<R> switchMapSingleObserver4 = this.inner.get();
                if (switchMapSingleObserver4 == switchMapSingleObserver) {
                    return;
                }
                AtomicReference<SwitchMapSingleObserver<R>> atomicReference = this.inner;
                while (!atomicReference.compareAndSet(switchMapSingleObserver4, switchMapSingleObserver3)) {
                    if (atomicReference.get() != switchMapSingleObserver4) {
                        break;
                    }
                }
                singleSource.subscribe(switchMapSingleObserver3);
                return;
            }
        } catch (Throwable th) {
            he1.R(th);
            this.upstream.dispose();
            this.inner.getAndSet(switchMapSingleObserver);
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
