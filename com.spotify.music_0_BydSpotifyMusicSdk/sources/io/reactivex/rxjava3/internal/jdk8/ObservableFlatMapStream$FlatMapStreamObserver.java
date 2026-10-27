package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFlatMapStream$FlatMapStreamObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5127032662980523968L;
    volatile boolean disposed;
    boolean done;
    final Observer<? super R> downstream;
    final io.reactivex.rxjava3.functions.o mapper;
    Disposable upstream;

    public ObservableFlatMapStream$FlatMapStreamObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = observer;
        this.mapper = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        this.upstream.dispose();
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
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
        } else {
            this.done = true;
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null Stream");
            Stream stream = (Stream) objApply;
            try {
                Iterator<T> it = stream.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (this.disposed) {
                        this.done = true;
                        break;
                    }
                    T next = it.next();
                    Objects.requireNonNull(next, "The Stream's Iterator.next returned a null value");
                    if (this.disposed) {
                        this.done = true;
                        break;
                    }
                    this.downstream.onNext(next);
                    if (this.disposed) {
                        this.done = true;
                        break;
                    }
                }
                stream.close();
            } finally {
            }
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
