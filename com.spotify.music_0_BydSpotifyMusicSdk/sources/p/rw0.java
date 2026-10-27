package p;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class rw0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ ww0 e;

    public /* synthetic */ rw0(int i, ww0 ww0Var) {
        this.c = i;
        this.e = ww0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewGroup viewGroup;
        ox0 ox0Var;
        int i = this.c;
        ww0 ww0Var = this.e;
        switch (i) {
            case 0:
                if (ww0Var.P != null) {
                    ww0Var.c().getClass();
                }
                break;
            default:
                if (ww0Var.M != null && (viewGroup = ww0Var.L) != null && (ox0Var = ww0Var.z) != null) {
                    df2 df2VarF = df2.f(viewGroup, ox0Var);
                    df2VarF.g();
                    df2VarF.c();
                    break;
                }
                break;
        }
    }
}
