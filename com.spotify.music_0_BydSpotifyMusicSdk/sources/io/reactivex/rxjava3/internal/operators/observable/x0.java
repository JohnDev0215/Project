package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class x0 implements Runnable {
    public final /* synthetic */ z0 c;

    public x0(z0 z0Var) {
        this.c = z0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        z0 z0Var = this.c;
        try {
            z0Var.c.onComplete();
        } finally {
            z0Var.l.dispose();
        }
    }
}
