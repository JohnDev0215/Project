package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class c2 extends a {
    public final /* synthetic */ int e;
    public final Object k;
    public final Object l;
    public final Object m;
    public final Object n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c2(ObservableSource observableSource, Object obj, Object obj2, Object obj3, Object obj4, int i) {
        super(observableSource);
        this.e = i;
        this.k = obj;
        this.l = obj2;
        this.m = obj3;
        this.n = obj4;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.e;
        ObservableSource observableSource = this.c;
        Object obj = this.n;
        Object obj2 = this.m;
        Object obj3 = this.l;
        Object obj4 = this.k;
        switch (i) {
            case 0:
                ObservableGroupJoin$GroupJoinDisposable observableGroupJoin$GroupJoinDisposable = new ObservableGroupJoin$GroupJoinDisposable(observer, (io.reactivex.rxjava3.functions.o) obj3, (io.reactivex.rxjava3.functions.o) obj2, (io.reactivex.rxjava3.functions.c) obj);
                observer.onSubscribe(observableGroupJoin$GroupJoinDisposable);
                ObservableGroupJoin$LeftRightObserver observableGroupJoin$LeftRightObserver = new ObservableGroupJoin$LeftRightObserver(observableGroupJoin$GroupJoinDisposable, true);
                observableGroupJoin$GroupJoinDisposable.disposables.c(observableGroupJoin$LeftRightObserver);
                ObservableGroupJoin$LeftRightObserver observableGroupJoin$LeftRightObserver2 = new ObservableGroupJoin$LeftRightObserver(observableGroupJoin$GroupJoinDisposable, false);
                observableGroupJoin$GroupJoinDisposable.disposables.c(observableGroupJoin$LeftRightObserver2);
                observableSource.subscribe(observableGroupJoin$LeftRightObserver);
                ((ObservableSource) obj4).subscribe(observableGroupJoin$LeftRightObserver2);
                break;
            case 1:
                ObservableJoin$JoinDisposable observableJoin$JoinDisposable = new ObservableJoin$JoinDisposable(observer, (io.reactivex.rxjava3.functions.o) obj3, (io.reactivex.rxjava3.functions.o) obj2, (io.reactivex.rxjava3.functions.c) obj);
                observer.onSubscribe(observableJoin$JoinDisposable);
                ObservableGroupJoin$LeftRightObserver observableGroupJoin$LeftRightObserver3 = new ObservableGroupJoin$LeftRightObserver(observableJoin$JoinDisposable, true);
                observableJoin$JoinDisposable.disposables.c(observableGroupJoin$LeftRightObserver3);
                ObservableGroupJoin$LeftRightObserver observableGroupJoin$LeftRightObserver4 = new ObservableGroupJoin$LeftRightObserver(observableJoin$JoinDisposable, false);
                observableJoin$JoinDisposable.disposables.c(observableGroupJoin$LeftRightObserver4);
                observableSource.subscribe(observableGroupJoin$LeftRightObserver3);
                ((ObservableSource) obj4).subscribe(observableGroupJoin$LeftRightObserver4);
                break;
            default:
                observableSource.subscribe(new h1(observer, (io.reactivex.rxjava3.functions.g) obj4, (io.reactivex.rxjava3.functions.g) obj3, (io.reactivex.rxjava3.functions.a) obj2, (io.reactivex.rxjava3.functions.a) obj));
                break;
        }
    }
}
