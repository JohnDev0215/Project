package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class h3 extends a {
    public final /* synthetic */ int e;
    public final io.reactivex.rxjava3.functions.o k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h3(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar, int i) {
        super(observableSource);
        this.e = i;
        this.k = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        int i2 = 1;
        ObservableSource observableSource = this.c;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        switch (i) {
            case 0:
                io.reactivex.rxjava3.subjects.c cVar = new io.reactivex.rxjava3.subjects.c();
                try {
                    Object objApply = oVar.apply(cVar);
                    Objects.requireNonNull(objApply, "The selector returned a null ObservableSource");
                    ObservableSource observableSource2 = (ObservableSource) objApply;
                    ObservablePublishSelector$TargetObserver observablePublishSelector$TargetObserver = new ObservablePublishSelector$TargetObserver(observer);
                    observableSource2.subscribe(observablePublishSelector$TargetObserver);
                    observableSource.subscribe(new r5(cVar, observablePublishSelector$TargetObserver, i2));
                } catch (Throwable th) {
                    he1.R(th);
                    observer.onSubscribe(EmptyDisposable.c);
                    observer.onError(th);
                    return;
                }
                break;
            case 1:
                observableSource.subscribe(new t0(new io.reactivex.rxjava3.observers.d(observer), oVar));
                break;
            case 2:
                observableSource.subscribe(new c1(observer, oVar));
                break;
            case 3:
                observableSource.subscribe(new s1(observer, oVar));
                break;
            case 4:
                observableSource.subscribe(new io.reactivex.rxjava3.internal.jdk8.i(observer, oVar, 1));
                break;
            case 5:
                e3 e3Var = new e3(observer, oVar);
                observer.onSubscribe(e3Var.k);
                observableSource.subscribe(e3Var);
                break;
            default:
                observableSource.subscribe(new f3(observer, oVar));
                break;
        }
    }
}
