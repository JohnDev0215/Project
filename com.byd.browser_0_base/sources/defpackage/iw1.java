package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class iw1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ lw1 f;

    public /* synthetic */ iw1(lw1 lw1Var, int i) {
        this.e = i;
        this.f = lw1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        lw1 lw1Var = this.f;
        switch (i) {
            case 0:
                lw1Var.p(false);
                lw1Var.u();
                lw1Var.y();
                break;
            default:
                lw1Var.I();
                break;
        }
    }
}
