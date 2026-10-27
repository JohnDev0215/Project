package p;

/* JADX INFO: loaded from: classes.dex */
public final class g1 implements Runnable {
    public final j1 c;
    public final lb1 e;

    public g1(j1 j1Var, lb1 lb1Var) {
        this.c = j1Var;
        this.e = lb1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.c.c != this) {
            return;
        }
        if (j1.n.n(this.c, this, j1.f(this.e))) {
            j1.c(this.c);
        }
    }
}
