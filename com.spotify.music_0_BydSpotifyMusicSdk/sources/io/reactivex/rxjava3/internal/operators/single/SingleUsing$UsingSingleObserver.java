package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class SingleUsing$UsingSingleObserver<T, U> extends AtomicReference<Object> implements SingleObserver<T>, Disposable {
    private static final long serialVersionUID = -5331524057054083935L;
    final io.reactivex.rxjava3.functions.g disposer;
    final SingleObserver<? super T> downstream;
    final boolean eager;
    Disposable upstream;

    public SingleUsing$UsingSingleObserver(SingleObserver singleObserver, Object obj, boolean z, io.reactivex.rxjava3.functions.g gVar) {
        super(obj);
        this.downstream = singleObserver;
        this.eager = z;
        this.disposer = gVar;
    }

    public final void a() {
        Object andSet = getAndSet(this);
        if (andSet != this) {
            try {
                this.disposer.accept(andSet);
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
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.upstream = DisposableHelper.c;
        if (this.eager) {
            Object andSet = getAndSet(this);
            if (andSet == this) {
                return;
            }
            try {
                this.disposer.accept(andSet);
            } catch (Throwable th2) {
                he1.R(th2);
                th = new CompositeException(th, th2);
            }
        }
        this.downstream.onError(th);
        if (this.eager) {
            return;
        }
        a();
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
        this.upstream = DisposableHelper.c;
        if (this.eager) {
            Object andSet = getAndSet(this);
            if (andSet == this) {
                return;
            }
            try {
                this.disposer.accept(andSet);
            } catch (Throwable th) {
                he1.R(th);
                this.downstream.onError(th);
                return;
            }
        }
        this.downstream.onSuccess(obj);
        if (this.eager) {
            return;
        }
        a();
    }
}
