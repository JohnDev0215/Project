package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableCollectWithCollector$CollectorObserver<T, A, R> extends DeferredScalarDisposable<R> implements Observer<T> {
    private static final long serialVersionUID = -229544830565448758L;
    final BiConsumer<A, T> accumulator;
    A container;
    boolean done;
    final Function<A, R> finisher;
    Disposable upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableCollectWithCollector$CollectorObserver(Observer observer, Object obj, BiConsumer biConsumer, Function function) {
        super(observer);
        this.container = obj;
        this.accumulator = biConsumer;
        this.finisher = function;
    }

    @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.upstream = DisposableHelper.c;
        A a = this.container;
        this.container = null;
        try {
            R rApply = this.finisher.apply(a);
            Objects.requireNonNull(rApply, "The finisher returned a null value");
            c(rApply);
        } catch (Throwable th) {
            he1.R(th);
            this.downstream.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.done = true;
        this.upstream = DisposableHelper.c;
        this.container = null;
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        try {
            this.accumulator.accept(this.container, obj);
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
