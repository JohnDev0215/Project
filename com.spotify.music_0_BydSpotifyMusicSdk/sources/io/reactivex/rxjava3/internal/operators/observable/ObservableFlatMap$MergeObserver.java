package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.operators.SpscArrayQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMap$MergeObserver<T, U> extends AtomicInteger implements Disposable, Observer<T> {
    public static final ObservableFlatMap$InnerObserver[] c = new ObservableFlatMap$InnerObserver[0];
    public static final ObservableFlatMap$InnerObserver[] e = new ObservableFlatMap$InnerObserver[0];
    private static final long serialVersionUID = -2117620485640801370L;
    final int bufferSize;
    final boolean delayErrors;
    volatile boolean disposed;
    volatile boolean done;
    final Observer<? super U> downstream;
    final AtomicThrowable errors = new AtomicThrowable();
    int lastIndex;
    final io.reactivex.rxjava3.functions.o mapper;
    final int maxConcurrency;
    final AtomicReference<ObservableFlatMap$InnerObserver<?, ?>[]> observers;
    volatile io.reactivex.rxjava3.operators.f queue;
    Queue<ObservableSource<? extends U>> sources;
    long uniqueId;
    Disposable upstream;
    int wip;

    public ObservableFlatMap$MergeObserver(int i, int i2, Observer observer, io.reactivex.rxjava3.functions.o oVar, boolean z) {
        this.downstream = observer;
        this.mapper = oVar;
        this.delayErrors = z;
        this.maxConcurrency = i;
        this.bufferSize = i2;
        if (i != Integer.MAX_VALUE) {
            this.sources = new ArrayDeque(i);
        }
        this.observers = new AtomicReference<>(c);
    }

    public final boolean a() {
        if (this.disposed) {
            return true;
        }
        Throwable th = this.errors.get();
        if (this.delayErrors || th == null) {
            return false;
        }
        b();
        this.errors.d(this.downstream);
        return true;
    }

    public final boolean b() {
        this.upstream.dispose();
        AtomicReference<ObservableFlatMap$InnerObserver<?, ?>[]> atomicReference = this.observers;
        ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr = e;
        ObservableFlatMap$InnerObserver<?, ?>[] andSet = atomicReference.getAndSet(observableFlatMap$InnerObserverArr);
        if (andSet == observableFlatMap$InnerObserverArr) {
            return false;
        }
        for (ObservableFlatMap$InnerObserver<?, ?> observableFlatMap$InnerObserver : andSet) {
            observableFlatMap$InnerObserver.getClass();
            DisposableHelper.a(observableFlatMap$InnerObserver);
        }
        return true;
    }

    public final void c() {
        if (getAndIncrement() == 0) {
            d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4 A[PHI: r4
  0x00c4: PHI (r4v6 int) = (r4v4 int), (r4v7 int) binds: [B:57:0x00aa, B:66:0x00c2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableFlatMap$MergeObserver.d():void");
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        if (b()) {
            this.errors.b();
        }
    }

    public final void f(ObservableFlatMap$InnerObserver observableFlatMap$InnerObserver) {
        ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr;
        while (true) {
            ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr2 = this.observers.get();
            int length = observableFlatMap$InnerObserverArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (observableFlatMap$InnerObserverArr2[i] == observableFlatMap$InnerObserver) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                observableFlatMap$InnerObserverArr = c;
            } else {
                ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr3 = new ObservableFlatMap$InnerObserver[length - 1];
                System.arraycopy(observableFlatMap$InnerObserverArr2, 0, observableFlatMap$InnerObserverArr3, 0, i);
                System.arraycopy(observableFlatMap$InnerObserverArr2, i + 1, observableFlatMap$InnerObserverArr3, i, (length - i) - 1);
                observableFlatMap$InnerObserverArr = observableFlatMap$InnerObserverArr3;
            }
            AtomicReference<ObservableFlatMap$InnerObserver<?, ?>[]> atomicReference = this.observers;
            while (!atomicReference.compareAndSet(observableFlatMap$InnerObserverArr2, observableFlatMap$InnerObserverArr)) {
                if (atomicReference.get() != observableFlatMap$InnerObserverArr2) {
                    break;
                }
            }
            return;
        }
    }

    public final void g(ObservableSource observableSource) {
        boolean z;
        do {
            z = false;
            if (!(observableSource instanceof io.reactivex.rxjava3.functions.q)) {
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                ObservableFlatMap$InnerObserver<?, ?> observableFlatMap$InnerObserver = new ObservableFlatMap$InnerObserver<>(this, j);
                while (true) {
                    ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr = this.observers.get();
                    if (observableFlatMap$InnerObserverArr == e) {
                        DisposableHelper.a(observableFlatMap$InnerObserver);
                        return;
                    }
                    int length = observableFlatMap$InnerObserverArr.length;
                    ObservableFlatMap$InnerObserver<?, ?>[] observableFlatMap$InnerObserverArr2 = new ObservableFlatMap$InnerObserver[length + 1];
                    System.arraycopy(observableFlatMap$InnerObserverArr, 0, observableFlatMap$InnerObserverArr2, 0, length);
                    observableFlatMap$InnerObserverArr2[length] = observableFlatMap$InnerObserver;
                    AtomicReference<ObservableFlatMap$InnerObserver<?, ?>[]> atomicReference = this.observers;
                    while (!atomicReference.compareAndSet(observableFlatMap$InnerObserverArr, observableFlatMap$InnerObserverArr2)) {
                        if (atomicReference.get() != observableFlatMap$InnerObserverArr) {
                            break;
                        }
                    }
                    observableSource.subscribe(observableFlatMap$InnerObserver);
                    return;
                }
            }
            try {
                Object obj = ((io.reactivex.rxjava3.functions.q) observableSource).get();
                if (obj != null) {
                    if (get() == 0 && compareAndSet(0, 1)) {
                        this.downstream.onNext(obj);
                        if (decrementAndGet() != 0) {
                        }
                    } else {
                        io.reactivex.rxjava3.operators.f hVar = this.queue;
                        if (hVar == null) {
                            hVar = this.maxConcurrency == Integer.MAX_VALUE ? new io.reactivex.rxjava3.operators.h(this.bufferSize) : new SpscArrayQueue(this.maxConcurrency);
                            this.queue = hVar;
                        }
                        hVar.offer(obj);
                        if (getAndIncrement() != 0) {
                            return;
                        }
                    }
                    d();
                }
            } catch (Throwable th) {
                he1.R(th);
                this.errors.a(th);
                c();
            }
            if (this.maxConcurrency == Integer.MAX_VALUE) {
                return;
            }
            synchronized (this) {
                try {
                    observableSource = this.sources.poll();
                    if (observableSource == null) {
                        this.wip--;
                        z = true;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        } while (!z);
        c();
    }

    public final void h(int i) {
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return;
            }
            synchronized (this) {
                try {
                    ObservableSource<? extends U> observableSourcePoll = this.sources.poll();
                    if (observableSourcePoll == null) {
                        this.wip--;
                    } else {
                        g(observableSourcePoll);
                    }
                } finally {
                }
            }
            i = i2;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
        } else if (this.errors.a(th)) {
            this.done = true;
            c();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null ObservableSource");
            ObservableSource<? extends U> observableSource = (ObservableSource) objApply;
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                synchronized (this) {
                    try {
                        int i = this.wip;
                        if (i == this.maxConcurrency) {
                            this.sources.offer(observableSource);
                            return;
                        }
                        this.wip = i + 1;
                    } finally {
                    }
                }
            }
            g(observableSource);
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
