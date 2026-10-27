package defpackage;

import android.text.TextUtils;
import android.util.Log;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class os implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ os(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                ((a23) chromeTabbedActivity.G2()).g(true).b();
                break;
            case 1:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.u3();
                break;
            case 2:
                n3 n3Var3 = ChromeTabbedActivity.S1;
                if (!chromeTabbedActivity.d()) {
                    oj2.O("Android.PartnerCustomizationInitializedBeforeInitialTab", PartnerBrowserCustomizations.b().d);
                    Log.i("cr_ChromeTabbedActivity", "#createInitialTab executed.");
                    chromeTabbedActivity.u1 = false;
                    qv0 qv0VarD = qv0.d();
                    qv0VarD.a.l("homepage", false);
                    qv0VarD.f();
                    boolean zF3 = chromeTabbedActivity.F3();
                    if (!zF3) {
                        String strC = qv0.c();
                        if (TextUtils.isEmpty(strC) || bk3.k(strC)) {
                            strC = "chrome-native://newtab/";
                        }
                        chromeTabbedActivity.p0(false).g(11, strC);
                    }
                    int i2 = chromeTabbedActivity.K.l;
                    if (i2 == 2 || i2 == 3) {
                        chromeTabbedActivity.A3(zF3);
                    }
                    Tab tabV2 = chromeTabbedActivity.v2();
                    boolean z = (tabV2 != null && bk3.l(tabV2.getUrl())) && !tabV2.isIncognito();
                    t6 t6Var = chromeTabbedActivity.L1;
                    if (!t6Var.l) {
                        t6Var.a(z, false);
                    }
                    t6Var.k = false;
                }
                break;
            default:
                n3 n3Var4 = ChromeTabbedActivity.S1;
                n3.l1(chromeTabbedActivity, chromeTabbedActivity.D2().isIncognito());
                break;
        }
    }
}
