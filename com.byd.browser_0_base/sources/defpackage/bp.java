package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bp implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeActivity f;

    public /* synthetic */ bp(ChromeActivity chromeActivity, int i) {
        this.e = i;
        this.f = chromeActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeActivity chromeActivity = this.f;
        switch (i) {
            case 0:
                return Integer.valueOf(chromeActivity.w2());
            case 1:
                return Boolean.valueOf(chromeActivity.N2());
            case 2:
                return chromeActivity.q0();
            case 3:
            default:
                return chromeActivity.P;
            case 4:
                return chromeActivity.C2();
        }
    }
}
