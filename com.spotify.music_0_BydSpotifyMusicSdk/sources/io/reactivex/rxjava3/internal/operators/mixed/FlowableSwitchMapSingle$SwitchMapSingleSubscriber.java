package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableSwitchMapSingle$SwitchMapSingleSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, tg2 {
    public static final SwitchMapSingleObserver c = new SwitchMapSingleObserver(null);
    private static final long serialVersionUID = -5402190102429853762L;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final rg2 downstream;
    long emitted;
    final o mapper;
    tg2 upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<SwitchMapSingleObserver<R>> inner = new AtomicReference<>();

    public static final class SwitchMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
        private static final long serialVersionUID = 8042919737683345351L;
        volatile R item;
        final FlowableSwitchMapSingle$SwitchMapSingleSubscriber<?, R> parent;

        public SwitchMapSingleObserver(FlowableSwitchMapSingle$SwitchMapSingleSubscriber flowableSwitchMapSingle$SwitchMapSingleSubscriber) {
            this.parent = flowableSwitchMapSingle$SwitchMapSingleSubscriber;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            FlowableSwitchMapSingle$SwitchMapSingleSubscriber<?, R> flowableSwitchMapSingle$SwitchMapSingleSubscriber = this.parent;
            AtomicReference<SwitchMapSingleObserver<R>> atomicReference = flowableSwitchMapSingle$SwitchMapSingleSubscriber.inner;
            while (!atomicReference.compareAndSet(this, null)) {
                if (atomicReference.get() != this) {
                    RxJavaPlugins.b(th);
                    return;
                }
            }
            if (flowableSwitchMapSingle$SwitchMapSingleSubscriber.errors.a(th)) {
                if (!flowableSwitchMapSingle$SwitchMapSingleSubscriber.delayErrors) {
                    flowableSwitchMapSingle$SwitchMapSingleSubscriber.upstream.cancel();
                    flowableSwitchMapSingle$SwitchMapSingleSubscriber.b();
                }
                flowableSwitchMapSingle$SwitchMapSingleSubscriber.c();
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
            this.parent.c();
        }
    }

    public FlowableSwitchMapSingle$SwitchMapSingleSubscriber(rg2 rg2Var, o oVar, boolean z) {
        this.downstream = rg2Var;
        this.mapper = oVar;
        this.delayErrors = z;
    }

    @Override // p.tg2
    public final void a(long j) {
        ci1.d(this.requested, j);
        c();
    }

    public final void b() {
        AtomicReference<SwitchMapSingleObserver<R>> atomicReference = this.inner;
        SwitchMapSingleObserver<R> switchMapSingleObserver = c;
        SwitchMapSingleObserver<R> andSet = atomicReference.getAndSet(switchMapSingleObserver);
        if (andSet == null || andSet == switchMapSingleObserver) {
            return;
        }
        DisposableHelper.a(andSet);
    }

    public final void c() {
        if (getAndIncrement() != 0) {
            return;
        }
        rg2 rg2Var = this.downstream;
        AtomicThrowable atomicThrowable = this.errors;
        AtomicReference<SwitchMapSingleObserver<R>> atomicReference = this.inner;
        AtomicLong atomicLong = this.requested;
        long j = this.emitted;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (atomicThrowable.get() != null && !this.delayErrors) {
                atomicThrowable.e(rg2Var);
                return;
            }
            boolean z = this.done;
            SwitchMapSingleObserver<R> switchMapSingleObserver = atomicReference.get();
            boolean z2 = switchMapSingleObserver == null;
            if (z && z2) {
                atomicThrowable.e(rg2Var);
                return;
            }
            if (z2 || switchMapSingleObserver.item == null || j == atomicLong.get()) {
                this.emitted = j;
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                while (!atomicReference.compareAndSet(switchMapSingleObserver, null) && atomicReference.get() == switchMapSingleObserver) {
                }
                rg2Var.onNext(switchMapSingleObserver.item);
                j++;
            }
        }
    }

    @Override // p.tg2
    public final void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        b();
        this.errors.b();
    }

    @Override // p.rg2
    public final void onComplete() {
        this.done = true;
        c();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            if (!this.delayErrors) {
                b();
            }
            this.done = true;
            c();
        }
    }

    @Override // p.rg2
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
            this.upstream.cancel();
            this.inner.getAndSet(switchMapSingleObserver);
            onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.upstream, tg2Var)) {
            this.upstream = tg2Var;
            this.downstream.onSubscribe(this);
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}
