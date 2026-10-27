package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class wy1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ az1 f;

    public /* synthetic */ wy1(az1 az1Var, int i) {
        this.e = i;
        this.f = az1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        az1 az1Var = this.f;
        switch (i) {
            case 0:
                az1Var.o = 1;
                az1Var.k.post(az1Var.g);
                break;
            default:
                az1Var.o = 4;
                az1Var.g.run();
                break;
        }
    }
}
