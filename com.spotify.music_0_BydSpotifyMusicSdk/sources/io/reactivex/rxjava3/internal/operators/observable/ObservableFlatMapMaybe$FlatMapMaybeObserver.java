package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMapMaybe$FlatMapMaybeObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = 8600231336733376951L;
    volatile boolean cancelled;
    final boolean delayErrors;
    final Observer<? super R> downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    Disposable upstream;
    final io.reactivex.rxjava3.disposables.a set = new io.reactivex.rxjava3.disposables.a();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicInteger active = new AtomicInteger(1);
    final AtomicReference<io.reactivex.rxjava3.operators.h> queue = new AtomicReference<>();

    public final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
        private static final long serialVersionUID = -502562646270949838L;

        public InnerObserver() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.a(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.b(get());
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver
        public final void onComplete() {
            ObservableFlatMapMaybe$FlatMapMaybeObserver observableFlatMapMaybe$FlatMapMaybeObserver = ObservableFlatMapMaybe$FlatMapMaybeObserver.this;
            observableFlatMapMaybe$FlatMapMaybeObserver.set.b(this);
            if (observableFlatMapMaybe$FlatMapMaybeObserver.get() == 0) {
                if (observableFlatMapMaybe$FlatMapMaybeObserver.compareAndSet(0, 1)) {
                    boolean z = observableFlatMapMaybe$FlatMapMaybeObserver.active.decrementAndGet() == 0;
                    io.reactivex.rxjava3.operators.h hVar = observableFlatMapMaybe$FlatMapMaybeObserver.queue.get();
                    if (z && (hVar == null || hVar.isEmpty())) {
                        observableFlatMapMaybe$FlatMapMaybeObserver.errors.d(observableFlatMapMaybe$FlatMapMaybeObserver.downstream);
                        return;
                    } else {
                        if (observableFlatMapMaybe$FlatMapMaybeObserver.decrementAndGet() == 0) {
                            return;
                        }
                        observableFlatMapMaybe$FlatMapMaybeObserver.a();
                        return;
                    }
                }
            }
            observableFlatMapMaybe$FlatMapMaybeObserver.active.decrementAndGet();
            if (observableFlatMapMaybe$FlatMapMaybeObserver.getAndIncrement() == 0) {
                observableFlatMapMaybe$FlatMapMaybeObserver.a();
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableFlatMapMaybe$FlatMapMaybeObserver observableFlatMapMaybe$FlatMapMaybeObserver = ObservableFlatMapMaybe$FlatMapMaybeObserver.this;
            observableFlatMapMaybe$FlatMapMaybeObserver.set.b(this);
            if (observableFlatMapMaybe$FlatMapMaybeObserver.errors.a(th)) {
                if (!observableFlatMapMaybe$FlatMapMaybeObserver.delayErrors) {
                    observableFlatMapMaybe$FlatMapMaybeObserver.upstream.dispose();
                    observableFlatMapMaybe$FlatMapMaybeObserver.set.dispose();
                }
                observableFlatMapMaybe$FlatMapMaybeObserver.active.decrementAndGet();
                if (observableFlatMapMaybe$FlatMapMaybeObserver.getAndIncrement() == 0) {
                    observableFlatMapMaybe$FlatMapMaybeObserver.a();
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onSuccess(java.lang.Object r5) {
            /*
                r4 = this;
                io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMapMaybe$FlatMapMaybeObserver r0 = io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMapMaybe$FlatMapMaybeObserver.this
                io.reactivex.rxjava3.disposables.a r1 = r0.set
                r1.b(r4)
                int r1 = r0.get()
                if (r1 != 0) goto L44
                r1 = 0
                r2 = 1
                boolean r3 = r0.compareAndSet(r1, r2)
                if (r3 == 0) goto L44
                io.reactivex.rxjava3.core.Observer<? super R> r3 = r0.downstream
                r3.onNext(r5)
                java.util.concurrent.atomic.AtomicInteger r5 = r0.active
                int r5 = r5.decrementAndGet()
                if (r5 != 0) goto L23
                r1 = r2
            L23:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.operators.h> r5 = r0.queue
                java.lang.Object r5 = r5.get()
                io.reactivex.rxjava3.operators.h r5 = (io.reactivex.rxjava3.operators.h) r5
                if (r1 == 0) goto L3d
                if (r5 == 0) goto L35
                boolean r5 = r5.isEmpty()
                if (r5 == 0) goto L3d
            L35:
                io.reactivex.rxjava3.internal.util.AtomicThrowable r5 = r0.errors
                io.reactivex.rxjava3.core.Observer<? super R> r0 = r0.downstream
                r5.d(r0)
                goto L86
            L3d:
                int r5 = r0.decrementAndGet()
                if (r5 != 0) goto L83
                goto L86
            L44:
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.operators.h> r1 = r0.queue
                java.lang.Object r1 = r1.get()
                io.reactivex.rxjava3.operators.h r1 = (io.reactivex.rxjava3.operators.h) r1
                if (r1 == 0) goto L50
            L4e:
                r3 = r1
                goto L72
            L50:
                io.reactivex.rxjava3.operators.h r1 = new io.reactivex.rxjava3.operators.h
                int r2 = io.reactivex.rxjava3.core.Observable.bufferSize()
                r1.<init>(r2)
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.operators.h> r2 = r0.queue
            L5b:
                r3 = 0
                boolean r3 = r2.compareAndSet(r3, r1)
                if (r3 == 0) goto L63
                goto L4e
            L63:
                java.lang.Object r3 = r2.get()
                if (r3 == 0) goto L5b
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.operators.h> r1 = r0.queue
                java.lang.Object r1 = r1.get()
                io.reactivex.rxjava3.operators.h r1 = (io.reactivex.rxjava3.operators.h) r1
                goto L4e
            L72:
                monitor-enter(r3)
                r3.offer(r5)     // Catch: java.lang.Throwable -> L87
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L87
                java.util.concurrent.atomic.AtomicInteger r5 = r0.active
                r5.decrementAndGet()
                int r5 = r0.getAndIncrement()
                if (r5 == 0) goto L83
                goto L86
            L83:
                r0.a()
            L86:
                return
            L87:
                r5 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L87
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMapMaybe$FlatMapMaybeObserver.InnerObserver.onSuccess(java.lang.Object):void");
        }
    }

    public ObservableFlatMapMaybe$FlatMapMaybeObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.delayErrors = z;
    }

    public final void a() {
        Observer<? super R> observer = this.downstream;
        AtomicInteger atomicInteger = this.active;
        AtomicReference<io.reactivex.rxjava3.operators.h> atomicReference = this.queue;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            if (!this.delayErrors && this.errors.get() != null) {
                io.reactivex.rxjava3.operators.h hVar = this.queue.get();
                if (hVar != null) {
                    hVar.clear();
                }
                this.errors.d(observer);
                return;
            }
            boolean z = atomicInteger.get() == 0;
            io.reactivex.rxjava3.operators.h hVar2 = atomicReference.get();
            Object objPoll = hVar2 != null ? hVar2.poll() : null;
            boolean z2 = objPoll == null;
            if (z && z2) {
                this.errors.d(observer);
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
        io.reactivex.rxjava3.operators.h hVar3 = this.queue.get();
        if (hVar3 != null) {
            hVar3.clear();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        this.set.dispose();
        this.errors.b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.active.decrementAndGet();
        if (getAndIncrement() == 0) {
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.active.decrementAndGet();
        if (this.errors.a(th)) {
            if (!this.delayErrors) {
                this.set.dispose();
            }
            if (getAndIncrement() == 0) {
                a();
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null MaybeSource");
            MaybeSource maybeSource = (MaybeSource) objApply;
            this.active.getAndIncrement();
            InnerObserver innerObserver = new InnerObserver();
            if (this.cancelled || !this.set.c(innerObserver)) {
                return;
            }
            maybeSource.subscribe(innerObserver);
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
