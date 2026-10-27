package io.reactivex.rxjava3.internal.operators.flowable;

import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class n extends io.reactivex.rxjava3.internal.subscribers.b implements io.reactivex.rxjava3.operators.a {
    public final io.reactivex.rxjava3.functions.p n;

    public n(rg2 rg2Var, io.reactivex.rxjava3.functions.p pVar) {
        super(rg2Var);
        this.n = pVar;
    }

    @Override // io.reactivex.rxjava3.operators.a
    public final boolean d(Object obj) {
        if (this.l) {
            return false;
        }
        int i = this.m;
        rg2 rg2Var = this.c;
        if (i != 0) {
            rg2Var.onNext(null);
            return true;
        }
        try {
            boolean zTest = this.n.test(obj);
            if (zTest) {
                rg2Var.onNext(obj);
            }
            return zTest;
        } catch (Throwable th) {
            he1.R(th);
            this.e.cancel();
            onError(th);
            return true;
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (d(obj)) {
            return;
        }
        this.e.a(1L);
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        io.reactivex.rxjava3.operators.d dVar = this.k;
        while (true) {
            Object objPoll = dVar.poll();
            if (objPoll == null) {
                return null;
            }
            if (this.n.test(objPoll)) {
                return objPoll;
            }
            if (this.m == 2) {
                dVar.a(1L);
            }
        }
    }
}
