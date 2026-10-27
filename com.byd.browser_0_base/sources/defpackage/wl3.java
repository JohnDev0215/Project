package defpackage;

import defpackage.r12;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class wl3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ am3 f;

    public /* synthetic */ wl3(am3 am3Var, int i) {
        this.e = i;
        this.f = am3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        am3 am3Var = this.f;
        switch (i) {
            case 0:
                r12.b bVar = am3Var.i.c;
                if (((float) bVar.c) > ((float) bVar.b) * 0.5f) {
                    n3.b1(am3Var.f.a, 4);
                }
                n3.a1(am3Var.f.a, 1);
                am3Var.b.d(new n62(am3Var.f, 20, new se3(11, am3Var)));
                break;
            case 1:
                n3.a1(am3Var.f.a, 4);
                am3Var.h.run();
                break;
            default:
                am3Var.getClass();
                am3Var.c.l(bm3.b, false);
                break;
        }
    }
}
