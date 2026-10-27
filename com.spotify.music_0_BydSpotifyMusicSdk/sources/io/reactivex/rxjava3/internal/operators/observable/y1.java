package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class y1 implements Emitter, Disposable {
    public final Observer c;
    public final io.reactivex.rxjava3.functions.g e;
    public Object k;
    public volatile boolean l;
    public boolean m;
    public boolean n;

    public y1(Observer observer, io.reactivex.rxjava3.functions.c cVar, io.reactivex.rxjava3.functions.g gVar, Object obj) {
        this.c = observer;
        this.e = gVar;
        this.k = obj;
    }

    public final void a(Object obj) {
        try {
            this.e.accept(obj);
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(th);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.l = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.core.Emitter
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Emitter
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
            return;
        }
        if (th == null) {
            th = io.reactivex.rxjava3.internal.util.c.b("onError called with a null Throwable.");
        }
        this.m = true;
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Emitter
    public void onNext(Object obj) {
        if (this.m) {
            return;
        }
        if (this.n) {
            onError(new IllegalStateException("onNext already called in this generate turn"));
        } else if (obj == null) {
            onError(io.reactivex.rxjava3.internal.util.c.b("onNext called with a null value."));
        } else {
            this.n = true;
            this.c.onNext(obj);
        }
    }
}
