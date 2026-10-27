package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Observer, Disposable {
    public final SingleObserver c;
    public final BiConsumer e;
    public final Function k;
    public Disposable l;
    public boolean m;
    public Object n;

    public c(SingleObserver singleObserver, Object obj, BiConsumer biConsumer, Function function) {
        this.c = singleObserver;
        this.n = obj;
        this.e = biConsumer;
        this.k = function;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l.dispose();
        this.l = DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l == DisposableHelper.c;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        SingleObserver singleObserver = this.c;
        if (this.m) {
            return;
        }
        this.m = true;
        this.l = DisposableHelper.c;
        Object obj = this.n;
        this.n = null;
        try {
            Object objApply = this.k.apply(obj);
            Objects.requireNonNull(objApply, "The finisher returned a null value");
            singleObserver.onSuccess(objApply);
        } catch (Throwable th) {
            he1.R(th);
            singleObserver.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
            return;
        }
        this.m = true;
        this.l = DisposableHelper.c;
        this.n = null;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        try {
            this.e.accept(this.n, obj);
        } catch (Throwable th) {
            he1.R(th);
            this.l.dispose();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.l, disposable)) {
            this.l = disposable;
            this.c.onSubscribe(this);
        }
    }
}
