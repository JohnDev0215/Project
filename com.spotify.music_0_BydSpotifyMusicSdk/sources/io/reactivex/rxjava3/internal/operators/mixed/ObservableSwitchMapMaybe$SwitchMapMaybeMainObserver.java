package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observer;
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
final class ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    public static final SwitchMapMaybeObserver c = new SwitchMapMaybeObserver(null);
    private static final long serialVersionUID = -5402190102429853762L;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final Observer<? super R> downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicReference<SwitchMapMaybeObserver<R>> inner = new AtomicReference<>();
    final o mapper;
    Disposable upstream;

    public static final class SwitchMapMaybeObserver<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
        private static final long serialVersionUID = 8042919737683345351L;
        volatile R item;
        final ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver<?, R> parent;

        public SwitchMapMaybeObserver(ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver observableSwitchMapMaybe$SwitchMapMaybeMainObserver) {
            this.parent = observableSwitchMapMaybe$SwitchMapMaybeMainObserver;
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver
        public final void onComplete() {
            ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver<?, R> observableSwitchMapMaybe$SwitchMapMaybeMainObserver = this.parent;
            AtomicReference<SwitchMapMaybeObserver<R>> atomicReference = observableSwitchMapMaybe$SwitchMapMaybeMainObserver.inner;
            while (!atomicReference.compareAndSet(this, null)) {
                if (atomicReference.get() != this) {
                    return;
                }
            }
            observableSwitchMapMaybe$SwitchMapMaybeMainObserver.b();
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver<?, R> observableSwitchMapMaybe$SwitchMapMaybeMainObserver = this.parent;
            AtomicReference<SwitchMapMaybeObserver<R>> atomicReference = observableSwitchMapMaybe$SwitchMapMaybeMainObserver.inner;
            while (!atomicReference.compareAndSet(this, null)) {
                if (atomicReference.get() != this) {
                    RxJavaPlugins.b(th);
                    return;
                }
            }
            if (observableSwitchMapMaybe$SwitchMapMaybeMainObserver.errors.a(th)) {
                if (!observableSwitchMapMaybe$SwitchMapMaybeMainObserver.delayErrors) {
                    observableSwitchMapMaybe$SwitchMapMaybeMainObserver.upstream.dispose();
                    observableSwitchMapMaybe$SwitchMapMaybeMainObserver.a();
                }
                observableSwitchMapMaybe$SwitchMapMaybeMainObserver.b();
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            this.item = obj;
            this.parent.b();
        }
    }

    public ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver(Observer observer, o oVar, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.delayErrors = z;
    }

    public final void a() {
        AtomicReference<SwitchMapMaybeObserver<R>> atomicReference = this.inner;
        SwitchMapMaybeObserver<R> switchMapMaybeObserver = c;
        SwitchMapMaybeObserver<R> andSet = atomicReference.getAndSet(switchMapMaybeObserver);
        if (andSet == null || andSet == switchMapMaybeObserver) {
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
        AtomicReference<SwitchMapMaybeObserver<R>> atomicReference = this.inner;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (atomicThrowable.get() != null && !this.delayErrors) {
                atomicThrowable.d(observer);
                return;
            }
            boolean z = this.done;
            SwitchMapMaybeObserver<R> switchMapMaybeObserver = atomicReference.get();
            boolean z2 = switchMapMaybeObserver == null;
            if (z && z2) {
                atomicThrowable.d(observer);
                return;
            }
            if (z2 || switchMapMaybeObserver.item == null) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                while (!atomicReference.compareAndSet(switchMapMaybeObserver, null) && atomicReference.get() == switchMapMaybeObserver) {
                }
                observer.onNext(switchMapMaybeObserver.item);
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
        SwitchMapMaybeObserver<R> switchMapMaybeObserver = c;
        SwitchMapMaybeObserver<R> switchMapMaybeObserver2 = this.inner.get();
        if (switchMapMaybeObserver2 != null) {
            DisposableHelper.a(switchMapMaybeObserver2);
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null MaybeSource");
            MaybeSource maybeSource = (MaybeSource) objApply;
            SwitchMapMaybeObserver<R> switchMapMaybeObserver3 = new SwitchMapMaybeObserver<>(this);
            while (true) {
                SwitchMapMaybeObserver<R> switchMapMaybeObserver4 = this.inner.get();
                if (switchMapMaybeObserver4 == switchMapMaybeObserver) {
                    return;
                }
                AtomicReference<SwitchMapMaybeObserver<R>> atomicReference = this.inner;
                while (!atomicReference.compareAndSet(switchMapMaybeObserver4, switchMapMaybeObserver3)) {
                    if (atomicReference.get() != switchMapMaybeObserver4) {
                        break;
                    }
                }
                maybeSource.subscribe(switchMapMaybeObserver3);
                return;
            }
        } catch (Throwable th) {
            he1.R(th);
            this.upstream.dispose();
            this.inner.getAndSet(switchMapMaybeObserver);
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
