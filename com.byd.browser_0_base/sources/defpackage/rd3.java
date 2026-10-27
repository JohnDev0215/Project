package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TopToolbarOverlayMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rd3 extends nh0 {
    public final /* synthetic */ td3 e;

    public rd3(td3 td3Var) {
        this.e = td3Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void M(int i, Tab tab) {
        this.e.c(tab);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l1(Tab tab, float f) {
        this.e.a();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        td3 td3Var = this.e;
        td3Var.d();
        td3Var.c(tab);
    }
}
