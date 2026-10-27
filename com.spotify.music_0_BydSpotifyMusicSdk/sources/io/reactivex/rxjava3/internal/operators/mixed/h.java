package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class h extends Completable {
    public final Observable c;
    public final o e;
    public final boolean k;

    public h(Observable observable, o oVar, boolean z) {
        this.c = observable;
        this.e = oVar;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        Observable observable = this.c;
        o oVar = this.e;
        if (vr2.u(observable, oVar, completableObserver)) {
            return;
        }
        observable.subscribe(new g(completableObserver, oVar, this.k));
    }
}
