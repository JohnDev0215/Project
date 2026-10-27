package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ActivityTabProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class a3 extends h23 {
    public final /* synthetic */ z2 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a3(z2 z2Var, a23 a23Var) {
        super(a23Var);
        this.g = z2Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        this.g.s(tab);
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        z2 z2Var = this.g;
        if (z2Var.j.z().getCount() <= 1) {
            z2Var.s(null);
        }
    }
}
