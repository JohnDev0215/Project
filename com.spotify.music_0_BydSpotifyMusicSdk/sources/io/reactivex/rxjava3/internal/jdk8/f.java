package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import java.util.stream.Stream;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Observable {
    public final /* synthetic */ int c;
    public final Observable e;
    public final io.reactivex.rxjava3.functions.o k;

    public /* synthetic */ f(Observable observable, io.reactivex.rxjava3.functions.o oVar, int i) {
        this.c = i;
        this.e = observable;
        this.k = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        Stream stream;
        int i = this.c;
        ObservableSource observableSource = this.e;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        switch (i) {
            case 0:
                EmptyDisposable emptyDisposable = EmptyDisposable.c;
                if (!(observableSource instanceof q)) {
                    observableSource.subscribe(new ObservableFlatMapStream$FlatMapStreamObserver(observer, oVar));
                } else {
                    try {
                        Object obj = ((q) observableSource).get();
                        if (obj != null) {
                            Object objApply = oVar.apply(obj);
                            Objects.requireNonNull(objApply, "The mapper returned a null Stream");
                            stream = (Stream) objApply;
                        } else {
                            stream = null;
                        }
                        if (stream == null) {
                            observer.onSubscribe(emptyDisposable);
                            observer.onComplete();
                        } else {
                            g.a(observer, stream);
                        }
                    } catch (Throwable th) {
                        he1.R(th);
                        observer.onSubscribe(emptyDisposable);
                        observer.onError(th);
                        return;
                    }
                }
                break;
            default:
                observableSource.subscribe(new i(observer, oVar, 0));
                break;
        }
    }
}
