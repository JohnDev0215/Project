package defpackage;

import defpackage.nj1;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: MultiInstanceManagerApi31.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oj1 extends h23 {
    public final /* synthetic */ y13 g;
    public final /* synthetic */ nj1 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public oj1(nj1 nj1Var, a23 a23Var, a23 a23Var2) {
        super(a23Var);
        this.h = nj1Var;
        this.g = a23Var2;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        nj1 nj1Var = this.h;
        Tab tab2 = nj1Var.y;
        if (tab2 == tab) {
            return;
        }
        nj1.a aVar = nj1Var.z;
        if (tab2 != null) {
            tab2.t(aVar);
        }
        nj1Var.y = tab;
        tab.x(aVar);
        SharedPreferencesManager.getInstance().l(ar.g.b(String.valueOf(nj1Var.w)), nj1Var.y.isIncognito());
        Tab tabP = nj1Var.y.isIncognito() ? z7.p(this.g.g(false)) : nj1Var.y;
        if (tabP == null) {
            SharedPreferencesManager.getInstance().o(ar.k.b(String.valueOf(nj1Var.w)), "");
            nj1.z(nj1Var.w, "");
        } else {
            SharedPreferencesManager.getInstance().o(ar.k.b(String.valueOf(nj1Var.w)), tabP.v().j());
            nj1.z(nj1Var.w, tabP.getTitle());
        }
    }

    @Override // defpackage.t13
    public final void V0(Tab tab) {
        nj1.y(this.h.w, this.g);
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        nj1.y(this.h.w, this.g);
    }

    @Override // defpackage.t13
    public final void n0(Tab tab) {
        nj1.y(this.h.w, this.g);
    }
}
