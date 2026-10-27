package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableBufferBoundary$BufferBoundaryObserver<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -8466418554264089604L;
    final io.reactivex.rxjava3.functions.o bufferClose;
    final ObservableSource<? extends Open> bufferOpen;
    final io.reactivex.rxjava3.functions.q bufferSupplier;
    volatile boolean cancelled;
    volatile boolean done;
    final Observer<? super C> downstream;
    long index;
    final io.reactivex.rxjava3.operators.h queue = new io.reactivex.rxjava3.operators.h(Observable.bufferSize());
    final io.reactivex.rxjava3.disposables.a observers = new io.reactivex.rxjava3.disposables.a();
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    Map<Long, C> buffers = new LinkedHashMap();
    final AtomicThrowable errors = new AtomicThrowable();

    public static final class BufferOpenObserver<Open> extends AtomicReference<Disposable> implements Observer<Open>, Disposable {
        private static final long serialVersionUID = -8498650778633225126L;
        final ObservableBufferBoundary$BufferBoundaryObserver<?, ?, Open, ?> parent;

        public BufferOpenObserver(ObservableBufferBoundary$BufferBoundaryObserver observableBufferBoundary$BufferBoundaryObserver) {
            this.parent = observableBufferBoundary$BufferBoundaryObserver;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.a(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.c;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            lazySet(DisposableHelper.c);
            ObservableBufferBoundary$BufferBoundaryObserver<?, ?, Open, ?> observableBufferBoundary$BufferBoundaryObserver = this.parent;
            observableBufferBoundary$BufferBoundaryObserver.observers.b(this);
            if (observableBufferBoundary$BufferBoundaryObserver.observers.g() == 0) {
                DisposableHelper.a(observableBufferBoundary$BufferBoundaryObserver.upstream);
                observableBufferBoundary$BufferBoundaryObserver.done = true;
                observableBufferBoundary$BufferBoundaryObserver.b();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            lazySet(DisposableHelper.c);
            ObservableBufferBoundary$BufferBoundaryObserver<?, ?, Open, ?> observableBufferBoundary$BufferBoundaryObserver = this.parent;
            DisposableHelper.a(observableBufferBoundary$BufferBoundaryObserver.upstream);
            observableBufferBoundary$BufferBoundaryObserver.observers.b(this);
            observableBufferBoundary$BufferBoundaryObserver.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(Object obj) {
            ObservableBufferBoundary$BufferBoundaryObserver<?, ?, Open, ?> observableBufferBoundary$BufferBoundaryObserver = this.parent;
            observableBufferBoundary$BufferBoundaryObserver.getClass();
            try {
                Object obj2 = observableBufferBoundary$BufferBoundaryObserver.bufferSupplier.get();
                Objects.requireNonNull(obj2, "The bufferSupplier returned a null Collection");
                Collection collection = (Collection) obj2;
                Object objApply = observableBufferBoundary$BufferBoundaryObserver.bufferClose.apply(obj);
                Objects.requireNonNull(objApply, "The bufferClose returned a null ObservableSource");
                ObservableSource observableSource = (ObservableSource) objApply;
                long j = observableBufferBoundary$BufferBoundaryObserver.index;
                observableBufferBoundary$BufferBoundaryObserver.index = 1 + j;
                synchronized (observableBufferBoundary$BufferBoundaryObserver) {
                    try {
                        Map<Long, C> map = observableBufferBoundary$BufferBoundaryObserver.buffers;
                        if (map != null) {
                            map.put(Long.valueOf(j), (C) collection);
                            ObservableBufferBoundary$BufferCloseObserver observableBufferBoundary$BufferCloseObserver = new ObservableBufferBoundary$BufferCloseObserver(observableBufferBoundary$BufferBoundaryObserver, j);
                            observableBufferBoundary$BufferBoundaryObserver.observers.c(observableBufferBoundary$BufferCloseObserver);
                            observableSource.subscribe(observableBufferBoundary$BufferCloseObserver);
                        }
                    } finally {
                    }
                }
            } catch (Throwable th) {
                he1.R(th);
                DisposableHelper.a(observableBufferBoundary$BufferBoundaryObserver.upstream);
                observableBufferBoundary$BufferBoundaryObserver.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.f(this, disposable);
        }
    }

    public ObservableBufferBoundary$BufferBoundaryObserver(Observer observer, ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.q qVar) {
        this.downstream = observer;
        this.bufferSupplier = qVar;
        this.bufferOpen = observableSource;
        this.bufferClose = oVar;
    }

    public final void a(ObservableBufferBoundary$BufferCloseObserver observableBufferBoundary$BufferCloseObserver, long j) {
        boolean z;
        this.observers.b(observableBufferBoundary$BufferCloseObserver);
        if (this.observers.g() == 0) {
            DisposableHelper.a(this.upstream);
            z = true;
        } else {
            z = false;
        }
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (map == null) {
                    return;
                }
                this.queue.offer(map.remove(Long.valueOf(j)));
                if (z) {
                    this.done = true;
                }
                b();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super C> observer = this.downstream;
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        int iAddAndGet = 1;
        while (!this.cancelled) {
            boolean z = this.done;
            if (z && this.errors.get() != null) {
                hVar.clear();
                this.errors.d(observer);
                return;
            }
            Collection collection = (Collection) hVar.poll();
            boolean z2 = collection == null;
            if (z && z2) {
                observer.onComplete();
                return;
            } else if (z2) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                observer.onNext(collection);
            }
        }
        hVar.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (DisposableHelper.a(this.upstream)) {
            this.cancelled = true;
            this.observers.dispose();
            synchronized (this) {
                this.buffers = null;
            }
            if (getAndIncrement() != 0) {
                this.queue.clear();
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(this.upstream.get());
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.observers.dispose();
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    this.queue.offer(it.next());
                }
                this.buffers = null;
                this.done = true;
                b();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            this.observers.dispose();
            synchronized (this) {
                this.buffers = null;
            }
            this.done = true;
            b();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().add(obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.f(this.upstream, disposable)) {
            BufferOpenObserver bufferOpenObserver = new BufferOpenObserver(this);
            this.observers.c(bufferOpenObserver);
            this.bufferOpen.subscribe(bufferOpenObserver);
        }
    }
}
