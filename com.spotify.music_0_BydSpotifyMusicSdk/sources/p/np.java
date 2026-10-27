package p;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class np extends Observable {
    public final /* synthetic */ int c;
    public final jp e;

    public /* synthetic */ np(ip1 ip1Var, int i) {
        this.c = i;
        this.e = ip1Var;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        boolean z;
        int i = this.c;
        jp jpVar = this.e;
        switch (i) {
            case 0:
                jp jpVarClone = jpVar.clone();
                mp mpVar = new mp(jpVarClone, observer);
                observer.onSubscribe(mpVar);
                if (!mpVar.isDisposed()) {
                    jpVarClone.E(mpVar);
                }
                break;
            default:
                jp jpVarClone2 = jpVar.clone();
                Disposable opVar = new op(jpVarClone2);
                observer.onSubscribe(opVar);
                if (!opVar.isDisposed()) {
                    try {
                        u42 u42VarB = jpVarClone2.b();
                        if (!opVar.isDisposed()) {
                            observer.onNext(u42VarB);
                        }
                        if (!opVar.isDisposed()) {
                            try {
                                observer.onComplete();
                                break;
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                he1.R(th);
                                if (z) {
                                    RxJavaPlugins.b(th);
                                    return;
                                }
                                if (opVar.isDisposed()) {
                                    return;
                                }
                                try {
                                    observer.onError(th);
                                    return;
                                } catch (Throwable th2) {
                                    he1.R(th2);
                                    RxJavaPlugins.b(new CompositeException(th, th2));
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        z = false;
                    }
                }
                break;
        }
    }
}
