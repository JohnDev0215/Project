package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class dp implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeActivity f;

    public /* synthetic */ dp(ChromeActivity chromeActivity, int i) {
        this.e = i;
        this.f = chromeActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeActivity chromeActivity = this.f;
        switch (i) {
            case 0:
                return chromeActivity.q0();
            case 1:
            default:
                return chromeActivity.F0;
            case 2:
                return chromeActivity.q0();
        }
    }
}
