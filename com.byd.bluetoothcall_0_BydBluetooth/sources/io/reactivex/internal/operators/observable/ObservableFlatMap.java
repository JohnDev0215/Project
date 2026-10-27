package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.m.f;
import d.a.n.b.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableFlatMap<T, U> extends a<T, U> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final f<? super T, ? extends h<? extends U>> f4279c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f4280d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f4281e;
    final int f;

    static final class InnerObserver<T, U> extends AtomicReference<io.reactivex.disposables.b> implements i<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile e<U> queue;

        InnerObserver(MergeObserver<T, U> mergeObserver, long j) {
            this.id = j;
            this.parent = mergeObserver;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // d.a.i
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (!this.parent.errors.addThrowable(th)) {
                d.a.p.a.l(th);
                return;
            }
            MergeObserver<T, U> mergeObserver = this.parent;
            if (!mergeObserver.delayErrors) {
                mergeObserver.disposeAll();
            }
            this.done = true;
            this.parent.drain();
        }

        @Override // d.a.i
        public void onNext(U u) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && (bVar instanceof d.a.n.b.a)) {
                d.a.n.b.a aVar = (d.a.n.b.a) bVar;
                int iRequestFusion = aVar.requestFusion(7);
                if (iRequestFusion == 1) {
                    this.fusionMode = iRequestFusion;
                    this.queue = aVar;
                    this.done = true;
                    this.parent.drain();
                    return;
                }
                if (iRequestFusion == 2) {
                    this.fusionMode = iRequestFusion;
                    this.queue = aVar;
                }
            }
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements io.reactivex.disposables.b, i<T> {
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final i<? super U> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final f<? super T, ? extends h<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile d.a.n.b.d<U> queue;
        Queue<h<? extends U>> sources;
        long uniqueId;
        io.reactivex.disposables.b upstream;
        int wip;
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];

        MergeObserver(i<? super U> iVar, f<? super T, ? extends h<? extends U>> fVar, boolean z, int i, int i2) {
            this.downstream = iVar;
            this.mapper = fVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            if (i != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(i);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        boolean addInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                if (innerObserverArr == CANCELLED) {
                    innerObserver.dispose();
                    return false;
                }
                int length = innerObserverArr.length;
                innerObserverArr2 = new InnerObserver[length + 1];
                System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                innerObserverArr2[length] = innerObserver;
            } while (!this.observers.compareAndSet(innerObserverArr, (InnerObserver<?, ?>[]) innerObserverArr2));
            return true;
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            Throwable thTerminate = this.errors.terminate();
            if (thTerminate != ExceptionHelper.f4356a) {
                this.downstream.onError(thTerminate);
            }
            return true;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            Throwable thTerminate;
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (!disposeAll() || (thTerminate = this.errors.terminate()) == null || thTerminate == ExceptionHelper.f4356a) {
                return;
            }
            d.a.p.a.l(thTerminate);
        }

        boolean disposeAll() {
            InnerObserver<?, ?>[] andSet;
            this.upstream.dispose();
            InnerObserver<?, ?>[] innerObserverArr = this.observers.get();
            InnerObserver<?, ?>[] innerObserverArr2 = CANCELLED;
            if (innerObserverArr == innerObserverArr2 || (andSet = this.observers.getAndSet(innerObserverArr2)) == innerObserverArr2) {
                return false;
            }
            for (InnerObserver<?, ?> innerObserver : andSet) {
                innerObserver.dispose();
            }
            return true;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drainLoop() {
            int size;
            i<? super U> iVar = this.downstream;
            int iAddAndGet = 1;
            while (!checkTerminate()) {
                d.a.n.b.d<U> dVar = this.queue;
                if (dVar != null) {
                    while (!checkTerminate()) {
                        U uPoll = dVar.poll();
                        if (uPoll != null) {
                            iVar.onNext(uPoll);
                        } else if (uPoll == null) {
                        }
                    }
                    return;
                }
                boolean z = this.done;
                d.a.n.b.d<U> dVar2 = this.queue;
                InnerObserver<?, ?>[] innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                boolean z2 = false;
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        size = this.sources.size();
                    }
                } else {
                    size = 0;
                }
                if (z && ((dVar2 == null || dVar2.isEmpty()) && length == 0 && size == 0)) {
                    Throwable thTerminate = this.errors.terminate();
                    if (thTerminate != ExceptionHelper.f4356a) {
                        if (thTerminate == null) {
                            iVar.onComplete();
                            return;
                        } else {
                            iVar.onError(thTerminate);
                            return;
                        }
                    }
                    return;
                }
                if (length != 0) {
                    long j = this.lastId;
                    int i = this.lastIndex;
                    if (length <= i || innerObserverArr[i].id != j) {
                        if (length <= i) {
                            i = 0;
                        }
                        for (int i2 = 0; i2 < length && innerObserverArr[i].id != j; i2++) {
                            i++;
                            if (i == length) {
                                i = 0;
                            }
                        }
                        this.lastIndex = i;
                        this.lastId = innerObserverArr[i].id;
                    }
                    int i3 = 0;
                    boolean z3 = false;
                    while (i3 < length) {
                        if (checkTerminate()) {
                            return;
                        }
                        InnerObserver<T, U> innerObserver = innerObserverArr[i];
                        while (!checkTerminate()) {
                            e<U> eVar = innerObserver.queue;
                            if (eVar != null) {
                                do {
                                    try {
                                        U uPoll2 = eVar.poll();
                                        if (uPoll2 != null) {
                                            iVar.onNext(uPoll2);
                                        } else if (uPoll2 == null) {
                                        }
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        innerObserver.dispose();
                                        this.errors.addThrowable(th);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                        removeInner(innerObserver);
                                        i3++;
                                        z3 = true;
                                    }
                                } while (!checkTerminate());
                                return;
                            }
                            boolean z4 = innerObserver.done;
                            e<U> eVar2 = innerObserver.queue;
                            if (z4 && (eVar2 == null || eVar2.isEmpty())) {
                                removeInner(innerObserver);
                                if (checkTerminate()) {
                                    return;
                                } else {
                                    z3 = true;
                                }
                            }
                            i++;
                            if (i == length) {
                                i = 0;
                            }
                            i3++;
                        }
                        return;
                    }
                    this.lastIndex = i;
                    this.lastId = innerObserverArr[i].id;
                    z2 = z3;
                }
                if (!z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        h<? extends U> hVarPoll = this.sources.poll();
                        if (hVarPoll == null) {
                            this.wip--;
                        } else {
                            subscribeInner(hVarPoll);
                        }
                    }
                } else {
                    continue;
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // d.a.i
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (this.done) {
                d.a.p.a.l(th);
            } else if (!this.errors.addThrowable(th)) {
                d.a.p.a.l(th);
            } else {
                this.done = true;
                drain();
            }
        }

        @Override // d.a.i
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                h<? extends U> hVar = (h) d.a.n.a.b.d(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        int i = this.wip;
                        if (i == this.maxConcurrency) {
                            this.sources.offer(hVar);
                            return;
                        }
                        this.wip = i + 1;
                    }
                }
                subscribeInner(hVar);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void removeInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver<?, ?>[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (innerObserverArr[i2] == innerObserver) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    innerObserverArr2 = EMPTY;
                } else {
                    InnerObserver<?, ?>[] innerObserverArr3 = new InnerObserver[length - 1];
                    System.arraycopy(innerObserverArr, 0, innerObserverArr3, 0, i);
                    System.arraycopy(innerObserverArr, i + 1, innerObserverArr3, i, (length - i) - 1);
                    innerObserverArr2 = innerObserverArr3;
                }
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
        }

        void subscribeInner(h<? extends U> hVar) {
            h<? extends U> hVarPoll;
            while (hVar instanceof Callable) {
                if (!tryEmitScalar((Callable) hVar) || this.maxConcurrency == Integer.MAX_VALUE) {
                    return;
                }
                boolean z = false;
                synchronized (this) {
                    hVarPoll = this.sources.poll();
                    if (hVarPoll == null) {
                        this.wip--;
                        z = true;
                    }
                }
                if (z) {
                    drain();
                    return;
                }
                hVar = hVarPoll;
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver<T, U> innerObserver = new InnerObserver<>(this, j);
            if (addInner(innerObserver)) {
                hVar.a(innerObserver);
            }
        }

        void tryEmit(U u, InnerObserver<T, U> innerObserver) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.downstream.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                e aVar = innerObserver.queue;
                if (aVar == null) {
                    aVar = new io.reactivex.internal.queue.a(this.bufferSize);
                    innerObserver.queue = aVar;
                }
                aVar.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        boolean tryEmitScalar(Callable<? extends U> callable) {
            try {
                U uCall = callable.call();
                if (uCall == null) {
                    return true;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.downstream.onNext(uCall);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                } else {
                    d.a.n.b.d<U> aVar = this.queue;
                    if (aVar == null) {
                        aVar = this.maxConcurrency == Integer.MAX_VALUE ? new io.reactivex.internal.queue.a<>(this.bufferSize) : new SpscArrayQueue<>(this.maxConcurrency);
                        this.queue = aVar;
                    }
                    if (!aVar.offer(uCall)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    }
                    if (getAndIncrement() != 0) {
                        return false;
                    }
                }
                drainLoop();
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.errors.addThrowable(th);
                drain();
                return true;
            }
        }
    }

    public ObservableFlatMap(h<T> hVar, f<? super T, ? extends h<? extends U>> fVar, boolean z, int i, int i2) {
        super(hVar);
        this.f4279c = fVar;
        this.f4280d = z;
        this.f4281e = i;
        this.f = i2;
    }

    @Override // d.a.e
    public void o(i<? super U> iVar) {
        if (ObservableScalarXMap.b(this.f4292b, iVar, this.f4279c)) {
            return;
        }
        this.f4292b.a(new MergeObserver(iVar, this.f4279c, this.f4280d, this.f4281e, this.f));
    }
}
