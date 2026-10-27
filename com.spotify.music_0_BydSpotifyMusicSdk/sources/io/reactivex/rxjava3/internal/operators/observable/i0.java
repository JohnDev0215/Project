package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends Observable {
    public final /* synthetic */ int c;
    public final ObservableSource[] e;
    public final Iterable k;
    public final io.reactivex.rxjava3.functions.o l;
    public final int m;
    public final boolean n;

    public /* synthetic */ i0(ObservableSource[] observableSourceArr, Iterable iterable, io.reactivex.rxjava3.functions.o oVar, int i, boolean z, int i2) {
        this.c = i2;
        this.e = observableSourceArr;
        this.k = iterable;
        this.l = oVar;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int length;
        int length2;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        int i = this.c;
        Iterable<ObservableSource> iterable = this.k;
        ObservableSource[] observableSourceArr = this.e;
        switch (i) {
            case 0:
                if (observableSourceArr == null) {
                    ObservableSource[] observableSourceArr2 = new ObservableSource[8];
                    try {
                        observableSourceArr = observableSourceArr2;
                        length = 0;
                        for (ObservableSource observableSource : iterable) {
                            if (length == observableSourceArr.length) {
                                ObservableSource[] observableSourceArr3 = new ObservableSource[(length >> 2) + length];
                                System.arraycopy(observableSourceArr, 0, observableSourceArr3, 0, length);
                                observableSourceArr = observableSourceArr3;
                            }
                            int i2 = length + 1;
                            Objects.requireNonNull(observableSource, "The Iterator returned a null ObservableSource");
                            observableSourceArr[length] = observableSource;
                            length = i2;
                        }
                    } catch (Throwable th) {
                        he1.R(th);
                        observer.onSubscribe(emptyDisposable);
                        observer.onError(th);
                        return;
                    }
                } else {
                    length = observableSourceArr.length;
                }
                int i3 = length;
                if (i3 == 0) {
                    observer.onSubscribe(emptyDisposable);
                    observer.onComplete();
                } else {
                    new ObservableCombineLatest$LatestCoordinator(i3, this.m, observer, this.l, this.n).subscribe(observableSourceArr);
                }
                break;
            default:
                if (observableSourceArr == null) {
                    observableSourceArr = new ObservableSource[8];
                    length2 = 0;
                    for (ObservableSource observableSource2 : iterable) {
                        if (length2 == observableSourceArr.length) {
                            ObservableSource[] observableSourceArr4 = new ObservableSource[(length2 >> 2) + length2];
                            System.arraycopy(observableSourceArr, 0, observableSourceArr4, 0, length2);
                            observableSourceArr = observableSourceArr4;
                        }
                        observableSourceArr[length2] = observableSource2;
                        length2++;
                    }
                } else {
                    length2 = observableSourceArr.length;
                }
                if (length2 == 0) {
                    observer.onSubscribe(emptyDisposable);
                    observer.onComplete();
                } else {
                    new ObservableZip$ZipCoordinator(observer, this.l, length2, this.n).subscribe(observableSourceArr, this.m);
                }
                break;
        }
    }
}
