package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Flowable {
    public final MaybeSource e;

    public g(Maybe maybe) {
        this.e = maybe;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new MaybeToFlowable$MaybeToFlowableSubscriber(rg2Var));
    }
}
