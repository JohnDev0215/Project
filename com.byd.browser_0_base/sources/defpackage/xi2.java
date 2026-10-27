package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xi2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ xi2(int i, int i2) {
        this.e = i2;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        int i2 = this.f;
        switch (i) {
            case 0:
                N.MMtVSAe3(i2, null);
                break;
            case 1:
                oj2.t(Profile.d()).e(vk0.a(i2));
                break;
            default:
                lc lcVarL = lc.L();
                if (i2 >= 0) {
                    lcVarL.getClass();
                    lc.J(i2, "Servicification.Startup3");
                } else {
                    lcVarL.getClass();
                }
                break;
        }
    }
}
