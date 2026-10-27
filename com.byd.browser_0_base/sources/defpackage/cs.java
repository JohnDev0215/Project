package defpackage;

import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cs implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ cs(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                g63 g63Var = (g63) chromeTabbedActivity.N0;
                if (g63Var.W0 == null) {
                    g63Var.W0 = new bw(new lj[0]);
                }
                return g63Var.W0;
            case 1:
                return chromeTabbedActivity.E2();
            case 2:
                return Boolean.valueOf(chromeTabbedActivity.S);
            default:
                return ChromeTabbedActivity.d3(chromeTabbedActivity);
        }
    }
}
