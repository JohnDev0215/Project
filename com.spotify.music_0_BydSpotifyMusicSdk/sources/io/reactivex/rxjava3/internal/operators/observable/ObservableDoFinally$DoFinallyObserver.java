package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableDoFinally$DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
    private static final long serialVersionUID = 4109457741734051389L;
    final Observer<? super T> downstream;
    final io.reactivex.rxjava3.functions.a onFinally;
    io.reactivex.rxjava3.operators.b qd;
    boolean syncFused;
    Disposable upstream;

    public ObservableDoFinally$DoFinallyObserver(Observer observer, io.reactivex.rxjava3.functions.a aVar) {
        this.downstream = observer;
        this.onFinally = aVar;
    }

    public final void a() {
        if (compareAndSet(0, 1)) {
            try {
                this.onFinally.run();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        io.reactivex.rxjava3.operators.b bVar = this.qd;
        if (bVar == null || (i & 4) != 0) {
            return 0;
        }
        int iB = bVar.b(i);
        if (iB != 0) {
            this.syncFused = iB == 1;
        }
        return iB;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.qd.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        a();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.qd.isEmpty();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.downstream.onComplete();
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.downstream.onError(th);
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                this.qd = (io.reactivex.rxjava3.operators.b) disposable;
            }
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Object objPoll = this.qd.poll();
        if (objPoll == null && this.syncFused) {
            a();
        }
        return objPoll;
    }
}
