package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class w implements Observer, tg2 {
    public final rg2 c;
    public Disposable e;

    public w(rg2 rg2Var) {
        this.c = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
    }

    @Override // p.tg2
    public final void cancel() {
        this.e.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.c.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        this.c.onNext(obj);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        this.e = disposable;
        this.c.onSubscribe(this);
    }
}
