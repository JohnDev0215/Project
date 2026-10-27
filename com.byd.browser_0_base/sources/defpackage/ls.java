package defpackage;

import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ls implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ ls(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                return chromeTabbedActivity.z2();
            case 1:
                n3 n3Var = ChromeTabbedActivity.S1;
                return Boolean.valueOf(chromeTabbedActivity.E3());
            case 2:
                return chromeTabbedActivity.P;
            case 3:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                g gVar = chromeTabbedActivity.N0.x;
                if (gVar == null) {
                    return null;
                }
                return gVar.b0;
            case 4:
                n3 n3Var3 = ChromeTabbedActivity.S1;
                if (!(!chromeTabbedActivity.S && zb2.k(chromeTabbedActivity)) || chromeTabbedActivity.h1.r()) {
                    return Boolean.FALSE;
                }
                ds2.b(chromeTabbedActivity.v2());
                chromeTabbedActivity.G3(6, 0);
                return Boolean.TRUE;
            case 5:
                return Boolean.valueOf(chromeTabbedActivity.U);
            case 6:
                return ChromeTabbedActivity.d3(chromeTabbedActivity);
            default:
                return Long.valueOf(chromeTabbedActivity.R);
        }
    }
}
