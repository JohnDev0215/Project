package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindowBoundarySelector$WindowBoundaryMainObserver<T, B, V> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = 8646217640096099753L;
    final int bufferSize;
    final io.reactivex.rxjava3.functions.o closingIndicator;
    final Observer<? super Observable<T>> downstream;
    long emitted;
    final ObservableSource<B> open;
    volatile boolean openDone;
    Disposable upstream;
    volatile boolean upstreamCanceled;
    volatile boolean upstreamDone;
    final io.reactivex.rxjava3.operators.f queue = new io.reactivex.rxjava3.internal.queue.a();
    final io.reactivex.rxjava3.disposables.a resources = new io.reactivex.rxjava3.disposables.a();
    final List<io.reactivex.rxjava3.subjects.f> windows = new ArrayList();
    final AtomicLong windowCount = new AtomicLong(1);
    final AtomicBoolean downstreamDisposed = new AtomicBoolean();
    final AtomicThrowable error = new AtomicThrowable();
    final WindowStartObserver<B> startObserver = new WindowStartObserver<>(this);
    final AtomicLong requested = new AtomicLong();

    public static final class WindowStartObserver<B> extends AtomicReference<Disposable> implements Observer<B> {
        private static final long serialVersionUID = -3326496781427702834L;
        final ObservableWindowBoundarySelector$WindowBoundaryMainObserver<?, B, ?> parent;

        public WindowStartObserver(ObservableWindowBoundarySelector$WindowBoundaryMainObserver observableWindowBoundarySelector$WindowBoundaryMainObserver) {
            this.parent = observableWindowBoundarySelector$WindowBoundaryMainObserver;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            ObservableWindowBoundarySelector$WindowBoundaryMainObserver<?, B, ?> observableWindowBoundarySelector$WindowBoundaryMainObserver = this.parent;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.openDone = true;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            ObservableWindowBoundarySelector$WindowBoundaryMainObserver<?, B, ?> observableWindowBoundarySelector$WindowBoundaryMainObserver = this.parent;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.upstream.dispose();
            observableWindowBoundarySelector$WindowBoundaryMainObserver.resources.dispose();
            if (observableWindowBoundarySelector$WindowBoundaryMainObserver.error.a(th)) {
                observableWindowBoundarySelector$WindowBoundaryMainObserver.upstreamDone = true;
                observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(Object obj) {
            ObservableWindowBoundarySelector$WindowBoundaryMainObserver<?, B, ?> observableWindowBoundarySelector$WindowBoundaryMainObserver = this.parent;
            observableWindowBoundarySelector$WindowBoundaryMainObserver.queue.offer(new n5(obj));
            observableWindowBoundarySelector$WindowBoundaryMainObserver.a();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableWindowBoundarySelector$WindowBoundaryMainObserver(Observer observer, ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, int i) {
        this.downstream = observer;
        this.open = observableSource;
        this.closingIndicator = oVar;
        this.bufferSize = i;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super Observable<T>> observer = this.downstream;
        io.reactivex.rxjava3.operators.f fVar = this.queue;
        List<io.reactivex.rxjava3.subjects.f> list = this.windows;
        int iAddAndGet = 1;
        while (true) {
            if (this.upstreamCanceled) {
                fVar.clear();
                list.clear();
            } else {
                boolean z = this.upstreamDone;
                Object objPoll = fVar.poll();
                boolean z2 = objPoll == null;
                if (z && (z2 || this.error.get() != null)) {
                    b(observer);
                    this.upstreamCanceled = true;
                } else if (z2) {
                    if (this.openDone && list.size() == 0) {
                        this.upstream.dispose();
                        WindowStartObserver<B> windowStartObserver = this.startObserver;
                        windowStartObserver.getClass();
                        DisposableHelper.a(windowStartObserver);
                        this.resources.dispose();
                        b(observer);
                        this.upstreamCanceled = true;
                    }
                } else if (objPoll instanceof n5) {
                    if (!this.downstreamDisposed.get()) {
                        try {
                            Object objApply = this.closingIndicator.apply(((n5) objPoll).a);
                            Objects.requireNonNull(objApply, "The closingIndicator returned a null ObservableSource");
                            ObservableSource observableSource = (ObservableSource) objApply;
                            this.windowCount.getAndIncrement();
                            io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.bufferSize, this);
                            m5 m5Var = new m5(this, fVarA);
                            observer.onNext(m5Var);
                            AtomicBoolean atomicBoolean = m5Var.l;
                            if (atomicBoolean.get() || !atomicBoolean.compareAndSet(false, true)) {
                                list.add(fVarA);
                                this.resources.c(m5Var);
                                observableSource.subscribe(m5Var);
                            } else {
                                fVarA.onComplete();
                            }
                        } catch (Throwable th) {
                            he1.R(th);
                            this.upstream.dispose();
                            WindowStartObserver<B> windowStartObserver2 = this.startObserver;
                            windowStartObserver2.getClass();
                            DisposableHelper.a(windowStartObserver2);
                            this.resources.dispose();
                            he1.R(th);
                            this.error.a(th);
                            this.upstreamDone = true;
                        }
                    }
                } else if (objPoll instanceof m5) {
                    io.reactivex.rxjava3.subjects.f fVar2 = ((m5) objPoll).e;
                    list.remove(fVar2);
                    this.resources.b((Disposable) objPoll);
                    fVar2.onComplete();
                } else {
                    Iterator<io.reactivex.rxjava3.subjects.f> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onNext(objPoll);
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    public final void b(Observer observer) {
        AtomicThrowable atomicThrowable = this.error;
        atomicThrowable.getClass();
        Throwable thD = io.reactivex.rxjava3.internal.util.c.d(atomicThrowable);
        if (thD == null) {
            Iterator<io.reactivex.rxjava3.subjects.f> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onComplete();
            }
            observer.onComplete();
            return;
        }
        if (thD != io.reactivex.rxjava3.internal.util.c.a) {
            Iterator<io.reactivex.rxjava3.subjects.f> it2 = this.windows.iterator();
            while (it2.hasNext()) {
                it2.next().onError(thD);
            }
            observer.onError(thD);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.downstreamDisposed.compareAndSet(false, true)) {
            if (this.windowCount.decrementAndGet() != 0) {
                WindowStartObserver<B> windowStartObserver = this.startObserver;
                windowStartObserver.getClass();
                DisposableHelper.a(windowStartObserver);
                return;
            }
            this.upstream.dispose();
            WindowStartObserver<B> windowStartObserver2 = this.startObserver;
            windowStartObserver2.getClass();
            DisposableHelper.a(windowStartObserver2);
            this.resources.dispose();
            this.error.b();
            this.upstreamCanceled = true;
            a();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.downstreamDisposed.get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        WindowStartObserver<B> windowStartObserver = this.startObserver;
        windowStartObserver.getClass();
        DisposableHelper.a(windowStartObserver);
        this.resources.dispose();
        this.upstreamDone = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        WindowStartObserver<B> windowStartObserver = this.startObserver;
        windowStartObserver.getClass();
        DisposableHelper.a(windowStartObserver);
        this.resources.dispose();
        if (this.error.a(th)) {
            this.upstreamDone = true;
            a();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.queue.offer(obj);
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
            this.open.subscribe(this.startObserver);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.windowCount.decrementAndGet() == 0) {
            this.upstream.dispose();
            WindowStartObserver<B> windowStartObserver = this.startObserver;
            windowStartObserver.getClass();
            DisposableHelper.a(windowStartObserver);
            this.resources.dispose();
            this.error.b();
            this.upstreamCanceled = true;
            a();
        }
    }
}
