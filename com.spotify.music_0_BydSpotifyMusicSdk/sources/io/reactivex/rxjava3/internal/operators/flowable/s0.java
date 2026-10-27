package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class s0 extends Single implements io.reactivex.rxjava3.internal.fuseable.c {
    public final Flowable c;
    public final Object e = null;

    public s0(Flowable flowable) {
        this.c = flowable;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.c
    public final Flowable b() {
        return new q0(this.c, this.e);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        this.c.subscribe((FlowableSubscriber) new r0(singleObserver, this.e));
    }
}
