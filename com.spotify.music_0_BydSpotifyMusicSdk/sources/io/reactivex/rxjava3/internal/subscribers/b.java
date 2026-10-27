package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.d;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements FlowableSubscriber, d {
    public final rg2 c;
    public tg2 e;
    public d k;
    public boolean l;
    public int m;

    public b(rg2 rg2Var) {
        this.c = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        this.e.a(j);
    }

    @Override // io.reactivex.rxjava3.operators.c
    public int b(int i) {
        return c(i);
    }

    public final int c(int i) {
        d dVar = this.k;
        if (dVar == null || (i & 4) != 0) {
            return 0;
        }
        int iB = dVar.b(i);
        if (iB != 0) {
            this.m = iB;
        }
        return iB;
    }

    @Override // p.tg2
    public final void cancel() {
        this.e.cancel();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.k.clear();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.k.isEmpty();
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
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
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.e, tg2Var)) {
            this.e = tg2Var;
            if (tg2Var instanceof d) {
                this.k = (d) tg2Var;
            }
            this.c.onSubscribe(this);
        }
    }
}
