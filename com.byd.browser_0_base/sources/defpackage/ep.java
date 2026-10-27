package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ep implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeActivity f;

    public /* synthetic */ ep(ChromeActivity chromeActivity, int i) {
        this.e = i;
        this.f = chromeActivity;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        ChromeActivity chromeActivity = this.f;
        switch (i) {
            case 0:
                return Boolean.valueOf(chromeActivity.U);
            default:
                return Boolean.valueOf(chromeActivity.M2());
        }
    }
}
