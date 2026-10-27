package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.observable.g5;
import io.reactivex.rxjava3.internal.operators.observable.m0;
import java.util.Collection;
import java.util.stream.Collector;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Single implements io.reactivex.rxjava3.internal.fuseable.d {
    public final /* synthetic */ int c;
    public final ObservableSource e;
    public final Object k;

    public /* synthetic */ d(ObservableSource observableSource, Object obj, int i) {
        this.c = i;
        this.e = observableSource;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        int i = this.c;
        ObservableSource observableSource = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                return new b((Observable) observableSource, 0, (Collector) obj);
            default:
                return new m0(observableSource, (q) obj, 9);
        }
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        int i = this.c;
        ObservableSource observableSource = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                try {
                    ((Observable) observableSource).subscribe(new c(singleObserver, ((Collector) obj).supplier().get(), ((Collector) obj).accumulator(), ((Collector) obj).finisher()));
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    singleObserver.onSubscribe(emptyDisposable);
                    singleObserver.onError(th);
                    return;
                }
            default:
                try {
                    Object obj2 = ((q) obj).get();
                    if (obj2 == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("The collectionSupplier returned a null Collection.");
                    }
                    Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                    observableSource.subscribe(new g5(singleObserver, (Collection) obj2));
                    return;
                } catch (Throwable th3) {
                    he1.R(th3);
                    singleObserver.onSubscribe(emptyDisposable);
                    singleObserver.onError(th3);
                    return;
                }
        }
    }

    public d(ObservableSource observableSource, int i) {
        this.c = 1;
        this.e = observableSource;
        this.k = new io.reactivex.rxjava3.internal.functions.a(i);
    }
}
