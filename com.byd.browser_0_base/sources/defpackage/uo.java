package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uo implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeActivity f;

    public /* synthetic */ uo(ChromeActivity chromeActivity, int i) {
        this.e = i;
        this.f = chromeActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ChromeActivity chromeActivity = this.f;
        switch (i) {
            case 0:
                k11 k11Var = ChromeActivity.Z0;
                chromeActivity.getClass();
                if (PartnerBrowserCustomizations.isIncognitoDisabled()) {
                    chromeActivity.c3();
                }
                break;
            default:
                k11 k11Var2 = ChromeActivity.Z0;
                chromeActivity.i2();
                break;
        }
    }
}
