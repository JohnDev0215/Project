package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.operators.flowable.u;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Flowable {
    public final CompletableSource e;

    public m(CompletableSource completableSource) {
        this.e = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(new u(rg2Var));
    }
}
