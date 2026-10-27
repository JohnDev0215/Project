package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class s5 extends a {
    public final /* synthetic */ int e;
    public final Object k;
    public final Object l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s5(ObservableSource observableSource, Object obj, Object obj2, int i) {
        super(observableSource);
        this.e = i;
        this.l = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        int i = this.e;
        ObservableSource observableSource = this.c;
        Object obj = this.l;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                io.reactivex.rxjava3.observers.d dVar = new io.reactivex.rxjava3.observers.d(observer);
                ObservableWithLatestFrom$WithLatestFromObserver observableWithLatestFrom$WithLatestFromObserver = new ObservableWithLatestFrom$WithLatestFromObserver(dVar, (io.reactivex.rxjava3.functions.c) obj);
                dVar.onSubscribe(observableWithLatestFrom$WithLatestFromObserver);
                ((ObservableSource) obj2).subscribe(new r5(this, observableWithLatestFrom$WithLatestFromObserver));
                observableSource.subscribe(observableWithLatestFrom$WithLatestFromObserver);
                return;
            case 1:
                observableSource.subscribe(new w(new io.reactivex.rxjava3.observers.d(observer), (io.reactivex.rxjava3.functions.q) obj, (ObservableSource) obj2));
                return;
            case 2:
                try {
                    Object obj3 = ((io.reactivex.rxjava3.functions.q) obj).get();
                    Objects.requireNonNull(obj3, "The initialSupplier returned a null value");
                    observableSource.subscribe(new f0(observer, obj3, (io.reactivex.rxjava3.functions.b) obj2));
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(th);
                    return;
                }
            case 3:
                try {
                    Object obj4 = ((io.reactivex.rxjava3.functions.q) obj2).get();
                    if (obj4 == null) {
                        throw io.reactivex.rxjava3.internal.util.c.b("The collectionSupplier returned a null Collection.");
                    }
                    Throwable th2 = io.reactivex.rxjava3.internal.util.c.a;
                    observableSource.subscribe(new e1(observer, (io.reactivex.rxjava3.functions.o) obj, (Collection) obj4));
                    return;
                } catch (Throwable th3) {
                    he1.R(th3);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(th3);
                    return;
                }
            case 4:
                observableSource.subscribe(new f1(observer, (io.reactivex.rxjava3.functions.o) obj, (io.reactivex.rxjava3.functions.d) obj2));
                return;
            case 5:
                observableSource.subscribe(new io.reactivex.rxjava3.internal.observers.g(observer, (io.reactivex.rxjava3.functions.g) obj, (io.reactivex.rxjava3.functions.a) obj2));
                return;
            case 6:
                try {
                    Object obj5 = ((io.reactivex.rxjava3.functions.q) obj2).get();
                    Objects.requireNonNull(obj5, "The seed supplied is null");
                    observableSource.subscribe(new b4(observer, (io.reactivex.rxjava3.functions.c) obj, obj5));
                    return;
                } catch (Throwable th4) {
                    he1.R(th4);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(th4);
                    return;
                }
            default:
                observableSource.subscribe(new a5(observer, (TimeUnit) obj2, (Scheduler) obj));
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s5(ObservableSource observableSource, Object obj, Object obj2, int i, int i2) {
        super(observableSource);
        this.e = i;
        this.k = obj;
        this.l = obj2;
    }
}
