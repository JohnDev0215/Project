package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Iterable {
    public final ObservableSource c;
    public final Object e;

    public g(ObservableSource observableSource, Object obj) {
        this.c = observableSource;
        this.e = obj;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Object obj = this.e;
        f fVar = new f();
        fVar.e = obj;
        this.c.subscribe(fVar);
        return new e(fVar);
    }
}
