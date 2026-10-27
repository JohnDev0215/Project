package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.az1;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableOnErrorNext$OnErrorNextSubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 4063763155303814625L;
    boolean done;
    final rg2 downstream;
    final io.reactivex.rxjava3.functions.o nextSupplier;
    boolean once;
    long produced;

    public FlowableOnErrorNext$OnErrorNextSubscriber(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = rg2Var;
        this.nextSupplier = oVar;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.once = true;
        this.downstream.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.once) {
            if (this.done) {
                RxJavaPlugins.b(th);
                return;
            } else {
                this.downstream.onError(th);
                return;
            }
        }
        this.once = true;
        try {
            Object objApply = this.nextSupplier.apply(th);
            Objects.requireNonNull(objApply, "The nextSupplier returned a null Publisher");
            az1 az1Var = (az1) objApply;
            long j = this.produced;
            if (j != 0) {
                f(j);
            }
            az1Var.subscribe(this);
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (!this.once) {
            this.produced++;
        }
        this.downstream.onNext(obj);
    }
}
