package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class n2 implements io.reactivex.rxjava3.functions.q {
    public final Observable c;

    public n2(Observable observable) {
        this.c = observable;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.c.replay();
    }
}
