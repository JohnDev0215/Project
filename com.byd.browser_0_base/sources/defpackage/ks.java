package defpackage;

import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ks implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ ks(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                return chromeTabbedActivity.N0.x;
            case 1:
                return chromeTabbedActivity.getIntent();
            case 2:
                n3 n3Var = ChromeTabbedActivity.S1;
                return Boolean.valueOf(chromeTabbedActivity.R1());
            case 3:
                chromeTabbedActivity.getClass();
                return 0;
            case 4:
                return Boolean.valueOf(chromeTabbedActivity.N2());
            default:
                return chromeTabbedActivity.q0();
        }
    }
}
