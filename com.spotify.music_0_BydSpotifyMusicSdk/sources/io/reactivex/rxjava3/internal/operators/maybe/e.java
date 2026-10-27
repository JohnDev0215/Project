package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.functions.o;
import p.ux1;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {
    public final o e;

    public e(Maybe maybe, ux1 ux1Var) {
        super(maybe);
        this.e = ux1Var;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        this.c.subscribe(new d(maybeObserver, this.e));
    }
}
