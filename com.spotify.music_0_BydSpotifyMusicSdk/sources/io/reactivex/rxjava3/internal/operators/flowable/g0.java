package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends Maybe implements io.reactivex.rxjava3.internal.fuseable.c {
    public final Flowable c;
    public final io.reactivex.rxjava3.functions.c e;

    public g0(o oVar) {
        zb0 zb0Var = zb0.f216p;
        this.c = oVar;
        this.e = zb0Var;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.c
    public final Flowable b() {
        return new e0(this.c, this.e, 0);
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        this.c.subscribe((FlowableSubscriber) new f0(maybeObserver, this.e));
    }
}
