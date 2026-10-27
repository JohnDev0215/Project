package p;

/* JADX INFO: loaded from: classes.dex */
public final class ig1 implements Runnable {
    public final /* synthetic */ jg1 c;

    public ig1(jg1 jg1Var) {
        this.c = jg1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        jg1 jg1Var = this.c;
        jg1Var.g.m.remove(jg1Var.d.a());
    }
}
