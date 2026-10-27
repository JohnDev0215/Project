package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.about_settings.AboutChromeSettings;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataTabsFragment;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: d, reason: case insensitive filesystem */
/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0179d implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0179d(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((AboutChromeSettings) obj).r(preference);
                break;
            case 1:
                PrivacySettings privacySettings = (PrivacySettings) obj;
                int i2 = PrivacySettings.i0;
                new dk3(privacySettings.D(), true, new lj1(15, privacySettings)).a();
                break;
            case 2:
                od2 od2Var = (od2) obj;
                od2Var.getClass();
                n3.X0("Settings.SafetyCheck.ManagePasswords");
                oj2.U(2, 10, "Settings.SafetyCheck.Interactions");
                ((e) n3.i0(od2Var.c)).b(preference.e, 1);
                break;
            case 3:
                AllSiteSettings allSiteSettings = (AllSiteSettings) obj;
                int i3 = AllSiteSettings.q0;
                zn2 zn2Var = allSiteSettings.h0;
                FragmentActivity fragmentActivityD = allSiteSettings.D();
                ((kr) zn2Var).getClass();
                String name = ClearBrowsingDataTabsFragment.class.getName();
                Intent intent = new Intent();
                intent.setClass(fragmentActivityD, SettingsActivity.class);
                if (!(fragmentActivityD instanceof Activity)) {
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                }
                intent.putExtra("show_fragment", name);
                ComponentName componentName = q11.a;
                try {
                    fragmentActivityD.startActivity(intent, null);
                } catch (ActivityNotFoundException unused) {
                }
                break;
            default:
                SingleCategorySettings singleCategorySettings = (SingleCategorySettings) obj;
                int i4 = SingleCategorySettings.w0;
                zn2 zn2Var2 = singleCategorySettings.h0;
                FragmentActivity fragmentActivityD2 = singleCategorySettings.D();
                ((kr) zn2Var2).getClass();
                ((hu0) hu0.a()).c(fragmentActivityD2, fragmentActivityD2.getString(R$string.help_context_protected_content), null, Profile.d());
                break;
        }
        return true;
    }
}
