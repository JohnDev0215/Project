package io.reactivex.rxjava3.internal.operators.observable;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class z implements Runnable {
    public final Collection c;
    public final /* synthetic */ b0 e;

    public z(b0 b0Var, Collection collection) {
        this.e = b0Var;
        this.c = collection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.e) {
            this.e.s.remove(this.c);
        }
        b0 b0Var = this.e;
        b0Var.h(this.c, b0Var.r);
    }
}
