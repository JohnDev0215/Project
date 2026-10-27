package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class s0 extends io.reactivex.rxjava3.observers.a {
    public final t0 e;
    public final long k;
    public final Object l;
    public boolean m;
    public final AtomicBoolean n = new AtomicBoolean();

    public s0(t0 t0Var, long j, Object obj) {
        this.e = t0Var;
        this.k = j;
        this.l = obj;
    }

    public final void a() {
        if (this.n.compareAndSet(false, true)) {
            t0 t0Var = this.e;
            long j = this.k;
            Object obj = this.l;
            if (j == t0Var.m) {
                t0Var.c.onNext(obj);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.m) {
            return;
        }
        this.m = true;
        a();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
        } else {
            this.m = true;
            this.e.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        this.m = true;
        dispose();
        a();
    }
}
