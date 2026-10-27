package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

/* JADX INFO: loaded from: classes.dex */
public final class g2 extends Completable implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;

    public g2(ObservableSource observableSource) {
        this.c = observableSource;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new o0(this.c, 3);
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        this.c.subscribe(new f2(completableObserver));
    }
}
