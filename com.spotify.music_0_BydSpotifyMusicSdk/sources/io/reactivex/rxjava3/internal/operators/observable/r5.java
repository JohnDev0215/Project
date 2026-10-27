package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class r5 implements Observer {
    public final /* synthetic */ int c;
    public final Observer e;
    public final Object k;

    public /* synthetic */ r5(Observer observer, AtomicReference atomicReference, int i) {
        this.c = i;
        this.e = observer;
        this.k = atomicReference;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                break;
            case 1:
                ((io.reactivex.rxjava3.subjects.c) observer).onComplete();
                break;
            default:
                observer.onComplete();
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                ObservableWithLatestFrom$WithLatestFromObserver observableWithLatestFrom$WithLatestFromObserver = (ObservableWithLatestFrom$WithLatestFromObserver) observer;
                DisposableHelper.a(observableWithLatestFrom$WithLatestFromObserver.upstream);
                observableWithLatestFrom$WithLatestFromObserver.downstream.onError(th);
                break;
            case 1:
                ((io.reactivex.rxjava3.subjects.c) observer).onError(th);
                break;
            default:
                observer.onError(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                ((ObservableWithLatestFrom$WithLatestFromObserver) observer).lazySet(obj);
                break;
            case 1:
                ((io.reactivex.rxjava3.subjects.c) observer).onNext(obj);
                break;
            default:
                observer.onNext(obj);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                DisposableHelper.f(((ObservableWithLatestFrom$WithLatestFromObserver) this.e).other, disposable);
                break;
            case 1:
                DisposableHelper.f((AtomicReference) obj, disposable);
                break;
            default:
                DisposableHelper.c((AtomicReference) obj, disposable);
                break;
        }
    }

    public r5(s5 s5Var, ObservableWithLatestFrom$WithLatestFromObserver observableWithLatestFrom$WithLatestFromObserver) {
        this.c = 0;
        this.k = s5Var;
        this.e = observableWithLatestFrom$WithLatestFromObserver;
    }
}
