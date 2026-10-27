package p;

import androidx.appcompat.widget.Toolbar;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bm2 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Toolbar e;

    public /* synthetic */ bm2(Toolbar toolbar, int i) {
        this.c = i;
        this.e = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Toolbar toolbar = this.e;
        switch (i) {
            case 0:
                toolbar.n();
                break;
            default:
                dm2 dm2Var = toolbar.T;
                xi1 xi1Var = dm2Var == null ? null : dm2Var.e;
                if (xi1Var != null) {
                    xi1Var.collapseActionView();
                }
                break;
        }
    }
}
