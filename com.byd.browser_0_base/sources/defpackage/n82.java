package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.o23;
import java.util.ArrayList;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: QualityEnforcer.java */
/* JADX INFO: loaded from: classes.dex */
public final class n82 {
    public final Activity a;
    public final il3 b;
    public final CustomTabsConnection c;
    public final CustomTabsSessionToken d;
    public final eu e;
    public final oj f;
    public final jg3 g;
    public boolean h;
    public boolean i;

    /* JADX INFO: compiled from: QualityEnforcer.java */
    public class a extends o23.b {
        public a() {
        }

        @Override // o23.b
        public final void A1(Tab tab) {
            n82.a(n82.this, tab.v());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (!navigationHandle.g || navigationHandle.c) {
                return;
            }
            n82 n82Var = n82.this;
            if (!n82Var.h) {
                String strK = n82Var.f.K();
                n82Var.h = true;
                n82Var.b.d(strK).g(new gp(this, 6, tabImpl));
            }
            GURL gurlV = tabImpl.v();
            if (n82.a(n82Var, gurlV)) {
                int i = navigationHandle.m;
                if (i == 404) {
                    n82.b(n82Var, tabImpl, 0, gurlV.j(), navigationHandle.m);
                    return;
                }
                if (i >= 500 && i <= 599) {
                    n82.b(n82Var, tabImpl, 1, gurlV.j(), navigationHandle.m);
                } else if (navigationHandle.l == -106) {
                    n82.b(n82Var, tabImpl, 2, gurlV.j(), navigationHandle.m);
                }
            }
        }
    }

    public n82(Activity activity, o23 o23Var, oj ojVar, CustomTabsConnection customTabsConnection, il3 il3Var, eu euVar, jg3 jg3Var) {
        a aVar = new a();
        this.a = activity;
        this.b = il3Var;
        this.d = ojVar.z();
        this.f = ojVar;
        this.c = customTabsConnection;
        this.e = euVar;
        this.g = jg3Var;
        this.i = true;
        o23Var.c(aVar);
    }

    public static boolean a(n82 n82Var, GURL gurl) {
        n82Var.getClass();
        if (gurl.k()) {
            return false;
        }
        boolean z = n82Var.i;
        s62<Boolean> s62VarD = n82Var.b.d(gurl.j());
        boolean z2 = !s62VarD.d() || s62VarD.b.booleanValue();
        n82Var.i = z2;
        return z && z2;
    }

    public static void b(n82 n82Var, Tab tab, int i, String str, int i2) {
        boolean z;
        String str2;
        String str3;
        n82Var.getClass();
        WebContents webContentsC = tab.c();
        n82Var.g.getClass();
        oj2.U(i, 4, "TrustedWebActivity.QualityEnforcementViolation");
        if (webContentsC != null) {
            N.MCnWTXic(webContentsC, "TrustedWebActivity.QualityEnforcementViolation", "ViolationType", i);
        }
        sk skVar = bq.a;
        boolean zM09VlOh_ = N.M09VlOh_("TrustedWebActivityQualityEnforcementWarning");
        eu euVar = n82Var.e;
        Activity activity = n82Var.a;
        String strJ = "";
        if (zM09VlOh_) {
            String string = (i == 0 || i == 1) ? xz.a.getString(R$string.twa_quality_enforcement_violation_error, Integer.valueOf(i2), str) : i != 2 ? i != 3 ? "" : xz.a.getString(R$string.twa_quality_enforcement_violation_asset_link, str) : xz.a.getString(R$string.twa_quality_enforcement_violation_offline, str);
            Context context = xz.a;
            context.getPackageManager();
            if (!((euVar.e == null || xz.a.getPackageManager().getInstallerPackageName(euVar.e) == null) ? false : true)) {
                Toast.b(1, context, string).e();
            }
            if (tab.c() != null) {
                String str4 = null;
                if (i == 3) {
                    String str5 = euVar.e;
                    activity.getPackageManager();
                    ArrayList arrayListB = zt1.b(str5);
                    if (arrayListB != null && arrayListB.size() > 0) {
                        str4 = (String) arrayListB.get(0);
                    }
                    str3 = str4;
                    str2 = str5;
                } else {
                    str2 = null;
                    str3 = null;
                }
                N.M05FuTNz(tab.c().y(), i, str, i2, str2, str3);
            }
        }
        if (N.M09VlOh_("TrustedWebActivityQualityEnforcement")) {
            Bundle bundle = new Bundle();
            if (i == 0 || i == 1) {
                strJ = i2 + " on " + str;
            } else if (i == 2) {
                strJ = C0175b.j("Page unavailable offline: ", str);
            } else if (i == 3) {
                strJ = C0175b.j("Digital asset links verification failed on ", str);
            }
            bundle.putString("crash_reason", strJ);
            Bundle bundleS = n82Var.c.s(n82Var.d, "quality_enforcement.crash", bundle);
            boolean z2 = bundleS != null && bundleS.getBoolean("success");
            if (i == 3) {
                if (euVar.e == null) {
                    z = false;
                } else {
                    z = xz.a.getPackageManager().getInstallerPackageName(euVar.e) != null;
                }
                if (!z) {
                    return;
                }
            }
            if (N.M09VlOh_("TrustedWebActivityQualityEnforcementForced") || z2) {
                oj2.U(i, 4, "TrustedWebActivity.QualityEnforcementViolation.Crashed");
                activity.finish();
            }
        }
    }
}
