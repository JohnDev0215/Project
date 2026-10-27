package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;

/* JADX INFO: loaded from: classes.dex */
public final class z3 implements Observer {
    public final /* synthetic */ int c;
    public final Observer e;

    public /* synthetic */ z3(Observer observer, int i) {
        this.c = i;
        this.e = observer;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                ObservableSampleWithObservable$SampleMainObserver observableSampleWithObservable$SampleMainObserver = (ObservableSampleWithObservable$SampleMainObserver) observer;
                observableSampleWithObservable$SampleMainObserver.upstream.dispose();
                observableSampleWithObservable$SampleMainObserver.a();
                break;
            default:
                ((b1) observer).e.onComplete();
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                ObservableSampleWithObservable$SampleMainObserver observableSampleWithObservable$SampleMainObserver = (ObservableSampleWithObservable$SampleMainObserver) observer;
                observableSampleWithObservable$SampleMainObserver.upstream.dispose();
                observableSampleWithObservable$SampleMainObserver.downstream.onError(th);
                break;
            default:
                ((b1) observer).e.onError(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                ((ObservableSampleWithObservable$SampleMainObserver) observer).b();
                break;
            default:
                ((b1) observer).e.onNext(obj);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                DisposableHelper.f(((ObservableSampleWithObservable$SampleMainObserver) observer).other, disposable);
                break;
            default:
                SequentialDisposable sequentialDisposable = ((b1) observer).k;
                sequentialDisposable.getClass();
                DisposableHelper.d(sequentialDisposable, disposable);
                break;
        }
    }
}
