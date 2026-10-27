package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterable {
    public final /* synthetic */ int c;
    public final ObservableSource e;

    public /* synthetic */ d(ObservableSource observableSource, int i) {
        this.c = i;
        this.e = observableSource;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int i = this.c;
        ObservableSource observableSource = this.e;
        switch (i) {
            case 0:
                c cVar = new c();
                Observable.wrap(observableSource).materialize().subscribe(cVar);
                return cVar;
            default:
                return new h(observableSource, new i());
        }
    }
}
