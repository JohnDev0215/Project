package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xv1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ yv1 f;

    public /* synthetic */ xv1(yv1 yv1Var, int i) {
        this.e = i;
        this.f = yv1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        yv1 yv1Var = this.f;
        switch (i) {
            case 0:
                yv1Var.u();
                yv1Var.J();
                yv1Var.y();
                break;
            default:
                yv1Var.p.run();
                yv1Var.p = null;
                break;
        }
    }
}
