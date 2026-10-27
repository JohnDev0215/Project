package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindowBoundary$WindowBoundaryMainObserver<T, B> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    public static final Object c = new Object();
    private static final long serialVersionUID = 2233020065421370272L;
    final int capacityHint;
    volatile boolean done;
    final Observer<? super Observable<T>> downstream;
    io.reactivex.rxjava3.subjects.f window;
    final k5 boundaryObserver = new k5(this);
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    final AtomicInteger windows = new AtomicInteger(1);
    final io.reactivex.rxjava3.internal.queue.a queue = new io.reactivex.rxjava3.internal.queue.a();
    final AtomicThrowable errors = new AtomicThrowable();
    final AtomicBoolean stopWindows = new AtomicBoolean();

    public ObservableWindowBoundary$WindowBoundaryMainObserver(Observer observer, int i) {
        this.downstream = observer;
        this.capacityHint = i;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super Observable<T>> observer = this.downstream;
        io.reactivex.rxjava3.internal.queue.a aVar = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        int iAddAndGet = 1;
        while (this.windows.get() != 0) {
            io.reactivex.rxjava3.subjects.f fVar = this.window;
            boolean z = this.done;
            if (z && atomicThrowable.get() != null) {
                aVar.clear();
                Throwable thD = io.reactivex.rxjava3.internal.util.c.d(atomicThrowable);
                if (fVar != null) {
                    this.window = null;
                    fVar.onError(thD);
                }
                observer.onError(thD);
                return;
            }
            Object objPoll = aVar.poll();
            boolean z2 = objPoll == null;
            if (z && z2) {
                atomicThrowable.getClass();
                Throwable thD2 = io.reactivex.rxjava3.internal.util.c.d(atomicThrowable);
                if (thD2 == null) {
                    if (fVar != null) {
                        this.window = null;
                        fVar.onComplete();
                    }
                    observer.onComplete();
                    return;
                }
                if (fVar != null) {
                    this.window = null;
                    fVar.onError(thD2);
                }
                observer.onError(thD2);
                return;
            }
            if (z2) {
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else if (objPoll != c) {
                fVar.onNext(objPoll);
            } else {
                if (fVar != null) {
                    this.window = null;
                    fVar.onComplete();
                }
                if (!this.stopWindows.get()) {
                    io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.capacityHint, this);
                    this.window = fVarA;
                    this.windows.getAndIncrement();
                    o oVar = new o(fVarA);
                    observer.onNext(oVar);
                    if (oVar.a()) {
                        fVarA.onComplete();
                    }
                }
            }
        }
        aVar.clear();
        this.window = null;
    }

    public final void b() {
        this.queue.offer(c);
        a();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.stopWindows.compareAndSet(false, true)) {
            this.boundaryObserver.dispose();
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.a(this.upstream);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.stopWindows.get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.boundaryObserver.dispose();
        this.done = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.boundaryObserver.dispose();
        if (this.errors.a(th)) {
            this.done = true;
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
        if (DisposableHelper.f(this.upstream, disposable)) {
            b();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.windows.decrementAndGet() == 0) {
            DisposableHelper.a(this.upstream);
        }
    }
}
