package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class i5 extends Observable {
    public final io.reactivex.rxjava3.functions.q c;
    public final io.reactivex.rxjava3.functions.o e;
    public final io.reactivex.rxjava3.functions.g k;
    public final boolean l;

    public i5(io.reactivex.rxjava3.functions.q qVar, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.g gVar, boolean z) {
        this.c = qVar;
        this.e = oVar;
        this.k = gVar;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        io.reactivex.rxjava3.functions.g gVar = this.k;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        try {
            Object obj = this.c.get();
            try {
                Object objApply = this.e.apply(obj);
                Objects.requireNonNull(objApply, "The sourceSupplier returned a null ObservableSource");
                ((ObservableSource) objApply).subscribe(new ObservableUsing$UsingObserver(observer, obj, gVar, this.l));
            } catch (Throwable th) {
                he1.R(th);
                try {
                    gVar.accept(obj);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(th);
                } catch (Throwable th2) {
                    he1.R(th2);
                    CompositeException compositeException = new CompositeException(th, th2);
                    observer.onSubscribe(emptyDisposable);
                    observer.onError(compositeException);
                }
            }
        } catch (Throwable th3) {
            he1.R(th3);
            observer.onSubscribe(emptyDisposable);
            observer.onError(th3);
        }
    }
}
