package io.reactivex.rxjava3.internal.operators.flowable;

import java.util.Objects;
import p.he1;
import p.rg2;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
public final class g extends io.reactivex.rxjava3.internal.subscribers.b implements io.reactivex.rxjava3.operators.a {
    public final io.reactivex.rxjava3.functions.o n;
    public final io.reactivex.rxjava3.functions.d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f72p;
    public boolean q;

    public g(rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.d dVar) {
        super(rg2Var);
        this.n = oVar;
        this.o = dVar;
    }

    @Override // io.reactivex.rxjava3.operators.a
    public final boolean d(Object obj) {
        if (this.l) {
            return false;
        }
        int i = this.m;
        rg2 rg2Var = this.c;
        if (i != 0) {
            rg2Var.onNext(obj);
            return true;
        }
        try {
            Object objApply = this.n.apply(obj);
            if (this.q) {
                io.reactivex.rxjava3.functions.d dVar = this.o;
                Object obj2 = this.f72p;
                ((u11) dVar).getClass();
                boolean zEquals = Objects.equals(obj2, objApply);
                this.f72p = objApply;
                if (zEquals) {
                    return false;
                }
            } else {
                this.q = true;
                this.f72p = objApply;
            }
            rg2Var.onNext(obj);
            return true;
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
        while (true) {
            Object objPoll = this.k.poll();
            if (objPoll == null) {
                return null;
            }
            Object objApply = this.n.apply(objPoll);
            if (!this.q) {
                this.q = true;
                this.f72p = objApply;
                return objPoll;
            }
            Object obj = this.f72p;
            ((u11) this.o).getClass();
            if (!Objects.equals(obj, objApply)) {
                this.f72p = objApply;
                return objPoll;
            }
            this.f72p = objApply;
            if (this.m != 1) {
                this.e.a(1L);
            }
        }
    }
}
