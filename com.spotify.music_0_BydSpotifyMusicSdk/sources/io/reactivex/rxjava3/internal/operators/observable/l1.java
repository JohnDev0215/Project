package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

/* JADX INFO: loaded from: classes.dex */
public final class l1 extends Maybe implements io.reactivex.rxjava3.internal.fuseable.d {
    public final ObservableSource c;
    public final long e;

    public l1(ObservableSource observableSource, long j) {
        this.c = observableSource;
        this.e = j;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.d
    public final Observable a() {
        return new j1(this.c, this.e, null, false);
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        this.c.subscribe(new k1(maybeObserver, this.e));
    }
}
