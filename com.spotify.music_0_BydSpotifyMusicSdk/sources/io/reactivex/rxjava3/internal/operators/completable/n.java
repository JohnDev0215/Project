package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.observable.l3;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class n extends Single {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;
    public final Object l;

    public /* synthetic */ n(Object obj, q qVar, Object obj2, int i) {
        this.c = i;
        this.k = obj;
        this.e = obj2;
        this.l = qVar;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.l;
        Object obj3 = this.k;
        switch (i) {
            case 0:
                ((CompletableSource) obj3).subscribe(new a(this, singleObserver));
                break;
            case 1:
                ((ObservableSource) obj3).subscribe(new l3(singleObserver, (io.reactivex.rxjava3.functions.c) obj2, obj));
                break;
            default:
                try {
                    Object obj4 = ((q) obj2).get();
                    Objects.requireNonNull(obj4, "The seedSupplier returned a null value");
                    ((ObservableSource) obj3).subscribe(new l3(singleObserver, (io.reactivex.rxjava3.functions.c) obj, obj4));
                } catch (Throwable th) {
                    he1.R(th);
                    singleObserver.onSubscribe(EmptyDisposable.c);
                    singleObserver.onError(th);
                    return;
                }
                break;
        }
    }

    public n(ObservableSource observableSource, Object obj, io.reactivex.rxjava3.functions.c cVar) {
        this.c = 1;
        this.k = observableSource;
        this.e = obj;
        this.l = cVar;
    }
}
