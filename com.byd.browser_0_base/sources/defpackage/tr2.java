package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tr2 implements t13 {
    public final /* synthetic */ yr2 e;

    public tr2(yr2 yr2Var) {
        this.e = yr2Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        if (i == 0 && bk3.l(tab.getUrl())) {
            this.e.s0(false);
        }
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        yr2 yr2Var = this.e;
        if (yr2Var.H()) {
            yr2Var.s0(true);
        }
    }

    @Override // defpackage.t13
    public final void d1(int i, Tab tab) {
        yr2 yr2Var = this.e;
        if (yr2Var.H() && i != 5) {
            yr2Var.C();
        }
        yr2Var.S = (yr2Var.g.g(false).getCount() == 0 && i == 5) ? false : true;
    }

    @Override // defpackage.t13
    public final void p0() {
        yr2 yr2Var = this.e;
        if (yr2Var.h.k(zr2.e) && yr2Var.H()) {
            boolean z = false;
            if (yr2Var.g.g(false).getCount() > 0 && !yr2Var.y) {
                z = true;
            }
            yr2Var.s0(z);
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        yr2 yr2Var = this.e;
        if (!yr2Var.H() || yr2Var.g.g(false).getCount() > 1) {
            return;
        }
        yr2Var.s0(false);
    }
}
