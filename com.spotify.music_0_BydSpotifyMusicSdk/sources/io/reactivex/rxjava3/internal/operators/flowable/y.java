package io.reactivex.rxjava3.internal.operators.flowable;

import java.util.Objects;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class y extends io.reactivex.rxjava3.internal.subscribers.b {
    public final io.reactivex.rxjava3.functions.o n;

    public y(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
        super(rg2Var);
        this.n = oVar;
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        int i = this.m;
        rg2 rg2Var = this.c;
        if (i != 0) {
            rg2Var.onNext(null);
            return;
        }
        try {
            Object objApply = this.n.apply(obj);
            Objects.requireNonNull(objApply, "The mapper function returned a null value.");
            rg2Var.onNext(objApply);
        } catch (Throwable th) {
            he1.R(th);
            this.e.cancel();
            onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Object objPoll = this.k.poll();
        if (objPoll == null) {
            return null;
        }
        Object objApply = this.n.apply(objPoll);
        Objects.requireNonNull(objApply, "The mapper function returned a null value.");
        return objApply;
    }
}
