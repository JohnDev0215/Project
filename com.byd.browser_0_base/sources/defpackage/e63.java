package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e63 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ g63 f;

    public /* synthetic */ e63(g63 g63Var, int i) {
        this.e = i;
        this.f = g63Var;
    }

    @Override // defpackage.nw2
    public final Object get() {
        z2 z2Var;
        int i = this.e;
        g63 g63Var = this.f;
        switch (i) {
            case 0:
                if (g63Var.g == null || (z2Var = g63Var.k) == null) {
                    return Boolean.FALSE;
                }
                Tab tab = (Tab) z2Var.f;
                return Boolean.valueOf((tab == null || !tab.isUserInteractable() || tab.isNativePage()) ? false : true);
            default:
                return new vo1(g63Var.g, g63Var.B);
        }
    }
}
