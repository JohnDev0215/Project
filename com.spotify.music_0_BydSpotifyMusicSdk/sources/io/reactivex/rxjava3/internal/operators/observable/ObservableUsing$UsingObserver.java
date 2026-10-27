package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableUsing$UsingObserver<T, D> extends AtomicBoolean implements Observer<T>, Disposable {
    private static final long serialVersionUID = 5904473792286235046L;
    final io.reactivex.rxjava3.functions.g disposer;
    final Observer<? super T> downstream;
    final boolean eager;
    final D resource;
    Disposable upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableUsing$UsingObserver(Observer observer, Object obj, io.reactivex.rxjava3.functions.g gVar, boolean z) {
        this.downstream = observer;
        this.resource = obj;
        this.disposer = gVar;
        this.eager = z;
    }

    public final void a() {
        if (compareAndSet(false, true)) {
            try {
                this.disposer.accept(this.resource);
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        boolean z = this.eager;
        DisposableHelper disposableHelper = DisposableHelper.c;
        if (z) {
            a();
            this.upstream.dispose();
            this.upstream = disposableHelper;
        } else {
            this.upstream.dispose();
            this.upstream = disposableHelper;
            a();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (!this.eager) {
            this.downstream.onComplete();
            a();
            return;
        }
        if (compareAndSet(false, true)) {
            try {
                this.disposer.accept(this.resource);
            } catch (Throwable th) {
                he1.R(th);
                this.downstream.onError(th);
                return;
            }
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (!this.eager) {
            this.downstream.onError(th);
            a();
            return;
        }
        if (compareAndSet(false, true)) {
            try {
                this.disposer.accept(this.resource);
            } catch (Throwable th2) {
                he1.R(th2);
                th = new CompositeException(th, th2);
            }
        }
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
