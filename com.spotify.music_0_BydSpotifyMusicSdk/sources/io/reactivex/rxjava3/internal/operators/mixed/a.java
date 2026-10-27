package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.o;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Observable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ a(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                CompletableAndThenObservable$AndThenObservableObserver completableAndThenObservable$AndThenObservableObserver = new CompletableAndThenObservable$AndThenObservableObserver((ObservableSource) obj2, observer);
                observer.onSubscribe(completableAndThenObservable$AndThenObservableObserver);
                ((CompletableSource) obj).subscribe(completableAndThenObservable$AndThenObservableObserver);
                break;
            default:
                SingleFlatMapObservable$FlatMapObserver singleFlatMapObservable$FlatMapObserver = new SingleFlatMapObservable$FlatMapObserver(observer, (o) obj2);
                observer.onSubscribe(singleFlatMapObservable$FlatMapObserver);
                ((SingleSource) obj).subscribe(singleFlatMapObservable$FlatMapObserver);
                break;
        }
    }
}
