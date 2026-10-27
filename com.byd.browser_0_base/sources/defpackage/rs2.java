package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StaticLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class rs2 extends h23 {
    public final /* synthetic */ ts2 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rs2(ts2 ts2Var, y13 y13Var) {
        super(y13Var);
        this.g = ts2Var;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        ts2 ts2Var = this.g;
        if (ts2Var.f98J) {
            ts2Var.P(tab);
            if (!ts2Var.f98J || tab.getView() == null) {
                return;
            }
            tab.getView().requestFocus();
        }
    }
}
