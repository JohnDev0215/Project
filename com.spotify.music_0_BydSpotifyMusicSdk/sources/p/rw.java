package p;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class rw {
    public final xy1 a;
    public final Observable b = Observable.just(Boolean.TRUE);
    public final Observable c;

    public rw(Observable observable, ij0 ij0Var) {
        this.a = ij0Var;
        this.c = observable.distinctUntilChanged();
    }
}
