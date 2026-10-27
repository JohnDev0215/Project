package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class b1 implements Observer {
    public final /* synthetic */ int c;
    public final Observer e;
    public final SequentialDisposable k;
    public boolean l;
    public final ObservableSource m;

    public b1(ObservableSource observableSource, Observer observer) {
        this.c = 1;
        this.e = observer;
        this.m = observableSource;
        this.l = true;
        this.k = new SequentialDisposable();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        int i = this.c;
        ObservableSource observableSource = this.m;
        switch (i) {
            case 0:
                if (!this.l) {
                    this.l = true;
                    ((ObservableSource) ((o) observableSource).e).subscribe(new z3(this, 1));
                    break;
                }
                break;
            default:
                if (!this.l) {
                    this.e.onComplete();
                } else {
                    this.l = false;
                    observableSource.subscribe(this);
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                if (!this.l) {
                    this.l = true;
                    observer.onError(th);
                } else {
                    RxJavaPlugins.b(th);
                }
                break;
            default:
                observer.onError(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        switch (this.c) {
            case 0:
                onComplete();
                break;
            default:
                if (this.l) {
                    this.l = false;
                }
                this.e.onNext(obj);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        SequentialDisposable sequentialDisposable = this.k;
        switch (i) {
            case 0:
                sequentialDisposable.getClass();
                DisposableHelper.d(sequentialDisposable, disposable);
                break;
            default:
                sequentialDisposable.getClass();
                DisposableHelper.d(sequentialDisposable, disposable);
                break;
        }
    }

    public b1(o oVar, SequentialDisposable sequentialDisposable, Observer observer) {
        this.c = 0;
        this.m = oVar;
        this.k = sequentialDisposable;
        this.e = observer;
    }
}
