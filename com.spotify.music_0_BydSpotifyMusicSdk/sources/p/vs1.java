package p;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class vs1 {
    public final wc1 a;
    public final rw b;
    public final xy1 c;
    public volatile Disposable d = EmptyDisposable.c;

    public vs1(wc1 wc1Var, rw rwVar, ij0 ij0Var) {
        this.a = wc1Var;
        this.b = rwVar;
        this.c = ij0Var;
    }

    public final synchronized void a(com.spotify.eventsender.eventsender.a aVar) {
        int iIntValue;
        Observable observable;
        if (this.d.isDisposed()) {
            zi0 zi0Var = (zi0) this.c.get();
            zl1 zl1Var = (zl1) zi0Var;
            int iIntValue2 = 30;
            if (zl1Var.d) {
                iIntValue = 1;
            } else {
                Integer numB = zl1.b(zl1Var.b);
                if (numB != null) {
                    iIntValue = numB.intValue();
                } else {
                    Integer numB2 = zl1.b(zl1Var.a);
                    iIntValue = numB2 != null ? numB2.intValue() : 30;
                }
            }
            long j = iIntValue;
            zl1 zl1Var2 = (zl1) zi0Var;
            Integer numB3 = zl1.b(zl1Var2.b);
            if (numB3 != null) {
                iIntValue2 = numB3.intValue();
            } else {
                Integer numB4 = zl1.b(zl1Var2.a);
                if (numB4 != null) {
                    iIntValue2 = numB4.intValue();
                }
            }
            Observable<Long> observableInterval = Observable.interval(j, iIntValue2, TimeUnit.SECONDS, io.reactivex.rxjava3.schedulers.e.b);
            rw rwVar = this.b;
            if (((zl1) ((zi0) rwVar.a.get())).e) {
                observable = rwVar.c;
                v03.i(observable, "{\n            realConnProvider\n        }");
            } else {
                observable = rwVar.b;
                v03.i(observable, "{\n            defaultConnProvider\n        }");
            }
            this.d = Observable.combineLatest(observableInterval, observable.onErrorReturnItem(Boolean.FALSE), new lj(4)).filter(new lj(20)).observeOn(io.reactivex.rxjava3.schedulers.e.c).concatMapCompletable(new dd1(14, aVar)).subscribe(new q(4, this), new com.spotify.automotive.auth.a(10, this));
        }
    }
}
