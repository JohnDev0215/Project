package p;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class y42 implements Observer {
    public final Observer c;

    public y42(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        Observer observer = this.c;
        int i = 0;
        try {
            if (th == null) {
                throw new NullPointerException("error == null");
            }
            observer.onNext(new x42(null, i, th));
            observer.onComplete();
        } catch (Throwable th2) {
            try {
                observer.onError(th2);
            } catch (Throwable th3) {
                he1.R(th3);
                RxJavaPlugins.b(new CompositeException(th2, th3));
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        u42 u42Var = (u42) obj;
        if (u42Var == null) {
            throw new NullPointerException("response == null");
        }
        this.c.onNext(new x42(u42Var, 0, null));
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        this.c.onSubscribe(disposable);
    }
}
