package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;

/* JADX INFO: loaded from: classes.dex */
public final class l2 implements io.reactivex.rxjava3.functions.a {
    public final Observer c;

    public l2(Observer observer) {
        this.c = observer;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        this.c.onComplete();
    }
}
