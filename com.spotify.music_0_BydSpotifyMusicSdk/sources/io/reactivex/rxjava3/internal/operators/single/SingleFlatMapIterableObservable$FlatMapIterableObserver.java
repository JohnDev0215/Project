package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import java.util.Iterator;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleFlatMapIterableObservable$FlatMapIterableObserver<T, R> extends BasicIntQueueDisposable<R> implements SingleObserver<T> {
    private static final long serialVersionUID = -8938804753851907758L;
    volatile boolean cancelled;
    final Observer<? super R> downstream;
    volatile Iterator<? extends R> it;
    final io.reactivex.rxjava3.functions.o mapper;
    boolean outputFused;
    Disposable upstream;

    public SingleFlatMapIterableObservable$FlatMapIterableObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = observer;
        this.mapper = oVar;
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.it = null;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        this.upstream = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.it == null;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.upstream = DisposableHelper.c;
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        Observer<? super R> observer = this.downstream;
        try {
            Iterator<? extends R> it = ((Iterable) this.mapper.apply(obj)).iterator();
            if (!it.hasNext()) {
                observer.onComplete();
                return;
            }
            if (this.outputFused) {
                this.it = it;
                observer.onNext(null);
                observer.onComplete();
                return;
            }
            while (!this.cancelled) {
                try {
                    observer.onNext(it.next());
                    if (this.cancelled) {
                        return;
                    }
                    try {
                        if (!it.hasNext()) {
                            observer.onComplete();
                            return;
                        }
                    } catch (Throwable th) {
                        he1.R(th);
                        observer.onError(th);
                        return;
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    observer.onError(th2);
                    return;
                }
            }
        } catch (Throwable th3) {
            he1.R(th3);
            this.downstream.onError(th3);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator<? extends R> it = this.it;
        if (it == null) {
            return null;
        }
        R next = it.next();
        Objects.requireNonNull(next, "The iterator returned a null value");
        if (!it.hasNext()) {
            this.it = null;
        }
        return next;
    }
}
