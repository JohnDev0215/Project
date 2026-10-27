package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: InterceptNavigationDelegateTabHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x11 implements lk3 {
    public final u11 e;

    public x11(TabImpl tabImpl) {
        u11 u11Var = new u11(tabImpl);
        this.e = u11Var;
        u11Var.c = new w11(u11Var);
        tabImpl.x(u11Var.b);
    }

    @Override // defpackage.lk3
    public final void destroy() {
        u11 u11Var = this.e;
        u11Var.a.t(u11Var.b);
        u11Var.c = null;
    }
}
