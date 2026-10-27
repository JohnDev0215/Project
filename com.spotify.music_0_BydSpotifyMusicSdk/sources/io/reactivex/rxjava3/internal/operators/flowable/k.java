package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class k extends Single implements io.reactivex.rxjava3.internal.fuseable.c {
    public final Flowable c;
    public final long e = 0;
    public final Object k = null;

    public k(Flowable flowable) {
        this.c = flowable;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.c
    public final Flowable b() {
        return new i(this.c, this.e, this.k);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        this.c.subscribe((FlowableSubscriber) new j(singleObserver, this.e, this.k));
    }
}
