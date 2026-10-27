package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* JADX INFO: loaded from: classes.dex */
public final class m2 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final Observer e;

    public /* synthetic */ m2(Observer observer, int i) {
        this.c = i;
        this.e = observer;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        Observer observer = this.e;
        switch (i) {
            case 0:
                observer.onError((Throwable) obj);
                break;
            case 1:
                observer.onNext(obj);
                break;
            default:
                ObserverResourceWrapper observerResourceWrapper = (ObserverResourceWrapper) observer;
                observerResourceWrapper.getClass();
                DisposableHelper.d(observerResourceWrapper, (Disposable) obj);
                break;
        }
    }
}
