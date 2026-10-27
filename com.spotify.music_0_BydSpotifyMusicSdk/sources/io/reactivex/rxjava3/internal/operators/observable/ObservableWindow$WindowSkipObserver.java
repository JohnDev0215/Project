package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindow$WindowSkipObserver<T> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = 3366976432059579510L;
    final int capacityHint;
    final long count;
    final Observer<? super Observable<T>> downstream;
    long firstEmission;
    long index;
    final long skip;
    Disposable upstream;
    final ArrayDeque<io.reactivex.rxjava3.subjects.f> windows = new ArrayDeque<>();
    final AtomicBoolean cancelled = new AtomicBoolean();

    public ObservableWindow$WindowSkipObserver(Observer observer, long j, long j2, int i) {
        this.downstream = observer;
        this.count = j;
        this.skip = j2;
        this.capacityHint = i;
        lazySet(1);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled.compareAndSet(false, true)) {
            run();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled.get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ArrayDeque<io.reactivex.rxjava3.subjects.f> arrayDeque = this.windows;
        while (!arrayDeque.isEmpty()) {
            arrayDeque.poll().onComplete();
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ArrayDeque<io.reactivex.rxjava3.subjects.f> arrayDeque = this.windows;
        while (!arrayDeque.isEmpty()) {
            arrayDeque.poll().onError(th);
        }
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        o oVar;
        ArrayDeque<io.reactivex.rxjava3.subjects.f> arrayDeque = this.windows;
        long j = this.index;
        long j2 = this.skip;
        if (j % j2 != 0 || this.cancelled.get()) {
            oVar = null;
        } else {
            getAndIncrement();
            io.reactivex.rxjava3.subjects.f fVarA = io.reactivex.rxjava3.subjects.f.a(this.capacityHint, this);
            oVar = new o(fVarA);
            arrayDeque.offer(fVarA);
            this.downstream.onNext(oVar);
        }
        long j3 = this.firstEmission + 1;
        Iterator<io.reactivex.rxjava3.subjects.f> it = arrayDeque.iterator();
        while (it.hasNext()) {
            it.next().onNext(obj);
        }
        if (j3 >= this.count) {
            arrayDeque.poll().onComplete();
            if (arrayDeque.isEmpty() && this.cancelled.get()) {
                return;
            } else {
                this.firstEmission = j3 - j2;
            }
        } else {
            this.firstEmission = j3;
        }
        this.index = j + 1;
        if (oVar == null || !oVar.a()) {
            return;
        }
        ((io.reactivex.rxjava3.subjects.e) oVar.e).onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }
}
