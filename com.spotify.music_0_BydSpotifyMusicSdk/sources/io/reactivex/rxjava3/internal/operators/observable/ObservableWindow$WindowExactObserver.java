package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableWindow$WindowExactObserver<T> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
    private static final long serialVersionUID = -7481782523886138128L;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final int capacityHint;
    final long count;
    final Observer<? super Observable<T>> downstream;
    long size;
    Disposable upstream;
    io.reactivex.rxjava3.subjects.f window;

    public ObservableWindow$WindowExactObserver(Observer observer, long j, int i) {
        this.downstream = observer;
        this.count = j;
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
        io.reactivex.rxjava3.subjects.f fVar = this.window;
        if (fVar != null) {
            this.window = null;
            fVar.onComplete();
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.subjects.f fVar = this.window;
        if (fVar != null) {
            this.window = null;
            fVar.onError(th);
        }
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        o oVar;
        io.reactivex.rxjava3.subjects.f fVarA = this.window;
        if (fVarA != null || this.cancelled.get()) {
            oVar = null;
        } else {
            getAndIncrement();
            fVarA = io.reactivex.rxjava3.subjects.f.a(this.capacityHint, this);
            this.window = fVarA;
            oVar = new o(fVarA);
            this.downstream.onNext(oVar);
        }
        if (fVarA != null) {
            fVarA.onNext(obj);
            long j = this.size + 1;
            this.size = j;
            if (j >= this.count) {
                this.size = 0L;
                this.window = null;
                fVarA.onComplete();
            }
            if (oVar == null || !oVar.a()) {
                return;
            }
            this.window = null;
            fVarA.onComplete();
        }
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
