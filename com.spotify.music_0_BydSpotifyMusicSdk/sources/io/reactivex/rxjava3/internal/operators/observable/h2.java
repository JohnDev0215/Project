package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class h2 implements io.reactivex.rxjava3.functions.q {
    public final Observable c;
    public final int e;
    public final boolean k;

    public h2(Observable observable, int i, boolean z) {
        this.c = observable;
        this.e = i;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.c.replay(this.e, this.k);
    }
}
