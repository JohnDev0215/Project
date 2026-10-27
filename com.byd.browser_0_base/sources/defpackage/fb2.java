package defpackage;

import J.N;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.messages.a;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: RequestDesktopUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fb2 {
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(boolean r6, double r7, int r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fb2.a(boolean, double, int, boolean):void");
    }

    public static boolean b(Profile profile, lb1 lb1Var, AppCompatActivity appCompatActivity) {
        if (lb1Var == null || !SharedPreferencesManager.getInstance().contains("Chrome.RequestDesktopSiteGlobalSetting.DefaultEnabled") || !N.MJSt3Ocq(profile, 73)) {
            return false;
        }
        sk skVar = bq.a;
        int i = 1;
        if (!N.M6bsIDpc("RequestDesktopSiteDefaults", "show_message_on_default_on", true)) {
            return false;
        }
        xe3 xe3VarT = oj2.t(profile);
        if (!xe3VarT.shouldTriggerHelpUI("IPH_RequestDesktopSiteDefaultOn")) {
            return false;
        }
        Resources resources = appCompatActivity.getResources();
        PropertyModel.b bVar = new PropertyModel.b(ng1.H);
        bVar.c(ng1.a, 33);
        bVar.e(ng1.f, resources.getString(R$string.rds_global_default_on_message_title));
        bVar.c(ng1.m, R$drawable.ic_desktop_windows);
        bVar.e(ng1.c, resources.getString(R$string.rds_global_default_on_message_button));
        bVar.e(ng1.d, new cd0(appCompatActivity, i, xe3VarT));
        bVar.e(ng1.x, new lj1(27, xe3VarT));
        ((a) lb1Var).c(bVar.a(), false);
        return true;
    }

    public static void c(Profile profile, GURL gurl, boolean z) {
        boolean z2 = Profile.c(profile) == 1;
        String strMAShzwTG = N.MAShzwTG(gurl.j());
        WebsitePreferenceBridge.b(profile, 73, gurl.e(), "*", 0);
        WebsitePreferenceBridge.b(profile, 73, strMAShzwTG, "*", (z2 || z != (N.M7ddkyN4(profile, 73) == 1)) ? z ? 1 : 2 : 0);
    }

    public static void d(AppCompatActivity appCompatActivity, double d, String str) {
        sk skVar = bq.a;
        if (N.M09VlOh_("RequestDesktopSiteDefaultsLogging")) {
            ya0 ya0VarA = ya0.a(appCompatActivity);
            Configuration configuration = appCompatActivity.getResources().getConfiguration();
            String strK = String.format(Locale.US, str.concat(", silently reporting crashes for debugging, displaySizeInInches: %.1f displayWidth: %d displayHeight: %d xdpi: %.1f ydpi: %.1f densityDpi: %d screenWidthDp: %d screenHeightDp: %d"), Double.valueOf(d), Integer.valueOf(ya0VarA.c.x), Integer.valueOf(ya0VarA.c.y), Float.valueOf(ya0VarA.e), Float.valueOf(ya0VarA.f), Integer.valueOf(configuration.densityDpi), Integer.valueOf(configuration.screenWidthDp), Integer.valueOf(configuration.screenHeightDp));
            String strG = SharedPreferencesManager.getInstance().g("Chrome.RequestDesktopSiteGlobalSetting.DefaultOnCohortDisplaySpec", "");
            if (!strG.isEmpty()) {
                strK = C0175b.k(strK, " ", strG);
            }
            ChromePureJavaExceptionReporter.d(new Throwable(strK));
        }
    }
}
