package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.o;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Observable {
    public final /* synthetic */ int c;
    public final Observable e;
    public final o k;
    public final boolean l;

    public /* synthetic */ i(Observable observable, o oVar, boolean z, int i) {
        this.c = i;
        this.e = observable;
        this.k = oVar;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        boolean z = this.l;
        o oVar = this.k;
        Observable observable = this.e;
        switch (i) {
            case 0:
                if (!vr2.v(observable, oVar, observer)) {
                    observable.subscribe(new ObservableSwitchMapMaybe$SwitchMapMaybeMainObserver(observer, oVar, z));
                }
                break;
            default:
                if (!vr2.w(observable, oVar, observer)) {
                    observable.subscribe(new ObservableSwitchMapSingle$SwitchMapSingleMainObserver(observer, oVar, z));
                }
                break;
        }
    }
}
