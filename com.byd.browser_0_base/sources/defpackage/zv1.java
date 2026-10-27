package defpackage;

import android.app.Activity;
import android.view.Window;
import defpackage.aw1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zv1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ aw1 f;

    public /* synthetic */ zv1(aw1 aw1Var, int i) {
        this.e = i;
        this.f = aw1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = 1;
        aw1 aw1Var = this.f;
        switch (i) {
            case 0:
                aw1.b bVar = aw1Var.f25J;
                aw1Var.K = bVar.b;
                Runnable runnable = aw1Var.p;
                if (runnable == null) {
                    Activity activity = aw1Var.e;
                    int i3 = activity.getWindow().getAttributes().y;
                    int i4 = bVar.b;
                    int i5 = aw1Var.Q;
                    if (i5 >= 0 && i5 != i3 && (i4 == 0 || i4 == 1)) {
                        oj2.U(bVar.c ? i4 == 0 ? 2 : 3 : i4 == 0 ? 0 : 1, 4, "CustomTabs.ResizeType2");
                    }
                    aw1Var.N();
                    int i6 = aw1Var.K;
                    Window window = activity.getWindow();
                    window.clearFlags(512);
                    aw1Var.R(aw1Var.l - window.getAttributes().y);
                    aw1Var.y();
                    aw1Var.K = i6;
                    Runnable runnable2 = aw1Var.p;
                    if (runnable2 != null) {
                        aw1Var.p = null;
                        aw1Var.i(runnable2);
                    }
                    aw1Var.J();
                    Runnable runnable3 = aw1Var.N;
                    if (runnable3 != null) {
                        runnable3.run();
                        aw1Var.N = null;
                        aw1Var.k.g(new ld(aw1Var, i2));
                    }
                } else {
                    runnable.run();
                }
                break;
            default:
                aw1Var.N();
                aw1Var.O = true;
                break;
        }
    }
}
