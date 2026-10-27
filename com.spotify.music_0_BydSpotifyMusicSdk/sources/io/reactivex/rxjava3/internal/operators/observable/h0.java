package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;
    public final io.reactivex.rxjava3.functions.q e;
    public final io.reactivex.rxjava3.functions.b k;

    public h0(ObservableSource observableSource, io.reactivex.rxjava3.functions.q qVar, io.reactivex.rxjava3.functions.b bVar) {
        this.c = observableSource;
        this.e = qVar;
        this.k = bVar;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new s5(this.c, this.e, this.k, 2);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        try {
            Object obj = this.e.get();
            Objects.requireNonNull(obj, "The initialSupplier returned a null value");
            this.c.subscribe(new g0(singleObserver, obj, this.k));
        } catch (Throwable th) {
            he1.R(th);
            singleObserver.onSubscribe(EmptyDisposable.c);
            singleObserver.onError(th);
        }
    }
}
