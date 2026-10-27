package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import java.util.concurrent.atomic.AtomicReference;
import p.az1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class n0 extends io.reactivex.rxjava3.flowables.a {
    public static final /* synthetic */ int n = 0;
    public final Flowable e;
    public final AtomicReference k;
    public final io.reactivex.rxjava3.functions.q l;
    public final az1 m;

    public n0(m0 m0Var, Flowable flowable, AtomicReference atomicReference, io.reactivex.rxjava3.functions.q qVar) {
        this.m = m0Var;
        this.e = flowable;
        this.k = atomicReference;
        this.l = qVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.m.subscribe(rg2Var);
    }

    @Override // io.reactivex.rxjava3.flowables.a
    public final void o() {
        AtomicReference atomicReference = this.k;
        FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber = (FlowableReplay$ReplaySubscriber) atomicReference.get();
        if (flowableReplay$ReplaySubscriber == null || !flowableReplay$ReplaySubscriber.isDisposed()) {
            return;
        }
        while (!atomicReference.compareAndSet(flowableReplay$ReplaySubscriber, null) && atomicReference.get() == flowableReplay$ReplaySubscriber) {
        }
    }
}
