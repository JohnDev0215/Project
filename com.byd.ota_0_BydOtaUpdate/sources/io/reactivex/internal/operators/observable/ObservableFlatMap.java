package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.m.b;
import d.a.o.e;
import d.a.p.c.d;
import d.a.p.e.b.a;
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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e<? super T, ? extends h<? extends U>> f5901d;
    public final boolean s;
    public final int x;
    public final int y;

    public static final class InnerObserver<T, U> extends AtomicReference<b> implements i<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        public volatile boolean done;
        public int fusionMode;
        public final long id;
        public final MergeObserver<T, U> parent;
        public volatile d.a.p.c.e<U> queue;

        public InnerObserver(MergeObserver<T, U> mergeObserver, long j) {
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
                d.a.r.a.o(th);
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
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && (bVar instanceof d.a.p.c.a)) {
                d.a.p.c.a aVar = (d.a.p.c.a) bVar;
                int iRequestFusion = aVar.requestFusion(3);
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

    public static final class MergeObserver<T, U> extends AtomicInteger implements b, i<T> {
        private static final long serialVersionUID = -2117620485640801370L;
        public final i<? super U> actual;
        public final int bufferSize;
        public volatile boolean cancelled;
        public final boolean delayErrors;
        public volatile boolean done;
        public final AtomicThrowable errors = new AtomicThrowable();
        public long lastId;
        public int lastIndex;
        public final e<? super T, ? extends h<? extends U>> mapper;
        public final int maxConcurrency;
        public final AtomicReference<InnerObserver<?, ?>[]> observers;
        public volatile d<U> queue;
        public b s;
        public Queue<h<? extends U>> sources;
        public long uniqueId;
        public int wip;
        public static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        public static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];

        public MergeObserver(i<? super U> iVar, e<? super T, ? extends h<? extends U>> eVar, boolean z, int i2, int i3) {
            this.actual = iVar;
            this.mapper = eVar;
            this.delayErrors = z;
            this.maxConcurrency = i2;
            this.bufferSize = i3;
            if (i2 != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(i2);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        public void addInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                if (innerObserverArr == CANCELLED) {
                    innerObserver.dispose();
                    return;
                }
                int length = innerObserverArr.length;
                innerObserverArr2 = new InnerObserver[length + 1];
                System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                innerObserverArr2[length] = innerObserver;
            } while (!this.observers.compareAndSet(innerObserverArr, (InnerObserver<?, ?>[]) innerObserverArr2));
        }

        public boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            this.actual.onError(this.errors.terminate());
            return true;
        }

        @Override // d.a.m.b
        public void dispose() {
            Throwable thTerminate;
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (!disposeAll() || (thTerminate = this.errors.terminate()) == null || thTerminate == ExceptionHelper.f5914a) {
                return;
            }
            d.a.r.a.o(thTerminate);
        }

        public boolean disposeAll() {
            InnerObserver<?, ?>[] andSet;
            this.s.dispose();
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

        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void drainLoop() {
            i<? super U> iVar = this.actual;
            int iAddAndGet = 1;
            while (!checkTerminate()) {
                d<U> dVar = this.queue;
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
                d<U> dVar2 = this.queue;
                InnerObserver<?, ?>[] innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                if (z && ((dVar2 == null || dVar2.isEmpty()) && length == 0)) {
                    if (this.errors.get() == null) {
                        iVar.onComplete();
                        return;
                    } else {
                        iVar.onError(this.errors.terminate());
                        return;
                    }
                }
                boolean z2 = false;
                if (length != 0) {
                    long j = this.lastId;
                    int i2 = this.lastIndex;
                    if (length <= i2 || innerObserverArr[i2].id != j) {
                        if (length <= i2) {
                            i2 = 0;
                        }
                        for (int i3 = 0; i3 < length && innerObserverArr[i2].id != j; i3++) {
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                        }
                        this.lastIndex = i2;
                        this.lastId = innerObserverArr[i2].id;
                    }
                    int i4 = 0;
                    boolean z3 = false;
                    while (i4 < length) {
                        if (checkTerminate()) {
                            return;
                        }
                        InnerObserver<T, U> innerObserver = innerObserverArr[i2];
                        while (!checkTerminate()) {
                            d.a.p.c.e<U> eVar = innerObserver.queue;
                            if (eVar != null) {
                                do {
                                    try {
                                        U uPoll2 = eVar.poll();
                                        if (uPoll2 != null) {
                                            iVar.onNext(uPoll2);
                                        } else if (uPoll2 == null) {
                                        }
                                    } catch (Throwable th) {
                                        d.a.n.a.a(th);
                                        innerObserver.dispose();
                                        this.errors.addThrowable(th);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                        removeInner(innerObserver);
                                        i4++;
                                        z3 = true;
                                    }
                                } while (!checkTerminate());
                                return;
                            }
                            boolean z4 = innerObserver.done;
                            d.a.p.c.e<U> eVar2 = innerObserver.queue;
                            if (z4 && (eVar2 == null || eVar2.isEmpty())) {
                                removeInner(innerObserver);
                                if (checkTerminate()) {
                                    return;
                                } else {
                                    z3 = true;
                                }
                            }
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                            i4++;
                        }
                        return;
                    }
                    this.lastIndex = i2;
                    this.lastId = innerObserverArr[i2].id;
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

        @Override // d.a.m.b
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
                d.a.r.a.o(th);
            } else if (!this.errors.addThrowable(th)) {
                d.a.r.a.o(th);
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
                h<? extends U> hVar = (h) d.a.p.b.b.d(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        int i2 = this.wip;
                        if (i2 == this.maxConcurrency) {
                            this.sources.offer(hVar);
                            return;
                        }
                        this.wip = i2 + 1;
                    }
                }
                subscribeInner(hVar);
            } catch (Throwable th) {
                d.a.n.a.a(th);
                this.s.dispose();
                onError(th);
            }
        }

        @Override // d.a.i
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.s, bVar)) {
                this.s = bVar;
                this.actual.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void removeInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver<?, ?>[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                if (length == 0) {
                    return;
                }
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    if (innerObserverArr[i3] == innerObserver) {
                        i2 = i3;
                        break;
                    }
                    i3++;
                }
                if (i2 < 0) {
                    return;
                }
                if (length == 1) {
                    innerObserverArr2 = EMPTY;
                } else {
                    InnerObserver<?, ?>[] innerObserverArr3 = new InnerObserver[length - 1];
                    System.arraycopy(innerObserverArr, 0, innerObserverArr3, 0, i2);
                    System.arraycopy(innerObserverArr, i2 + 1, innerObserverArr3, i2, (length - i2) - 1);
                    innerObserverArr2 = innerObserverArr3;
                }
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
        }

        public void subscribeInner(h<? extends U> hVar) {
            while (hVar instanceof Callable) {
                tryEmitScalar((Callable) hVar);
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    return;
                }
                synchronized (this) {
                    hVar = this.sources.poll();
                    if (hVar == null) {
                        this.wip--;
                        return;
                    }
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver<T, U> innerObserver = new InnerObserver<>(this, j);
            addInner(innerObserver);
            hVar.a(innerObserver);
        }

        public void tryEmit(U u, InnerObserver<T, U> innerObserver) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.actual.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                d.a.p.c.e aVar = innerObserver.queue;
                if (aVar == null) {
                    aVar = new d.a.p.f.a(this.bufferSize);
                    innerObserver.queue = aVar;
                }
                aVar.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void tryEmitScalar(Callable<? extends U> callable) {
            try {
                U uCall = callable.call();
                if (uCall == null) {
                    return;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.actual.onNext(uCall);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                } else {
                    d<U> aVar = this.queue;
                    if (aVar == null) {
                        aVar = this.maxConcurrency == Integer.MAX_VALUE ? new d.a.p.f.a<>(this.bufferSize) : new SpscArrayQueue<>(this.maxConcurrency);
                        this.queue = aVar;
                    }
                    if (!aVar.offer(uCall)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    } else if (getAndIncrement() != 0) {
                        return;
                    }
                }
                drainLoop();
            } catch (Throwable th) {
                d.a.n.a.a(th);
                this.errors.addThrowable(th);
                drain();
            }
        }
    }

    public ObservableFlatMap(h<T> hVar, e<? super T, ? extends h<? extends U>> eVar, boolean z, int i2, int i3) {
        super(hVar);
        this.f5901d = eVar;
        this.s = z;
        this.x = i2;
        this.y = i3;
    }

    @Override // d.a.g
    public void t(i<? super U> iVar) {
        if (ObservableScalarXMap.b(this.f3190c, iVar, this.f5901d)) {
            return;
        }
        this.f3190c.a(new MergeObserver(iVar, this.f5901d, this.s, this.x, this.y));
    }
}
