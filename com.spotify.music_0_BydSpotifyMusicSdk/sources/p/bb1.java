package p;

import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bb1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ ib1 e;

    public /* synthetic */ bb1(ib1 ib1Var, int i) {
        this.c = i;
        this.e = ib1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        ib1 ib1Var = this.e;
        switch (i) {
            case 1:
                ib0 ib0Var = ib1Var.k;
                if (ib0Var != null) {
                    ib0Var.setListSelectionHidden(true);
                    ib0Var.requestLayout();
                }
                break;
            default:
                ib0 ib0Var2 = ib1Var.k;
                if (ib0Var2 != null) {
                    WeakHashMap weakHashMap = zt2.a;
                    if (lt2.b(ib0Var2) && ib1Var.k.getCount() > ib1Var.k.getChildCount() && ib1Var.k.getChildCount() <= ib1Var.u) {
                        ib1Var.G.setInputMethodMode(2);
                        ib1Var.c();
                        break;
                    }
                }
                break;
        }
    }
}
