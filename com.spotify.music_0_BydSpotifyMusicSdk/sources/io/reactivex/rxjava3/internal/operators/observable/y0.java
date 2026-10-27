package io.reactivex.rxjava3.internal.operators.observable;

/* JADX INFO: loaded from: classes.dex */
public final class y0 implements Runnable {
    public final Throwable c;
    public final /* synthetic */ z0 e;

    public y0(z0 z0Var, Throwable th) {
        this.e = z0Var;
        this.c = th;
    }

    @Override // java.lang.Runnable
    public final void run() {
        z0 z0Var = this.e;
        try {
            z0Var.c.onError(this.c);
        } finally {
            z0Var.l.dispose();
        }
    }
}
