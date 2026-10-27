package p;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class mp implements Disposable, pp {
    public final jp c;
    public final Observer e;
    public volatile boolean k;
    public boolean l = false;

    public mp(jp jpVar, Observer observer) {
        this.c = jpVar;
        this.e = observer;
    }

    @Override // p.pp
    public final void a(jp jpVar, Throwable th) {
        if (jpVar.i()) {
            return;
        }
        try {
            this.e.onError(th);
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(new CompositeException(th, th2));
        }
    }

    @Override // p.pp
    public final void b(jp jpVar, u42 u42Var) {
        if (this.k) {
            return;
        }
        try {
            this.e.onNext(u42Var);
            if (this.k) {
                return;
            }
            this.l = true;
            this.e.onComplete();
        } catch (Throwable th) {
            he1.R(th);
            if (this.l) {
                RxJavaPlugins.b(th);
                return;
            }
            if (this.k) {
                return;
            }
            try {
                this.e.onError(th);
            } catch (Throwable th2) {
                he1.R(th2);
                RxJavaPlugins.b(new CompositeException(th, th2));
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k = true;
        this.c.cancel();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k;
    }
}
