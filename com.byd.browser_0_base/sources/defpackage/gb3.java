package defpackage;

import defpackage.wm3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ToggleTabStackButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gb3 extends nh0 {
    public final /* synthetic */ ib3 e;

    public gb3(ib3 ib3Var) {
        this.e = ib3Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        ib3 ib3Var = this.e;
        ToggleTabStackButton toggleTabStackButton = ib3Var.c;
        if (toggleTabStackButton == null || !toggleTabStackButton.isShown() || ib3Var.e.getAsBoolean()) {
            return;
        }
        qs1<Boolean> qs1Var = ib3Var.f;
        if (qs1Var.get() == null || qs1Var.get().booleanValue()) {
            return;
        }
        wm3.a aVar = new wm3.a(1);
        aVar.b = true;
        hw0 hw0Var = new hw0(ib3Var.b.getResources(), "IPH_TabSwitcherButton", R$string.iph_tab_switcher_text, R$string.iph_tab_switcher_accessibility_text);
        hw0Var.j = toggleTabStackButton;
        hw0Var.k = new f52(14, ib3Var);
        hw0Var.m = new d92(15, ib3Var);
        hw0Var.q = aVar;
        ib3Var.d.a(hw0Var.a());
    }
}
