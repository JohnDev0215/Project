package p;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import retrofit2.adapter.rxjava3.HttpException;

/* JADX INFO: loaded from: classes.dex */
public final class im implements Observer {
    public final Observer c;
    public boolean e;

    public im(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.e) {
            return;
        }
        this.c.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (!this.e) {
            this.c.onError(th);
            return;
        }
        AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
        assertionError.initCause(th);
        RxJavaPlugins.b(assertionError);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        u42 u42Var = (u42) obj;
        boolean zI = u42Var.a.i();
        Observer observer = this.c;
        if (zI) {
            observer.onNext(u42Var.b);
            return;
        }
        this.e = true;
        HttpException httpException = new HttpException(u42Var);
        try {
            observer.onError(httpException);
        } catch (Throwable th) {
            he1.R(th);
            RxJavaPlugins.b(new CompositeException(httpException, th));
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        this.c.onSubscribe(disposable);
    }
}
