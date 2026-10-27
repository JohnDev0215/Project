package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.ScalarSubscription;
import java.util.Objects;
import p.az1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends Flowable {
    public final Object e;
    public final io.reactivex.rxjava3.functions.o k;

    public p0(io.reactivex.rxjava3.functions.o oVar, Object obj) {
        this.e = obj;
        this.k = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        EmptySubscription emptySubscription = EmptySubscription.c;
        try {
            Object objApply = this.k.apply(this.e);
            Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
            az1 az1Var = (az1) objApply;
            if (!(az1Var instanceof io.reactivex.rxjava3.functions.q)) {
                az1Var.subscribe(rg2Var);
                return;
            }
            try {
                Object obj = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                if (obj != null) {
                    rg2Var.onSubscribe(new ScalarSubscription(obj, rg2Var));
                } else {
                    rg2Var.onSubscribe(emptySubscription);
                    rg2Var.onComplete();
                }
            } catch (Throwable th) {
                he1.R(th);
                rg2Var.onSubscribe(emptySubscription);
                rg2Var.onError(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            rg2Var.onSubscribe(emptySubscription);
            rg2Var.onError(th2);
        }
    }
}
