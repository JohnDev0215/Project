package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class w0 implements FlowableSubscriber, tg2 {
    public final rg2 c;
    public final io.reactivex.rxjava3.functions.p e;
    public tg2 k;
    public boolean l;

    public w0(rg2 rg2Var, io.reactivex.rxjava3.functions.p pVar) {
        this.c = rg2Var;
        this.e = pVar;
    }

    @Override // p.tg2
    public final void a(long j) {
        this.k.a(j);
    }

    @Override // p.tg2
    public final void cancel() {
        this.k.cancel();
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.c.onComplete();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
        } else {
            this.l = true;
            this.c.onError(th);
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        try {
            boolean zTest = this.e.test(obj);
            rg2 rg2Var = this.c;
            if (zTest) {
                rg2Var.onNext(obj);
                return;
            }
            this.l = true;
            this.k.cancel();
            rg2Var.onComplete();
        } catch (Throwable th) {
            he1.R(th);
            this.k.cancel();
            onError(th);
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.k, tg2Var)) {
            this.k = tg2Var;
            this.c.onSubscribe(this);
        }
    }
}
