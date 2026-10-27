package defpackage;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class yl3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ am3 f;

    public /* synthetic */ yl3(am3 am3Var, int i) {
        this.e = i;
        this.f = am3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        am3 am3Var = this.f;
        switch (i) {
            case 0:
                n3.a1(am3Var.f.a, 2);
                am3Var.g.onResult(am3Var.f);
                break;
            default:
                am3Var.getClass();
                am3Var.c.l(bm3.b, false);
                int i2 = am3Var.f.a;
                am3Var.b.b(new jb1(29, am3Var), i2);
                break;
        }
    }
}
