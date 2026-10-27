package p;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class yg2 implements ObservableOnSubscribe {
    public final /* synthetic */ zg2 c;
    public final /* synthetic */ String e;
    public final /* synthetic */ Observable k;

    public yg2(zg2 zg2Var, String str, Observable observable) {
        this.c = zg2Var;
        this.e = str;
        this.k = observable;
    }

    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public final void subscribe(ObservableEmitter observableEmitter) {
        v03.j(observableEmitter, "emitter");
        zg2 zg2Var = this.c;
        String str = this.e;
        Observable observable = this.k;
        zg2Var.getClass();
        v03.j(str, "tag");
        v03.j(observable, "source");
        int i = 1;
        Disposable disposableSubscribe = observable.subscribe(new wg2(observableEmitter, 0), new wg2(observableEmitter, i), new a0(i, observableEmitter));
        v03.i(disposableSubscribe, "disposable");
        mn2 mn2Var = new mn2(observableEmitter, disposableSubscribe, new vg2(str, ux2.e), str);
        synchronized (zg2Var) {
            zg2Var.a.add(mn2Var);
            zg2Var.a.size();
        }
        observableEmitter.setCancellable(new xg2(str, mn2Var, zg2Var, disposableSubscribe));
    }
}
