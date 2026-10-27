package r;

import androidx.appcompat.widget.Toolbar;
import q.MenuItemC2152i;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Toolbar f21560m;

    public /* synthetic */ t0(Toolbar toolbar, int i7) {
        this.l = i7;
        this.f21560m = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                w0 w0Var = this.f21560m.f11371V;
                MenuItemC2152i menuItemC2152i = w0Var == null ? null : w0Var.f21566m;
                if (menuItemC2152i != null) {
                    menuItemC2152i.collapseActionView();
                }
                break;
            default:
                this.f21560m.l();
                break;
        }
    }
}
