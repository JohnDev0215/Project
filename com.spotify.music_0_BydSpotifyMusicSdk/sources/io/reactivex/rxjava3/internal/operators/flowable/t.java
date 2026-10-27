package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
import p.a30;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class t extends Flowable implements io.reactivex.rxjava3.functions.q {
    public final Callable e;

    public t(a30 a30Var) {
        this.e = a30Var;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() throws Exception {
        Object objCall = this.e.call();
        Objects.requireNonNull(objCall, "The callable returned a null value");
        return objCall;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        DeferredScalarSubscription deferredScalarSubscription = new DeferredScalarSubscription(rg2Var);
        rg2Var.onSubscribe(deferredScalarSubscription);
        try {
            Object objCall = this.e.call();
            Objects.requireNonNull(objCall, "The callable returned a null value");
            deferredScalarSubscription.c(objCall);
        } catch (Throwable th) {
            he1.R(th);
            if (deferredScalarSubscription.get() == 4) {
                RxJavaPlugins.b(th);
            } else {
                rg2Var.onError(th);
            }
        }
    }
}
